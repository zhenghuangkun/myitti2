<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp"%>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/courseManage/experimentGroupStyle.css"/>
	
</head>

<body class="skin-1">

	<!-- 会发生错误，MessageContrller 的getMessage 函数出现错误。 -->
	<%@ include file="/WEB-INF/pages/common/header.jsp"%>

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

		<%@ include file="/WEB-INF/pages/common/sidebar.jsp"%>

		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <span>课程管理</span>
						</li>
						<li class="active">课程实验组</li>
					</ul>
				</div>
				
				<div class="page-content">
					<%@ include file="/WEB-INF/pages/common/setting.jsp"%>
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
													<label>实验组：</label>
													<input type="text" class="form-control" name="groupName" id="groupName" placeholder="实验组名称." style="width:240px"></input>
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
					
					<!-- <div class=""> -->
						<div class="row">
							<div class="widget-body">
								<div class="widget-main">
									<div>
										<div class="add tableTools-container">
											<button type="button" class="btn btn-primary btn-sm" onclick="showExperimentGroupConfigureModal()">
												<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span>
												新增实验组
											</button>
										</div>
									</div>
									
								</div>
								<!-- /.widget-main -->
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.row -->
						
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">
								<i class="ace-icon fa fa-sliders light-green bigger-120"></i>
								&nbsp;实验组信息</div>
								<table id="allExperimentGroupTb" class="table table-striped table-hover">
									
								</table>
							</div>
						</div>
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<%@ include file="/WEB-INF/pages/common/footer.jsp"%>

		<!-- 添加课程实验组 模态框 -->
		<%-- <%@ include file="experimentGroupConfigure.jsp"%> --%>
		<%@ include file="experimentGroupConfigure.jsp"%>
	</div>
	
	<!-- datatbles -->
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/extensions/Select/js/dataTables.select.min.js"></script>
	
	<!-- layui js -->
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.js" charset="utf-8"></script>
	<!-- 引入用户管理脚本 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/courseManage/experimentGroup.js"></script>
	
</body>
</html>
