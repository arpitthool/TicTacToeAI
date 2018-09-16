package com.example.arpit.tictactoeai;

/**
 *refer https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/ for code explanation
 * @author arpit
 */


public class AI_Player {

    public class Move {

        int row, col;
    }

    private static final int Max = 1000;
    private static final int Min = -1000;
    int player = 1 , opponent = -1 ;
    boolean isMax = false ;


    private boolean isMoveLeft(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int evaluate(int[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] + board[i][1] + board[i][2] == 3) {
                return 10;
            } else if (board[i][0] + board[i][1] + board[i][2] == -3) {
                return -10;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == 3) {
                return 10;
            }
            if (board[0][i] + board[1][i] + board[2][i] == -3) {
                return -10;
            }
        }

        if (board[0][0] + board[1][1] + board[2][2] == 3) {
            return 10;
        }
        if (board[0][0] + board[1][1] + board[2][2] == -3) {
            return -10;
        }

        if (board[0][2] + board[1][1] + board[2][0] == 3) {
            return 10;
        }
        if (board[0][2] + board[1][1] + board[2][0] == -3) {
            return -10;
        }
        return 0;
    }
    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board

    private int minimax(int[][] board, int depth, boolean isMax) {


        int score = evaluate(board);

        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10) {
            return score - depth;
        }

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10) {
            return score + depth;
        }

        // If there are no more moves and no winner then
        // it is a tie
        if (isMoveLeft(board) == false) {
            return 0;
        }

        // If this maximizer's move
        if (isMax) {
            int best = Min;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board, depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        } // If this minimizer's move
        else {
            int best = Max;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board, depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        }
    }

    //minimax algorithm with alpha-beta pruning

    private int minimaxAlphaBeta( int[][] board, int depth, boolean isMax, int alpha, int beta){
        int score = evaluate(board);

        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10) {
            return score - depth;
        }

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10) {
            return score + depth;
        }

        // If there are no more moves and no winner then
        // it is a tie
        if (isMoveLeft(board) == false) {
            return 0;
        }

        // If this maximizer's move
        if (isMax) {
            int best = Min;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimaxAlphaBeta(board, depth + 1, !isMax, alpha, beta));

                        // Undo the move
                        board[i][j] = 0;

                        alpha = Math.max(alpha, best);
                        // Alpha Beta Pruning
                        if (beta <= alpha) {
                            System.out.println("Prunned");
                            break;
                        }
                    }
                }
            }
            return best;
        } // If this minimizer's move
        else {
            int best = Max;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimaxAlphaBeta(board, depth + 1, !isMax, alpha, beta));

                        // Undo the move
                        board[i][j] = 0;

                        beta = Math.min(beta, best);
                        // Alpha Beta Pruning
                        if (beta <= alpha) {
                            System.out.println("Prunned");
                            break;
                        }
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible move for the player
    public int[] findBestMove(int[][] board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == 0) {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    //int moveVal = minimax(board, 0, isMax);
                    int moveVal = minimaxAlphaBeta( board, 0,isMax, Min, Max);
                    // Undo the move
                    board[i][j] = 0;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.println("The value of the best Move is:" + bestVal);
        System.out.println("row = " + bestMove.row + " col = " + bestMove.col);

        int[] Best = {bestMove.row, bestMove.col};
        return Best;
    }
}
