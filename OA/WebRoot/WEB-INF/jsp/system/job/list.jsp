<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>岗位列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/zTree/zTreeStyle/zTreeStyle.css" type="text/css">   
    
    <script src="${pageContext.request.contextPath}/js/job_privilege.js"></script>

    <script src="${pageContext.request.contextPath}/zTree/js/jquery-ztree-2.5.js"></script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/css/images/title_arrow.gif"/> 部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">岗位名称</td>
				<td width="200px">岗位说明</td>
				<td>岗位相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">     	
        	<s:iterator value="#jobList">
				<tr class="TableDetail1 template">
					<td><s:property value="name"/></td>
					<s:hidden name="jid"></s:hidden>
					<td><s:property value="description"/></td>
					<td>
					    <a onClick="return window.confirm('这将删除该岗位所有员工的信息，您确定要删除吗？')" href="jobAction_delete.action?jid=<s:property value="jid"/>">删除</a>
						<s:a href="jobAction_updateUI.action?jid=%{jid}">修改</s:a>
						<s:a href="javascript:privilegeclick();">设置权限</s:a>
					</td>
				</tr>
			</s:iterator>
       </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="jobAction_addUI.action"><img src="${pageContext.request.contextPath}/css/images/createNew.png" /></a>
        </div>
    </div>
 	
 	<!-- 显示树内容 -->
 	<div class="ItemBlock_Title1" id="userTitle" style="display: none;"><!-- 信息说明 -->
		<div class="ItemBlock_Title1">
				<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif"/>
				<label id="roleImage"></label>
		</div>
		<div class="ItemBlock_Title1" id="privilegeTitle" style="display: none;"><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" />选择权限</div> 
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
								<input type="checkbox" id="allchecked"/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
								<ul id="privilegeTree" class="tree"></ul>
								<img id="loading" src="css/images/loading.gif"/>
							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <image id="savePrivilege" src="${pageContext.request.contextPath}/css/images/save.png"/>
        </div>
	</div>
	

   <!--说明-->	
   <div id="Description"> 
	说明：<br />
	1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br />
	2，点击部门名称，可以查看此部门相应的下级部门列表。<br />
	3，删除部门时，同时删除此部门的所有下级部门。
   </div>
</div>
</body>
</html>
