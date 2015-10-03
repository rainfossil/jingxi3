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
    
    <title>My JSP 'account.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/account.css">

  </head>
  
  <body>
     <div id="1">
    		<div class="1">
			<div class="12">
				<a href="account">${sessionScope.acc }</a>
				<a href="register.jsp">注册</a>
				<a href="mycar?status=1">我的购物车</a>
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
					<p class="3112">个人账户</p>
					<table border=box rules=all bordercolor=black cellspacing=0px cellpadding=5px width="700px">
						<tr align="center">
							<td>日期</td>
							<td>收入&nbsp;(￥)</td>
							<td>支出&nbsp;(￥)</td>
							<td>浮动&nbsp;(￥)</td>
						</tr>
						<c:forEach items="${lii}" var="sss">
							<tr align="center">	
								<td>${sss.dateTime }</td>
								<td id="income">${sss.income }</td>
								<td id="pay">${sss.pay }</td>
								<c:choose>
									<c:when test="${sss.income >= sss.pay }">
										<td id="income">${sss.income - sss.pay }</td>
									</c:when>
									<c:otherwise>
										<td id="pay">${sss.income - sss.pay }</td>
									</c:otherwise>
								</c:choose>
							</tr>	
						</c:forEach>
						<tr align="center">
							<td>账户余额</td>
							<td colspan="3">${balance }&nbsp;￥</td>
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
