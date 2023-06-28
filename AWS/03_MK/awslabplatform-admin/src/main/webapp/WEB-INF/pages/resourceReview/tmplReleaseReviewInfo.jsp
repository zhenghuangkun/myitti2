<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
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
							<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>基本信息</h3>
							<!-- #section:pages/profile.info -->
							<div class="profile-user-info profile-user-info-striped">
								<div class="profile-info-row">
									<div class="profile-info-name"> 模板名称 </div>

									<div class="profile-info-value">
										<span>${tmplate.tmplName}</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> 模板类型 </div>

									<div class="profile-info-value">
										<span>${tmplate.tmplTypeName}</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> 模板估价 </div>

									<div class="profile-info-value">
										<span>${tmplate.tmplPrice}元/分钟</span>
									</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name">发布人 </div>

									<div class="profile-info-value">
										<span>${tmplate.releaser}</span>
									</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name">发布范围 </div>
									<div class="profile-info-value">
										<span>
										<c:choose>
										<c:when test="${tmplate.releaseRange == 1}"> 
										   		平台
									    </c:when>
										<c:otherwise>
												${tmplate.releaseDept}
										</c:otherwise>
										</c:choose>
										</span>
									</div>
								</div>
								
							</div>
							
							<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>模板详细描述</h3>
							<!-- PAGE CONTENT BEGINS -->
							<div>
								<p>
									${tmplate.description}
								</p>
								<h4 class="lighter pull-right">
									<i class="ace-icon fa fa-download icon-animated-hand-pointer blue"></i>
									<a href="${tmplate.tmplScript.fileUrl}" target="_blank" class="pink"> 预览完整版模板资源脚本 </a>
								</h4>
							</div>
							
						</div>
						
							
						
						<div class="col-xs-8 col-sm-4 center" style="padding-top: 5%;">
							<!-- #section:pages/profile.picture -->
							<span class="profile-picture">
						   		<img class="img-responsive" src="${pageContext.request.contextPath}/resource/images/template/templatelogo.jpg" alt="图片加载错误"/> 
							</span>

							<!-- /section:pages/profile.picture -->
							<div class="space-4"></div>
						</div>
						
						<!-- /section:pages/profile.info -->
						<div class="space-20"></div>

					</div>
					
					<c:if test="${tmplate.releaseReviewState == 1}">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-horizontal" role="form" id="tmplReviewForm">
									<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>审核</h3>
									<input id="tmplId" type="text" class="hidden" value="${tmplate.tmplId}"/>
									<div class="form-group">
										<div class="col-sm-9">
											<textarea id="reviewContent" name="reviewContent" class="autosize-transition form-control" rows="10" placeholder="输入您的审核意见..."></textarea>
										</div>
									</div>
									
									<div class="space-6"></div>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-xs-12 center">
								<div class="clearfix form-actions">
									<div class="col-md-12">
										<button class="btn btn-info btn-round" type="button" id="pass" data-opt="2">
											<i class="ace-icon fa fa-heart bigger-110"></i>
											同意发布
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn btn-info btn-round" type="button" id="refuse" data-opt="3">
											<i class="ace-icon fa fa-check bigger-110"></i>
											拒绝发布
										</button>
										
										&nbsp; &nbsp; &nbsp;
<!-- 										<button class="btn btn-info btn-round" type="button" id="refuse" data-opt="3">
											<i class="ace-icon fa fa-check bigger-110"></i>
											资源启动
										</button> -->
									</div>
								</div>
							</div>
						</div>
					</c:if>
					
					<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>发布流程记录</h3>
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<div class="container">
								<div class="qa-message-list">
									<c:choose>
										<c:when test="${empty releaseRecords}"> 
										   		没有流程记录
									    </c:when>
										<c:otherwise>
											<c:forEach var="releaseRecord" items="${releaseRecords}">
												<div class="message-item">
													<div class="message-inner">
														<div class="message-head clearfix">
															<div class="avatar pull-left">
																<img src="${pageContext.request.contextPath}/resource/assets/avatars/avatar5.png" />
															</div>
															<div class="user-detail">
																<h5 class="handle">
																	<c:choose>
																		<c:when test="${releaseRecord.operation == 1}"> 
																		   		提交发布申请 
																	   </c:when>
																		<c:when test="${releaseRecord.operation == 2}"> 
																		   		发布申请审核通过
																		   </c:when>
																		<c:when test="${releaseRecord.operation == 3}"> 
																		   		发布申请审核不通过
																		   </c:when>
																		<c:otherwise>
																		</c:otherwise>
																	</c:choose>
																</h5>
																<div class="post-meta">
																	<div class="asker-meta blue">
																		<span class="qa-message-when"> 
																			<span class="qa-message-when-data"> 
																				<i class="ace-icon fa fa-clock-o bigger-110"></i>${releaseRecord.optTime}
																			</span>
																		</span> 
																		<span class="qa-message-who"> 
																			<span class="qa-message-who-pad"></span> 
																			<span class="qa-message-who-data"> 
																				<i class="fa fa-user bigger-110"></i>${releaseRecord.operator}
																			</span>
																		</span><br/>
																		<span class="qa-message-who"> 
																			<span class="qa-message-who-data"> 
																				发布范围 ： 
																				<c:if test="${releaseRecord.releaseRange == 1}">
																					平台共享
																				</c:if>
																				<c:if test="${releaseRecord.releaseRange == 2}">
																					${releaseRecord.releaseDept}共享
																				</c:if>
																			</span>
																		</span>  
																		<span class="qa-message-what"></span>
																	</div>
																</div>
															</div>
														</div>
														<div class="qa-message-content">${releaseRecord.remark}</div>
													</div>
												</div>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
		            
				</div><!-- /.page-content -->
			</div><!-- /.main-content-inner -->
		</div><!-- /.main-content -->
		
		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.raty.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script type="text/javascript">
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
		<script src="${pageContext.request.contextPath}/resource/js/resourceReview/tmplReleaseReviewInfo.js"></script>
	</body>
</html>