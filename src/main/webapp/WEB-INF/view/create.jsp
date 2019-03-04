<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<table style="border: 1px solid black" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>name</th>
        <th>login</th>
        <th>password</th>
        <th>email</th>
        <th>role</th>
        <th>Button 1</th>
    </tr>
    <tr>
        <form action="${pageContext.servletContext.contextPath}/user_create_servlet" method="post">
            <input type="hidden" name="action" value="create">
            <td><input required type='text' name='name'></td>
            <td><input required type='text' name='login'></td>
            <td><input required type='text' name='password'></td>
            <td><input required type='text' name='email'></td>
            <td><select name="role">
                <option value="admin">Admin</option>
                <option value="moderator">Moderator</option>
                <option value="user">User</option>
            </select></td>
            <td>
                <button type='submit'> Create User</button>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
