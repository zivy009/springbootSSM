 
<form id="pagerForm" method="post" action="demo/list">
	 
	<input   type="hidden" name="pageNum" value="${((page.pageIndex)!0)}" />
	<input type="hidden" name="numPerPage" value="${(page.pageSize)!}" />
 	<input type="hidden" name="keyword" value="${(keyword)!}" />
</form>
 
 

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo/list" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称：<input type="text" name="keyword" />
				</td>
			 
				<td>
					添加日期：<input name="addtime" type="text" class="date" readonly="true" />
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
			<li><a class="add" href="demo/add" target="dialog" rel="save"><span>添加</span></a></li>
			<li><a class="delete" href="demo/del?id={sid}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="demo/add?id={sid}" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th orderField="id"  >ID</th>
				<th>名称</th>
				<th>时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<#list list as a>
			<tr target="sid" rel="${a.id}">
				<td>${a.id!}</td>
				<td>${a.name!}</td>
				<td>${a.addtime!?string("yyyy-MM-dd")}</td>
				<td>
					<a href="demo/del?id=${a.id}" title="确定要删除这条记录么?"  target="ajaxTodo">删除</a>
				</td>
			</tr>
			</#list> 
		</tbody>
	</table>
	
		<#include "../include_page/page.ftl"/>
	
</div>
