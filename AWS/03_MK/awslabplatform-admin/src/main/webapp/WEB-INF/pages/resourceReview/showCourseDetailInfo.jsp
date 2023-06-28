<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="timer" class="java.util.Date"/>

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
									  		<td align="right">已学习人数</td>
									  		<td>${course.useCount}人</td>
									  		
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
									  		<td align="right">评论数</td>
									  		<td>${course.evaluateAmount}条</td>
									  		<td align="right">综合评分</td>
									  		<td>
									  			<p style="color:#ff9000;font-size: 48px;margin-left: 3px;margin-top: 12px;margin-bottom: 10px;">${course.avgScore}</p>
									  			<div class='bigger-120' style='display: inline-block;padding-left:1px;padding-top:1px;'>
													<div class='courseRaty' data-length='${course.avgScore}'></div>
												</div>
											</td>
									  	</tr>
									  	<!-- 第六行 -->
									  	<tr>
									  		<td align="right">课程简介</td>
									  		<td colspan="3">
									  		${course.description}
									  		</td>
									  	</tr>
									  	
									  	<!-- 第七行 -->
									  	<tr>
									  		<td align="right">课程图标</td>
									  		<td colspan="3">
									  			<div class="layui-col-md4">
													<img src="${coursePicFile}" class="img-responsive" alt="课程图标">
												</div>
									  		</td>
									  	</tr>
									  	
									  	<!-- 第八行 -->
									  	<tr>
									  		<td align="right">课程审核结果</td>
									  		<td colspan="3">
									  			<div>
									  				<br/>
									  				<c:choose>
									  					<c:when test="${course.status==5}"><strong style="color: blue">已发布</strong><br/><br/>
									  						<strong>审核历史:</strong><br/>
									  						<c:forEach items="${courseCheckHistory}" var="history">
									  							<p>
									  							${history.userName}&nbsp;&nbsp;&nbsp; ${history.checkStatus eq 1 ? '通过' : '未通过'} &nbsp;&nbsp; <fmt:formatDate value="${history.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
									  							<c:if test="${history.content != ''}">
																	<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${history.content}
									  							</c:if>
									  							</p>
									  						</c:forEach>
									  					</c:when>
									  					<c:when test="${course.status==6}"><strong style="color: blue">通过</strong><br/><br/>
									  						<strong>审核历史:</strong><br/>
									  						<c:forEach items="${courseCheckHistory}" var="history">
									  							<p>
									  							${history.userName}&nbsp;&nbsp;&nbsp; ${history.checkStatus eq 1 ? '通过' : '未通过'} &nbsp;&nbsp; <fmt:formatDate value="${history.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
									  							<c:if test="${history.content != ''}">
																	<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${history.content}
									  							</c:if>
									  							</p>
									  						</c:forEach>
									  					</c:when>
									  					<c:when test="${course.status==4}">
										  					<strong style="color: red">未通过</strong><br/><br/>
									  						<strong>审核历史:</strong><br/>
									  						<c:forEach items="${courseCheckHistory}" var="history">
									  							<p>
									  							${history.userName}&nbsp;&nbsp;&nbsp; ${history.checkStatus eq 1 ? '通过' : '未通过'} &nbsp;&nbsp; <fmt:formatDate value="${history.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
									  							<c:if test="${history.content != ''}">
																	<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${history.content}
									  							</c:if>
									  							</p>
									  						</c:forEach>
									  					</c:when>
									  					<c:when test="${course.status==3}"><strong style="color: red">审核中</strong><br/><br/>
									  						<strong>审核历史:</strong><br/>
									  						<c:forEach items="${courseCheckHistory}" var="history">
									  							<p>
									  							${history.userName}&nbsp;&nbsp;&nbsp; ${history.checkStatus eq 1 ? '通过' : '未通过'} &nbsp;&nbsp; <fmt:formatDate value="${history.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
									  							<c:if test="${history.content != ''}">
																	<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${history.content}
									  							</c:if>
									  							</p>
									  						</c:forEach>
									  					</c:when>
									  					<c:when test="${course.status==2}"><strong style="color: red">未提交</strong><br/><br/></c:when>
									  					<c:when test="${course.status==1}"><strong style="color: red">终止</strong><br/><br/></c:when>
									  				</c:choose>
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
															<td  style="word-break:break-all;">${experiment.policyName}</td>
														</c:when>
														<c:otherwise>
															<td  style="word-break:break-all;">未选择</td>
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

	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.js"></script>
	
	<script src="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.js"></script>
	<script type="text/javascript">
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
	
	<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/resourceReview/show_course_detail.js"></script>
	
</body>
</html>