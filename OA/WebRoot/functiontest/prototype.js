function Person(){
	
}


function Test(){
	var p=new Person();
	p.a=33;
	alert(p.a);
}
function Student(){}
//利用原型为我们的对象直接添加属性的做法！这时候可以直接点出属性。
Student.prototype.a=6;
function Test1(){
	var test=new Student();
	alert(test.a);
}
Test1();
Test();
//通过prototype为对象添加方法，并进行方法的调用
Student.prototype.setName=function(name){
	this.name=name;	
};
Student.prototype.getName=function(){
	return this.name;
};
function Test3(){
	var test=new Student();
	test.setName(name);
};
Test3();
//function Person(){}表示创建一个对象！！！
// var json={key:vaue;key:value;}表示创建一个json格式的对象。js里面也就只有这两中格式的对象创建
//使用prototype为对象创建的属性是各个new 出来的对象都会直接哦那个有的。
