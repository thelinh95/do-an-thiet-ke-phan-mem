var gameBoard = document.getElementById("game-board");
var resultMessage = document.getElementById("result-message");

// Mảng chứa các loại khối
var blockTypes = ["red", "blue"];

// Khối hiện tại đang xuất hiện trong game board
var currentBlock = null;

// Tọa độ hàng và cột của khối hiện tại
var currentRow = 0;
var currentCol = 0;

var NUM_ROWS = 10;
var NUM_COLS = 10;
var RANDOM_OCCUR_COLUMN = 10;

// Hàm tạo một khối mới và cho nó xuất hiện trong game board
function createBlock() {
  // Chọn một loại khối ngẫu nhiên
  var randomBlockType = blockTypes[Math.floor(Math.random() * blockTypes.length)];

  // Thiết lập khối hiện tại
  currentBlock = randomBlockType;
  currentRow = 0;
  currentCol = Math.floor(Math.random() * RANDOM_OCCUR_COLUMN); // Vị trí ngẫu nhiên trong khoảng từ 0 đến 6

  // Đặt giá trị màu sắc cho ô trong game matrix
  setBlockColor(currentRow, currentCol, currentBlock);
}



// Tạo ma trận game với NUM_ROWS hàng và NUM_COLS cột
var gameMatrix = [];
for (var i = 0; i < NUM_ROWS; i++) {
  var row = [];
  for (var j = 0; j < NUM_COLS; j++) {
    row.push(null);
  }
  gameMatrix.push(row);
}

// Vẽ ma trận game lên giao diện
function drawGame() {
  gameBoard.innerHTML = "";

  for (var i = 0; i < NUM_ROWS; i++) {
    for (var j = 0; j < NUM_COLS; j++) {
      var block = document.createElement("div");
      block.classList.add("block");

      if (gameMatrix[i][j] === "red") {
        block.classList.add("red");
      } else if (gameMatrix[i][j] === "blue") {
        block.classList.add("blue");
      }

      // Kiểm tra vị trí của ô vuông trong ma trận game
      // và thêm lớp "row-divider" và "column-divider" tương ứng
      if (i !== NUM_ROWS-1) {
        block.classList.add("row-divider");
      }
      if (j !== NUM_COLS-1) {
        block.classList.add("column-divider");
      }

      gameBoard.appendChild(block);
    }
  }
}


// Đặt giá trị màu sắc cho ô trong ma trận game
function setBlockColor(row, col, color) {
  gameMatrix[row][col] = color;
}

// Kiểm tra xem một khối có gây va chạm hay không
function isCollision(row, col, blockType) {
  // Kiểm tra va chạm với khối đã xếp và viền màn hình
  if (
    row < 0 ||
    row >= NUM_ROWS ||
    col < 0 ||
    col >= NUM_COLS ||
    gameMatrix[row][col] !== null
  ) {
    return true;
  }

  return false;
}

// Di chuyển khối vuông sang trái
function moveLeft() {
  var canMove = true;
  for (var i = 0; i < NUM_ROWS; i++) {
    for (var j = 0; j < NUM_COLS; j++) {
      if (gameMatrix[i][j] === "red") {
        if (isCollision(i, j - 1, "red")) {
          canMove = false;
          break;
        }
      } else if (gameMatrix[i][j] === "blue") {
        if (isCollision(i, j - 1, "blue")) {
          canMove = false;
          break;
        }
      }
    }
    if (!canMove) {
      break;
    }
  }

  if (canMove) {
    for (var i = 0; i < NUM_ROWS; i++) {
      for (var j = 0; j < NUM_COLS; j++) {
        if (gameMatrix[i][j] === "red") {
          gameMatrix[i][j - 1] = "red";
          gameMatrix[i][j] = null;
        } else if (gameMatrix[i][j] === "blue") {
          gameMatrix[i][j - 1] = "blue";
          gameMatrix[i][j] = null;
        }
      }
    }
    drawGame();
  }
}


