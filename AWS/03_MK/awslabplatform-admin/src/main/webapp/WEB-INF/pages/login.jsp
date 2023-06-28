<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp" %>
	<style>
		.header {
			height: 50px;
			border-bottom: 1px solid #e2e2e2;
			position: relative;
			font-family: "Microsoft Yahei";
		}

		.header .switch {
			height: 45px;
			position: absolute;
			left: 30px;
			bottom: 0;
			font-size: 16px;
		}

		.header .switch #switch_qlogin {
			margin-right: 85px;
		}

		.header .switch .switch_btn {
			color: #999;
			display: inline-block;
			height: 45px;
			line-height: 45px;
			outline: none;
			*hide-focus: expression(this.hideFocus=true);
		}

		.header .switch .switch_btn_focus {
			color: #333;
			display: inline-block;
			height: 45px;
			line-height: 45px;
			outline: none;
			*hide-focus: expression(this.hideFocus=true);
		}

		.header .switch .switch_btn:hover {
			color: #333;
			text-decoration: none;
		}

		.header .switch .switch_btn_focus:hover {
			text-decoration: none;
		}

		#switch_bottom {
			position: absolute;
			bottom: -1px;
			_bottom: -2px;
			border-bottom: 2px solid #848484;
		}
	</style>
</head>

<body class="login-layout" style="background:url(${pageContext.request.contextPath}/resource/images/bg.jpg) top left;
		background-size:100% 100%;overfolw:hidden;">
<div class="space-32"></div>
<div class="main-container">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-6">
				<div class="login-container">
					<div class="space-30"></div>
					<img alt="logo" src="${pageContext.request.contextPath}/resource/images/aws.png"
						 style="width:50px;height:50px">
					<span class="white" id="id-text2"
						  style="font-weight:bold;font-size:30px;position:absolute;margin-left:15px;margin-top:2px">云普乐斯教学实验云平台</span>
					<div class="space-12"></div>
					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border" style="background:#9fcbe8;">
							<div class="widget-body">
								<div class="widget-main">
									<div id="alert_warning" class="alert alert-warning red" style="display: none">
										<a href="" class="close" data-dismiss="alert">
											&times;
										</a>
										<span id="errorInfo" class="ace-icon fa fa-info-circle"></span>
									</div>
									<h4 class="header blue lighter bigger">
										<i class="ace-icon fa fa-coffee green"></i>
										登录
									</h4>

									<div class="space-6"></div>

									<div class="web_qr_login" id="web_qr_login" style="display: block;">
										<!--手机登录-->
										<div class="web_login" id="web_login">
											<form id="loginForm">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="loginInfo" id="loginInfo"
																   class="form-control" placeholder="请输入手机账号或邮箱账号"
																   style="border-radius:15px" required autofocus/>
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="userPwd" class="form-control"
																   placeholder="请输入登录密码" required/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="verCode" class="col-sm-8"
																   placeholder="验证码"/>
														</span>
														<button id="verCode" class="col-sm-4 btn btn-sm"
																style="float:right" type="button"
																value="发送验证码" onclick="sendVerificationCode()">发送验证码
														</button>
													</label>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" id="rememberMePhone"
																   name="rememberMePhone" class="ace"/>
															<span class="lbl">记住密码</span>
														</label>
														<label class="inline" style="float:right">
															<a href="${pageContext.request.contextPath}/ForgetPwd/adminForgetPwd">忘记密码？</a>
														</label>
													</div>

													<div>
														<button type="submit" id="login"
																class="width-100 center-block btn btn-sm btn-info">
															<span class="bigger-110">登录</span>
														</button>
													</div>

<!-- 													<div class="clearfix"> -->
<!-- 														<label style="float:right;margin-top:11px"> -->
<!-- 															<a>注册账号</a> -->
<!-- 														</label> -->
<!-- 													</div> -->
													<!--登录end-->
													<div class="space-4"></div>
												</fieldset>
											</form>
										</div><!-- 手机登录end -->
									</div>

								</div>
							</div><!-- /.widget-body -->
						</div><!-- /.login-box -->
					</div>
				</div><!-- /.login-container -->
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!--/.main-content  -->
</div><!-- /.main-container -->
<script type="text/javascript">
	var contentPath = '${pageContext.request.contextPath}';
	//倒计时
	var wait = 60;
	if ('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/resource/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");

	$(function () {
		$('#switch_qlogin').click(function () {
			$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
			$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
			$('#switch_bottom').animate({left: '0px', width: '70px'});
			$('#qlogin').css('display', 'none');
			$('#web_qr_login').css('display', 'block');
		});
		$('#switch_login').click(function () {
			$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
			$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
			$('#switch_bottom').animate({left: '154px', width: '70px'});
			$('#qlogin').css('display', 'block');
			$('#web_qr_login').css('display', 'none');
		});
		if (getParam("a") == '0') {
			$('#switch_login').trigger('click');
		}
	});

	//根据参数名获得该参数 pname等于想要的参数名
	function getParam(pname) {
		var params = location.search.substr(1); // 获取参数 平且去掉？
		var ArrParam = params.split('&');
		if (ArrParam.length == 1) {
			//只有一个参数的情况
			return params.split('=')[1];
		}
		else {
			//多个参数参数的情况
			for (var i = 0; i < ArrParam.length; i++) {
				if (ArrParam[i].split('=')[0] == pname) {
					return ArrParam[i].split('=')[1];
				}
			}
		}
	}

	function sendVerificationCode() {
		/*window.location.href=contentPath+'/sendEmailValidationCode?Email='+ $(" #loginInfo ").val();*/
		var Email = $(" #loginInfo ").val();
		$.ajax({
			url: contextPath + '/sendEmailVerCode',
			data: {Email: Email},
			type: 'POST',
			success: function (data) {
				/*var retData = $.parseJSON(data);
                if (!retData.sucess) {
                    layer.msg(retData.message);
                    return;
                }
                menuTb.ajax.reload(null, false);
                $('#addMenuModel').modal('hide');*/
				//返回的动作在这里做
			}
		});
	}
		
 	function forgetPwd(){
		window.location.href="${pageContext.request.contextPath}/adminForgetPwd";
	}
	
	
	function time(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.innerHTML = "发送验证码"
			// o.value = "发送验证码";
			wait = 60;
		} else {
			o.setAttribute("disabled", true);
			o.innerHTML = "重新发送("+ wait + ")";
			// o.value = "重新发送(" + wait + ")";
			wait--;
			setTimeout(function () {
				time(o)
					},
					1000)
		}
	}

	document.getElementById("verCode").onclick = function () {
		if ("" == $(" #loginInfo ").val()){
			alert("请填写用户名！")
		}else {
			sendVerificationCode();
			time(this);
		}
	}
</script>
<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/login.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/return_code.js"></script>
<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.cookie.js"></script>
</body>
</html>
