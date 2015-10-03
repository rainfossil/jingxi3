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
    
    <title>My JSP 'cart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/cart.css">
	
  </head>
  
  <body>
    <div id="1">
    		<div class="1">
			<div class="12">
				<a href="account">${sessionScope.acc }</a>
				<a href="">注册</a>
				<a href="mycar?status=2">我的收藏</a>
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
					<p class="3112">购物车</p>
					<table border=box rules=all bordercolor=black cellspacing=0px cellpadding=5px width="600px">
						<tr align="center">
							<td>选择</td>
							<td>商品编号</td>
							<td>商品名称</td>
							<td>商品单价</td>
							<td>数量</td>
							<td>修改数量</td>
							<td>小计(￥)</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${lii}" var="sss">
							<tr align="center">
								<td><input type="checkbox" name="all"></td>
								<td>${sss.key.wareNumber }</td>
								<td>${sss.key.we.name }</td>
								<td>${sss.key.we.price }</td>
								<td>${sss.key.wareCount }</td>
								<td align="left">
								<form action="cc" method="post">
									<input size=1 type="text" name="+" value="1"/><input id="23" type="submit" name="con" value="+"/><br/>
									<input size=1 type="text" name="-" value="1"/><input id="23" type="submit" name="con" value="-"/><br/>
									<input type="hidden" name="ware_Number" value="${sss.key.wareNumber }"/>
									<input type="hidden" name="order_Number" value="${sss.key.orderNumber }"/>
								</form>
								</td>
								<td>${sss.key.wareCount*sss.key.we.price }</td>
								<td><a href="cc?con=x&ware_Number=${sss.key.wareNumber }&order_Number=${sss.key.orderNumber }">删除</a></td>
							</tr>	
						</c:forEach>

						<tr align="center">
							<td><input type="checkbox" name="all"></td>
							<td></td>
							<td colspan="6"></td>
						
						</tr>
						<tr align="center">
							<td>总计</td>
							<td colspan="7">${tp }￥</td>
						</tr>
					</table>
					<div class="3111">
						<c:if test="${bo ne 0}">
						<a href="of.jsp">去结算&nbsp;&gt;&gt;&gt;</a>
						</c:if>
					</div>
				</div>
				<div class="312">
					${page }
				</div>
			</div>
		</div>
    		<div class="4">
			<p class="p3">copyright@京西2018&nbsp;<span>中文(简体)</span>《中华人民共和国电信与信息服务业务经营许可证》编号:京ICP证060911号</p>
		</div>
		</div>
  </body>
</html>
