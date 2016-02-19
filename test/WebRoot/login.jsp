﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>欢迎登录后台管理系统</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="js/jquery.js"></script>
	<script src="js/cloud.js" type="text/javascript"></script>
	
	<script language="javascript">
		$(function(){
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    })  
	});  
	
	
	function login() {
    // console.info("点击了登录");
    var userName = $("#aName").val();
    // console.info(userName)
    var userPass = $("#aPass").val();
    // console.info(userPass);
    if (userName == "" || userName == null) {
        alert("用户名不能为空");
        return false;
    } else if (userPass == "" || userPass == null) {
        alert("密码不能为空");
        return false;
    } else {
        return true;
    }
}
	
	</script> 
	
	
	

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  
    
<div class="logintop">    
    <span>欢迎登录智能路灯监控平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    <span class="systemlogo"></span>    
    <div class="loginbox">
   
    <form name="loginForm" action="Login.action" method="post">
    <ul>
    <li><input id="aName" name="admin.adminName" type="text" class="loginuser" value="用户名" onclick=""/></li>
    <li><input id="aPass" name="admin.password" type="password" class="loginpwd" value="" onclick=""/></li>
    <li><input name="" type="submit" class="loginbtn" value="登录"  onclick="login();"  />
    <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>
    <label><a href="#">忘记密码？</a></label></li>
     <s:actionerror/>
    </ul>
    </form>
    </div>
    </div>
    
</body>
</html>
