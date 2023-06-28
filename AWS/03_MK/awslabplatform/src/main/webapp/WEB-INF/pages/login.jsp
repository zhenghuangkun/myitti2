<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/font-awesome.min.css" />
<title>登陆</title>
<style>
.login-form {
	width: 50vw;
    max-width: 15em;
    width: 100%;
    padding: 40px 2em 0;
    position: relative;
    background: rgba(0, 0, 0, 0.6);
}

.lf--submit {
  display: block;
  width: 100%;
  height:50px;
  background: linear-gradient(to right, #35c3c1, #00d6b7);
  border: 0;
  color: #fff;
  font-size: .75em;
  font-weight: 200;
}
.login-form:before{
	 background: rgba(0, 0, 0, 0);
}
.thebody{
	background:url(${pageContext.request.contextPath}/resource/images/bg.png) top left;
	background-size:100% 100%;
	padding: 0;margin: 0;
}

</style>
</head>
<body class="login-layout thebody" >
		<span style="color:white;">云普乐斯教学实验云平台</span>
		<div class="main-container" style="margin-top:30px;">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-6" >
					<div class="login-container" >
						<form class='login-form' id ="loginForm">
	
		  					<div class="flex-row">
		  						<input id="referrer" type="text" name="referrer" class="form-control" style="display:none;">
		   						<span class="lf--label"><i class="fa fa-user fa-fw" style="color:#afb0b2"></i></span>
		    					<input name="loginInfo" id="loginInfo" class='lf--input' placeholder='电话号码或邮箱' 
		    							type='text' required maxlength="30">
		  					</div>
		
		  					<div class="flex-row">
		    					<span class="lf--label"><i class="fa fa-unlock-alt fa-fw" style="color:#afb0b2"></i></span>
		    					<input name="userPwd" id="userPwd" class='lf--input' placeholder='密码' type='password'
		    						 required maxlength="30">
		  					</div>
		
		  					<div class="flex-row" style="margin-bottom:15px;">
		  						<input id="word" name="imgVerCode" id="imgVerCode" class='lf--input' type='text' value="" placeholder='验证码' required maxlength="6">
								<img style="cursor: pointer;" id="sendVerCode" onclick="changeImgVerCode()" src="${pageContext.request.contextPath}/createLoginCode"/>
		  					</div>
							<div class="flex-row" style="font-size:14px;font-wight:200;color:white">
								<label>
									<input type="checkbox"  name = "rememberMe" id="rememberMe"/>
									<span>记住密码</span>
								</label>
								<label style="display:block; width:280px; text-align:right;">
									<a href="${pageContext.request.contextPath}/forgetPwd" style="text-decoration:none;color:#04c4ab">忘记密码？</a>
								</label>
							</div>
							
							<div style="height:75px">
								<input style="cursor: pointer;" class='lf--submit' type='submit' value='登&nbsp;&nbsp;&nbsp;录'>
							</div>
		  							
	  					</form>
  					</div>
  				</div>
  			</div>
		</div>
		</div>
		
		<!-- jQuery 需要最先引入 -->
		<script src="${pageContext.request.contextPath}/resource/plugins/jquery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/plugins/jquery/jquery.form.js"></script>
		<script src="${pageContext.request.contextPath}/resource/plugins/jquery/jquery.cookie.js"></script>
		<script src="${pageContext.request.contextPath}/resource/plugins/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/resource/js/login.js"></script>
		
		<script type="text/javascript">
			$('#referrer').val(document.referrer);
			var contentPath = '${pageContext.request.contextPath}';
			var element = document.getElementById('sendVerCode');
			function changeImgVerCode() {
				element.src = "${pageContext.request.contextPath}/createLoginCode?timestamp=" + (new Date()).valueOf();
			}
		</script>
</body>
</html>