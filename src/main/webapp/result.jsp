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
    <%=subject.getName()%> Result
  </h2>

  <div class="row mt-5">
    <form>
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
                     value="<%=currentOption.getKey()%>" readonly>
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