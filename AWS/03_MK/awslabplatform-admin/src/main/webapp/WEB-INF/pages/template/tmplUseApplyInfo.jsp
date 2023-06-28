<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp"%>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css"/>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/template/template.css"/>
</head>

<body class="no-skin">
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12 col-sm-8">
						<!-- PAGE CONTENT BEGINS -->
						<input id="applyId" type="text" class="hidden"
							value="${tmplUseApply.applyId}" /> <input id="tmplId" type="text"
							class="hidden" value="${tmplate.tmplId}" /> <input id="stackName"
							type="text" class="hidden" value="${tmplUseApply.stackName}" />
						<h3 class="header blue bolder smaller">
							<i class="ace-icon fa fa-leaf green"></i>申请信息
						</h3>
						<!-- #section:pages/profile.info -->
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name">模板名称</div>

								<div class="profile-info-value">
									<span>${tmplate.tmplName}</span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">模板类型</div>

								<div class="profile-info-value">
									<span>${tmplate.tmplTypeName}</span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name"> 发布者  </div>
								<div class="profile-info-value">
									<c:choose>
											<c:when test="${tmplate.tmplFromFlag != 1}"> 
												<span>${tmplate.releaser}</span>		
											</c:when>
											 <c:otherwise> 
										   		<span>平台管理员</span>
										    </c:otherwise>
										</c:choose>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">收藏人数</div>

								<div class="profile-info-value">
									<span>${tmplate.collectCount}人</span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">模板估价</div>

								<div class="profile-info-value">
									<span>${tmplate.tmplPrice}元/分钟</span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">综合评价指数</div>
								<div class="col-xs-12">
									<div class="row">
										<div class="profile-info-value">
											<div class="bigger-110">
												<div class="raty read"
													data-score="${tmplate.evaluateAvgrate}"></div>
											</div>
										</div>
										<div class="profile-info-value">
											<div class=" bigger-110">${tmplate.evaluateAvgrate}</div>
										</div>
									</div>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">申请人</div>

								<div class="profile-info-value">
									<span>${tmplUseApply.applicant}</span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">使用时长</div>

								<div class="profile-info-value">
									<span>${tmplUseApply.useTimeLength}分钟</span>
								</div>
							</div>

							<div class="profile-info-row">
								<div class="profile-info-name">预计消费</div>

								<div class="profile-info-value">
									<span>${tmplUseApply.totalPrice}元</span>
								</div>
							</div>
						</div>

						<h3 class="header blue bolder smaller">
							<i class="ace-icon fa fa-leaf green"></i>模板详细描述
						</h3>
						<!-- PAGE CONTENT BEGINS -->
						<div>
							<p>${tmplate.description}</p>
							<h4 class="lighter pull-right">
								<i
									class="ace-icon fa fa-download icon-animated-hand-pointer blue"></i>
								<a href="${tmplate.tmplScript.fileUrl}" target="_blank"
									class="pink"> 预览完整版模板资源脚本 </a>
							</h4>
						</div>

					</div>



					<div class="col-xs-8 col-sm-4 center" style="padding-top: 5%;">
						<!-- #section:pages/profile.picture -->
						<span class="profile-picture"> <c:choose>
								<c:when test="${tmplate.tmplImg != null}">
									<img class="img-responsive" src="${tmplate.tmplImg.fileUrl}"
										alt="图片加载错误" />
								</c:when>
								<c:otherwise>
									<img class="img-responsive"
										src="${pageContext.request.contextPath}/resource/images/template/templatelogo.jpg"
										alt="图片加载错误" />
								</c:otherwise>
							</c:choose>

						</span>

						<!-- /section:pages/profile.picture -->
						<div class="space-4"></div>
					</div>

					<!-- /section:pages/profile.info -->
					<div class="space-20"></div>

				</div>
				
				<div class="row">
					<div class="col-xs-12 center">
						<div class="clearfix form-actions">
							<div class="col-md-12">
								<button id="makeTmpl"  class="btn btn-white btn-info btn-bold" type="button">
									<i class="ace-icon fa fa-pencil-square-o bigger-110"></i> 模板制作
								</button>
								
								<c:if test="${tmplUseApply.reviewState == 3}">
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-white btn-info btn-bold" type="button"
										data-toggle="modal" data-target="#applymodel">
										<i class="ace-icon fa fa-undo bigger-110"></i> 重新申请
									</button>
								</c:if>
								
								<c:if test="${tmplUseApply.reviewState == 2}">
									&nbsp; &nbsp; &nbsp;
									<button id="tmplBtn" class="btn btn-white btn-info btn-bold"
										type="button">
										<i class="ace-icon fa fa-eye-slash bigger-120 blue"></i> 模板资源
									</button>
								</c:if>
							</div>
						</div>
					</div>
				</div>

				<!-- 模态框（Modal） -->
				<div id="applymodel" class="modal" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="blue bigger">模板测试申请</h4>
							</div>
							<div class="modal-body">
								<div class="form-horizontal" id="applyForm">
									<div class="form-group">
										<label class="col-sm-2">申请时长：</label>
										<div class="col-sm-6">
											<div class="input-group">
												<input type="text" id="useTimeLength" name="useTimeLength">&nbsp;分钟
											</div>

										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2">费用(元)：</label>
										<div class="price col-sm-6">
											<span
												class="label label-lg label-inverse arrowed-in arrowed-in-right">
												¥&nbsp;<b id="totalPrice">0</b>
											</span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2">备注：</label>
										<div class="col-sm-8">
											<textarea  id="remark" class="form-control"
												name="remark" rows="10"></textarea>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary btn-round"
									id="applySubmit">提交</button>
								<button type="button" class="btn btn-default btn-round"
									data-dismiss="modal">取消</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>

				<h3 class="header blue bolder smaller">
					<i class="ace-icon fa fa-leaf green"></i>流程记录
				</h3>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="container">
							<div class="qa-message-list">
								<c:choose>
									<c:when test="${empty tmplUseApply.tmplReivewRecord}"> 
									   		没有流程记录
									   </c:when>
									<c:otherwise>
										<c:forEach var="reivewRecord"
											items="${tmplUseApply.tmplReivewRecord}">
											<div class="message-item">
												<div class="message-inner">
													<div class="message-head clearfix">
														<div class="avatar pull-left">
															<img
																src="${pageContext.request.contextPath}/resource/assets/avatars/avatar5.png" />
														</div>
														<div class="user-detail">
															<h5 class="handle">
																<c:choose>
																	<c:when test="${reivewRecord.operation == 1}"> 
																	   		提交申请
																	   </c:when>
																	<c:when test="${reivewRecord.operation == 2}"> 
																	   		审核通过
																	   </c:when>
																	<c:when test="${reivewRecord.operation == 3}"> 
																	   		审核不通过
																	   </c:when>
																	<c:otherwise>

																	</c:otherwise>
																</c:choose>
															</h5>
															<div class="post-meta">
																<div class="asker-meta blue">
																	<span class="qa-message-when"> <span
																		class="qa-message-when-data"> <i
																			class="ace-icon fa fa-clock-o bigger-110"></i>${reivewRecord.optTime}
																	</span>
																	</span> <span class="qa-message-who"> <span
																		class="qa-message-who-pad"> </span> <span
																		class="qa-message-who-data"> <i
																			class="fa fa-user bigger-110"></i>${reivewRecord.operator}
																	</span>
																	</span> <span class="qa-message-what"></span>

																</div>
															</div>
														</div>
													</div>
													<div class="qa-message-content">
														${reivewRecord.remark}</div>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>

						</div>
					</div>
				</div>

			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content-inner -->
	</div>
	<!-- /.main-content -->

	<!-- page specific plugin scripts -->
	<script
		src="${pageContext.request.contextPath}/resource/assets/js/jquery.raty.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
