 
<form method="post" action="sysuser/updRoleDo" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
	<input name="id" type="hidden" value="${(sysUser.id)!}" />
	<div class="formBar">
		<ul>
			<li><button type="submit" class="buttonActive">保存</button></li>
			<li><button type="button" class="button close">取消</button></li>
		</ul>
	</div>
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
		 
			<dl>
				<dt>用户姓名：${(sysUser.name)!}</dt>
				
			</dl>
			 
			 <dl>
				 <#list roleAll as item>
				 <label><input type="checkbox" name="roleIds" value="${item.id}" <#if item.checked>checked="checked" </#if> /> ${item.name}</label>
				 </#list>
			 </dl>
		</div>
	</div>
</form>