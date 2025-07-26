import java.util.*;

import Exceptions.InvalidPositionException;

public class Engine {

    private Board boardT;
    private Boolean playerX;
    private String state;

    public Engine(Boolean playerX) {
        this.boardT = new Board();
        // try {
        //     boardT.move(1, true);
        //     boardT.move(2, true);
        // } catch (InvalidPositionException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        this.playerX = playerX;
        state = "Projected draw";
    }

    public int evaluate(Board board, Boolean isX) {
        ArrayList<Integer> possibleMoves = board.generateMoves(isX);

        // Check for win, draw, or loss
        if (board.hasPlayerWon(!isX)) {
            return (!isX == playerX) ? 10 : -10;
        }
        if (possibleMoves.isEmpty()) {
            return 0; // draw
        }


        int bestScore;

        if (isX == playerX) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }

        for (Integer move : possibleMoves) {
            Board updatedBoard = board.cloneBoard();
            try {
                updatedBoard.move(move, isX);
                int score = evaluate(updatedBoard, !isX);

                if (isX == playerX) {
                    bestScore = Math.max(bestScore, score);
                } else {
                    bestScore = Math.min(bestScore, score);
                }

            } catch (Exception e) {
                System.out.println("Invalid move: " + move);
            }
        }
        return bestScore;
    }

    public Integer run() {
        Integer bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        ArrayList<Integer> possibleMoves = boardT.generateMoves(playerX);

        for (Integer move : possibleMoves) {
            Board updatedBoard = boardT.cloneBoard();
            try {
                updatedBoard.move(move, playerX);
                int score = evaluate(updatedBoard, !playerX);

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            } catch (Exception e) {
                System.out.println("Error on move: " + move);
            }
        }

        if (bestScore == 0) {
            state = "Projected draw";
        } else {
            state = "Projected win";
        }

        return bestMove;
    }

    public void move(Integer move, Boolean turn) {
        try {
            boardT.move(move, turn);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkIfWinner(Boolean isX) {
        return boardT.hasPlayerWon(isX);
    }

    public Boolean  checkIfDraw(Boolean isX) {
        return boardT.generateMoves(isX).isEmpty();
    }

    public String getState() {
        return state;
    }
}
