/**********AWS account 管理*************/

var awsAccountTb = "";
var zeroData=0;
var selectData=null;
/**
 * 页面初始化
 */
$(function () {
	
	isUPTCheck();//获取是否为UPT所有，如果改变按钮的选中就改变页面的显示情况
	
	accountValidatorForm();//验证添加AWS Account账号表单
	
	addModalShow(); //点击添加按钮，跳出添加页面的弹出框
	
	submitAccountData();//提交添加AWS Account账户信息数据
	
	selectOrg();//显示机构的下拉列表，将下拉列表显示在添加数据的页面上
	
	getMechanismPid();//绑定学校名称（机构名称）下拉列表change事件，获取当前所属院系名称的下来列表
	
	editAccountData();//编辑数据的提交
	
	/*aws account列表初始化*/
	
	initAwsAccountTB();
	
	uploadExcelData();//导入Excel表格数据
	
});

/**
 * 校验重置重点在这里 当modal隐藏时销毁验证再重新加载验证
 */
$('#awsAccountModel').on('hidden.bs.modal', function() {
	pageValidator();//当页面的校验发生改变的时候，重新的获取页面的校验
});

/**
 * 当页面的校验发生改变的时候，重新的获取页面的校验
 */
function pageValidator() {
   $('#awsAccountForm').data('bootstrapValidator').destroy();
   $('#awsAccountForm').data('bootstrapValidator', null);
   accountValidatorForm();//验证添加AWS Account账号表单
}


/****************************************
 * 查询
 ****************************************/
function doSearch(){

	awsAccountTb.ajax.reload();
}

/**
 * 点击添加按钮，跳出添加页面的弹出框
 */
function addModalShow() {
	$("#addModalShow").click(function(){
		$("input[type='text']").val("");
		$("input[type='date']").val("");
		$("#isUPT").prop("checked",true);
		$("#isPayings").val("1");
		$("#payingAccountIdDiv").hide();
		$("#payingAccountNameDiv").hide();
		$("#addh4").show();
		$("#edith4").hide();
		$("#org").val(-1);
		$("#departmentId").val(" ");
		$("#submitAccountData").show();
		$("#editAccountData").hide();
		$("#awsAccountModel").modal('show');
	});
}

/**
 * 显示机构的下拉列表，将下拉列表显示在添加数据的页面上
 */
function selectOrg(){
	selectData=new Array();
	$.ajax({
		url : contextPath +'/awsaccount/findMechanismName',
		async : false,
		type : 'post',
		dataType : "json",
		data:{"mechanismPid":zeroData},
		success : function(data) {
			var insertText =  ""; 			
			for(var i = 0;i<data.length;i++){
				selectData.push(data[i].mechanismId);
				insertText += "<option value="+data[i].mechanismId+">"+data[i].mechanismName+"</option>";
				}
			$("#org").append(insertText);
			$("#inputState").append(insertText);
		},
		error : function() {

		}
	})
}

/**
 * 绑定学校名称（机构名称）下拉列表change事件，获取当前所属院系名称的下来列表
 */
function getMechanismPid(){
	$("#org").change(function() {
		var org=$(this).children('option:selected').val();
		pageValidator();//当页面的校验发生改变的时候，重新的获取页面的校验
		$("#departmentId option:not(:first)").remove();
		$("#payingAccountId option:not(:first)").remove();
		$("#payingAccountName").val("");
		findByMechanismData(org);//获取学院列表数据
		//getByHostAwsAccountData(org);//获取主账号的下拉列表数据
	});
}

/**
 * 获取院系列表数据
 * @param org
 */
function findByMechanismData(org){
	$.ajax({
		url : contextPath +'/awsaccount/findMechanismName',
		async : false,
		type : 'post',
		dataType : "json",
		data:{"mechanismPid":org},
		success : function(data) {
			var insertText =  ""; 			
			for(var i = 0;i<data.length;i++){
				insertText += "<option value="+data[i].mechanismId+">"+data[i].mechanismName+"</option>";
				}
			$("#departmentId").append(insertText);
		}
	});
}


/**
 * 获取是否为主账号，如果改变按钮的选中就改变页面的显示情况
 */
function isUPTCheck() {
	 //UPT所有（是/否）
    $("#isUPT").on('change', function () {
    	if(this.checked) {
    		$("#isPayings").val("1");
    		$("#payingAccountId").val("");
    		$("#payingAccountName").val("");
		}else {
    		$("#isPayings").val("0");
		}
    });
}


/**
 * aws account 列表初始化
 */
