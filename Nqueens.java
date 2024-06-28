import java.util.*;

public class Nqueens {
    public static boolean issafe(char board[][], int i, int j) {
        int r = i;
        int c = j;
        while (c >= 0) {
            if (board[r][c] == 'Q') {
                return false;
            }
            c--;
        }
        c = j;
        while (c >= 0 && r >= 0) {
            if (board[r][c] == 'Q') {
                return false;
            }
            c--;
            r--;
        }
        r = i;
        c = j;
        while (c >= 0 && r < board.length) {
            if (board[r][c] == 'Q') {
                return false;
            }
            c--;
            r++;
        }
        return true;
    }

    public static void solve(char board[][], int c) {
        if (c >= board.length) {
            print(board);
            System.out.println();
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (issafe(board, i, c)) {
                board[i][c] = 'Q';
                solve(board, c + 1);
                board[i][c] = '.';
            }
        }

    }

    public static void print(char board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char board[][] = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        solve(board, 0);
    }
}
