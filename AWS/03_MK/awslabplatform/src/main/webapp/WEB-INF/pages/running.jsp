<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resource/images/favicon.ico" type="image/x-icon">
<title>${experiment.experimentName }</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/resource/plugins/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/resource/plugins/layer/layer.js" ></script>
<script src="${pageContext.request.contextPath }/resource/plugins/layui/layui.js" ></script>
<script src="${pageContext.request.contextPath }/resource/plugins/bootstrap-3.3.7/js/bootstrap.min.js"></script>


<script type="text/javascript">
	
	/*显示实验指南PDF*/
    function showPdf(isShow) {
        var state = "";
        if (isShow) {
            state = "block";
        } else {
            state = "none";
        }
        var pop = document.getElementById("pop");
        pop.style.display = state;
        var lightbox = document.getElementById("lightbox");
        lightbox.style.display = state;
    }
    function close() {
        showPdf(false);
    }

</script>

<style>
	.HotDate{
		margin:15px 0;
		width: 200px;
		background-color:#f38900;
		  border-radius: 10px;
	}
	.HotDate span{
		display:inline-block;
		width:60px;
		line-height:48px;
		font-size:28px;
		text-align:center;
		color:#fff;
	}
	.HotDate a{
		font-size:40px;
		text-align:center;
		color:#fff;
	}
	#Viewthedoc{
		 width:80px;
		 height:32px;
		 color:#fff;
		 background-color:#1e9fff;
		 text-align:center;
	}
	#ipdetail{
		width:225px;
		margin-bottom:15px
	}
	#ipdetail .subtitle{
		color:#8f8e91
	}
	.spacediv{
		min-width: 1140px;
		white-space: nowrap;
	}
</style>
</head>

<body>
<!-- 弹出框提示1 -->
<!--控制实验启动提示-->
<div class="layui-layer layui-layer-page" id="tipsword"  style="z-index: 1000; top: 149px; left: 640px;position: fixed ;display: none;"><div class="layui-layer-title" >提示</div><div class="layui-layer-content"><div style="padding: 20px 100px;" id="tipsconten"></div></div>
	<span class="layui-layer-setwin"><a onclick="closetips()" class="layui-layer-ico layui-layer-close layui-layer-close1"></a></span><div  class="layui-layer-btn layui-layer-btn-c"><a onclick="closetips()"  class="layui-layer-btn0">确定</a></div>
</div>
<!--不登录控制台-->
<div class="layui-layer layui-layer-page" id="tipsword1"  style="z-index: 1000; top: 149px; left: 640px;position: fixed ;display: none;"><div class="layui-layer-title" >提示</div><div class="layui-layer-content"><div style="padding: 20px 100px;" id="tipsconten1"></div></div>
	<span class="layui-layer-setwin"><a onclick="closetips1()" class="layui-layer-ico layui-layer-close layui-layer-close1"></a></span><div  class="layui-layer-btn layui-layer-btn-c"><a onclick="closetips1()"  class="layui-layer-btn0">确定</a></div>
</div>
<!-- 弹出框提示2 -->
<%--<div class="layui-layer-shade" id=""  style="z-index: 198; background-color: rgb(0, 0, 0); opacity: 0.8;"></div>--%>
<div class="layui-layer layui-layer-page" id="tipsword2"  style="z-index: 1989; width: 600px; top: 160px; left: 618px;display: none">
	<div class="layui-layer-title" >提示</div>
	<div class="layui-layer-content">
		<div style="padding:30px; line-height: 22px; background-color: #fff; color: #000; font-weight: 300;">
			<li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>
			<li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>
			<li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>
			<li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>
			<li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>
			<li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>
			<li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>
		</div>
	</div>

	<div class="layui-layer-btn layui-layer-btn-c" >
		<a class="layui-layer-btn0">确定</a>
		<a class="layui-layer-btn1" onclick="closetips2()">取消</a>
	</div>
</div>


<!-- 弹出框提示3 -->

<div class="layui-layer layui-layer-page" id="tipsword3"  style="z-index: 1000; top: 149px; left: 640px;position: fixed ;display: none;">
	<div class="layui-layer-title" >提示</div>
	<div class="layui-layer-content">
		<div style="padding: 20px 100px;" >是否要释放资源？</div>
	</div>
	<span class="layui-layer-setwin">
		<a onclick="closetips3()" class="layui-layer-ico layui-layer-close layui-layer-close1"></a>
	</span>
	<div class="layui-layer-btn layui-layer-btn-c" >
		<a class="layui-layer-btn0" >确定</a>
		<a class="layui-layer-btn1" onclick="closetips3()">取消</a>
	</div>
</div>


<!-- 弹出框提示4 -->

