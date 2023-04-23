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
    <%
        if (subject.isFound()) {
            int maximumNumberOfQuestions = (int) request.getAttribute("maximumNumberOfQuestions");
    %>
    <h2 class="bg-white bg-opacity-75 shadow"><%=subject.getName()%>
    </h2>
    <div class="row text-center mt-5">
        <form method="post" action="${pageContext.request.contextPath}/test" class="bg-white col-md-6 col-auto mx-auto
            my-5 p-5 shadow">
            <div class="col-12 mb-5 mx-auto">
                <input type="text" class="form-control d-none" id="subjectID" name="subjectID" required
                       value="<%=subject.getId()%>">

                <label class="form-label" for="numberOfQuestions">Number of Questions<span class="text-danger">*</span></label>
                <input type="number" class="form-control" id="numberOfQuestions" name="numberOfQuestions" required
                       placeholder="Enter number of questions" min="2" max="<%=maximumNumberOfQuestions%>">
            </div>

            <div class="col-12 mb-5 mx-auto">
                <button class="btn btn-primary px-3" type="submit" name="startTest">Start Test</button>
            </div>
        </form>
    </div>
    <%
    } else {
        ArrayList<Subject> availableSubjects = (ArrayList<Subject>) request.getAttribute("availableSubjects");
    %>
    <div class="row text-center mt-5">
        <form method="get" action="${pageContext.request.contextPath}/subject" class="bg-white col-md-6 col-auto mx-auto
        my-5 p-5 shadow">
            <div class="col-12 mb-5 mx-auto">
                <label class="form-label" for="name">Subject<span class="text-danger">*</span></label>
                <select class="form-select" id="name" name="name" required>
                    <option value=""></option>
                    <%
                        for (Subject currentSubject : availableSubjects) {
                    %>
                    <option value="<%=currentSubject.getName().toLowerCase()%>">
                        <%=currentSubject.getName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="col-12 mb-5 mx-auto">
                <button class="btn btn-primary px-3" type="submit">Proceed</button>
            </div>
        </form>
    </div>
    <%
        }
    %>
</div>
<%
    }
%>

</div>
</body>
</html>