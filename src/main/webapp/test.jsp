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
    <h2 class="text-center">
        <%=subject.getName()%>
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
                    <p class="col-1 text-end">
                        <%=questionCount%>.
                    </p>
                    <label class="form-label col-11"
                           for="question<%=questionCount%>"><%=currentQuestion.getQuestion()%>
                    </label>
                </div>
                <%
                    char optionLabel = 'A';
                    for (Entry<Character, String> currentOption : currentQuestion.getOptions().entrySet()) {
                %>
                <div class="row mb-3">
                    <div class="col-1 form-check text-end">
                        <label class="form-check-label">
                            <%=optionLabel%>.
                            <input type="radio" id="answer<%=questionCount%>" name="answer<%=questionCount%>"
                                   value="<%=currentOption.getKey()%>">
                        </label>
                    </div>
                    <p class="col-11">
                        <%=currentOption.getValue()%>
                    </p>
                </div>
                <%
                        optionLabel++;
                    }
                %>
            </div>
            <%
                }
            %>

            <div class="col-md-4 mb-5 mx-auto text-center">
                <button class="btn btn-primary px-3" type="button" id="submitButton">Submit</button>
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