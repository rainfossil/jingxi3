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
    
    <title>My JSP 'collect.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/collect.css">
	

  </head>
  
  <body>
   <div id="1">
    		<div class="1">
			<div class="12">
				<a href="account">${sessionScope.acc }</a>
				<a href="register.jsp">注册</a>
				<a href="mycar?status=1">我的购物车</a>
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
					<p class="3112">收藏夹</p>
					<table border=box rules=all bordercolor=black cellspacing=0px cellpadding=5px width="700px">
						<tr align="center">
							<td>选择</td>
							<td>商品编号</td>
							<td>商品名称</td>
							<td>商品价格(￥)</td>
							<td>收藏日期</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${lii}" var="sss">
							<tr align="center">
								<td><input type="checkbox" name="all"></td>
								<td>${sss.key.wareNumber }</td>
								<td>${sss.key.we.name }</td>
								<td>${sss.key.we.price }</td>
								<td>${sss.key.dateTime }</td>
								<td>
									<c:choose>
										<c:when test="${sss.value eq 'yes'}">
										已加入
										</c:when>
										<c:otherwise>
											<a href="shopcart?sta=1&wn=${sss.key.wareNumber }&collect=yes">加入购物车</a>
										</c:otherwise>
									</c:choose>	
									|&nbsp;&nbsp;<a href="shopcart?sta=2&wn=${sss.key.wareNumber }&collect=yes">删除</a>
								</td>
							</tr>	
						</c:forEach>
						<tr align="center">
							<td><input type="checkbox" name="all"></td>
							<td>全选</td>
							<td colspan="4">将选择项加入购物车</td>
						
						</tr>
					</table>
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
