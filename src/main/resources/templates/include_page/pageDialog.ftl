<#if page??>
<div class="pageBar panelBar">
	<div class="pages">
		<span>显示</span>
		<select class="combox" name="numPerPage" onchange="dwzPageBreak({targetType:'dialog', numPerPage:this.value})">
			<option value="30"${(page.pageSize=30)?then(' selected="true"','')}>30</option>
			<option value="50"${(page.pageSize=50)?then(' selected="true"','')}>50</option>
			<option value="100"${(page.pageSize=100)?then(' selected="true"','')}>100</option>
		</select>
		<span>条，共${page.totalCount}条</span>
	</div>
	<div class="pagination" targetType="dialog" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageIndex}"></div>
</div>
</#if>