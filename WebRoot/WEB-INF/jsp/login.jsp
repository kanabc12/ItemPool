<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>登陆</title>
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="wrap">
		<div id="header"></div>
		<div id="content-wrap">
			<div class="space"></div>
			<form action="index.html" method="post">
				<div class="content">
					<div class="field">
						<label>账 户：</label><input class="username" name="" type="text" />
					</div>
					<div class="field">
						<label>密 码：</label><input class="password" name="" type="password" /><br />
					</div>
					<div class="field">
						<label>验证码：</label><input class="password" name="" type="password" /><br />
					</div>
					<div class="btn">
						<input name="" type="submit" class="login-btn" value="" />
					</div>
				</div>
			</form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>
