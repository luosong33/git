/**
 * Created by xld on 2016/7/26.
 */
var id = 0;
var status;
$(function() {
	$('[data-toggle="popover"]').popover(
					{
						html : true,
						template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div><div><button>是</button><button>否</button></div></div>'
					});

	$(document).on('click', '.system-management ul.nav-header>li', function() {
		var $this = $(this);
		var targetId = $this.find('a').attr('href');
		$.data(document.body, 'currentTab', targetId);
	});

	$(document).on('click', '.j-submit', function(event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var operation = $.data(document.body, 'operation') // new or edit
		var $this = $(this);

		if (operation == "organize-new") {// 新增部门
			addDept();
		} else if (operation == "role-new") {// 新增角色
			submitNewRole("add");
		} else if (operation == "role-edit") {// 编辑角色
			submitNewRole("edit");
		} else if (operation == "reset") {// 重置密码
			reSetPassword();
		} else if (operation == "user-new") {// 新增用户
			submitNewUser("add");
		}  else if (operation == "user-edit") {// 新增用户
			submitNewUser("edit");
		}
	});

	$(document).on('click', '.j-cancel', function(event) {
		var $this = $(this);
	});

	$(document).on('click', '.btn-search', function(event) {
		event.preventDefault();
		var id = $(this).attr("id");
		if (id == "user-search") {
			$('#userTable').bootstrapTable('refresh', {
				url : '/user/listData?'+$("#user-search-form").serialize()
			});
		} else if (id == "role-search") {
			$('#roleTable').bootstrapTable('refresh', {
				url : '/user/listRole?' +$("#role-search-form").serialize()
			});
		} else if (id == "dept-search") {
			$('#deptTable').bootstrapTable('refresh', {
				url : '/user/listDept?'+$("#dept-search-form").serialize()
			});
		}
	});

	$('#systemManageModal').on(
			'show.bs.modal',
			function(event) {
				var button = $(event.relatedTarget) // Button that triggered the
				// modal
				var operation = button.data('operation') // new or edit
				var currentTab = $.data(document.body, 'currentTab')
						|| "#userManage"; // userManage or roleManage
				var modal = $(this)

				modal.find('.modal-dialog').removeClass('modal-md').addClass(
						'modal-lg')

				var modalTitle = "";
				var templateSource = "";
				var type = "";
				if (currentTab == "#userManage") {
					if (operation == "new") {
						type = "addUser";
						operation = "user-new"
						modalTitle = "新增用户";
						templateSource = $("#add-new-user-template").html();
					} else if (operation == "reset") {
						type = "reset";
						modalTitle = "重置密码";
						modal.find('.modal-dialog').removeClass('modal-lg').addClass('modal-md');
						templateSource = $("#reset-password-template").html();
					} else {
						type = "editUser";
						operation = "user-edit"
						modalTitle = "编辑用户";
						templateSource = $("#edit-user-template").html();
					}

				} else {
					if (operation == "new") {
						type = "addRole";
						operation = "role-new"
						modalTitle = "新增角色";
						templateSource = $("#add-new-role-template").html();
					} else if (operation == "organize-new") {
						type = "addDept";
						modalTitle = "新增部门";
						modal.find('.modal-dialog').removeClass('modal-lg').addClass('modal-md');
						templateSource = $("#new-organize-template").html();
					} else if (operation == "organize-edit") {
						type = "editDept";
						modalTitle = "编辑部门";
						modal.find('.modal-dialog').removeClass('modal-lg').addClass('modal-md');
						templateSource = $("#new-organize-template").html();
					} else {
						type = "editRole";
						operation = "role-edit"
						modalTitle = "编辑角色";
						templateSource = $("#edit-role-template").html();
					}
				}
				$.data(document.body, 'operation', operation);
				modal.find('.modal-title').text(modalTitle)
				var templateData = {};
                if (type != '') {
                	$.ajax({
        				cache : true,
        				type : "POST",
        				url : "/user/toEdit",
        				data : {
        					"id" : id,
        					"type" : type
        				},// 你的formid
        				// async: false,//是否同步,默认异步
        				dataType : "json",
        				error : function(request) {
        					setErrorAlert("超时或系统异常");
        				},
        				success : function(data) {
        					if (data && data.statusCode == 200) {
        						templateData = data.data;
        						
        						var template = Handlebars.compile(templateSource);
        						modal.find('.modal-body').html(template(templateData)); // todo: pass
        						// get functions by platformId
        				        $("#edit-role-platform-id").on("change",function(event){
        				        	var platformId = $("#edit-role-platform-id").val();
        				        	var id = "999999";
        				        	if (platformId == "102000"){
        				        		id = platformId;
        				        	}
        				        	var templateSource = $("#function-"+id).html();
				                	var template= Handlebars.compile(templateSource);
				                	$("#edit-function").html(template({}));
        				        });
        				        $("#add-role-platform-id").on("change",function(event){
        				        	var platformId = $("#add-role-platform-id").val();
        				        	var id = "999999";
        				        	if (platformId == "102000"){
        				        		id = platformId;
        				        	}
        				        	var templateSource = $("#function-"+id).html();
				                	var template= Handlebars.compile(templateSource);
				                	$("#add-function").html(template({}));
        				        });
        				        
        				        $("#add-user-dept-id").on("change",function(event){
        				        	var deptId = $("#add-user-dept-id").val();
        				        	$.get("/user/getRolesByDeptId?deptId="+deptId, function(result){
        				        		if (result && result.statusCode == 200) {
        	        						templateData = result.data;
        	        						var templateSource = $("#get-departs-by-platform-template").html();
        	        						var template= Handlebars.compile(templateSource);
        	        						$("#add-user-role").html(template(templateData));
        				        		}
        				        	});
        				        });
        					}
        				}
        			}).then(
					  function() {
					    if (templateData.role) {
					    	$("#role-id").val(templateData.role.id);
					    	$("#edit-role-platform-id").val(templateData.role.platformId);
					    	$("#edit-role-platform-id").trigger('change');
					    	$("#edit-role-name").val(templateData.role.name);
					    	$("#edit-role-dept-id").val(templateData.role.deptId);
					    	var functions = templateData.role.functions;
					    	$("input[name=function]").each( function () {
					    		if(functions.indexOf($(this).val()) >= 0) {
                    				$(this).prop('checked', true);
                    			}
                        	});
					    };
					    if (templateData.account) {
					    	$("#user-id").val(templateData.account.id);
					    	$("#edit-user-dept-id").val(templateData.account.department);
					    	$("#edit-user-account").val(templateData.account.account);
					    	$("#account-span").text(templateData.account.account);
					    	$("#edit-user-mail").val(templateData.account.mail);
					    	$("#edit-user-password").val(templateData.account.password);
					    	$("#edit-user-phone").val(templateData.account.phone);
					    	$("#edit-user-name").val(templateData.account.name);
					    	var roles = templateData.account.roles;
					    	$("input[name=role]").each( function () {
					    		if(roles.indexOf($(this).val()) >= 0) {
                    				$(this).prop('checked', true);
                    			}
                        	});
					    };
					  }
        			);
                }
				

			})

	$(document).on(
			'click',
			'input[type="checkbox"].cbxAll',
			function() {
				var $this = $(this), $parentContainer = $this.closest('.role-section');

				$parentContainer.find('.row-body input[type="checkbox"]').prop(
						'checked', $this.prop('checked'));
			})

	// checked on child checkbox
	$(document).on(
					'click',
					'.row-body input[type="checkbox"]',
					function() {
						var $this = $(this), $parentContainer = $this.closest('.role-section');

						var cbxAll = $parentContainer
								.find('input[type="checkbox"].cbxAll'), cbxChildren = $parentContainer
								.find('.row-body input[type="checkbox"]'), cbxCheckedChildren = $parentContainer
								.find('.row-body input[type="checkbox"]:checked');
						var totalCount = cbxChildren.length, checkedCount = cbxCheckedChildren.length;

						cbxAll.prop('checked', totalCount == checkedCount);

					})
})
$('#userTable').bootstrapTable(
				{
					onLoadSuccess : function() {
						$('[data-toggle="popover"]').popover(
										{
											html : true,
											template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div><div><button onclick="setStatus()">是</button><button onclick="hidePopover(this)">否</button></div></div>'
										});
						
						$('[data-toggle="popover2"]').popover(
								{
									html : true,
									template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div><div><button onclick="reSetPassword()">是</button><button onclick="hidePopover(this)">否</button></div></div>'
								});
					},
					onClickCell : function(field, value, row, $element) {
						id = row.id;
						status = row.status;
						return false;
					}
				});
