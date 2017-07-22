var privilege={
		//所有的操作，包括方法，函数，参数都是直接在这里定义和实现的
		data: {
			job: {
				name:'',
				jid:''
			}
		},
		init: {//利用页面的数据对data进行初始化操作；
			initDate: function(){
				//超链接调用此函数
				
				privilege.data.job.name=$(this).parent().siblings("td:first").text();
				
				privilege.data.job.jid=$(this).parent().siblings("input[type='hidden']").val();
				
			},
			initEvent: function(){//对事件进行初始化
				//1.实现参数的初始化：
				$("a").each(function() {//超链接点击事件的实现
					
					if($(this).text()=="设置权限"){
						$(this).unbind("click");
						$(this).bind("click",function(){
							//下面是点击后需要是实现的功能：
							//1.初始化数据并显示
							privilege.init.initDate.call(this);
							privilege.option.divOpt.showDiv();
							//回掉的时候！！！注意不要写initDate()!!!!
							privilege.option.userOtp.showUser();
							
							//一开始的时候，还没有数据加载时候，全选是无法被选中的
							$("#allchecked").attr("disabled","disabled");
							privilege.option.zTree.LoadingToTree(true);
							privilege.option.zTree.loadTree();
							
						})
						
						
					}
				});
				
				
				//全选框事件：
				$("#allchecked").unbind("change");
				$("#allchecked").bind("change",function(){
					privilege.option.zTree.allChecked.call(this);
				});
				
				$("#savePrivilege").unbind("click");
				$("#savePrivilege").bind("click",function(){
					privilege.option.zTree.userSave();
				});
				
				
			}
		},
		option: {
			divOpt: {
				showDiv: function(){
					$("div:hidden").show();
				}
			},
			userOtp: {
				showUser: function(){
					$("#roleImage").text(privilege.data.job.name);
				}
			},
			//树的属性和函数：
			zTree: {
				
				zTreePlugin: '',
				setting:{
					isSimpleData: true,
					treeNodeKey: "id",
					treeNodeParentKey: "pid",
					showLine: true,
					checkable:true,
					callback:{
						change:function(){
							privilege.option.zTree.checkedState();
						}
					},
					root:{
						isRoot:true,
						nodes:[]
					}
				},
			    loadTree: function(){//回调函数，表示的是前面js形参赋值后运算的结果。
			    	var jid=privilege.data.job.jid;
			    	
			    	$.post("privilegeAction_showPrivilegeByJobs.action",{jid:jid},function(data){
			    		//此时的function是另外一个回调函数
			    		
			    		privilege.option.zTree.zTreePlugin=$("#privilegeTree").zTree(privilege.option.zTree.setting,data);//这个树生成函数是有一个返回值的
			    		//此时的data是有值的！！！一般有值的就是在在这里进行回调函数的使用
			    		privilege.option.zTree.LoadingToTree(false);
			    		$("#allchecked").attr("disabled","");
			    		privilege.option.zTree.checkedState();
			    		
			    	});
			    },
			    
			    //一开始就检测被选中的框：
			    showChecked: function(){//初始化的时候的判断而已，只执行一次。！！
			    	var uncheckeds=privilege.option.zTree.zTreePlugin.getCheckedNodes(false);
			    	if(uncheckeds.length==0){
			    		//全部被选中的时候：
			    		$("#allchecked").attr("checked",true);
			    		
			    	}else{
			    		$("#allchecked").attr("checked",false);
			    		
			    	}
			    },
			    
			    //复选框事件：
			    allChecked: function(check){
			    	if($(this).attr("checked")){
			    		privilege.option.zTree.zTreePlugin.checkAllNodes(true);
			    	}else{
			    		privilege.option.zTree.zTreePlugin.checkAllNodes(false);
			    	}
			    },
			    
			    //保存权限：
			    userSave: function(){
			    	//1.获取被选择的节点，
			    	//2.获取job 的jid;
			    	//将参数直接传递到action进行关系的设置：
			    	var nodes=privilege.option.zTree.zTreePlugin.getCheckedNodes(true);
			    	var param='';
			    	for(var i=0;i<nodes.length;i++){
			    		if(i==nodes.length-1){
			    			param=param+nodes[i].id;
			    		}else{
			    			param=param+nodes[i].id+",";
			    		}
			    	}
			    	var jid=privilege.data.job.jid;
			    	params={
			    			jid:jid,
			    			param:param
			    	}
			    	$.post("privilegeAction_updatePrivilege.action",params,function(data){
			    		alert("操作成功！");
			    	});
			    },
			    LoadingToTree: function(state){
			    	if(state){//true表示loading
			    		$("#privilegeTree").hide();
			    		$("#loading").show();
			    	}else{
			    		$("#privilegeTree").show();
			    		$("#loading").hide();
			    	}
			    },
			    //判断加载树后全选框的状态：
			    checkedState: function(){
			    	var uncheckeds=privilege.option.zTree.zTreePlugin.getCheckedNodes(false);
			    	if(uncheckeds==0){
			    		//表示全部选中
			    		$("#allchecked").attr("checked",true);
			    	}else{
			    		$("#allchecked").attr("checked",false);
			    	}
			    }
			    
			}
		}
		
}
$(function(){
	privilege.init.initEvent();

})