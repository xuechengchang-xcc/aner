<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path =request.getContextPath();
    String basePath=request.getScheme()+"://"
                    +request.getServerName()+":"
                    +request.getServerPort()+"/"
                    +path; //返回http://localhost:8088/path
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
退出成功！<a href="<%=basePath%>/login">登录</a>
</body>
</html>