<div class="layui-layer layui-layer-page" id="tipsword4"  style="z-index: 1000; top: 149px; left: 640px;position: fixed ;display: none;">
	<div class="layui-layer-title" >提示</div>
	<div class="layui-layer-content">
		<div style="padding: 20px 100px;" >确定要结束实验吗？</div>
	</div>
	<span class="layui-layer-setwin">
		<a onclick="closetips4()" class="layui-layer-ico layui-layer-close layui-layer-close1"></a>
	</span>
	<div class="layui-layer-btn layui-layer-btn-c" >
		<a class="layui-layer-btn0" onclick="deleteResource()" >确定</a>
		<a class="layui-layer-btn1" onclick="closetips4()">取消</a>
	</div>
</div>


<!-- 弹出框提示5 -->

<div class="layui-layer layui-layer-page" id="tipsword5"  style="z-index: 1000; top: 149px; left: 640px;position: fixed ;display: none;">
	<div class="layui-layer-title" >提示</div>
	<div class="layui-layer-content">
		<div style="padding: 20px 100px;" >确定要结束实验吗？</div>
	</div>
	<span class="layui-layer-setwin">
		<a onclick="closetips5()" class="layui-layer-ico layui-layer-close layui-layer-close1"></a>
	</span>
	<div class="layui-layer-btn layui-layer-btn-c" >
		<a class="layui-layer-btn0" onclick="stopControlExp()" >确定</a>
		<a class="layui-layer-btn1" onclick="closetips5()">取消</a>
	</div>
</div>

!-- 弹出框提示6   提示结束实验成功 -->

<div class="layui-layer layui-layer-page" id="tipsword6"  style="z-index: 1000; top: 149px; left: 640px;position: fixed ;display: none;"><div class="layui-layer-title" >提示</div><div class="layui-layer-content"><div style="padding: 20px 100px;" >结束实验成功！</div></div>
	<span class="layui-layer-setwin"><a onclick="closetips6()" class="layui-layer-ico layui-layer-close layui-layer-close1"></a></span><div  class="layui-layer-btn layui-layer-btn-c"><a onclick="closetips6()"  class="layui-layer-btn0">确定</a></div>
