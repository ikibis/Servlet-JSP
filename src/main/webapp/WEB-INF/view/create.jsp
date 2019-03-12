<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
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
            <th>name</th>
            <th>login</th>
            <th>password</th>
            <th>email</th>
            <th>role</th>
            <th>country</th>
            <th>city</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <form id="create_user" action="${pageContext.servletContext.contextPath}/user_create_servlet" method="post">
            <td>
                <label for="name"></label><input class="form-control input-sm" id="name" type="text" name='name'>
            </td>
            <td>
                <label for="login"></label><input class="form-control input-sm" id="login" type="text" name='login'>
            </td>
            <td>
                <label for="password"></label><input class="form-control input-sm" id="password" type="text"
                                                     name='password'>
            </td>
            <td>
                <label for="email"></label><input class="form-control input-sm" id="email" type="text" name='email'>
            </td>
            <td>
                <label for="role"></label><select name="role" id="role">
                <option value="admin">Admin</option>
                <option value="moderator">Moderator</option>
                <option value="user">User</option>
            </select>
            </td>
            <td>
                <label for="country"></label>
                <select id="country" name="country">
                    <script>$(fillCountriesCreate());</script>
                </select>
            </td>
            <td>
                <label for="city"></label>
                <select id="city" name="city">
                    <script>
                        fillCitiesCreate($('#country').val());
                        $('#country').change(function () {
                            fillCitiesCreate($('#country').val());
                        });
                    </script>
                </select>
            </td>
            <td>
                <input type="hidden" name="action" value="create">
                <button type='button' onclick="create()"> Create</button>
            </td>
        </form>
        </tbody>
    </table>
</div>
</body>
</html>