/**********院系AWS账号池 管理*************/

var awsIAMPoolTb = "";
var accountIdCheck="";
var hiddenType=0;
/**
 * 页面初始化
 */
$(function () {
	/*awsIamPool列表初始化*/
	
	addModalShow(); //点击添加按钮，跳出添加页面的弹出框
	
	isUseTypeCheck();//获取是否console登录，如果改变按钮的选中就改变页面的显示情况
	
	selectPayAccount();//显示关联付款账户下拉列表，将下拉列表显示在添加数据的页面上
	
	getPayAccountId();//根据下拉框值的发生改变，来显示对应的账户
	
	iamPoolValidatorForm(); //验证添加院系AWS账号池表单
	
	editIamPoolData();//提交编辑AWS IAM账户信息数据
	
	submitIamPoolData();//提交添加AWS IAM账户信息数据
	
	initAwsIamPoolTB();
	
	uploadExcelData();//导入Excel表格数据
});


/**
 * 校验重置重点在这里 当modal隐藏时销毁验证再重新加载验证
 */
$('#awsIAMPoolModel').on('hidden.bs.modal', function () {
    $('#awsIAMPoolForm').data('bootstrapValidator').destroy();
    $('#awsIAMPoolForm').data('bootstrapValidator', null);
    iamPoolValidatorForm();    //重新加载验证
});


/****************************************
 * 查询
 ****************************************/
function doSearch(){
	
	awsIAMPoolTb.ajax.reload();
}

/**
 * 点击查询获取关联付款账户下拉列表
 */
function doPayAccountSearch(){
	$("#payAccountTbody").empty();

	selectPayAccount();//显示关联付款账户下拉列表，将下拉列表显示在添加数据的页面上
}


/**
 * 点击添加按钮，跳出添加页面的弹出框
 */
function addModalShow() {
	$("#addModalShow").click(function(){
		$("input[type='text']").val("");
		$("#addh4").show();
		$("#edith4").hide();
		$("#submitIamPoolData").show();
		$("#editIamPoolData").hide();
		$("#payAccountDiv").hide();
		$("#payAccountName option:not(:first)").remove();
		$("#payAccountName").val(-1);
		hiddenType=1;
		selectPayAccount();//显示关联付款账户下拉列表
		$("#awsIAMPoolModel").modal('show');
	});
}

/**
 * 显示关联付款账户下拉列表，将下拉列表显示在添加数据的页面上
 */
function selectPayAccount(){
	$.ajax({
		url : contextPath +'/awsaccount/getAllAccountList',
		async : false,
		type : 'post',
		data : {"payAccountData":$("#payAccountData").val()},
		dataType : "json",
		success : function(data) {
			var insertText="";
			var payAccountText="";
			if(data.length>0){
				for(var i = 0;i<data.length;i++){
					insertText += "<tr><td id='"+data[i].accountId+"'>"+data[i].accountName+"</td></tr>";
					payAccountText += "<option value=" + data[i].accountId +">"+data[i].accountName+"</option>";
				}

				if(hiddenType!=1){
					$("#payAccountTbody").append(insertText);
					awsAccountClick(data[0].accountId);//点击表格获取表格的数据，及改变表格的颜色
				}
				
				$("#payAccountName").append(payAccountText);
			}else{
				accountIdCheck="-1";
				awsIAMPoolTb.ajax.reload();
			}
		},
		error : function() {

		}
	})
}

/**
 * 根据下拉框值的发生改变，来显示对应的账户
 */
function getPayAccountId(){
	$("#payAccountName").change(function() {
		var payAccountIdData=$(this).children('option:selected').val();
		if(payAccountIdData==-1){$("#payAccountDiv").hide();}else{$("#payAccountDiv").show();}
		console.log(payAccountIdData)
		$("#payAccountID").text(payAccountIdData);
		$("#payAccountIdHid").val(payAccountIdData);
		$("#payAccountNameHid").val($(this).find("option:selected").text());
		
	});
}

/**
 * 点击表格获取表格的数据，及改变表格的颜色
 */
function awsAccountClick(accountId){
	$("#"+accountId).css({
 		"background-color":"#6fb3e0",
 		"color":"#ffffff",
 	});
	accountIdCheck=accountId;
	
	$("#payAccountTbody td").click(function(){
		$("#payAccountTbody td").css({
	 		"background-color":"",
	 		"color":"#666",
	 	});
	 	$(this).css({
	 		"background-color":"rgba(111, 179, 224, 0.47)",
	 		"color":"#ffffff",
	 	});
	 	accountIdCheck=$(this).attr('id');
	 	awsIAMPoolTb.ajax.reload();
	});
	awsIAMPoolTb.ajax.reload();
}



/**
 * 获取是否console登录，如果改变按钮的选中就改变页面的显示情况
 */
