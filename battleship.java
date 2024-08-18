import java.util.Scanner;

public class Battleship {
    private static final int BOARD_SIZE = 5;
    private static final int SHIPS_COUNT = 5;

    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");

        char[][] playerOneShipsBoard = createEmptyBoard();
        char[][] playerTwoShipsBoard = createEmptyBoard();
        char[][] playerOneTargetBoard = createEmptyBoard();
        char[][] playerTwoTargetBoard = createEmptyBoard();

        Scanner scanner = new Scanner(System.in);

        System.out.println("PLAYER 1, ENTER YOUR SHIPS’ COORDINATES.");
        enterShipsCoordinates(scanner, playerOneShipsBoard);
        clearConsole();

        System.out.println("PLAYER 2, ENTER YOUR SHIPS’ COORDINATES.");
        enterShipsCoordinates(scanner, playerTwoShipsBoard);
        clearConsole();

        boolean player1Turn = true;
        while (true) {
            if (playTurn(scanner, player1Turn, playerOneShipsBoard, playerOneTargetBoard, playerTwoShipsBoard, playerTwoTargetBoard)) {
                break;
            }
            player1Turn = !player1Turn;
        }

        displayFinalBoards(playerOneShipsBoard, playerOneTargetBoard, playerTwoShipsBoard, playerTwoTargetBoard);
    }

    private static boolean playTurn(Scanner scanner, boolean player1Turn, char[][] playerOneShipsBoard, char[][] playerOneTargetBoard, char[][] playerTwoShipsBoard, char[][] playerTwoTargetBoard) {
        char[][] currentPlayerShipsBoard;
        char[][] currentPlayerTargetBoard;
        char[][] opponentShipsBoard;
        String playerLabel;

        if (player1Turn) {
            currentPlayerShipsBoard = playerOneShipsBoard;
            currentPlayerTargetBoard = playerOneTargetBoard;
            opponentShipsBoard = playerTwoShipsBoard;
            playerLabel = "1";
        } else {
            currentPlayerShipsBoard = playerTwoShipsBoard;
            currentPlayerTargetBoard = playerTwoTargetBoard;
            opponentShipsBoard = playerOneShipsBoard;
            playerLabel = "2";
        }

        System.out.println("Player " + playerLabel + ", enter hit row/column:");
        int hitRow = getValidCoordinate(scanner, "row");
        int hitCol = getValidCoordinate(scanner, "column");

        if (currentPlayerTargetBoard[hitRow][hitCol] != '-') {
            System.out.println("You already fired on this spot. Choose different coordinates.");
            return false;
        }

        currentPlayerTargetBoard[hitRow][hitCol] = (opponentShipsBoard[hitRow][hitCol] == '@') ? 'X' : 'O';

        if (opponentShipsBoard[hitRow][hitCol] == '@') {
            System.out.println("PLAYER " + playerLabel + " HIT PLAYER " + (player1Turn ? "2" : "1") + "’s SHIP!");
            opponentShipsBoard[hitRow][hitCol] = 'X';

            if (hasPlayerWon(opponentShipsBoard)) {
                System.out.println("PLAYER " + playerLabel + " WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
                return true;
            }
        } else {
            System.out.println("PLAYER " + playerLabel + " MISSED!");
        }

        printBoard(currentPlayerTargetBoard);
        clearConsole();

        return false;
    }

    private static void enterShipsCoordinates(Scanner scanner, char[][] shipsBoard) {
        for (int i = 0; i < SHIPS_COUNT; i++) {
            int shipIndex = i + 1;
            System.out.println("Enter ship " + shipIndex + " location:");

            int row = getValidCoordinate(scanner, "row");
            int col = getValidCoordinate(scanner, "column");

            if (shipsBoard[row][col] == '@') {
                System.out.println("You already have a ship there. Choose different coordinates.");
                i--;
                continue;
            }

            shipsBoard[row][col] = '@';
        }
    }

    private static int getValidCoordinate(Scanner scanner, String coordinateType) {
        int coordinate;
        while (true) {
            System.out.print("Enter " + coordinateType + " (0-" + (BOARD_SIZE - 1) + "): ");
            if (scanner.hasNextInt()) {
                coordinate = scanner.nextInt();
                if (isValidCoordinate(coordinate)) {
                    break;
                }
            } else {
                scanner.next(); // Clear invalid input
            }
            System.out.println("Invalid input. Please enter a valid " + coordinateType + " between 0 and " + (BOARD_SIZE - 1) + ".");
        }
        return coordinate;
    }

    private static boolean isValidCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < BOARD_SIZE;
    }

    private static char[][] createEmptyBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '-';
            }
        }
        return board;
    }

    private static void printBoard(char[][] board) {
        System.out.println("  0 1 2 3 4"); // Column labels
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + " "); // Row label
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean hasPlayerWon(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == '@') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void clearConsole() {
        System.out.println("\n".repeat(50)); // Mimic clearing the console
    }

    private static void displayFinalBoards(char[][] playerOneShipsBoard, char[][] playerOneTargetBoard, char[][] playerTwoShipsBoard, char[][] playerTwoTargetBoard) {
        System.out.println("Final boards:\n");

        System.out.println("Player 1's ships board:");
        printBoard(playerOneShipsBoard);
        System.out.println("\nPlayer 1's target history board:");
        printBoard(playerOneTargetBoard);

        System.out.println("\nPlayer 2's ships board:");
        printBoard(playerTwoShipsBoard);
        System.out.println("\nPlayer 2's target history board:");
        printBoard(playerTwoTargetBoard);
    }
}
