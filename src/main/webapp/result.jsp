<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ostech.cbt.model.Subject" %>
<%@ page import="com.ostech.cbt.model.Question" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="com.ostech.cbt.model.Answer" %>

<%!
    String pageTitle = "Result";
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
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
            int numberOfQuestions = (int) request.getAttribute("numberOfQuestions");
            int numberOfCorrectAnswers = (int) request.getAttribute("numberOfCorrectAnswers");
    %>
    <h2 class="text-center">
        <%=candidate.getFullName()%>'s <%=subject.getName()%> Result
    </h2>

    <div class="col-3 text-center fixed-top mt-5 p-5">
        <h3>Test Score: <%=numberOfCorrectAnswers%>/<%=numberOfQuestions%>
        </h3>
    </div>

    <div class="row mt-5">
        <form>
            <%
                for (int i = 0; i < questions.size(); i++) {
            %>
            <div class="questions col-12 mb-5">
                <div class="row">
                    <p class="col-3 text-end">
                        <%=i + 1%>.
                    </p>
                    <label class="form-label col-9"
                           for="question<%=i%>">
                        <%=questions.get(i).getQuestion()%>
                    </label>
                </div>
                <%
                    char optionLabel = 'A';
                    for (Entry<Character, String> currentOption : questions.get(i).getOptions().entrySet()) {
                %>
                <div class="row mb-3">
                    <div class="col-3 form-check text-end">
                        <label class="form-check-label">
                            <%=optionLabel%>.
                            <input type="radio" id="answer<%=i%>" name="answer<%=i%>"
                                   value="<%=currentOption.getKey()%>" disabled
                                <%
                                  if (currentOption.getKey() == questions.get(i).getSelectedOption()) {
                                %>
                                   checked
                                <%
                                }
                                %>>
                        </label>
                    </div>
                    <p class="col-9">
                        <%=currentOption.getValue()%>
                        <%
                            if (currentOption.getKey() == questions.get(i).getSelectedOption() &&
                                    answers.get(i).getCorrectOption() != questions.get(i).getSelectedOption()) {
                        %>
                        <span class="text-danger fw-bolder">&#x2715;</span>
                        <%
                        } else if (currentOption.getKey() == answers.get(i).getCorrectOption()) {
                        %>
                        <span class="text-success fw-bolder">&#x2714;</span>
                        <%
                            }
                        %>
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

<%
session.removeAttribute("subject");
session.removeAttribute("questions");
session.removeAttribute("testTime");
%>