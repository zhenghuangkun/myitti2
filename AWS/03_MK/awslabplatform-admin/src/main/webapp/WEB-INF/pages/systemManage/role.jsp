<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
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
							<li class="active">角色管理</li>
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
						<div class="add tableTools-container">
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
							        data-target="#addRoleModel">
								<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span>
								新增
							</button>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">角色列表</div>
								<table id="roleTb" class="table table-striped table-hover">

								</table>
							</div>
						</div>

					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>

		<!-- 添加角色模态框（Modal） -->
		<form class="form-horizontal" method="post" action="" role="form" id="addRoleForm">
			<div class="modal fade" id="addRoleModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">添加角色</h4>
			            </div>
			            <div class="modal-body">

			          		  <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >角色类型：</label>
								<div class="col-sm-9">
									<select name="roleType">
										<option value="0">超级管理员</option>
										<option value="1">平台管理员</option>
										<option value="2">院系管理员</option>
										<option value="3">教师</option>
										<option value="4">助教</option>
										<option value="5">学生</option>
									</select>
								</div>
							 </div>

							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >角色名称：</label>
								<div class="col-sm-9">
									<input type="text"  class="form-control" name="roleName"/>
								</div>
							 </div>

							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >角色描述：</label>
								<div class="col-sm-9">
									<input type="text" id="roleDESC" class="form-control" name="roleDESC"/>
								</div>
							 </div>
						</div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-primary" onclick="addRole()">提交</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancle()">取消</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			 </div>
		  </form>

		<!-- 修改角色模态框（Modal） -->
		<form class="form-horizontal" method="post" action="" role="form" id="updateRoleForm">
			<div class="modal fade" id="updateRoleModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" >修改角色</h4>
			            </div>
			            <div class="modal-body">
			            	 <input type="text"  name="roleId" style="display: none;"/>
			          		 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >角色类型：</label>
								<div class="col-sm-9">
									<select name="roleType">
										<option value="0">超级管理员</option>
										<option value="1">平台管理员</option>
										<option value="2">院系管理员</option>
										<option value="3">教师</option>
										<option value="4">助教</option>
										<option value="5">学生</option>
									</select>
								</div>
							 </div>

							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"  >角色名称：</label>
								<div class="col-sm-9">
									<input type="text"  class="form-control" name="roleName"/>
								</div>
							 </div>

							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="upDataRoleDESC">角色描述：</label>
								<div class="col-sm-9">
									<input type="text" id="upDataRoleDESC" class="form-control" name="roleDESC"/>
								</div>
							 </div>

						</div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-primary" onclick="updateRole()">提交</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancle()">取消</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			 </div>
		 </form>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script type="text/javascript">
			var contextPath = '${pageContext.request.contextPath}';
		</script>

		<script src="${pageContext.request.contextPath}/resource/js/systemManage/role.js"></script><!-- 引入角色管理脚本 -->
	</body>
</html>
