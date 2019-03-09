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
    <script>
        function validate() {
            var result = true;
            var name = $('#name').val();
            var login = $('#login').val();
            var password = $('#password').val();
            var email = $('#email').val();
            var country = $('#country').val();
            var city = $('#city').val();
            if (name == '') {
                result = false;
                alert('Please, enter your Name');
            }
            if (login == '') {
                result = false;
                alert('Please, enter your Login');
            }
            if (password == '') {
                result = false;
                alert('Please, enter your Password');
            }
            if (email == '') {
                result = false;
                alert('Please, enter your Email');
            }
            if (country == '') {
                result = false;
                alert('Please, enter your Country');
            }
            if (city == '') {
                result = false;
                alert('Please, enter your City');
            }
            return result;
        }
    </script>
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
        <form action="${pageContext.servletContext.contextPath}/user_create_servlet" method="post" onsubmit="validate()">
            <td>
                <input class="form-control input-sm" id="name" type="text" name='name'>
            </td>
            <td>
                <input class="form-control input-sm" id="login" type="text" name='login'>
            </td>
            <td>
                <input class="form-control input-sm" id="password" type="text" name='password'>
            </td>
            <td>
                <input class="form-control input-sm" id="email" type="text" name='email'>
            </td>
            <td>
                <select name="role" id="role">
                    <option value="admin">Admin</option>
                    <option value="moderator">Moderator</option>
                    <option value="user">User</option>
                </select>
            </td>
            <td>
                <input class="form-control input-sm" id="country" type="text" name='country'>
            </td>
            <td>
                <input class="form-control input-sm" id="city" type="text" name='city'>
            </td>
            <td>
                <input type="hidden" name="action" value="create">
                <button type='submit'> Create User</button>
            </td>
        </form>
        </tbody>
    </table>
</div>
</body>
</html>