<%-- 	<script
		src="${pageContext.request.contextPath}/resource/assets/js/fuelux/fuelux.spinner.min.js"></script> --%>
	<script
		src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.js"></script>

	<script type="text/javascript">
			var price = ${tmplate.tmplPrice};//价格
			var stackState = '${tmplUseApply.stackState}';//堆栈状态
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
	<script
		src="${pageContext.request.contextPath}/resource/js/template/tmplUseApplyInfo.js"></script>
</body>
	<c:choose>
		<c:when test="${tmplUseApply.stackState == 'CREATE_NO'}">
			<c:set var="startBtn" value="" />
			<c:set var="terminationBtn" value="layui-hide" />
			<c:set var="AMIMakeBtn" value="layui-hide" />
		</c:when>
		<c:when test="${tmplUseApply.stackState == 'CREATE_COMPLETE'}">
			<c:set var="startBtn" value="layui-hide" />
			<c:set var="terminationBtn" value="" />
			<c:set var="AMIMakeBtn" value="" />
		</c:when>
		<c:otherwise>
			<c:set var="startBtn" value="layui-hide" />
			<c:set var="terminationBtn" value="layui-hide"/>
			<c:set var="AMIMakeBtn" value="layui-hide" />
		</c:otherwise>
	</c:choose>

	<div id="resouceTab" class="win" style="display: none">
		<div id="instance">
			<c:forEach var="awsInstances" varStatus="status" items="${tmplUseApply.awsInstances}">
				<h4 class="header blue bolder smaller">
					<i class="ace-icon fa fa-cloud green"></i>服务器&nbsp;${awsInstances.instanceName}
				</h4>
				<label>到期时间：${tmplUseApply.useEndTime}</label>
				<br/>
				<label class="instanceState">状态： <span
					class="${awsInstances.instanceState ==  'terminated'? 'red' : 'green'}">${awsInstances.instanceState}</span>
				</label>
				<br/>
				<c:if test="${tmplUseApply.stackState == 'DELETE_COMPLETE'}">
					<label>实际使用时长：${tmplUseApply.actualUseLength}分钟</label>
					<br/>
				</c:if>
				<label>服务器ID：<span>${awsInstances.instanceId}</span></label>
				<br/>
				<label>服务器类型：<span>${awsInstances.instanceType}</span></label>
				<br/>
				<label>公有IP：<span>${awsInstances.publicIpAddress}</span></label>
				<br/>
				<label>私有IP：<span>${awsInstances.privateIpAddress}</span></label>
				<br/>
				<label>密钥：<span>${fn:substring(awsInstances.keyName, 0, 5)}</span>&nbsp;&nbsp;
					<a href="${awsInstances.keyPairUrl}" data-toggle="tooltip"
					title="点击下载连接密钥" ><i class="ace-icon fa fa-download blue"></i></a></label>
				<br/>
				<button class="btn btn-white btn-info btn-bold ${AMIMakeBtn} AMIMakeBtn"
					type="button" data-ref="${awsInstances.instanceId}">
					<i class="fa fa-hand-o-right icon-animated-hand-pointer bigger-120 blue"></i> 镜像制作
				</button>
				<br/>
			</c:forEach>
		</div>
		<h4 id="progressTitle" class="header blue bolder smaller layui-hide">资源创建中...</h4>
		<div class="layui-progress layui-progress-big layui-hide"
			lay-filter="progress" lay-showPercent="true">
			<div class="layui-progress-bar layui-bg-blue" lay-percent="0%"></div>
		</div>
	
		<h3 class="header blue bolder smaller"></h3>
		<button id="startBtn"
			class="btn btn-white btn-info btn-bold ${startBtn}" type="button">
			<i class="ace-icon fa fa-flask bigger-120 blue"></i> 启动资源
		</button>
		&nbsp; &nbsp;
		<button id="terminationBtn"
			class="btn btn-white btn-info btn-bold ${terminationBtn}"
			type="button" data-ref="terminationRes">
			<i class="ace-icon fa fa-trash-o bigger-120 blue"></i> 释放资源
		</button>
	</div>
</html>