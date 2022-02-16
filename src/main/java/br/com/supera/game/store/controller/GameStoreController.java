package br.com.supera.game.store.controller;

import br.com.supera.game.store.service.GameStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
public class GameStoreController {

    @Autowired
    private final GameStoreService service;
}
