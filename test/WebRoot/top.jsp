<%@ page language="java" import="java.util.*" pageEncoding="gb2312" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�ޱ����ĵ�</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//���������л�
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.jsp" target="_parent"><img src="images/logo.png" title="ϵͳ��ҳ" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="default.jsp" target="rightFrame" class="selected"><img src="images/icon01.png" title="����̨" /><h2>����̨</h2></a></li>
    <li><a href="imgtable.jsp" target="rightFrame"><img src="images/icon02.png" title="ģ�͹���" /><h2>ģ�͹���</h2></a></li>
    <li><a href="imglist.jsp"  target="rightFrame"><img src="images/icon03.png" title="ģ�����" /><h2>ģ�����</h2></a></li>
    <li><a href="tools.jsp"  target="rightFrame"><img src="images/icon04.png" title="���ù���" /><h2>���ù���</h2></a></li>
    <li><a href="computer.jsp" target="rightFrame"><img src="images/icon05.png" title="�ļ�����" /><h2>�ļ�����</h2></a></li>
    <li><a href="tab.jsp"  target="rightFrame"><img src="images/icon06.png" title="ϵͳ����" /><h2>ϵͳ����</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="����"  class="helpimg"/></span><a href="#">����</a></li>
    <li><a href="#">����</a></li>
    <li><a href="login.jsp" target="_parent">�˳�</a></li>
    </ul>
     
    <div class="user">
    <span>${sessionScope.admin}</span>
    <i>��Ϣ</i>
    <b>0</b>
    </div>    
    
    </div>

</body>
</html>
