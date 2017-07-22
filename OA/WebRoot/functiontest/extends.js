function Stduent1(){
	
}
Stduent1.prototype.a=66;
Stduent1.prototype.show=function(){
	alert("show is a property,but its value is a object");
};


var p=new Stduent1();
p.show();
function createF(json){
	function F(){};
	for(var i in json){
		F.prototype[i]=json[i];
	}
	return F;//表示被创建是对象是一个F对象！！！！
	
}
var FF=new createF({a:33,b:77});
//注意！F()和F是不一样的！F()表示的是一个构造器，相当于function F(){};F表示的是一个属性值,是一个构造器的对象？？？
var t=new FF();
alert(t.a);

function createP(json){
	function F(){};
	for(var i in json){
		F.prototype[i]=json[i];
	}
	var f=new F();
	return f;
}
var hh=createP({a:222,b:9999});
alert(hh.b);
//一般使用构造器取得的就是该构造器的对象！，如果构造器返回的是另外一个构造器的话，那么我们还要再次获取对象，就是再一次使用new 
function thisT(){
	alert(this);
}
Stduent1.c=thisT;
Stduent1.c();
//匿名函数的调用和一般都函数的调用其实是一样的，都是加个括号进行调用的。
(function(){
	alert("sdscs");
})();
(function(a){alert(a);})(33);
