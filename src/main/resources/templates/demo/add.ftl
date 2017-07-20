 
<form method="post" action="demo/save.do" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
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
		</div>
	</div>
</form>