<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/register.css">
	<script type="text/javascript" src="js/register.js"></script>
	<!-- 
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(
			function(){
				$("#formvalidation").validate(
					{
						rules:{
							id:{required:true},
							pass:{required:true},
							pass1:{required:true},
							email:{required:true}
						}
					}
				);
			}
		);
	</script>
	 -->
  </head>
  
  <body>
    <div id="1">
    		<div class="1">
			<div class="12">
			<a href="jx">首页</a>
			<a href="login.jsp">登录</a>
			<!-- 
				<a href="">登录</a>
				<a href="">注册</a>
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
				<div class="311">
					<form id="formvalidation" name="" action="register" method="post"><br/><br/>
  						<p class="p1">账号注册</p>
						<div class="3110">(&nbsp;&nbsp;*&nbsp;&nbsp;为必填项&nbsp;&nbsp;)</div>
  						昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：<input size="31" type="text" id="id" name="id" value="${na }" onblur="verify('id')"/>&nbsp;<label id="31111">*</label><br/>
						<div id="3111" class="3110">${me1 }</div>
  						密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input size="33" type="password" id="pass" name="pass" value="${pa }" onblur="verify('pass')"/>&nbsp;<label id="31112">*</label><br/>
						<div id="3112" class="3110">${me2 }</div>
  						再次输入密码：<input size="33" type="password" name="pass1" value="${pa1 }" id="pass1" onblur="verify('pass1')"/>&nbsp;<label id="31113">*</label><br/>
						<div id="3113" class="3110">${me3 }</div>
  						输&nbsp;&nbsp;&nbsp;入&nbsp;&nbsp;&nbsp;邮&nbsp;&nbsp;箱：<input size="32" type="text" id="email" name="email" value="${em }" onblur="verify('email')"/>&nbsp;<label id="31114">*</label><br/>
						<div id="3114" class="3110">${me4 }</div><br/><br/>
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						<input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  						<input type="reset" value="取消"><br><br><br>
  						<a href="login.jsp" style="float:right;">已有账号,现在登录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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
