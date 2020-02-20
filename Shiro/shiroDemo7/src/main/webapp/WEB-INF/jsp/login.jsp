<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;//返回形式http://localhost:8080/upload/
 %>
    <html>
<head>
    <title>登录</title>
    <style>.error{color: red}</style>
</head>
<body>
    <div class="error">${error}</div>
    <form action="<%=basePath%>/login" method="post">
        用户名：<input type="text" name="username"/>
        密码：<input type="password" name="password"/>
        <input type="submit" value="登录">
    </form>
</body>
</html>
