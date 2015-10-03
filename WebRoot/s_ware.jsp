<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 's_ware.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/s_ware.css">
	<link rel="stylesheet" type="text/css" href="css/jqzoom.css">
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jqzoom.pack.1.0.1.js"></script>
	<script>
		$(
			function(){
				var options={
					zoomType: 'standard',
					lens:true
				}
				$(".jqzoom").jqzoom(options);
			}
		);
	</script>
  </head>
  
  <body>
    <div id="1">
    		<div class="1">
			<div class="12">
				<c:choose>
					<c:when test="${not empty sessionScope.acc}">
						<a href="">${sessionScope.acc }</a>
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
			<div class="main"><a href="jx">京西</a></div>
		</div>
    		<div class="3">
			<div class="31">
				<div id="311">
					<c:choose>
						<c:when test="${ca eq 'yes'}">
							已加入购物车
						</c:when>
						<c:otherwise>
							<a href="shopcart?wn=${ware.number }&sta=1">加入购物车</a>
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;
					<c:choose>
						<c:when test="${co eq 'yes'}">
							已收藏
						</c:when>
						<c:otherwise>
							<a href="shopcart?wn=${ware.number }&sta=2">加入收藏夹</a>
						</c:otherwise>
					</c:choose>
				</div>
				<dl>
					<dt>
					<a href="${ware.address3 }" class="jqzoom" title="大图"">
						<img src="${ware.address2 }"/>
					</a>
					<!-- 
					<a href="#"><img src="${ware.address2 }"/></a>
					 -->
					</dt>
					<dd>编号：${ware.number }</dd>
					<dd>名称：${ware.name }</dd>
					<dd>标价：${ware.price }</dd>
					<dd>日期：${ware.dayTime }</dd>
					<dd>描述：${ware.description }<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;------------------------------------------------------------------<br/>
						&nbsp;&nbsp;-----------------------------<br/>
					</dd>
				</dl>
			</div>
		</div>
    		<div class="4">
			<p class="p3">copyright@京西2018&nbsp;<span>中文(简体)</span>《中华人民共和国电信与信息服务业务经营许可证》编号:京ICP证060911号</p>
		</div>
	</div>
  </body>
</html>
