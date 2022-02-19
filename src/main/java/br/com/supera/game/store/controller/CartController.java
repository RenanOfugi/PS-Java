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

    @PostMapping("/{id-cart}/product/{id-product}/add")
    public String addProduct(@PathVariable("id-cart") Long idCart,@PathVariable("id-product") Long idVar){

        Cart cart;

        try {
            cart = service.findById(idCart);
        } catch (RuntimeException e){
            cart = new Cart(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO, new ArrayList<>());
        }

        return service.addToCart(idVar, cart);
    }

    @DeleteMapping("/{id-cart}/product/{id-product}/remove")
    public String deleteProduct(@PathVariable("id-cart") Long idCart,@PathVariable("id-product") Long idVar){
        Cart cart = service.findById(idCart);
        return service.removeToCart(idVar, cart);
    }

    @GetMapping
    public List<Cart> listAllCart(){
        return service.listAll();
    }
}
