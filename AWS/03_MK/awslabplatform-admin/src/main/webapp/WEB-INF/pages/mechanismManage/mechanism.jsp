<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/pulgins/zTree/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript">
	    $(function() {
	    	var height=window.screen.availHeight*0.68;
	    	$("#divHeight").height(height);
	    	$("#divContentHeight").height(height-50);
		});
	</script>
	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</head>

<body class="skin-1">
	<!-- 会发生错误，MessageContrller 的getMessage 函数出现错误。 -->
	<%@ include file="/WEB-INF/pages/common/header.jsp"%>
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
							<span>机构管理</span>
						</li>
						<li class="active">机构设置</li>
					</ul>
				</div>

				<div class="page-content" style="padding: 8px 20px 8px 8px;">
					<%@ include file="/WEB-INF/pages/common/setting.jsp" %>			
					<div class="row">
						<!-- 左边的content -->
						<div class="col-xs-12 col-sm-4 col-md-4" >
							<div class="col-xs-12 col-sm-12 col-md-12" id="divHeight" style="overflow:auto;background-color: #ecf1f4;;height:100%">
								<div class="search-filter-header bg-primary" style="margin-top:4px; padding-top: 1px;">
									<h5 class="smaller no-margin-bottom">
										<i class="ace-icon fa fa-sliders light-green bigger-120"></i>
										&nbsp; 机构名称
									</h5>
								</div>
								<a id="mechanismParentDialog" class="btn btn-info btn-sm" style="border-radius:4px;margin-top: 10px">
				                <span class="ace-icon fa fa-plus icon-on-right bigger-110"></span> 添加机构</a>				                      
								<div class="space-6"></div>																
								<div class="row">
									<ul id="mechanismTree" class="ztree" style="margin:2px 10px 2px 10px;border: 1px solid grey;height:455px;overflow:auto"></ul>
								</div>
							</div>		
						</div>
                        <!-- 右边的content -->	
						<div class="col-xs-12 col-sm-8 col-md-8" style="padding-left:5px;">
							<div class="row" >
								<div class="col-xs-12 col-sm-12 col-md-12" style="padding: 11px;background-color: #ecf1f4;height: 40px;max-height:100%">
									<i class="glyphicon glyphicon-leaf text-primary"></i>
									<label id="checkMechanism" style="font-size: 17px" class="text-primary"></label>
								</div>
							</div>									
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12" id="divContentHeight" style="max-height:100%;">
									<div class="row">
										<div class="col-xs-12 label label-lg label-info arrowed-in arrowed-right" style="width:98.5%">
											<b>详细信息</b>
										</div>
									</div>
									<div class="col-xs-6 col-sm-5 pricing-box" style="margin-top: 25px;float: left;">
										<div class="widget-box widget-color-orange">
											<div class="widget-header">
												<h5 class="widget-title bigger lighter">机构名称</h5>
											</div>
											<div class="widget-body" style="height: 300px;word-wrap:break-word; overflow:hidden;">
												<div class="widget-main" id="">
													<label id="mechanismNameData"></label>
												<hr />
											  </div>
											</div>
										</div>
									</div>
									<div class="col-xs-6 col-sm-5 pricing-box" style="float: right;margin-top: 25px;">
										<div class="widget-box widget-color-green">
											<div class="widget-header">
												<h5 class="widget-title bigger lighter">机构描述</h5>
											</div>
											<div class="widget-body" style="height: 300px;word-wrap:break-word; overflow:hidden;">
												<div class="widget-main">
													<label id="mechanismdDescribeData"></label>	
												<hr />	
												</div>
											</div>
										</div>
									</div>
							</div>
						</div>
					</div>
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->
     </div>
		<%@ include file="/WEB-INF/pages/common/footer.jsp" %>	
	</div>
	
	<!-- 添加机构名称父项的弹出框（Modal） -->
	<form class="form-horizontal" method="post" action="" role="form" id="" onsubmit="">
		<div class="modal fade" id="mechanismParentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 id="hA" class="modal-title"><span class="ace-icon fa fa-plus icon-on-right bigger-110"></span> 添加机构信息</h4>
		                <h4 id="hE" class="modal-title"><span class="ace-icon fa fa-pencil icon-on-right bigger-110"></span> 编辑机构信息</h4>
		            </div>
		            <div class="modal-body">
						<div class="form-group ">
							<label class="control-label col-lg-2">机构名称:</label>
							<div class="col-lg-10">
								<input class="form-control" id="mechanismName" name="mechanismName" type="text" required />
							</div>
						</div>
						<div class="form-group ">
							<label  class="control-label col-lg-2">机构描述:</label>
							<div class="col-lg-10">
								<input class="form-control" id="mechanismdDescribe" name="mechanismdDescribe" type="text" />
							</div>
						</div>
						<div class="form-group" id="notEmptyData" style="text-align:center;height:5px"></div>
					</div>
		            <div class="modal-footer">
		            	<input id="copyMechanismName" type="hidden" />
		            	<input id="mechanismPid" type="hidden" />
		            	<input id="mechanismId" type="hidden" />
		                <button type="button" id="submitParentMechanism" class="btn btn-primary">提交</button>
		                <button type="button" id="editMechanism" class="btn btn-primary">提交</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		 </div>
	</form>
	<!-- 添加新机构节点名称的弹出框（Modal） -->
	<form class="form-horizontal" method="post" action="" role="form" id="" onsubmit="">
		<div class="modal fade" id="addTreeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title"><span class="ace-icon fa fa-plus icon-on-right bigger-110"></span> 添加新节点</h4>
		            </div>
		            <div class="modal-body">
						<div class="form-group ">
							<label class="control-label col-lg-3">新节点名称:</label>
							<div class="col-lg-9">
								<input class="form-control" id="subItemName" name="subItemName" type="text" required />
							</div>
						</div>
						<div class="form-group ">
							<label  class="control-label col-lg-3">新节点描述:</label>
							<div class="col-lg-9">
								<input class="form-control" id="subItemDescribe" name="subItemDescribe" type="text" />
							</div>
						</div>
						<div class="form-group" id="notEmptySubItem" style="text-align:center;height:5px"></div>
						<input id="mechanismId" type="hidden" />
					</div>
		            <div class="modal-footer">
		                <button type="button" id="addSubItemSubmit" class="btn btn-primary">提交</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		 </div>
	</form>
</body>
	<script src="${pageContext.request.contextPath}/resource/assets/js/x-editable/bootstrap-editable.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/x-editable/ace-editable.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/zTree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/zTree/jquery.ztree.excheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pulgins/zTree/jquery.ztree.exedit.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/mechanismManage/mechanism.js"></script><!-- 引入机构管理脚本 -->
</html>
