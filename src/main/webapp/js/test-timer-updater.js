const timeLeft = document.getElementById("timeLeft");
let testTime = document.getElementById("timeLeft").innerHTML;

testTime++;

function updateTimer() {
    if (testTime != 0) {
        testTime--;
        updateTestTimeAtServer();
        const timeInMinutes = Math.floor(testTime / 60);
        const remainderSeconds = testTime % 60;
        timeLeft.innerHTML = timeInMinutes + ":" +
            ((remainderSeconds < 10) ? ("0" + remainderSeconds) : remainderSeconds);
    }

    timeLeft.style.display = "";
}

function updateTestTimeAtServer() {
    const testTimeRequest = new XMLHttpRequest();

    testTimeRequest.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
        }
    };

    testTimeRequest.open("POST", "updateTestTime", true);
    testTimeRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    testTimeRequest.send("newTestTime" + testTime);
}

function submitTestOnTimeUp() {
    const resultPage = document.getElementById("resultPage").innerHTML;

    if (confirm("Time up. You may now see your result")) {
        window.location.replace(resultPage);
    } else {
        window.location.replace(resultPage);
    }
}

function submitTest() {
    const resultPage = document.getElementById("resultPage").innerHTML;

    if (confirm("Are you sure you want to submit now?")) {
        window.location.replace(resultPage);
    }
}

setInterval(updateTimer, 1000);