<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="layout.tld" prefix="e"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">

<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<meta name="Description" content="股东会的描述" />
<meta name="Keywords" content="股东会的关键字，关键字" />
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title><e:block name="title">Bootstrap 101 Template</e:block></title>

<e:block name="style">
	<!-- Bootstrap -->
	<link
		href="<c:url value='/res/plugin/bootstrap/css/bootstrap.min.css'/>"
		rel="stylesheet">

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</e:block>


<e:block name="more_style"></e:block>
</head>
<body>
	<!-- 页头 -->
	<e:block name="header">

	</e:block>
	<e:block name="menu">

	</e:block>
	<e:block name="content">
		<h1>你好，世界！</h1>
	</e:block>


	<!-- 页脚 -->
	<e:block name="footer">
	</e:block>


	<e:block name="script">
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		

		<!-- Bootstrap Core JavaScript -->
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script
			src="<c:url value='/res/plugin/bootstrap/js/bootstrap.min.js'/>"></script>

	</e:block>

	<e:block name="more_script">


	</e:block>
</body>
</html>