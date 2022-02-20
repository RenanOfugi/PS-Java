package br.com.supera.game.store.controller;

import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.service.GameStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/store/products")
@RequiredArgsConstructor
public class GameStoreController {

    private final GameStoreService service;

    @GetMapping
    public List<Product> listAllProducts(){
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id){
        return service.findById(id);
    }
}
