<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/pages/common/head.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/systemManage/dictionaryData.css" />
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
							<span>系统管理</span>
						</li>
						<li class="active">数据字典</li>
					</ul>
				</div>
				<div class="page-content" style="padding: 0px 20px 0px 2px;">
					<%@ include file="/WEB-INF/pages/common/setting.jsp" %>
					<div class="page-header" style="margin:0px 0px 0px;padding-bottom:4px;">
					   <section id="main-content">
          					<section class="wrapper">	
				              <div class="row">         
				                  <div class="col-lg-4">
				                      <section class="panel">
				                          <header class="panel-heading">数据字典列表  <a id="dicParentDialog" class="btn btn-info btn-sm" style="float:right;border-radius:4px;">
				                          	<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span> 添加</a></header>
				                          <div class="list-group" id="listGroup"></div>
				                      </section>
				                  </div> 
				                  <div class="table_content">
			                  		<div class="add tableTools-container">
										<a id="dicSubItemDialog" data-toggle="modal" class="btn btn-primary btn-sm" style="border-radius:4px;">
										<span class="ace-icon fa fa-plus icon-on-right bigger-110"></span> 新增</a>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<div class="table-header">数据字典列表</div>
											<table id="subItemTable" class="table table-striped table-hover"></table>
										</div>
									</div>			                  
			                 	 </div>			                 	                 
				              </div>				            
				              <!-- page end-->
				          </section>
				      </section>
				      	<input id="firstDicId" type="hidden" />		
					</div>
				</div><!-- /.page-content -->
			</div>
		</div><!-- /.main-content -->
		<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
	</div>
	<!-- 添加数据字典父项弹出框（Modal） -->
	<form class="form-horizontal" method="post" action="" role="form" id="" onsubmit="">
		<div class="modal fade" id="dicParentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 id="plusH" class="modal-title"><span class="ace-icon fa fa-plus icon-on-right bigger-110"></span> 添加父项字典</h4>
		                <h4 id="pencilH" class="modal-title"><span class="ace-icon fa fa-pencil icon-on-right bigger-110"></span> 编辑父项字典</h4>
		            </div>
		            <div class="modal-body">
						<div class="form-group ">
							<label class="control-label col-lg-2">英文名称:</label>
							<div class="col-lg-10">
								<input class="form-control" id="dicCode" name="dicCode" type="text" required />
							</div>
						</div>
						<div class="form-group ">
							<label  class="control-label col-lg-2">中文名称:</label>
							<div class="col-lg-10">
								<input class="form-control" id="dicName" name="dicName" type="text" required />
							</div>
						</div>
						<div class="form-group" id="notEmptyData" style="text-align:center;height:5px"></div>					
					</div>
		            <div class="modal-footer">
		            	<input id="copyDicName" type="hidden" />
		            	<input id="dicId" type="hidden" />
		                <button type="button" id="submitDic" class="btn btn-primary">提交</button>
		                <button type="button" id="editDic" class="btn btn-primary">提交</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		 </div>
	</form>
	
	<!-- 添加数据字典子项弹出框（Modal） -->
	<form class="form-horizontal" method="post" action="" role="form" id="" onsubmit="">
		<div class="modal fade" id="subItemModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 id="siplusH" class="modal-title"><span class="ace-icon fa fa-plus icon-on-right bigger-110"></span> 添加子项字典</h4>
		                <h4 id="sipencilH" class="modal-title"><span class="ace-icon fa fa-pencil icon-on-right bigger-110"></span> 编辑子项字典</h4>
		            </div>
		            <div class="modal-body">
						<div class="form-group ">
							<label  class="control-label col-lg-2">子项数值:</label>
							<div class="col-lg-10">
								<input class="form-control" id="itemValue" name="itemValue" type="text" required />
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-lg-2">子项名称:</label>
							<div class="col-lg-10">
								<input class="form-control" id="itemName" name="itemName" type="text" required />
							</div>
						</div>	
						<div class="form-group" id="notEmptySubItem" style="text-align:center;height:5px"></div>					
					</div>
		            <div class="modal-footer">
		            	<input id="subItemDicId" type="hidden" />
		            	<input id="copyItemName" type="hidden" />
		            	<input id="copyitemValue" type="hidden" />
		            	<input id="itemId" type="hidden" />
		                <button id="addSubItemSubmit"  type="button" class="btn btn-primary">提交</button>
		                <button id="editSubItemSubmit" type="button" class="btn btn-primary">提交</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		 </div>
	</form>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/assets/js/dataTables/jquery.dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/pulgins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/systemManage/dictionnaryData.js"></script><!-- 引入机构管理脚本 -->
</body>
</html>