function isUseTypeCheck() {
	 //console登录（是/否）
    $("#useType").on('change', function () {
    	if(this.checked) {
    		$("#isPayings").val("0");
		}else {
    		$("#isPayings").val("1");
		}
    });
}

/**
 * 提交编辑院系AWS账户池信息数据
 */
function editIamPoolData() {

	$("#editIamPoolData").click(function() {
		var url=contextPath +'/awaiamPool/editAwsIamPool';
		submitEditData(url);
	});
}

/**
 * 提交添加院系AWS账户池信息数据
 */
function submitIamPoolData() {
	$("#submitIamPoolData").click(function (){
		var url=contextPath +'/awaiamPool/addAwsIamPool';
		submitEditData(url);
	});	
}


/**
 * 提交和编辑数据
 * @param url
 */
function submitEditData(url) {
	var bv = $('#awsIAMPoolForm').data('bootstrapValidator');
	bv.validate();
	if (!bv.isValid()) {
		return;
	}
	var formData = $('#awsIAMPoolForm').serializeJSON();
	console.log(formData);
	$.ajax({
		url : url,
		type : 'POST',
		data :JSON.stringify(formData),
		contentType : "application/json;charset=utf-8",
		dataType: "json",
		success : function(data) {
			if(data.resultFlag){
				$("#awsIAMPoolModel").modal("hide");
				awsIAMPoolTb.ajax.reload();
				hiddenType=0;
			}else{
				layer.alert(data.message, {icon: 5});
			}			
		}
	});
}


/**
 * awsIamPool列表初始化
 */
function initAwsIamPoolTB(){
	awsIAMPoolTb = $('#awsIAMPoolTb').DataTable({
		ajax: {
			url: contextPath + '/awaiamPool/selectAwsIamPoolData',// 数据请求地址
			type: "POST",
			data: function (params) {
				//此处为定义查询条件 传给控制器的参数
				params.inputUnitName = $('#inputUnitName').val();
				params.inputState = $('#inputState').val();
				params.accountIdCheck = accountIdCheck;
				return params;
			}
		},
		stateSave: true,
		lengthChange: true,
		searching: false,
		ordering: false,
		autoWidth: false,
		serverSide: true,
		pagingType: "full_numbers",
		iDisplayLength: 10,
		lengthMenu: [10,20,30,50,100],
		columns: [{
				"sWidth":"10%",
				"data": "useType",
				"title": "使用类型",
				"render": function (data, type, row, meta) {
					switch (data) {
						case 0:
							return "登录";
							break;
						case 1:
							return "不登录";
							break;
					}
				}
			},{
				"sWidth":"13%",
				"data": "accountID",
				"title": "Account ID",
				"defaultContent":''
			},
			{
				"sWidth":"12%",
				"data": "payAccountID",
				"title": "关联付款账号",
				"defaultContent":''
			},{
				"sWidth":"12%",
				"data": "payAccountName",
				"title": "付款账户名称",
				"defaultContent":''
			},
			{
				"sWidth":"15%",
				"data": "createTime",
				"title": "加入时间",
				"defaultContent":''
			},
			{
				"sWidth": "10%",
				"data": "isUsed",
				"title": "试验中",
				"render": function (data, type, row, meta) {
					switch (data) {
						case 0:
							return "否";
							break;
						case 1:
							return "是";
							break;
					}
				}
			},
			{
				"sWidth": "10%",
				"data": "currentUserName",
				"title": "当前使用者",
				"defaultContent":''
			},
			{
				"sWidth":"18%",
				"data": "opt",
				"title": "操作",
				"render": function (data, type, row, meta) {
					var returnData="<div id="+ row.id +" class='hidden-sm hidden-xs btn-group'>" +
									"<button class='btn btn-xs btn-info'  onclick='getAwsIamPoolData(" + '"' + row.id + '"' +")'>" +
									"<i class='ace-icon fa fa-pencil bigger-120'>修改</i>" +
									"</button>" +
									"<button class='btn btn-xs btn-danger' onclick='deleteAwsIamPoolData(" + '"' + row.id + '"' +")'>" +
									"<i class='ace-icon fa fa-trash-o bigger-120'>删除</i>" +
									"</button>";
					if(row.isActive==0){
						return returnData +	"<button class='btn btn-xs btn-primary' onclick='activateOrFailure(\""+ row.id + "\",\"" + row.isActive +"\")' >" +
						"<i class='ace-icon fa fa-lock bigger-120'>失效</i>" +
						"</button>" +
						"</div>";				
					}else{
						return returnData +	"<button class='btn btn-xs btn-success' onclick='activateOrFailure(\""+ row.id + "\",\"" + row.isActive +"\")' >" +
						"<i class='ace-icon fa fa-unlock bigger-120'>激活</i>" +
						"</button>" +
						"</div>";
					}
				}
			}
		],
		language: {
			url: contextPath + "/resource/json/language-zh.json"
		},drawCallback:function(s){//回调函数
	        tableCheckColor();//点击awsIamPool列表改变背景颜色
        }
	});
	
}

