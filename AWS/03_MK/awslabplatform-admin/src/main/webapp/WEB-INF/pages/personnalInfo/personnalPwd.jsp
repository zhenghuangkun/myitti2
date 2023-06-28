<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp" %>
	
	<style type="text/css">
	.container{
		width:600px;
		margin:100px auto;
	}
	.container>div{
		margin-bottom:15px;
	}
	input{
		display: inline-block;
		border:0;
		padding:0;
		margin:0;
	}
	#oldPwd,#newPwd,#confirmPwd{
		width:260px;
		height:34px;
		border:1px solid #ccc;
		padding:0 10px;
		font-size: 14px;
		color:#ccc;
		outline: none
	}
	label{
		display:inline-block;
		width:150px;
		text-align: right;
		font-size:16px;
	}
	#btn{
		display: inline-block;
		width:160px;
		height:40px;
		text-align:center;
		font-size:14px;
		background: #1e9fff;
		color:#fff;
		margin-left:205px;
	}
	#oldPwdIco{
		display: none;
		font-size: 14px;
		color:#888;
	}
	.warn{
		display: inline-block;
		margin-left:150px;
		width:22px;
		height:22px;
		background: url("${pageContext.request.contextPath}/resource/images/adm_ico00.png");
		background-repeat: no-repeat;
		background-size:22px 22px;
		vertical-align: top;
	}
	.right{
		display: inline-block;
		width:22px;
		height:22px;
		background: url("${pageContext.request.contextPath}/resource/images/adm_ico01.png");
		background-repeat: no-repeat;
		background-size:22px 22px;
		vertical-align: top;
	}
	.cuo{
		display: inline-block;
		width:22px;
		height:22px;
		background: url("${pageContext.request.contextPath}/resource/images/adm_ico02.png");
		background-repeat: no-repeat;
		background-size:22px 22px;
		vertical-align: top;
	}
	#newPwdIco{
		display: none;
		font-size: 14px;
		color:#888;
	}
	#confirmPwdIco{
		display: none;
		font-size: 14px;
		color:#888;
	}
	.btn-wrapper{
		margin-top: 15px;
	}
	</style>
</head>

<body class="skin-1">
	<%@ include file="/WEB-INF/pages/common/header.jsp" %>
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try{ace.settings.loadState('main-container')}catch(e){}
		</script>

		<%@ include file="/WEB-INF/pages/common/sidebar.jsp" %>

		<div class="main-content" >
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<span style="font-size:16px">修改密码</span>
						</li>
					</ul>
				</div>

				<div class="page-content">				
					<div class="row">
						<div class="container">
							<div class="userName-wrapper">
								<label>旧密码:</label>
								<input type="password" id="oldPwd" placeholder="请输入旧密码">
								<span id="oldPwdIco"></span>
							</div>
							
							<div class="passWord-wrapper">
								<label>新密码:</label>
								<input type="password" id="newPwd" placeholder="请输入新密码">
								<span id="newPwdIco"></span>
							</div>
							
							<div class="passWord1-wrapper">
								<label>确认密码:</label>
								<input type="password" id="confirmPwd" placeholder="请重新确认新密码">
								<span id="confirmPwdIco"></span>
							</div>
							
							<div class="btn-wrapper">
								<input type="button" id="btn" value="确认修改">
							</div>
						</div>
					</div>				
				</div>

			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.min.js" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script type="text/javascript">
	
	$('#oldPwd').blur(function(){
		var oldPwd=$("#oldPwd").val();
		if(oldPwd==""){
			$("#oldPwdIco").css('display','inline-block');
			$("#oldPwdIco").html('<i class="cuo"></i>旧密码不能为空！');
		}else{
			$("#oldPwdIco").css('display','none');
		}
	})
	
 	$('#newPwd').blur(function(){
 		var newPwd=$("#newPwd").val();
 		var regex = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,30}/;
 		if(newPwd==''){
 			$("#newPwdIco").css('display','inline-block');
 			$("#newPwdIco").html('<i class="cuo"></i>新密码不能为空！');
 		}else if(!newPwd.match(regex)){
 			$("#newPwdIco").css('display','inline-block');
 			$("#newPwdIco").html('<i class="warn"></i>密码必须由6-30位字母、数字、特殊字符构成!');
 		}else{
 			$("#newPwdIco").css('display','none');
 		}
	})
	
	/* 修改密码框 */
	$('#confirmPwd').blur(function(){
 		var confirmPwd=$("#confirmPwd").val();
 		if(confirmPwd==''){
 			$("#confirmPwdIco").css('display','inline-block');
 			$("#confirmPwdIco").html('<i class="cuo"></i>确认密码不能为空！');
 		}else if(confirmPwd!=$("#newPwd").val()){
 			$("#confirmPwdIco").css('display','inline-block');
 			$("#confirmPwdIco").html('<i class="warn"></i>前后两次密码输入不一致！');
 		}else{
 			$("#confirmPwdIco").css('display','none');
 		}
	})
		
	/* 修改密码前端判断  */
	function checkPwd(){
		var regex = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,30}/;
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var confirmPwd = $("#confirmPwd").val();
		if(oldPwd==""){
			layer.alert("请输入旧密码！",{
    			icon: 5,
        		title: "提示"
        	});
			return false;
		}else if(newPwd==""){
			layer.alert("请输入新密码！",{
    			icon: 5,
        		title: "提示"
        	});
			return false;
		}else if(confirmPwd==""){
			layer.alert("请填写确认密码！",{
    			icon: 5,
        		title: "提示"
        	});
			return false;
		}else if(!newPwd.match(regex)){
 			layer.alert("密码必须由6-30位字母、数字、特殊字符构成!",{
    			icon: 5,
        		title: "提示"
        	});
 			return false;
 		}else if(confirmPwd!=newPwd){
			layer.alert("前后两次密码输入不一致！",{
    			icon: 5,
        		title: "提示"
        	});
			return false;
		}else if(newPwd==oldPwd){
			layer.alert("新密码与修改的密码相同！",{
    			icon: 5,
        		title: "提示"
        	});
			return false;
		}
		else{
			return true;
		}
	} 
	
	$("#btn").click(function(){
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var confirmPwd = $("#confirmPwd").val();
		if(checkPwd()){
			$.ajax({
				type: "POST",
	            url: "${pageContext.request.contextPath}/personnalEditPwd",
	            data: {oldPwd:oldPwd,newPwd:newPwd,confirmPwd:confirmPwd},
	            dataType: "json",
	            success: function(data){
	            	if(data.resultFlag){
	            		setTimeout(function(){//两秒后跳转  
	                        alert(data.message);   
	                        location.href="${pageContext.request.contextPath}/index";//PC网页式跳转  
	                     },2000);  
	            	}else{
	            		layer.alert(data.message,{
	              			 title: "提示"
	   		            });
	            	}
	            }
			})
		}
	}) 
	
	</script>

</body>
</html>


