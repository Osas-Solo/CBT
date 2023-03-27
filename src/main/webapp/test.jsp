<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ostech.cbt.model.Subject" %>
<%@ page import="com.ostech.cbt.model.Question" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>

<%!
    String pageTitle = "Test";
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

    if (!subject.isFound()) {
%>
<script>
    const subjectPage = "${pageContext.request.contextPath}/subject";
    window.location.replace(subjectPage);
</script>
<%
    }
%>
<div>
    <%
        if (subject.isFound()) {
            ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions");
    %>
    <h2 class="text-center"><%=subject.getName()%>
    </h2>
    <div class="row mt-5">
        <form method="post" action="${pageContext.request.contextPath}/result">
            <%
                int questionCount = 0;
                for (Question currentQuestion : questions) {
                    questionCount++;
            %>
            <div class="col-12 mb-5">
                <div class="row">
                    <p class="col-1">
                        <%=questionCount%>.
                    </p>
                    <label class="form-label col-11"
                           for="question<%=questionCount%>"><%=currentQuestion.getQuestion()%>
                    </label>
                </div>
            </div>
            <%
                char optionLabel = 'A';
                for (Entry<Character, String> currentOption : currentQuestion.getOptions().entrySet()) {
            %>
            <div class="row mb-3">
                <p class="col-1">
                    <%=optionLabel%>.
                </p>
                <div class="col-11 form-check">
                    <label class="form-check-label">
                        <input type="radio" id="answer<%=questionCount%>" name="answer<%=questionCount%>"
                               value="<%=currentOption.getValue()%>">
                        <%=currentOption.getValue()%>
                    </label>
                </div>
            </div>
            <%
                    optionLabel++;
                }
            %>
            <%
                }
            %>

            <div class="col-md-4 mb-5 mx-auto">
                <button class="btn btn-primary px-3" type="submit" name="submit">Submit</button>
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