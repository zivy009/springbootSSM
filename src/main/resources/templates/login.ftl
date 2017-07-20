<!DOCTYPE>
 
<html>
<head>
 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="${ctx.contextPath}/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href=""><img src="${ctx.contextPath}/dwz/themes/default/images/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
				 
				</div>
		 
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="index.html">
					<p>
						<label>用户名：</label>
						<input type="text" name="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input" />
					</p>
					<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="${ctx.contextPath}/defaultKaptcha" onClick="${ctx.contextPath}/defaultKaptcha?d=\'+new Date()*1'" alt="" width="75" height="24" /></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="${ctx.contextPath}/dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
			 
				<div class="login_inner">
			 
			 
	 
			 
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy;   All Rights Reserved.
		</div>
	</div>
</body>
</html>