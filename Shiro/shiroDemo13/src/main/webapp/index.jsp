<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<body>
<shiro:guest>
    欢迎游客登录,<a href="${pageContext.request.contextPath}/login.sjp">点击登录</a>
</shiro:guest>
<shiro:user>
    欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/logout">点击退出</a>
</shiro:user>
<shiro:hasRole name="admin">
    您有角色admin
</shiro:hasRole>
<h2>Hello World!</h2>
</body>
</html>
