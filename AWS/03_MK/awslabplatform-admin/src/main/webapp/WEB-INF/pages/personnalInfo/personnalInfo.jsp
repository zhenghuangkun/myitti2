<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/common/head.jsp" %>
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/jquery.gritter.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/select2.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-datepicker3.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-editable.min.css" />
		
		<style type="text/css">
			.gritter-center{
				position:fixed;
				left:33%;
				right:33%;
				top:50%
			}
			
			.gritter-close {
				display:none;
				position:absolute;
				top:5px;
				left:380px;
				cursor:pointer;
				width:30px;
				height:30px;
			}
			.profile-info-name{
				font-size:16px;
			}
			.profile-info-value{
				font-size:16px;
			}
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
								<span style="font-size:16px">个人信息</span>
							</li>
							
						</ul>
					</div>

					<div class="page-content">
						<%-- <%@ include file="/WEB-INF/pages/common/setting.jsp" %> --%>
						<div class="page-header">
							
						</div>
						
						<div class="row">
							<div class="col-xs-12">
									<div class="clearfix">
										<div class="pull-left alert alert-success no-margin">
											<button type="button" class="close" data-dismiss="alert">
												<i class="ace-icon fa fa-times"></i>
											</button>
	
											<i class="ace-icon fa fa-umbrella bigger-120 blue"></i>
											温馨提示：点击头像可自定义头像、点击可编辑领域可自定义信息...
										</div>
									</div>
									<div class="hr dotted"></div>
									<div class="space-12"></div>
									<div id="user-profile-1" class="user-profile row">
										<div class="col-xs-12 col-sm-3 center">
											<div>
												<!-- #section:pages/profile.picture -->
												<span class="profile-picture">
													<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="${pageContext.request.contextPath}/resource/assets/avatars/profile-pic.jpg" />
												</span>

												<!-- /section:pages/profile.picture -->
												<div class="space-6"></div>

												<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
													<div class="inline position-relative">
														<span class="user-title-label dropdown-toggle" >
															<i class="ace-i010/06/20con fa fa-circle light-green"></i>
															&nbsp;
															<span class="white">张 三</span>
														</span>
													</div>
												</div>
											</div>
											<div class="space-6"></div>
										</div>
										
										<div class="col-xs-12 col-sm-8">
										
											<!-- #section:pages/profile.info -->
											<div class="profile-user-info profile-user-info-striped">
												<div class="profile-info-row">
													<div class="profile-info-name">真实姓名</div>

													<div class="profile-info-value">
														<span class="editable" id="userName">张三</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name"> 性 别</div>

													<div class="profile-info-value">
														<span class="editable" id="sex">男</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 出身日期 </div>

													<div class="profile-info-value">
														<span class="editable" id="birthday">1990/06</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 家庭住址 </div>

													<div class="profile-info-value">
														<span class="editable" id="address">福建福州</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name"> 电话 </div>

													<div class="profile-info-value">
														<span class="editable" id="phone">18046056025</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name"> 邮箱 </div>

													<div class="profile-info-value">
														<span class="editable" id="email">377398043@qq.com</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 学 校 </div>

													<div class="profile-info-value">
														<span class="editable" id="university">福州大学</span>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name"> 院系 </div>

													<div class="profile-info-value">
														<span class="editable" id="department">计算机信息学院</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									
							</div><!-- /.col -->
						</div><!-- /.row -->
						<div class="space-20"></div>
						<div class="hr hr12 dotted"></div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
		</div>
			<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
		<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.gritter.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrap-wysiwyg.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/select2.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/x-editable/bootstrap-editable.min.js"></script>
		<script src="${pageContext.request.contextPath}/resource/assets/js/x-editable/ace-editable.min.js"></script>
		
		
		<!-- 中文日期插件 -->
		<%-- <script src="${pageContext.request.contextPath}/resource/js/bootstrap-datepicker.languages.js"></script> --%>

		
		<script type="text/javascript">
			jQuery(function($) {
				
				//editables on first profile page
				$.fn.editable.defaults.mode = 'inline';
				$.fn.editableform.loading = "<div class='editableform-loading'><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
			    $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>'+
			                                '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';    
				
				//用户昵称  editable
				var currentValue = $("#userName").innerText;
			    $('#userName').editable({
					type: 'text',
					name: 'username',
					success:function(response, newValue){
						if(currentValue == newValue)
							return;
						currentValue = newValue;
						alert("ajax 提交数据到服务器");
					}
					
			    });
			    
			    //性别  editable
			    var sexValue = [];
			    $.each({ "Man": "男", "Lady": "女"}, function(k, v) {
			    	sexValue.push({id: k, text: v});
			    });
			    $('#sex').editable({
					type: 'select2',
					source:sexValue,
					select2:{
						'width':80
					},
					success:function(response,newValue){
						
					}
			    });
			     
			     
			   //出生年月  editable
			   $('#birthday').editable({
					type: 'adate',
					date:{
						format:'yyyy/mm/dd',
						weekStart:1,
						language:"cn"
					},
					success:function(response,newValue){
						
					}
			    });
			    
			    //家庭住址 editable
				var currenAddress = $("#address").innerText;
			    $('#address').editable({
					type: 'text',
					name: 'address',
					success:function(response, newValue){
						if(currenAddress == newValue)
							return;
						currenAddress = newValue;
						//alert("ajax 提交数据到服务器");
					}
					
			    });
			    
			    //电话号码编辑 phone 
				var currentPhone = $("#phone").innerText;
			    $('#phone').editable({
					type: 'text',
					name: 'phone',
					validate:function(value){
						//加入电话号码格式验证
					},
					success:function(response, newValue){
						if(currentPhone == newValue)
							return;
						currentPhone = newValue;
						//alert("ajax 提交数据到服务器");
					}
					
			    });
			    
			   //邮箱编辑  phone 
				var currentEmail = $("#email").innerText;
			    $('#email').editable({
					type: 'text',
					name: 'email',
					validate:function(value){
						//加入邮箱验证
					},
					success:function(response, newValue){
						if(currentEmail == newValue)
							return;
						currentEmail = newValue;
						//alert("ajax 提交数据到服务器");
					}
					
			    });
			    
			    
			 	// *** editable avatar *** //
				try {//ie8 throws some harmless exceptions, so let's catch'em
			
					//first let's add a fake appendChild method for Image element for browsers that have a problem with this
					//because editable plugin calls appendChild, and it causes errors on IE at unpredicted points
					try {
						document.createElement('IMG').appendChild(document.createElement('B'));
					} catch(e) {
						Image.prototype.appendChild = function(el){}
					}
			
					var last_gritter
					$('#avatar').editable({
						type: 'image',
						name: 'avatar',
						value: null,
						//onblur: 'ignore',  //don't reset or hide editable onblur?!
						image: {
							//specify ace file input plugin's options here
							btn_choose: 'Change Avatar',
							droppable: true,
							maxSize: 110000,//~100Kb
			
							//and a few extra ones here
							name: 'avatar',//put the field name here as well, will be used inside the custom plugin
							on_error : function(error_type) {//on_error function will be called when the selected file has a problem
								if(last_gritter) $.gritter.remove(last_gritter);
								if(error_type == 1) {//file format error
									last_gritter = $.gritter.add({
										title: 'File is not an image!',
										text: 'Please choose a jpg|gif|png image!',
										class_name: 'gritter-error gritter-center'
									});
								} else if(error_type == 2) {//file size rror
									last_gritter = $.gritter.add({
										title: 'File too big!',
										text: 'Image size should not exceed 100Kb!',
										class_name: 'gritter-error gritter-center',

									});
								}
								else {//other error
								}
							},
							on_success : function() {
								$.gritter.removeAll();
							}
						},
					    url: function(params) {
							// ***UPDATE AVATAR HERE*** //
							//for a working upload example you can replace the contents of this function with 
							//examples/profile-avatar-update.js
			
							var deferred = new $.Deferred
			
							var value = $('#avatar').next().find('input[type=hidden]:eq(0)').val();
							if(!value || value.length == 0) {
								deferred.resolve();
								return deferred.promise();
							}
			
			
							//dummy upload
							setTimeout(function(){
								if("FileReader" in window) {
									//for browsers that have a thumbnail of selected image
									var thumb = $('#avatar').next().find('img').data('thumb');
									if(thumb) $('#avatar').get(0).src = thumb;
								}
								
								deferred.resolve({'status':'OK'});
			
								if(last_gritter) $.gritter.remove(last_gritter);
								last_gritter = $.gritter.add({
									title: 'Avatar Updated!',
									text: 'Uploading to server can be easily implemented. A working example is included with the template.',
									class_name: 'gritter-info gritter-center'
								});
								
							 } , parseInt(Math.random() * 800 + 800))
			
							return deferred.promise();
							
							// ***END OF UPDATE AVATAR HERE*** //
						},
						
						success: function(response, newValue) {
						}
					})
				}catch(e) {}
			    
				/////////////////////////////////////
				$(document).one('ajaxloadstart.page', function(e) {
					//in ajax mode, remove remaining elements before leaving page
					try {
						$('.editable').editable('destroy');
					} catch(e) {}
					$('[class*=select2]').remove();
				});
			});
		</script>
		
	
	</body>
</html>
