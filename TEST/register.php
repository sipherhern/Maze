<?php
include('connectToMysql.php');
header("Content-Type:text/html;charset=utf-8");
	
$username 	= trim($_POST['username']); 
$password		= $_POST['password'];
$phone=$_POST['phone'];
$name		= trim($_POST['name']);
$email		= trim($_POST['email']);
if (!empty($username)) 
{		// �û���д�����ݲ�ִ�����ݿ����
    // ������֤, empty()�����жϱ��������Ƿ�Ϊ��
    if (empty($username) || empty($email) 
            || empty($password)|| empty($phone) ) {
        echo '�������벻����';
        exit;
    }
    if (strlen($password) < 6 || strlen($password) > 30) {
        echo '���������6��30���ַ�֮��';
        exit;
    }
    // ��ͻ�����֤Emailʱ��ͬ��������ʽ
    $pattern = "/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/";
    if (!preg_match($pattern, $email)) {
        echo 'Email��ʽ���Ϸ�!';
        exit;
    }
	 $result=$db->query("select * from user where username='$username'" );
	  if ($result && mysql_num_rows($result) > 0)
	   {
       $db->Get_admin_msg("register.php","���û����ѱ�ռ�ã�������ע��");
   		}
	else
	{
		$pwd = MD5($pwd);
		 $db->query("insert into  `user` set username='$_POST[username]',password='$_POST[password]',email='$_POST[email]',phone='$_POST[phone]',comment='$_POST[comment]'");
		  $db->Get_admin_msg("login.php","ע��ɹ������¼");	
	}	
}
?>

<!DOCTYPE html>
<html>
<head>
<title>�û�ע��</title>
<link href="css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords" content="Pink Contact Form ,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements"./>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

<!--webfonts-->
<link href='http://fonts.useso.com/css?family=Lato:100,300,400,700,100italic,300italic,400italic|Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Raleway:400,200,300,500,600,800,700,900' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script language='javascript'>
<!--
// ��֤��������Ч�Եĺ���
// ����������trueʱ��˵����֤�ɹ��������������ύ
// ����������falseʱ��˵����֤ʧ�ܣ������ݱ���ֹ�ύ
function doCheck()
{
    var username   = document.frmRegister.username.value;
    var password         = document.frmRegister.password.value;
    var phone = document.frmRegister.phone.value;
    var name        = document.frmRegister.name.value;
    var email       = document.frmRegister.email.value;
    
    if (username == '') {
        alert('�������û���!'); return false;
    }
	if (email == '') {
        alert('������Email!'); return false;
    }
    if (password == '') {
        alert('����������!'); return false;
    }    
	if (phone == '') {
        alert('���������ĵ绰!'); return false;
    }
    
    if (password.length < 6 || password.length > 30) {
        alert('���������6��30���ַ�֮��!'); return false;
    }
    
    // ʹ��������ʽ��֤Email�ĸ�ʽ
    var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (! pattern.test(email) ) {
        alert('Email��д����ȷ!'); return false;
    }
    
    return true;
}
-->
</script>
</head>
<body>
	<h1>�û�ע��</h1>
     
	<div class="login-01">
			<form  name="frmRegister" method="post"  action="register.php"  onSubmit="return doCheck();">
				<ul>
				<li class="first">
					<a href="#" class=" icon user"></a>
                    <input type="text" class="text" name="username" value="" placeholder="�����������û��� / Username" >
					<div class="clear"></div>
				</li>
				<li class="first">
					<a href="#" class=" icon email"></a><input type="text" class="text" name="email" value="" placeholder="�������������� / Email">
					<div class="clear"></div>
				</li>
                <li class="first">
					<a href="#" class=" icon email"></a><input type="password" class="text" name="password"  placeholder="������6-30���ַ�֮�� / Password" value="" width=275 height="55">
					<div class="clear"></div>
				</li>
				<li class="first">
					<a href="#" class=" icon phone"></a><input type="text" class="text" name="phone" value=""  placeholder="���������ĵ绰 / Phone" >
					<div class="clear"></div>
				</li>
				<li class="second">
				<a href="#" class=" icon msg"></a><textarea value="Message" name="comment" placeholder="�༭���ĸ���ǩ�� / Comment"></textarea>
				<div class="clear"></div>
				</li>
			</ul>
            
			<input type="submit" name="into_news" value="Submit" >
			<div class="clear"></div>
		</form>
</div>
	<!--start-copyright-->
   		<div class="copy-right">
   			<div class="wrap">
				<p>Copyright &copy; 2015.Personal space.���˿ռ�</a></p>
		</div>
	</div>
	<!--//end-copyright-->
</body>
</html>