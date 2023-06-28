<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.css" />
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
									<li class="active">
										<a role="button" href="${pageContext.request.contextPath}/template/index">模板推荐</a>
									</li>

									<li>
										<a role="button" href="${pageContext.request.contextPath}/template/collection">收藏列表</a>
									</li>

									<li>
										<a role="button" href="${pageContext.request.contextPath}/template/using">模板试用</a>
									</li>
								</ul>
							</div>
<%-- 							<button class="btn btn-white btn-default btn-round" onclick="window.open('${pageContext.request.contextPath}/template/list','_blank')">
								<i class="glyphicon glyphicon-plus bigger-120 blue"></i>
								添加模板
							</button> --%>
							<div class="col-xs-6 col-sm-4 pull-right">
								<div class="input-group">
									<input type="text" class="form-control search-query" id="keyword" placeholder="请输入模板名称" value="${keyword}"/>
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
						
						<c:forEach var="type" items="${tmplType}">
							<div class="row">
								<div class="col-xs-12">
									<h3 class="header smaller lighter blue">
										${type.itemName}
										<small>
											<a href="${pageContext.request.contextPath}/template/all" target="_blank">
												(查看全部模板
												<i class="ace-icon fa fa-external-link"></i>)
											</a>
										</small>
									</h3>
								</div>
							</div>
							<div class="row">
								<c:forEach var="tmpl" items="${tmplList}">
									<c:if test="${tmpl.tmplType == type.itemId}">
										<div class="col-xs-6 col-sm-4 col-md-3">
											<div class="thumbnail blue" role="button" onclick="window.open('${pageContext.request.contextPath}/template/info?tmplId=${tmpl.tmplId}','_blank')">
												<span class="search-promotion label label-success arrowed-in arrowed-in-right">
													<c:choose>
													   <c:when test="${tmpl.tmplFromFlag == 1}"> 
													   		平台推荐
													   </c:when>
													   <c:otherwise> 
													   		教师共享
													   </c:otherwise>
													</c:choose>
												</span>
												<c:choose>
												   <c:when test="${tmpl.tmplImg != null}"> 
												   		<img class="media-object" src="${tmpl.tmplImg.fileUrl}" alt="图片加载错误"/> 
												   </c:when>
												   <c:otherwise> 
												   		<img class="media-object" src="${pageContext.request.contextPath}/resource/images/template/templatelogo.jpg" alt="图片加载错误"/> 
												   </c:otherwise>
												</c:choose>
												<div class="caption">
													<div class="clearfix">
														<span class="pull-right label label-grey info-label">${tmpl.collectCount}人</span>
				
														<div class="pull-left bigger-110">
															<div class="raty" data-score="${tmpl.evaluateAvgrate}"></div>
														</div>
													</div>
				
													<h4 >
														${tmpl.tmplName}
													</h4>
													<p style="height: 37.2px">${tmpl.description}</p>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</c:forEach>
						
					</div><!-- /.page-content -->
				</div>
				
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		<script src="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.js"></script>
		<script type="text/javascript">
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
		<script type= "text/javascript" src="${pageContext.request.contextPath}/resource/js/template/tmplIndex.js"></script>

	</body>
</html>
