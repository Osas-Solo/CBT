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
    Subject subject = (Subject) session.getAttribute("subject");

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
            ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
    %>
    <h2 class="text-center">
        <%=subject.getName()%>
    </h2>

    <div class="col-3 text-center float-end fixed-top mt-5 p-5">
        <%
            int testTime = (int) request.getAttribute("testTime");
        %>
        <h3>Time Left: <span style="display: none" id="timeLeft"><%=testTime%></span></h3>
    </div>
    <script src="js/test-timer-updater.js"></script>
    <div class="row mt-5">
        <form method="post" action="${pageContext.request.contextPath}/result">
            <%
                int questionNumber = 0;
                for (Question currentQuestion : questions) {
                    questionNumber++;
            %>
            <div class="questions col-12 mb-5" style="display: none">
                <div class="row">
                    <p class="col-3 text-end">
                        <%=questionNumber%>.
                    </p>
                    <label class="form-label col-9"
                           for="question<%=questionNumber%>"><%=currentQuestion.getQuestion()%>
                    </label>
                </div>
                <%
                    char optionLabel = 'A';
                    for (Entry<Character, String> currentOption : currentQuestion.getOptions().entrySet()) {
                %>
                <div class="row mb-3">
                    <div class="col-3 form-check text-end">
                        <label class="form-check-label">
                            <%=optionLabel%>.
                            <input type="radio" id="answer<%=questionNumber%>" name="answer<%=questionNumber%>"
                                   value="<%=currentOption.getKey()%>"
                                   oninput="updateAnswer(<%=questionNumber%>, '<%=currentOption.getKey()%>')">
                        </label>
                    </div>
                    <p class="col-9">
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

            <div class="mb-5 mx-auto text-center">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <button type="button" id="previousQuestionButton" class="page-link btn btn-primary me-3"
                                onclick="displayPreviousQuestion()">Previous
                        </button>
                    </li>
                    <%
                        for (int i = 1; i <= questions.size(); i++) {
                    %>
                    <li class="page-item">
                        <button type="button" class="page-link btn btn-primary me-3"
                                onclick="displayQuestion(<%=i%>)"><%=i%>
                        </button>
                    </li>
                    <%
                        }
                    %>
                    <li class="page-item">
                        <button type="button" id="nextQuestionButton" class="page-link btn btn-primary me-3" onclick="displayNextQuestion()">
                            Next
                        </button>
                    </li>
                </ul>
            </div>

            <span class="d-none" id="resultPage">${pageContext.request.contextPath}/result</span>

            <div class="col-md-4 mb-5 mx-auto text-center">
                <button id="submitButton" class="btn btn-primary px-3" type="button" onclick="submitTest()" disabled>Submit</button>
            </div>
            <script src="js/question-navigator.js"></script>
            <script src="js/answer-updater.js"></script>
            <script>
                setTimeout(submitTestOnTimeUp, <%=testTime + 1%> * 1000);
            </script>
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