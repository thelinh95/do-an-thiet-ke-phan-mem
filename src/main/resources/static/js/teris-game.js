var gameBoard = document.getElementById("game-board");

// Tạo ma trận game với 20 hàng và 10 cột
var gameMatrix = [];
for (var i = 0; i < 20; i++) {
  var row = [];
  for (var j = 0; j < 10; j++) {
    row.push(null);
  }
  gameMatrix.push(row);
}

// Vẽ ma trận game lên giao diện
function drawGame() {
  gameBoard.innerHTML = "";

  for (var i = 0; i < 20; i++) {
    for (var j = 0; j < 10; j++) {
      var block = document.createElement("div");
      block.classList.add("block");

      if (gameMatrix[i][j] === "red") {
        block.classList.add("red");
      } else if (gameMatrix[i][j] === "blue") {
        block.classList.add("blue");
      }

      gameBoard.appendChild(block);
    }
  }
}

// Đặt giá trị màu sắc cho ô trong ma trận game
function setBlockColor(row, col, color) {
  gameMatrix[row][col] = color;
}

// Hàm tạo một khối vuông đỏ 2x2
function createRedBlock() {
  setBlockColor(0, 0, "red");
  setBlockColor(0, 1, "red");
  setBlockColor(1, 0, "red");
  setBlockColor(1, 1, "red");
}

// Hàm tạo một khối vuông xanh 3x3
function createBlueBlock() {
  setBlockColor(0, 0, "blue");
  setBlockColor(0, 1, "blue");
  setBlockColor(0, 2, "blue");
  setBlockColor(1, 0, "blue");
  setBlockColor(1, 1, "blue");
  setBlockColor(1, 2, "blue");
  setBlockColor(2, 0, "blue");
  setBlockColor(2, 1, "blue");
  setBlockColor(2, 2, "blue");
}


var score = 0; // Biến lưu trữ điểm số của người chơi

// Hàm tính điểm khi xóa hàng
function calculateScore(rowsCleared) {
  // Cập nhật điểm số dựa trên số hàng đã xóa
  score += rowsCleared * 100;
}

// Hàm tính điểm khi xếp khối
function calculateScoreForBlock(blockType) {
  // Cập nhật điểm số dựa trên loại khối đã xếp
  if (blockType === "red") {
    score += 50;
  } else if (blockType === "blue") {
    score += 75;
  }
}

// Hàm tính điểm khi xếp khối đặc biệt
function calculateSpecialScore() {
  // Cập nhật điểm số cho việc xếp khối đặc biệt
  score += 200;
}

// Hàm tính điểm cho thời gian chơi
function calculateScoreForTime(timePlayed) {
  // Cập nhật điểm số dựa trên thời gian chơi
  // Ví dụ: Cứ mỗi 1 phút chơi, người chơi nhận được 10 điểm
  var minutesPlayed = Math.floor(timePlayed / 60000);
  var scoreForTime = minutesPlayed * 10;
  score += scoreForTime;
}

// Hàm tính điểm
function calculateScore() {
  // Code tính điểm của trò chơi Teris

  // In điểm số ra console hoặc hiển thị trên giao diện
  console.log("Điểm số: " + score);

  submitScore(score);

}

// Hàm gửi kết quả lên server qua Ajax
function submitScore(score) {
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
                    playData: score
                       };
  xhr.send(JSON.stringify(gamePlayDTO));
}


// Hàm kết thúc trò chơi
function endGame() {
  // Dừng trò chơi hoặc hiển thị thông báo kết thúc

  // Tính điểm
  calculateScore();
}

// Hàm khởi động trò chơi
function startGame() {
  // Code khởi tạo và bắt đầu trò chơi Teris

  // Đặt thời gian đếm ngược là 5 phút (300,000ms)
  var duration = 300000;

  // Thiết lập thời gian đếm ngược
  setTimeout(function() {
    // Kết thúc trò chơi sau khi hết thời gian
    endGame();
  }, duration);
}

// Khởi tạo game với các khối vuông
function initializeGame() {
  createRedBlock();
  createBlueBlock();
  drawGame();
  startGame();
  calculateScore();
}

window.addEventListener("load", initializeGame);