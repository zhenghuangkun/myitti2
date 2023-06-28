<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


	<div id="sidebar" class="sidebar responsive ace-save-state">

		<!-- 导航 -->
		<ul id="nav" class="nav nav-list">
 			<!-- 菜单 -->
			<c:forEach var="parent" items="${sessionScope.menus}" varStatus="status">
			<li  id="status${status.index}">
				<a href="#" class="dropdown-toggle" >
					<i class="menu-icon fa ${parent.menuIcon}"></i>
					<span class="menu-text"> ${parent.menuName} </span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>

				<ul class="submenu">
					<c:forEach var="child" items="${parent.childMenus}">
					<li data-status="${status.index}" id="${child.menuId }">
						<a href="<c:if test="${!(empty child.menuUrl) && child.menuUrl !='#'}" >${pageContext.request.contextPath}</c:if>${child.menuUrl}">
							<i class="menu-icon fa fa-caret-right"></i>
							${child.menuName}
						</a>
						<b class="arrow"></b>
					</li>
					</c:forEach>
				</ul>
			</li>
			</c:forEach>
		</ul>
		
		<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
			<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
		</div>

		<script type="text/javascript">
		var liId='';
		$(function(){
				var urlstr = location.href;
				var index = -1;
 				$("#nav li").each(function () {
					var href = $(this).find("a").attr('href');
					if (urlstr.indexOf(href) > -1 && href != '') {
						$(this).addClass('active');
						index = $(this).attr('data-status');
						liId = $(this).attr('id');
				    } else {
						$(this).removeClass('active');
					}
				});
 				if (index != -1) {
					$("#status"+index).addClass('active open');
				}
		})

		</script>
	</div>
