function myHttp(){
	var xmlHttp;
	try{
		xmlHttp=new XMLHttpRequest();//浏览器提供的方法
		
	}catch(e){
		alert("获取失败");
	}
	alert(xmlHttp);
	return xmlHttp;	
}
function ajax(ajaxJSON){
	var xmlHttp=myHttp();
	xmlHttp.onreadystatechange=function(){
		
		if(xmlHttp.readyState==4){//请求是否成功？？？
			
			if(xmlHttp.status==200||xmlHttp.status==304){
				
				ajaxJSON.callback(xmlHttp.responseText);//自己默认是回调函数
				//表示调用一个回调函数，带有参数。带参数的一个函数，这里是实参
			}
			
		}
	}
	xmlHttp.open(ajaxJSON.method,ajaxJSON.url,true);//异步请求
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xmlHttp.send(ajaxJSON.data);
}