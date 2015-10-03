<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'of.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/result.css">

  </head>
  
  <body>
    <div id="1">
    		<div class="1">
			<div class="12">
					<c:choose>
					<c:when test="${not empty sessionScope.acc}">
						<a href="account">${sessionScope.acc }</a>
					</c:when>
					<c:otherwise>
						<a href="mp?ch=1">登录</a>
					</c:otherwise>
					</c:choose>
					<a href="register.jsp">注册</a>
					<a href="mycar?status=1&ch=3">我的购物车</a>
					<a href="mycar?status=2&ch=4">我的收藏</a>
					<c:if test="${sessionScope.rx==3}">
						<a href="backstagemg.jsp">发布商品</a>
					</c:if>
					<a href="forum">论坛</a>
					<a href="out">退出</a>
			</div>
		</div>
    		<div class="2">
			<div class="main"><a href="#">京西</a></div>
		</div>
    		<div class="3">
			<div class="31">
				<div class="311">
				
					<c:choose>
						<c:when test="${empty success}">
							<label>账户余额不足</label>
						</c:when>
						<c:otherwise>
							<label>提交订单成功</label><br/><br/><br/><br/><br/>
							<a href="jx">继续购物</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
    		<div class="4">
			<p class="p3">copyright@京西2018&nbsp;<span>中文(简体)</span>《中华人民共和国电信与信息服务业务经营许可证》编号:京ICP证060911号</p>
		</div>
	</div>
  </body>
</html>
