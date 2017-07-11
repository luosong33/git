var tree, form1, form2;
function save_base() {
	var data = form1.getData();
	console.log(data);
	for ( var field in data) {
		form1.hideFieldError(field);
	}
	if (!data.name) {
		form1.showFieldError('name', '名称不能为空！')
	}
	$.post("save_base", data, function(data) {
		if (data.ok) {
			$.ligerDialog.hide();
			$.ligerDialog.confirm('保存成功！是否重新加载？', function(yes) {
				if (yes) {
					tree.reload();
				}
			});
		} else {
			$.ligerDialog.error(data.msg);
		}
	}, "json");
}
function tips(state, title, message) {
	mini.showTips({
		content : "<b>" + title + "</b> <br/>" + message,
		state : state,
		x : 'center',
		y : 'middle',
		timeout : 3000
	});
}
function create(pid) {
	mini.prompt("请输入菜单名称：", "请输入", function(action, value) {
		if (action == "ok") {
			var param = {
				name : value
			};
			if (pid) {
				param.pid = pid;
			}
			$.post("create", param, function(data) {
				if (data.ok) {
					var tree = mini.get("tree1");
					if (pid == null) {
						tree.addNode(data.data, "add", null);
					} else {
						var parent = tree.getNode('' + pid);
						tree.addNode(data.data, "add", parent);
					}
					tips('success', '成功', '数据保存成功');
				} else {
					tips('danger', '失败', '数据保存失败');
				}
			}, "json");
		}
	});
}
function addRoot() {
	create(null);
}
function addChild() {
	var tree = mini.get("tree1");
	var node = tree.getSelectedNode();
	if (node != null) {
		create(node.id);
	}
}
function removeMenu() {
	var tree = mini.get("tree1");
	var node = tree.getSelectedNode();
	if (node) {
		if (confirm("确定删除选中节点?")) {
			$.post("delete", {
				id : node.id,
			}, function(data) {
				if (data.ok) {
					tips('success', '成功', '数据保存成功');
					tree.removeNode(node);
				} else {
					tips('danger', '失败', '数据保存失败');
				}
			}, "json");
		}
	}
}
function onMoveNode(e) {
	var tree = mini.get("tree1");
	var node = tree.getSelectedNode();

	alert("moveNode");
}
function saveData() {
	var tree = mini.get("tree1");
	var data = tree.getData();
	var json = mini.encode(data);

	alert("在线演示，不提供保存，下载开发包内有此功能。");
}
$(function() {
	mini.parse();
	var layout = mini.get("layout1");
});