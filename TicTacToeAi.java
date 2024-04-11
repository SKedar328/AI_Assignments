package AI_Assignments;

import java.util.Scanner;
public class TicTacToeAi {

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        while (true) {
            tictactoeAI();
            System.out.println("Press Enter to play again or type 'exit' to quit:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }

    private static void tictactoeAI() {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                board[i][j] = ' ';
            }
        }

        char currentPlayer = 'X';

        while (true) {
            printBoard(board);

            if (currentPlayer == 'X') {
                int move;
                Scanner scanner = new Scanner(System.in);
                System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
                move = scanner.nextInt();

                if (move == 0) {
                    System.out.println("Game Terminated!");
                    break;
                }
                if (move < 1 || move > 9 || board[(move - 1) / 3][(move - 1) % 3] != ' ') {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                int row = (move - 1) / 3;
                int col = (move - 1) % 3;

                board[row][col] = currentPlayer;
            } else {
                System.out.print("AI's Turn");
                System.out.println();
                makeAIMove(board);
            }

            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                if (currentPlayer == 'O') {
                    System.out.println("AI Wins!");
                } else {
                    System.out.println("Player " + currentPlayer + " wins!");
                }
                break;
            }
            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a tie!");
                break;
            }
 
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }

    private static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void convertMove(int move, int[] result) {
        move--;
        result[0] = move / 3;
        result[1] = move % 3;
    }

    private static int evaluate(char[][] board) {
        if (checkWin(board, 'X')) {
            return -1;
        } else if (checkWin(board, 'O')) {
            return 1;
        }

        if (isBoardFull(board)) {
            return 0;
        }

        return -2;
    }
    private static int minimax(char[][] board, int depth, boolean isMaximizing) {
        int score = evaluate(board);
        if (score != -2) {
            return score;
        }

        if (isMaximizing) {
            int maxEval = -1000;
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int eval = minimax(board, depth + 1, false);
                        board[i][j] = ' ';
                        maxEval = Math.max(maxEval, eval);
                    }
                    
                }
            }
            return maxEval;
        } else {
            int minEval = 1000;
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int eval = minimax(board, depth + 1, true);
                        board[i][j] = ' ';
                        minEval = Math.min(minEval, eval);
                    }
                }
            }
            return minEval;
        }
    }

    private static void makeAIMove(char[][] board) {
        int bestMove = -1;
        int bestScore = -1000;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int moveScore = minimax(board, 0, false);
                    board[i][j] = ' ';
                    if (moveScore > bestScore) {
                        bestScore = moveScore;
                        bestMove = i * 3 + j + 1;
                    }
                }
            }
        }
        int[] result = new int[2];
        convertMove(bestMove, result);
        board[result[0]][result[1]] = 'O';
    }
}

   