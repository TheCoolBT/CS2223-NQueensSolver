import java.util.ArrayList;

public class Methods {

    public int[][] fillBoard(int[] positions, int n) {
        int[][] board = new int[n][n];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j == positions[i - 1]) {
                    board[i - 1][j - 1] = 1;
                } else {
                    board[i - 1][j - 1] = 0;
                }
            }
        }
        return board;
    }

    public int[] boardToPos(int[][] board,int n) {
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]==1) {
                    pos[i] = j + 1;
                }
            }
        }
        return pos;
    }


    public void printBoard(int[][] board) {
        int N = board.length;
        for (int[] ints : board) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + ints[j]
                        + " ");
            System.out.println();
        }
    }

    public boolean isLegal(int[][] board, int row, int col) {
        int n = board.length;

        int i, j;
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        for (i = row, j = col; j >= 0 && i < n; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean sameCol(int[] pos) {
        for (int i = 0; i < pos.length; i++)
        {
            for (int j = i + 1; j < pos.length; j++)
            {
                if (pos[i]== pos[j] && pos[i] != 0 && pos[i] != 9) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLegalPosition(int[] pos, int n) {
        int[][] board = fillBoard(pos, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]== 1) {
                    board[i][j] = 0;
                    if (!isLegal(board,i, j)) {
                        return false;
                    }
                    board[i][j] =1;
                }
            }
        }
        return !sameCol(pos);
    }



    public boolean comparePos (int[] p1, int[] p2, int n) {
        ArrayList<Integer> c1 = new ArrayList<>();
        ArrayList<Integer> c2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (p1[i] < n+1 && p1[i] > 0) {
                c1.add(p1[i]);
            }

        }
        for (int j = 0; j < c1.size(); j++ ) {
            c2.add(p2[j]);
        }
        return c1.equals(c2);
    }

    public int[] successor(int[] pos, int n) {
        if (isLegalPosition(pos, n)) {
            for (int i = 0; i < n; i++) {
                if (pos[i] > n) {
                    pos[i-1]++;
                    break;
                }
                if (pos[i] == 0 || i + 1 == n) {
                    pos[i]++;
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                if (pos[i] == 0 || pos[i] > n) {
                    pos[i-1]++;
                    break;
                }
                if (i+1 == n) {
                    pos[i]++;
                    break;
                }
            }
        }
        return pos;
    }

    public int[] nextLegalPosition (int[] pos, int n) {
        int[] temp = new int[n];
        System.arraycopy(pos, 0, temp, 0, n);
        while(true) {
            if (!comparePos(temp, pos, n) && isLegalPosition(temp,n) ) {
                break;
            }
            else {
                temp = successor(temp, n);
            }
        }
        for (int i = 0; i < n; i++) {
            if (temp[i] == 9) {
                temp[i] = 0;
            }
        }
        return temp;
    }

    public boolean isFull (int[] pos,int n) {
        for (int i : pos) {
            if (i == 0 || i > n) {
                return false;
            }
        }
        return true;
    }

    public int[] firstLegal(int n ) {
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = 0;
        }

        while (!isFull(pos, n)) {
            nextLegalPosition(pos, n);
        }

        return pos;
    }



    public boolean nextLegal(int[][] board ,int col) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            if (isLegal(board, i, col)) {
                board[i][col] = 1;

                if (nextLegal(board, col + 1)) {
                    printBoard(board);
                    return true;
                }
                board[i][col] = 0;
            }
        }
        printBoard(board);
        return false;
    }


}

