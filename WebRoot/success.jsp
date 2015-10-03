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
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/success.css">


  </head>
  
  <body>
    <div id="1">
    		<div class="1">
			<div class="12">
				<a href="jx">首页</a>
				<a href="login.jsp">登录</a>
			<!--
				<a href="">登录</a>
				<a href="register.jsp">注册</a>
				<a href="">我的购物车</a>
				<a href="">我的收藏</a>
				<a href="">我的商铺</a>
				<a href="">论坛</a>
				<a href="">退出</a>
				-->
			</div>
		</div>
    		<div class="2">
			<div class="main"><a href="jx">京西</a></div>
		</div>
    		<div class="3">
			<div class="31">
				<div class="312">
					<img src="image/login.jpg"/>
				</div>
				<div class="311">
					<form action="login" method="post"><br/><br/>
  						<p class="p1">注册成功</p>
  						<p>你的京西账号为：<label>${acco }</label></p>
  						<p>你的昵称为：<label>${un }</label></p>
  						
  						<a href="login.jsp" style="float:right;">现在登录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
  					</form>
				</div>
			</div>
		</div>
    		<div class="4">
			<p class="p3">copyright@京西2018&nbsp;<span>中文(简体)</span>《中华人民共和国电信与信息服务业务经营许可证》编号:京ICP证060911号</p>
		</div>
	</div>
  </body>
</html>
