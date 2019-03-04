<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>login</th>
        <th>password</th>
        <th>Button 1</th>
    </tr>
    <tr>
        <form action="${pageContext.servletContext.contextPath}/login" method="post">
            <td><input required type='text' name='login'></td>
            <td><input required type='password' name='password'></td>
            <td>
                <button type='submit'> Log In</button>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