/**
 * 点击awsIamPool列表改变背景颜色
 */
function tableCheckColor() {
 $("#awsIAMDiv").hide();
 $("#awsIAMPoolTb tr").click(function(){
	 
 	$("#awsIAMPoolTb tr").css({
 		"background-color":"",
 	})
 	$(this).css({
 		"background-color":"rgba(111, 179, 224, 0.47)",
 	})
 	
 	//根据表格选中的行获取的ID，来获取IAM User账户数据信息
 	getAwsIamData($(this).children().eq(7).children('div').attr('id'));
 })
};

/**
 * 根据表格选中的行获取的ID，来获取IAM User账户数据信息
 */
function getAwsIamData(id){
	$("#awsIAMTb").empty();
	$.ajax({
		url : contextPath +'/awaiamPool/getAwsIamData',
		async : false,
		type : 'post',
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var insertText="";
			var firstText="";
			if(data.length>0){
				$("#awsIAMDiv").show();
				for(var i = 0;i<data.length;i++){
					if(data[i].iAMKind==0){
						firstText="<tr><td>IAM-管理</td>"
					}else{
						firstText="<tr><td>IAM-实验</td>"
					}
					insertText += firstText+"<td>"+data[i].iAMName+"</td><td>"+data[i].password+"</td><td>"+data[i].accessKeyID+"</td><td>"+data[i].accessKey+"</td></tr>";
				}
				$("#awsIAMTb").append(insertText);
				
			}else{
				
				awsIAMPoolTb.ajax.reload();
			}
		},
		error : function() {

		}
	})
	
}

/**
 * 获取选中的行数据，根据Id获取要编辑的数据，显示在页面上
 * @param id
 */
function getAwsIamPoolData(id) {
	$("input[type='text']").val("");
	hiddenType=1
	$("#addh4").hide();
	$("#edith4").show();
	$("#submitIamPoolData").hide();
	$("#editIamPoolData").show();
	$("#payAccountDiv").show();
	$("#payAccountName option:not(:first)").remove();
	$("#payAccountName").val(-1);
	$.ajax({
		url : contextPath+'/awaiamPool/getAwsIamPoolData',
		async : false,
		type : 'post',
		dataType : "json",
		data : {"id":id},
		success : function(data) {			
			$("#id").val(data.id);
			$("#accountID").val(data.accountID);
			$("#copyAccountID").val(data.accountID);
			$("#payAccountID").text(data.payAccountID);
			$("#payAccountIdHid").val(data.payAccountID);
			$("#payAccountNameHid").val(data.payAccountName);
			selectPayAccount();//显示关联付款账户下拉列表
			$("#payAccountName").val(data.payAccountID);
			if(data.useType==0){
				$("#useType").prop("checked",true);
				$("#isPayings").val("0");
			}else{
				$("#useType").prop("checked",false);
				$("#isPayings").val("1");
			}
			$("#IAMId").val(data.iAMId);
			$("#IAMName").val(data.iAMName);
			$("#copyIamName").val(data.iAMName);
			$("#password").val(data.password);	
			$("#accessKeyID").val(data.accessKeyID);
			$("#accessKey").val(data.accessKey);
			$("#awsIAMPoolModel").modal('show');
		}
	});
}


/**
 * 假删除院系AWS账户池列表上的数据
 */
function deleteAwsIamPoolData(id){
  layer.confirm('您确定要删除该数据信息吗?', {icon: 3, title:'提示'}, function(index){
	$.ajax({
		url : contextPath+'/awaiamPool/deleteAwsIamPoolData',
		async : false,
		type : 'post',
		dataType : "json",
		data : {"id":id},
		success : function(data) {
			if(data.success){
				awsIAMPoolTb.ajax.reload();
			}else{
				layer.alert("删除数据信息失败!", {icon: 5});
				}
			}	
		});	
		layer.close(index);
	});
}

/**
 * 激活与失效功能
 */
function activateOrFailure(id ,isActive){
	var dataTitle='',iconType='';
	if(isActive==0){
		dataTitle='您确定要失效该数据信息吗?';
		iconType=2;
	}else{
		dataTitle='您确定要激活该数据信息吗?';
		iconType=1;
	}
	layer.confirm(dataTitle, {icon: iconType, title:'提示'}, function(index){
	$.ajax({
		url : contextPath+'/awaiamPool/activateOrFailure',
		async : false,
		type : 'post',
		dataType : "json",
		data : {"id":id,"isActive":isActive},
		success : function(data) {
			if(data.success){
				awsIAMPoolTb.ajax.reload();
			}else{
				layer.alert("操作处理失败!", {icon: 5});
				}
			}	
		});	
		layer.close(index);
	});
}


