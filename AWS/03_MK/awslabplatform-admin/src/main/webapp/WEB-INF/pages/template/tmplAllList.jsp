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

	<body class="no-skin">
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="row">
							<div class="search-area well col-xs-12">
								<div class="row">
									<div class="col-xs-6 col-sm-5 col-md-4 pull-left">
										<div id="toggle-result-format" class="btn-group btn-overlap" data-toggle="buttons">
											<label class="btn btn-lg btn-white btn-success active" data-class="btn-success" aria-pressed="true">
												<input type="radio" value="optTime"/>
												<i class="icon-only ace-icon fa fa-th">最新</i>
											</label>
											
											<label class="btn btn-lg btn-white btn-success active" data-class="btn-success" aria-pressed="true">
												<input type="radio" value="evaluateAvgrate"/>
												<i class="icon-only ace-icon fa fa-list">最热</i>
											</label>
										</div>
									</div>
									
									<div class="col-xs-5 col-sm-4 col-md-3 pull-right">
										<div class="input-group">
											<input type="text" class="form-control" id="keyword" name="keyword" placeholder="请输入模板名称" />
											<div class="input-group-btn">
												<button id="searchBtn" type="button" class="btn btn-default no-border btn-sm" >
													<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<c:forEach var="pageData" items="${pageInfo.data}">
								<div class="col-sm-6 col-md-3">
									<div class="thumbnail blue" role="button" onclick="window.open('${pageContext.request.contextPath}/template/info?tmplId=${pageData.tmplId}','_blank')">
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
											<p style="height: 37.2px">${pageData.description}</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						
						<div class="row center">
						 	<div class="col-sm-12 customBootstrap">
						 		 <ul id="pagination" class="pagination"></ul>
						 	</div>
						 </div>
						
					</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div>
		
		<script src="${pageContext.request.contextPath}/resource/pulgins/paginator/jqpaginator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.js"></script>
		<script type="text/javascript">
			var currentPage = ${pageInfo.nowpage};//获取当前页
			var totalSize = ${pageInfo.recordsTotal};//获取总数
			var perSize = ${pageInfo.pagesize};//获取每页显示数
			var defalutSort = "${pageInfo.sort}";//获取默认排序 
			var keyword = "${pageInfo.condition.keyword}";//获取搜索关键词
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
		
		<script src="${pageContext.request.contextPath}/resource/js/template/tmplAllList.js"></script>

	</body>
</html>