const questions = document.getElementsByClassName("questions");
let currentQuestionNumber = 1;

displayQuestion(1);

function displayQuestion(questionNumber) {
    for (const currentQuestion of questionNumber) {
        currentQuestion.style.display = "none";
    }

    questions[questionNumber - 1].style.display = "";
    currentQuestionNumber = questionNumber;
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