/****************************************
 * 导入Excel表格数据
 *@param
 *@return  
 ****************************************/
function uploadExcelData(){	
	$("#uploadExcel").click(function() {
	$("#errorMessage").text("");
	layer.open({
	      type: 1,
	      title :'批量导入数据',
	      area: ['420px', '270px'],
	      shadeClose: false, //点击遮罩关闭
	      content: "<div id='tb' style='padding:10px 0px 10px 40px;' >" +
	      		"<div style='font-size: 15px;color: red;font-family: serif;'>请选择一个后缀为.xls的Excel文件上传</div><br>" +
	      		"<div class='layui-upload'>" +
	      		"<button type='button' class='layui-btn layui-btn-normal' id='test8'>选择文件</button>" +
	      		"</div><button class='layui-btn layui-btn-radius' id='test9' style='margin-top: 50px'>导入数据</button>" +
	      		"</div>",
	      success: function(layero, index){
	    	  uploadFile();
    	  },
	      cancel: function(index, layero){ }    
	    });	
	});
}
/**
 * 上传EXCEL文件
 */
function uploadFile() {
	var table = layui.table;
	var $ = layui.jquery;
	var upload = layui.upload;
	 //选完文件后不自动上传
	  upload.render({
	    elem: '#test8'
	    ,url: contextPath + '/awaiamPool/uploadExcel'
	    ,accept: 'file' //普通文件
	    ,exts: 'xls' //只允许上传后缀为.xls文件上传
	    ,auto: false
	    ,bindAction: '#test9'
		,before: function(obj){
			var index = layer.load(0); //添加laoding,0-2两种方式
		}   
	    ,done: function(res){
	    	if(res.errorMessage!=undefined){
	    		awsIAMPoolTb.ajax.reload();
	    		errorOpenDialog();//下载导入失败的数据
			}else{
				layer.alert(res.resultMessage, {icon: 6},function(){
					awsIAMPoolTb.ajax.reload();
					layer.closeAll();
				});
			}
	    }
	  });
}
/**
 * 下载导入失败的数据
 */
function errorOpenDialog() {
	layer.open({type: 1,
		title :'批量导入结果详情',
		area: ['380px', '250px'],
		shadeClose: false, //点击遮罩关闭
		content: "<div id='error' style='padding:10px 30px 10px 30px;' >" +
				"<div id='errorMessage' style='margin-top: 10px;font-size: 14px'>" +
				"</div><button class='layui-btn layui-btn-radius' style='margin-top: 55px' onclick='downloadErrorExcelData();'>异常数据下载</button></div>",
		success: function(layero, index){
		},
		cancel: function(index, layero){ 
			awsIAMPoolTb.ajax.reload();
			layer.closeAll();
		}    
	});	
	$("#errorMessage").append("您批量导入的数据有部分数据导入异常【必填字段没有填写或AccountID、IAM User已存在或付款账号、付款账号名称不存在】，请点击“异常数据下载”，改正后再重新导入数据!");
}

/**
 * 下载批量导入Excel的异常数据
 */
function downloadErrorExcelData() {
	location.href =contextPath + '/awaiamPool/errorExportExcelData';
	layer.closeAll();
}



/**
 * 验证添加院系AWS账号池表单
 */
function iamPoolValidatorForm() {
	$('#awsIAMPoolForm').bootstrapValidator({
		excluded:[":hidden"] ,//bootstrapValidator的默认配置
		message: '该字段无效！',
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			accountID: {
				validators: {
					notEmpty: {
						message: 'AccountID不能为空！'
					},stringLength: {
                        max: 30,
                        message: 'AccountID不超过30字符!'
                    }
				}
			},payAccountNames: {
				validators: {
	                regexp: {
	                    regexp: /^(?!-1)/,
	                    message: '请选择关联付款账户名!'
	                }
	            }	
			},IAMName: {
				validators: {
					notEmpty: {
						message: 'IAM Uaer不能为空！'
					},stringLength: {
                        max: 50,
                        message: 'IAM Uaer不超过50字符！'
                    }
				}
			},password: {
				validators: {
					notEmpty: {
						message: 'PassWord 不能为空！'
					},stringLength: {
		                max: 20,
		                message: 'PassWord不超过20字符!'
		            }
				}
			},accessKeyID: {
				validators: {
					notEmpty: {
						message: 'PK不能为空！'
					},stringLength: {
                        max: 50,
                        message: 'PK不超过50字符！'
                    }
				}
			},accessKey: {
				validators: {
					notEmpty: {
						message: 'SK不能为空！'
					},stringLength: {
                        max: 50,
                        message: 'SK不超过50字符！'
                    }
				}
			}
		}
	});
}