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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
</head>
<body>
<div class="container mb-5">
    <header class="sticky-md-top p-3 mb-3">
        <h1 class="text-center"><a href="${pageContext.request.contextPath}/index">CBT</a></h1>

        <nav>
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/test">Test</a>
                </li>
                <%
                    if (candidate == null) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/signup">Signup</a>
                </li>
                <%
                    } else {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                </li>
                <%
                    }
                %>
            </ul>
        </nav>
    </header>
