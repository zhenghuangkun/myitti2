/**
 * 页面初始化
 */
$(function () {

	addModalShow();//点击添加按钮，跳出添加页面的弹出框
	
	tacticsValidatorForm();//添加策略表单验证
	
});

/**
 * 校验重置重点在这里 当modal隐藏时销毁验证再重新加载验证
 */
$('#addTacticsModel').on('hidden.bs.modal', function() {
	pageValidator();//当页面的校验发生改变的时候，重新的获取页面的校验
});

/**
 * 当页面的校验发生改变的时候，重新的获取页面的校验
 */
function pageValidator() {
   $('#tacticsForm').data('bootstrapValidator').destroy();
   $('#tacticsForm').data('bootstrapValidator', null);
   tacticsValidatorForm();//添加策略表单验证
}

/**
 * 点击添加按钮，跳出添加页面的弹出框
 */
function addModalShow() {
	$("#addModalShow").click(function(){
		$("input[type='text']").val("");
		$("input[type='file']").val("");
		$("input[type='hidden']").val("");
		$("#description").val(''); 
		$("#addh4").show();
		$("#edith4").hide();
		$("#fileUrlDiv").hide();
		$("#tacticsForm").attr("action", contextPath+"/addPolicy");
		$("#addTacticsModel").modal('show');
	});
}

/**
 * 删除策略
 * 
 */
function deletePolicy(id){
	//layer.confirm('您确定要删除该策略信息?',{icon: 3, title:'提示'})
	layer.confirm('您确定要删除该策略？', {
		icon: 3,
		btn: ['确定','取消'] //按钮
		}, function(){
		  window.location.href=contextPath+"/deletePolicy?id="+id;
		}, function(){
		});
}
/**
 * 编辑策略
 * 
 */
function updatePolicy(id,name,description,fileUrl){
	
	$("#addh4").hide();
	$("#edith4").show();
	$("#fileUrlDiv").show();
	$("#tacticsForm").attr("action", contextPath+"/updatePolicy");//添加要操作到的路径
	$("#tacticsForm").bootstrapValidator('removeField','myfile');//移除上传文件的校验
	$("#addTacticsModel").modal('show');
	
	$("#id").val(id);
	$("#name").val(name);
	$("#description").val(description);
	document.getElementById("fileUrl").href= fileUrl;
	
}


/**
 * 添加策略表单验证
 */
function tacticsValidatorForm() {
	$('#tacticsForm').bootstrapValidator({
		excluded:[":hidden"] ,//bootstrapValidator的默认配置
		message: '该字段无效！',
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			name: {
				validators: {
					notEmpty: {
						message: '策略名称不能为空！'
					},stringLength: {
                        max: 50,
                        message: '策略名称不超过50字符'
                    },regexp: {
						regexp: /^[a-zA-Z\d\+=,.@-_]{1,50}$/,
						message: '请输入由字母、数字或   +=,.@-_ 组成字符！'
					}
				}
			},
			description: {
				validators: {
					notEmpty: {
						message: '策略描述不能为空！'
					},stringLength: {
                        max: 500,
                        message: '策略描述不超过500字符！'
                    }
				}
			},
			myfile: {
				validators: {
					notEmpty: {
						message: '请上传策略文件！'
					}
				}
			}
		}
	});
}

