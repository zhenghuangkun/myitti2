<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.css" />
		<style>
		</style>
	</head>

	<body class="skin-1">

		<%@ include file="/WEB-INF/pages/common/header.jsp" %>

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
								<span>策略</span>
							</li>
							<li class="active">策略管理</li>
						</ul>
					</div>

					<div class="page-content">
						<div class="space-6"></div>
						<div class="page-header">
							<button id="addModalShow" type="button" class="btn btn-primary btn-sm">
								<span class="ace-icon fa fa-plus icon-on-right bigger-120"></span>添加策略
							</button>
						</div>
						<div class="space-6"></div>
						<div class="page-body">
							<table class="table  table-bordered table-hover">
								<thead>
									<tr>
										<th style="width:15%" class="center">策略名称</th>
										<th style="width:70%" class="center">策略描述</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${policyList}" var="policy">
									<tr>
										<th class="center">${policy.name}</th>
										<th>${policy.description}</th>
										<th class="center">
											<div>
												<button onclick="javaScript:updatePolicy('${policy.id}','${policy.name}','${policy.description}','${policy.fileUrl}')" class="btn btn-xs btn-info" data-toggle="modal" data-target="#editTacticsModel"><i class="ace-icon fa fa-pencil bigger-120">修改</i></button>
												<button onclick="javaScript:deletePolicy('${policy.id}')" class="btn btn-xs btn-danger" id="delTactics"><i class="ace-icon fa fa-trash-o bigger-120">删除</i></button>
											</div>
										</th>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div><!-- /.page-content -->
				</div>
				
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		 <form class="form-horizontal" method="post" action="" role="form" id="tacticsForm" enctype="multipart/form-data">
			<div class="modal fade" id="addTacticsModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			             	<h4 id="addh4" class="modal-title">添加策略</h4>
			                <h4 id="edith4" class="modal-title">编辑策略</h4>
			            </div>
			            
			            <div class="clearfix">
							<div class="pull-left alert alert-success no-margin  col-sm-12">
								<i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
								<span style="color: red">*</span>&nbsp;&nbsp;表示为必填项
							</div>
						</div>
			            <div class="modal-body">
			            <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >策略名称：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<input type="text" name="name" id="name" class="form-control"  maxlength="50" />
							</div>
						 </div>
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >策略描述：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<textarea name="description" id="description" class="autosize-transition form-control" style="height: 125px;" placeholder="策略描述不超过500个字符" maxlength="500" ></textarea>
							</div>
						 </div>	
						  <div class="form-group" id="fileUrlDiv">
							<label class="col-sm-3 control-label no-padding-right" >策略内容：</label>
							<div class="col-sm-8">
							<label class="col-sm-3 control-label no-padding-right" ><a href="#" target="_blank" id="fileUrl">查看</a></label>
							</div>
						  </div>
						  <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >上传策略文件：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<input type="file" name="myfile" class="form-control" style="display:inline-block;height: 40px;"  >
							</div>
						 </div>						 
						</div>
						<div class="modal-footer">
							<input type="hidden" name="id" id="id" />
				            <button type="submit" id="addTactics" class="btn btn-primary" >提交</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal -->
			 </div>
		  </form><!-- 添加态框（Modal）end -->	  
		<script src="${pageContext.request.contextPath}/resource/pulgins/raty/jquery.raty.js"></script>
		<script type="text/javascript">
			$.fn.raty.defaults.path = '${pageContext.request.contextPath}/resource/pulgins/raty/images';//设置raty的全局图片路径
		</script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script type= "text/javascript" src="${pageContext.request.contextPath}/resource/js/policy/policy.js"></script>
		<!-- 引入自定义扩展验证 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/validator/bootstrapValidator-extend.js"></script>
	</body>
</html>
