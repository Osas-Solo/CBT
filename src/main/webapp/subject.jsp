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
            my-5 p-5 shadow" id="numberOfQuestionsForm">
            <div class="col-12 mb-1 mx-auto">
                <input type="text" class="form-control d-none" id="subjectID" name="subjectID" required
                       value="<%=subject.getId()%>">

                <label class="form-label" for="numberOfQuestions">Number of Questions<span class="text-danger">*</span></label>
                <input type="number" class="form-control" id="numberOfQuestions" name="numberOfQuestions" required
                       placeholder="Enter number of questions" min="2" max="<%=maximumNumberOfQuestions%>"
                       oninput="updateTestTimeInstruction()">
            </div>
        </form>
    </div>

    <div class="bg-white col-md-9 col-auto mx-auto mb-5 p-5 shadow">
        <div class="row">
            <div class="col-md-4 col-12">
                <h3>Instructions</h3>
                <ul class="list-group">
                    <li class="list-group-item" id="testTimeInstruction" style="display: none">
                        You have <span id="testTime"></span> to complete the test
                    </li>
                    <li class="list-group-item">You can only select one option for every question</li>
                    <li class="list-group-item">Use the navigation buttons to go back and forth between questions</li>
                    <li class="list-group-item">
                        You can always skip any question you aren't sure of. You may return back to that questions
                        as long as your test has not timed out yet</li>
                </ul>
            </div>

            <img src="img/exam-girl.jpg" class="col-md-8 col-12">

            <div class="col-12 my-5 mx-auto">
                <button class="btn btn-primary px-3" type="submit" id="startTestButton" name="startTest"
                        onclick="displayQuestionNumberErrorAlert()" form="numberOfQuestionsForm">
                    Start Test
                </button>
            </div>
        </div>
    </div>

    <script>
        const testTimeInstruction = document.getElementById("testTimeInstruction");
        const testTime = document.getElementById("testTime");
        const numberOfQuestionsInput = document.getElementById("numberOfQuestions");

        function updateTestTimeInstruction() {
            if (numberOfQuestionsInput.value > 0 && numberOfQuestionsInput.value <= <%=maximumNumberOfQuestions%>) {
                testTimeInstruction.style.display = "block";
                const time = numberOfQuestionsInput.value * 60 * 1.5;
                const timeInMinutes = Math.floor(time / 60);
                const remainderSeconds = time % 60;
                testTime.innerHTML = timeInMinutes + " minutes" +
                    ((remainderSeconds > 0) ? " and " + remainderSeconds + " seconds" : "");
            } else {
                testTimeInstruction.style.display = "none";
            }
        }

        function displayQuestionNumberErrorAlert() {
            const numberOfQuestions = numberOfQuestionsInput.value;

            if (numberOfQuestions < 2 || numberOfQuestions > <%=maximumNumberOfQuestions%>) {
                alert("Sorry, you can only set the number of questions to a range of 2 to <%=maximumNumberOfQuestions%>");
            }
        }
    </script>

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