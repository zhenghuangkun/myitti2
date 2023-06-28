<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
<title>课程</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/layui/css/layui.css" media="all">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/plugins/raty/jquery.raty.css" />
<script src="${pageContext.request.contextPath }/resource/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/plugins/layer/layer.js" ></script>
<script src="${pageContext.request.contextPath}/resource/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/resource/plugins/raty/jquery.raty.js"></script>
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resource/css/starability-all.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resource/css/coursedetail.css"/>

<style>
.btn-primary{
margin-left: 93px;
}
/* .coursedetails{
	white-space: nowrap;
	word-break: break-all;
	min-width:1120px;
}
.courseIntroduction{
	min-width:560px;
	white-space: normal;
}
.content{
	min-width:1150px;
	white-space: nowrap;
} */
</style>
</head>
<body style="background-color: #F0F0F0;height: 100%;position: relative;">
	<div class="layui-layout layui-layout-admin">
		<%@include file="head.jsp" %>
	</div>
	<br>

	<div class="layui-container content" style="margin-top:60px">
		<div class="layui-row layui-col-space10" style="background-color: white; padding: 15px;">
			<div class="layui-col-md12">
				<div class="layui-row layui-col-space10 coursedetails">
				
					<div class="layui-col-md5" style="margin-right:15px;width:420px">
						<img src="${course.coursePicFileId}" class="img-responsive"
						alt="Cinque Terre" style="width:410px;height:230px">
					</div>
					
					<div class="layui-col-md6 courseIntroduction">
						<div class="layui-row" style="height: 40px">
							<p style="font-size:24px;color:#131313">${course.courseName}</p>
						</div>
						<div class="layui-row" style="height: 28px">
							<div class="layui-col-md3" style="margin: center">
								<div class='raty' data-length="${course.avgScore}"></div>
							</div>
							<div class="layui-col-md1">
								<span style="color: rgb(255, 155, 60);">${course.avgScore}分</span>
							</div>
							<div class="layui-col-md4">
								<span style="color:#8d8b8b">（${course.evaluateAmount}人评论）</span>
							</div>
						</div>
						<div class="layui-row">
							<div class="layui-col-md6" style="color:#8d8b8b"><span class="glyphicon glyphicon-user"></span>${course.useCount} 人学过</div>
						</div>
						<hr style="margin-top: 9px;margin-bottom: 9px;border: 0;border-top: 1px solid #d2d2d2;">
						<div class="layui-row" style="font-size:14px;color:#2a2929;width: 100%;word-break:break-all; ">${course.description}</div>
					</div>
						
					<div class="layui-col-md1" style="width:110px;height:240px;margin-left:15px;">
						<div class="panel panel-default" style="width:100px;height:230px">
							<div class="panel-heading" style="font-size: 12px;"><span class="glyphicon glyphicon-user"></span> 授课教师</div>
							<div style="padding:12px">
								<img src="${pageContext.request.contextPath }/resource/images/teacher.jpg" class="img-circle" style="width: 75px;">
							</div>
							<span style="font-size:18px;display: inline-block;width: 100px;text-align: center;margin-bottom: 10px;">${teacher.realName }</span>
							<span style="font-size:12px;display: inline-block;width: 100px;text-align: center;">${teacher.departmentId }</span>
						</div>
					</div>
					
				</div>	
			</div>
		</div>
		<br>
		<div class="panel panel-warning">
			<div class="panel-heading">
				<h3 class="panel-title">实验列表</h3>
			</div>
			
			
			<div class="panel-body">
			
			<c:forEach items="${experimentList}" var="exp">
				<div class="panel panel-success">
					<div class="panel-heading">
						<div class="layui-row">
							<div class="layui-col-md10">
								<h3 class="panel-title">${exp.experimentName }</h3>
							</div>
							<div class="layui-col-md2">