</div>

	<div class="layui-layout layui-layout-admin">
		<%@include file="head.jsp"%>
		<div class="layui-container" style="margin-top:60px">
			<div class="layui-tab layui-tab-card spacediv">
			
				<ul class="layui-tab-title">
					<li class="layui-this">${experiment.experimentName }</li>
					<li>实验报告</li>
				</ul>
				<div class="layui-tab-content">

					<div class="layui-tab-item layui-show layui-row" style="height: 100%;position: relative;">
						<div class = "layui-col-xs3" style="min-height: 600px;">
							<div id="Viewthedoc" style="display: none;">
								<i class="fa fa-file" aria-hidden="true" style="margin-top:8px"></i>
								<a id="clickpdf" href="${experiment.guideUrl }" target="pdfContainer"
									onclick="showPdf(true)" style="color:#fff">实验指南</a>
							</div>
							<div id="clock" style="display:none">
								<span style="font-size:16px;color:#ec6b6f;margin-left:50px"><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;剩余时间</span>
							</div>
							<div id="CountMsg" class="HotDate ">
								<span id="t_h" style="padding-left: 5px"><fmt:formatNumber type="number" value="${(experiment.runtime-experiment.runtime%60)/60 }" maxFractionDigits="0"/>时</span> 
								<span id="t_m">${experiment.runtime%60 }分</span> 
								<span id="t_s">00秒</span>
							</div>

							<c:if test="${experiment.templateUrl!=null&&''!=fn:trim(experiment.templateUrl) }">
 							<button id="startUp" disabled="disabled" onclick="javascript:startUp()" class="layui-btn layui-btn-disabled">启动实验</button>
							<button id="delete" disabled="disabled" onclick="showtipsword4()" class="layui-btn layui-btn-disabled">结束实验</button><br />
							</c:if>
							<c:if test="${controlExp!=null}">

								<button id="startUpCourse"  onclick="javascript:startUpControlExp()" class="layui-btn layui-btn-normal">启动实验</button>
								<button id="deleteCourse" disabled="disabled" onclick="showtipsword5()" class="layui-btn layui-btn-disabled">结束实验</button><br />
							</c:if>
							<input type="hidden" value="stackName1">
							<div id="InstanceInfo"><br> 
							
								<div id="progress" class="progress progress-striped active layui-col-xs9" >
									<div id="progressbar" class="progress-bar progress-bar-success" role="progressbar"
										 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
										 style="width: 0%">
									</div>
								</div>

								<div class="layui-col-xs11" id="daidin" style="display: none;margin-left: 5px">
									<div class="layui-collapse " style="height:100%;">
										<div class="layui-colla-item">
											<p class="layui-colla-title" style="margin: 0" >
												<span id="daidin1"></span>
											</p >
											<div class="layui-colla-content layui-show">
												<p style="white-space: normal;word-break: break-all;width: 100%;">
													<i class="fa fa-circle" aria-hidden="true" style="color:#00a7ac;"></i>
													登录网址：<a id="daidin2" target="_blank"></a>
												</p >

												<p><i class="fa fa-circle" aria-hidden="true" style="color:#249cfd;"></i>
													账号：<input id="daidin3"   value="">
													<button id="copyuser" title="点击复制账号" class="fa fa-copy" aria-hidden="true" style="color:#249cfd;"></button>
												</p >

												<p><i class="fa fa-circle" aria-hidden="true" style="color:#5b3f99;"></i>
													密码：<input id="daidin4" value="">
													<button id="copypwd" title="点击复制密码" class="fa fa-copy" aria-hidden="true" style="color:#249cfd;"></button>
													
													
											
												</p >

											</div>
										</div>
									</div>
								</div>

								<div id="ipdetail" class="layui-col-xs11" style="margin-top:10px;height:100%;">
								</div>
								<div class="layui-col-xs11"  style="margin-top:10px;background-color:#f2f2f2;" id="control">
									    <input type="hidden" id="controlExp" value="${controlExp}">
										<input type="hidden" id="consoleLoginLink" value="${studentExperment.consoleLoginLink}">
										<input type="hidden" id="poolUsed" value="${studentExperment.poolUsed}">
										<input type="hidden" id="iamName" value="${studentExperment.iamName}">
										<input type="hidden" id="password" value="${studentExperment.password}">
									    <input type="hidden" id="endTime" value="${studentExperment.endTime}">
									    <input type="hidden" id="stopTime" value="${experiment.runtime}">
									    <input type="hidden" id="expermentId" value="${experimentId}">
								</div>
								<div class="layui-col-xs11" style="margin-top:20px;">
									<a href="#" class="layui-btn layui-btn-disabled" id="keyUrl" style="width:200px;margin-left:10px"><i class="fa fa-download" 
					 			aria-hidden="true" style="margin-right:5px" disabled="disabled" ></i>密钥下载</a>	
								</div>
								<div class="layui-col-xs11" style="margin-top:10px;">
									<button disabled="disabled" onclick="connGuide()" class="layui-btn layui-btn-disabled" id="connect" style="width:200px;margin-left:10px"><i class="fa fa-file" 
					 			aria-hidden="true" style="margin-right:5px" ></i>连接向导</button>
								</div>


							</div>	
						</div>					
						<div class="lightbox" id="lightbox"></div>
						<div id="pop" class="pop layui-col-xs9" 
							style="display: none; position:absolute; height:100%; left: 25%;">
							<div style="position:relative;height:100%">
								<iframe src="${experiment.guideUrl}" frameborder="0" id="pdfContainer"
								name="pdfContainer"
								style="position: absolute; top: 0; width: 100%;height: 100%;z-index: 9;"></iframe>
							
							</div>
							
						</div>

					</div>
					<div class="layui-tab-item">
						<div id="div1" class="toolbar">
						    </div>
						    <div id="div2" class="text" style="height: 430px"> <!--可使用 min-height 实现编辑区域自动增加高度-->
						    <p>
								${report.content}
							</p>
						</div>
						<div align="right">
							<br>
							<button id="submit"  class="layui-btn layui-btn-normal">保存</button>
						</div>
						<script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/wangEditor.min.js"></script>
						<script type="text/javascript">
							var E = window.wangEditor;
							var editor = new E('#div1', '#div2') ;
							var $text1 = $('#text1')
							editor.customConfig.onchange = function (html) {
					            // 监控变化，同步更新到 textarea
					            $text1.val(html)
					        }
							editor.customConfig.uploadImgServer = 'upload'; //上传URL
							editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
							editor.customConfig.uploadImgMaxLength = 100;    
							editor.customConfig.uploadFileName = 'myFileName';
							editor.customConfig.uploadImgHooks = {
							    customInsert: function (insertImg, result, editor) {
							                // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
							                // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
							         
							                // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
							                var url =result.data;
							                insertImg(url);
							         
							                // result 必须是一个 JSON 格式字符串！！！否则报错
							            }
							        }
							editor.create();
							 // 初始化 textarea 的值
					        $text1.val(editor.txt.html())
						</script>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- 资源时间 -->
	<script type="text/javascript">
	var endtime=new Date();
		function getRTime() {

			var EndTime = new Date(endtime); //截止时间 
			var NowTime = new Date();
			var t = EndTime.getTime() - NowTime.getTime();
			if(t>0){
				$('#clock').attr("style", "display:block");
				var d = Math.floor(t / 1000 / 60 / 60 / 24);
				var h = Math.floor(t / 1000 / 60 / 60 % 24);
				var m = Math.floor(t / 1000 / 60 % 60);
				var s = Math.floor(t / 1000 % 60);
	
				// document.getElementById("t_d").innerHTML = d + "天"; 
				document.getElementById("t_h").innerHTML = h + "时";
				document.getElementById("t_m").innerHTML = m + "分";
				document.getElementById("t_s").innerHTML = s + "秒";
			}else{
				$('#clock').attr("style", "display:none");
			}
		}
		var timer = setInterval(getRTime, 1000);
	</script>
	<script type="text/javascript">
		//showPdf(true);
		$("#clickpdf").click();
	</script>
