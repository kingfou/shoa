<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
    <!-- 插件的使用：删除提醒 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/zTree/zTreeStyle/zTreeStyle.css" type="text/css">
    <script src="${pageContext.request.contextPath}/js/delete-plug.js"></script>
    <script src="${pageContext.request.contextPath}/js/user_job.js"></script>
    <script src="${pageContext.request.contextPath}/zTree/js/jquery-ztree-2.5.js"></script>
	
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/css/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td>所属岗位</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        	<s:iterator value="#userList" var="d">
	            <tr class="TableDetail1 template">
	                <td><s:property value="username"/></td>
	                <td><s:property value="department.name"/></td>
	                <s:hidden name="uid"></s:hidden>
	                <td>
	                	<s:iterator value="jobs">
	                		<s:property value="name"/>
	                	</s:iterator>
	                </td>
	                <td><s:a href="userAction_delete.action?uid=%{uid}">删除</s:a>
	                    <s:a href="userAction_updateUI.action?uid=%{uid}">修改</</s:a>
						<s:a href="javascript:privilegeclick();">设置职位</s:a>
	                </td>
	            </tr>
            </s:iterator>
        </tbody>
    </table>
    
     <div id="TableTail">
        <div id="TableTail_inside">
            <a href="userAction_addUI.action"><img src="${pageContext.request.contextPath}/css/images/createNew.png" /></a>
		</div>
	 </div>

    
    <div class="ItemBlock_Title1" id="userTitle" style="display: none;"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif"/> 用 户 姓 名：
			  <label id="userImage"></label>
        </div>
    <div class="ItemBlock_Title1" id="privilegeTitle" style="display: none;">
        <div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif"/>选 择 职位</div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder" style="display: none;" id="privilegeContent">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="checkbox" id="allchecked" disabled="disabled"/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
								<ul id='privilegeTree' class="tree"></ul>
								<img id="loading" src="css/images/loading.gif"/>
							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <image id="privilegesave" src="${pageContext.request.contextPath}/css/images/save.png"/>
        </div>
</div>

</body>
</html>
