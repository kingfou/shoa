var menuitem={
		setting:{
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			showLine: true,			
			root:{
				isRoot:true,
				nodes:[]
			}
		},
		loadtree:function(){
		//请求一个action获得数据：
			$.post("privilegeAction_showItems.action",null,function(data){
				
				$("#menuTree").zTree(menuitem.setting, data);
			})
		}
}
$(function(){
	menuitem.loadtree();
})