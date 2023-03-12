<%@ page import="com.ostech.cbt.model.Candidate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Candidate candidate = (Candidate) request.getAttribute("candidate");
%>
<!DOCTYPE html>
<html>
<head>
    <title>CBT | <%=pageTitle%></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
</head>
<body>
<div class="container mb-5">
    <header class="bg-primary">
        <h1 class="text-center"><a href="${pageContext.request.contextPath}/index">CBT</a></h1>

        <nav class="nav">
            <ul class="nav nav-justified">
                <li class="nav-item">
                    <a class="navbar-link" href="${pageContext.request.contextPath}/index">Home</a>
                </li>
                <li class="nav-item">
                    <a class="navbar-link" href="${pageContext.request.contextPath}/test">Test</a>
                </li>
                <%
                    if (!candidate.isFound()) {
                %>
                <li class="nav-item">
                    <a class="navbar-link" href="${pageContext.request.contextPath}/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="navbar-link" href="${pageContext.request.contextPath}/signup">Signup</a>
                </li>
                <%
                    } else {
                %>
                <li class="nav-item">
                    <a class="navbar-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                </li>
                <%
                    }
                %>
            </ul>
        </nav>
    </header>