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
								<span>概览</span>
							</li>
							<li class="active">资源概览</li>
						</ul>
					</div>

					<div class="page-content">
						<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
						<div class="page-header">						
							<div class="widget-box" style="width:250px;">								
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-inline" role="form">
											<div class="form-group form-group-margin" style="font-size:16px;font-weight:600">
												<h4 style="display:block;width:130px;float:left;">账单月份查询：</h4>
												<select style="margin:4px 0;" id="dateinfo" name="dateinfo">
													<option id="getCurrentMonth"></option>
												</select>
											</div>		
										</form>
									</div>
								</div>
							</div>
						</div>
						<div class="page-header">
								<div class="row clearfix">
									<div class="col-md-6 column">
										<div class="panel panel-danger">
											<div class="panel-heading">
												<h3 class="panel-title">
													可用额度（单位：元）
												</h3>
											</div>
											<div class="panel-body" align="center" style="height:90px;font-size:16px">
												<%-- <img width="50px" alt="" src="${pageContext.request.contextPath}/resource/images/home/money.png"><br>
												总额度：<span id="">10086</span> &nbsp;
												已花费：<span id="">899</span> --%>
												<div class="col-md-6">
													<div style="width: 23%;float:left;font-size:14px"><img width="40px" alt="" src="${pageContext.request.contextPath}/resource/images/home/money.png"><br>
													总额度</div>
													<div style="font-size:28px;width:75%;float:left;margin-top:10px;"><span id="aCombination"></span></div>
												</div>
												
												<div class="col-md-6">
													<div style="width: 23%;float:left;font-size:14px"><img width="40px" alt="" src="${pageContext.request.contextPath}/resource/images/home/money.png"><br>
													已花费</div>
													<div style="font-size:28px;width:75%;float:left;margin-top:10px;"><span id="hasCost"></span></div>
												</div>
											</div>

										</div>
									</div>
									<div class="col-md-6 column">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<h3 class="panel-title">
													已开发票（单位：元）
												</h3>
											</div>
											<div class="panel-body" align="center" style="height: 90px;font-size:16px">
												<%-- <img width="50px" alt="" src="${pageContext.request.contextPath}/resource/images/home/release.png"><br>
												已开发票金额：<span id="">658</span> &nbsp;
												未开发票金额：<span id="">241</span> --%>
												<div class="col-md-6">
													<div style="width: 36%;float:left;font-size:14px"><img width="40px" alt="" src="${pageContext.request.contextPath}/resource/images/home/release.png"><br>
													已开发票金额</div>
													<div style="font-size:28px;width:64%;float:left;margin-top:10px;"><span id="haveInvoice"></span></div>
												</div>
												
												<div class="col-md-6">
													<div style="width: 36%;float:left;font-size:14px"><img width="40px" alt="" src="${pageContext.request.contextPath}/resource/images/home/release.png"><br>
													未开发票金额</div>
													<div style="font-size:28px;width:64%;float:left;margin-top:10px;"><span id="notInvoice"></span></div>
												</div>
											</div>

										</div>
									</div>
									
								</div>						
						</div>
						
						<div class="page-header">							
							<h1>各学院账单明细</h1>	
							<div class="space" style="border:1px solid #cce2c1"></div>													
							<div class="row">
								<div class="col-xs-12" id="departmentsName">	
																


								</div>							
							</div>
						</div>
						
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>

		</div>
		<script src="${pageContext.request.contextPath}/resource/pulgins/jquery.serializejson.js"></script>	
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/extensions/Select/js/dataTables.select.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
        <script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/resource/pulgins/layui/layui.all.js"></script>
     	<script src="${pageContext.request.contextPath}/resource/js/userManage/overview.js"></script>

	</body>
</html>
