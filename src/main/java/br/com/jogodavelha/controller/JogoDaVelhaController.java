package br.com.jogodavelha.controller;

import br.com.jogodavelha.service.JogoDaVelhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/move")
public class JogoDaVelhaController {
    private final JogoDaVelhaService service;

    public JogoDaVelhaController(JogoDaVelhaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Integer> fazJogada(@RequestBody Character[] board) {
        Integer nextPlay = service.discoverNextPlay(board);
        return new ResponseEntity<>(nextPlay, HttpStatus.OK);
    }
}
