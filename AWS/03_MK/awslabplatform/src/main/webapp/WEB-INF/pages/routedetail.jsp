<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
	<title>路径详情</title>
	
	<!-- css 引入 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/coursestyle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/timeline.css" />
	<script src="${pageContext.request.contextPath }/resource/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	
	<!-- 样式 -->
	<style type="text/css">		
    	.layout-noside {
		    height: 100%;
		    margin-top: 60px;
		}
		
		.path-desc-top{
			height:240px
		}
		
		.path-desc{
	    	height: 100px;
	    	font-size: 18px;
	    	padding-top: 32px;
	    	background-color: #d7ecfa;
	    	/* box-shadow:3px 3px 3px #c7c1c1; */
		}
		
		.learn-info{
			float:right;
			
		}
		
		.padding-right{
			padding-right:20px;
		}
		
		.path_course{
			margin-top: 20px;
			margin-bottom:60px;
	    	background-color: #d7ecfa;
	    	/* box-shadow:3px 3px 3px #b4c5b9; */
		}
		
		.path-title-box {
		    margin: 10px 0 50px;
		    font-size: 20px;
		    color: #787878;
		}
		.text-center {
		    text-align: center;
		}
		
		.path-title-box .line {
		    width: 8%;
		    display: inline-block;
		    border-top: 2px solid #58ad43;
		    margin-bottom: 3px;
		}
		
		
		/*以下样式与route.jsp 相同*/
		.block{
			display: block;
		}
		
		.boder-none{
			border:none;
		}
		
		.margin-top{
			margin-top:20px;
		}
		
		.learn-path-name{
			padding: 30px 0 15px;
		    color: #666;
		    font-size: 16px;
		}
		
		.learn-path-courses{
			padding: 0 0 30px;
		    color: #a4a4a4;
		    font-size: 14px;
		}
		
		.text-center{
			text-align: center;
		}
		
		a:link,a:visited{
 			text-decoration:none;  /*超链接无下划线*/
 		}
 		
 		.ahover > a:hover {
 			transform: scale(1.1);
            -webkit-transform: scale(1.1);
            -moz-transform: scale(1.1);
            -o-transform: scale(1.1);
            -ms-transform: scale(1.1);
		}
		
		.cd-timeline-content {
			box-shadow:3px 3px 3px #b4c5b9;
		}
	</style>
	
