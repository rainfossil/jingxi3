<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'backstagemg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<!-- 
	 <link rel="stylesheet" type="text/css" href="css/backstagemg.css"> 
	-->
	<style>
	body{margin:0px;
			padding-left:27px;
			font-size:17px;}
		a{text-decoration:none;
			
			color:black;}
		
		li{list-style-type:none;
		
		height:55px;
		width:156px;
		font-size:33px;
		padding-left:8px;
		padding-top:8px;
		background-repeat:no-repeat;
		background-image:url("image/effect/border1.jpg");}
		img{border:0px;}
		#1{
			height:680px;
			width:1370px;
			
			margin-top:0px;}
		.1{
			height:50px;
			width:1370px;
			}
		.2{
			height:80px;
			width:1370px;
			}
		.3{
			height:470px;
			width:1370px;
			}
		.4{
			height:80px;
			width:1370px;
			}

		.12{
			height:33px;
			width:500px;
			float:right;}

		.1 a{
			height:33px;	
			padding-top:13px;}
	
		.p3{
			font-size:13px;	
			margin-bottom:23px;
			color:#a7a7a7;
			width:750px;
			height:50px;
			margin-left:300px;
			padding-top:23px;}
		.p3 span{color:black;}
		.main{float:left;
			font-size:38px;
			padding-top:10px;
			padding-left:77px}
		.31{position:absolute;
			z-index:1;
			height:470px;
			width:750px;
			margin-left:-3px;
			background-repeat:no-repeat;
			background-color:white;
			float:left;
			margin-left:300px;
			background-image:url("image/effect/border4.jpg");}
		#31211{display:block;}	
		#3121{color:black;}
		#3122{color:#a7a7a7;}
		.31 div{padding-left:23px;
			padding-top:23px;
			display:none}
		.31 table{margin-bottom:23px;}
		#312 label{font-size:23px;}
		.32{position:absolute;
			z-index:2;
			height:468px;
			width:228px;
			background-color:white;
			float:left;
			margin-left:73px;
			background-repeat:no-repeat;
			background-image:url("image/effect/border3.jpg");}
		.32 ul{
			margin-top:16px;
			width:156px;
			float:right;
			padding-right:3px;}
		#special{
			background-image:url("image/effect/border2.jpg");
			}
		#311{display:block;}
	</style>
	 
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
				<a href="forum">论坛</a>
				<a href="out">退出</a>
			</div>
		</div>
    	<div class="2">
			<div class="main"><a href="jx">京西</a></div>
		</div>
    	<div class="3">
    		<div class="32">
    			<ul>
    				<li id="special">京西规范</li>
    				<li>发布商品</li>
    				<li>修改信息</li>
    				<li>商铺管理</li>
    				<li>广告管理</li>
    			</ul>
    		</div>
			<div class="31">
				<div id="311">
					<table border=box rules=all bordercolor=black cellspacing=0px cellpadding=5px width="600px">
						<tr align="center">
							<td colspan="6">发布商品规范(标识符)</td>
						</tr>
						<tr align="center">
							<td>图册</td>
							<td>音乐</td>
							<td>图书</td>
							<td>家用电器</td>
							<td>手机数码</td>
							<td>食品服务</td>
						</tr>
						<tr align="center">
							<td>p</td>
							<td>m</td>
							<td>b</td>
							<td>e</td>
							<td>d</td>
							<td>s</td>
						</tr>
					</table>
					<table border=box rules=all bordercolor=black cellspacing=0px cellpadding=5px width="600px">
						<tr align="center">
							<td colspan="3">上传图片规范(px)</td>
						</tr>
						<tr align="center">
							<td>首页</td>
							<td>介绍页</td>
							<td>头像</td>
						</tr>
						<tr align="center">
							<td>168*105</td>
							<td>1680*1050</td>
							<td>100*100</td>
						</tr>
					</table>
				</div>
				<div id="312">
					<label id="3121">发布商品</label>&nbsp;&nbsp;&nbsp;&nbsp;<label id="3122">修改信息</label>
					<div id="31211">
						<form action="publish" method="post" enctype="multipart/form-data">
							商品标识符：&nbsp;<input type="text" name="mark"></input><br/><br/>
							商&nbsp;品&nbsp;名&nbsp;字：&nbsp;&nbsp;<input type="text" name="name"></input><br/><br/>
							商&nbsp;品&nbsp;描&nbsp;述：&nbsp;&nbsp;<textarea rows="3"  name="intro"></textarea><br/><br/>
							商&nbsp;品&nbsp;价&nbsp;格：&nbsp;&nbsp;<input type="text"  name="pri" value=""></input><br/><br/>
							首&nbsp;页&nbsp;图&nbsp;片1：<input type="file"  name="ad1"></input><br/><br/>
							首&nbsp;页&nbsp;图&nbsp;片2：<input type="file"  name="ad2"></input><br/><br/>
							介&nbsp;绍&nbsp;图&nbsp;片3：<input type="file"  name="ad3"></input><br/><br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="发布"></input>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重填"></input>
						</form>
					</div>
					<div id="31221">
						<form action="publish" method="post" enctype="multipart/form-data">
						输入商品编号：<input type="text" name="waren"></input><br/>
						<input type="radio" name="delete" value="yes"></input>删除该商品
						<input type="radio" name="delete" value="no"></input>修改该商品<br/><br/><br/>
							<div id="312211">
							商品标识符：&nbsp;<input type="text" name="mark"></input><br/>
							商&nbsp;品&nbsp;名&nbsp;字：&nbsp;&nbsp;<input type="text" name="name"></input><br/>
							商&nbsp;品&nbsp;描&nbsp;述：&nbsp;&nbsp;<textarea rows="3"  name="intro"></textarea><br/>
							商&nbsp;品&nbsp;价&nbsp;格：&nbsp;&nbsp;<input type="text"  name="pri" value=""></input><br/>
							首&nbsp;页&nbsp;图&nbsp;片1：<input type="file"  name="ad1"></input><br/>
							首&nbsp;页&nbsp;图&nbsp;片2：<input type="file"  name="ad2"></input><br/>
							介&nbsp;绍&nbsp;图&nbsp;片3：<input type="file"  name="ad3"></input><br/><br/>
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="确认"></input>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="取消"></input>
						</form>
					</div>
				</div>
				<div>修改信息</div>
				<div>商铺管理</div>
				<div>广告管理</div>
			</div>
		</div>
    		<div class="4">
			<p class="p3">copyright@京西2018&nbsp;<span>中文(简体)</span>《中华人民共和国电信与信息服务业务经营许可证》编号:京ICP证060911号</p>
		</div>
	</div>
  </body>
