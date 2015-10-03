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
	
	<link rel="stylesheet" type="text/css" href="css/of.css">

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
						<c:when test="${empty total}">
							<p class="3111">提交订单信息</p>
							<form action="mfs" method="post">
								填写收货人姓名：<br/>
								<input type="text" size=38 name="name" value=""></input><br/>
								填写收货地址：<br/>
								<input type="text" size=38 name="address" value=""></input><br/>
								填写邮编：<br/>
								<input type="text" size=38 name="zipcode" value=""></input><br/>
								填写联系电话：<br/>
								<input type="text" size=38 name="phone" value=""></input><br/><br/>
								请选择快递：<br/>
								<input type="radio" name="express" value="FedEx"></input>联邦快递<br/><br/>
								请选择支付方式：<br/>
								<input type="radio" name="paym" value="使用账户余额支付"></input>使用账户余额支付<br/><br/>
								是否开具发票：<br/>
								<input type="radio" name="receipt" value="yes"></input>是
								<input type="radio" name="receipt" value="no"></input>否<br/><br/>&nbsp;&nbsp;&nbsp;
								<input type="submit" name="" value="提交"></input>&nbsp;&nbsp;
								<input type="reset" name="" value="取消"></input>
							</form>
						</c:when>
						<c:otherwise>
							<p class="3111">确认订单信息</p>
							姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${sessionScope.of.cargo_receiver }<br/>
							收货地址：${sessionScope.of.consignee }<br/>
							邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：${sessionScope.of.zip_code }<br/>
							联系电话：${sessionScope.of.phone }<br/>
							快&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;递：${sessionScope.of.express }<br/><br/>
							需支付金额：${total }&nbsp;￥<br/>
							支付方式：${sessionScope.of.paymethod }<br/><br/>
							是否开具发票：${sessionScope.of.receipt }<br/>
							<form action="shopcart" method="post">
								<input type="hidden" name="sta" value="0"></input>
								<input type="submit" name="confirm" value="确认提交"></input>&nbsp;&nbsp;
								<a href="of.jsp">取消</a>
							</form>
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