</head>
<body>
	<div class="layui-layout-admin">
		<%@include file="head.jsp" %>
		<div class="container layout-noside">
		
			<!-- 顶部图片 -->
			<div class="col-md-12 path-desc-top clearfix" style="background: url('${pageContext.request.contextPath }/resource/images/routedetail.png') no-repeat;background-size: cover;">
			</div>
			
			<!-- 路径描述 -->
			<div class="col-md-12 path-desc">
	            <div class="learn-info">
	            	
	                <span class="glyphicon glyphicon-user "> </span>
	                <span id="totleUser" class="padding-right">学习人数${userCount}</span>
	                <span class="glyphicon glyphicon-film "> </span>
	                <span class="padding-right padding-right">课程 ${pageInfo.list.size()}</span>
	                <span class="glyphicon glyphicon-film glyphicon-time"> </span>
	                <span class="padding-right">预计学习${totleTime} 小时</span>
	            </div>
            </div>
            
            <!-- 路径课程信息 -->
            <div class="col-md-12 path_course">
            	<div class="">
            		<!-- 这里加上 路径详情四个字 -->
            		
            	</div>
            	<!-- 时间轴 -->
            	<div id="cd-timeline" class="cd-container">
					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-picture">
							<img src="${pageContext.request.contextPath }/resource/images/cd-icon-location.svg">
						</div>
				
						<div class="cd-timeline-content">
							<div class="clearfix text-center path-title-box">
	                            <span class="line hidden-xs"></span>
	                            <span>入 门</span>
	                            <span class="line hidden-xs"></span>
                        	</div>
                        	
                        	<div class="layui-row layui-col-space20 boder-none margin-top">
                        		<c:forEach items="${pageInfo.list}" var="course">
	                        		<c:if test="${ course.courseTypeLevel=='入门'}">
										<div class="layui-col-md3 ahover">
											<a href="coursedetail?courseId=${course.courseId }" class="thumbnail ahover"> 
												<img class="img-responsive" src="${course.coursePicFileId}" />
												<span class="learn-path-name block text-center" style="height: 97px">${course.courseName }</span>
												<span class="learn-path-courses block text-center glyphicon glyphicon-user">学习人数:${course.useCount }</span>
											</a>
										</div>
									</c:if> 
                        		</c:forEach>
							</div>
						</div>
					</div>
					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-picture">
							<img src="${pageContext.request.contextPath }/resource/images/cd-icon-location.svg">
						</div>
				
						<div class="cd-timeline-content">
							<div class="clearfix text-center path-title-box">
	                            <span class="line hidden-xs"></span>
	                            <span>初 级</span>
	                            <span class="line hidden-xs"></span>
                        	</div>
							<div class="layui-row layui-col-space20 boder-none margin-top">
								<c:forEach items="${pageInfo.list}" var="course">
	                        		<c:if test="${ course.courseTypeLevel=='初级'}">
										<div class="layui-col-md3 ahover">
											<a href="coursedetail?courseId=${course.courseId }" class="thumbnail ahover"> 
												<img class="img-responsive" src="${course.coursePicFileId}" />
												<span class="learn-path-name block text-center" style="height: 97px">${course.courseName }</span>
												<span class="learn-path-courses block text-center glyphicon glyphicon-user">学习人数:${course.useCount }</span>
											</a>
										</div>
									</c:if> 
                        		</c:forEach>
							</div>
							
							
						</div>
					</div>
					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-picture">
							<img src="${pageContext.request.contextPath }/resource/images/cd-icon-location.svg">
						</div>
				
						<div class="cd-timeline-content">
							<div class="clearfix text-center path-title-box">
	                            <span class="line hidden-xs"></span>
	                            <span>中 级</span>
	                            <span class="line hidden-xs"></span>
                        	</div>
							<div class="layui-row layui-col-space20 boder-none margin-top">
								<c:forEach items="${pageInfo.list}" var="course">
	                        		<c:if test="${ course.courseTypeLevel=='中级'}">
										<div class="layui-col-md3 ahover">
											<a href="coursedetail?courseId=${course.courseId }" class="thumbnail ahover"> 
												<img class="img-responsive" src="${course.coursePicFileId}" />
												<span class="learn-path-name block text-center" style="height: 97px">${course.courseName }</span>
												<span class="learn-path-courses block text-center glyphicon glyphicon-user">学习人数:${course.useCount }</span>
											</a>
										</div>
									</c:if> 
                        		</c:forEach>
							</div>
						</div>
					</div>
					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-picture">
							<img src="${pageContext.request.contextPath }/resource/images/cd-icon-location.svg">
						</div>
				
						<div class="cd-timeline-content">
							<div class="clearfix text-center path-title-box">
	                            <span class="line hidden-xs"></span>
	                            <span>高 级</span>
	                            <span class="line hidden-xs"></span>
                        	</div>
							<div class="layui-row layui-col-space20 boder-none margin-top">
								<c:forEach items="${pageInfo.list}" var="course">
	                        		<c:if test="${ course.courseTypeLevel=='高级'}">
										<div class="layui-col-md3 ahover">
											<a href="coursedetail?courseId=${course.courseId }" class="thumbnail ahover"> 
												<img class="img-responsive" src="${course.coursePicFileId}" />
												<span class="learn-path-name block text-center" style="height: 97px">${course.courseName }</span>
												<span class="learn-path-courses block text-center glyphicon glyphicon-user">学习人数:${course.useCount }</span>
											</a>
										</div>
									</c:if> 
                        		</c:forEach>
							</div>
							
						</div>
					</div>
					<div class="cd-timeline-block">
						<div class="cd-timeline-img cd-movie">
							<img src="${pageContext.request.contextPath }/resource/images/cd-icon-location.svg">
						</div>
				
						<div class="cd-timeline-content">
							<div class="clearfix text-center path-title-box">
	                            <span class="line hidden-xs"></span>
	                            <span>项目实战</span>
	                            <span class="line hidden-xs"></span>
                        	</div>
							<div class="layui-row layui-col-space20 boder-none margin-top">
								<c:forEach items="${pageInfo.list}" var="course">
	                        		<c:if test="${ course.courseTypeLevel=='实践'}">
										<div class="layui-col-md3 ahover">
											<a href="coursedetail?courseId=${course.courseId }" class="thumbnail ahover"> 
												<img class="img-responsive" src="${course.coursePicFileId}" />
												<span class="learn-path-name block text-center" style="height: 97px">${course.courseName }</span>
												<span class="learn-path-courses block text-center glyphicon glyphicon-user">学习人数:${course.useCount }</span>
											</a>
										</div>
									</c:if> 
                        		</c:forEach>
							</div>
							
						</div>
					</div>
				</div>
            </div>
		</div>
		<%@include file="foot.jsp" %>
	</div>

</body>
</html>