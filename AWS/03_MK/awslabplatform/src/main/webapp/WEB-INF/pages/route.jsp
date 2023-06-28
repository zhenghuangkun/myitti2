<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
	<title>路径</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/coursestyle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath }/resource/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	
	
	<style type="text/css">
		.learn-path-banner {
		    height: 340px;
		    background: url(${pageContext.request.contextPath }/resource/images/1.png) no-repeat; background-size:100% 100%;overfolw:hidden;
		    margin-top:60px;
    	}
    	.layout-noside {
		    min-height: 100%;
		    margin-top: 30px;
		    margin-bottom: 60px;
		}
		.boder-none{
			border:none;
		}
		
		.margin-top{
			margin-top:20px;
		}
		.block{
			display: block;
		}
		
		.learn-path-name{
			padding: 30px 0 15px;
		    color: #666;
		    font-size: 18px;
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
		
		.layui-container{
			height:100%;
			width:70%;
		}


		@media(min-width:992px){

			.courseimg{
				height: 150px !important ;
				width: 100%;
			}

		}
	
	</style>
	
</head>
<body>
	<div class="layui-layout-admin">
		<%@include file="head.jsp" %>
		<div class="learn-path-banner">
		</div>

		<div class="min_length layui-container layout-noside">
			<div class="panel">
				<div class="layui-row layui-col-space20 boder-none margin-top">
					<c:forEach items="${courseTypeList}" var="courseType">
						<div class="layui-col-md3 ahover">
							<a href="routedetail?itemId=${courseType.itemId }" class="thumbnail ahover"> 
								<img class="courseimg img-responsive" src="${courseType.typePic }" />
								<span class="learn-path-name block text-center">${courseType.itemName }</span>
								<span class="learn-path-courses block text-center">${courseType.count}门课程</span>
							
							</a>
						</div>
					</c:forEach>
				</div>
				

				
			</div>
		</div>
		<%@include file="foot.jsp" %>
	</div>
	
	
	<script>
		console.log($(".courseimg"))

		$(function(){
			$('#route').addClass("layui-this");
		})
		
		layui.use('laypage', function() {
			var laypage = layui.laypage;

			//执行一个laypage实例
			laypage.render({
				elem : 'pagebar' //注意，这里的 test1 是 ID，不用加 # 号
				,
				count : 500,
				limit : 20
			//数据总数，从服务端得到
			});
		});
	</script>
</body>
</html>