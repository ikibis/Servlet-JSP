<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>Users Store</title>
    <style>
        .topleft {
            position: absolute;
            top: 8px;
            left: 16px;
            font-size: 18px;
        }
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="topleft">
    <h2>Users</h2>
    <p>This table contains all added users:</p>
    <form action="${pageContext.servletContext.contextPath}/user_create_servlet" method="get">
        <button type="submit"> Add User</button>
    </form>

    <form action="${pageContext.servletContext.contextPath}/exit" method="post">
        <button type="submit"> Exit</button>
    </form>
</div>


<br/>
<c:if test="${error != ''}">
    <div div class="center" style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<br/>
<br/>
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
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out>
                </td>
                <td><c:out value="${user.contacts.name}"></c:out>
                </td>
                <td><c:out value="${user.login}"></c:out>
                </td>
                <td><c:out value="${user.password}"></c:out>
                </td>
                <td><c:out value="${user.contacts.email}"></c:out>
                </td>
                <td><c:out value="${user.role}"></c:out>
                </td>
                <td><c:out value="${user.contacts.country}"></c:out>
                </td>
                <td><c:out value="${user.contacts.city}"></c:out>
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
        </tbody>
    </table>
</div>
</body>
</html>
