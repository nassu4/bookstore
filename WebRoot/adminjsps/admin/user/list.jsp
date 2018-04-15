<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {background: rgb(254,238,189);}
	table {font-family: 宋体; font-size: 11pt; border-color: rgb(78,78,78);  width: 60%;}
	#th {background: rgb(78,78,78);}
</style>
  </head>
  
  <body>
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<tr id="th" bordercolor="rgb(78,78,78)">
    		<th>用户名称</th>
    		<th>用户状态</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach var="user" items="${list }">
    	  <tr bordercolor="rgb(78,78,78)">
    		<td>${user.name }</td>
    		<c:if test="${user.status == 0 }">
    		  <td>禁用</td>
    		  <td>
    		    <a href="${pageContext.request.contextPath }/user_changeStatus.do?uid=${user.uid }">激活</a> |
    		    <a href="<c:url value='/adminjsps/admin/user/mod.jsp?name=${user.name }'/>">修改</a> |
    		    <a href="${pageContext.request.contextPath }/user_delete.do?uid=${user.uid }">删除</a>
    		  </td>
    		</c:if>
    		<c:if test="${user.status != 0 }">
    		  <td>激活</td>
    		  <td>
    		    <a href="${pageContext.request.contextPath }/user_changeStatus.do?uid=${user.uid }">禁用</a> |
    		    <a href="<c:url value='/adminjsps/admin/user/mod.jsp?name=${user.name }'/>">修改</a> |
    		    <a href="${pageContext.request.contextPath }/user_delete.do?uid=${user.uid }">删除</a>
    		  </td>
    		</c:if>
    	  </tr>
    	</c:forEach>
    </table>
  </body>
</html>
