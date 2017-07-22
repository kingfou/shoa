function myHttp(){
	var xmlHttp;
	try{
		xmlHttp=new XMLHttpRequest();//浏览器提供的方法
		
	}catch(e){
		alert("获取失败");
	}
	return xmlHttp;	
}
function ajax(ajaxJSON){
	var xmlHttp=myHttp();
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState==4){//请求是否成功？？？
			if(xmlHttp.status==200||xmlHttp.status==304){
				alert(xmlHttp.responseText);
				ajaxJSON.callback(xmlHttp.responseText);
			}
			
		}
	}
	xmlHttp.open(ajaxJOSN.method,ajaxJSON.url,true);//异步请求
	ajaxJOSN.callback(xmlHttp.responseText);
}