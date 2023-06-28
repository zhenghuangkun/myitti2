<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/paginator/jqpaginator.css" />
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
							<li class="active">实验模板设计</li>
						</ul>
					</div>

					<div class="page-content">
						<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
						<div class="row" style="background-color:#076eb0">
						    <div class="col-sm-12 col-md-12">
						    	<div class="center">
						            <img role="button" height="100px" src="${pageContext.request.contextPath}/resource/images/template/time.png" 
						            		onclick="window.open('${pageContext.request.contextPath}/tmpldesign/design','_blank')" alt="图片加载错误">
							        <div class="center">
							        	<h4><a role="button" style="font-size:16px;color:#fff;text-decoration:none" onclick="window.open('${pageContext.request.contextPath}/tmpldesign/design','_blank')">点击此处设计模板</a></h4>
							        </div>
						        </div>
						    </div>
						 </div>
						
						<div class="row">
							<div class="search-area well col-xs-12">
								<div class="row">
									<div class="col-xs-7 col-sm-6 col-md-5 pull-left">
										<div id="toggle-result-format" class="btn-group btn-overlap" data-toggle="buttons">
											<label class="btn btn-lg btn-white btn-success active" data-class="btn-success">
												<input type="radio" value="optTime" />
												<i class="icon-only ace-icon fa fa-th">按时间排序</i>
											</label>

<!-- 											<label class="btn btn-lg btn-white btn-grey" data-class="btn-primary">
												<input type="radio" value="evaluateAvgrate"  />
												<i class="icon-only ace-icon fa fa-list">最热</i>
											</label> -->
										</div>
									</div>
									
									<div class="col-xs-5 col-sm-4 col-md-3 pull-right">
										<div class="input-group">
											<input type="text" class="form-control" id="keyword" name="keyword" placeholder="请输入模板名称" />
											<div class="input-group-btn">
												<button id="searchBtn" type="button" class="btn btn-default no-border btn-sm">
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
									<div class="thumbnail" style="white-space:nowrap;min-width:220px">
										<c:choose>
										   <c:when test="${pageData.tmplImg != null}"> 
										   		<img class="media-object" src="${pageData.tmplImg.fileUrl}" alt="图片加载错误"/> 
										   </c:when>
										   <c:otherwise> 
										   		<img class="media-object" src="${pageContext.request.contextPath}/resource/images/template/templatelogo.jpg" alt="图片加载错误"/> 
										   </c:otherwise>
										</c:choose>
										
										<div class="caption">
							                <h4>${pageData.tmplName}</h4>
							                <p style="height: 37.2px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;">${pageData.description}...</p>
							                <p>
							                	<c:if test="${pageData.releaseStatus == 0}">
							                		<a name="releaseBtn" class="btn btn-primary btn-round" role="button" data-ref="${pageData.tmplId}">发布</a>
							                		<a name="editBtn" class="btn btn-primary btn-round" role="button" data-ref="${pageData.tmplId}">编辑</a>
							                    </c:if>
							                    <a name="deleteBtn" class="btn btn-default btn-round" role="button" data-ref="${pageData.tmplId}">删除</a>
							                </p>
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
					</div>
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		
		<script src="${pageContext.request.contextPath}/resource/pulgins/paginator/jqpaginator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		
		<script type="text/javascript">
			var currentPage = ${pageInfo.nowpage};//获取当前页
			var totalSize = ${pageInfo.recordsTotal};//获取总数
			var perSize = ${pageInfo.pagesize};//获取每页显示数
			var defalutSort = "${pageInfo.sort}";//获取默认排序 
			var keyword = "${pageInfo.condition.keyword}";//获取搜索关键词
		</script>
		
		<script src="${pageContext.request.contextPath}/resource/js/template/tmplDesignList.js"></script>
	</body>
</html>
