function validateSignup() {
    validateFirstName();
    validateLastName();
    validateEmailAddress();
    validatePassword();
    confirmPassword();
}

function validateFirstName() {
    const firstName = document.getElementById("firstName").value;
    const firstNameError = document.getElementById("firstNameErrorMessage");

    const isFirstNameValid = /^[A-Z][a-z]+$/.test(firstName) && firstName.length > 0 && firstName.length <= 50;

    if (isFirstNameValid) {
        firstNameError.innerText = "";
    } else {
        firstNameError.innerText = "Please, first name should be an uppercase letter followed by lowercase letters and " +
            "can only have a maximum of 100 letters";
    }
}

function validateLastName() {
    const lastName = document.getElementById("lastName").value;
    const lastNameError = document.getElementById("lastNameErrorMessage");

    const isLastNameValid = /^[A-Z][a-z]+$/.test(lastName) && lastName.length > 0 && lastName.length <= 50;

    if (isLastNameValid) {
        lastNameError.innerText = "";
    } else {
        lastNameError.innerText = "Please, last name should be an uppercase letter followed by lowercase letters and " +
            "can only have a maximum of 100 letters";
    }
}

function validateEmailAddress() {
    const emailAddress = document.getElementById("emailAddress").value;
    const emailAddressError = document.getElementById("emailAddressErrorMessage");

    const isEmailAddressValid = /^[A-Za-z0-9+_.-]+@(.+\..+)$/.test(emailAddress);

    if (isEmailAddressValid) {
        emailAddressError.innerText = "";
    } else {
        emailAddressError.innerText = "Please, enter a valid email address";
    }
}

function validatePassword() {
    const password = document.getElementById("password").value;
    const passwordError = document.getElementById("passwordErrorMessage");

    const isPasswordValid = /[a-z]/.test(password) && /[A-Z]/.test(password) &&
        /[0-9]/.test(password)  && password.length >= 8 && password.length <= 20;

    if (isPasswordValid) {
        passwordError.innerText = "";
    } else {
        passwordError.innerText = "Please, enter a password with at least one uppercase, lowercase and a digit. " +
            "Passwords must contain at least 8 characters and cannot exceed 20 characters";
    }
}

function confirmPassword() {
    const password = document.getElementById("password").value;
    const passwordConfirmation = document.getElementById("passwordConfirmation").value;
    const passwordConfirmationError = document.getElementById("passwordConfirmationErrorMessage");

    const isPasswordConfirmed = password === passwordConfirmation;

    if (isPasswordConfirmed) {
        passwordConfirmationError.innerText = "";
    } else {
        passwordConfirmationError.innerText = "Sorry, passwords do not match";
    }
}