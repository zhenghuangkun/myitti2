<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="navbar" class="navbar navbar-default ace-save-state">
	<div class="navbar-container ace-save-state" id="navbar-container">
		<div class="navbar-header pull-left">
			<a class="navbar-brand">
				<small>
					<i class="fa fa-cloud"></i>
					云普乐斯教学实验云平台
				</small>
			</a>
		</div>

		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="purple dropdown-modal">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-bell icon-animated-bell"></i>
						<span class="badge badge-important" id="_msg_total_size"></span>
					</a>

					<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							通知
						</li>

						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar navbar-pink" id="_msg_ul">

							</ul>
						</li>
					</ul>
				</li>

				<li class="light-blue dropdown-modal">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<span onmouseover="MouseOver(this)" onmouseout="MouseOut(this)">
							<img class="nav-user-photo"
								 src="${pageContext.request.contextPath}/resource/assets/avatars/user.jpg" alt="Jason's Photo"/>
							<span class="user-info">
								<small>欢迎</small>
								<small>${userRealName}</small>
								<input type="hidden" id="departmentName" value="${departmentName}" />
								<input type="hidden" id="schoolName" value="${schoolName}" />
							</span>
						</span>
						<i class="ace-icon fa fa-caret-down"></i>
					</a>

					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
<!-- 						<li> -->
<%-- 							<a href="${pageContext.request.contextPath}/personnalInfo"> --%>
<!-- 								<i class="ace-icon glyphicon glyphicon-user"></i> -->
<!-- 								个人信息 -->
<!-- 							</a> -->
<!-- 						</li> -->
						<li>
							<a href="${pageContext.request.contextPath}/personnalPwd">
								<i class="ace-icon fa fa-key"></i>
								修改密码
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/logout.do">
								<i class="ace-icon fa fa-power-off"></i>
								安全退出
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>

<script>
/**
 * 鼠标的移动事件
 */
function MouseOver(obj){	
	if($("#schoolName").val()!=''){//学校名称
		var name="";
		if($("#departmentName").val()!=''){//学院/系名称
			name=$("#schoolName").val()+"<br/>"+$("#departmentName").val();
		}else{
			name=$("#schoolName").val();
		}	
		layer.tips(name,obj,{tips: [3, '#FF6666'],time: -1});
	}
}
function MouseOut(obj){
	layer.closeAll();
}
</script>
<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
