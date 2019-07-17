<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path=request.getContextPath();
    String bastPath=request.getScheme()+"://"
                    +request.getServerName()+":"+
                    request.getServerPort()+"/"+path;
%>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red}</style>
</head>
<body>
    <div class="error">${error}</div>
    <form action="<%=bastPath%>/formfilterlogin" method="post">
        用户名:<input type="text" name="username"/><br/>
        密码:<input type="text" name="password"><br/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
