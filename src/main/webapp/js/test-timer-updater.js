const timeLeft = document.getElementById("timeLeft");
let testTime = document.getElementById("timeLeft").innerHTML;

testTime++;

function updateTimer() {
    if (testTime != 0) {
        testTime--;
        const timeInMinutes = Math.floor(testTime / 60);
        const remainderSeconds = testTime % 60;
        timeLeft.innerHTML = timeInMinutes + ":" +
            ((remainderSeconds < 10) ? ("0" + remainderSeconds) : remainderSeconds);
    }

    timeLeft.style.display = "";
}

setInterval(updateTimer, 1000);