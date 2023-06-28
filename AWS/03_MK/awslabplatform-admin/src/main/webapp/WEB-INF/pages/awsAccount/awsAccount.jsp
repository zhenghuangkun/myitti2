<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/layui/css/layui.css" />
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
							<li class="active">平台AWS账号池</li>
						</ul>
					</div>
					<div class="page-content">
						<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
						<div class="page-header">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="widget-title">
										<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>搜索栏
									</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="row">
											<div class="col-xs-12">
												<form class="form-inline" role="form" >
													<div class="form-group" style="padding-left: 30px;">
														<label>付款账号：</label>
														<input type="text" class="form-control" id="inputUnitName" maxlength="30"></input>
													</div>
													<div class="form-group" style="padding-left: 30px;">
														<label>付款账号名称：</label>
														<input type="text" class="form-control" id="inputUserRealName" maxlength="30"></input>
													</div>
													<div class="form-group" style="padding-left: 30px;">
														<label>所属机构：</label>
														<select class="form-control" id="inputState">
															<option value="-1">请选择所属机构</option>
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
							<button id="addModalShow" type="button" class="btn btn-primary btn-sm">
								<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span>新增
							</button>
							<div class="layui-inline" style="width:250px;float:right;">
							 	<a href="${pageContext.request.contextPath}/resource/excelModel/accountModel.xls" class="btn btn-info btn-sm"><i class='ace-icon fa fa-hand-o-down bigger-120'> </i>下载模板</a>
						   		<a class="btn btn-success btn-sm" id="uploadExcel"><i class='ace-icon fa fa-hand-o-up bigger-120'> </i>批量导入</a>
				   			 </div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">平台AWS账号列表</div>
								<table id="awsAccountTb" class="table table-striped table-hover"></table>
							</div>
						</div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		</div>
		
		<form class="form-horizontal" method="post"  role="form" id="awsAccountForm" onsubmit="return ">
			<div class="modal fade" id="awsAccountModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 id="addh4" class="modal-title">添加平台AWS账号信息</h4>
			                <h4 id="edith4" class="modal-title">编辑平台AWS账号信息</h4>
			            </div>
			            
			            <div class="clearfix">
							<div class="pull-left alert alert-success no-margin col-sm-12">
								<i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
								温馨提示：账户需要到<a href='https://www.amazonaws.cn/sign-up/' title='点击创建账户' target='_blank'>aws平台创建</a>、创建之后再填写以下信息.
							</div>
						</div>
			            <div class="modal-body">
			          		  <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >付款账号：</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="accountId" name="accountId" maxlength="30"/>
								</div>
							 </div>
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >付款账号名称：</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="accountName" name="accountName" maxlength="20"/>
								</div>
							 </div>
							 <div class="form-group" style="display:none">
								<label class="col-sm-3 control-label no-padding-right" >关联邮箱：</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="email" name="email" maxlength="20">
								</div>
							 </div>
							
							 <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >所属学校(机构)：</label>
								<div class="col-sm-8">
									<select  class="selector" name="org" id="org" style="width:100%">
										<option value="-1">请选择所属学校(机构)</option>
									</select>
								</div>
							 </div>
							 <div class="form-group" id="departmentIdDiv">
								<label class="col-sm-3 control-label no-padding-right" >所属院系：</label>
								<div class="col-sm-8">
									<select class="selector" name="departmentId" id="departmentId" style="width:100%">
										<option value=" ">请选择所属院系</option>
									</select>
								</div>
							 </div>
							  <div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" >UPT所有(是/否)：</label>
								<div class="col-sm-8" style="padding-top:6px;">
									<input type="checkbox" id="isUPT" checked="checked" class="ace ace-switch ace-switch-2" maxlength="20"/>
									<span class="lbl"></span>
									<input type="hidden" name="isUPT" id="isPayings" value="1" maxlength="20"/>
								</div>
							 </div>
							 <div class="form-group" id="" >
								<label class="col-sm-3 control-label no-padding-right" >IAM User：</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="IAM" name="IAM" />
								</div>
							 </div>	
							 <div class="form-group" id="" >
								<label class="col-sm-3 control-label no-padding-right" >AK：</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="AK" name="AK" />
								</div>
							 </div>	
							 <div class="form-group" id="" >
								<label class="col-sm-3 control-label no-padding-right" >SK：</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="SK" name="SK" />
								</div>
							 </div>	
							 <div class="form-group" id="payingAccountIdDiv" style="display:none">
								<label class="col-sm-3 control-label no-padding-right" >付款账户：</label>
								<div class="col-sm-8">
									<select class="selector" name="payingAccountId" id="payingAccountId" style="width:100%">
										<option value=" ">请选择付款账户</option>
									</select>
								</div>
							 </div>
							 <div class="form-group" id="payingAccountNameDiv" style="display:none">
								<label class="col-sm-3 control-label no-padding-right" >付款账户名称：</label>
								<div class="col-sm-8">
									<input type="text"  class="form-control" id="payingAccountName" name="payingAccountName" readonly="readonly"/>
								</div>
							 </div>						
						</div>
			            <div class="modal-footer">
			            	<input type="hidden" name="id" id="id" />
			            	<input type="hidden" name="copyAccountId" id="copyAccountId" />
			            	<input type="hidden" name="copyAccountName" id="copyAccountName" />
			                <button type="button" id="submitAccountData" class="btn btn-primary" >提交</button>
			                <button type="button" id="editAccountData" class="btn btn-primary" >提交</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
			        </div><!-- /.modal-content -->
			   </div><!-- /.modal -->
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
		<script src="${pageContext.request.contextPath}/resource/js/awsAccount/awsAccount.js"></script><!-- 引入AWS account账户管理js -->
		
	    
	</body>
</html>
