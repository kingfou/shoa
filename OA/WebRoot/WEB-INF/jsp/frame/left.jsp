<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/zTree/zTreeStyle/zTreeStyle.css" type="text/css">   
    
    <script src="${pageContext.request.contextPath}/js/menuitem.js"></script>

    <script src="${pageContext.request.contextPath}/zTree/js/jquery-ztree-2.5.js"></script>

<html>
<head>
<title>导航菜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body style="margin: 0">
<TABLE border=0 width="700">
	<TR>
		<TD width=340px align=center valign=top>
		<div class="zTreeDemoBackground">
			<ul id="menuTree" class="tree"></ul>
		</div>		
		</TD>
	</TR>
</TABLE>
</body>
</html>
