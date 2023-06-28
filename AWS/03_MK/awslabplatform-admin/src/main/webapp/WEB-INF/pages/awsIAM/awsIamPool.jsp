<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css" type="text/css"/>
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
								<span>AWS资源池管理</span>
							</li>
							<li class="active">院系AWS账号池</li>
						</ul>
					</div>

					<div class="page-content">
						<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
						<div class="page-header">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="widget-title">
										<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
										搜索栏
									</h4>
								</div>

								<div class="widget-body">
									<div class="widget-main">
										<div class="row">
											<div class="col-xs-12">
												<form class="form-inline" role="form" >
													<div class="form-group" style="padding-left: 30px;">
														<label>Account ID：</label>
														<input type="text" class="form-control" id="inputUnitName" maxlength="30"></input>
														<input type="hidden" class="form-control" id="inputIAMStatus" value="0"></input>
													</div>

													<div class="form-group" style="padding-left: 30px;">
														<label>使用情况：</label>
														<select class="form-control" id="inputState">
															<option value="-1">请选择使用详情</option>
															<option value="0">否</option>
															<option value="1">是</option>
														</select>
													</div>
													<div class="form-group" style="padding-left: 15px;">
														<button type="button" class="btn btn-primary btn-sm" onclick="doSearch()">
															<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索
														</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="add tableTools-container">
							<div class="layui-inline" style="width:300px;float:right;">
								<a id="addModalShow" class="btn btn-primary btn-sm"><i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>新增</a>
							 	<a href="${pageContext.request.contextPath}/resource/excelModel/IamModel.xls" class="btn btn-info btn-sm"><i class='ace-icon fa fa-hand-o-down bigger-120'> </i>下载模板</a>
						   		<a class="btn btn-success btn-sm" id="uploadExcel"><i class='ace-icon fa fa-hand-o-up bigger-120'> </i>批量导入</a>
				   			 </div>
						</div>
						<div class="row">
							<div class="col-xs-2 widget-box" style="margin-top: 43px" >
								<div class="table-header">账户列表</div>
								<div style="padding-top: 8px">
									<div class="form-group col-xs-8" style="padding-left: 10px;">
										<input type="text" class="form-control" id="payAccountData" maxlength="35">
									</div>
									<div class="form-group col-xs-1" >
										<button type="button" class="btn btn-primary btn-sm" onclick="doPayAccountSearch()">
											<span class="ace-icon fa icon-on-right bigger-110"></span>检索
										</button>
									</div>
								</div>
			
								<div class="layui-form col-xs-12" style="max-height: 350px;overflow: auto;">
								  <table class="layui-table table-striped table-hover">
								    <colgroup><col width="200"><col></colgroup>					
								    <tbody id="payAccountTbody"></tbody>
								  </table>
								</div>
							</div>
							
							<!-- 表格列表 -->
							<div class="col-xs-10" style="margin-top: 10px">
								<div class="table-header">院系AWS账号列表</div>
								
								<table id="awsIAMPoolTb" class="table table-striped table-hover "></table>
								
								<div id="awsIAMDiv" class="layui-form col-xs-12">
								  <table class="layui-table">
									  <colgroup><col width="200"><col width="200">
								      <col width="200"><col width="200"><col width="200">
								    </colgroup>	
								     <thead>
								      <tr>
								        <th>IAM用途</th><th>IAM user</th>
								        <th>Password</th><th>PK</th><th>SK</th>
								      </tr> 
								    </thead>			
								    <tbody id="awsIAMTb"></tbody>
								  </table>
								</div>
							</div>
						</div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		<form class="form-horizontal" method="post"  role="form" id="awsIAMPoolForm" onsubmit="return ">
			<div  class="modal fade" id="awsIAMPoolModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 id="addh4" class="modal-title" id="myModalLabel">添加院系AWS账号信息</h4>
			                <h4 id="edith4" class="modal-title" id="myModalLabel">编辑院系AWS账号信息</h4>
			            </div>
			            
			    		<div class="clearfix">
							<div class="pull-left alert alert-success no-margin  col-sm-12">
								<i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
								温馨提示：请填写IAM用户信息    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a id="registerIam" onclick="getIamRegisterUrl()" href='#' title='点击创建IAM账号' target='_blank'>aws平台上创建IAM账号</a>
							</div>
						</div>
			            <div class="modal-body">
			          		  <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >AccountID:</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="accountID" name="accountID" maxlength="30"/>
								</div>
							 </div>
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >关联付款账户名:</label>
								<div class="col-sm-8">
									<select class="form-control" id="payAccountName" name="payAccountNames">
										<option value="-1">请选择关联付款账户名</option>
									</select>
								</div>
							 </div>
							<div class="form-group" id="payAccountDiv">
							 	<label class="col-sm-3 control-label no-padding-right"><img src="${pageContext.request.contextPath}/resource/images/circular.png"></img></label>
								<label class="control-label no-padding-right" style="margin-left:10px;">关联付款账户:<span id="payAccountID"></span></label>
								<input type="hidden" name="payAccountID" id="payAccountIdHid" />
								<input type="hidden" name="payAccountName" id="payAccountNameHid" />
							 </div>
							  <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >console登录(是/否):</label>
								<div class="col-sm-8" style="padding-top:6px;">
									<input type="checkbox" id="useType" checked="checked" class="ace ace-switch ace-switch-2" maxlength="20"/>
									<span class="lbl"></span>
									<input type="hidden" name="useType" id="isPayings" value="0" maxlength="20"/>
								</div>
							 </div>
							
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >IMA User:</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="IAMName" name="IAMName" maxlength="20"/>
								</div>
							 </div>
							
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >PassWord:</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="password" name="password" maxlength="20"/>
								</div>
							 </div>
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >PK:</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="accessKeyID" name="accessKeyID" maxlength="35"/>
								</div>
							 </div>
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >SK:</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="accessKey" name="accessKey" maxlength="50"/>
								</div>
							 </div>		
						</div>
			            <div class="modal-footer">
			            	<input type="hidden" name="id" id="id" />
			            	<input type="hidden" name="IAMId" id="IAMId" />
			            	<input type="hidden" name="copyAccountID" id="copyAccountID" />
			            	<input type="hidden" name="copyIamName" id="copyIamName" />
			                <button type="button" id="submitIamPoolData" class="btn btn-primary">提交</button>
			                <button type="button" id="editIamPoolData" class="btn btn-primary">提交</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
			        </div>
			       </div>
			   </div>
		</form>

		<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.serializejson.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
        <script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
        <script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.all.js"></script>
		<script src="${pageContext.request.contextPath}/resource/js/awsIAM/awsIamPool.js"></script><!-- 引入AWS IAM账户管理js -->
		
	</body>
</html>
