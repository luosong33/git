<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户管理</title>
<%@ include file="/common/ligerui.jsp"%>
<style type="text/css">
body {
	padding: 2px;
	margin: 0;
}

#layout1 {
	width: 100%;
	margin: 0;
	padding: 0;
}
</style>
<script src="${ctx}/scripts/frame.js"></script>
</head>
<body>
	<div>
		aaaaa<br /> aaaaa<br /> aaaaa<br />
	</div>
	<div id="layout1">
		<div position="left"></div>
		<div position="center" title="标题">
			<div id="navtab1">
				<div tabid="welcome" title="欢迎" lselected="true">
					<iframe frameborder="0" name="welcome" src="${ctx}/welcome"></iframe>
				</div>
			</div>
		</div>
	</div>
</body>
</html>