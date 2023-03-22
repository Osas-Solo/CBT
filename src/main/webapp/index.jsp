<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ostech.cbt.model.Subject" %>

<%!
    String pageTitle = "Home";
%>

<%@include file="header.jsp" %>

<div class="text-center">
    <h2>Select a subject below to begin a test</h2>
</div>
<div class="row justify-content-center">
    <%
        ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");

        if (subjects != null) {
            for (Subject currentSubject : subjects) {
    %>
    <article class="col-md-4 my-5 p-5 shadow text-center">
        <h3>
            <%=currentSubject.getName()%>
        </h3>
        <p class="btn btn-primary mt-3">
            <a href="${pageContext.request.contextPath}/test/<%=currentSubject.getName().toLowerCase()%>">
                Take Test
            </a>
        </p>
    </article>
    <%
            }
        }
    %>
</div>
</div>
</body>
</html>