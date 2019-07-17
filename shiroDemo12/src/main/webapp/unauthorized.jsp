<%--
  Created by IntelliJ IDEA.
  User: 安儿
  Date: 2019/7/9
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>没有权限</title>
    <style>.error{color:red;}</style>
</head>
<body>
    <div class="error">您没有权限[${exception.message}]</div>
</body>
</html>
