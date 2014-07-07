<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../javascript/util.js"></script>
<script type="text/javascript">
	$(function() {
		$('#loginForm').form('clear');
		onPressKey($('#loginForm'), $('#btnSubmit'));
	});
	function submitForm() {
		$('#loginForm').form('submit', {
			url : '${pageContext.request.contextPath}/user/ajaxLogin',
			method : 'post',
			success : function(data) {
				if (data == 'true') {
					location.href = "main.jsp";
				} else {
					$.messager.show({
						title : '提示',
						msg : '用户名和密码不匹配!',
						closable : false
					});

				}
			}
		});
	}
</script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font: 12px 宋体;
	background: #4BB8EF url(../Images/bg.gif) repeat-x;
}

img {
	border: 0;
}

.login-top {
	width: 100%;
	height: 186px;
	margin: 147px auto 0;
	background: url(../Images/login_01.gif) no-repeat center 0;
}

.login-area {
	width: 100%;
	height: 140px;
	margin: 0 auto;
	background: url(../Images/login_02.gif) no-repeat center 0;
}

.login-area form {
	width: 290px;
	margin: 0 auto;
}

.login-area label {
	clear: left;
	float: left;
	margin-top: 13px;
	width: 60px;
	font: 600 14px 宋体;
}

.login-area  input {
	width: 122px;
	height: 16px;
	margin-top: 11px;
	border: 1px #767F94 solid;
	font: 12px/16px 宋体;
}

input.login-sub {
	width: 204px;
	height: 34px;
	border: 0;
	background: url(../Images/login_sub.gif) no-repeat 90px 1px; *
	margin-top: 5px;
}

.login-copyright {
	width: 100%;
	height: 30px;
	margin: 18px auto 0;
	background: url(../Images/copyright.gif) no-repeat center 0;
}
</style>
</head>

<body>
	<div class="login-top"></div>
	<div class="login-area">
		<form id="loginForm">
			<label> 工&nbsp;&nbsp;号： </label> <input type="text"
				name="userinfo.userId" class="easyui-validatebox" required="true" />
			<label> 密&nbsp;&nbsp;码： </label> <input type="password"
				name="userinfo.passWord" class="easyui-validatebox" required="true" />

			<input id="btnSubmit" type="button" class="login-sub"
				onclick="submitForm()" />
		</form>
	</div>
	<div class="login-copyright"></div>
</body>
</html>
