<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#myButton").click(function(){
				var myText = $("#myText").val();
				var option = {
						url:"test/toPerson12.do",
						type:"post",
						dataType:"text",
						data:{
							name:myText
						},
						success:function(responseText){
							alert(responseText);
						},
						error:function(){
							alert("system error");
						}
				};
				
				$.ajax(option);
			});
		})
		
	</script>
  </head>
  
  <body>
   	<input type="text" id="myText">
   	<input type="button" id="myButton" value="submit">
  </body>
</html>