<!-- 	操作资源js -->
	<script type="text/javascript">
		var getting ="";
		//启动资源
		function startUp(){
			//启动按键变为不可操作
			$("#startUp").attr("class","layui-btn layui-btn-disabled");
			$("#startUp").attr("disabled","disabled");
			timer = setInterval(getRTime, 1000);
			$.ajax({
				type: "POST",
				url: "startUp",
				data: "experimentId=${experimentId}",
			 	dataType:"json",
	            success: function(data){
					tips1(data);
					if(data!=-1||data!=-2){
						location.reload();
					}

                }
            })
			getting = setInterval(getStackEvents, 5000);
		}
		//释放资源
		function deleteResource(){
			closetips4()
			$.ajax({
                type: 'POST',
                url: "deleteResource",
                data: "experimentId=${experimentId}",
                success: function(data){
                	$("#delete").attr("class","layui-btn layui-btn-disabled");
                	$("#delete").attr("disabled","disabled");
                	$("#keyUrl").attr("class","layui-btn layui-btn-disabled");
                	$("#keyUrl").attr("disabled","disabled");
                	$("#connect").attr("class","layui-btn layui-btn-disabled");
                	$("#connect").attr("disabled","disabled");
                	$("#startUp").attr("class","layui-btn layui-btn-normal");
            		$("#startUp").removeAttr("disabled");
            		//清空ip等信息
            		$("#ipdetail").replaceWith('<div id="ipdetail" class="layui-col-xs11" style="margin-top:20px">');
	           		//停止计时
	           		endtime=new Date();
                	clearInterval(timer);
                	document.getElementById("t_h").innerHTML = 0 + "时";
    				document.getElementById("t_m").innerHTML = 0 + "分";
    				document.getElementById("t_s").innerHTML = 0 + "秒";
            		clearInterval(getting);
            		changePrecent(0);
					showtipsword6()
                }
            })
            
		}
	</script>
	
	
	<script type="text/javascript">
		//定义变量
		var ipArray = new Array();
		var DNSArray = new Array();
		var isWindows=false;
		var windowsPassword="";
		$(function(){
			//初始化 controlExp 用来判断是哪种类型实验
			var controlExp =$("#controlExp").val();
			if(controlExp==1){
				var res =$("#poolUsed").val();
				if(res==1||res==2){
					xianshi();
					if("${message}"!=null&&''!="${message}"){
						layer.alert("${message}");
					}
				}
			}else{
				getStackEvents();
				getting = setInterval(getStackEvents, 5000);
				if("${message}"!=null&&''!="${message}"){
					layer.alert("${message}");
				}
			}




		})

		function getResource(){
			$.ajax({
				//async:false,
				type: "POST",
				url: "getResource",
				data: "experimentId=${experimentId}",
			 	dataType:"json",
	            success: function(data){
	            	if("failed"!=data){
	            	 var data = JSON.parse(data);
	            	 var instanceList = data.instanceList;
	            	 endtime=data.endtime;
	            	 var keyUrl=data.keyUrl;
	            	 
	            	 windowsPassword=data.windowsPassword;
	            	            	 
	            	 for(var i=0;i<instanceList.length;i++){
	            		 var num=i+1;
	            		 var plantform="";
	            		 if(instanceList[i].platform=="windows"){
							isWindows=true;//如果是windows实例
	            		 	plantform="windows";
	            		 }
	            		 var instanceDiv = $('<div style="background-color:#f2f2f2;"></div>');
	            		 instanceDiv.append('<div style="padding:8px 16px 0;">\
									<span class="subtitle" style="font-size:16px;color:#353841;font-weight:600">'+plantform+'实例'+num+'</span></div> ');
	            		 instanceDiv.append('<div style="margin:8px 16px;"><i class="fa fa-circle" aria-hidden="true" style="color:#249cfd;"></i>\
									<span class="subtitle">公有 IP：</span><br>\
									<span style="color:#353841" id="publicIpAddress">'+instanceList[i].publicIpAddress+'</span></div> ');
	            		 instanceDiv.append('<div style="margin:8px 16px;"><i class="fa fa-circle" aria-hidden="true" style="color:#249cfd;"></i>\
									<span class="subtitle">公有 DNS：</span><br>\
									<span style="color:#353841;white-space: normal;word-break: break-all;width: 100%;" id="publicIpAddress">'+instanceList[i].publicDnsName+'</span></div> ');
	            		 instanceDiv.append('<div style="margin:8px 16px;;"><i class="fa fa-circle" aria-hidden="true" style="color:#00a7ac;"></i>\
									<span class="subtitle">实例ID：</span><br>\
									<span style="color:#353841" id="instanceId">'+instanceList[i].instanceId+'</span></div>');
	            		 instanceDiv.append('<div style="margin:8px 16px;padding-bottom:"><i class="fa fa-circle" aria-hidden="true" style="color:#5b3f99;"></i>\
									<span class="subtitle">实例类型： </span><br>\
									<span style="color:#353841" id="instanceType">'+instanceList[i].instanceType+'</span></div>');
	            		 $("#ipdetail").append(instanceDiv);
	            		 ipArray[i]=instanceList[i].publicIpAddress;
	            		 DNSArray[i]=instanceList[i].publicDnsName;
	            		 
	            	 }
	            	 if(!isWindows){
		            	 $("#keyUrl").attr("href","${pageContext.request.contextPath }/downloadKey?keyUrl="+keyUrl);
		            	 $("#keyUrl").attr("class","layui-btn layui-btn-normal");
		            	 $("#keyUrl").removeAttr("disabled");	 
	            	 }else{
	            		 $("#keyUrl").attr("style","display:none;");
	            	 }
	            	 $("#connect").attr("class","layui-btn layui-btn-normal");
	            	 $("#connect").removeAttr("disabled");	
            		 $("#startUp").attr("class","layui-btn layui-btn-disabled");
            		 $("#startUp").attr("disabled","disabled");
	            	$("#delete").attr("class","layui-btn layui-btn-normal");
	            	$("#delete").removeAttr("disabled");
	            	$("#progress").removeClass("active");
 	            	
	            	changePrecent(100);
	            	}else{
	            		$("#delete").attr("class","layui-btn layui-btn-disabled");
	                	$("#delete").attr("disabled","disabled");
	                	$("#keyUrl").attr("class","layui-btn layui-btn-disabled");
	                	$("#keyUrl").attr("disabled","disabled");
	                	$("#connect").attr("class","layui-btn layui-btn-disabled");
	                	$("#connect").attr("disabled","disabled");
	                	$("#startUp").attr("class","layui-btn layui-btn-normal");
	            		$("#startUp").removeAttr("disabled");
	            		//清空ip等信息
	            		$("#ipdetail").replaceWith('<div id="ipdetail" class="layui-col-xs11" style="margin-top:20px">');
		           		//停止计时
		           		endtime=new Date();
	                	clearInterval(timer);
	                	document.getElementById("t_h").innerHTML = 0 + "时";
	    				document.getElementById("t_m").innerHTML = 0 + "分";
	    				document.getElementById("t_s").innerHTML = 0 + "秒";
	            		clearInterval(getting);
	            		changePrecent(0);
	            	}
	            }
	        })
		}
	
	
		var percent=3;
		function getStackEvents(){
			$.ajax({
				//async:false,
				type: "POST",
				url: "getStackEvents",
			 	dataType:"json",
	            success: function(data){
	            	if("failed"!=data){
	            		var stackEventList = JSON.parse(data);
	            		var resourceType=stackEventList[0].resourceType;
	            		var resourceStatus=stackEventList[0].resourceStatus;
	            		percent=stackEventList.length*6+Math.random()*1|0;
	            		if(percent>90){
	            			percent=90;
	            		}
	            		changePrecent(percent);
	            		 if(resourceType=="AWS::CloudFormation::Stack"){
	            			if(resourceStatus=="CREATE_COMPLETE"){
		            			 getResource();
		            			 clearInterval(getting);
		            			 changePrecent(100);
		            			 timer = setInterval(getRTime, 1000);
	            			 }
	            			if(resourceStatus=="DELETE_COMPLETE"){
		            			 clearInterval(getting);
	           				}
	            			if(resourceStatus=="ROLLBACK_COMPLETE"){
	            				//资源回滚，创建失败
		            			 clearInterval(getting);
		            			 deleteResource();
		            			 layer.alert("资源创建失败！请联系本课程教师。");
	           				}
	            		 }
	            	}else{
	            		clearInterval(getting);	
		            	$("#startUp").attr("class","layui-btn layui-btn-normal");
	            		$("#startUp").removeAttr("disabled");
	            	}
	            }
	        })
		}
		//改变进度
		function changePrecent(num){
			$("#progressbar").attr("style","width:"+num+"%");
		}
		//保存实验报告
		document.getElementById('submit').addEventListener('click', function () {
	        // 获取 html内容
	        var mycontent = $(".w-e-text").html();
	        if($(".w-e-text").text().trim()==""){
	        	layer.msg("还没有填写文字内容哦。")
	        	return false;
	        }
	        mycontent=  ajax_encode(mycontent);
			$.ajax({
                type: 'POST',
                url: "saveReport",
                data: {
                	'experimentId':'${experimentId}',
                	'content':mycontent
                	},
                success: function(data){
                	layer.msg(data);
                }
            })
	            
	    }, false)
	    
	    function connGuide(){

			var res =$("#poolUsed").val();
			var connStr="";
			var ipStr="";
			if(res==1||res==2){
				var content='<div class="GO-IDYFLJH" style="padding:0px">\
				<div class="GO-IDYFCJH"><span class="GO-IDYFAJH">要访问您的实例:</span>\
				<ol> <li>请先访问登录地址</li>\
				<li>复制实例的登录地址</li> \
				<li>1. 在浏览器中地址栏中将复制的地址，写入，并访问</li> \
				<li>2. 再将实例里的用户名和密码复制到该地址下的用户名和密码输入框中</li> \
				<li>3. 切记勿改动页面中的付费账号里的账号，如果改变会导致登录失败，若不小心对其进行改动，刷新页面即可回复</li> \
				<li>4. 鼠标点击登录即可</li> \
				<li>5. 登录成功后就可以平台上做实验</li> \
				</div> \
			<div>其它连接方式，请参阅我们的<a href="https://docs.amazonaws.cn/console/ec2/instances/connect/docs" target="_blank">链接文档</a>。</div></div>'
				if(isWindows){
					var consoleLoginLink= $("#consoleLoginLink").val();
					content='<div class="GO-IDYFLJH" style="padding:0px">\
					<div class="GO-IDYFCJH"><span class="GO-IDYFAJH">注意:windows实例在启动后内部加载还需要时间，启动后至少4分钟才能成功连接。</span>\
					<ol> <li>请先<a href="'+consoleLoginLink +'" id="awsUrl"  target="_blank">请先访问登录地址</a>。</li>\
					<li>1. 再将实例里的用户名和密码复制到该地址下的用户名和密码输入框中</li> \
					<li>2. 切记勿改动页面中的付费账号里的账号，如果改变会导致登录失败，若不小心对其进行改动，刷新页面即可回复</li> \
					<li>3. 鼠标点击登录即可</li> \
					<li>4. 登录成功后就可以平台上做实验</li></div> \
				<div>其它连接方式，请参阅我们的<a href="https://docs.amazonaws.cn/console/ec2/instances/connect/windows" target="_blank">链接文档</a>。</div></div>'

				}
			}else{

				for(var i=0;i<ipArray.length;i++){
					connStr=connStr+'<pre>ssh -i "key.pem" ec2-user@'+DNSArray[i]+'</pre>';
					ipStr=ipStr+'<pre>'+DNSArray[i]+'</pre>';
				}
				var content='<div class="GO-IDYFLJH" style="padding:0px">\
				<div class="GO-IDYFCJH"><span class="GO-IDYFAJH">要访问您的实例:</span>\
				<ol> <li>请先下载您的私有密钥文件 (key.pem)，每次启动资源密钥都不一样哦。</li>\
				<li>打开 SSH 客户端(例如SecureCRT)。</li> \
				<li>1. 新增一个连接，连接方式选择SSH2，点[下一步]</li> \
				<li>2. Hostname 填写公有 IP或者公有 DNS：'+ipStr+'  &nbsp&nbsp Port默认22，Username: ec2-user，点[下一步]，再点[完成]</li> \
				<li>3. 鼠标右击刚刚创建好的连接，选择Properties。</li> \
				<li>4. 依次点击弹出框左侧Connection -> SSH2 ，点击右侧PublicKey，再点旁边[Properties...]。</li> \
				<li>5. 在Use identity or certificata file 单选 下选择您下载的私有密钥文件(key.pem)，点[OK]，再点[OK]</li> \
				<li>6. 双击您已配置好的连接就可以操作您的实例啦。</li> </div> \
			<div>其它连接方式，请参阅我们的<a href="https://docs.amazonaws.cn/console/ec2/instances/connect/docs" target="_blank">链接文档</a>。</div></div>'
				if(isWindows){
					content='<div class="GO-IDYFLJH" style="padding:0px">\
					<div class="GO-IDYFCJH"><span class="GO-IDYFAJH">注意:windows实例在启动后内部加载还需要时间，启动后至少4分钟才能成功连接。</span>\
					<ol> <li>请先<a href="${pageContext.request.contextPath }/windowsConn?publicDnsName='+DNSArray[0]+'">下载远程桌面文件</a>。</li>\
					<li>1. 双击远程桌面文件，计算机：填公有 IP或公有 DNS，点[连接]'+ipStr+'</li> \
					<li>2. 提示无法识别此远程连接的发部者。是否仍要连接？点[连接]</li> \
					<li>3. 输入用户名：<pre>Administrator</pre>密码：<pre>'+windowsPassword+'</pre>点[确定]</li> \
					<li>4. 提示无法验证此远程计算机的身份。是否仍要连接？点[是]。</li></div> \
				<div>其它连接方式，请参阅我们的<a href="https://docs.amazonaws.cn/console/ec2/instances/connect/windows" target="_blank">链接文档</a>。</div></div>'
				}
			}


			layer.open({
		 	       type: 0,
		 	       title: "连接向导",
		 	       shadeClose: false,
		 	       shade: false,
		 	       maxmin: false, //开启最大化最小化按钮
		 	       area: ['670px', '500px'],
		 	       content: content
		 	     });        
		}
	    /**
     * 将 <、>等符号替换为html转义字符，如有别的字符，可自行添加
     */
    function ajax_encode(str) {
      str = str.replace(/</g, "&lt;");
      str = str.replace(/>/g, "&gt;");
      return str;
    }
     //开启控制台实验
     function startUpControlExp(){
		 $("#startUpCourse").attr("class","layui-btn layui-btn-disabled");
		 $("#startUpCourse").attr("disabled","disabled");
		 // var  runtime=$("#stopTime").val()*1000*60;
		 // var now = new Date();
		 // var StartTime = now.getTime()+runtime;
		 // endtime= new Date(StartTime);
		 //  timer = setInterval(getRTime, 1000);
    	 $.ajax({
             type: 'POST',
             url: "startUpControlExp",
             data: {
             	'experimentId':'${experimentId}',
             	},
             success: function(data){
             	tips(data.poolUsed);
				 $("#poolUsed").val(data.poolUsed);
             	if(data!=null){
             		if(data.poolUsed==1||data.poolUsed==2){
						var daidin1='实例';
						var daidin2=data.consoleLoginLink;
						var daidin3=data.iamName;
						var daidin4=data.password;
						$("#consoleLoginLink").val(data.consoleLoginLink);
						$("#daidin1").text(daidin1);
						$("#daidin2").text(daidin2);
						$("#daidin2").attr("href",daidin2);
						$("#daidin3").text(daidin3);
						$("#daidin3").attr("value",daidin3);
						$("#daidin4").text(daidin4);
						$("#daidin4").attr("value",daidin4);
						$("#connect").attr("class","layui-btn layui-btn-normal");
						$("#connect").removeAttr("disabled");
						isWindows=true;
						$("#endTime").val(data.endTime);
						//alert(endtime);
						endtime=data.endTime;
						$("#deleteCourse").attr("class","layui-btn layui-btn-normal");
						$("#deleteCourse").removeAttr("disabled");
						$("#startUpCourse").attr("class","layui-btn layui-btn-disabled");
						$("#startUpCourse").attr("disabled","disabled");
						timer = setInterval(getRTime, 1000);
						$("#daidin").css({
							"display":"block"
						})
					}else{
						$("#startUpCourse").attr("class","layui-btn layui-btn-normal");
						$("#startUpCourse").removeAttr("disabled");
						// //停止计时
						// endtime=new Date();
						// clearInterval(timer);
						// document.getElementById("t_h").innerHTML = 0 + "时";
						// document.getElementById("t_m").innerHTML = 0 + "分";
						// document.getElementById("t_s").innerHTML = 0 + "秒";
						// $("#daidin").css({
						// 	"display":"none"
						// })

					}

                }

			 },
			 error:function(){
				 $("#startUpCourse").attr("class","layui-btn layui-btn-normal");
				 $("#startUpCourse").removeAttr("disabled");
			 }
         })
     }
     //结束控制台实验
      function stopControlExp() {
		  closetips5();
		  stopControl();
		  //location.href ="stopControlExp?experimentId="+$("#expermentId").val();
		  $.ajax({
			  type: 'POST',
			  url: "stopControlExp",
			  data: {
				  'experimentId':'${experimentId}',
			  },
			  success: function(data){

			  },
			  // error:function(){
				//   $("#deleteCourse").attr("class","layui-btn layui-btn-normal");
				//   $("#deleteCourse").removeAttr("disabled");
			  // }
		  })

	  }

	  function stopControl(){
		  $("#deleteCourse").attr("class","layui-btn layui-btn-disabled");
		  $("#deleteCourse").attr("disabled","disabled");
		  showtipsword6()
		  $("#connect").attr("class","layui-btn layui-btn-disabled");
		  $("#connect").attr("disabled","disabled");
		  $("#startUp").attr("class","layui-btn layui-btn-normal");
		  $("#startUp").removeAttr("disabled");
		  //清空ip等信息
		  $("#consoleLoginLink").val("");
		  $("#iamName").val("");
		  $("#password").val("");
		  $("#poolUsed").val("");
		  $("#endTime").val("");
		  $("#daidin1").text("");
		  $("#daidin2").text("");
		  $("#daidin3").text("");
		  $("#daidin4").text("");
		  //停止计时
		  endtime=new Date();
		  clearInterval(timer);
		  document.getElementById("t_h").innerHTML = 0 + "时";
		  document.getElementById("t_m").innerHTML = 0 + "分";
		  document.getElementById("t_s").innerHTML = 0 + "秒";
		  //clearInterval(getting);
		  //changePrecent(0);
		  $("#daidin").css({
			  "display":"none"
		  })
		  $("#startUpCourse").attr("class","layui-btn layui-btn-normal");
		  $("#startUpCourse").removeAttr("disabled");
	  }



       //控制台实验初始化
		function xianshi(){
			var res =$("#poolUsed").val();
			if(res==1||res==2){
				var daidin1='实例';
				var daidin2=$("#consoleLoginLink").val();
				var daidin3=$("#iamName").val();
				var daidin4=$("#password").val();
				endtime =$("#endTime").val();
				//alert(endtime);
				$("#daidin1").text(daidin1);
				$("#daidin2").text(daidin2);
				$("#daidin2").attr("href",daidin2);
				$("#daidin3").text(daidin3);
				$("#daidin3").attr("value",daidin3);
				$("#daidin4").attr("value",daidin4);
				$("#daidin4").text(daidin4);
				$("#connect").attr("class","layui-btn layui-btn-normal");
				$("#connect").removeAttr("disabled");
				isWindows=true;
				$("#daidin").css({
					"display":"block"
				})
				timer = setInterval(getRTime, 1000);
				$("#startUpCourse").attr("class","layui-btn layui-btn-disabled");
				$("#startUpCourse").attr("disabled","disabled");
				$("#deleteCourse").attr("class","layui-btn layui-btn-normal");
				$("#deleteCourse").removeAttr("disabled");

			}else {
				$("#daidin").css({
					"display":"none"
				})
			}
		}

		//弹框提示
		function tips(res){

				if(res==2){
					//console.log('不弹窗')
					return;

				}
				var content="";

				switch (res){

					case '0':content='启动实验失败，请联系管理员，没有找到对应实验策略！';

						break;
					case '1':content="启动实验成功，开始实验！";

						break;
					case '3':content="启动实验失败，没有可用的实验账号，请稍等后在进行尝试，或联系管理员！";

						break;
					case '-1':content="启动实验失败，请联系管理员,未找到该实验信息，无法启动实验！";

						break;
					case '-2':content='启动实验失败，请联系管理员，该实验账号凭证失效，无法使用！';

						break;
					case '-3':content='启动实验失败，请联系管理员，绑定该策略失败，该策略有问题，无法启动实验！';

						break;
					case '-4':content='启动实验失败，有实验未正常关闭！';

						break;
					default :content='启动实验失败,请联系管理员！';

						break;
				}

				$("#tipsconten").text(content);
				$("#tipsword").css({
					'display':'block'
				})

		}
		//弹框提示
		function tips1(res){
			//console.log(typeof res);
			//console.log(res)
			if(res!=-1&&res!=-2){
				//console.log('不弹窗')
				return;
			}
			var content=null;


			switch (res){


				case -1:content='启动实验失败，请联系管理员,未找到该实验信息，无法启动实验。';

					break;
				case -2:content='启动实验失败，请联系管理员，未找到实验账号，无法启动实验。';

					break;

			}
			if(content!=null){
			$("#tipsconten1").text(content);
			$("#tipsword1").css({
					'display':'block'
				})
			}
		}
		//tips()
		//关闭弹框
		function closetips(){
			$("#tipsword").css({
				'display':'none'
			})
			//location.reload();
		}
		function closetips1(){
			$("#tipsword1").css({
				'display':'none'
			})
		}
		//关闭弹框2
		function closetips2(){
			$("#tipsword2").css({
				'display':'none'
			})
		}

		//关闭弹框3
		function closetips3(){
			$("#tipsword3").css({
				'display':'none'
			})
		}
		//关闭弹框4
		function closetips4(){
			$("#tipsword4").css({
				'display':'none'
			})
		}

		//关闭弹框5
		function closetips5(){
			$("#tipsword5").css({
				'display':'none'
			})
		}
		//关闭弹框6 结束实验成功
		function closetips6(){
			$("#tipsword6").css({
				'display':'none'
			})
		}
		//显示结束实验提示
		function showtipsword4(){
			$("#tipsword4").css({
				'display':'block'
			})
		}

		//显示结束控制台实验提示
		function showtipsword5(){
			$("#tipsword5").css({
				'display':'block'
			})
		}


		//显示弹框6 结束实验成功弹框
		function showtipsword6(){
			$("#tipsword6").css({
				'display':'block'
			})
		}
		
		//复制账号
		$("#copyuser").on('click',function(){
		  var copyText = $("#daidin3")
      	  copyText.select(); //选择对象
		  document.execCommand("Copy"); //执行浏览器复制命令
		
      
   		 });
		
		//复制密码
		$("#copypwd").on('click',function(){
		  var copyText = $("#daidin4")
      	  copyText.select(); //选择对象
		  document.execCommand("Copy"); //执行浏览器复制命令
		
      
   		 });
		
		
		
		
	
		
	</script>
</body>
</html>
