<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<!-- page specific plugin styles -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/template/template.css"/>
	</head>

	<body class="no-skin">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-8">
							<form class="form-horizontal" role="form" id="tmplMakeForm">
								<h4 class="header blue bolder smaller">模板信息</h4>
								<!-- #section:elements.form -->
								<input type="text" id="tmplId" name="tmplId" class="hidden" value="${tmplate.tmplId}"/>
								<input type="text" id="operation" name="operation" class="hidden" value="${operation}"/>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"> 模板名称 </label>
									<div class="col-sm-4">
										<input type="text" name="tmplName"  class="form-control" value="${tmplate.tmplName}"/>
									</div>
								</div>
	
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"> 模板描述 </label>
									<div class="col-sm-9">
										<textarea name="description" class="autosize-transition form-control" rows="10">${tmplate.description}</textarea>
									</div>
								</div>
									
								<div class="space-6"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"> 源模板 </label>
									<div class="col-sm-9">
										<select id="associatedTmplId" name="associatedTmplId">
											<c:forEach var="associatedTemplate" items="${templates}">
												<c:choose>
													
												   <c:when test="${tmplate.associatedTmplId == associatedTemplate.tmplId}"> 
												   		<option value="${associatedTemplate.tmplId}" selected="selected">${associatedTemplate.tmplName}</option>
												   </c:when>
												   <c:otherwise> 
												   		<option value="${associatedTemplate.tmplId}">${associatedTemplate.tmplName}</option>
												   </c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										<span class="help-button" data-trigger="hover" data-toggle="tooltip" title="点击查看源模板信息">?</span>
									</div>
								</div>
							</form>
						</div>
					</div> 
					<h4 class="header blue bolder smaller lighter">
						实验模板资源脚本
					</h4>
							
					<div class="space-6"></div>
							
			        <div class="layui-upload">
			        	<c:choose>
						   <c:when test="${tmplate.tmplScript != null}"> 
						   		<button type="button" class="layui-btn layui-btn-normal layui-hide" id="fileListBtn">
							  		<i class="glyphicon glyphicon-plus"></i><i class="layui-icon"></i>添加资源脚本
							  	</button>
						   </c:when>
						   <c:otherwise> 
						   		<button type="button" class="layui-btn layui-btn-normal" id="fileListBtn">
							  		<i class="glyphicon glyphicon-plus"></i><i class="layui-icon"></i>添加资源脚本
							  	</button>
						   </c:otherwise>
						</c:choose>
						 
					  	<div class="layui-upload-list">
						    <table class="layui-table">
						    	<thead>
							        <tr>
							        	<th></th>
								        <th>文件名</th>
								        <th>大小(KB)</th>
								        <th>状态</th>
								        <th>操作</th>
							    	</tr>
						      	</thead>
						      	<tbody id="fileList">
						      		<c:if test="${tmplate.tmplScript != null}">
						      			<tr id="${tmplate.tmplScript.fileId}">
						     				<td>
						     					<img class="layui-upload-img" src="${pageContext.request.contextPath}/resource/images/fileicon.jpg">
						     				</td>
						     				<td>${tmplate.tmplScript.fileName}</td>
						     				<td>${tmplate.tmplScript.fileSize}</td>
						     				<td><span style="color: #5FB878;">上传成功</span></td>
						     				<td>
							      				<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>
							      				<button class="layui-btn" onclick="window.open('${tmplate.tmplScript.fileUrl}')">预览</button>
							      				<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>
						      				</td>
						      			</tr>
									</c:if>
						      	</tbody>
						    </table>
					  	</div>
					  	<c:choose>
						   <c:when test="${tmplate.tmplScript != null}"> 
						   		<button type="button" class="layui-btn layui-hide" id="listAction">开始上传</button> 
						   </c:when>
						   <c:otherwise> 
						   		<button type="button" class="layui-btn" id="listAction">开始上传</button>
						   </c:otherwise>
						</c:choose>
					</div>
					
					<div class="row">
						<div class="col-xs-12 center">
							<div class="clearfix form-actions">
								<div class="col-md-12">
									<button id="saveBtn" class="btn btn-info btn-round" type="button">
										<i class="ace-icon fa fa-floppy-o bigger-110"></i>
										保存
									</button>
									<c:if test="${operation == 'edit' && tmplate.releaseStatus != 1 && tmplate.releaseReviewState != 1 && tmplate.releaseReviewState != 2}">
										&nbsp; &nbsp; &nbsp;
										<button class="btn btn-info btn-round" type="button"
										data-toggle="modal" data-target="#releasemodel">
											<i class="ace-icon fa fa-check bigger-110"></i>
											发布申请
										</button>
									</c:if> 
								</div>
							</div>
						</div>
					</div>
					
					<c:if test="${operation == 'edit'}">
					<h3 class="header blue bolder smaller">
						<i class="ace-icon fa fa-leaf green"></i>发布流程记录
					</h3>
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
					</c:if>
					
				</div><!-- /.page-content -->
			</div><!-- /.main-content-inner -->
		</div><!-- /.main-content -->
		
		<!-- 模态框（Modal） -->
		<div id="releasemodel" class="modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="blue bigger">模板发布申请</h4>
					</div>
					<div class="modal-body">
						<div class="form-horizontal" >
							<div class="form-group">
								<label class="col-sm-2">发布范围：</label>
								<div class="col-sm-6">
									<input name="releaseRange" type="radio" class="ace" value="1" checked="checked"/>
									<span class="lbl">平台共享</span>
									<input name="releaseRange" type="radio" class="ace" value="2"/>
									<span class="lbl">院系共享</span>
								</div>
							</div>
							<div id="releaseDeptDiv" class="form-group hide">
								<label class="col-sm-2">院系：</label>
								<div class="col-sm-6">
									<select name="releaseDept" id="releaseDept">
										<c:forEach var="mechanism" items="${mechanisms}">
									   		<option value="${mechanism.mechanismId}">${mechanism.mechanismName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">申请备注：</label>
								<div class="col-sm-8">
									<textarea  id="remark" class="form-control" maxlength="500"
										name="remark" rows="10"></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary btn-round"
							id="releaseSubmit">提交</button>
						<button type="button" class="btn btn-default btn-round"
							data-dismiss="modal">取消</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/validator/bootstrapValidator-extend.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.serializejson.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.js"></script>
		<script type="text/javascript">
			var tmplImg = "";//模板缩略图
			var tmplScript = {data:"", uploadState:false};//模板资源脚本
		</script>
		
		<script src="${pageContext.request.contextPath}/resource/js/template/tmplMaking/tmplMakingInfo.js"></script><!-- 引入模板制作脚本 -->
		

	</body>
</html>