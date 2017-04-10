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
<div class="container">
    <br>
    <br>
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-8"><h2>You are signedin as: ${loginUser.username}</h2></div>
        <div class="col-xs-6 col-md-4"><a href="main/logout" class="btn btn-default">Logout</a></div>
    </div>
    <h3>Welcome to your interview registration App</h3>
    <br>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">Insert new Interview</h4>
        </div>
        <div class="panel-body">
            <form:form class="col-md-12" method="post" modelAttribute="interview" action="interview/add">
        <div class="form-group">
            <form:input type="text" class="form-control input-lg" placeholder="Company" path="company"/>
            <form:errors path="company" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control input-lg" placeholder="Interviewer" path="interviewer"/>
            <form:errors path="interviewer" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:textarea class="form-control input-lg" rows="5" placeholder="Comments" path="comments"/>
            <form:errors path="comments" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:select class="form-control" placeholder="Interview Type" path="interviewType">
                <form:option value="----Choose One Interview Type-----"/>
                <form:option value="NA"/>
                <form:option value="General"/>
                <form:option value="Technical"/>
                <form:option value="Proposal"/>
            </form:select>
        </div>
        <div class="form-group">
            <form:select class="form-control" placeholder="Interview Status" path="status">
                <form:option value="----Choose One Status Type-----"/>
                <form:option value="Prospect"/>
                <form:option value="Interview"/>
                <form:option value="Hired"/>
                <form:option value="Not Hired"/>
                <form:option value="Future"/>
            </form:select>
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control input-lg" placeholder="YYYY-MM-DD" path="date"/>
            <form:errors path="date" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control input-lg" placeholder="HH:MM" path="hour"/>
            <form:errors path="hour" cssClass="error"/>
        </div>
        <div class="form-group">
            <tr>
                <th><input type="submit" class="btn btn-primary" value="Add/Update"/></th>
                <th><a href="interviews" class="btn btn-warning">Reset</a></th>
            </tr>
        </div>
    </form:form>
    <div>
        <h6>${message}</h6>
    </div>
    </div>
</div>
<div>
    <h3>List of User Interviews</h3>
    <table class="table table-striped">
        <tr>
            <th>Date</th>
            <th>Hour</th>
            <th>Company</th>
            <th>Type</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <c:forEach var="interview" items="${interviews}">
            <tr>
                <td>${interview.date}</td>
                <td>${interview.hour}</td>
                <td>${interview.company}</td>
                <td>${interview.interviewType}</td>
                <td>${interview.status}</td>
                <td>
                    <a href="interview/edit/${interview.interview_id}" class="btn btn-info">Edit</a>
                    <a href="interview/delete/${interview.interview_id}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

