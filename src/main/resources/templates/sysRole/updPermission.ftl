 
<form method="post" action="sysRole/updPermissionDo" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
	
	<div class="formBar">
		<ul>
			<li><button type="submit" class="buttonActive">保存</button></li>
			<li><button type="button" class="button close">取消</button></li>
		</ul>
	</div>
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			 
			<dl>
				 
				<dd>
				<input name="id" type="hidden" value="${(RequestParameters.id)!}" />
				<ul class="tree treeFolder treeCheck expand" >
				
				<#list permissions as key,val>
					<li><a >${val.name}</a>
					 
						<ul>
						<#list val.data as item>
							<li><a tname="permissionID" tvalue="${(item.id)!}" <#if item.isChecked() > checked="true" </#if> >${(item.name)!}</a></li>
						</#list>
						 
						</ul>
					</li>
 				</#list>
				</ul>

</dd>
			</dl>
			 
		</div>
	</div>
</form>