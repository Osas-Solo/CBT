<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ostech.cbt.model.Subject" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CBT</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<%--<%
    ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");

    for (Subject currentSubject: subjects) {
%>
    <p>${currentSubject.getName()}</p>
<%
    }
%>--%>
<a href="${pageContext.request.contextPath}/">Hello Servlet</a>
</body>
</html>