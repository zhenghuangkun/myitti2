<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
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
							<span>用户管理</span>
						</li>
						<li class="active">平台管理员</li>
					</ul>
				</div>

				<div class="page-content">
					<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
					<div class="page-header">
						<div class="widget-box">
							<div class="widget-header">
								<h4 class="widget-title">
									<i class="ace-icon fa fa-search icon-on-right bigger-110"> 搜索栏</i>
								</h4>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<form class="form-inline" role="form">
										<div class="form-group form-group-margin">
											<input type="text" class="form-control" id="searchData" name="searchData" maxlength="30"></input>
											<input type="hidden" class="form-control" id="inputUserStatus" value="1"></input>
										</div>
										<div class="form-group form-group-margin">
											<button type="button" class="btn btn-primary btn-sm" onclick="doSearch()">
												<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>搜索
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="add tableTools-container">
						<button type="button" id="showAddUserModel" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addUserModel">
							<span class="ace-icon fa fa-plus icon-on-right bigger-110"> 新增</span>
						</button>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="table-header">平台管理列表</div>
							<table id="userPdfTb" class="table table-striped table-hover"></table>
						</div>
					</div>
				</div>
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
	 <%@ include file="/WEB-INF/pages/common/footer.jsp" %>
  </div>
	
	<!-- 添加、编辑用户模态框（Modal） -->
	<form class="form-horizontal" method="post" action="" role="form" id="paltFormForm" >
		<div class="modal fade" id="addOrEditPaltFormModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 id="addh4" class="modal-title">添加平台用户信息</h4>
			            <h4 id="edith4" class="modal-title">编辑平台用户信息</h4>
		            </div>
		             <div class="clearfix">
							<div class="pull-left alert alert-success no-margin  col-sm-12">
								<i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
								温馨提示：<span style="color: red">*</span>&nbsp;&nbsp;表示为必填项
							</div>
						</div>
		            <div class="modal-body">
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >姓名：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<input type="text"  class="form-control" name="realName" placeholder="请填写真实姓名" id="realName" maxlength="20"/>
							</div>
						 </div>
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >机构名称：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<select id="schoolId" name="schoolId" class="form-control">
									<option value="-1">请选择机构名称</option>
								</select>
							</div>
							<div class="col-sm-1" style="padding-left:0px;padding-right:0px;">
								<%-- <a href="#" onclick="promptMessage();"><img src="${pageContext.request.contextPath}/resource/images/prompt.png"  title="点击提示" ></img></a> --%>
							</div>
						 </div>
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >学院：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<select  name="departmentId" class="form-control" id="departmentId">
									<option value="-1">请选择学院</option>
								</select>
							</div>
						 </div>	
						<!-- <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >绑定AwsAccount：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<select name="awsAccount" class="form-control" id="awsAccount">
									<option value="-1">请选择awsAccount账户</option>
								</select>
							</div>
						 </div>
						 <div class="form-group" id="awsAccountDiv">
						 	<label class="col-sm-3 control-label no-padding-right" ><img src="${pageContext.request.contextPath}/resource/images/circular.png"></img></label>
							<label class="col-sm-3 control-label" >AwsAccount账户:</label>
							<label class="col-sm-1 control-label"  id="awsAccountId"></label>
						 </div>
 						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >（是/否）绑定IAM：</label>
							<div class="col-sm-8" style="padding-top:6px;">
								<input type="checkbox" id="isAwsIam" class="ace ace-switch ace-switch-2"/>
								<span class="lbl"></span>
								<input type="hidden" name="isAwsIam" id="isAwsIams" value="1"/>
							</div>
						 </div> 
						 <div class="form-group" id="awsIamDiv">
							<label class="col-sm-3 control-label no-padding-right" >绑定Aws IAM：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<select name="IAM" class="form-control" id="IAM">
									<option value=" ">请选择AWS IAM账户</option>
								</select>
							</div>
						 </div>-->
						 
						  <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >用户角色：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<select name="roleId" class="form-control" id="roleId">
									<option value="-1">请选择对应用户角色</option>
								</select>
							</div>
						 </div>	
						 						 
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >电话号码：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="phoneNum" id="phoneNum" maxlength="20"/>
							</div>
						 </div>
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >email：&nbsp;<span style="color: red">*</span></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="email" id="email" maxlength="50" />
							</div>
						 </div>
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >出生年月：</label>
							<div class="col-sm-8">
								<input type="date"  class="form-control" name="birthday" id="birthday"/>
							</div>
						 </div>
						 <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" >地址：</label>
							<div class="col-sm-8">
								<input type="text"  class="form-control" name="address" id="address" maxlength="40" />
							</div>
						 </div>
					</div>
					<div class="modal-footer">
				     	<input type="hidden" name="userId" id="userId" />
				     	<input type="hidden" name="roleType" id="roleType" value='1' />
					    <input type="hidden" name="copyPhoneNum" id="copyPhoneNum" />
					    <input type="hidden" name="copyEmail" id="copyEmail" />
					    <input type="hidden" name="copyIamName" id="copyIamName" />
					    <input type="hidden" name="iamType" id="iamType" value="platform" />
						<button type="button" id="addUserData" class="btn btn-primary" >提交</button>
			            <button type="button" id="editUserData" class="btn btn-primary" >提交</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		 </div>
	  </form><!-- 用户详情 用户模态框（Modal）end -->
	<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.serializejson.js"></script>	  
	<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
    <script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/userManage/userManage.js"></script><!-- 引入平台管理脚本 -->
</body>
</html>

