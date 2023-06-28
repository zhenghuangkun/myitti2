<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<style>
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
								<span>模板管理</span>
							</li>
							<li class="active">模板库</li>
						</ul>
					</div>

					<div class="page-content">
						<div class="space-6"></div>
					
						<div class="row">
							<div class="col-xs-6 col-sm-6">
								<ul class="nav nav-pills">
									<li>
										<a role="button" href="${pageContext.request.contextPath}/template/index">模板推荐</a>
									</li>

									<li>
										<a role="button" href="${pageContext.request.contextPath}/template/collection">收藏列表</a>
									</li>

									<li class="active">
										<a role="button" href="${pageContext.request.contextPath}/template/using">模板试用</a>
									</li>
								</ul>
							</div>
							
						</div>

						<div class="space-6"></div>
						
						<div class="row">
							<div class="col-xs-6 col-sm-4">
								<label for="reviewState">状态 :</label>
								<select id="reviewState">
									<option value="0">全部</option>
									<option value="1">未审核</option>
									<option value="2">审核通过</option>
									<option value="3">审核拒绝</option>
								</select>
							</div>
							<div class="col-xs-6 col-sm-4 pull-right">
								<div class="input-group">
									<input type="text" class="form-control search-query" id="keyword" placeholder="请输入模板名称" />
									<span class="input-group-btn">
										<button id="searchBtn" type="button" class="btn btn-inverse btn-white">
											<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
											搜索
										</button>
									</span>
								</div>
							</div>
						</div>
						
						<div class="space-6"></div>
						
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">模板试用申请列表</div>
								<table id="templateTb" class="table table-striped table-hover"></table>
							</div>
						</div>
					
					</div><!-- /.page-content -->
				</div>
				
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script type= "text/javascript" src="${pageContext.request.contextPath}/resource/js/template/tmplUseApplyList.js"></script>

	</body>
</html>
