<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>首页</title>
	<script src="${pageContext.request.contextPath }/resource/plugins/layer/layer.js" ></script>
	<script src="${pageContext.request.contextPath }/resource/js/httpPost.js"></script>
	<style type="text/css">
		div.layui-header ul li a {
			text-decoration: none;
		}
	.fixedhead{
		position:fixed;
		top:0;
		width:100%;
		min-width:850px;
		white-space: nowrap;
	}
	ul{
		margin:0;
	}

	.min_length{

		min-height: 800px;
	}

	.margin-left{
		margin-left:30px;
	}
	</style>

</head>
<body style="height: 100%">
	<div class="layui-header fixedhead">
		<!-- <div class="layui-logo"> -->
			<img alt="logo" src="${pageContext.request.contextPath}/resource/images/logoname.png"
					 style="padding:15px 0">
		<!-- </div> -->
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left"  style="margin-left:60px">
			<li class="layui-nav-item margin-left" id="home"><a href="${pageContext.request.contextPath}/home"><span
					style="font-size: 17px;display:inline-block;line-height:52px;margin-top:8px">首页</span></a></li>
			<li class="layui-nav-item margin-left" id="course"><a href="${pageContext.request.contextPath}/courselist"><span
					style="font-size: 17px;display:inline-block;line-height:52px;margin-top:8px">课程</span></a></li>
			<li class="layui-nav-item margin-left" id="route"><a href="${pageContext.request.contextPath}/route"><span
					style="font-size: 17px;display:inline-block;line-height:52px;margin-top:8px">路径</span></a></li>
		</ul>

	<ul class="layui-nav layui-layout-right">
		<c:choose>
			<c:when test="${sessionScope.currentUser != null}">
				<c:if test="${sessionScope.currentUser.picFileUrl==null}">
					<li class="layui-nav-item"><a href="javascript:;" onclick="user()" id="user"> <img
							src="${pageContext.request.contextPath }/resource/images/head.jpg" class="layui-nav-img">${sessionScope.currentUser.realName}</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.currentUser.picFileUrl!=null}">
					<li class="layui-nav-item"><a href="javascript:;" onclick="user()" id="user"> <img
							src="${sessionScope.currentUser.picFileUrl}" class="layui-nav-img">${sessionScope.currentUser.realName}</a>
					</li>
				</c:if>
				<li class="layui-nav-item"><a href="javascript:;" onclick="logout()" id="logout">安全退出</a></li>
			</c:when>
			<c:otherwise>
				<li class="layui-nav-item"><a href="javascript:;" onclick="login()" id="login" class="heada">登录</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>


<script src="${pageContext.request.contextPath }/resource/plugins/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
	$(function(){
		//用户是否登录
		if("${sessionScope.currentUser}"!=null&&""!="${sessionScope.currentUser}"){
			//如果有正在进行的实验弹出提示
			if("${sessionScope.experimentId}"!=null&&""!="${sessionScope.experimentId}"){
				var url = window.location.href;
				if(url.indexOf("running")==-1){//是否在实验页面
					layer.open({
				        type: 1
				        ,title:"您有实验未结束"
				        ,offset: "rt" //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
				        ,id: 'layerDemo' //防止重复弹出
				        ,area: ['200px']
				        ,btn: '继续实验'
				        ,btnAlign: 'c' //按钮居中
				        ,shade: 0 //不显示遮罩
				        ,yes: function(){
				          window.location.href="${pageContext.request.contextPath }/running?experimentId=${sessionScope.experimentId}"
						}
					});
				}
			}
		}
	});
	$(function(){
		layui.use('element', function () {
			var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

			//监听导航点击
			element.on('nav(demo)', function (elem) {
				//console.log(elem)
			});
		});
	})



	function login() {
		var URL =  document.referrer;
		var params = { //post 提交参数
		        "URL": URL
		    };
		httpPost("${pageContext.request.contextPath}/login", params);
		//window.location.href = "${pageContext.request.contextPath }/login";
	}
	function logout() {

		if($("#consoleLoginLink").val()!=null&&""!=$("#consoleLoginLink").val()
		      ||"${sessionScope.controlExperimentId}"!=null&&""!="${sessionScope.controlExperimentId}"){
			layer.confirm('还有控制台实验未结束，确定退出并释放资源？', {
				btn: ['确定', '取消'] //可以无限个按钮
			}, function(index, layero){
				//按钮【确定】的回调
				var expermentId=null;
				if($("#expermentId").val()!=null&&""!=$("#expermentId").val()){
					expermentId=$("#expermentId").val();
				}else{
					expermentId="${sessionScope.controlExperimentId}";
				}
				$.ajax({
					type: 'POST',
					url: "${pageContext.request.contextPath}/stopControlExp",
					data: "experimentId="+expermentId,
					success: function(data){
					}
				})
				window.location.href ="${pageContext.request.contextPath }/logout";
				//location.href ="${pageContext.request.contextPath }/stopControlExp?experimentId="+$("#expermentId").val();

			}, function(index){
				//按钮【取消】的回调
				window.location.href = "${pageContext.request.contextPath }/logout";
			});
		}else if("${sessionScope.experimentId}"!=null&&""!="${sessionScope.experimentId}"){
			layer.confirm('还有实验未结束，确定退出并释放资源？', {
				  btn: ['确定', '取消'] //可以无限个按钮
				}, function(index, layero){
				  //按钮【确定】的回调
				  $.ajax({
	                type: 'POST',
	                url: "${pageContext.request.contextPath }/deleteResource",
	                data: "experimentId=${sessionScope.experimentId}",
	                success: function(data){
						window.location.href = "${pageContext.request.contextPath }/logout";
	                }
	            })
				}, function(index){
				  //按钮【取消】的回调
				window.location.href = "${pageContext.request.contextPath }/logout";
			});
		}else{
			window.location.href = "${pageContext.request.contextPath }/logout";
		}
	}
	function user() {
		window.location.href = "${pageContext.request.contextPath }/user/userInfo";
	}
</script>
</body>
</html>


