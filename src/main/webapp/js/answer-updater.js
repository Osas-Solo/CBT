function updateAnswer(questionNumber, selectedOption) {
    const questionsRequest = new XMLHttpRequest();

    questionsRequest.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
        }
    };

    questionsRequest.open("POST", "updateAnswer", true);
    questionsRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    questionsRequest.send("questionNumber=" + questionNumber + "&selectedOption=" + selectedOption);
}