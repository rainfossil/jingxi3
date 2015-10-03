//邮箱验证
function verifyEmail(email){
	var emailrv=/^\w{2,23}@\w{2,5}\.\w{2,5}(.\w{2,5})?$/;
	var boo=emailrv.test(email);
	return boo;
}
//昵称验证(可用字母、汉字、数字，不小于3位)
function verifyName(name){
	var namerv=/^[^\f\n\r\t\v\s\.!@#%*;:"'~`<>\$\(\)\{\}\[\]\?\^\/\|]{3,}$/;
	var boo=namerv.test(name);
	return boo;
}
//密码验证(需同时包含字母和数字，不小于7位)
function verifyPass(pass){
	var passrv1=/^[a-zA-Z0-9]{7,}$/;
	var passrv2=/[0-9]+/;
	var passrv3=/[a-zA-Z]+/;
	if(passrv1.test(pass)&&passrv2.test(pass)&&passrv3.test(pass)){
		return true;
	}else{
		return false;
	}
}
function verify(param){
	var inp=document.getElementById(param);
	var value=inp.value;
	
	if(param=="email"){
		var flag=verifyEmail(value);
		var message=document.getElementById("3114");
		var label=document.getElementById("31114");
		if(flag){
			label.style.color="green";
			label.innerHTML="√";
			message.innerHTML="";
		}else{
			label.style.color="red";
			label.innerHTML="*";
			message.innerHTML="邮箱格式错误";
		}
	}else if(param=="pass"){
		var flag=verifyPass(value);
		var message=document.getElementById("3112");
		var label=document.getElementById("31112");
		if(flag){
			label.style.color="green";
			label.innerHTML="√";
			message.innerHTML="";
		}else{
			label.style.color="red";
			label.innerHTML="*";
			message.innerHTML="需同时包含字母和数字，不小于7位";
		}
	}else if(param=="id"){
		var flag=verifyName(value);
		var message=document.getElementById("3111");
		var label=document.getElementById("31111");
		if(flag){
			label.style.color="green";
			label.innerHTML="√";
			message.innerHTML="";
		}else{
			label.style.color="red";
			label.innerHTML="*";
			message.innerHTML="可用字母、汉字、数字，不小于3位";
		}
	}else if(param=="pass1"){
		var pass=document.getElementById("pass");
		var value1=pass.value;
		var message=document.getElementById("3113");
		var label=document.getElementById("31113");
		if(value==value1){
			label.style.color="green";
			label.innerHTML="√";
			message.innerHTML="";
		}else{
			label.style.color="red";
			label.innerHTML="*";
			message.innerHTML="两次输入不同";
		}
	}
}