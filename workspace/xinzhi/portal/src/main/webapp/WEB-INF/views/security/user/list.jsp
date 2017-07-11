<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<%@ include file="/common/ligerui.jsp"%>
<%-- <script src="${ctx}/scripts/security/user/list.js"></script> --%>
</head>
<body>
	<div id="form1">
	</div>
	<div id="layout1">
		<div position="left"></div>
		<div position="center" title="标题">
			<h4>$("#layout1").ligerLayout({ leftWidth: 200});</h4>
			<br /> 如果上面有其他页面元素，layout会自适应调整
		</div>
	</div>
	<script>
		function query() {
			var form = new liger.get("form1");
	         var data = form.getData();
	         alert(JSON.stringify(data));
		}
		$(function() {
			$("#form1").ligerForm({
				inputWidth : 170,
				labelWidth : 90,
				space : 40,
				fields : [ {
					name : "ProductID",
					type : "hidden"
				}, {
					display : "产品名称",
					name : "ProductName",
					type : "text"
				}],
				buttons : [ {
					text : "查询",
					width : 60,
					click : query
				} ,{
					text : "查询2",
					width : 60,
					click : query
				}]
			});
			$("#layout1").ligerLayout({
				leftWidth : 200
			});
		});
	</script>
</body>
</html>