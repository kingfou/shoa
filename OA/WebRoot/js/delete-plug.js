//写一个删除的插件
(function(jQuery){
	//1.动态的添加一个属性，我们在页面调用这个属性就可以完成功能。
	jQuery.oaConfirm=function(){
		$("a").each(function(){
			
			if($(this).text()=="删除"){
				$(this).unbind("click");
				$(this).bind("click",function(){
					if(window.confirm("确定要进行删除操作吗？浙江删除该员工的所有信息？？")){
						return true;
					}else{return false;
					}
				})
			}
		})
	}
})(jQuery)
$(function(){
	jQuery.oaConfirm();
})