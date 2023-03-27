<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ostech.cbt.model.Subject" %>

<%!
    String pageTitle = "Subject";
%>

<%@include file="header.jsp" %>

<%
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
    Subject subject = (Subject) request.getAttribute("subject");
    boolean isSpecificSubjectRequested = (boolean) request.getAttribute("isSpecificSubjectRequested");

    if (isSpecificSubjectRequested && !subject.isFound()) {
%>
<script>
    const subjectPage = "${pageContext.request.contextPath}/subject";
    window.location.replace(subjectPage);
</script>
<%
    }
%>
<div class="text-center">
    <h2><%=subject.getName()%></h2>
</div>
<div class="row justify-content-center">
</div>
<%
    }
%>

</div>
</body>
</html>