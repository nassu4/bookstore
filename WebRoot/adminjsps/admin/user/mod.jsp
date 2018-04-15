<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改用户</title>
    
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
</style>
  </head>
  
  <body>
    <h1>修改用户</h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="${pageContext.request.contextPath }/user_modifyPassword.do" method="post">
    	用户名称：<input type="text" name="name" value="${param.name }" readonly="readonly" />
    	用户密码：<input type="password" name="password"/>
    	<input type="submit" value="修改密码"/>
    </form>
  </body>
</html>
