package br.com.jogodavelha.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JogoDaVelha {
    public Integer bestAction(Character[][] board, char eu) {
        List<Integer[]> plays = possiblePlays(board);
        Integer best = Integer.MIN_VALUE;
        Integer[] bestAction = null;

        for (Integer[] play : plays) {
            Character[][] result = play(board, play, eu);
            Integer value = miniMax(result, eu == 'X' ? 'O' : 'X', eu, 9);
            if (value > best) {
                best = value;
                bestAction = play;
            }
        }
        return positionMatToPositionList(bestAction);
    }

    private Character[][] play(Character[][] board, Integer[] positions, Character player) {
        Character[][] newBoard = new Character[][]{
                {board[0][0], board[0][1], board[0][2]},
                {board[1][0], board[1][1], board[1][2]},
                {board[2][0], board[2][1], board[2][2]}
        };

        newBoard[positions[0]][positions[1]] = player;
        return newBoard;
    }

    private Integer miniMax(Character[][] board, Character player, Character eu, Integer maxDepth) {
        List<Integer[]> plays = possiblePlays(board);
        Character w = checkWinner(board);

        if (w != null && w.equals(eu)) return 1;
        if (w != null) return -1;
        if (plays.isEmpty()) return 0;

        if (player.equals(eu)) {
            Integer best = Integer.MIN_VALUE;
            for (Integer[] play : plays) {
                Character[][] result = play(board, play, player);
                Integer value = miniMax(result, player == 'X' ? 'O' : 'X', eu, maxDepth--);
                if (value > best) best = value;
            }
            return best;
        } else {
            Integer best = Integer.MAX_VALUE;
            for (Integer[] play : plays) {
                Character[][] result = play(board, play, player);
                Integer value = miniMax(result, player == 'X' ? 'O' : 'X', eu, maxDepth--);
                if (value < best) best = value;
            }
            return best;
        }
    }

    private List<Integer[]> possiblePlays(Character[][] board) {
        List<Integer[]> plays = new ArrayList<>();

        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (board[l][c].equals(' ')) plays.add(new Integer[]{l, c});
            }
        }

        return plays;
    }

    private Character checkWinner(Character[][] board) {
        if (board[0][0].equals(board[0][1]) && board[0][0].equals(board[0][2]) && !board[0][0].equals(' '))
            return board[0][0];
        if (board[1][0].equals(board[1][1]) && board[1][0].equals(board[1][2]) && !board[1][0].equals(' '))
            return board[1][0];
        if (board[2][0].equals(board[2][1]) && board[2][0].equals(board[2][2]) && !board[2][0].equals(' '))
            return board[2][0];

        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals(' '))
            return board[0][2];
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals(' '))
            return board[0][0];

        if (board[0][0].equals(board[1][0]) && board[0][0].equals(board[2][0]) && !board[0][0].equals(' '))
            return board[0][0];
        if (board[0][1].equals(board[1][1]) && board[0][1].equals(board[2][1]) && !board[0][1].equals(' '))
            return board[0][1];
        if (board[0][2].equals(board[1][2]) && board[0][2].equals(board[2][2]) && !board[0][2].equals(' '))
            return board[0][2];

        return null;
    }

    private Integer positionMatToPositionList(Integer[] position) {
        if (position[0].equals(0) && position[1].equals(0)) return 0;
        if (position[0].equals(0) && position[1].equals(1)) return 1;
        if (position[0].equals(0) && position[1].equals(2)) return 2;
        if (position[0].equals(1) && position[1].equals(0)) return 3;
        if (position[0].equals(1) && position[1].equals(1)) return 4;
        if (position[0].equals(1) && position[1].equals(2)) return 5;
        if (position[0].equals(2) && position[1].equals(0)) return 6;
        if (position[0].equals(2) && position[1].equals(1)) return 7;
        if (position[0].equals(2) && position[1].equals(2)) return 8;
        return null;
    }
}
