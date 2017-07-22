var job = {
	//专门存放数据的
		data: {
			user:{
				username:'',
				uid:''
			}
		},
		init: {
		//进行数据的初始化
			initData: function(){
				job.data.user.username=$(this).parent().siblings("td:first").text();
				job.data.user.uid=$(this).parent().siblings("input[type='hidden']").val();
			},
		//对事件进行初始化
		    initEvent: function(){
		    	
		    	//给设置角色声明事件
				$("a").each(function() {
					if($(this).text()=="设置职位"){
						$(this).unbind("click"),
						$(this).bind("click",function(){ 
							//复选框不可以被选中！
							$("#allchecked").attr("disabled","disabled");
							job.option.divOpt.showDiv();
							
							//使用this调用一个方法的做法：
							job.init.initData.call(this);//利用超级链接进行数据的实例化？
							job.option.userOpt.showUserName();
							job.option.roleTree.changeLoadinfToTree({tree:false});
							job.option.roleTree.loadTree();//加载树
							return false;
						})
					}
				});
				//全选事件
				$("#allchecked").unbind("change");
				$("#allchecked").bind("change",function(){
					//直接调用下面定义喊的方法就开以；
					var checked=$(this).attr("checked");
					//job.option.roleTree.allChecked();
					job.option.roleTree.allChecked(checked);
				});
				
				//保存事件
				$("#privilegesave").unbind("click");
				$("#privilegesave").bind("click",function(){
					job.option.roleTree.saveJob();
				});
				
		}
			
		},
		option: {
			//操作内容
			divOpt: {
				showDiv: function(){
					$("div:hidden").show();
				},
			},
			userOpt: {
				showUserName:function(){
					$("#userImage").text(job.data.user.username);
				}
			},
			roleTree: {
				zTreePlugin: '',
				setting:{
					isSimpleData: true,
					treeNodeKey: "jid",
					treeNodeParentKey: "pid",
					showLine: true,
					checkable:true,
					callback:{
						change:function(){
							job.option.roleTree.showChecked();
						}
					},
					root:{
						isRoot:true,
						nodes:[]
					}
				},
			    loadTree: function(){//回调函数，表示的是前面js形参赋值后运算的结果。
			    	var uid=job.data.user.uid;
			    	$.post("jobTreeAction_getJSON.action",{uid:uid},function(data){
			    		//此时的function是另外一个回调函数
			    		job.option.roleTree.changeLoadinfToTree({tree:true});
			    		job.option.roleTree.zTreePlugin=$("#privilegeTree").zTree(job.option.roleTree.setting,data);//这个树生成函数是有一个返回值的
			    		//此时的data是有值的！！！一般有值的就是在在这里进行回调函数的使用
			    		
			    		
			    		$("#allchecked").attr("disabled","");
			    		job.option.roleTree.showChecked();
			    		
			    	});
			    },
			    
			    changeLoadinfToTree:function(json){
			    	if(json.tree==true){
			    		$("#privilegeTree").show();
			    		$("#loading").hide();
			    	}else{
			    		$("#privilegeTree").hide();
			    		$("#loading").show();
			    	}
			    },
			    allChecked: function(checked){
			    	if(checked){//获取是否被选择的状态
			    		
			    		job.option.roleTree.zTreePlugin.checkAllNodes(true);
			    	}else{
			    		
			    		job.option.roleTree.zTreePlugin.checkAllNodes(false);
			    	}
			    },
			    
			    showChecked: function(){//初始化的时候的判断而已，只执行一次。！！
			    	var uncheckeds=job.option.roleTree.zTreePlugin.getCheckedNodes(false);
			    	if(uncheckeds.length==0){
			    		//全部被选中的时候：
			    		$("#allchecked").attr("checked",true);
			    		
			    	}else{
			    		$("#allchecked").attr("checked",false);
			    		
			    	}
			    },
			    saveJob: function(){
			    	//传递参数进行数据交互
			    	
			    	//1.获取被选中的
			    	
			    	var checkedNodes=job.option.roleTree.zTreePlugin.getCheckedNodes(true);
			    	var buffer="";
			    	for(var i=0;i<checkedNodes.length;i++){
			    		if(i==checkedNodes.length-1){
			    			buffer=buffer+checkedNodes[i].jid;
			    		}else{
			    			buffer=buffer+checkedNodes[i].jid+",";
			    		}
			    	}
			    	var params={
			    			uid:job.data.user.uid,
			    			param:buffer
			    	}
			    	
			    	$.post("jobAction_saveJobsAandUser.action",params,function(data){
			    		alert("操作成功！");
			    	});
			    	
			   	
			    }
			    
			    
			}
		}
}
$(function(){
	job.init.initEvent();
	
})