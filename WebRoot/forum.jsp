<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'forum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{margin:0px;
			padding-left:27px;
			font-size:17px;}
		a{text-decoration:none;
			
			color:black;}
		
		li{list-style-type:none;}
		img{border:0px;}
		#1{position:absolute;
			
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
		.6{display:none;
			position:absolute;
			background-color:gray;
			height:888px;
			width:1370px;
			
			margin-top:0px;
			filter:alpha(opacity=50);}
		.5{display:none;
			position:absolute;
			margin-top:0px;
			height:683px;
			width:1370px;}
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
		.31{height:470px;
			width:750px;
			
			margin-left:300px;
			}
		.311{
			height:123px;}	
		.3111{font-size:18px;
			border:1px solid black;
			}
		#3111{background-color:transparent;}
		.3112{font-size:18px;
			margin-left:503px;
			margin-top:7px;
		}
		.312{margin-top:13px;}
		.313{margin-bottom:7px;
			height:103px;}
		.313 a{color:blue;}
		.3132{float:right;
			margin-right:53px;}
		.3131{font-size:13px;
			color:#a7a7a7;}
		.3133{font-size:13px;
			color:red;
			float:left;
			margin-top:7px;}
		.51{border:1px solid black;
			margin-top:38px;
			margin-left:380px;
			background-color:white;
			height:600px;
			width:530px;}
		.511{border-top:none;
			border-lefet:none;
			border-right:none;
			border-bottom:1px solid red;
			margin-top:63px;}
		.5111{font-size:15px;
			border:1px solid black;
			margin-left:23px;
			}
		.5112{font-size:15px;
			margin-left:350px;
			margin-top:7px;
		}
		img{border:none;
			float:right;}
		
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
					<a href="register.jsp">注册</a>
					<a href="mycar?status=1&ch=3">我的购物车</a>
					<a href="mycar?status=2&ch=4">我的收藏</a>
					<c:if test="${sessionScope.rx==3}">
						<a href="backstagemg.jsp">发布商品</a>
					</c:if>
					<a href="out">退出</a>
			</div>
		</div>
    		<div class="2">
			<div class="main"><a href="#">京西</a></div>
		</div>
    	<div class="3">
			<div class="31">
				<div class="311">
					<form action="forum" method="post" >
						<textarea name="content" rows="3" cols="50" class="3111"></textarea><br/>
						<input type="submit" value="发表" class="3112" name="forum" id="3111"></input>
					</form>
				</div>
				<c:forEach items="${li}" var="x">
					<div class="313">
						<a href="">${x.id }</a><label class="3131">&nbsp;&nbsp;&nbsp;${x.dateTime }</label><br/>
						<label>${x.content }</label><br/>
						<div class="3133">评论&nbsp;&nbsp;${x.times }&nbsp;&nbsp;次</div>
						<div class="3132">评论</div>
						 
						<div class="31321" style="visibility: hidden;">${x.contentnumber }</div>
						
						
					</div>
					<hr>
				</c:forEach>
				<div class="312">${page }</div>
			</div>
		</div>
    		<div class="4">
			<p class="p3">copyright@京西2018&nbsp;<span>中文(简体)</span>《中华人民共和国电信与信息服务业务经营许可证》编号:京ICP证060911号</p>
		</div>
		
	</div>
	<div class="6"></div>
	<div class="5">
		<div class="51"> 
			<img src="image/effect/close.jpg">
			<div class="511">
				<form action="" method="post" >
					<textarea name="content" rows="3" cols="38" class="5111" id="textareax"></textarea><br/>
					<input type="hidden" name="big" id="trans">
					<input type="button" value="评论" class="5112" name="forum" onclick="comment();"></input>
				</form>
			</div>
			<div class="512"></div>
		</div>
	</div>
  </body>
</html>
<script type="text/javascript">
 var xmlRequest;
 var flag=false;
     //创建异步请求对象的方法
     function createXmlHttpRequest(){
        //判断客户机浏览器的类型，根据浏览器的类型创建相应的异步请求对象
       if(window.ActiveXObject){
         //针对IE浏览器的操作
         return new ActiveXObject("Microsoft.XMLHTTP");
       }else if(window.XMLHttpRequest){
        //针对于其他类型的浏览器的操作
         return new XMLHttpRequest();
       }
     }   
     //触发异步请求事件的方法
     function comment(){
       var searchvalue=document.getElementById("trans").value;
       var textarea=document.getElementById("textareax").value;
       
       //alert(searchvalue+"%%%%%%"+textarea);
       searchvalue=encodeURI(encodeURI(searchvalue,"UTF-8"),"UTF-8");
       textarea=encodeURI(encodeURI(textarea,"UTF-8"),"UTF-8");
       //定义服务器处理异步请求组件的URL及请求参数。
       var urls="http://www.rainfossil.cn:36501/jingxi3/commentajax?searchvalue="+searchvalue+"&textarea="+textarea+"&ts="+new Date().getTime();
       xmlRequest=createXmlHttpRequest();  //创建异步请求对象
       
       xmlRequest.onreadystatechange=returnCall; //调用回调函数
       
       xmlRequest.open("GET",urls,false);  //建立到服务器连接
       //xmlRequest.setRequestHeader("Cache-Control","no-cache");
       xmlRequest.send(null);  //发送异步请求    
     }    
     function returnCall(){
      if(xmlRequest.readyState==4&&xmlRequest.status==200){ 
      	var data=xmlRequest.responseText;
      	var json=eval(data);//转换
      	var di=$(".512");
      	di.empty();//清空初始化
      	//alert(data.length);
      	var arr=new Array();
      	$("#textareax").val("");
		for(var i=0;i<json.length;i++){
			arr[i]=$("<div style='margin-bottom:7px'><font color='blue'>"+json[i].commentator+":</font>&nbsp;&nbsp;&nbsp;"+json[i].commentary+"<br/><font color='#a7a7a7' size='1px'>"+json[i].dateTime+"</font></div>");			
			di.append(arr[i]);
		}
		
      }
     }
     
	$(
		
		function(){
			$(".51 img").click(
				function(){
					$(".6").attr("style","display:none;");
					$(".5").attr("style","display:none;");
				}
			);
			for(var i=0;i<5;i++){
				(function(arg){
					$(".31").on(
								"click",
								".3132:eq("+arg+")",
								function(){
									$(".6").attr("style","display:block;");
									$(".5").attr("style","display:block;");	
									var va=$(".31321:eq("+arg+")").text();
									//alert(va+"*******"+arg);
									$("#trans").val(va);
									comment();
								}
							);

				})(i);
			}
			//仅触发一次事件
			/*
			$(".5").one(
				"mouseover",
				function(){
					comment();
				}
			);
			*/
		}
	);
	
</script>
