package br.com.supera.game.store.controller;

import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.exceptions.InvalidParametersException;
import br.com.supera.game.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/store/cart")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private final CartService service;

    @PostMapping("/{id-cart}/products/{id-product}/add")
    public String addProduct(@PathVariable("id-cart") Long idCart,@PathVariable("id-product") Long idVar){

        Cart cart;

        try {
            cart = service.findById(idCart);
        } catch (RuntimeException e){
            cart = new Cart(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO, new ArrayList<>());
        }

        return service.addToCart(idVar, cart);
    }

    @DeleteMapping("/{id-cart}/products/{id-product}/remove")
    public String deleteProduct(@PathVariable("id-cart") Long idCart,@PathVariable("id-product") Long idVar){

        Cart cart;
        try {
            cart = service.findById(idCart);
        } catch (RuntimeException e){
            cart = new Cart(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO, new ArrayList<>());
        }

        return service.removeToCart(idVar, cart);
    }

    @GetMapping
    public List<Cart> listAllCart(){
        return service.listAll();
    }

    @GetMapping("/{id-cart}")
    public Cart findById(@PathVariable("id-cart") Long id){
        return service.findById(id);
    }

    @GetMapping("/{id-cart}/sort/{field}/{sort}")
    public Cart findByIdSort(@PathVariable("id-cart") Long id, @PathVariable("field") String field ,@PathVariable("sort") String sort){

        if (!sort.equals("asc") && !sort.equals("desc")){
            throw new InvalidParametersException("Invalid Sort Parameter");
        }

        return service.findByIdSort(id, field,sort);
    }
}
