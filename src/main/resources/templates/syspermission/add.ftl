 
<form method="post" action="sysPermission/save.do" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
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
				<dt>描述：</dt>
				<dd><input class="required" name="description" type="text" size="30" value="${(model.description)!}"/></dd>
			</dl>
			<dl>
				<dt>Code：</dt>
				<dd><input class="required" name="code" type="text" size="30" value="${(model.code)!}"/></dd>
			</dl>
			<#if !(model)?? || model.parentId!=0>
			<dl>
			<dt>父菜单：</dt>
			<dd>
			
			<select class="combox" name="parentId" >
				<option value="0">根节点</option>
				<#list parent as a>
				<option value="${a.id}">${a.name}</option>
				</#list>
			</select>
			
			</dt>
			</dl>
			</#if>
			
		</div>
	</div>
</form>