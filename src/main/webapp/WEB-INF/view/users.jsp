<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users Store</title>
</head>
<body>
<h2>
    <form action="${pageContext.servletContext.contextPath}/user_create_servlet" method="get">
        <button type="submit"> Add User</button>
    </form>
    <form action="${pageContext.servletContext.contextPath}/exit" method="post">
        <button type="submit"> Exit</button>
    </form>
</h2>
<br/>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
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
        <th>Button 2</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out>
            </td>
            <td><c:out value="${user.name}"></c:out>
            </td>
            <td><c:out value="${user.login}"></c:out>
            </td>
            <td><c:out value="${user.password}"></c:out>
            </td>
            <td><c:out value="${user.email}"></c:out>
            </td>
            <td><c:out value="${user.role}"></c:out>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/user_update_servlet" method="get">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                    <button type="submit"> Edit</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/servlets" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                    <button type="submit"> Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>