$('#roleTable').bootstrapTable(
				{
					onLoadSuccess : function() {
						$('[data-toggle="popover"]').popover(
										{
											html : true,
											template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div><div><button onclick="setRoleStatus()">是</button><button onclick="hidePopover(this)">否</button></div></div>'
										});
					},
					onClickCell : function(field, value, row, $element) {
						id = row.id;
						status = row.status;
						return false;
					}
				});
$('#deptTable').bootstrapTable(
				{
					onLoadSuccess : function() {
						$('[data-toggle="popover"]').popover(
										{
											html : true,
											template : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div><div><button onclick="delDept(this)">是</button><button onclick="hidePopover(this)">否</button></div></div>'
										});

					},
					onClickCell : function(field, value, row, $element) {
						id = row.id;
						return false;
					}
				});

function uoFormatter(value, row, index) {
	var str = '<button class="btn-reset" data-container="body" title="确认" data-toggle="popover2" data-placement="bottom" data-content="是否进行重置密码？" data-id='
			+ row.id + ' >重置密码</button>';
	str += '<button class="btn-edit" data-operation="edit" data-toggle="modal" data-target="#systemManageModal"data-id=' + row.id + '>编 辑</button>'
	if (row.status == 0) {
		str += '<button class="btn-start" data-container="body" title="确认" data-toggle="popover" data-placement="bottom" data-content="是否进行禁用？" data-id='
				+ row.id + ' data-status=' + row.status + ' >禁用</button>';
	} else if (row.status == 1) {
		str += '<button class="btn-start" data-container="body" title="确认" data-toggle="popover" data-placement="bottom" data-content="是否进行启用？" data-id='
				+ row.id + ' data-status=' + row.status + ' >启用</button>';
	}
	return str;
}