</html>
	<script type="text/javascript">
		$(
			function(){
				for(var i=0;i<5;i++){
					(function(arg){	
						$("ul").on(
							"click",
							"li:eq("+i+")",
							function(){
								//alert($(this).text());
								$("li").attr("style","background-image:url('image/effect/border1.jpg')");
								$(this).attr("style","background-image:url('image/effect/border2.jpg')");
								$(".31>div").attr("style","display:none");
								$(".31>div:eq("+arg+")").attr("style","display:block");
								//alert(arg);
								if(arg==1){
									//var div1=document.getElementById("3121");
									//var div2=document.getElementById("3122");	
									//var cssstyle=window.getComputedStyle ? window.getComputedStyle(div1,null).display : div1.currentStyle.display;
									$("#3121").on(
										"click",
										function(){
										$("#31211").attr("style","display:none");
										$("#31221").attr("style","display:none");
										$("#31211").attr("style","display:block");
										$("#3121").attr("style","color:#a7a7a7");
										$("#3122").attr("style","color:#a7a7a7");
										$("#3121").attr("style","color:black");
										}
									);
									$("#3122").on(
										"click",
										function(){
										$("#31211").attr("style","display:none");
										$("#31221").attr("style","display:none");
										$("#31221").attr("style","display:block");
										$("#3121").attr("style","color:#a7a7a7");
										$("#3122").attr("style","color:#a7a7a7");
										$("#3122").attr("style","color:black");
										}
									);
								}	
							}
						);
					})(i);	
					
				}
				var r1=$("#31221 :radio[value='yes']");
				var r2=$("#31221 :radio[value='no']");
				var r3=$("#312211");
				//alert(r3.attr("id"));
				r1.on(
					"click",
					function(){
						r3.attr("style","display:none");
					}
				)
				r2.on(
					"click",
					function(){
						r3.attr("style","display:block");
						/*
						r3.attr("style","display:none");
						if(r2.attr("checked")){
							r3.attr("style","display:block");
						}else{
							r3.attr("style","display:none");
						}
						*/
					}
				);

			}
		);	
						//alert("access"+$("li:eq(1)").text());
				//$(".31>div:eq(4)").attr("style","display:block");
				//alert("access"+$(".31>div:eq(4)").text());
				/*
				$("ul").on(
					"mousemove",
					"li",
					function(){
						$(this).attr("style","background-image:url('image/effect/border2.jpg')");
					}
				);
				$("ul").on(
					"mouseout",
					"li",
					function(){
						$(this).attr("style","background-image:url('image/effect/border1.jpg')");
					}
				);
				*/
	</script>