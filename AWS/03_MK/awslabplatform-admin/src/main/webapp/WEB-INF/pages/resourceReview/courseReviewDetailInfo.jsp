<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/pages/common/head.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/resourceReview/courseReviewInfoStyle.css"/>


</head>
<body class="skin-1" style="background-color: #F0F0F0;">
	
	<!-- 会发生错误，MessageContrller 的getMessage 函数出现错误。 -->
	<%@ include file="/WEB-INF/pages/common/header.jsp"%>

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

		<%@ include file="/WEB-INF/pages/common/sidebar.jsp"%>
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <span>资源审核</span>
						</li>
						<li class="active">课程审核</li>
						<li class="active">课程详细信息</li>
					</ul>
				</div>
				
				<div class="layui-container">
					<br/>
					<br/>
					<input type="hidden" name="courseId" id="courseId" value="${course.courseId}"/>
					<div class="panel panel-info">
						<!-- 标题 -->
						<div class="panel-heading">
							<h3 class="panel-title">课程基本信息</h3>
						</div>
						
						<!-- 内容 -->
						<div class="panel-body">
							
							<div class="layui-col-md9">
								
								<table class="layui-table mytable" lay-skin="nob">
								  	  <!-- 列设置 -->
								  	  <colgroup>
									    <col style="background:whitesmoke;" width="15%">
									    <col width="30%">
									    <col style="background:whitesmoke;" align="center" width="15%">
									    <col width="30%">
									  </colgroup>
									  
									  <!-- 内容 -->
									  <tbody>
									  	<!-- 第一行 -->
									  	<tr>
									  		<td align="right">课程名</td>
									  		<td><h4>${course.courseName}</h4></td>
									  		<td align="right">院系</td>
									  		<td>${college.mechanismName}</td>
									  	</tr>
									  	<!-- 第二行 -->
									  	<tr>
									  		<td align="right">专业</td>
									  		<td>${specialty.mechanismName}</td>
									  		<td align="right">类型</td>
									  		<td>${courseTypeName}&nbsp;&nbsp;${courseTypeLevelName}</td>
									  	</tr>
									  	<!-- 第三行 -->
									  	<tr>
									  		<%-- <td align="right">课程属性</td>
									  		<td>${course.commentAllowable eq true ? '允许评价' : '不允许评价'}</td> --%>
									  		<td align="right">共享范围</td>
									  		<td>
									  			${course.openRange eq 0 ? '学生' : course.openRange eq 1 ? '学院' : '学校'}&nbsp;&nbsp;&nbsp;
									  			<c:choose>
									  				<c:when test="${course.openRange==0}">
									  					<a href="javascript:void(0)" onclick="showExperimentGroupStudent('${course.courseId}')">共&nbsp;${courseNumber}个学生</a>
									  				</c:when>
									  				
									  				<c:otherwise>
									  					共&nbsp;${courseNumber}个学生
									  				</c:otherwise>
									  			</c:choose>
									  		</td>
									  	</tr>
									  	<!-- 第四行 -->
									  	<tr>
									  		<td align="right">课程启动类型</td>
									  		<td>${course.courseStartUpType eq 1 ? '自动发布' : '手动发布'}</td>
									  		<td align="right">课程启动日期</td>
									  		<td>${course.courseStartUpType eq 1 ?  course.courseStratTime: ''}</td>
									  	</tr>
									  	<!-- 第五行 -->
									  	<tr>
									  		<td align="right">课程简介</td>
									  		<td colspan="3">
									  		${course.description}
									  		</td>
									  	</tr>
									  	
									  	<!-- 第六行 -->
									  	<tr>
									  		<td align="right">课程图标</td>
									  		<td colspan="3">
									  			<div class="layui-col-md4">
													<img src="${coursePicFile}" class="img-responsive" alt="课程图标">
												</div>
									  		</td>
									  	</tr>
									  </tbody>
								</table>
							</div>
							
							<div class="layui-col-md3">
								<br/>
								<div class="panel panel-default">
									<div class="panel-heading" style="font-size: 16px;"><span class="glyphicon glyphicon-user"></span> 授课教师</div>
									<div class="panel-body">
										<div class="layui-row">
											<div class="layui-col-md6">
												<img src="${pageContext.request.contextPath}/resource/images/courseManage/head4.JPG" class="img-circle" style="width: 100px;"/>
											</div>
											<div class="layui-col-md6">
												<br/>指导教师:<br/>
												<strong>${Teacher.realName}<br>${Teacher.phoneNum}</strong><br/>
												
											</div>
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
					</div>
					
					<div class="panel panel-warning">
						<div class="panel-heading">
							<h3 class="panel-title">实验列表</h3>
						</div>
						<div class="panel-body">
							<c:forEach items="${Experiment}" var="experiment">
								<div class="panel panel-success">
									<div class="panel-heading">
										<div class="layui-row">
											<div class="layui-col-md8">
												<h3 class="panel-title">
													实验${experiment.experimentNo} &nbsp;&nbsp;&nbsp;&nbsp;${experiment.experimentName}
												</h3>
												
											</div>
											<div class="layui-col-md4">
												<button class="layui-btn layui-btn-primary" onclick="viewExperimentGuideInfo(true, '${experiment.guideUrl}')">实验指南</button>
												<button class="layui-btn layui-btn-primary" onclick="openTemplateDetailInfo('${experiment.templateId}')">查看模板</button>
											</div>
										</div>
									</div>
									<div class="panel-body">
										<table class="layui-table experimentTable" lay-skin="nob">
											<colgroup>
											    <col width="10%">
											    <col width="40%">
											    <col width="10%">
											    <col width="40%">
											</colgroup>
											
											<tbody>
												<tr>
													<td align="right"><strong>模板</strong></td>
													<td style="word-break:break-all;">${experiment.templetName}</td>
													<td align="right"><strong>时长</strong></td>
													<td>${experiment.runtime}分</td>
													
												</tr>
												<tr>
													<td align="right"><strong>开始时间</strong></td>
													<td style="word-break:break-all;"><i class="fa fa-clock-o light-orange bigger-110"></i>&nbsp;${experiment.startTime}</td>
													<td align="right"><strong>结束时间</strong></td>
													<td><i class="fa fa-clock-o light-orange bigger-110"></i>&nbsp;${experiment.endTime}</td>
												</tr>
												<tr>
													<td align="right"><strong>策略</strong></td>
													<c:choose>
														<c:when test="${!empty experiment.policyName}">
															<td style="word-break:break-all;">${experiment.policyName}</td>
														</c:when>
														<c:otherwise>
															<td style="word-break:break-all;">未选择</td>
														</c:otherwise>
													</c:choose>
													<td align="right"><strong>区域</strong></td>
													<td>${experiment.experimentRegionName}</td>
												</tr>
												<tr>
													<td align="right"><strong>描述</strong></td>
													<td colspan="3" style="word-break:break-all;">
														实验描述: ${experiment.experimentDescription}
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</c:forEach>
						
						</div>
					</div>
			
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">课程审核</h3>
						</div>
						<div class="panel-body">
			
							<form role="form" method="post" id="courseDetailCheckInfoForm">
								<div class="layui-row layui-col-space20">
									<div class="layui-col-md1">
										<label class="no-padding-right">不同意理由:</label>
									</div>
									<div class="layui-col-md10">
										<textarea class="form-control" id="description" name="description" onkeydown="displayWordCound(this,500)" onkeyup="displayWordCound(this, 500)" onfocus="displayWordCound(this, 500)" rows="3" placeholder="请输入不同意理由..." style="resize: none; width:100%;" maxlength="500"></textarea>
										
									</div>
									<br/><br/><br/><br/><span id="description-total" style="margin-left: -13px;">0</span>/500
								</div>
								
								<div style="float:right;">
									<br/>
									<button class="btn btn-info" type="button" onclick="courseDetailReviewOk()">
									<i class="ace-icon fa fa-check"></i>
									同意</button>
									
									<button class="btn btn-info" type="button" onclick="courseDetailReviewNg()">
									<i class="ace-icon fa fa-times red2"></i>
									不同意</button>
								</div>
										
							</form>
						</div>
					</div>

				</div><!-- /.layui-container -->
				
			</div><!-- /.main-content-inner -->	
		</div><!-- /.main-content -->
		<%@ include file="/WEB-INF/pages/common/footer.jsp"%>	
	</div><!-- /.main-container -->		
	
	<div class="lightbox" id="lightbox"></div>
	<div id="pop" class="pop"
		style="display: none; position: absolute; left: 60%; top: 50px; width: 893px; height: 100%; margin-left: -446.5px; z-index: 9;">
		<a href="javascript:close()"
			style="position: absolute; right: -90px; display: inline-block; width: 80px; height: 30px;"
			id="close">关闭</a>
		<iframe src="" frameborder="0" id="pdfContainer"
			name="pdfContainer"
			style="position: absolute; left: 50%; top: 0; width: 893px; height: 100%; margin-left: -446.5px; z-index: 9;"></iframe>
	</div>
	
	
	<%-- <script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script> --%>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.js"></script>
	
	<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/resourceReview/course_detail_check.js"></script>
	
</body>
</html>