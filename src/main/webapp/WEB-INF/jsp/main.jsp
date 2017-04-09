<%--
  Created by IntelliJ IDEA.
  User: codecadet
  Date: 05/04/17
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Users Management Page</title>
</head>
<body>

<div>
    <div>
    <tr>
        <th><h1>${loginUser.username}</h1></th>
        <th><a href="main/logout" class="btn btn-default">Logout</a>
        </th>
    </tr>
    </div>
    <h3>Welcome to this amazing App!</h3>
    <p></p>
    <p></p>
    <h4>User Management</h4>
    <form:form class="col-md-12" method="post" modelAttribute="user" action="main/add">
        <p></p>
        <p></p>
        <div class="form-group">
            <form:input type="text" class="form-control input-lg" placeholder="Name" path="username"/>
            <form:errors path="username" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="password" class="form-control input-lg" placeholder="Password" path="password"/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="email" class="form-control input-lg" placeholder="Email" path="email"/>
            <form:errors path="email" cssClass="error"/>
        </div>
        <div class="form-group">
            <tr>
                <th><input type="submit" class="btn btn-primary" value="Add/Update"/></th>
                <th><a href="main" class="btn btn-warning">Reset</a></th>
            </tr>
        </div>
    </form:form>
    <div>
        <h6>${message}</h6>
    </div>

</div>

<div>
    <h3>List of App Users</h3>
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <a href="main/edit/${user.username}" class="btn btn-info">Edit</a>
                    <a href="main/delete/${user.username}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <h3>List of User Interviews</h3>
    <table class="table table-striped">
        <tr>
            <th>Company</th>
            <th>User</th>
            <th>Date</th>
            <th>Hour</th>
            <th>Type</th>
            <th>Status</th>
        </tr>

        <sql:setDataSource var="users" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/ac_interviews"
                           user="testing"/>

        <c:forEach var="interview" items="${interviews}">

            <sql:query dataSource="${users}" var="result">
                SELECT DISTINCT users.username FROM users,interviews WHERE users.user_id = interviews.user_id AND interviews.user_id = ${interview.user_id};
            </sql:query>

            <tr>
                <td>${interview.company}</td>
                <c:forEach var="u" items="${result.rows}">
                <td>${u.username}</td>
                </c:forEach>
                <td>${interview.date}</td>
                <td>${interview.hour}</td>
                <td>${interview.interviewType}</td>
                <td>${interview.status}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
