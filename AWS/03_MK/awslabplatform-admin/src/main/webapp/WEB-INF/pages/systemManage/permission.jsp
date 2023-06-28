<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/zTree/zTreeStyle/zTreeStyle.css" type="text/css">
		<style>
			 .form-group-margin{
			 	 margin-left: 10px;
			 	 margin-top: 10px;
			 	 margin-bottom: 10px;
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

			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<span>系统管理</span>
							</li>
							<li class="active">权限管理</li>
						</ul>
					</div>

					<div class="page-content">
						<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
						<div class="page-header">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="widget-title">
										<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
										搜索栏
									</h4>
								</div>

								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline" role="form">
											<div class="form-group form-group-margin">
												<label class="control-label" >角色名称：</label>
												<input type="text" class="form-control" id="roleName"></input>
											</div>
											<div class="form-group form-group-margin">
												<label class="control-label">角色类型：</label>
												<select id="roleType">
													<option value="-1">全部</option>
													<option value="0">超级管理员</option>
													<option value="1">平台管理员</option>
													<option value="2">院系管理员</option>
													<option value="3">教师</option>
													<option value="4">助教</option>
													<option value="5">学生</option>
												</select>
											 </div>
											<div class="form-group form-group-margin">
												<button type="button" class="btn btn-primary btn-sm" onclick="doSearch()">
													<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
													搜索
												</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">角色权限列表</div>
								<table id="permissonTb" class="table table-striped table-hover">

								</table>
							</div>
						</div>

					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>

		<!-- 权限配置模态框（Modal） -->
		<form class="form-horizontal" method="post" action="" role="form" id="permissionForm" onsubmit="return updatePermission()">
			<div class="modal fade" id="permissonModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">权限配置</h4>
			            </div>
			            <div class="modal-body">
							<ul id="menuTree" class="ztree"></ul>
						</div>
			            <div class="modal-footer">
			                <button type="submit" class="btn btn-primary">提交</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			 </div>
		</form>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/zTree/jquery.ztree.core.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/zTree/jquery.ztree.excheck.min.js"></script>
				<script type="text/javascript">
			var contextPath = '${pageContext.request.contextPath}';
		</script>

		<script src="${pageContext.request.contextPath}/resource/js/systemManage/permisson.js"></script><!-- 引入权限管理脚本 -->

	</body>
</html>
