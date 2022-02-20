package br.com.supera.game.store.service;

import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.exceptions.CartNotFoundException;
import br.com.supera.game.store.exceptions.ProductExistException;
import br.com.supera.game.store.exceptions.ProductNotFoundException;
import br.com.supera.game.store.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final GameStoreService gameStoreService;

    @Autowired
    private final CartRepository cartRepository;

    public String addToCart(Long id, Cart cart) {

        Product product = gameStoreService.findById(id);
        cart = updateSumDataCart(cart, product);

        return insertCart(cart);
    }

    public String removeToCart(Long id, Cart cart) {
        Product product = gameStoreService.findById(id);
        cart = updateSubDataCart(cart, product);

        return insertCart(cart);
    }

    public Cart findById(Long id){
        return cartRepository.findById(id).orElseThrow(()-> new CartNotFoundException("Cart not exist"));
    }

    public Cart findByIdSort(Long id, String field,String sort) {
        Cart cart = findById(id);

        switch (field){
            case "name":
                cart.getProducts().sort(Comparator.comparing(Product::getName));
                break;
            case "price":
                cart.getProducts().sort(Comparator.comparing(Product::getPrice));
                break;
            case "score":
                cart.getProducts().sort(Comparator.comparing(Product::getScore));
                break;
        }

        if (sort.equals("desc")){
            Collections.reverse(cart.getProducts());
        }

        return cart;
    }

    public Cart updateSumDataCart(Cart cart, Product product){

        cart.getProducts().add(product);

        BigDecimal subtotal = cart.getSubtotal().add(product.getPrice());
        BigDecimal shippingCost;

        if(subtotal.compareTo(new BigDecimal("250")) >= 0){
            shippingCost = BigDecimal.ZERO;
        } else {
            shippingCost = cart.getShippingCost().add(BigDecimal.TEN);
        }

        BigDecimal total = subtotal.add(shippingCost);

        cart.setSubtotal(subtotal);
        cart.setShippingCost(shippingCost);
        cart.setTotal(total);

        return cart;
    }

    public Cart updateSubDataCart(Cart cart, Product product){

        boolean remove = cart.getProducts().remove(product);

        if(!remove){
            throw new ProductNotFoundException("Product not exist in cart");
        }

        BigDecimal subtotal = cart.getSubtotal().subtract(product.getPrice());
        BigDecimal shippingCost;

        if(subtotal.compareTo(new BigDecimal("250")) >= 0){
            shippingCost = BigDecimal.ZERO;
        } else {
            shippingCost = BigDecimal.TEN.multiply(new BigDecimal(cart.getProducts().size()));
        }

        BigDecimal total = subtotal.add(shippingCost);

        cart.setSubtotal(subtotal);
        cart.setShippingCost(shippingCost);
        cart.setTotal(total);

        return cart;
    }

    public String insertCart(Cart cart){

        Cart cartSave;
        try{
            cartSave = cartRepository.save(cart);
        } catch (RuntimeException exception){
            throw new ProductExistException("Product already exist in cart");
        }

        return HttpStatus.OK.toString();
    }

    public List<Cart> listAll() {
        return cartRepository.findAll();
    }
}