function initAwsAccountTB(){
	awsAccountTb = $('#awsAccountTb').DataTable( {
		ajax: {
            url: contextPath +'/awsaccount/selectAwsAccountData',// 数据请求地址
            type: "POST",
            data: function (params) {
				//此处为定义查询条件 传给控制器的参数
				params.inputUnitName = $('#inputUnitName').val();
				params.inputUserRealName = $('#inputUserRealName').val();
				params.inputState = $('#inputState').val();
				return params;
			}
        },
        stateSave:true,
	    lengthChange: true,
	    searching: false,
	    ordering: false,
	    info: true,
	    autoWidth: false,
	    serverSide:true,
	    pagingType: "full_numbers",
	    iDisplayLength: 10,
        lengthMenu: [10,20,30,50,100],
        columns: [{
				"data": "accountName",
				"title": "付款账号名称",
				"defaultContent":''
			},{
				"data": "accountId",
				"title": "付款账号",
				"defaultContent":''
			},{
				"data": "orgName",
				"title": "所属机构",
				"defaultContent":''
			},{
				"data": "departmentName",
				"title": "院系名称",
				"defaultContent":''
			},{
				"data": "isUPT",
				"title": "是否UPT所有",
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
			},{
				"data": "iAM",
				"title": "IAM User",
				"defaultContent":''
			}/*,{
				"data": "aK",
				"title": "AK",
				"defaultContent":''
			},{
				"data": "sK",
				"title": "SK",
				"defaultContent":''
			}*/,{
				"data": "createTime",
				"title": "加入时间",
				"defaultContent":''
			},{
				"data": "opt",
				"title": "操作",
				"render": function (data, type, row, meta) {
					var returnData="<div class='hidden-sm hidden-xs btn-group'>" +
									"<button class='btn btn-xs btn-info' onclick='getAwsAccountData(" + '"' + row.id + '"' +")'>" +
									"<i class='ace-icon fa fa-pencil bigger-120'>修改</i>" +
									"</button>" +
									"<button class='btn btn-xs btn-danger' onclick='deleteAwsAccountData(\""+ row.id + "\",\"" + row.accountId +"\",\"" + row.accountName +"\")'>" +
									"<i class='ace-icon fa fa-trash-o bigger-120'>删除</i>" +
									"</button>";
					if(row.isActive==0){
						return returnData +	"<button class='btn btn-xs btn-primary' onclick='activateOrFailure(\""+ row.id + "\",\"" + row.isActive +"\",\"" + row.accountId +"\",\"" + row.accountName +"\")' >" +
						"<i class='ace-icon fa fa-lock bigger-120'>失效</i>" +
						"</button>" +
						"</div>";				
					}else{
						return returnData +	"<button class='btn btn-xs btn-success' onclick='activateOrFailure(\""+ row.id + "\",\"" + row.isActive +"\",\"" + row.accountId +"\",\"" + row.accountName +"\")' >" +
						"<i class='ace-icon fa fa-unlock bigger-120'>激活</i>" +
						"</button>" +
						"</div>";
					}
				}
			}
		],
		language: {
			url: contextPath + "/resource/json/language-zh.json"
		}
     });
}

/**
 * 获取选中的行数据，根据Id获取要编辑的数据，显示在页面上
 * @param id
 */
function getAwsAccountData(id) {
	$("#addh4").hide();
	$("#edith4").show();
	$("#submitAccountData").hide();
	$("#editAccountData").show();
	$("#departmentId option:not(:first)").remove();
	$("#payingAccountId option:not(:first)").remove();
	$.ajax({
		url : contextPath+'/awsaccount/getAwsAccountData',
		async : false,
		type : 'post',
		dataType : "json",
		data : {"id":id},
		success : function(data) {
			$("#id").val(data.id);
			$("#accountId").val(data.accountId);
			$("#accountName").val(data.accountName);
			$("#copyAccountId").val(data.accountId);
			$("#copyAccountName").val(data.accountName);
			$("#email").val(data.email);
			if(data.isUPT==0){
				$("#isUPT").prop("checked",false);
				$("#isPayings").val("0");
			}else{
				$("#isUPT").prop("checked",true);
				$("#isPayings").val("1");
			}
			$("#org").val(data.org);
			findByMechanismData(data.org);
			//getByHostAwsAccountData(data.org);
			$("#departmentId").val(data.departmentId);
			$("#IAM").val(data.iAM);
			$("#AK").val(data.aK);
			$("#SK").val(data.sK);
			$("#awsAccountModel").modal('show');
		}
	});
}

