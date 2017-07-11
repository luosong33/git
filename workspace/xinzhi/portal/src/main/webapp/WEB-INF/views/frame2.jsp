<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统管理</title>
<%@ include file="/common/miniui.jsp"%>
<script src="${ctx}/styles/core.js"  type="text/javascript"></script>

<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.logo {
	font-family: "微软雅黑", "Helvetica Neue", ​Helvetica, ​Arial, ​sans-serif;
	font-size: 28px;
	font-weight: bold;
	cursor: default;
	position: absolute;
	top: 25px;
	left: 14px;
	line-height: 28px;
	color: #444;
}

.topNav {
	position: absolute;
	right: 8px;
	top: 12px;
	font-size: 12px;
	line-height: 25px;
}

.topNav a {
	text-decoration: none;
	font-weight: normal;
	font-size: 12px;
	line-height: 25px;
	margin-left: 3px;
	margin-right: 3px;
	color: #333;
}

.topNav a:hover {
	text-decoration: underline;
}

.mini-layout-region-south img {
	vertical-align: top;
}
</style>
</head>
<body>
	<div class="mini-layout" style="width: 100%; height: 100%;">
		<div title="north" region="north" class="app-header"
			bodyStyle="overflow:hidden;" height="80" showHeader="false"
			showSplit="false">
			<div class="logo">MiniUI 在线示例</div>

			<div class="topNav">
				<a href="../index.html">首页</a> | <a href="../demo/index.html">在线示例</a>
				| <a href="../docs/api/index.html">Api手册</a> | <a
					href="../index.html#tutorial">开发教程</a> | <a
					href="../index.html#quickstart">快速入门</a>
			</div>

			<div
				style="position: absolute; right: 12px; bottom: 8px; font-size: 12px; line-height: 25px; font-weight: normal;">
				皮肤： <select id="selectSkin" onchange="onSkinChange(this.value)"
					style="width: 100px; margin-right: 10px;">
					<option value="">Default</option>
					<option value="blue">Blue</option>
					<option value="pure">Pure</option>
					<option value="gray">Gray</option>
					<option value="olive2003">Olive2003</option>
					<option value="blue2003">Blue2003</option>
					<option value="blue2010">Blue2010</option>
					<option value="bootstrap">Bootstrap</option>
					<option value="metro">metro</option>
					<option value="metro-green">metro-green</option>
					<option value="metro-orange">metro-orange</option>
					<option value="jqueryui-cupertino">jqueryui-cupertino</option>
					<option value="jqueryui-smoothness">jqueryui-smoothness</option>
				</select> 尺寸： <select id="selectMode" onchange="onModeChange(this.value)"
					style="width: 100px;">
					<option value="">Default</option>
					<option value="medium">Medium</option>
					<option value="large">Large</option>
				</select>
			</div>
		</div>
		<div showHeader="false" region="south"
			style="border: 0; text-align: center;" height="25" showSplit="false">
			Copyright © 上海普加软件有限公司版权所有</div>
		<div region="west" title="在线演示" showHeader="true"
			bodyStyle="padding-left:1px;" showSplitIcon="true" width="230"
			minWidth="100" maxWidth="350">
			<ul id="demoTree" class="mini-tree" showTreeIcon="true"
				style="width: 100%; height: 100%;" enableHotTrack="true"
				 textField="name" idField="id" parentField="pid"
				onbeforeexpand="onBeforeExpand">
				<li><a href="overview.html" target="main">Overview</a></li>
			</ul>
		</div>
		<div title="center" region="center" style="border: 0;">
			<div id="mainTabs" class="mini-tabs" activeIndex="0"
				style="width: 100%; height: 100%;">
			</div>
		</div>


	</div>
</body>
</html>
<script type="text/javascript">
	mini.parse();
	
	var tabs = mini.get("mainTabs");
	var tab = { name: 'welcome', title: '欢迎', url: 'welcome' };
    tabs.addTab(tab);
    tabs.activeTab(tab);
    
    function addTab(name, title, url) {
        var tab = { name: name, title: title, url: url, showCloseButton: true };
        tab.ondestroy = function (e) {
            var tabs = e.sender;
            var iframe = tabs.getTabIFrameEl(e.tab);

            //获取子页面返回数据
            var pageReturnData = iframe.contentWindow.getData ? iframe.contentWindow.getData() : "";

            alert(e.tab.removeAction + " : " + pageReturnData);
            
            //如果禁止销毁的时候，自动active一个新tab：e.autoActive = false;
        }
        tabs.addTab(tab);

        //active tab
        tabs.activeTab(tab);

    }
    function removeTab() {
        var tab = tabs.getActiveTab();
        if (tab) {
            tabs.removeTab(tab);
        }
    }
	
	function onBeforeExpand(e) {
		var tree = e.sender;
		var nowNode = e.node;
		var level = tree.getLevel(nowNode);

		var root = tree.getRootNode();
		tree.cascadeChild(root, function(node) {
			if (tree.isExpandedNode(node)) {
				var level2 = tree.getLevel(node);
				if (node != nowNode && !tree.isAncestor(node, nowNode)
						&& level == level2) {
					tree.collapseNode(node, true);
				}
			}
		});

	}
</script>