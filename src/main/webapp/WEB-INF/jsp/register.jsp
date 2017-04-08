<%--
  Created by IntelliJ IDEA.
  User: codecadet
  Date: 04/04/17
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Login Form</title>
</head>
<body>

<form:form class="col-md-12" method="post" modelAttribute="user">
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
        <button class="btn btn-danger btn-lg btn-block" type="submit">Register</button>
        <a href="login" class="btn btn-warning btn-lg btn-block">Cancel</a>
    </div>
    <div style="color:red">${error}</div>
</form:form>
</body>
</html>
