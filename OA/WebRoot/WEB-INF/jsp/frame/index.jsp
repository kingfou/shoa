<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ItcastOA</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="100,*,25" framespacing="0" border="0" frameborder="no">
    <frame src="forwardAction_forward.action?method=top" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="forwardAction_forward.action?method=left" scrolling="yes" />
        <frame noresize name="right" src="forwardAction_forward.action?method=right" scrolling="yes" />
    </frameset>
    <frame noresize name="status_bar" scrolling="no" src="forwardAction_forward.action?method=bottom" />
</frameset>
<noframes>

<body>
</body>
</noframes>
</html>
