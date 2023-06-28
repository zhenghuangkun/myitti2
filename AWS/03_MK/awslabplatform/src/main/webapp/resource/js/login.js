/**************************************
 * login js
 *************************************/


$(function(){

	 //如果cookie 中记住我是打勾状态，将cookie 中的用户名密码设置到页面上
	 if ($.cookie("rememberMe") == "true") {
		 $("#rememberMe").attr("checked", "checked");
		 $('input[name="loginInfo"]').val($.cookie("loginInfo"));
		 $('input[name="userPwd"]').val($.cookie("userPwd"));
	 }
});

/**
 * 使用ajaxSubmit 将数据提交到登录controller 处理
 */
$('#loginForm').on('submit', function() {
	
    $(this).ajaxSubmit({
        type: 'post',
        url: contentPath+'/login/userLogin',
        success: function(data) {
        	if(data.resultFlag == false){
        		/**
        		 * 登录失败
        		 */
        		layer.alert(data.message, {
					icon: 5,
					title: "提示"
				});
        		return;
			}else{
				/**
				 * 登录成功
				 * 保存用户信息到cookie
				 * 页面跳转到
				 */
				saveUserInfo();
				if(data.data!=null&&data.data!=""){
					window.location.href = data.data;
				}else{
					window.location.href = contentPath+'/home';
				}
			}
            $(this).resetForm();
        }
    });
    return false; // 阻止表单自动提交事件
});

/**
 * 保存用户名、密码信息到cookie 中
 * 功能内容描述：
 * 1.判断记住我是否勾选
 * 2.将用户名、密码存入cookie
 */
function saveUserInfo(){

	if($("#rememberMe").is(':checked')){

		var loginInfo = $('input[name="loginInfo"]').val();
		var userPwd = $('input[name="userPwd"]').val();

		//rmbUser
		$.cookie("rememberMe", "true", {  expires : 7 });
		//userName
		$.cookie("loginInfo", loginInfo, {  expires : 7 });
		//userPwd
		$.cookie("userPwd", userPwd, {  expires : 7 });
	}else{
		//delete rmbUser
		$.cookie("rememberMe", "false", {  expires : -1 });
		//delete userName
		$.cookie("loginInfo", "", {  expires : -1 });
		//delete userPwd
		$.cookie("userPwd", "", {  expires : -1 });
	}
}
