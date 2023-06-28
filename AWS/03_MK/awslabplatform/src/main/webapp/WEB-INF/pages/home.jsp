<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
<title>首页</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/resource/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<style>
	.ahover a:hover{
		transform: scale(1.1);
        -webkit-transform: scale(1.1);
        -moz-transform: scale(1.1);
        -o-transform: scale(1.1);
        -ms-transform: scale(1.1);
	} 
</style>

</head>
<body>
<div class="layui-layout layui-layout-admin">
<%@include file="head.jsp" %>

<!-- 轮播图片 -->
<div class="clearfix" style="margin-top:60px">
			<div class="ccolumn">
				<div class="carousel slide" id="carousel-429243">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0"
							data-target="#carousel-429243"></li>
						<li data-slide-to="1" data-target="#carousel-429243"></li>
						<li data-slide-to="2" data-target="#carousel-429243"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img alt="" src="${pageContext.request.contextPath }/resource/images/1.png" style="width:100%"/>
							<div class="carousel-caption">
<!-- 								<h4 ></h4> -->
<!-- 								<p>农夫三拳，有点痛！</p> -->
							</div>
						</div>
						<div class="item">
							<img alt="" src="${pageContext.request.contextPath }/resource/images/2.png" style="width:100%"/>
							<div class="carousel-caption">
<!-- 								<h4>Second Thumbnail label</h4> -->
<!-- 								<p>Cras justo odio, dapibus ac facilisis in, egestas eget -->
<!-- 									quam. Donec id elit non mi porta gravida at eget metus. Nullam -->
<!-- 									id dolor id nibh ultricies vehicula ut id elit.</p> -->
							</div>
						</div>
						<div class="item">
							<img alt="" src="${pageContext.request.contextPath }/resource/images/3.png" style="width:100%"/>
							<div class="carousel-caption">
<!-- 								<h4>Third Thumbnail label</h4> -->
<!-- 								<p>Cras justo odio, dapibus ac facilisis in, egestas eget -->
<!-- 									quam. Donec id elit non mi porta gravida at eget metus. Nullam -->
<!-- 									id dolor id nibh ultricies vehicula ut id elit.</p> -->
							</div>
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-429243"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-429243"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		</div>
		
		<div class="container">

			<!-- <div class="panel"> -->
			<div>
				<div class="col-md-12 column" style="height: 65px;padding-top: 10px">
					<a href="home"  style="text-decoration:none"><span id="hot" style="font-size: 28px">热门课程</span></a> | <a href="home?orderBy=updateTime"  style="text-decoration:none"><span id="new" style="font-size: 18px">最新课程</span></a>
				</div>
				<br>
				<c:forEach items="${pageInfo.list }" var="course">
					<div class="col-md-3 col-sm-6 ahover" >
						<a href="coursedetail?courseId=${course.courseId}" class="thumbnail"  style="text-decoration:none"> 
						<img class="img-responsive" alt="" style="width: 242px; height: 137px" src="${course.coursePicFileId}" />
						 <span style="display: inline-block;font-size: 14px;color: #131313;margin: 10px 0 10px 6px;height: 30px">${course.courseName}</span>
					  	<br><span class="glyphicon glyphicon-user" style="margin-left:6px;color:#8d8b8b"> </span>
						<span style="display: inline-block;font-size:12px;color:#8d8b8b;margin-bottom:12px">已学习人数:${course.useCount}</span></a>
					</div>
				</c:forEach>			
			</div>
		</div>
	</div>
	<div id="pagebar" align="center" style="margin-bottom: 200px"></div>

	<%@include file="foot.jsp" %>
	<!-- 	实现分页script -->
	<script>
		layui.use('laypage', function() {
			var laypage = layui.laypage;

			//执行一个laypage实例
			laypage.render({
				elem : 'pagebar' ,//注意，这里的 test1 是 ID，不用加 # 号
				curr: '${pageInfo.pageNum}',
				count : '${pageInfo.total}',//数据总数，从服务端得到
				limit : '${pageInfo.pageSize}',
				jump: function(obj, first){
				    //obj包含了当前分页的所有参数，比如：
				    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
				    console.log(obj.limit); //得到每页显示的条数
				    //首次不执行
				    if(!first){
				    	if('${orderBy }'!=''){
				    		window.location.href="home?orderBy="+'${orderBy }'+"&currentPage="+obj.curr;
				    	}else{
				    		window.location.href="home?currentPage="+obj.curr;
				    	}
				    }
				}
			});
		});
	$(function(){
		$('#home').addClass("layui-this");
		//初始化
		if('${orderBy }'!=''){//热门课程|最新课程样式切换
			$("#new").attr("style","font-size: 28px")
			$("#hot").attr("style","font-size: 18px")
		}
	})
	</script>
</body>
</html>