<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	img {
		height: 150px;
	}
</style>
<script type="text/javascript">
	window.onload = function() {
		var form = document.getElementById("form");
		var mod = document.getElementById("mod");
		var del = document.getElementById("del");
		var method = document.getElementById("method");
		
		mod.onclick = function() {
			method.value = "modify";
			form.submit();
		}
		del.onclick = function() {
			method.value = "delete";
			form.submit();
		}
	}
</script>
  </head>
  
  <body>
  <div>
    <img src="${book.image }" border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="${pageContext.request.contextPath }/book_modify.do" method="post">
  	<input type="hidden" name="bid" value="${book.bid }" />
  	<input type="hidden" name="image" value="${book.image }" />
  	图书名称：<input type="text" name="name" value="${book.name }"/><br/>
  	图书单价：<input type="text" name="price" value="${book.price }"/><span> 单位：元</span><br/>
  	图书作者：<input type="text" name="author" value="${book.author }"/><br/>
  	图书分类：<select style="width: 150px; height: 20px;" name="cid">
  			 <c:forEach var="category" items="${list }">
  			   <option value="${category.cid }" <c:if test="${book.cid == category.cid }">selected="selected"</c:if>>${category.name }</option>
  		     </c:forEach>
    	   </select><br/>
  	<!-- <input type="button" value="删除" id="del" /> -->
  	<input type="button" value="修改" id="mod" />
  </form>
  </body>
</html>
