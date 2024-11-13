import java.util.Scanner;

public class Main {
    final static int ROWS = 3;
    final static int COLUMNS = 3;
    private static String board[][];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            board = new String[ROWS][COLUMNS];
            clearBoard();
            showBoard();
            boolean done = false;

            while (!done) {
                System.out.println("Getting move from " + currentPlayer);
                int row, column;
                do {
                    System.out.println("Enter your row (1-3): ");
                    row = in.nextInt() - 1;
                    System.out.println("Enter your column (1-3): ");
                    column = in.nextInt() - 1;
                } while (!isValidMove(row, column));
                recordMove(currentPlayer, row, column);
                showBoard();
                if (isWin(currentPlayer)) {
                    System.out.println(currentPlayer + " wins!");
                    done = true;
                }
                else {
                    togglePlayer();
                }
            }

            playAgain = askPlayAgain(in);
        }
    }

    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static void showBoard() {
        for (int r = 0; r < ROWS; r++) {
            System.out.print("| ");
            for (int c = 0; c < COLUMNS; c++) {
                System.out.print(board[r][c] + " | ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int r, int c) {
        if (r < 0 || r >= ROWS || c < 0 || c >= COLUMNS) {
            System.out.println("Move out of range.");
            return false;
        }
        if (!board[r][c].equals(" ")) {
            System.out.println("Cell already occupied.");
            return false;
        }
        return true;
    }

    private static void recordMove(String player, int r, int c) {
        board[r][c] = player;
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static void togglePlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    private static boolean askPlayAgain(Scanner in) {
        System.out.println("Do you want to play again? (yes/no)");
        String response = in.next();
        return response.equalsIgnoreCase("yes");
    }

}