function roFormatter(value, row, index) {
	var str = '<button class="btn-edit" data-operation="edit" data-toggle="modal" data-target="#systemManageModal" data-id=' + row.id + '>编 辑</button>';
	if (row.status == 0) {
		str += '<button class="btn-start" data-container="body" title="确认" data-toggle="popover" data-placement="bottom" data-content="是否进行禁用？" data-id='
				+ row.id + ' data-status=' + row.status + ' >禁用</button>';
	} else if (row.status == 1) {
		str += '<button class="btn-start" data-container="body" title="确认" data-toggle="popover" data-placement="bottom" data-content="是否进行启用？" data-id='
				+ row.id + ' data-status=' + row.status + ' >启用</button>';
	}
	return str;
}

function doFormatter(value, row, index) {
	var str = '<button class="btn-start" data-container="body" title="确认" data-toggle="popover" data-placement="bottom" data-content="是否进行删除？" data-id='
			+ row.id + ' >删除</button>';

	return str;
}

function platFormatter(value, row, index) {
	var jsonData = {"100100": "聚贸电商总平台", "100200": "聚贸化工", "100300": "聚贸有色" , "100400": "聚运通" , "100700": "煤炭" , "100800": "钢材" , "100900": "矿产" 
		, "101000": "农产品" , "101100": "工业品" , "101200": "消费品" , "101300": "机械" , "101400": "食品" , "101500": "金融" , "101600": "大数据" , "101700": "聚认证" 
		, "101800": "聚智能" , "101900": "聚咨询", "102000": "聚达通", "102100": "支付结算中心", "102200": "石油" };
	return jsonData[row.platformId];
}

function refresh_table(id) {
	if (id == "userTable"){
		$("#"+id).bootstrapTable('refresh', {
			url : '/user/listData?1=1',
			toOnPage : 'true'
		});
	} else if (id == "roleTable"){
		$("#"+id).bootstrapTable('refresh', {
			url : '/user/listRole?1=1',
			toOnPage : 'true'
		});
	} else if (id == "deptTable"){
		$("#"+id).bootstrapTable('refresh', {
			url : '/user/listDept?1=1',
			toOnPage : 'true'
		});
	}
	
}

function reSetPassword() {
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
			$('[data-toggle="popover2"]').popover('hide');
			if (data && data.statusCode == 200) {
				layer.alert(data.message);
			} else {
				if (data.message) {
					layer.alert(data.message);
				} else {
					layer.alert("操作失败");
				}
			}
		}
	});
}

function setStatus() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/user/setStatus",
		data : {
			"id" : id,
			"status" : 1-status
		},// 你的formid
		dataType : "json",
		error : function(request) {
			setErrorAlert("超时或系统异常");
		},
		success : function(data) {
			$('[data-toggle="popover"]').popover('hide');
			if (data && data.statusCode == 200) {
				layer.alert(data.message);
				refresh_table("userTable");
			} else {
				if (data.message) {
					layer.alert(data.message);
					refresh_table("userTable");
				} else {
					layer.alert("操作失败");
				}
			}
		}
	});
}

