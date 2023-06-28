<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
	</head>

	<body class="no-skin">

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
								<span>用户管理</span>
							</li>
							<li class="active">管理人员管理</li>
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
										<div class="row">
											<div class="col-xs-12">
												<form class="form-inline" role="form" >
													<div class="form-group" style="padding-left: 30px;">
														<label>单位：</label>
														<input type="text" class="form-control" id="inputUnitName"></input>
													</div>

													<div class="form-group" style="padding-left: 30px;">
														<label>用户名称：</label>
														<input type="text" class="form-control" id="inputUserRealName"></input>
													</div>
													<div class="form-group" style="padding-left: 30px;">
														<label>状态：</label>
														<select class="form-control" id="inputState">
															<option value="-1">全部</option>
															<option value="0">可用</option>
															<option value="2">不可用</option>
														</select>
													</div>
													<div class="form-group" style="padding-left: 15px;">
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
							</div>
						</div>
						<div class="add tableTools-container">
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editModal" onclick="showAddModal()">
								<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span>
								新增
							</button>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">管理人员列表</div>
								<table id="adminTb" class="table table-striped table-bordered table-hover">

								</table>
							</div>
						</div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>

		</div>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/extensions/Select/js/dataTables.select.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
        <script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
		<script src="${pageContext.request.contextPath}/pages/userManage/js/studentManage.js"></script><!-- 引入用户管理脚本 -->
	</body>
</html>
