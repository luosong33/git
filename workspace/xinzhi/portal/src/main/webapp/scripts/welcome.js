function newNavtab(id, text, url) {
	top.addTab(id, text, url);
}
$(function() {
	manager = $("#maingrid").ligerGrid(
			{
				columns : [
						{
							width : 100,
							display : '资源名',
							name : 'name'
						},
						{
							width : 50,
							display : '资源类型',
							name : 'type'
						},
						{
							width : 100,
							display : '项目',
							name : 'authority',
							render : function(row, index, value) {
								return value.project.name;
							}
						},
						{
							width : 100,
							display : '权限',
							name : 'authority',
							render : function(row, index, value) {
								return value.name;
							}
						},
						{
							align : 'left',
							width : 250,
							display : '资源链接',
							name : 'source',
							render : function(row, index, value) {
								return '<a href="javascript:newNavtab(\'r_'
										+ row.id + '\',\'' + row.name
										+ '\',\''
										+ row.authority.project.baseUrl + value
										+ '\')">' + value + '</a>';
							}
						} ],
				root : 'rows',
				record : 'total',
				width : '100%',
				pageSizeOptions : [ 5, 10, 15, 20 ],
				height : '97%',
				url : 'links',
				dataAction : 'server', // 服务器排序
				usePager : true, // 服务器分页
				pageSize : 50,
				scroll : true,
				frozen : true,
				alternatingRow : false,
				scrollToPage : true,
				scrollToAppend : true
			});
});