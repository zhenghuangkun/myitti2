<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<style type="text/css">
.table-head{margin-left: 10px;}  
.table-body{width:100%; height:300px;overflow-y:scroll;margin-left: 10px;}  
.table-head table,.table-body table{width:100%;}
.table-thead {
	background-color: transparent;
	border-color: grey;
	width: 100%;
	font-size: 14px;
	border-collapse: collapse;
	border-spacing: 0;
	background-color:#f2f2f2;
	line-height: 2.5;
}
		</style>
	</head>
	<body>
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row" >
						<div class="col-xs-12 col-sm-12 col-md-12" style="border-bottom:1px solid #A8D4F5;">
							<label style="font-size: 17px" class="text-primary">
								<i class="ace-icon fa fa-leaf"></i>
								<strong id="studentExperimentInfoTitleId">实验组信息</strong>
							</label>
						</div>
					</div>
					
					<div class="row" style="margin-top: 15px;">
						<div class="col-md-10">
							<div class="profile-user-info profile-user-info-striped">
							 	<div class="profile-info-row">
									<div class="profile-info-name"> 组名 </div>
									<div class="profile-info-value">
										<span>测试组</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 创建时间 </div>
									<div class="profile-info-value">
										<i class="fa fa-map-marker light-orange bigger-110"></i>
										<span>2018-04-04 12:10:20</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 创建者 </div>
									<div class="profile-info-value">
										<span>郑煌坤</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 组人数 </div>
									<div class="profile-info-value">
										<span>20人</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 关联课程数 </div>

									<div class="profile-info-value">
										<span>30</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 组成员信息 -->
					<div class="row" >
						<div class="col-xs-12 col-sm-12 col-md-12" style="border-bottom:1px solid #A8D4F5;margin-top: 20px;">
							<label style="font-size: 17px" class="text-primary">
								<i class="ace-icon fa fa-leaf"></i>
								<strong id="studentExperimentInfoTitleId">组成员信息</strong>
							</label>
						</div>
					</div>
					
					<div class="row" style="width:83.3%;margin-left: 10px;margin-top: 20px;">
						
						<table class="table table-hover" style="width:98%;">
							<colgroup>
								<col width="14%">
								<col width="20%">
								<col width="25%">
								<col width="15%">
								<col width="14%">
								<col width="12%">
							</colgroup>
							<thead>
								<tr>
									<th>姓名</th>
									<th>学号</th>
									<th>院系</th>
									<th>专业</th>
									<th>年级</th>
									<th>班级</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
								<tr>
									<td>郑煌坤</td>
									<td>20210864307</td>
									<td>计算机学院</td>
									<td>嵌入式专业</td>
									<td>大二</td>
									<td>1班</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div><!-- /.page-content -->
			</div><!-- /.main-content-inner -->
		</div><!-- /.main-content -->
	</body>
</html>