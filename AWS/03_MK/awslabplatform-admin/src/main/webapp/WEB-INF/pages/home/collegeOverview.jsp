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
							<div class="widget-box">
								<div class="row clearfix">
								
									<div class="col-md-6 column">
										<div class="panel panel-danger">
											<div class="panel-heading">

												<h3 class="panel-title">
													可选择账单月份查询   已花费（元）   
												</h3>
											</div>
											<div style="height: 50px">
											<select class="form-control"  id="dateinfo" name="dateinfo">  
								                <option id="getCurrentMonth"></option>  
								            </select>  
											</div>

											<div class="panel-body" align="center" style="height: 90px">
												<img width="50px" alt="" src="${pageContext.request.contextPath}/resource/images/home/money.png"><br>
												<span id="costMoney"></span>（元）
											</div>
											<div class="panel-footer">

											</div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="panel panel-warning">
											<div class="panel-heading">
												<h3 class="panel-title">
													课程
												</h3>
											</div>
											<div class="panel-body" align="center" style="height: 140px">
											<img width="50px" alt="" src="${pageContext.request.contextPath}/resource/images/home/books.png"><br>
												已审核：<span id="approvedYes"></span> &nbsp;
												未审核：<span id="notAuditNo"></span>
											</div>
											<div class="panel-footer">
												 
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">教师课程数量排行Top10</div>
								<table id="teaSubjectTb" class="table table-striped table-hover">
							        <tr style="color: #104E8B">
							          <th>序号</th>
							          <th>老师姓名</th>
							          <th>发布课程 </th>
							          <th>学习人数</th>
							        </tr>
							      </table>
							</div>
						</div>	

					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>

		</div>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/extensions/Select/js/dataTables.select.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/pulgins/jquery.populateForm.js"></script>
        <script src="${pageContext.request.contextPath}/resource/assets/js/bootbox.min.js"></script>
        <script src="${pageContext.request.contextPath}/resource/js/userManage/overview.js"></script>
	</body>
</html>