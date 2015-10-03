<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">


  </head>
  
  <body>
  <div id="1">
    		<div class="1">
			<div class="12">
				<a href="jx">首页</a>
				<a href="register.jsp">注册</a>
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
  						<p class="p1">用户登录</p>
  						<div id="message" class="3110">${message }</div>
  						账&nbsp;&nbsp;&nbsp;&nbsp;号：<input size="31" type="text" name="account" id="account" value=""/>
  						<br/><br/><br/>
  						密&nbsp;&nbsp;&nbsp;&nbsp;码：<input size="33" type="password" name="pass" id="pass" value=""/><br>
						<br/><br/>
						<input type="hidden" name="ch" value="${ch }"/>
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						<input type="submit" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						<input type="reset" value="取消"><br><br><br>
  						<a href="register.jsp" style="float:right;">现在注册&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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
