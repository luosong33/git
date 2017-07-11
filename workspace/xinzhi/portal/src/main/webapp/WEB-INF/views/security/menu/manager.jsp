<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<%@ include file="/common/miniui.jsp"%>
<script src="${ctx}/scripts/security/menu/manager.js"></script>
</head>
<body>
	<div id="layout1" class="mini-layout">
		<div title="菜单结构树" region="west" width="300" showSplitIcon="true">
			<div id="panel1" class="mini-panel" showToolbar="true" showHeader="false" style="width: 100%; height: 100%;">
				<div property="toolbar" align="right">
					<a class="mini-button" iconCls="icon-add" onclick="addRoot()">添加根菜单</a>
					<a class="mini-button" iconCls="icon-add" onclick="addChild()">添加子菜单</a>
					<a class="mini-button" iconCls="icon-remove" onclick="removeMenu()">删除</a>
				</div>
				<ul id="tree1" class="mini-tree" url="all" showTreeIcon="true" textField="name" parentField="pid" resultAsTree="false" allowDrag="true" allowDrop="true"
					allowLeafDropIn="true">
					<!--  onbeforeload="onBeforeTreeLoad"  -->
				</ul>
			</div>
		</div>
		<div title="菜单信息编辑" region="center">
			<div id="form1">
				<input name="id" class="mini-hidden" />
				<input name="pid" class="mini-hidden" />
				<table>
					<tr>
						<td><label for="textbox1$text">菜单名称：</label></td>
						<td><input id="textbox1" name="name" class="mini-textbox" required="true" /></td>
					</tr>
					<tr>
						<td><label for="textarea1$text">菜单描述：</label></td>
						<td><input id="textarea1" name="description" class="mini-textarea" required="true" /></td>
					</tr>
					<tr>
						<td><label for="resource1$text">访问资源：</label></td>
						<td><input id="resource1" name="resource" class="mini-buttonedit" /></td>
					</tr>
					<tr>
						<td></td>
						<td align="right"><input value="保存" type="button" onclick="save()" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>