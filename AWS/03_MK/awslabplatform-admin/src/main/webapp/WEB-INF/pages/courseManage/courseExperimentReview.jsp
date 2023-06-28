<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="timer" class="java.util.Date"/>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/courseManage/experimentReviewStyle.css">
	

</head>

<body class="skin-1">
	<!-- 会发生错误，MessageContrller 的getMessage 函数出现错误。 -->
	<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	

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
							<span>课程管理</span>
						</li>
						<li class="active">所有课程</li>
						<li class="active">课程详细信息</li>
						<li class="active">实验报告</li>
					</ul>
				</div>

				<div class="page-content">
					<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
					<!-- <div class="page-header">
						
					</div> -->
					
					<div class="row">
						<div class="col-xs-12 col-sm-3 col-md-3" >
							<div class="col-xs-12 col-sm-12 col-md-12" id="studentInfoHeight" >
								<!-- 标题 -->
								<!-- <div class="search-filter-header bg-primary" style="margin-top:2px; padding-top: 1px;padding-bottom: -1px;">
									<h5 class="smaller no-margin-bottom">
										<i class="ace-icon fa fa-sliders light-green bigger-120"></i>
										&nbsp; 学生信息
									</h5>
								</div> -->
								 
								<div class="table-header" style="margin-top:12px;">
									
									<i class="ace-icon fa fa-sliders light-green bigger-120"></i>
									&nbsp;学生信息
								</div>
								
								<div class="space-10"></div>
								<!-- 隐藏域-课程ID -->
								<input type="hidden" id="courseId" name="courseId" value="${courseId}">
								
								<!-- 查找模块 -->
								<form>
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-12">
											<div class="input-group">
												<input type="text" class="form-control" id="studentName" name="studentName" placeholder="请输入学生信息...">
												<div class="input-group-btn">
													<button type="button" class="btn btn-default no-border btn-sm" onclick="doSearch()">
														<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
								</form>
								
								<!-- <div class="space-10"></div> -->
								<!-- 分割线 -->
								<div class="hr hr-dotted"></div>
								<!-- <div class="space-10"></div> -->
								
								<!-- 学生信息 -->
								<div>
									<div class="studentList">
									    <!-- 共享范围是学生 -->
									    <c:choose>
											<c:when test="${openRange==0}">
				                          	  <c:forEach items="${experimentGroupList}" var="group">
											  		<div class="group-menu">
					                              		<a class="group-item" href="javascript:void(0)" onclick="groupClickFunc(this)" data-group-id="${group.groupId}"><span class="glyphicon glyphicon-minus smaller-90"></span>&nbsp;${group.groupName}</a>
							                              <div class="student-item">
							                              	  <c:forEach items="${group.children}" var="student">
							                              	  		<a class="studentList-item" href="javascript:void(0)" onclick="studentExperimentInfo(this)" data-student-id="${student.id}" data-student-name="${student.realName}">
							                              	  			<c:if test="${empty student.picFileUrl}">
							                              	  				<img class="studentInfoPic" src="${pageContext.request.contextPath}/resource/images/courseManage/studentDefault2.jpg"></img>
							                              	  			</c:if>
							                              	  			<c:if test="${student.picFileUrl!=null}">
							                              	  				<img class="studentInfoPic" src="${student.picFileUrl}"></img>
							                              	  			</c:if>
							                              	  			${student.realName}
							                              	  		</a>
							                              	  </c:forEach>
						                          	  	  </div>	
					                          	  	</div>
											  </c:forEach>
			                          	    </c:when>
			                          	    
			                          	    <c:when test="${openRange==1}">
				                          	    <div class="group-menu">
				                              	  <a class="group-item" href="javascript:void(0)" onclick="groupClickFunc(this)" data-group-id="${collegeId}"><span class="glyphicon glyphicon-minus smaller-90"></span>&nbsp;${collegeName}</a>
					                              <div class="student-item">
					                              	  <c:forEach items="${students}" var="student">
					                              	  		<a class="studentList-item" href="javascript:void(0)" onclick="studentExperimentInfo(this)" data-student-id="${student.id}" data-student-name="${student.realName}">
					                              	  			<c:if test="${empty student.picFileUrl}">
					                              	  				<img class="studentInfoPic" src="${pageContext.request.contextPath}/resource/images/courseManage/studentDefault2.jpg"></img>
					                              	  			</c:if>
					                              	  			<c:if test="${student.picFileUrl!=null}">
					                              	  				<img class="studentInfoPic" src="${student.picFileUrl}"></img>
					                              	  			</c:if>
					                              	  			${student.realName}
					                              	  		</a>
					                              	  </c:forEach>
				                          	  	  </div>	
				                          	  	</div>
			                          	    </c:when>
			                          	    
			                          	    <c:when test="${openRange==2}">
				                          	  <div class="group-menu">
				                              	  <a class="group-item" href="javascript:void(0)" onclick="groupClickFunc(this)" data-group-id="${schoolId}"><span class="glyphicon glyphicon-minus smaller-90"></span>&nbsp;${schoolName}</a>
					                              <div class="student-item">
					                              	  <c:forEach items="${students}" var="student">
					                              	  		<a class="studentList-item" href="javascript:void(0)" onclick="studentExperimentInfo(this)" data-student-id="${student.id}" data-student-name="${student.realName}">
					                              	  			<c:if test="${empty student.picFileUrl}">
					                              	  				<img class="studentInfoPic" src="${pageContext.request.contextPath}/resource/images/courseManage/studentDefault2.jpg"></img>
					                              	  			</c:if>
					                              	  			<c:if test="${student.picFileUrl!=null}">
					                              	  				<img class="studentInfoPic" src="${student.picFileUrl}"></img>
					                              	  			</c:if>
					                              	  			${student.realName}
					                              	  		</a>
					                              	  </c:forEach>
				                          	  	  </div>	
				                          	  </div>
			                          	    </c:when>
		                          	    </c:choose>
		                          </div>
								</div>
							</div>
							
						</div>
						
							
						<div class="col-xs-12 col-sm-9 col-md-9" style="padding-left: 12px;">
							<div class="row" >
								
								<div class="col-xs-12 col-sm-12 col-md-12 studentExperimentInfoTitle">
									<label style="font-size: 17px" class="text-primary">
										<i class="ace-icon fa fa-leaf"></i>
										<strong id="studentExperimentInfoTitleId" data-student-id="${firstStudent.id}">${firstStudent.realName}</strong>
									</label>
								</div>
							</div>
							<!-- <div style="margin: 2px 0 2px 0;"></div> -->
							<!-- 空两行 -->
							<div class="space-2"></div>
										
							<div class="row" id="studentExperimentInfoHeight">
							
								<!-- 实验一 -->
								<c:forEach items="${courseReport}" var="report">
									<div class="col-xs-12 col-sm-12 col-md-12 studentParent" >
										<!-- box -->
										<div class="widget-box">
											<!-- 头部 -->
											<div class="widget-header">
												<h5 class="widget-title experimentTitle">实验${report.experimentNo}</h5>
												
												<!-- 展开，隐藏 -->
												<div class="widget-toolbar">
													<a href="#" data-action="collapse">
														<i class="1 ace-icon fa fa-angle-up bigger-125"></i>
													</a>
												</div>
												
												<!-- 工具栏 -->
												<div class="widget-toolbar no-border showReport">
													<div class="studentReport" style="display: none">${report.content}</div>
													<button class="btn btn-xs btn-light bigger" type="button" onclick="viewExperimentReport(this)">
														<i class="ace-icon glyphicon glyphicon-download-alt"></i>
														查看报告
													</button>
												</div>
												
											</div>
											
											<!-- 内容部分 -->
											<div class="widget-body">
												<div class="row">
													<!-- 空格8行 -->
													<div class="space-8"></div>	
														
													<div class="col-md-5">
														<div class="profile-user-info profile-user-info-striped">
														 	<div class="profile-info-row">
														 		<div class="studentExperimentId" style="display: none">${report.experimentId}</div>
																<div class="profile-info-name"> 实验名 </div>
	
																<div class="profile-info-value">
																	<span>${report.experimentName}</span>
																</div>
															</div>
			
															<div class="profile-info-row">
																<div class="profile-info-name"> 实验时长<small>(分钟)</small> </div>
	
																<div class="profile-info-value">
																	<span>${report.runtime}</span>
																</div>
															</div>
															
															<div class="profile-info-row">
																<div class="profile-info-name"> 提交时间 </div>
	
																<div class="profile-info-value">
																	<i class="fa fa-clock-o light-orange bigger-110"></i>
																	<span><fmt:formatDate value="${report.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
																</div>
															</div>
															
															
			
															<%-- <div class="profile-info-row">
																<div class="profile-info-name"> 提交时间 </div>
	
																<div class="profile-info-value">
																	<i class="fa fa-map-marker light-orange bigger-110"></i>
																	<span>${report.createTime}</span>
																</div>
															</div>
															
															<div class="profile-info-row">
																<div class="profile-info-name"> 实验用时<small>(分钟)</small> </div>
	
																<div class="profile-info-value">
																	<span>30</span>
																</div>
															</div> --%>
															
														</div>
														
														<!-- 空格8行 -->
														<div class="space-8"></div>	
													</div>
													
													<div class="col-md-7">
														<form>
															<div class="row">
																<div class="col-md-3">
																	<h5 class="blue lighter experimentComment">评价</h5>
																</div>
																
																<div class="col-md-9">
																	<c:choose>
																		<c:when test="${report.comment != ''}">
																			<textarea class="col-md-12" rows="3"  onkeydown="displayWordCound(this,500)" onkeyup="displayWordCound(this, 500)" onfocus="displayWordCound(this, 500)" readonly="readonly" placeholder="请输入实验评价…" style="width:98%;">${report.comment}</textarea>
																		</c:when>
																		<c:otherwise>
																			<textarea class="col-md-12" rows="3"  onkeydown="displayWordCound(this,500)" onkeyup="displayWordCound(this, 500)" onfocus="displayWordCound(this, 500)" placeholder="请输入实验评价…" style="width:98%;"></textarea>	
																		</c:otherwise>
																	</c:choose>
													
																	<div style="float: right;margin-top: 0px;padding-right: 18px;">
																		<span>${fn:length(report.comment)}</span>/500
																	</div>
																</div>
																
															</div>
															<!-- 空格16行 -->
															<div class="space-16"></div>		
															<div class="row">
																<div class="col-md-3">
																	<h5 class="blue lighter experimentScore">评分</h5>
																</div>
																
																<div class="col-md-5">
																	<c:choose>
																		<c:when test="${report.comment != ''}">
																			<input name="score" value="${report.score}" onkeyup="value=value.replace(/[^\d]/g,'')" readonly="readonly"  placeholder="请输入实验得分..." style="height:32px;width:80%;padding-left: 8px;" maxlength="3"/>
																		</c:when>
																		<c:otherwise>
																			<input name="score" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入实验得分..." style="height:32px;width:80%;padding-left: 8px;" maxlength="3"/>	
																		</c:otherwise>
																	</c:choose>
																	
																</div>
																
																<div class="scorePromptIcan">
																	<i></i>
																	<small>最高分数100分</small>
																</div>
															</div>
														</form>
													</div>
													
													<!-- 空格两行 -->
													<div class="space-4"></div>	
												</div>
												
												
												<!-- 尾部 -->
												<div class="widget-toolbox padding-8 clearfix">
													
													
													<c:choose>
														<c:when test="${report.comment != ''}">
															<button class="btn btn-xs btn-success pull-right submitBtnClass" type="button" disabled='disabled'  onclick="submitExperimetComment(this)">
																<span class="bigger-110">提交</span>
																<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
															</button>
															<span class="pull-right"  style="width:10px;">&nbsp;</span>
															<button class="btn btn-xs btn-info pull-right editBtnClass" type="button"  onclick="editExperimentComment(this)">
																<i class="ace-icon fa fa-pencil icon-on-left"></i>
																<span class="bigger-110">编辑</span>
															</button>
														</c:when>
														<c:otherwise>
															<button class="btn btn-xs btn-success pull-right submitBtnClass" type="button" onclick="submitExperimetComment(this)">
																<span class="bigger-110">提交</span>
																<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
															</button>
															<span class="pull-right"  style="width:10px;">&nbsp;</span>
															<button class="btn btn-xs btn-info pull-right editBtnClass" type="button" disabled='disabled' onclick="editExperimentComment(this)">
																<i class="ace-icon fa fa-pencil icon-on-left"></i>
																<span class="bigger-110">编辑</span>
															</button>
														</c:otherwise>
													</c:choose>
												</div>
												
											</div>
										</div>	
									</div>
									<!-- 空格四行 -->
									<div class="col-xs-12 col-sm-12 col-md-12 space-4"></div>	
								</c:forEach>
								
							</div>
						</div>
					</div>
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->

		<%@ include file="/WEB-INF/pages/common/footer.jsp" %>

		
	</div>
	
	<!-- layui js -->
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/courseManage/course_experiment_review.js"></script><!-- 引入用户管理脚本 -->
	
</body>
</html>
