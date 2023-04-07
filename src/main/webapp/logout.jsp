<%!
    String pageTitle = "Logout";
%>

<%@include file="header.jsp" %>

<%
    if (request.getAttribute("logoutMessage") != null) {
        String logoutMessage = (String) request.getAttribute("logoutMessage");
%>
<script>
    const loginPage = "${pageContext.request.contextPath}/login";

    if (confirm("<%=logoutMessage%>")) {
        window.location.replace(loginPage);
    } else {
        window.location.replace(loginPage);
    }
</script>
<%
    }

    if (!candidate.isFound()) {
%>
<script>
    const loginPage = "${pageContext.request.contextPath}/login";

    if (confirm("Sorry, you would have to login to proceed")) {
        window.location.replace(loginPage);
    } else {
        window.location.replace(loginPage);
    }
</script>
<%
} else {
%>
<form id="logoutForm" class="d-none" method="post" action="${pageContext.request.contextPath}/logout">
</form>

<script>
    const logoutForm = document.getElementById("logoutForm");
    const previousPage = document.referrer;
    console.log("Previous page: " + previousPage);

    if (confirm("Are you sure you want to logout?")) {
        logoutForm.requestSubmit();
    } else {
        window.location.replace(previousPage);
    }
</script>

<%
    }
%>

</div>
</body>
</html>