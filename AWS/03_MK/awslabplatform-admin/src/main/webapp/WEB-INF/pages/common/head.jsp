<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8" />
<title>云普乐斯教学实验平台</title>

<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<%@ include file="/WEB-INF/pages/common/common.jsp" %>
<link rel="icon" href="${pageContext.request.contextPath}/resource/images/favicon.ico" type="image/x-icon" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/font-awesome.min.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/ace-fonts.min.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
<![endif]-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
  <link rel="stylesheet" href="assets/css/ace-ie.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${pageContext.request.contextPath}/resource/assets/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
<script src="assets/js/html5shiv.js"></script>
<script src="assets/js/respond.js"></script>
<![endif]-->
<!-- basic scripts -->
<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/resource/assets/js/jquery.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="assets/js/jquery-1.11.3.js"></script>
<![endif]-->
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var basePath = '<%=basePath%>';
	var userName = '${userName}';
	if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/resource/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<!--[if lte IE 8]>
  <script src="assets/js/excanvas.js"></script>
<![endif]-->

<script src="${pageContext.request.contextPath}/resource/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/resource/assets/js/bootstrapValidator.min.js"></script>

