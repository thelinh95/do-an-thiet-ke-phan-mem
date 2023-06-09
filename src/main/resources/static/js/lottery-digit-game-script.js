document.addEventListener("DOMContentLoaded", function() {
    var numOfLetters = gameRandomDigit;

    var letterInputs = document.getElementById("letter-inputs");
    var submitButton = document.getElementById("submit-button");
    var resultMessage = document.getElementById("result-message");

    for (var i = 0; i < numOfLetters; i++) {
        var input = document.createElement("input");
        input.setAttribute("type", "text");
        input.setAttribute("maxlength", "1");
        input.setAttribute("placeholder", "_");
        input.setAttribute("pattern", "[0-9]+");

        input.addEventListener("input", function() {
            this.value = this.value.replace(/\D/g, "");
        });

        letterInputs.appendChild(input);
    }

    submitButton.addEventListener("click", function() {
        var guess = "";
        var inputs = document.querySelectorAll("#letter-inputs input");
        for (var i = 0; i < inputs.length; i++) {
            var letter = inputs[i].value.toUpperCase();
            guess += letter;
        }

        if (guess.length === numOfLetters) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", '/voucher/' + voucherId + '/redeem', true);
            xhr.setRequestHeader("Content-Type", "application/json");
            var csrfToken = $("input[name='_csrf']").val();
            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    console.log(response);

                    if (response.win === true) {
                        resultMessage.textContent = response.winMessage;
                    } else {
                        resultMessage.textContent = response.failedErrorMessage;
                    }
                    resultMessage.style.display = "block";
                }
            };

            var gamePlayDTO = {
                              gameType: gameType,
                              playedAt: new Date().getTime(),
                              playData: guess
                                 };
            xhr.send(JSON.stringify(gamePlayDTO));
        }
    });
});
