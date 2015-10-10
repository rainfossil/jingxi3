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
    
    <title>My JSP 'jx.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/jx.css">
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  </head>
  
  <body>
    	<div id="rf_1">
    		<div class="rf_1">
			<div class="rf_12">
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
					<a href="forum?ch=2">论坛</a>
					<a href="out">退出</a>
			</div>
		</div>
    	<div class="rf_2">
    		<div class="main"><a href="jx">京西</a></div>
			<div class="rf_13">
				<form action="jx" method="post">
					<input class="rf_111" type="text" name="search" value="" size="50" id="sajax" autocomplete="off" onkeyup="messageinf();"></input>
					<input class="rf_112" type="submit" name="sea" value="搜索>>"></input>
				</form>
				<div class="rf_131" id="xxx" style="display:none">
				</div>
			</div>
		</div>
    		<div class="rf_3">
			<div class="rf_11">
				<div class="rf_113">
					<ul>
						<li><a href="jx?target=p">&amp;&nbsp;图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</a></li>
						<li><a href="jx?target=m">&amp;&nbsp;音&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乐</a></li>
						<li><a href="">&amp;&nbsp;图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;书</a></li>
						<li><a href="">&amp;&nbsp;家用电器</a></li>
						<li><a href="">&amp;&nbsp;手机数码</a></li>
						<li><a href="">&amp;&nbsp;食品服务</a></li>
						<li><a href="">&amp;&nbsp;全球资讯</a></li>
					</ul>
				</div>
			</div>
			<div class="rf_14">
				<%int i=30; %>
				<c:forEach items="${li }" var="lii">
					<%i++; %>
					<div>
						<style type="text/css">
							#<%="rf_"+i %> a:hover{color:orange;
								background-image:url(${lii.address2});
								background-repeat:no-repeat;}
						</style>
						<ul>
						<li class="rf_23" id="rf_<%=i %>">
							<img src="${lii['address1'] }"/>
							<a href="${lii['address3'] }" title="点击查看原图"></a>
						</li>
						<li id="rf_3">
							编号：${lii.number }<br/>
							名称：${lii.name }<br/>
							时间：${lii.dayTime }<br/><br/>
							<a href="sware?number=${lii.number }">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">收藏</a>
						</li>
					</ul>
					</div>
				</c:forEach>
			</div>
			<div class="rf_15">
				${page }
			</div>
		</div>
    		<div class="rf_4">
			<p class="p3">copyright@京西2018&nbsp;<span>中文(简体)</span>《中华人民共和国电信与信息服务业务经营许可证》编号:京ICP证060911号</p>
		</div>
	</div>
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
     function messageinf(){
       var searchvalue=document.getElementById("sajax").value;
       //alert(searchvalue);
       searchvalue=encodeURI(encodeURI(searchvalue,"UTF-8"),"UTF-8");
       //定义服务器处理异步请求组件的URL及请求参数。
       var urls="<%=basePath%>ajax?searchvalue="+searchvalue+"&ts="+new Date().getTime();
       xmlRequest=createXmlHttpRequest();  //创建异步请求对象
       xmlRequest.onreadystatechange=returnCall; //调用回调函数
       xmlRequest.open("GET",urls,true);  //建立到服务器连接
       xmlRequest.send(null);  //发送异步请求    
       // alert(data);
     }    
      //回调方法。在进行判断的时候一定要先行判断请求的准备状态（readystate）,
     //然后再判断服务器响应的状态码（status）;否则js脚本会抛异常。
     function returnCall(){
      if(xmlRequest.readyState==4&&xmlRequest.status==200){  
        //通过异步请求对象取得服务器返回的数据
	        var data=xmlRequest.responseText;
			var array=data.split("&");
			var messagediv=$("#xxx");
			var inp=$("#sajax");
			var md=$("#xxx").empty();
			if(data==""){
				md.attr("style","display:none");
			}else{
				md.attr("style","display:block");
			}
			if(inp.val().length==0){
				md.attr("style","display:none");
			}

			var arr=new Array();
			for(var i=0;i<array.length;i++){
				arr[i]=$("<div id=rf_7"+i+">"+array[i]+"</div>");			
				messagediv.append(arr[i]);
				messagediv.on(
					"click",
					"#rf_7"+i,
					function(){
						inp.val($(this).text());
						md.attr("style","display:none");
						$("form").trigger("submit");
					}
				);
				messagediv.on(
					"mousemove",
					"#rf_7"+i,
					function(){
						$(this).attr("style","background-color:#ece9d8");
					}
				);
				messagediv.on(
					"mouseout",
					"#rf_7"+i,
					function(){
						$(this).attr("style","background-color:white");
					}
				);
			}
			$("body").bind(
				"click",
				function(){
					md.attr("style","display:none");
				}
			);
        }
     }
     		//alert($(this).attr("id")+$(this).text());
			//alert(array.length);
			//alert(array.join(":"));
			//var messagediv=document.getElementById("xxx");
     			/*
			var newDiv1=$("<div id='70'>"+array[0]+"</div>");
			var newDiv2=$("<div id='71'>"+array[1]+"</div>");
			newDiv1.appendTo(messagediv);
			newDiv2.appendTo(messagediv);
			$("#70").bind(
					"click",
					function(){
						//alert($("#"+iid).attr("id")+$("#"+iid).text());
						inp.val($("#70").text());
						md.attr("style","display:none");
						$("form").trigger("submit");
					}
				);
			$("#71").bind(
					"click",
					function(){
						//alert($("#"+iid).attr("id")+$("#"+iid).text());
						inp.val($("#71").text());
						md.attr("style","display:none");
						$("form").trigger("submit");
					}
				);
				*/
			
    			//divap.appendTo(messagediv);
     			//arr[i].attr("style","border:1px solid black");
				//newDiv.attr("style","position: absolute;z-index:4");
				//newDiv.setAttribute("id","hhh");
				//alert($("#"+iid).attr("id")+$("#"+iid).text());
     				/*
				$("#xxx:nth-child("+i+")").bind(
					"click",
					function(){
						alert($("#xxx:nth-child("+i+")").attr("id")+$("#xxx:nth-child("+i+")").text());
						inp.val($("#xxx:nth-child("+i+")").text());
						md.attr("style","display:none");
						//$("form").trigger("submit");
					}
				);
				*/
     			/*
			for(var i=1;i<=array.length;i++){
				$("#xxx:nth-child("+i+")").bind(
					"click",
					function(){
						alert($("#xxx:nth-child("+i+")").attr("id")+$("#xxx:nth-child("+i+")").text());
						inp.val($("#xxx:nth-child("+i+")").text());
						md.attr("style","display:none");
						//$("form").trigger("submit");
					}
				);
			}
			
			for(var i=0;i<array.length;i++){
				$("#7"+i).bind(
					"click",
					function(){
						alert($("#7"+i).attr("id")+$("#7"+i).text());
						inp.val($("#7"+i).text());
						md.attr("style","display:none");
						//$("form").trigger("submit");
					}
				);
			}
			*/
</script>
  </body>
</html>


