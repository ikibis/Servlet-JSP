<%@ page import="ru.kibis.servlets.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<br/>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>login</th>
        <th>password</th>
        <th>email</th>
        <th>role</th>
        <th>Button 1</th>
    </tr>
    <tr>
        <form action="${pageContext.servletContext.contextPath}/user_update_servlet" method="post">
            <td>
                <c:out value="${user.id}"></c:out>
            </td>
            <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
            <input type="hidden" name="action" value="update">
            <td><input required type=text name="name" value="<c:out value="${user.name}"></c:out>"></td>
            <td><input required type=text name="login" value="<c:out value="${user.login}"></c:out>"></td>
            <td><input required type=text name="password" value="<c:out value="${user.password}"></c:out>"></td>
            <td><input required type=text name="email" value="<c:out value="${user.email}"></c:out>"></td>
            <td>
                <c:out value="${user.role}"></c:out>
                <input required type=hidden name="role" value="<c:out value="${user.role}"></c:out>"></td>
            </td>
            <td>
                <button type="submit"> Save Changes</button>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