function addDept() {
	if (!$(".form-horizontal").valid())
		return false;
	$.ajax({
		cache : true,
		type : "POST",
		url : "/user/addDept",
		data : $('.form-horizontal').serialize(),// 你的formid
		dataType : "json",
		error : function(request) {
			setErrorAlert("超时或系统异常");
		},
		success : function(data) {
			if (data && data.statusCode == 200) {
				layer.alert(data.message);
				$('#systemManageModal').modal("hide");
				refresh_table("deptTable");
			} else {
				if (data.message) {
					layer.alert(data.message);
					$('#systemManageModal').modal("hide");
					refresh_table("deptTable");
				} else {
					layer.alert("操作失败");
				}
			}
		}
	});
}

function hidePopover(event) {
	$("div.popover").css("display", "none");
}

// 启用、停用，即修改角色状态。
function delDept(event) {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/user/delDept",
		data : {
			"id" : id
		},
		// async: false,//是否同步,默认异步
		dataType : "json",
		error : function(request) {
			setErrorAlert("超时或系统异常");
		},
		success : function(data) {
			if (data && data.statusCode == 200) {
				layer.alert(data.message);
				$('[data-toggle="popover"]').popover('hide');
				refresh_table("deptTable");
			} else {
				if (data.message) {
					layer.alert(data.message);
					$('[data-toggle="popover"]').popover('hide');
					refresh_table("deptTable");
				} else {
					layer.alert("操作失败");
				}
			}
		}
	});
}

//新增user
function submitNewUser(type){
	if(!$(".form-horizontal").valid()) return false;
	var roleIds=[];
	$('input:checkbox[name="role"]:checked').each(function() {
		roleIds.push($(this).val());
    });
	//非空判断
	var auths = roleIds? roleIds.toString():"";
	if(!auths){
		layer.alert("请选择角色");
		return;
	}
    var indexLoading=layer.load(0,{shade: 0.2});
    $.ajax({
        cache: true,
        type: "POST",
        url: "/user/add",
        data : {
			"id" : $("#user-id").val(),
			"department" : $("#" + type + "-user-dept-id").val(),
			"account" : $("#" + type + "-user-account").val(),
			"name" : $("#" + type + "-user-name").val(),
			"mail" : $("#" + type + "-user-mail").val(),
			"password" : $("#" + type + "-user-password").val(),
			"phone" : $("#" + type + "-user-phone").val(),
			"roles" : auths
		},
        dataType: "json",
        error: function (request) {
            setErrorAlert("超时或系统异常");
        },
        success: function (data) {
        	$('#systemManageModal').modal("hide");
			refresh_table("userTable");
            if (data && data.statusCode == 200) {
                layer.msg(data.message, {
                  time: 2000 // 2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    parent.layer.closeAll();
                }); 
            } else {
            	 layer.close(indexLoading);
                if (data.message) {
                    layer.alert(data.message);
                } else {
                    layer.alert("操作失败");
                }
            }
        }
    });
}

//新增角色
function submitNewRole(type){
	var functionIds=[];
	$('input:checkbox[name="function"]:checked').each(function() {
		functionIds.push($(this).val());
    });
	//非空判断
	var auths = functionIds? functionIds.toString():"";
	if(!auths){
		layer.alert("功能项不能为空");
		return;
	}
	if (!$(".form-horizontal").valid())
		return false;
	$.ajax({
		cache : true,
		type : "POST",
		url : "/user/addRole",
		data : {
			"id" : $("#role-id").val(),
			"platformId" : $("#" + type + "-role-platform-id").val(),
			"name" : $("#" + type + "-role-name").val(),
			"deptId" : $("#" + type + "-role-dept-id").val(),
			"functions" : auths
		},
		// async: false,//是否同步,默认异步
		dataType : "json",
		error : function(request) {
			setErrorAlert("超时或系统异常");
		},
		success : function(data) {
			if (data && data.statusCode == 200) {
				layer.alert(data.message);
				$('#systemManageModal').modal("hide");
				refresh_table("roleTable");
			} else {
				if (data.message) {
					layer.alert(data.message);
					$('#systemManageModal').modal("hide");
					refresh_table("roleTable");
				} else {
					layer.alert("操作失败");
				}
			}
		}
	});
}

function setRoleStatus() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/user/setRoleStatus",
		data : {
			"id" : id,
			"status" : 1-status
		},// 你的formid
		dataType : "json",
		error : function(request) {
			setErrorAlert("超时或系统异常");
		},
		success : function(data) {
			$('[data-toggle="popover"]').popover('hide');
			if (data && data.statusCode == 200) {
				layer.alert(data.message);
				refresh_table("roleTable");
			} else {
				if (data.message) {
					layer.alert(data.message);
					refresh_table("roleTable");
				} else {
					layer.alert("操作失败");
				}
			}
		}
	});
}
