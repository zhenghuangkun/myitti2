<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
	<title>个人信息</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/layui/css/layui.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/user.css" media="all">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
	<!-- 上传头像 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/amazeui/css/amazeui.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/amazeui/css/amazeui.cropper.css">

	<script src="${pageContext.request.contextPath }/resource/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/plugins/layer/layer.js" ></script>
	<script src="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resource/plugins/layui/layui.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/zui.min.css">
	<script src="${pageContext.request.contextPath }/resource/js/zui.min.js"></script>
	<!-- 上传头像 -->
        <script src="${pageContext.request.contextPath }/resource/plugins/amazeui/js/amazeui.min.js" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath }/resource/plugins/amazeui/js/cropper.min.js" charset="utf-8"></script>
		<style>
			.up-frame-bj .up-frame-radius{padding-bottom: 20px;border-radius: 5px;}
			.up-frame-parent .up-frame-header{height: 50px;padding: 0px;line-height: 50px;border-bottom:solid 1px #e5e5e5;}
			.up-frame-parent .up-frame-header label{font-size: 16px;float: left;margin-left: 14px;color: #808080;}

			.up-frame-parent .up-frame-body{padding:20px;}
			.up-frame-parent .up-pre-before {width:327px;height: 327px;padding:0px;float: left;background: #fcfcfc;border: 1px solid #e3e3e3;}
			.up-frame-parent .up-pre-before img{width: 100%;}

			.up-frame-parent .up-frame-body .up-pre-after{background: #fcfcfc;overflow: hidden;width: 186px;height: 186px;border: 1px solid #e3e3e3;float: left;margin-left: 25px;}

			.up-frame-parent .up-control-btns{width: 150px;height:30px;margin-left: auto;margin-right: auto;background: #dddddd;text-align: center;line-height: 30px; margin-top: 30px;}
			.up-frame-parent .up-control-btns span{width: 30%;}

			@media screen and (max-width: 1024px){
				.up-frame-parent .up-pre-before {width: 100%;}
				.up-frame-parent .up-frame-body .up-pre-after{margin-left: 0px;margin-top: 10px;}
			}
		</style>
</head>
<body style="background-color: #F9F9F9">
<div class="layui-layout layui-layout-admin">
	<%@include file="head.jsp" %>

<div class="layui-container" style="margin-top:70px;">
	<!-- 头像 -->
	<div class="main-header" style="height:200px;background-color:#ffffff;background:url(${pageContext.request.contextPath}/resource/images/person1.png)">
        <div class="container">
            <div class="row">
                <div class="col-sm-12" style="margin-top:50px" id="changeimg">
                	<c:if test="${sessionScope.currentUser.picFileUrl==null}">
	                    <a style="display:block;width: 100px;margin:0 auto">
	                    	<img class="am-circle" id="userDefaultPic" alt="点击图片上传" src="${pageContext.request.contextPath }/resource/images/head.jpg">
	                    	<span>点击更换头像</span>
	                    </a>
                    </c:if>
                    <c:if test="${sessionScope.currentUser.picFileUrl!=null}">
	                    <a style="display:block;width: 100px;margin:0 auto">
	                    	<img class="am-circle" id="userDefaultPic" alt="点击图片上传" src="${sessionScope.currentUser.picFileUrl}">
	                    	<span>点击更换头像</span>
	                    </a>
                    </c:if>
                </div>
                <!--图片上传框-->
				<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
					<div class="am-modal-dialog up-frame-parent up-frame-radius">
						<div class="am-modal-hd up-frame-header">
							<label>修改头像</label>
							<a href="javascript: void(0)" class="am-close am-close-spin" id="closemodel" data-am-modal-close>&times;</a>
						</div>
						<div class="am-modal-bd  up-frame-body">
							<div class="am-g am-fl">
								<div class="am-form-group am-form-file">
									<div class="am-fl">
										<button type="button" class="am-btn am-btn-default am-btn-sm">
										<i class="am-icon-cloud-upload"></i>选择要上传的文件</button>
									</div>
									<input type="file" id="inputImage">
								</div>
							</div>
							<div class="am-g am-fl" >
								<div class="up-pre-before up-frame-radius">
						      		<img alt="" src="" id="image" >
						      	</div>
								<div class="up-pre-after up-frame-radius"></div>
							</div>
							<div class="am-g am-fl">
								<div class="up-control-btns">
					    			<span class="am-icon-rotate-left"  onclick="rotateimgleft()"></span>
					    			<span class="am-icon-rotate-right" onClick="rotateimgright()"></span>
					    			<span class="am-icon-check" id="up-btn-ok" url=""></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--图片上传框end-->
				<!--加载框-->
		    	<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
				  <div class="am-modal-dialog">
				    <div class="am-modal-hd">正在上传...</div>
				    <div class="am-modal-bd">
				      <span class="am-icon-spinner am-icon-spin"></span>
				    </div>
				  </div>
				</div>

				<!--警告框-->
				<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
				  <div class="am-modal-dialog">
				    <div class="am-modal-hd">信息</div>
				    <div class="am-modal-bd"  id="alert_content">
				              成功了
				    </div>
				    <div class="am-modal-footer">
				      <span class="am-modal-btn" id="am-modal-btn">确定</span>
				    </div>
				  </div>
				</div>

            </div>
        </div>
    </div>
    <!-- 导航栏 -->
    <nav class="main-navigation">
        <div class="container">
            <div class="row" style="height:45px">
                <div class="col-sm-12">
                    <div class="collapse navbar-collapse" id="main-menu">
                        <ul class="menu">
        					<li id="switchinfor"><a name="select" href="#information" class="active" onclick="show('#information')">基本信息</a></li>
							<li id="switchpwd"><a name="select" href="#password" onclick="show('#password')">账号密码</a></li>
							<li id="switchcour"><a name="select" href="#mycourse" onclick="show('#mycourse')">我的课程</a></li>
							<li id="switchreport"><a name="select" href="#report" onclick="show('#report')">实验报告</a></li>
						</ul>
						<div class="switch_bottom" id="switch_bottom"
						style="position: absolute; width: 120px;left: 345px;border: 2px solid #0b87f0;">
                    	</div>
                	</div>
            	</div>
        	</div>
        </div>
    </nav>

	<div class="content-wrap" style="background-color:#fff;margin-top:15px;">
		<!-- <div class="container">
			<div class="row"> -->
				<div class="column content switch" style="float:none;display: block;margin-left: auto;margin-right: auto">

					<div id="information" class="layui-row layui-col-space10">
						<div class="column content" style="float:none;display: block;margin-left: auto;margin-right: auto">
							<table class="table table-hover" style="border-color:#e5e5e5">
								<tbody>
								<tr>
									<td style="color:#9f9f9f;text-align:center">学号</td>
									<td id="stuId">${sessionScope.currentUser.stuId}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">姓名</td>
									<td id="realName">${sessionScope.currentUser.realName}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">学校</td>
									<td id="schoolId">${sessionScope.currentUser.schoolName}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">院系</td>
									<td id="mechanismId">${sessionScope.currentUser.mechanismName}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">专业</td>
									<td id="major">${sessionScope.currentUser.majorName}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">年级</td>
									<td id="grade">${sessionScope.currentUser.gradeName}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">班级</td>
									<td id="classes">${sessionScope.currentUser.className}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">出生年月</td>
									<td id="birthday">${sessionScope.currentUser.birthday}</td>
								</tr>
								<tr>
									<td style="color:#9f9f9f;text-align:center">住址</td>
									<td id="address" style="width:462px;">${sessionScope.currentUser.address}</td>
								</tr>
								</tbody>
							</table>
							<div class="col-md-8 col-sm-offset-2 text-center">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#myModal" style="">修改信息
								</button>

							<div class="modal fade" id="myModal">
							<div class="modal-dialog ">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
										</button>
										<h4 class="modal-title">修改个人信息</h4>
									</div>
									<div class="modal-body">
										<table class="table table-hover">
											<tbody>
											<tr>
												<td>出生年月</td>
												<td>
													<input class="layui-input" value="${sessionScope.currentUser.birthday}"
													 id="editBirthday" name="birthday">
												     <!--  <div class="layui-input-inline">
												        <input type="text" class="layui-input" id="test3" placeholder="yyyy-MM">
												      </div> -->
												</td>
											</tr>
											<tr>
												<td>住址</td>
												<td><input class="layui-input" value="${sessionScope.currentUser.address}"
												 id="editAddress" name="address" maxlength="50"  placeholder="最多不超过50个字符"></td>
											</tr>
											</tbody>
										</table>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭
										</button>
										<button type="button" class="btn btn-default"
												 id="editUser">保存
										</button>
									</div>
								</div>
							</div>
						</div>
							</div>
						</div>
					</div>
					<!-- 账号密码 -->
					<div id="password" style="display: none" class="layui-row layui-col-space10">
						<div class="column content" style="float:none;display: block;margin-left: auto;margin-right: auto">
							<!-- 修改手机 -->
							<div class="layui-collapse" lay-accordion="">
								  <div class="layui-colla-item">
								  	<span class="editname">手机</span>
								  	<span class="editnum">13364545395</span>
								    <span class="layui-colla-title editpwd">点击修改</span>
								    <div class="layui-colla-content">
								    	<!-- <div id="oldphone" style="display:">
									    	<div style="width:300px;margin:10px auto">
												<span style="display:inline-block;padding-top: 18px;width:55px;">旧手机号</span>
												<input style="width:200px;display:inline" name="phone" lay-verify="required|phone" autocomplete="off"
														   class="layui-input" type="tel" readonly="readonly">
											</div>
											<div style="width:300px;margin:10px auto">
												<span style="display:inline-block;padding-top:18px;width:55px;">验证码</span>
												<input style="width:200px;display:inline" class="layui-input" placeholder="请输入验证码">
											</div>
											<div style="width:265px;margin:10px auto">
												<button class="layui-btn layui-btn-normal">发送验证码</button>
												<button class="layui-btn layui-btn-normal" id="changephone">保存</button>
												<button class="layui-btn layui-btn-primary">取消</button>
											</div>
								    	</div> -->
								    	<div id="newphone" style="display:">
									    	<div style="width:300px;margin:10px auto">
												<span style="display:inline-block;padding-top: 18px;width:55px;">新手机号</span>
												<input style="width:200px;display:inline" name="phone" lay-verify="required|phone" autocomplete="off"
														   class="layui-input" type="tel">
											</div>
											<div style="width:300px;margin:10px auto">
												<span style="display:inline-block;padding-top:18px;width:55px;">验证码</span>
												<input style="width:200px;display:inline" class="layui-input" placeholder="请输入验证码">
											</div>
											<div style="width:265px;margin:10px auto" style="display:none">
												<button class="layui-btn layui-btn-normal">发送验证码</button>
												<button class="layui-btn layui-btn-normal" id="">保存</button>
												<button class="layui-btn layui-btn-primary">取消</button>
											</div>
										</div>
								    </div>
								  </div>
							</div>
							<!-- 修改邮箱-->
							<div class="layui-collapse" lay-accordion="">
								<div class="layui-colla-item">
								  	<span class="editname">邮箱</span>
								  	<span class="editnum" id="oldemail">${sessionScope.currentUser.email}</span>
								    <span class="layui-colla-title editpwd">点击修改</span>
								    <div class="layui-colla-content">
<%-- 								    	<div id="oldemail" style="display:">
									    	<div style="width:300px;margin:10px auto">
												<span style="display:inline-block;padding-top: 18px;width:55px;">旧邮箱号</span>
												<input style="width:200px;display:inline" name="email" value="${sessionScope.currentUser.email}"
														   class="layui-input" type="email" readonly="readonly">
											</div>
											<div style="width:300px;margin:10px auto">
												<span style="display:inline-block;padding-top:18px;width:55px;">验证码</span>
												<input style="width:200px;display:inline" class="layui-input" placeholder="请输入验证码">
											</div>
											<div style="width:265px;margin:10px auto">
												<button class="layui-btn layui-btn-normal">发送验证码</button>
												<button class="layui-btn layui-btn-normal" id="changeemail">保存</button>
												<button class="layui-btn layui-btn-primary">取消</button>
											</div>
								    	</div> --%>
								    	<div id="newemail" style="display:">
									    	<div style="width:300px;margin:10px auto">
												<span style="display:inline-block;padding-top: 18px;width:55px;">新邮箱号</span>
												<input style="width:200px;display:inline" name="email" id="email"
														   class="layui-input" type="email">
												<span id="newEmailIco"></span>
											</div>
											<div style="width:300px;margin:10px auto">
												<span  style="display:inline-block;padding-top:18px;width:55px;">验证码</span>
												<input id="emailCode" style="width:200px;display:inline" class="layui-input" placeholder="请输入验证码">
												<span id="emailCodeIco"></span>
											</div>
											<div style="width:200px;margin:10px auto" style="display:none">
												<button id="verCode" type="button" class="layui-btn layui-btn-normal">发送验证码</button>
												<button class="layui-btn layui-btn-normal" id="emailSave" type="submit" onclick="checkEmailCode()">保存</button>
											</div>
										</div>
								    </div>
								</div>
							</div>
							<!-- 修改密码-->
							<div class="layui-collapse" lay-accordion="">
								  <div class="layui-colla-item">
								  	<span class="editname">密码</span>
								  	<span class="editnum">******</span>
								    <span class="layui-colla-title editpwd">点击修改</span>
								    <div class="layui-colla-content">
								    	<div style="width:400px;margin:10px auto">
											<span style="display:inline-block;padding-top: 18px;width:55px;">旧密码</span>
											<input style="width:180px;display:inline" name="oldPwd" id="oldPwd"
													   class="layui-input" type="password">
											<span id="oldPwdIco"></span>
										</div>
										<div style="width:400px;margin:10px auto">
											<span style="display:inline-block;padding-top: 18px;width:55px;">新密码</span>
											<input style="width:180px;display:inline" name="newPwd" id="newPwd"
													   class="layui-input" type="password">
											<span id="newPwdIco"></span>
										</div>
										<div style="width:400px;margin:10px auto">
											<span style="display:inline-block;padding-top: 18px;width:55px;">确认密码</span>
											<input style="width:180px;display:inline" name="confirmPwd" id="confirmPwd"
													   class="layui-input" type="password">
											<span id="confirmPwdIco"></span>
										</div>
										<div style="width:145px;margin:0 auto">
											<button class="layui-btn layui-btn-normal" id="savePwd">保存</button>											
										</div>
								    </div>
								  </div>
							</div>


						</div>
					</div>
					<!-- 我的课程 -->

					<div id="mycourse" style="display: none" class="layui-row">
						<br>
						<c:forEach items="${myCourseList}" var="myCourse">
						<section class="courses-list-wrapper list-wrapper-1">
							<div class="courses-list-item">
								<div class="row">
									<div class="course-img col-md-4">
										<a href="${pageContext.request.contextPath }/coursedetail?courseId=${myCourse.courseId }" title="" style="text-decoration: none;">
											<img alt=""
												 src="${myCourse.coursePic}">
										</a>
										<div class="course-progress-bar">
											<div class="progress-bar  progress-bar-success"
												 role="progressbar" aria-valuenow="60" aria-valuemin="0"
												 aria-valuemax="100" style="width: ${myCourse.lastExperimentNo/myCourse.experimentCount*100 }%;"></div>
										</div>
									</div>
									<div class="course-detail col-md-8">
										<p class="course-name">
											<a href="${pageContext.request.contextPath }/coursedetail?courseId=${myCourse.courseId }"title=""> ${myCourse.courseName } </a>
										</p>
										<p class="last-learn" style="">上次学到：${myCourse.lastExperimentName }(${myCourse.lastExperimentNo }/${myCourse.experimentCount })</p>
										<p class="course-relate-data">
											<a href="${pageContext.request.contextPath }/coursedetail?courseId=${myCourse.courseId }" class="btn btn-primary pull-right"
											   title="">继续学习</a>
										</p>
									</div>
								</div>
							</div>
							<div class="lab-out-wrapper lab-reports-wrapper">
								<div class="row"></div>
							</div>
							<div class="lab-out-wrapper lab-questions-wrapper">
								<ul class="row question-items">
								</ul>
							</div>
						</section>
						</c:forEach>

					</div>

					<!-- 实验报告 -->

					<div id="report" style="display: none;"
					 class="layui-row layui-col-space10">
					<br>
					<div class="layui-row layui-col-space10" style="width:860px">
						<c:forEach items="${reportList}" var="report"> 
							<div style="float: left; width:212px;"
								 align="center">
								<div style="position: relative; border:1px solid #d2d2d2;border-radius:5px;height:300px ">
									<p style="font-weight:700;margin:10px 0;">${report.experimentName }</p>
									<hr style="width: 150px;border-top: 2px solid red;" />
									<div style="height: 100px ; ">
										<p style="font-size:12px ">实验日期</p>
										<p style="font-size:12px"><fmt:formatDate value="${report.createTime }" pattern="yyyy-MM-dd HH:mm"/></p>
									</div>
									<c:if test="${!empty report.score }">
										<span >实验分数: </span>
										<span  style="font-size:28px;color:#eb6100">${report.score }</span>
										<p style="font-size:12px"><fmt:formatDate value="${report.remarkTime }" pattern="yyyy-MM-dd HH:mm"/> </p>
									</c:if>
									<!-- 查看详情 相对于父级元素布局  居于底部-->
									<div  style="position: absolute; bottom:10px;  left:50%; margin-left:-60px;">
										<div  style="display: none">${report.experimentName}</div>
										<div  style="display: none">${report.content}</div>
										<div  style="display: none">${report.comment}</div>
										<button type="button" class="btn btn-primary" style="width:120px;height:32px"
												onclick="javaScript:showReport(this)">查看
										</button>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var contentPath = '${pageContext.request.contextPath}';

	$("#changeimg").click(function(){
	$("#doc-modal-1").modal({width:'600px'});
        });

	$("#am-modal-btn").click(function(){
		$("#closemodel").click();
		window.location.reload();
	})

	$(function () {
		editUser();
	})
		var $image = $('#image');
		$image.cropper({
	        aspectRatio: '1',
	        autoCropArea:0.8,
	        preview: '.up-pre-after',

	    });

	    // 上传图片
	    var $inputImage = $('#inputImage');
	    var URL = window.URL || window.webkitURL;
	    var blobURL;

	    if (URL) {
	        $inputImage.change(function () {
	            var files = this.files;
	            var file;

	            if (files && files.length) {
	               file = files[0];

	               if (/^image\/\w+$/.test(file.type)) {
	                    blobURL = URL.createObjectURL(file);
	                    $image.one('built.cropper', function () {
	                        // Revoke when load complete
	                       URL.revokeObjectURL(blobURL);
	                    }).cropper('reset').cropper('replace', blobURL);
	                    $inputImage.val('');
	                } else {
	                	layer.alert('请选择一个图像文件。',{
	                		title: "提示"
	                	});
	                }
	            }

	        });
	    } else {
	        $inputImage.prop('disabled', true).parent().addClass('disabled');
	    }

	    //绑定上传事件
	    $('#up-btn-ok').on('click',function(){
	    	var $modal = $('#my-modal-loading');
	    	var $modal_alert = $('#my-alert');
	    	var img_src=$image.attr("src");
	    	if(img_src==""){
	    		set_alert_info("没有选择上传的图片");
	    		$modal_alert.modal();
	    		return false;
	    	}

	    	$modal.modal();

	    	var url=$(this).attr("url");
	    	var canvas=$("#image").cropper('getCroppedCanvas');
	    	var data=canvas.toDataURL(); //转成base64
	        $.ajax( {
	                url:'${pageContext.request.contextPath}/user/uploadStuFace',
	                dataType:'json',
	                type: "POST",
	                traditional: true,//后台接收数组
	                data: {"image":data.toString()},
	                success: function(data){
	                	$modal.modal('close');
	                	set_alert_info(data.result);
	                	$modal_alert.modal();
	                	if(data.resultFlag){
	                		$("#userDefaultPic").attr("src", data.url);
	                	}
	                },
	                error: function(){
	                	/*$modal.modal('close');
	                	set_alert_info("上传文件失败了！");
	                	$modal_alert.modal();
	                	//console.log('Upload error');  */
	                }
	         });
	    });



		function rotateimgright() {
		$("#image").cropper('rotate', 90);
		}


		function rotateimgleft() {
		$("#image").cropper('rotate', -90);
		}

		function set_alert_info(content){
			$("#alert_content").html(content);
		}


	/**
	*选择日期的年选择器 
	*/
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		//年月选择器
		laydate.render({
		elem: '#editBirthday',
		theme: '#0b87f0',
		type: 'month'
		});
	})

	/** 
	*标题底下游标  
	*/
 	$(document).ready(function() {
 		$("#switchpwd").click(function(){
 			$('#switch_bottom').animate({left:'468px'})
 		});
 		$("#switchcour").click(function(){
 			$('#switch_bottom').animate({left:'592px'})
 		});
 		$("#switchreport").click(function(){
 			$('#switch_bottom').animate({left:'715px'})
 		});
 		$("#switchinfor").click(function(){
 			$('#switch_bottom').animate({left:'345px'})
 		});
	})

	/**
	*改个人信息 
	*/
    function editUser(){
 		$("#editUser").click(function() {
 			var birthday = $("#editBirthday").val();
 			var address = $("#editAddress").val();
 			$.ajax({
 				url: '${pageContext.request.contextPath}/user/editUserInfo',
 				async : false,
 				type : 'post',
 				dataType: "json",
 				data: {"birthday":birthday,"address":address},
 				success: function (data) {
 					if(data.resultFlag){
 						layer.alert(data.message,{
 	           			 title: "提示"
 			            });
 						  window.location.reload();
 					}
 			}})
 		});

	}

	/* ul切换 */
	function show(arg) {
		$("a[name='select']").removeClass("active");
		$("div[class='column content switch']>div").attr("style", "display:none");
		$(arg).attr("style", "display:");
	}

	/* 判断邮箱是否为空 */
 	document.getElementById("verCode").onclick = function () {
		if ("" == $("#email").val()) {
			$("#newEmailIco").css('display','inline-block');
			$("#newEmailIco").html('<i class="warn"></i>邮箱不能为空！');
			return false;
		}else if($("#email").val()==$("#oldemail").html()){
			$("#newEmailIco").css('display','inline-block');
			$("#newEmailIco").html('<i class="warn"></i>请不要填写旧邮箱！');
			return false;
		}else{
			var emailFormat = myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
			var strEmail = emailFormat.test($("#email").val());
			if (!strEmail) {
                $("#newEmailIco").css('display','inline-block');
    			$("#newEmailIco").html('<i class="warn"></i>邮箱格式不正确，请重新输入！！');
                return false;
     		}else{
     			sendEmailCode()
				time(this);
				/* $("#emailSave").attr("class","layui-btn layui-btn-normal"); */
			}
		}
	
	
		}

	/**
	*修改邮箱判断新邮箱 
	*/
 	$('#email').blur(function(){
		var email=$("#email").val();
		var emailFormat = myReg=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
		var strEmail = emailFormat.test($("#email").val());
		if(email==""){
			$("#newEmailIco").css('display','inline-block');
			$("#newEmailIco").html('<i class="warn"></i>邮箱不能为空！');
		}else if(!strEmail){
			$("#newEmailIco").css('display','inline-block');
			$("#newEmailIco").html('<i class="warn"></i>邮箱格式不正确！');
		}else{
			$("#newEmailIco").css('display','none');
		}
	})

/* 	$('#emailCode').blur(function(){
		var emailCode=$("#emailCode").val();
		if(emailCode==""){
			$("#emailCodeIco").css('display','inline-block');
			$("#emailCodeIco").html('<i class="warn"></i>验证码不能为空！');
		}else{
			$("#emailCodeIco").css('display','none');
		}
	}) */

	/* 发送邮箱 */
	function sendEmailCode() {
		var email = $("#email").val();
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/user/sendEditEmailVerCode',
			data: {Email:email},
			dataType:"json",
			success: function (data) {

			}
		});
	}

	/* 修改邮箱 */
	function checkEmailCode(){
		var email = $("#email").val();
		var emailCode = $("#emailCode").val();
		if ("" == email) {
			$("#newEmailIco").css('display','inline-block');
			$("#newEmailIco").html('<i class="warn"></i>请填写邮箱！');
			return false;}
		else if(email==$("#oldemail").html()){
			$("#newEmailIco").css('display','inline-block');
			$("#newEmailIco").html('<i class="warn"></i>请不要填写旧邮箱！');
			return false;}
		else if(emailCode==""){
			$("#emailCodeIco").css('display','inline-block');
			$("#emailCodeIco").html('<i class="warn"></i>请输入验证码！');
			return false;}
		$.ajax({
			type: "POST",
            url: "${pageContext.request.contextPath}/user/editUserEmail",
            data: {newEmail:email,verCode:emailCode},
            dataType: "json",
            success: function(data){
            	if(data.resultFlag){
            		setTimeout(function(){//两秒后跳转
                        alert(data.message);
                        location.href="${pageContext.request.contextPath}/login";//PC网页式跳转
                     },2000);}
            	else{
            		layer.alert(data.message,{
            			icon: 5,
	            		title: "提示"
	            	});
            	}
			}
		})
		
	}


	var wait = 60;
	function time(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.innerHTML = "发送验证码"
			// o.value = "发送验证码";
			wait = 60;
		} else {
			o.setAttribute("disabled", true);
			o.innerHTML = "重新发送(" + wait + ")";
			// o.value = "重新发送(" + wait + ")";
			wait--;
			setTimeout(function () {
						time(o)
					},
					1000)
		}
	}

	/* 修改密码旧密码框 */
	$('#oldPwd').blur(function(){
		var oldPwd=$("#oldPwd").val();
		if(oldPwd==""){
			$("#oldPwdIco").css('display','inline-block');
			$("#oldPwdIco").html('<i class="cuo"></i>旧密码不能为空！');
		}else{
			$("#oldPwdIco").css('display','none');
		}
	})

	/* 修改密码新密码框 */
 	$('#newPwd').blur(function(){
 		var newPwd=$("#newPwd").val();
 		var regex = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,30}/;
 		if(newPwd==''){
 			$("#newPwdIco").css('display','inline-block');
 			$("#newPwdIco").html('<i class="cuo"></i>新密码不能为空！');
 		}else if(!newPwd.match(regex)){
 			$("#newPwdIco").css('display','inline-block');
 			$("#newPwdIco").html('<i class="warn"></i>密码必须由6-30位字母、数字、特殊字符构成!');
 		}else{
 			$("#newPwdIco").css('display','none');
 		}
	})

	/* 修改密码确认密码框 */
	$('#confirmPwd').blur(function(){
 		var confirmPwd=$("#confirmPwd").val();
 		if(confirmPwd==''){
 			$("#confirmPwdIco").css('display','inline-block');
 			$("#confirmPwdIco").html('<i class="cuo"></i>确认密码不能为空！');
 		}else if(confirmPwd!=$("#newPwd").val()){
 			$("#confirmPwdIco").css('display','inline-block');
 			$("#confirmPwdIco").html('<i class="warn"></i>前后两次密码输入不一致！');
 		}else{
 			$("#confirmPwdIco").css('display','none');
 		}
	})

	/* 修改密码前端判断  */
	function checkPwd(){
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var confirmPwd = $("#confirmPwd").val();
		var regex = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,30}/;
		if(oldPwd==""){
			$("#oldPwdIco").css('display','inline-block');
			$("#oldPwdIco").html('<i class="cuo"></i>旧密码不能为空！');
			return false;
		}else if(newPwd==""){
 			$("#newPwdIco").css('display','inline-block');
 			$("#newPwdIco").html('<i class="cuo"></i>新密码不能为空！');
			return false;
		}else if(confirmPwd==""){
 			$("#confirmPwdIco").css('display','inline-block');
 			$("#confirmPwdIco").html('<i class="cuo"></i>确认密码不能为空！');
			return false;
		}else if(!newPwd.match(regex)){
			$("#newPwdIco").css('display','inline-block');
 			$("#newPwdIco").html('<i class="cuo"></i>密码必须由6-30位字母、数字、特殊字符构成!');
 			return false;
 		}else if(confirmPwd!=newPwd){
 			$("#confirmPwdIco").css('display','inline-block');
 			$("#confirmPwdIco").html('<i class="warn"></i>前后两次密码输入不一致！');
			return false;
		}else if(newPwd==oldPwd){
			$("#newPwdIco").css('display','inline-block');
 			$("#newPwdIco").html('<i class="cuo"></i>新密码与修改的密码相同！');
			return false;
		}
		else{
			return true;
		}
	}


 	$("#savePwd").click(function(){
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var confirmPwd = $("#confirmPwd").val();
		if(checkPwd()){
			$.ajax({
				type: "POST",
	            url: "${pageContext.request.contextPath}/user/editUserPassword",
	            data: {oldPwd:oldPwd,newPwd:newPwd,confirmPwd:confirmPwd},
	            dataType: "json",
	            success: function(data){
	            	if(data.resultFlag){
	            		 /* window.location.href="${pageContext.request.contextPath}/login";
	            		layer.alert(data.message,{
	           			 title: "提示"
			            });  */
	            		setTimeout(function(){//两秒后跳转
	                        alert(data.message);
	                        location.href="${pageContext.request.contextPath}/login";//PC网页式跳转
	                     },2000);
	            	}else{
	            		layer.alert(data.message,{
	              			 title: "提示"
	   		            });
	            	}
	            }
			})
		}
	})

//  	显示实验报告
 	function showReport(obj){
 		var experimentName= $(obj).siblings().get(0).innerHTML;
 		var reportContent= $(obj).siblings().get(1).innerHTML;
 		var comment= $(obj).siblings().get(2).innerHTML;
 		if(comment!=""){
 			comment="教师评论："+comment;
 		}
 	    layer.open({
 	       type: 1,
 	       title: experimentName,
 	       shadeClose: true,
 	       shade: true,
 	       maxmin: true, //开启最大化最小化按钮
 	       area: ['893px', '600px'],
 	       content: "<div style='padding:30px'>"+reportContent+"<hr><div style='font-size:18px;color:#eb6100;' align='center'><span >"
 	       			+comment+"</span></div></div>"
 	     });
 	}

</script>
</body>
</html>
