<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/select2.min.css" />
		<style>
			 .form-group-margin{
			 	 margin-left: 10px;
			 	 margin-top: 10px;
			 	 margin-bottom: 10px;
			 }
			 #menuTb tbody tr td{
				 word-break: break-all;
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
							<li class="active">菜单管理</li>
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
											    <label class="control-label">菜单等级：</label>
												<select id="menuLevelSearch">
													<option value="-1">全部</option>
													<option value="0">一级菜单</option>
													<option value="1">二级菜单</option>
													<option value="2">三级菜单</option>
												</select>
											</div>

											<div class="form-group form-group-margin">
												<label class="control-label" >菜单名称：</label>
												<input type="text" class="form-control" id="menuNameSearch"></input>
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
							        data-target="#addMenuModel">
								<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span>
								新增
							</button>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">菜单列表</div>
								<table id="menuTb" class="table table-striped table-hover"></table>
							</div>
						</div>

					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>

		<!-- 添加菜单模态框（Modal） -->
		<form class="form-horizontal" role="form" method="post" action="" id="addMenuForm" onsubmit="return addMenu()">
			<div class="modal fade" id="addMenuModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">添加菜单</h4>
			            </div>
			            <div class="modal-body">
				            	 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="" >菜单等级：</label>
									<div class="col-sm-9">
										<select  id="addMenuLevel" name="menuLevel">
											<option value="0">一级菜单</option>
											<option value="1">二级菜单</option>
											<option value="2">三级菜单</option>
										</select>
									</div>
								 </div>
								 <div id="addPIDDiv" class="form-group" style="display: none;">
									<label class="col-sm-3 control-label no-padding-right" for="">父菜单：</label>
									<div class="col-sm-9">
										<select  id="addMenuPID" name="menuPID">
											<option value='0'></option>
										</select>
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="menuName">菜单名称：</label>
									<div class="col-sm-9">
										<input type="text" id="menuName" name="menuName" class="form-control" />
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="menuIcon">菜单图标：</label>
									<div class="col-sm-9">
										<input type="text" id="menuIcon" name="menuIcon" class="form-control" />
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="menuUrl">菜单链接：</label>
									<div class="col-sm-9">
										<input type="text" id="menuUrl" name="menuUrl" class="form-control" />
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="menuDESC">菜单描述：</label>
									<div class="col-sm-9">
										<input type="text" id="menuDESC" name="menuDESC" class="form-control" />
									</div>
								 </div>
								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="menuSort">排序标识：</label>
									<div class="col-sm-9">
										<input type="text" id="menuSort" name="menuSort" class="form-control" />
									</div>
								 </div>
								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="permissionIdentifier">权限标识符：</label>
									<div class="col-sm-9">
										<input type="text" id="permissionIdentifier" name="permissionIdentifier" class="form-control" />
									</div>
								 </div>

						</div>
			            <div class="modal-footer">
			                <button type="submit" class="btn btn-primary">提交</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			 </div>
		 </form>

		<form class="form-horizontal" role="form" method="post" action="" id="updateMenuForm" onsubmit="return updateMenu()">
			<!-- 修改菜单模态框（Modal） -->
			<div class="modal fade" id="updateMenuModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">修改菜单</h4>
			            </div>
       			            <div class="modal-body">
       			            	<input type="text"  name="menuId" style="display: none;"/>
				            	 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">菜单等级：</label>
									<div class="col-sm-9">
										<select  id="updateMenuLevel" name="menuLevel">
											<option value="0">一级菜单</option>
											<option value="1">二级菜单</option>
											<option value="2">三级菜单</option>
										</select>
									</div>
								 </div>
								 <div id="updatePIDDiv" class="form-group" style="display: none;">
									<label class="col-sm-3 control-label no-padding-right">父菜单：</label>
									<div class="col-sm-9">
										<select  id="updateMenuPID" name="menuPID">
											<option value='0'></option>
										</select>
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">菜单名称：</label>
									<div class="col-sm-9">
										<input type="text"  name="menuName" class="form-control" />
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" >菜单图标：</label>
									<div class="col-sm-9">
										<input type="text"  name="menuIcon" class="form-control" />
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" >菜单链接：</label>
									<div class="col-sm-9">
										<input type="text"  name="menuUrl" class="form-control" />
									</div>
								 </div>

								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" >菜单描述：</label>
									<div class="col-sm-9">
										<input type="text"  name="menuDESC" class="form-control" />
									</div>
								 </div>
								 <div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"  >排序标识：</label>
									<div class="col-sm-9">
										<input type="text"  name="menuSort" class="form-control" />
									</div>
								 </div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" >权限标识符：</label>
									<div class="col-sm-9">
										<input type="text"  name="permissionIdentifier" class="form-control" />
									</div>
								</div>

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
		<script src="${pageContext.request.contextPath}/resource/assets/js/select2.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
		<script type="text/javascript">
			var contextPath = '${pageContext.request.contextPath}';
		</script>
		<script src="${pageContext.request.contextPath}/resource/js/systemManage/menu.js"></script><!-- 引入菜单管理脚本 -->
	</body>
</html>
