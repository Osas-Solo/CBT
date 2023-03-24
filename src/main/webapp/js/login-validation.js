function hideErrorMessages() {
    hideEmailAddressErrorMessage();
    hidePasswordErrorMessage();
}

function hideEmailAddressErrorMessage() {
    const emailAddressError = document.getElementById("emailAddressErrorMessage");
    emailAddressError.style.display = "none";
}

function hidePasswordErrorMessage() {
    const passwordError = document.getElementById("passwordErrorMessage");
    passwordError.style.display = "none";
}