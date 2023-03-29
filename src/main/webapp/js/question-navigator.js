const questions = document.getElementsByClassName("questions");
const submitButton = document.getElementById("submitButton");

let currentQuestionNumber = 1;

displayQuestion(1);

function displayQuestion(questionNumber) {
    for (const currentQuestion of questions) {
        currentQuestion.style.display = "none";
    }

    questions[questionNumber - 1].style.display = "";
    currentQuestionNumber = questionNumber;

    if (currentQuestionNumber == questions.length) {
        submitButton.removeAttribute("disabled");
    } else {
        submitButton.setAttribute("disabled", "")
    }
}

function displayPreviousQuestion() {
    if (currentQuestionNumber != 1) {
        displayQuestion(currentQuestionNumber - 1);
    }
}

function displayNextQuestion() {
    if (currentQuestionNumber != questions.length) {
        displayQuestion(currentQuestionNumber + 1);
    }
}