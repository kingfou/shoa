function Person(){
	
}//表示声明一个对象
Person.a=5;//表示给对象属性赋值;
var json={
	c:function(){
		alert("json");
	}
};
Person.b=json;
Person.b.c();//表示会执行这个方法。
function EXT(){	
}
function btn1(){
	alert("but1");
}
function btn2(){
	alert("but2");
}
//function是一个对象！在js里一切皆对象，但是没有类得概念。
//赋值的时候，采用的是属性名称的样子，不带括号，使用的时候，如果属性本来就是一个对象的话，那么就要使用属性名称+（）这样的写法
 
EXT.button=btn1;
EXT.button();
EXT.button.BUT=btn2;
EXT.button.BUT();