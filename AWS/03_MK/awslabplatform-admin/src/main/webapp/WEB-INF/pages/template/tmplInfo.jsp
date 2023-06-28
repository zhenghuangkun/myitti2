<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/paginator/jqpaginator.css" />
		<style type="text/css">
		</style>
	</head>

	<body class="no-skin">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12 col-sm-8">
							<!-- PAGE CONTENT BEGINS -->
							<input type="text" class="hidden" id="tmplId" value="${tmplate.tmplId}"/>
							<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>模板基本信息</h3>
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
						
						<div class="col-xs-12 col-sm-4 center" style="padding-top: 5%;">
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
					</div>
					
					<div class="row">
						<div class="col-xs-12 center">
							<div class="clearfix form-actions">
								<div class="col-md-12">
									<button class="btn btn-info btn-round" type="button" id="collection" data-ref="${collection.collectionId}">
										<c:choose>
										   <c:when test="${collection == null}"> 
										   		<i class="ace-icon fa fa-heart-o bigger-110"></i>收藏
										   </c:when>
										   <c:otherwise> 
										   		<i class="ace-icon fa fa-heart bigger-110"></i>取消收藏 
										   </c:otherwise>
										</c:choose>
										
									</button>
									&nbsp; &nbsp; &nbsp;
									<button class="btn btn-info btn-round" type="button" data-toggle="modal" data-target="#applymodel">
										<i class="ace-icon fa fa-check bigger-110"></i>
										测试申请
									</button>
								</div>
							</div>
						</div>
					</div>
					
					<!-- 模态框（Modal） -->
					<div id="applymodel" class="modal" tabindex="-1">
					    <div class="modal-dialog">
					        <div class="modal-content">
					            <div class="modal-header">
					                <button type="button" class="close" data-dismiss="modal" >&times;</button>
					                <h4 class="blue bigger" >模板测试申请</h4>
					            </div>
					            <div class="modal-body">
					            	<div class="form-horizontal"  id="applyForm">
										 <div class="form-group">
											<label class="col-sm-2" >申请时长：</label>
											<div class="col-sm-6">
												<div class="input-group">
											  		<input type="text" id="useTimeLength" name="useTimeLength" >&nbsp;分钟
											  	</div>
											  	
											</div>
										 </div>
										 <div class="form-group">
											<label class="col-sm-2" >费用(元)：</label>
										  	<div class="price col-sm-6">
												<span class="label label-lg label-inverse arrowed-in arrowed-in-right">
													¥&nbsp;<b id="totalPrice">0</b>
												</span>
											</div>
										 </div>
										 <div class="form-group">
											<label class="col-sm-2">备注：</label>
											<div class="col-sm-8">
												<textarea id="remark" class="form-control" name="remark" rows="10"></textarea>
											</div>
										 </div>
		 							</div>
								</div>
					            <div class="modal-footer">
					                <button type="button" class="btn btn-primary btn-round" id="applySubmit">提交</button>
					                <button type="button" class="btn btn-default btn-round" data-dismiss="modal">取消</button>
					            </div>
					        </div><!-- /.modal-content -->
					    </div><!-- /.modal -->
					 </div>
					
						
					<div class="row">
						<div class="col-sm-12">
							<form class="form-horizontal" role="form" id="tmplCommentForm">
								<h3 class="header blue bolder smaller"><i class="ace-icon fa fa-leaf green"></i>评论</h3>
								<div class="form-group">
									<div class="col-sm-9">
										<textarea id="content" name="content" class="autosize-transition form-control" rows="10" placeholder="输入您想说的话..."></textarea>
									</div>
								</div>
								
								<div class="space-6"></div>
								
								<div class="row">
									<div class="col-sm-6">
										<div id="rating" class="raty" data-score="1"><label style="font-size: 20px;">模板评分：</label></div>
									</div>
									
									<div class="col-sm-3">
										<button class="btn btn-info" type="button" id="comment">
											<i class="ace-icon fa fa-check bigger-110"></i>
											发表评论
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
						
					<div class="space-20"></div>

					<div class="widget-box transparent">
						<div class="widget-header widget-header-small">
							<h4 class="widget-title blue smaller">
								<i class="ace-icon fa fa-rss orange"></i>
								最新评论
							</h4>

							<div class="widget-toolbar action-buttons">
								<a href="#" data-action="reload">
									<i class="ace-icon fa fa-refresh blue"></i>
								</a>
							</div>
						</div>

						<!-- 评论 -->
						<div class="widget-body">
							<div class="widget-main padding-8">
								<div class="profile-feed" id="tmplCommentData">
									<c:forEach var="pageData" items="${commentInfo.data}">
										<div class="profile-activity clearfix">
											<div>
												<c:choose>
												   <c:when test="${!empty pageData.userImageUrl}"> 
												   		<img class="pull-left" src="${ pageData.userImageUrl}" />
												   </c:when>
												   <c:otherwise> 
												   		<img class="pull-left" src="${pageContext.request.contextPath}/resource/assets/avatars/avatar5.png" />
												   </c:otherwise>
												</c:choose>
												
												<a class="user">${pageData.userRealName}</a>
												<br/>
												<div>${pageData.content}</div>
												<div class="time">
													<i class="ace-icon fa fa-clock-o bigger-110"></i>
													${pageData.commentTime}
												</div>
											</div>
											<div class="tools action-buttons">
												<a id="deleteComment" data-commentId="${pageData.commentId}" class="blue" role="button">
													<i class="ace-icon fa fa-trash-o bigger-125"></i>
													删除
												</a>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						
						<div class="row center">
						 	<div class="col-sm-12 customBootstrap">
						 		 <ul id="pagination" class="pagination"></ul>
						 	</div>
						</div>
					</div>
					
				</div><!-- /.page-content -->
			</div><!-- /.main-content-inner -->
		</div><!-- /.main-content -->
		
		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/paginator/jqpaginator.min.js"></script>

		<script type="text/javascript">
			var currentPage = ${commentInfo.nowpage};//获取当前页
			var totalSize = ${commentInfo.recordsTotal};//获取总数
			var perSize = ${commentInfo.pagesize};//获取每页显示数
			var price = ${tmplate.tmplPrice};//价格
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
		
		<script src="${pageContext.request.contextPath}/resource/js/template/tmplInfo.js"></script>

	</body>
</html>