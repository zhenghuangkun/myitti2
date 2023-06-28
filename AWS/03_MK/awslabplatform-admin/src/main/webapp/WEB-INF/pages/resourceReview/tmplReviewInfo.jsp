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
							<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>模板使用申请信息</h3>
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
									<div class="profile-info-name"> 收藏人数 </div>

									<div class="profile-info-value">
										<span>${tmplate.collectCount}人</span>
									</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name"> 模板估价 </div>

									<div class="profile-info-value">
										<span>${tmplate.tmplPrice}元/分钟</span>
									</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name"> 综合评价指数 </div>
									<div class="col-xs-12">
										<div class="row">
											<div class="profile-info-value">
												<div class="bigger-110">
													<div class="raty read" data-score="${tmplate.evaluateAvgrate}"></div>
												</div>
											</div>
											<div class="profile-info-value">
												<div class=" bigger-110">
													${tmplate.evaluateAvgrate}
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name"> 申请人 </div>

									<div class="profile-info-value">
										<span>${tmplUseApply.applicant}</span>
									</div>
								</div>
								
								<div class="profile-info-row">
									<div class="profile-info-name"> 使用时长 </div>

									<div class="profile-info-value">
										<span>${tmplUseApply.useTimeLength}分钟</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> 预计消费 </div>

									<div class="profile-info-value">
										<span>${tmplUseApply.totalPrice}元</span>
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
								<c:choose>
							   		<c:when test="${tmplate.tmplImg != null}"> 
								   		<img class="img-responsive" src="${tmplate.tmplImg.fileUrl}" alt="图片加载错误"/> 
								    </c:when>
								    <c:otherwise> 
								   		<img class="img-responsive" src="${pageContext.request.contextPath}/resource/images/template/templatelogo.jpg" alt="图片加载错误"/> 
								    </c:otherwise>
								</c:choose>

							</span>

							<!-- /section:pages/profile.picture -->
							<div class="space-4"></div>
						</div>
						
						<!-- /section:pages/profile.info -->
						<div class="space-20"></div>

					</div>
					
					<c:if test="${tmplUseApply.reviewState == 1}">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-horizontal" role="form" id="tmplReviewForm">
									<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>审核</h3>
									<input id="applyId" type="text" class="hidden" value="${tmplUseApply.applyId}"/>
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
											同意使用
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn btn-info btn-round" type="button" id="refuse" data-opt="3">
											<i class="ace-icon fa fa-check bigger-110"></i>
											拒绝使用
										</button>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					

					
					<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>流程记录</h3>
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<div class="container" >
				                <div class="qa-message-list">
				                	<c:choose>
									   <c:when test="${empty tmplUseApply.tmplReivewRecord}"> 
									   		没有流程记录
									   </c:when>
									   <c:otherwise>
									   		 <c:forEach var="reivewRecord" items="${tmplUseApply.tmplReivewRecord}">
							                    <div class="message-item">
							                        <div class="message-inner">
							                            <div class="message-head clearfix">
							                                <div class="avatar pull-left">
							                                    <img src="${pageContext.request.contextPath}/resource/assets/avatars/avatar5.png"/>
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
			                          				                    <span class="qa-message-when">
																			<span class="qa-message-when-data">
																				<i class="ace-icon fa fa-clock-o bigger-110"></i>${reivewRecord.optTime}
																			</span>
							                                            </span>
							                                            <span class="qa-message-who">
																			<span class="qa-message-who-pad"> </span>
							                                            	<span class="qa-message-who-data">
							                                            	<i class="fa fa-user bigger-110"></i>${reivewRecord.operator}
							                                            	</span>
							                                            </span>
							                                            <span class="qa-message-what"></span>
			
							                                        </div>
							                                    </div>
							                                </div>
							                            </div>
							                            <div class="qa-message-content">
							                            	${reivewRecord.remark}
							                            </div>
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
		<script src="${pageContext.request.contextPath}/resource/js/resourceReview/tmplReviewInfo.js"></script>
	</body>
</html>