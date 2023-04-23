const questions = document.getElementsByClassName("questions");
const previousQuestionButton = document.getElementById("previousQuestionButton");
const nextQuestionButton = document.getElementById("nextQuestionButton");

let currentQuestionNumber = 1;

displayQuestion(1);

function displayQuestion(questionNumber) {
    for (const currentQuestion of questions) {
        currentQuestion.style.display = "none";
    }

    questions[questionNumber - 1].style.display = "";
    currentQuestionNumber = questionNumber;

    if (currentQuestionNumber == questions.length) {
        previousQuestionButton.removeAttribute("disabled");
        nextQuestionButton.setAttribute("disabled", "");
    } else if (questionNumber == 1) {
        previousQuestionButton.setAttribute("disabled", "");
        nextQuestionButton.removeAttribute("disabled");
    } else {
        previousQuestionButton.removeAttribute("disabled");
        nextQuestionButton.removeAttribute("disabled");
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