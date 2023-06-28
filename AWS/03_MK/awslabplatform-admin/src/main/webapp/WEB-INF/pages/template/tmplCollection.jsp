<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/paginator/jqpaginator.css" />
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

									<li class="active">
										<a role="button" href="${pageContext.request.contextPath}/template/collection">收藏列表</a>
									</li>

									<li>
										<a role="button" href="${pageContext.request.contextPath}/template/using">模板试用</a>
									</li>
								</ul>
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
							<c:forEach var="pageData" items="${pageInfo.data}">
								<div class="col-xs-6 col-sm-4 col-md-3">
									<div class="thumbnail blue" role="button" onclick="window.open('${pageContext.request.contextPath}/template/info?tmplId=${pageData.tmplId}','_blank')" >
										<span class="search-promotion label label-success arrowed-in arrowed-in-right">
											<c:choose>
											   <c:when test="${pageData.tmplFromFlag == 1}"> 
											   		平台推荐
											   </c:when>
											   <c:otherwise> 
											   		教师共享
											   </c:otherwise>
											</c:choose>
										</span>
										<c:choose>
										   <c:when test="${pageData.tmplImg != null}"> 
										   		<img class="media-object" src="${pageData.tmplImg.fileUrl}" alt="图片加载错误"/> 
										   </c:when>
										   <c:otherwise> 
										   		<img class="media-object" src="${pageContext.request.contextPath}/resource/images/template/templatelogo.jpg" alt="图片加载错误"/> 
										   </c:otherwise>
										</c:choose>
										<div class="caption">
											<div class="clearfix">
												<span class="pull-right label label-grey info-label">${pageData.collectCount}人</span>
		
												<div class="pull-left bigger-110">
													<div class="raty" data-score="${pageData.evaluateAvgrate}"></div>
												</div>
											</div>
		
											<h4 >
												${pageData.tmplName}
											</h4>
											<p>${pageData.description}</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						
						<div class="row" style="position: fixed;bottom:60px;right:40%">
						 	<div class="col-sm-12 customBootstrap">
						 		 <ul id="pagination" class="pagination"></ul>
						 	</div>
						 </div>
						
					</div><!-- /.page-content -->
				</div>
				
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		<script src="${pageContext.request.contextPath}/resource/pulgins/paginator/jqpaginator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.js"></script>
		<script type="text/javascript">
			var currentPage = ${pageInfo.nowpage};//获取当前页
			var totalSize = ${pageInfo.recordsTotal};//获取总数
			var perSize = ${pageInfo.pagesize};//获取每页显示数
			var keyword = "${pageInfo.condition.keyword}";//获取搜索关键词
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
		<script type= "text/javascript" src="${pageContext.request.contextPath}/resource/js/template/tmplCollection.js"></script>

	</body>
</html>
