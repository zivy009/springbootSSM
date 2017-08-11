 
<form method="post" action="sysRole/save.do" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
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
				<dt>名称：</dt>
				<dd><input class="required" name="name" type="text" size="30" value="${(model.name)!}"/></dd>
			</dl>
			<dl>
				<dt>code：</dt>
				<dd><input class="required" name="code" type="text" size="30" value="${(model.code)!}"/></dd>
			</dl>
			<dl>
				<dt>描述：</dt>
				<dd><input class="required" name="description" type="text" size="30" value="${(model.description)!}"/></dd>
			</dl>
		</div>
	</div>
</form>