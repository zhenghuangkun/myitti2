<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/courseManage/courseManage.css"/>
</head>

<body class="skin-1">
	<!-- 会发生错误，MessageContrller 的getMessage 函数出现错误。 -->
	<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	

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
							<span>课程管理</span>
						</li>
						<li class="active">我的课程</li>
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
													<label>课程：</label>
													<input type="text" class="form-control" id="inputCourseIdOrName" placeholder="课程名称." style="width:240px"></input>
												</div>
												 &nbsp;&nbsp;&nbsp;&nbsp;
												<div class="form-group" style="padding-left: 40px;">
													<label>状态：</label>
													<select class="form-control" id="inputState">
														<option value="-1">全部</option>
														<option value="2">未提交</option>
														<option value="3">已提交</option>
														<option value="4">未通过</option>
														<option value="5">已通过</option>
														<option value="6">已发布</option>
														<option value="1">终止</option>
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
						<button type="button" class="btn btn-primary btn-sm" onclick="addCoursePage()">
							<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span>
							新增课程
						</button>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="table-header">
							<i class="ace-icon fa fa-sliders light-green bigger-120"></i>
							&nbsp;课程信息</div>
							<table id="courseTb" class="table table-striped table-hover">

							</table>
						</div>
					</div>
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->
		
		<!-- <div class="loader"></div> -->
		
		<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
	</div>
	
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/extensions/Select/js/dataTables.select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.js"></script>
	<script type="text/javascript">
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/courseManage/course_list.js"></script><!-- 引入用户管理脚本 -->
	
</body>
</html>
