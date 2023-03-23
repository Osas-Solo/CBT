<%
    String pageTitle = "Signup";
    String firstName = "";
    String lastName = "";
    String emailAddress = "";

    String firstNameErrorMessage = "";
    String lastNameErrorMessage = "";
    String emailAddressErrorMessage = "";
    String passwordErrorMessage = "";
    String passwordConfirmationErrorMessage = "";

    if (request.getAttribute("firstName") != null) {
        firstName = (String) request.getAttribute("firstName");
        firstNameErrorMessage = (String) request.getAttribute("firstNameErrorMessage");
    }

    if (request.getAttribute("lastName") != null) {
        lastName = (String) request.getAttribute("lastName");
        lastNameErrorMessage = (String) request.getAttribute("lastNameErrorMessage");
    }

    if (request.getAttribute("emailAddress") != null) {
        emailAddress = (String) request.getAttribute("emailAddress");
        emailAddressErrorMessage = (String) request.getAttribute("emailAddressErrorMessage");
    }

    if (request.getAttribute("passwordErrorMessage") != null) {
        passwordErrorMessage = (String) request.getAttribute("passwordErrorMessage");
        passwordConfirmationErrorMessage = (String) request.getAttribute("passwordConfirmationErrorMessage");
    }
%>

<%@include file="header.jsp" %>

<div class="text-center mb-5">
    <h1>Signup</h1>
</div>
<div class="row">
    <form method="post" action="${pageContext.request.contextPath}/signup">
        <div class="col-md-4 mb-5 mx-auto">
            <label class="form-label" for="firstName">First Name<span class="text-danger">*</span></label>
            <input type="text" class="form-control" id="firstName" name="firstName" required
                   placeholder="Enter first name" value="<%=firstName%>" oninput="validateSignup()">
            <div class="text-danger" id="firstNameErrorMessage"><%=firstNameErrorMessage%></div>
        </div>
        <div class="col-md-4 mb-5 mx-auto">
            <label class="form-label" for="lastName">Last Name<span class="text-danger">*</span></label>
            <input type="text" class="form-control" id="lastName" name="lastName" required
                   placeholder="Enter last name" value="<%=lastName%>" oninput="validateSignup()">
            <div class="text-danger" id="lastNameErrorMessage"><%=lastNameErrorMessage%></div>
        </div>
        <div class="col-md-4 mb-5 mx-auto">
            <label class="form-label" for="emailAddress">Email Address<span class="text-danger">*</span></label>
            <input type="email" class="form-control" id="emailAddress" name="emailAddress" required
                   placeholder="Enter email address" value="<%=emailAddress%>" oninput="validateSignup()">
            <div class="text-danger" id="emailAddressErrorMessage"><%=emailAddressErrorMessage%></div>
        </div>
        <div class="col-md-4 mb-5 mx-auto">
            <label class="form-label" for="password">Password<span class="text-danger">*</span></label>
            <input type="password" class="form-control" id="password" name="password" required
                   placeholder="Enter password" oninput="validateSignup()">
            <div class="text-danger" id="passwordErrorMessage"><%=passwordErrorMessage%></div>
        </div>
        <div class="col-md-4 mb-5 mx-auto">
            <label class="form-label" for="passwordConfirmation">Confirm Password<span class="text-danger">*</span></label>
            <input type="password" class="form-control" id="passwordConfirmation" name="passwordConfirmation" required
                   placeholder="Confirm password" oninput="validateSignup()">
            <div class="text-danger" id="passwordConfirmationErrorMessage"><%=passwordConfirmationErrorMessage%></div>
        </div>

        <script src="${pageContext.request.contextPath}/js/signup-validation.js"></script>

        <div class="col-md-4 mb-5 mx-auto">
            <button class="btn btn-primary px-3" type="submit" name="signup">Signup</button>
            <p class="mt-1">Already registered? <a href="${pageContext.request.contextPath}/login">Login instead</a></p>
        </div>
    </form>
</div>
</div>
</body>
</html>