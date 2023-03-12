<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ostech.cbt.model.Subject" %>

<%!
String pageTitle = "Home";
%>

<%@include file="header.jsp"%>

    <div>
        <h2>Select a subject below to begin a text</h2>
    </div>
    <div class="col-12 d-flex">
    <%
        ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");

        for (Subject currentSubject : subjects) {
    %>
        <article class="m-5">
            <h3>
                <a href="${pageContext.request.contextPath}/subject/<%=currentSubject.getName().toLowerCase()%>">
                    <%=currentSubject.getName()%>
                </a>
            </h3>
        </article>
    <%
        }
    %>
    </div>
</div>
</body>
</html>