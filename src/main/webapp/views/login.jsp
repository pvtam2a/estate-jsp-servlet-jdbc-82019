<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Icon -->
			<div class="fadeIn first">
				<img src="<c:url value='/template/login/image/client-login-icon-4.gif' />" id="icon" class="icon-login-from" alt="User Icon" /> 
			</div>

			<!-- Login Form -->
			<form action="<c:url value='login' />" method="post" id="loginForm">
				<input type="text" id="userName" class="fadeIn second" name="userName" placeholder="Tên đăng nhập"> 
					
				<input type="password" id="password" class="fadeIn third" name="password" placeholder="Mật khẩu"> 
				
				<input	type="hidden" name="action" value="login">
				<input	type="submit" class="fadeIn fourth" value="Đăng Nhập">
			</form>

			<!-- Remind Passowrd -->
			<!-- <div id="formFooter">
				<a class="underlineHover" href="#">Forgot Password?</a>
			</div> -->

		</div>
	</div>
</body>
</html>