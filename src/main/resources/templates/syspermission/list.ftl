 
<form id="pagerForm" method="post" action="sysPermission/list">
	 
	<input   type="hidden" name="pageNum" value="${((page.pageIndex)!0)}" />
	<input type="hidden" name="numPerPage" value="${(page.pageSize)!}" />
 	<input type="hidden" name="keyword" value="${(keyword)!}" />
</form>
 
 

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="sysPermission/list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称：<input type="text" name="keyword" />
				</td>
			 
				 
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">&nbsp;&nbsp;检&nbsp;&nbsp;&nbsp;&nbsp;索&nbsp;&nbsp;</button></div></div></li>
 
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<@shiro.hasPermission name="sysPermission:add">
			<li><a class="add" href="sysPermission/add" target="dialog" rel="save"><span>添加</span></a></li>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="sysPermission:del">
			<li><a class="delete" href="sysPermission/del?id={sid}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			</@shiro.hasPermission>
			<@shiro.hasPermission name="sysPermission:upd">
			<li><a class="edit" href="sysPermission/upd?id={sid}" target="dialog"><span>修改</span></a></li>
			</@shiro.hasPermission>
			<li class="line">line</li>
		 </ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th orderField="id"  >ID</th>
				<th>名称</th>
				<th>描述</th>
				<th>code</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<#list list as a>
			<tr target="sid" rel="${a.id}">
				<td>${a.id!}</td>
				<td>${a.name!}</td>
				<td>${a.description!}</td>
				<td>${a.code!}</td>
				<td>
					<a href="sysPermission/del?id=${a.id}" title="确定要删除这条记录么?"  target="ajaxTodo">删除</a>
				</td>
			</tr>
			</#list> 
		</tbody>
	</table>
	
		<#include "../include_page/page.ftl"/>
	
</div>
