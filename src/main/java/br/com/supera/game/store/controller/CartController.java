package br.com.supera.game.store.controller;

import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private final CartService service;

    @PostMapping
    public String addProduct(@RequestParam("id") Long id){

        Cart cart = service.findById(1L); //Foi utilizado 1L referente ao 1 carrinho de compras, para o desafio
        return service.addToCart(id, cart);
    }

    @GetMapping
    public List<Cart> listAllCart(){
        return service.listAll();
    }
}
