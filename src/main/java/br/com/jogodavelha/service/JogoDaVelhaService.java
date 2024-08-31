package br.com.jogodavelha.service;


import br.com.jogodavelha.ia.JogoDaVelha;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JogoDaVelhaService {
    public Integer discoverNextPlay(Character[] board) {
        if (board.length != 9) throw new IllegalArgumentException();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) board[i] = ' ';
        }

        Character[][] boardFormat = {
                {board[0], board[1], board[2]},
                {board[3], board[4], board[5]},
                {board[6], board[7], board[8]}
        };

        JogoDaVelha jogoDaVelha = new JogoDaVelha();
        return jogoDaVelha.bestAction(boardFormat, 'O');
    }
}
