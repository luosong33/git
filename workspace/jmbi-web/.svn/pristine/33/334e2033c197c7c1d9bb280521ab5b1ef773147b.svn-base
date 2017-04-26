//为子窗口刷新父窗口表格提供方法
var table = $('#accountList');
function refresh_table() {
	// table.bootstrapTable('refresh');
	table.bootstrapTable('refresh', {
		url : '/user/listData?1=1',
		toOnPage : 'true'
	});
}
$(function() {
	window.operateEvents = {

	};

	var companyParams = {
		url : '/user/listData?1=1', // 请求后台的URL（*）
		toolbar : '#toolbar', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		method : 'post',
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		queryParams : queryParams,
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		strictSearch : true,
		clickToSelect : true, // 是否启用点击选中行
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表gradeName
		width : 1100,
		selectItemName : "id",
		idField : "id",
		columns : [
				{
					class : 'th',
					align : 'center',
					field : 'id',
					title : '序号',
					checkbox : true,
					width : 20
				},
				{
					class : 'th',
					align : 'center',
					field : 'name',
					title : '姓名',
					width : 80
				},
				{
					class : 'th',
					align : 'center',
					field : 'account',
					title : '账号',
					width : 100
				},
				{
					class : 'th',
					align : 'center',
					field : 'department',
					title : '部门',
					width : 80
				},
				{
					class : 'th',
					align : 'center',
					field : 'mail',
					title : '邮箱',
					width : 90
				},
				{
					class : 'th',
					align : 'center',
					field : 'phone',
					title : '联系方式',
					width : 90
				},
				{
					class : 'th',
					align : 'center',
					field : 'createTime',
					title : '创建时间',
					width : 100,
					formatter : function(value, row, index) {
						if (value == null || value.length <= 2) {
							return '';
						}
						return value;
					}
				},
				{
					class : 'th',
					align : 'center',
					field : 'title',
					title : '操作',
					width : 120,
					events : operateEvents,
					formatter : function(value, row, index) {
						var str = '<a class=" J_reSetPassWord" data-id=' + row.id
								+ ' >重置密码</a>';
						if (row.status == 0) {
							str += '&nbsp;<a class=" J_upOrDown" data-id='
									+ row.id + ' data-status=' + row.status
									+ ' >禁用</a>';
						} else if (row.status == 1) {
							str += '&nbsp;<a class=" J_upOrDown" data-id='
									+ row.id + ' data-status=' + row.status
									+ ' >启用</a>';
						}
						str += '&nbsp;<a class=" J_del" data-id=' + row.id
								+ ' >删除</a>';
						return str;
					}
				} ],
		onLoadSuccess : function(data) {

			// 删除
			$(".J_del").on('click', function() {
				var id = $(this).attr("data-id");
				layer.open({
					title : "删除",
					content : '确定要删除该记录吗?',
					yes : function(index, layero) {
						$.ajax({
							cache : true,
							type : "POST",
							url : "/user/delete",
							data : {
								"id" : id
							},// 你的formid
							// async: false,//是否同步,默认异步
							dataType : "json",
							error : function(request) {
								setErrorAlert("超时或系统异常");
							},
							success : function(data) {
								if (data && data.statusCode == 200) {
									layer.alert(data.message);
									refresh_table();
								} else {
									if (data.message) {
										layer.alert(data.message);
										refresh_table();
									} else {
										layer.alert("操作失败");
									}
								}
							}
						});
						layer.close(index);
					}
				})

			});

			$(".J_reSetPassWord").on('click', function() {
				var id = $(this).attr("data-id");
				 
				var message = '重置';
				layer.open({
					title : message,
					content : '确定要重置该账号的密码吗?',
					yes : function(index, layero) {
						$.ajax({
							cache : true,
							type : "POST",
							url : "/user/reSetPassword",
							data : {
								"id" : id 
							},// 你的formid
							dataType : "json",
							error : function(request) {
								setErrorAlert("超时或系统异常");
							},
							success : function(data) {
								if (data && data.statusCode == 200) {
									layer.alert(data.message);
									refresh_table();
								} else {
									if (data.message) {
										layer.alert(data.message);
										refresh_table();
									} else {
										layer.alert("操作失败");
									}
								}
							}
						});
						layer.close(index);
					}
				})

			
			});
			// 编辑同弹窗
//			$(".J_edit").on('click', function() {
//				var id = $(this).attr("data-id");
//				var url = "user/edit?id=" + id;
//				layer.open({
//					type : 2,
//					title : '修改',
//					content : [ url, 'yes' ],
//					area : [ '935px', '650px' ],
//					success : function(data) {
//
//					}
//				});
//			});
			// 启用、禁用
			$(".J_upOrDown").on('click', function() {
				var id = $(this).attr("data-id");
				var status = $(this).attr("data-status");

				var message = '';
				if (status == 0) {
					message = '禁用';
				} else {
					message = '启用';
				}
				layer.open({
					title : message,
					content : '确定要' + message + '该记录吗?',
					yes : function(index, layero) {
						$.ajax({
							cache : true,
							type : "POST",
							url : "/user/setStatus",
							data : {
								"id" : id,
								"status" : status
							},// 你的formid
							// async: false,//是否同步,默认异步
							dataType : "json",
							error : function(request) {
								setErrorAlert("超时或系统异常");
							},
							success : function(data) {
								if (data && data.statusCode == 200) {
									layer.alert(data.message);
									refresh_table();
								} else {
									if (data.message) {
										layer.alert(data.message);
										refresh_table();
									} else {
										layer.alert("操作失败");
									}
								}
							}
						});
						layer.close(index);
					}
				})

			});

		}

	};
	$(".search-button").on('click', function() {
		table.bootstrapTable('refresh', {
			url : '/user/listData?1=1'
		});
	});
	// 1.初始化Table
	doQuery();

	// 状态切换
	$(".status-tabs").find("li").unbind('click').on('click', function() {
		$(".status-tabs").find("li").removeClass('active');
		$(this).addClass('active');
		table.bootstrapTable('refresh', {
			url : '/user/listData?1=1'
		});
	});

	$(".stat-option .J_disp").on('click', function() {
		$(this).parent(".stat-option").siblings(".stat-li").toggle();
	});
	$("body").on("click", ".stat-li", function(e) {
		e ? e.stopPropagation() : event.cancelBubble = true;
	});

	$(".stat-li li").on('click', function() {
		var text = $(this).html();
		var parNode = $(this).parent(".stat-li");
		parNode.siblings(".stat-option").find("span").html(text);
		parNode.fadeOut();
	});

	function doQuery() {
		table.width(companyParams.width);
		table.bootstrapTable(companyParams);
	}

	function queryParams(pageReqeust) {
		var reqeust = common.getDetail($('.saveForm'), pageReqeust);
		reqeust.codeStatus = $('.status-tabs').find(".active").attr(
				"data-value");
		return reqeust;
	}

});

$(".btn-add").click(function() {
	var url = "/user/edit";
	layer.open({
		type : 2,
		title : '新增',
		content : [ url, 'yes' ],
		area : [ '935px', '450px' ],
		success : function(data) {

		}
	});
});