/**
 * 提交添加AWS Account账户信息数据
 */
function submitAccountData() {
	$("#submitAccountData").click(function (){
		var url=contextPath +'/awsaccount/addAwsAccount';
		submitEditData(url);
	});	
}

/**
 * 编辑数据的提交
 */
function editAccountData() {
	$("#editAccountData").click(function() {
		var url=contextPath +'/awsaccount/editAwsAccount';
		
		$.ajax({
			url : contextPath+'/awaiamPool/getByAwsIamPoolList',
			async : false,
			type : 'post',
			dataType : "json",
			data : {"copyAccountId":$("#copyAccountId").val(),"copyAccountName":$("#copyAccountName").val()},
			success : function(data) {
				if(data.length>0){
					if($("#copyAccountId").val()==$("#accountId").val() && $("#copyAccountName").val()!=$("#accountName").val()){
						layer.confirm('修改付款账号名称会影响院系AWS账号池，您确定要修改吗?', {icon: 3, title:'提示'}, function(index){		
							submitEditData(url);
							layer.close(index);
						});
					}else if($("#copyAccountId").val()!=$("#accountId").val() && $("#copyAccountName").val()==$("#accountName").val()){
						layer.confirm('修改付款账号会影响院系AWS账号池，您确定要修改吗?', {icon: 3, title:'提示'}, function(index){		
							submitEditData(url);
							layer.close(index);
						});
					}else if($("#copyAccountId").val()!=$("#accountId").val() && $("#copyAccountName").val()!=$("#accountName").val()){
						layer.confirm('修改付款账号与付款账号名称会影响院系AWS账号池，您确定要修改吗?', {icon: 3, title:'提示'}, function(index){		
							submitEditData(url);
							layer.close(index);
						});
					}else{
						submitEditData(url);
					}
				}else{
					submitEditData(url);
				}
			}
		});
	});
}

/**
 * 提交和编辑数据
 * @param url
 */
function submitEditData(url) {
	var bv = $('#awsAccountForm').data('bootstrapValidator');
	bv.validate();
	if (!bv.isValid()) {
		return;
	}
	var formData = $('#awsAccountForm').serializeJSON();
	console.log(formData);
	$.ajax({
		url : url,
		type : 'POST',
		data :JSON.stringify(formData),
		contentType : "application/json;charset=utf-8",
		dataType: "json",
		success : function(data) {
			if(data.resultFlag){
				$("#awsAccountModel").modal("hide");
				awsAccountTb.ajax.reload();
			}else{
				layer.alert(data.message, {icon: 5});	
			}			
		}
	});
}

/**
 * 删除AWS Account列表上的数据
 */
function deleteAwsAccountData(id,accountId,accountName){
	$.ajax({
		url : contextPath+'/awaiamPool/getByAwsIamPoolList',
		async : false,
		type : 'post',
		dataType : "json",
		data : {"copyAccountId":accountId,"copyAccountName":accountName},
		success : function(data) {
			if(data.length>0){
				layer.confirm('删除该数据信息会影响院系AWS账号池，您确定要删除该数据信息吗?', {icon: 3, title:'提示'}, function(index){
					deleteAwsAccount(id);
					layer.close(index);
				});
			}else{
				layer.confirm('您确定要删除该数据信息吗?', {icon: 3, title:'提示'}, function(index){
					deleteAwsAccount(id);
					layer.close(index);
				});
			}
		}
	});
}

/**
 * 删除对应的AwsAccount数据
 * @param id
 */
function deleteAwsAccount(id){
	$.ajax({
		url : contextPath+'/awsaccount/deleteAwsAccountData',
		async : false,
		type : 'post',
		dataType : "json",
		data : {"id":id},
		success : function(data) {
			if(data.success){
				awsAccountTb.ajax.reload();
			}else{
				layer.alert("删除数据信息失败!", {icon: 5});
			}
		}	
	});	
}

/**
 * 激活与失效功能
 */