<!-- 								<button class="btn btn-default">查看文档</button> -->
								<a class="btn btn-primary" href="${pageContext.request.contextPath }/running?experimentId=${exp.experimentId }">
									${sessionScope.experimentId==exp.experimentId?"继续实验":"开始实验"}</a>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<span style="">${exp.description }</span>
						<span style="font-size:12px;display:block;float:right">实验时长：${exp.runtime}分钟</span>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>

		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">课程评论（${course.evaluateAmount}）</h3>
			</div>
			<div class="panel-body">

				<form action="addComment" method="post" onsubmit="return check()">
					<input type="hidden" id="courseId" name="courseId" value="${course.courseId}">
					<div class="layui-row layui-col-space20">
						<div class="layui-col-md10">
							<textarea id="content" name="content" class="form-control" rows="3" style="resize: none"></textarea>
						</div>
						<div class="layui-col-md2">
							<div class="layui-row " style="height: 40px">
								<div class="starability-container">
									<fieldset class="starability-growRotate">
										<input type="radio" id="rate5-5" name="rating" value="5" checked="checked" /> 
										<label for="rate5-5" title="非常好">5 stars</label>
										<input type="radio" id="rate4-5" name="rating" value="4" /> 
										<label for="rate4-5" title="很好">4 stars</label> 
										<input type="radio" id="rate3-5" name="rating" value="3" /> 
										<label for="rate3-5" title="一般">3 stars</label> 
										<input type="radio" id="rate2-5" name="rating" value="2" /> 
										<label for="rate2-5" title="不好">2 stars</label> 
										<input type="radio" id="rate1-5" name="rating" value="1" />
										<label for="rate1-5" title="差劲">1 star</label>
									</fieldset>
								</div>
							</div>
							<div class="layui-row" style="height: 50px;padding-top: 15px" >
								<button class="btn btn-info" >发表评论</button>
								
							</div>
						</div>
					</div>
				</form>
			</div>
			<div style="padding-left: 15px;padding-right: 15px;">
				<div style="padding-bottom: 10px; margin-bottom: 20px; border-bottom: 1px solid #d2d2d2;font-size:16px">最新评论</div>
				<div class="streamLoad">
				
			</div>
		</div>
	</div>
	</div>
	
	<%@include file="foot.jsp" %>
	<script type="text/javascript">	
	//页面初始化
	$(function () {
		initRaty();
		initStreamLoad();
		if('${message}'!=''){
			layer.alert('${message}')
		}
	});
	/**
 * 初始化星级评分插件
 */
function initRaty(){
	// 找到raty的div
	$("div.raty").each(function() {
	    var $this = $(this);
	
	    // 获取初始化值
	    //var score = $this.text();
	    var score = $this.attr("data-length");

	    // 初始化raty
	    $this.raty({
	        // 设置值
	        score : score,
	        //noRatedMsg : "",
	        half: true,
	        // 由Raty生成的隐藏字段的名称。
	        readOnly : true
	    });
	    
	});
}

/**
 *初始化流加载 
 */
function initStreamLoad()
{
	layui.use('flow', function(){
		  var flow = layui.flow;
		 
		  flow.load({
		    elem: '.streamLoad' //流加载容器
		    //,scrollElem: '#LAY_demo1' //滚动条所在元素，一般不用填，此处只是演示需要。
		    ,done: function(page, next){ //执行下一页的回调
		    	var lis = [];
		    	
		    	$.ajax({
					url: 'getComments',
					dataType:'json',
					type:'post',
					data:{
						//type:pe,//类型0 1 2 3 4
						page: page,
						pageSize: 5,
						courseId:$("#courseId").val()
					},
					success:function(res){
						layui.each(res.data.list, function(index, item){
							var studentPic = "${pageContext.request.contextPath }/resource/images/head.jpg";
							//var studentName = "匿名用户";
							var studentName = item.student.realName;
							if(item.student.picFileUrl != null && item.student.picFileUrl != ""){
								studentPic = item.student.picFileUrl;
								//studentName = item.student.realName;
							}  
						      lis.push("\
						    		  <div class='layui-row' style='padding-bottom: 10px; margin-bottom: 20px; border-bottom: 1px solid #d9dde1;'>\
										<div class='layui-col-md1' style='margin-top: 5px;'>\
											<img class='studentPic' alt='匿名用户' src='"+studentPic+"'>\
										</div>\
										<div class='layui-row layui-col-md10' style='height: 100%'>\
											<label style='color:#4d555d;'>"+studentName+"</label>\
											<div class='bigger-120' style='display: inline-block;padding-left:15px;padding-top:-1px;'>\
												<div class='raty' data-length='"+item.score+"'></div>\
											</div>\
											<div style='word-wrap: break-word; margin-top:5px;'>\
												<p>\
													"+item.content+"\
												</p>\
											</div>\
											<div style='color: gray;margin-top:20px;'>"+item.commentTime+"</div>\
										</div>\
									</div>");
						});
						//initRaty();
						next(lis.join(''), page < (res.data.pages) ); //总页数
						initRaty();
					}
		    	});
		    	//initRaty();
		    }	
		  
		  });
	});
}


//检查评论内容 
function check(){
	if($("#content").val().trim()==""){
		layer.msg("您忘记输入评论内容了哦。");
	return false;
	}else if($("#content").val().trim().length>1000){
		layer.msg("您的评论太长了哦！请在1000字以内。");
		return false;
	}
	else{
		return true;
	}
}
</script>
	<script type="text/javascript">
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/plugins/raty/images';//设置raty评分星星的全局图片路径
	</script>
	
</body>
</html>