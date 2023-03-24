<%
    String pageTitle = "Login";
    String emailAddress = "";

    String emailAddressErrorMessage = "";
    String passwordErrorMessage = "";

    if (request.getAttribute("emailAddress") != null) {
        emailAddress = (String) request.getAttribute("emailAddress");
        emailAddressErrorMessage = (String) request.getAttribute("emailAddressErrorMessage");
    }

    if (request.getAttribute("passwordErrorMessage") != null) {
        passwordErrorMessage = (String) request.getAttribute("passwordErrorMessage");
    }
%>

<%@include file="header.jsp" %>

<%
    String loginConfirmationDialog = "";

    if (request.getAttribute("loginConfirmationDialog") != null) {
        loginConfirmationDialog = (String) request.getAttribute("loginConfirmationDialog");
    }
%>
<%=loginConfirmationDialog%>

<div class="text-center mb-5">
    <h1>Login</h1>
</div>
<div class="row">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="col-md-4 mb-5 mx-auto">
            <label class="form-label" for="emailAddress">Email Address<span class="text-danger">*</span></label>
            <input type="email" class="form-control" id="emailAddress" name="emailAddress" required
                   placeholder="Enter email address" value="<%=emailAddress%>"
                   oninput="hideErrorMessages()">
            <div class="text-danger" id="emailAddressErrorMessage"><%=emailAddressErrorMessage%>
            </div>
        </div>
        <div class="col-md-4 mb-5 mx-auto">
            <label class="form-label" for="password">Password<span class="text-danger">*</span></label>
            <input type="password" class="form-control" id="password" name="password" required
                   placeholder="Enter password" oninput="hideErrorMessages()">
            <div class="text-danger" id="passwordErrorMessage"><%=passwordErrorMessage%>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/login-validation.js"></script>

        <div class="col-md-4 mb-5 mx-auto">
            <button class="btn btn-primary px-3" type="submit" name="login">Login</button>
            <p class="mt-1">Not registered yet? <a href="${pageContext.request.contextPath}/signup">Signup instead</a></p>
        </div>
    </form>
</div>
</div>
</body>
</html>