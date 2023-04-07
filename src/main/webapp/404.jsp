<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ostech.cbt.model.Subject" %>

<%!
    String pageTitle = "Lost";
%>

<%@include file="header.jsp" %>

<script>
    let currentPage = window.location.toString();
    currentPage = currentPage.slice(0, currentPage.lastIndexOf("/"));
    const homePage = "${pageContext.request.contextPath}/index";

    if (currentPage.endsWith("${pageContext.request.contextPath}")) {
        window.location.replace(homePage);
    }
</script>

<div class="text-center">
    <h2>404 Not Found</h2>
</div>
<div class="text-center">
    <p>Hello there traveller. You seem to have lost your way.</p>
    <button class="btn btn-primary" type="button" onclick="history.back()">Find Your Way Back</button>
</div>
</div>
</body>
</html>