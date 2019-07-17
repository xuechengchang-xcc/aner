<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath=request.getScheme()+"://"
                    +request.getServerName()+":"
                    +request.getServerPort()+"/"
                    +path;  //返回形式http://localhost:8080/upload/
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    欢迎${subject.principal}登录成功！<a href="<%=basePath%>/logout">退出</a>
</body>
</html>
