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
							<li class="active">个人模板</li>
						</ul>
					</div>

					<div class="page-content">
						<div class="space-6"></div>
					
						<div class="row">
							<div class="col-xs-6 col-sm-6">
								<ul class="nav nav-pills">
									<li class="active">
										<a role="button" href="${pageContext.request.contextPath}/tmplmaking/index">实例列表</a>
									</li>

									<li>
										<a role="button" href="${pageContext.request.contextPath}/tmplmaking/imagelist">镜像管理</a>
									</li>

									<li>
										<a role="button" href="${pageContext.request.contextPath}/tmplmaking/makelist">个人模板</a>
									</li>
								</ul>
							</div>
							
						</div>

						<div class="space-6"></div>
						
						<div class="row">
							<div class="col-xs-6 col-sm-4 pull-right">
								<div class="input-group">
									<input type="text" class="form-control search-query" id="keyword" placeholder="请输入实例ID" />
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
								<div class="table-header">实例列表</div>
								<table id="instanceTb" class="table table-striped table-hover"></table>
							</div>
						</div>
					
					</div><!-- /.page-content -->
				</div>
				
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		
		<!-- 模态框（Modal） -->
		<div id="imageMakeModel" class="modal" tabindex="-1">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" >&times;</button>
		                <h4 class="blue bigger" >镜像制作</h4>
		            </div>
		            <div class="modal-body">
		            	<div class="form-horizontal"  id="imageMakeForm">
							 <div class="form-group">
								<label class="col-sm-2">镜像描述：</label>
								<div class="col-sm-8">
									<textarea  id="imageDescribe" class="form-control" name="imageDescribe" rows="10" maxlength="255"></textarea>
								</div>
							 </div>
						</div>
					</div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-primary btn-round" id="makeSubmit">确定</button>
		                <button type="button" class="btn btn-default btn-round" data-dismiss="modal">取消</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		 </div>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script type= "text/javascript" src="${pageContext.request.contextPath}/resource/js/template/tmplMaking/tmplInstanceList.js"></script>
		
	</body>
</html>