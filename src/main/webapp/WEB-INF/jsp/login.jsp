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
    <script src="https://use.fontawesome.com/43bdc53d05.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Oswald|PT+Sans" rel="stylesheet">
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <title>Login Form</title>
</head>
<body>
<br>
<br>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-sm-4"></div>
            <div class="col-xs-6 col-sm-4">
                <div>
                    <div class="panel-body">
                        <div align="center">
                            <img src="http://www.academiadecodigo.org/assets/img/logo_footer.jpg">
                        </div>
                        <br>
                        <br>
                        <div class="row">
                            <form:form method="post" modelAttribute="user">
                                <div class="form-group">
                                    <form:input type="text" class="form-control input-lg" placeholder="Name" path="username"/>
                                    <form:errors path="username" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <form:input type="password" class="form-control input-lg" placeholder="Password" path="password"/>
                                    <form:errors path="password" cssClass="error"/>
                                </div>
                                <div class="col-xs-6 form-group pull-right">
                                    <button class="btn btn-success btn-lg btn-block" type="submit">LOGIN</button>
                                </div>
                                <div class="col-xs-6 form-group pull-left">
                                    <a href="register" class="btn btn-danger btn-lg btn-block">REGISTER</a>
                                </div>
                                <div style="color:red">${error}</div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        <div class="col-xs-6 col-sm-4"></div>
    </div>
</div>
</body>
</html>
