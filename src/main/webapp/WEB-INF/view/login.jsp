<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
    <style>
        .center {
            position: absolute;
            left: 0;
            top: 30%;
            width: 100%;
            text-align: center;
        }
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            var login = $('#login').val();
            var password = $('#password').val();
            if (login == '') {
                result = false;
                alert('Please, enter your Login');
            }
            if (password == '') {
                result = false;
                alert('Please, enter your Password');
            }
            return result;
        }
    </script>
</head>
<body>
<div class="center">
    <h2>
        Please, enter your Login and Password
    </h2>
    <br/>
    <c:if test="${error != ''}">
        <div style="background-color: red">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <form class="form-inline" action="${pageContext.servletContext.contextPath}/login" method="post"
          onsubmit="validate()">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" name="login">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-default">Log In</button>
    </form>
</div>
</body>
</html>
