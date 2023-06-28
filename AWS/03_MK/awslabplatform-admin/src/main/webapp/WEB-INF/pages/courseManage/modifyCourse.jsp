<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/chosen.min.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-datetimepicker.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/select2.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/zTree/zTreeStyle/zTreeStyle.css" type="text/css">
	<!-- layer css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/courseManage/modifyCourseStyle.css"/>
</head>

<body class="skin-1">

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
						<li><i class="ace-icon fa fa-home home-icon"></i> <span>课程管理</span>
						</li>
						<li class="active">修改课程</li>
					</ul>
				</div>
				
				<div class="page-content">
					<%@ include file="/WEB-INF/pages/common/setting.jsp"%>
					<!-- <div class=""> -->
						<div class="row">
							<div class="widget-body">
								<div class="widget-main">
									<!-- 课程信息表单 -->
									<form class="form-horizontal from-course-class" id="addCourseFrom" method="POST" action="" enctype="multipart/form-data">
										
										<input type="hidden" name="courseUuid" id="courseUuid" value="${courseUid}">
										<input type="hidden" name="requestType" id="requestType" value="${requestType}">
										
										
										<!-- 课程属性 -->
										<div class="col-xs-12 col-sm-12 col-md-12" style="background-color: #f7f8fb; border:1px solid #dfe3ed">
											<!-- 课程属性-带水平横线 -->
											<div class="mytitle">
												<!-- <span class="ace-icon fa fa-leaf green"></span> -->
												课程属性
											</div>
																			
											<!-- 空两行 -->
											<div class="space-2"></div>
											
											<div class="form-group">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel">课程共享范围</label>
												<div class="col-xs-12 col-sm-4">
													<div class="openRange">
														<label class="line-height-0 blue" >
															<input name="openRange" value="0" class="ace ace-courseOpenRange" id="courseOpenRange1" type="radio" ${course.openRange eq '0' ? 'checked' : ''}/>
															<span class="lbl" style="font-size:16px;"> 学生</span>
														</label>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="line-height-0 blue">
															<input name="openRange" value="1" class="ace ace-courseOpenRange" id="courseOpenRange2" type="radio" ${course.openRange eq '1' ? 'checked' : ''}/>
															<span class="lbl"> 院系</span>
														</label>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="line-height-0 blue">
															<input name="openRange" value="2" class="ace ace-courseOpenRange" id="courseOpenRange3" type="radio" ${course.openRange eq '2' ? 'checked' : ''}/>
															<span class="lbl"> 平台</span>
														</label>
													</div>
												</div>
												<div class="col-xs-12 col-sm-5 prompt collegeOpenRange">
													<i class=promptIcan></i>
													<small id="collegeOpenRangeLabel">请选择课程所属院系</small>
												</div>
											</div>
											
											<div class="form-group selectExperiment">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" for="selectExperiment">选择实验组</label>
												
												<div class="col-xs-12 col-sm-4">
													<!-- 实验组 -->
													<div class="tree-container">
														<ul class="ztree" id="mytree" style="width:100%;height: 190px;"></ul>
													</div>
													
												</div>
																							
												
												<div class="col-xs-12 col-sm-2">
													
													<a class="blue" href="javascript:void(0)" onclick="showExperimentGroupConfigureModal()">
														<i class="glyphicon glyphicon-plus"></i>
														添加实验组
													</a>
												</div>
											</div>
											
											<!-- 空16行 -->
											<!-- <div class="space-16"></div> -->
											
											<div class="form-group" >
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" >课程启动类型</label>
												<!-- #section:plugins/fuelux.treeview -->
												
												<div class="col-xs-12 col-sm-2 startUpType">
													<input id="courseStartUpTypeVal" name="courseStartUpTypeVal" type="checkbox" class="ace ace-switch ace-switch-5" ${course.courseStartUpType eq '1' ? 'checked' : ''}>
													<span class="lbl middle"></span>
												</div>
												
												<div class="col-xs-12 col-sm-5 prompt" id="promptCourseStartType">
													<i class=promptIcan></i>
													<small>课程审核通过后自动发布该课程</small>
												</div>
											</div>
											
											<!-- 空16行 -->
											<!-- <div class="space-16"></div> -->
											
											<div class="form-group" id="courseStartTimeConfig">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel">启动日期</label>
												
												
												<div class="col-xs-12 col-sm-4">
													<div class="input-group input-group-sm myweidth">
														<input class="form-control laydatetime" id="myDatepickerInput" name="courseStratTime" type="text" value="${course.courseStartUpType eq '1' ? course.courseStratTime : ''}"/>
													</div>
												</div>
											</div>
										</div>
										
										<!-- 空26行 -->
										<div class="col-xs-12 col-sm-12 col-md-12" style="height:26px;background-color: #ffffff"></div>
										
										
										
										<!-- 课程基本信息 -->
										<div class="col-xs-12 col-sm-12 col-md-12" style="background-color: #f7f8fb; border:1px solid #dfe3ed">
											<!-- 课程基本信息-带水平横线 -->
											<div class="mytitle">
											<!-- <span class="ace-icon fa fa-leaf green"></span> -->
											基本信息
											</div>
											
											
											<!-- 院系设置 -->
											<div class="form-group">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" for="collegeId">所属院系</label>
	
												<div class="col-xs-12 col-sm-4">
													<div class="clearfix myweidth">
														<select id="collegeId" name="collegeId" class="select2" data-placeholder="请选择院系..." onchange="changeCollege(this)">
															<option value="0">请选择</option>
															<c:forEach items="${collegeList}" var="college">
																<option value="${college.mechanismId}"
																	${course.collegeId eq college.mechanismId ? 'selected' : ''}>${college.mechanismName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
	
											<!-- 空16行 -->
											<div class="space-16"></div>
	
											<!-- 专业设置 -->
											<div class="form-group">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" for="specialtyId">专业</label>
												
												<div class="col-xs-12 col-sm-4">
													<div class="clearfix myweidth">
														<select id="specialtyId" name="specialtyId" class="select2" data-placeholder="请选择专业...">
															<option value="0">请选择</option>
															<c:forEach items="${specialtyList}" var="specialty">
																<option value="${specialty.mechanismId}"
																	${course.specialtyId eq specialty.mechanismId ? 'selected' : ''}>${specialty.mechanismName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
	
											<!-- 空16行 -->
											<div class="space-16"></div>
											
											
											<div class="form-group">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" for="courseType">课程类型</label>
												
												<div class="col-xs-12 col-sm-4">
													<div class="clearfix myweidth">
														<select id="courseType" name="courseType" class="select2" data-placeholder="请选择类型...">
															<option value="0">请选择</option>
															<c:forEach items="${courseTypeList}" var="courseType">
																<option value="${courseType.itemId}"
																	${course.courseType eq courseType.itemId ? 'selected' : ''}>${courseType.itemName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-xs-12 col-sm-4" style="margin-top:8px;">
													<a class="blue" href="javascript:void(0)" data-toggle="modal" data-target="#addCourseTypeModal">
														<i class="glyphicon glyphicon-plus"></i>
														添加课程类型
													</a>
												</div>				
											</div>
											<!-- 空16行 -->
											<div class="space-16"></div>
											
											<div class="form-group">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" for="courseName">课程名</label>
												
												<div class="col-xs-12 col-sm-4">
													<div class="clearfix myweidth">
														<!-- 课程名，不能为空 -->
														<input name="courseName" id="courseName" value="${course.courseName}" class="col-xs-12 col-sm-12" type="text" placeholder="课程名称" required="required" maxlength="30" style="border-radius:4px">
														
													</div>
												</div>
												
											</div>
	
											<!-- 空16行 -->
											<div class="space-16"></div>
											
											<div class="form-group">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" for="courseTypeLevel">课程等级</label>
												
												<div class="col-xs-12 col-sm-4">
													<div class="clearfix myweidth">
														<select class="levelselect" id="courseTypeLevel" name="courseTypeLevel" style="width:100%" data-placeholder="请选择等级...">
															<option value="0">请选择</option>
															<c:forEach items="${courseLevelList}" var="courseTypeLevel">
																<option value="${courseTypeLevel.itemId}"
																	${course.courseTypeLevel eq courseTypeLevel.itemId ? 'selected' : ''}>${courseTypeLevel.itemName}</option>
															</c:forEach>
														</select>
													</div>
												</div>			
											</div>
											
											<!-- 空16行 -->
											<div class="space-16"></div>
											
											<!-- 课程图片 -->
											<div class="form-group">
												<label class="col-xs-12 col-md-2 no-padding-right coursePictureLabel">课程图标</label>
												
												<div class="col-xs-12 col-sm-4">
													<div class="clearfix coursePictureClass">
														<!-- <input name="coursePic" id="coursePic" class="col-xs-12 col-sm-6" type="file"> -->
														<a href="javascript:void(0)" id="coursePic" style="width:100px">
															<img src="${course.coursePicUrl}" class="img-responsive" alt="请选择图片" id="coursePicImg">
														</a>
													</div>
												</div>
												
												
												<div class="col-xs-12 col-md-5 prompt">
													<i class=promptIcan></i>
													<small>图片格式:jpg/bmp等,大小不超过5M</small>
												</div>
												
											</div>
											
											
											<!-- 空16行 -->
											<div class="space-16"></div>
	
											<!-- 课程简介 -->
											<div class="form-group">
												<label class="control-label col-xs-12 col-md-2 no-padding-right mylabel" for="description">课程简介</label>
												<div class="col-xs-12 col-sm-6">
													<div class="clearfix">
														<textarea class="input-xxlarge valid textarea-class" name="description" id="description" onkeydown="displayWordCound(this,500)" onkeyup="displayWordCound(this, 500)" onfocus="displayWordCound(this, 500)" maxlength="500" placeholder="请输入课程描述、课程要求、课程目标..."  style="height: 65px;">${course.description}</textarea>
													</div>
												</div>
												<br/><br/><br/><span id="description-total">${fn:length(course.description)}</span>/500
											</div>
											
										</div>
										<!-- 空26行 -->
										<div class="col-xs-12 col-sm-12 col-md-12" style="height:26px;background-color: #ffffff"></div>
										
										<!-- 课程实验 -->
										<div class="col-xs-12 col-sm-12 col-md-12" style="background-color: #f7f8fb; border:1px solid #dfe3ed">
											<!-- 课程属性-带水平横线 -->
											<div class="mytitle">
												<!-- <span class="ace-icon fa fa-leaf green"></span> -->
												课程实验
											</div>
											
											<!-- 空两行 -->
											<div class="space-2"></div>
											
											<button type="button" class="btn btn-white btn-default btn-round" data-toggle="modal" data-target="#addCourseExperiment">
												<span class="glyphicon glyphicon-plus"></span>
													添加实验
											</button>
											
											<!-- 空两行 -->
											<div class="space-2"></div>
											
											<table id="courseExperimentTB" class="table table-striped table-hover">
												
											</table>
											
										</div>
										
										<!-- 空26行 -->
										<div class="col-xs-12 col-sm-12 col-md-12" style="height:26px;background-color: #ffffff"></div>
										<!-- 水平分割线(双虚线) -->
										<div class="hr hr-18 dotted hr-double"></div>
										
										<div class="col-xs-12 col-sm-12 col-md-12" >
											<div style="float:right;">
												<button class="btn btn-info" type="button" onclick="submitCourseCheck()">
													<i class="ace-icon fa fa-check bigger-110"></i>
													提交
												</button>
												<button class="btn btn-info" id="startSubmit" type="button" style="display: none"></button>
												<button class="btn btn-grey" type="button" onclick="saveCourse()">
													<i class="ace-icon fa fa-floppy-o bigger-110"></i>
													保存
												</button>
												<!-- <button class="btn" type="reset" >
													<i class="ace-icon fa fa-undo bigger-110"></i>
													重置
												</button> -->
												<button class="btn btn-danger" type="button" onclick="quitAddPage()">
													<i class="ace-icon fa fa-reply icon-only bigger-110"></i>
													退出
												</button>
											</div>	
										</div>
										
									</form>
								</div>
								<!-- /.widget-main -->
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.row -->

					<!-- </div> -->
					<!-- /.page-header -->
					
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<%@ include file="/WEB-INF/pages/common/footer.jsp"%>

		<!-- 添加课程类型 模态框 -->
		<%@ include file="addCourseTypeModal.jsp"%>
		
		<!-- 添加课程实验 模态框 -->
		<%@ include file="addCourseExperiment.jsp"%>
		<%@ include file="modifyCourseExperiment.jsp"%>
		
		<!-- 添加课程实验组 模态框 -->
		<%@ include file="experimentGroupConfigure.jsp"%>
		
	</div>
	
	<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/extensions/Select/js/dataTables.select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/resource/assets/js/SignXConstantValue.js"></script> --%>

	
	<script src="${pageContext.request.contextPath}/resource/assets/js/chosen.jquery.min.js"></script>

	<script src="${pageContext.request.contextPath}/resource/assets/js/fuelux/fuelux.tree.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/fuelux/fuelux.spinner.min.js"></script>
	
	<!-- 日期时间选择js -->
	<script src="${pageContext.request.contextPath}/resource/assets/js/date-time/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/date-time/moment.zh-cn.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/assets/js/select2.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/zTree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/zTree/jquery.ztree.excheck.min.js"></script>
	
	<!-- layui js -->
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/jquery.serializejson.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.js" charset="utf-8"></script>
	
	<!-- 引入自定义扩展验证 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/validator/bootstrapValidator-extend.js"></script>
	
	<!-- 引入用户管理脚本 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/courseManage/modify_course.js"></script>
	

</body>
</html>
