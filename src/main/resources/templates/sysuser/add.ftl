 
<form method="post" action="sysuser/save.do" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
	<input name="id" type="hidden" value="${(model.id)!}" />
	<div class="formBar">
		<ul>
			<li><button type="submit" class="buttonActive">保存</button></li>
			<li><button type="button" class="button close">取消</button></li>
		</ul>
	</div>
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>登录名称：</dt>
				<dd><input class="required" name="loginName" type="text" size="30" value="${(model.loginName)!}"/></dd>
			</dl>
			<dl>
				<dt>用户姓名：</dt>
				<dd><input class="required" name="name" type="text" size="30" value="${(model.name)!}"/></dd>
			</dl>
			<dl>
				<dt></dt>
				<dd>默认密码：123456</dd>
			</dl>
			 
		</div>
	</div>
</form>