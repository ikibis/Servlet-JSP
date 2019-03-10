<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="<c:url value="/js/scripts.js" />"></script>
</head>
<body>
<br>
<br>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>login</th>
            <th>password</th>
            <th>email</th>
            <th>role</th>
            <th>country</th>
            <th>city</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <form action="${pageContext.servletContext.contextPath}/user_update_servlet" method="post"
              onsubmit="validate()">
            <td>
                <c:out value="${user.id}"></c:out>
            </td>
            <td>
                <label for="name"></label>
                <input class="form-control input-sm" id="name" type="text" name='name'
                       value="<c:out value="${user.contacts.name}"></c:out>">
            </td>
            <td>
                <label for="login"></label>
                <input class="form-control input-sm" id="login" type="text" name='login'
                       value="<c:out value="${user.login}"></c:out>">
            </td>
            <td>
                <label for="password"></label>
                <input class="form-control input-sm" id="password" type="text"
                       name='password'
                       value="<c:out value="${user.password}"></c:out>">
            </td>
            <td>
                <label for="email"></label>
                <input class="form-control input-sm" id="email" type="text" name='email'
                       value="<c:out value="${user.contacts.email}"></c:out>">
            </td>
            <td>
                <c:out value="${user.role}"></c:out>
                <input required type=hidden name="role" value="<c:out value="${user.role}"></c:out>">
            </td>
            <td>
                <label for="country"></label>
                <input class="form-control input-sm" id="country" type="text"
                       name='country'
                       value="<c:out value="${user.contacts.country}"></c:out>">
            </td>
            <td>
                <label for="city"></label>
                <input class="form-control input-sm" id="city" type="text" name='city'
                       value="<c:out value="${user.contacts.city}"></c:out>">
            </td>
            <td>
                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                <input type="hidden" name="action" value="update">
                <button type='submit'> Save Changes</button>
            </td>
        </form>
        </tbody>
    </table>
</div>
</body>
</html>