 
<form method="post" action="sysuser/updPwdDo" class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
	
	<div class="formBar">
		<ul>
			<li><button type="submit" class="buttonActive">保存</button></li>
			<li><button type="button" class="button close">取消</button></li>
		</ul>
	</div>
	<div class="pageContent">
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>
				
				密码：</dt>
				<dd>
				 
				<input name="id" type="hidden" value=" ${(RequestParameters.id)!""}" />
				<input id="w_validation_pwd" type="password" name="password" class="required alphanumeric" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
					
				</dd>
			</dl>
			<dl>
				<dt>密码确认：</dt>
				<dd>
				<input type="password" name="repassword" class="required" equalto="#w_validation_pwd"/>
				</dd>
			</dl>
			 
			 
		</div>
	</div>
</form>