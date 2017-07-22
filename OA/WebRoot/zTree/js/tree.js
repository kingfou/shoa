var tree={
	setting:{
		isSimpleData: true,
		treeNodeKey: "mid",
		treeNodeParentKey: "pid",
		showLine: true,
		checkable:true,
		root:{
			isRoot:true,
			nodes:[]
		}
	},
    loadTree:function(){//回调函数，表示的是前面js形参赋值后运算的结果。
    	$.post("menuAction_show.action",null,function(data){
    		//此时的function是另外一个回调函数
    		//alert(data);
    		$("#tree").zTree(tree.setting,data);
    		//此时的data是有值的！！！一般有值的就是在在这里进行回调函数的使用
    		
    	})
    }
}
$(function(){
	tree.loadTree();
})