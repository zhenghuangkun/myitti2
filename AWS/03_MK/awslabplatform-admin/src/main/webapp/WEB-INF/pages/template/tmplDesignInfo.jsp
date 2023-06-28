<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-editable.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css">
	</head>

	<body class="no-skin">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12 col-sm-4 center" style="padding-top: 10%;">
							<div>
								<!-- #section:pages/profile.picture -->
								<span class="profile-picture">
									<c:choose>
									   <c:when test="${tmplate.tmplImg != null}"> 
									   		<img id="tmplImg" class="editable img-responsive" alt="图片加载失败" data-pk="1" width="180" height="200"
												src="${tmplate.tmplImg.fileUrl}" />
									   </c:when>
									   <c:otherwise> 
									   		<img id="tmplImg" class="editable img-responsive" alt="图片加载失败" data-pk="1" width="180" height="200"
												src="${pageContext.request.contextPath}/resource/images/template/templatelogo.jpg" />
									   </c:otherwise>
									</c:choose>
									
								</span>
	
								<!-- /section:pages/profile.picture -->
								<div class="space-4"></div>
	
								<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
									<div class="inline position-relative">
										<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
											<i class="ace-icon fa fa-circle light-green"></i>
											&nbsp;
											<span class="white">点击图片更换模板缩略图</span>
										</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-xs-8">
							
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal" role="form" id="tmplDesignForm">
								<h4 class="header blue bolder smaller">实验模板基本信息</h4>
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
									<label class="col-sm-3 control-label no-padding-right"> 模板类型 </label>
									<div class="col-sm-9">
										<select id="${tmplType[0].dicCode}" name="${tmplType[0].dicCode}">
											<c:forEach var="dicData" items="${tmplType}">
												<c:choose>
												   <c:when test="${dicData.itemValue == tmplate.tmplType}"> 
												   		<option value="${dicData.itemId}" selected="selected">${dicData.itemName}</option>
												   </c:when>
												   <c:otherwise> 
												   		<option value="${dicData.itemId}">${dicData.itemName}</option>
												   </c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"> 模板描述 </label>
									<div class="col-sm-9">
										<textarea name="description" class="autosize-transition form-control" rows="10">${tmplate.description}</textarea>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"> 模板参考定价 </label>
									<div class="col-sm-3">
										<input type="text" name="tmplPrice"  class="form-control" value="${tmplate.tmplPrice}"/>
									</div>
									<label class="control-label no-padding-right"> 元/分钟</label>
								</div>
								
<!-- 								<div class="form-group">
									<div class="radio">
										<label class="col-sm-3 control-label no-padding-right"> 发布范围  </label>
										<label>
											<input name="releaseRange" type="radio" value="0" class="ace" checked="checked"/>
											<span class="lbl"> 私有  </span>
										</label>
										<label>
											<input name="releaseRange" type="radio" value="1"  class="ace" />
											<span class="lbl"> 院系 </span>
											<select id="templateType">
												<option value="1">计算机学院</option>
												<option value="2">通信工程学院</option>
											</select>
										</label>
										<label>
											<input name="releaseRange" type="radio" value="2"  class="ace" />
											<span class="lbl"> 社区  </span>
										</label>
									</div>
								</div> -->
							</form>
							<div class="space-6"></div>
							<h4 class="header blue bolder smaller lighter">
								实验模板资源脚本 &nbsp;&nbsp;
								<i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue "></i>
								<a onclick="window.open('https://ap-northeast-2.signin.aws.amazon.com/oauth?response_type=code&client_id=arn%3Aaws%3Aiam%3A%3A015428540659%3Auser%2Fcloudformation&redirect_uri=https%3A%2F%2Fap-northeast-2.console.aws.amazon.com%2Fcloudformation%2Fdesigner%2Fhome%3Fregion%3Dap-northeast-2%26state%3DhashArgs%2523%26isauthcode%3Dtrue&forceMobileLayout=0&forceMobileApp=0', 
										'_blank')" class="green" role="button"> AWS模板编辑器>>> 
								</a>
								
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
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12 center">
							<div class="clearfix form-actions">
								<div class="col-md-12">
									<button id="saveBtn" class="btn btn-info btn-round" type="button">
										<i class="ace-icon fa fa-floppy-o bigger-110"></i>
										保存
									</button>
<%-- 									<c:if test="${operation != 'new'}">
										&nbsp; &nbsp; &nbsp;
										<button id="releaseBtn" class="btn btn-info" type="button">
											<i class="ace-icon fa fa-check bigger-110"></i>
											发布
										</button>
									</c:if> --%>
								</div>
							</div>
						</div>
					</div>
					
				</div><!-- /.page-content -->
			</div><!-- /.main-content-inner -->
		</div><!-- /.main-content -->
		
		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/resource/assets/js/x-editable/bootstrap-editable.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/x-editable/ace-editable.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/validator/bootstrapValidator-extend.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.serializejson.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.js"></script>
		<script type="text/javascript">
			var tmplImg = "";//模板缩略图
			var tmplScript = {data:"", uploadState:false};//模板资源脚本
 			/*var tmplScript = {//模板资源脚本
 				fileId: "${tmplate.tmplScript.fileId}",
				fileName: "${tmplate.tmplScript.fileName}",
				correlationId: "${tmplate.tmplScript.correlationId}",
				fileUrl: "${tmplate.tmplScript.fileUrl}",
				fileType: "${tmplate.tmplScript.fileType}",
				fileSize: "${tmplate.tmplScript.fileSize}",
				imgWidth: "${tmplate.tmplScript.imgWidth}",
				imgHight: "${tmplate.tmplScript.imgHight}",
				distinguishFlag: "${tmplate.tmplScript.distinguishFlag}",
				operator: "${tmplate.tmplScript.operator}",
				optTime: "${tmplate.tmplScript.optTime}",
				state: "${tmplate.tmplScript.state}", 
				uploadState:false
			}; */
		</script>
		
		<script src="${pageContext.request.contextPath}/resource/js/template/tmplDesignInfo.js"></script><!-- 引入模板设计脚本 -->
		

	</body>
</html>