// Di chuyển khối vuông sang phải
function moveRight() {
  var canMove = true;
  for (var i = 0; i < NUM_ROWS; i++) {
    for (var j = NUM_COLS-1; j >= 0; j--) {
      if (gameMatrix[i][j] === "red") {
        if (isCollision(i, j + 1, "red")) {
          canMove = false;
          break;
        }
      } else if (gameMatrix[i][j] === "blue") {
        if (isCollision(i, j + 1, "blue")) {
          canMove = false;
          break;
        }
      }
    }
    if (!canMove) {
      break;
    }
  }

  if (canMove) {
    for (var i = 0; i < NUM_ROWS; i++) {
      for (var j = NUM_COLS-1; j >= 0; j--) {
        if (gameMatrix[i][j] === "red") {
          gameMatrix[i][j + 1] = "red";
          gameMatrix[i][j] = null;
        } else if (gameMatrix[i][j] === "blue") {
          gameMatrix[i][j + 1] = "blue";
          gameMatrix[i][j] = null;
        }
      }
    }
    drawGame();
  }
}

// Di chuyển khối vuông xuống dưới
function moveDown() {
  var canMove = true;
  for (var i = NUM_ROWS-1; i >= 0; i--) {
    for (var j = 0; j < NUM_COLS; j++) {
      if (gameMatrix[i][j] === "red") {
        if (isCollision(i + 1, j, "red")) {
          canMove = false;
          break;
        }
      } else if (gameMatrix[i][j] === "blue") {
        if (isCollision(i + 1, j, "blue")) {
          canMove = false;
          break;
        }
      }
    }
    if (!canMove) {
      break;
    }
  }

  if (canMove) {
    for (var i = NUM_ROWS-1; i >= 0; i--) {
      for (var j = 0; j < NUM_COLS; j++) {
        if (gameMatrix[i][j] === "red") {
          gameMatrix[i + 1][j] = "red";
          gameMatrix[i][j] = null;
        } else if (gameMatrix[i][j] === "blue") {
          gameMatrix[i + 1][j] = "blue";
          gameMatrix[i][j] = null;
        }
      }
    }
    drawGame();
  }
}

// Hàm kiểm tra xem khối đã đạt đến đáy chưa
function isBlockAtBottom() {
  for (var i = NUM_ROWS-1; i >= 0; i--) {
    for (var j = 0; j < NUM_COLS; j++) {
      if (
        gameMatrix[i][j] === "red" ||
        gameMatrix[i][j] === "blue"
      ) {
        if (i === NUM_ROWS-1 || gameMatrix[i + 1][j] !== null) {
          return true;
        }
      }
    }
  }
  return false;
}

function handleBlockAtBottom() {
  // Kiểm tra xem có hàng nào đã đầy không
  for (var i = NUM_ROWS - 1; i >= 0; i--) {
    var isFullRow = true;
    for (var j = 0; j < NUM_COLS; j++) {
      if (gameMatrix[i][j] === null) {
        isFullRow = false;
        break;
      }
    }

    if (isFullRow) {
      // Xóa hàng đầy
      for (var j = 0; j < NUM_COLS; j++) {
        gameMatrix[i][j] = null;
      }

      // Dịch chuyển các hàng phía trên xuống
      for (var k = i - 1; k >= 0; k--) {
        for (var j = 0; j < NUM_COLS; j++) {
          gameMatrix[k + 1][j] = gameMatrix[k][j];
          gameMatrix[k][j] = null;
        }
      }

      // Tăng điểm khi xóa hàng
      var score = parseInt(resultMessage.textContent);
      score += 100;
      resultMessage.textContent = score;
    }
  }

  drawGame();
}



  // Hàm bắt đầu trò chơi
  function startGame() {
    createBlock(); // Tạo một khối mới
    drawGame(); // Vẽ game board

    setInterval(function () {
      if (!isBlockAtBottom()) {
        moveDown();
      } else {
        handleBlockAtBottom();
        createBlock(); // Tạo một khối mới khi khối hiện tại đạt đến đáy
      }
    }, 1000);

    var gameBoard = document.getElementById("game-board");
    gameBoard.setAttribute("tabindex", "0");
    // Lắng nghe sự kiện nhấn phím để di chuyển khối vuông
    gameBoard.addEventListener("keydown", function (event) {
      if (event.code === "ArrowLeft") {
        moveLeft();
      } else if (event.code === "ArrowRight") {
        moveRight();
      }
    });
  }

  // Gọi hàm bắt đầu trò chơi khi trang web được tải
startGame();
