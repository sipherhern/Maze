<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected");
		$(this).addClass("selected");
	})	
});
</script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>

<script type="text/javascript">
$(document).ready(function(e) {
    $(".select").uedSelect({
		width : 110		  
	});
});
</script>

<script>
$(function () {
    $("#checkAll").click(function () {
        var checked_status = this.checked;
        $("input[name=check]").each(function () {
            this.checked = checked_status;
        });
    });
});
</script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">单灯报警信息</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <div class="tools">
    
    	<ul class="toolbar">
      	<li> 
	        <div class="vocation">
			    <select class="select">
				    <option>全警报</option>
					<option>掉线</option>
					<option>防盗</option>
					<option>过温</option>
				    <option>状态异常</option>
				    <option>过流</option>
			    </select>
		    </div>
	    </li>
        <li class="click"><span><img src="images/magglass.gif" /></span>搜索</li>
        <li class="click"><span><img src="images/t03.png" /></span>删除所选</li>
        </ul>
               
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    </div>
    </div>
    
    
    <table class="imgtable">
    <thead>
    <tr>
	    <th><input type="checkbox" id="checkAll" /></th>
	    <th>删除</th>
	    <th>终端号</th>
	    <th>终端名</th>
	    <th>杆号</th>
	    <th>报警内容</th>
	    <th>时间</th>
    </tr>
    </thead>
    
    <tbody>
    
    <tr>
	    <td><input type="checkbox" name="check" /></td>
	    <td><a href="#">非常不错的国外后台模板，支持HTML5</a><p>发布时间：2013-10-12 09:25:18</p></td>
	    <td>后台界面<p>ID: 82122</p></td>
	    <td>开放浏览</td>
	    <td>admin</td>
	    <td>已审核</td>
	    <td>128</td>
    </tr>
    
     <tr>
	    <td><input type="checkbox" name="check" /></td>
	    <td><a href="#">非常不错的国外后台模板，支持HTML5</a><p>发布时间：2013-10-12 09:25:18</p></td>
	    <td>后台界面<p>ID: 82122</p></td>
	    <td>开放浏览</td>
	    <td>admin</td>
	    <td>已审核</td>
	    <td>128</td>
    </tr>
    </tbody>
    </table>
 
   
    <div class="pagin">
    	<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    

    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
		<div class="tipinfo">
		    <span><img src="images/ticon.png" /></span>
		    <div class="tipright">
			    <p>是否确认对信息的修改 ？</p>
			    <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
		    </div>
	    </div>
		    
	    <div class="tipbtn">
		    <input name="" type="button"  class="sure" value="确定" />&nbsp;
		    <input name="" type="button"  class="cancel" value="取消" />
	    </div>
	</div>
    
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
    
</body>

</html>
