<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
<title>课程</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/layui/css/layui.css" media="all">

<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/coursestyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/resource/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/js/httpPost.js"></script>

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
		<br>

		<div class="layui-container " style="margin-top:60px">
			<div class="panel panel-default">
				<div style="height:55px;background-color:#f5f5f5;border-bottom:1px solid transparent;border-color: #ddd;">
					<span style="font-size:18px;line-height:55px;margin-left:30px">课程</span>
					<div class="input-group" style="width:300px;float:right;margin:10px 30px 0 0;">
            			<input type="text" class="form-control" name="courseName" value="${courseName }">
            			<span onclick="javaScript:search()" class="input-group-addon btn" style="background-color:#fff">查找</span>
        			</div>					
				</div>
				<div class="layui-row" style="margin-top:15px">
					<div class="row course-cates">
						<div style="float: left;margin-left: 42px;">方向：</div>
						<div class="col-md-11 course-cates-content">
							<a class="" href="courselist" >全部</a>
							<c:forEach items="${courseTypeList }" var="type">
								<a class=""  href="courselist?itemId=${type.itemId }" >${type.itemName }</a>
							</c:forEach>

						</div>
					</div>
				</div>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
					
						<div class="layui-row layui-col-space10" style="min-height: 600px">
							<c:forEach items="${pageInfo.list }" var="course">
								<div class="col-md-3 col-sm-6 ahover">
									<a href="coursedetail?courseId=${course.courseId}" class="thumbnail"  style="text-decoration:none"> 
									<img class="img-responsive" alt="" style="width: 242px; height: 137px" src="${course.coursePicFileId}" />
							 		<span style="display: inline-block;font-size: 14px;color: #131313;margin: 10px 0 10px 6px;height: 30px">${course.courseName} </span>
						  			<br><span class="glyphicon glyphicon-user" style="margin-left:6px;color:#8d8b8b"> </span>
									<span style="display: inline-block;font-size:12px;color:#8d8b8b;margin-bottom:12px">已学习人数:${course.useCount}</span></a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
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
				    	var courseName =  $(":input[name='courseName']").val();
				    	if('${itemId }'!=''){
				    		window.location.href="courselist?itemId="+'${itemId }'+"&currentPage="+obj.curr;
				    	}if(courseName!=''){
				    		var params = { //post 提交参数
							        "courseName": courseName,
							        "currentPage": obj.curr
							    };
							httpPost("courselist", params);
				    	}
				    	else{
				    		httpPost("courselist", {"currentPage": obj.curr});
				    	}
				    }
				}
			});
		});
		
		
		
		//查找课程
		function search(){
			var courseName =  $(":input[name='courseName']").val();
			var params = { //post 提交参数
			        "courseName": courseName
			    };
			httpPost("courselist", params);
		}
 		
		
 		$(function(){
			//初始化
			$('#course').addClass("layui-this");
			if('${itemId }'!=''){
				$("a[href='courselist?itemId=${itemId }']").addClass("active");
				$("a[href='courselist?itemId=${itemId }']").siblings().removeClass("active");
			}else{
				$("a[href='courselist']").addClass("active");
			}
		})
	</script>

</body>
</html>