function activateOrFailure(id ,isActive,accountId,accountName){
	var dataTitle='',iconType='';	
	if(isActive==0){
		dataTitle='您确定要失效该数据信息吗?';
		iconType=2;
		$.ajax({
			url : contextPath+'/awaiamPool/getByAwsIamPoolList',
			async : false,
			type : 'post',
			dataType : "json",
			data : {"copyAccountId":accountId,"copyAccountName":accountName},
			success : function(data) {
				if(data.length>0){
					layer.confirm("失效该数据信息会影响院系AWS账号池，"+dataTitle, {icon: 3, title:'提示'}, function(index){
						awsActivateOrFailure(id,isActive);
						layer.close(index);
					});
				}else{
					layer.confirm(dataTitle, {icon: iconType, title:'提示'}, function(index){
						awsActivateOrFailure(id,isActive);
						layer.close(index);
					});
				}
			}
		});
	}else{
		dataTitle='您确定要激活该数据信息吗?';
		iconType=1;
		layer.confirm(dataTitle, {icon: iconType, title:'提示'}, function(index){
			awsActivateOrFailure(id,isActive);
			layer.close(index);
		});
	}
}

/**
 * 激活或失效AwsAccount数据
 * @param id isActive
 */
function awsActivateOrFailure(id,isActive){
	$.ajax({
		url : contextPath+'/awsaccount/activateOrFailure',
		async : false,
		type : 'post',
		dataType : "json",
		data : {"id":id,"isActive":isActive},
		success : function(data) {
			if(data.success){
				awsAccountTb.ajax.reload();
			}else{
				layer.alert("操作处理失败!", {icon: 5});
			}
		}	
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
	    ,url: contextPath + '/awsaccount/uploadExcel'
	    ,accept: 'file' //普通文件
	    ,exts: 'xls' //只允许上传后缀为.xls文件上传
	    ,auto: false
	    ,bindAction: '#test9'
    	,before: function(obj){
			var index = layer.load(0); //添加laoding,0-2两种方式
		} 
	    ,done: function(res){
	    	if(res.errorMessage!=undefined){
	    		awsAccountTb.ajax.reload();
	    		errorOpenDialog();//下载导入失败的数据
			}else{
				layer.alert(res.resultMessage, {icon: 6},function(){
					awsAccountTb.ajax.reload();
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
			awsAccountTb.ajax.reload();
			layer.closeAll();
		}    
	});	
	$("#errorMessage").append("您批量导入的数据有部分数据导入异常【付款账号、付款账号名称已经存在或所属学校(机构)、学院(系别)在机构管理中不存在】，请点击“异常数据下载”，改正后再重新导入数据!");
}

/**
 * 下载批量导入Excel的异常数据
 */
function downloadErrorExcelData() {
	location.href =contextPath + '/awsaccount/errorExportExcelData';
	layer.closeAll();
}

/**
 * 获取主账号的下拉列表数据
 * @param org
 */
/*function getByHostAwsAccountData(org){
	$.ajax({
		url : contextPath +'/awsaccount/getByHostAwsAccount',
		async : false,
		type : 'post',
		dataType : "json",
		data:{"school":org},
		success : function(data) {
			var insertText =  ""; 			
			for(var i = 0;i<data.length;i++){
				insertText += "<option value="+data[i].accountId+">"+data[i].accountId+"</option>";
			}
			$("#payingAccountId").append(insertText);
		}
	});
}*/

/**
 * 验证添加AWS Account账号表单
 */
function accountValidatorForm() {
	$('#awsAccountForm').bootstrapValidator({
		excluded:[":hidden"] ,//bootstrapValidator的默认配置
		message: '该字段无效！',
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			accountId: {
				validators: {
					notEmpty: {
						message: '付款账号不能为空！'
					},stringLength: {
                        max: 30,
                        message: '付款账号不超过30字符'
                    }
				}
			},
			accountName: {
				validators: {
					notEmpty: {
						message: '付款账号名称不能为空！'
					},stringLength: {
                        max: 20,
                        message: '付款账号名称不超过20字符！'
                    }
				}
			},
			/*email: {
				validators: {
					notEmpty: {
						message: '电子邮箱不能为空！'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/,
						message: '电子邮箱格式不正确！'
					}
				}
			},*/
			org: {
				validators: {
	                regexp: {
	                    regexp: /^(?!-1)/,
	                    message: '请选择所属学校(机构)!'
	                }
                }	
			},departmentId: {
				validators: {
	                regexp: {
	                    regexp: /^(?! )/,
	                    message: '请选择所属院系!'
	                }
                }	
			},
			IAM: {
				validators: {
					notEmpty: {
						message: 'IAM Uaer不能为空！'
					},stringLength: {
                        max: 50,
                        message: 'IAM Uaer不超过50字符！'
                    }
				}
			},AK: {
				validators: {
					notEmpty: {
						message: 'AK不能为空！'
					},stringLength: {
                        max: 50,
                        message: 'AK不超过50字符！'
                    }
				}
			},SK: {
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