$(function() {
	function login() {
		var form = liger.get("form1");
		var data = form.getData();
		for ( var field in data) {
			form.hideFieldError(field);
		}
		if (!data.username) {
			form.showFieldError('username', '用户名不能为空！')
		}
		if (!data.password) {
			form.showFieldError('password', '密码不能为空！')
		}
		$.post("login", data, function(data) {
			if(data.ok){
				$.ligerDialog.success('登录成功！');
				location.href = 'frame';
			} else {
				$.ligerDialog.error(data.msg);
			}
		}, "json");
	}
	function reset() {
		var form = liger.get("form1");
		form.setData({});
	}
	$.ligerDialog.open({
		target : $("#form1")
	});

	$("#form1").ligerForm({
		labelAlign : 'right',
		inputWidth : 170,
		labelWidth : 60,
		space : 40,
		fields : [ {
			name : "username",
			display : "用户名",
			type : "text"
		}, {
			name : "password",
			display : "密码",
			type : "password"
		} ],
		buttons : [ {
			text : "登录",
			width : 60,
			click : login
		}, {
			text : "重置",
			width : 60,
			click : reset
		} ]
	});
});