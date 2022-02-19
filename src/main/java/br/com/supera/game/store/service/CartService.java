package br.com.supera.game.store.service;

import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.exceptions.ProductExistException;
import br.com.supera.game.store.exceptions.ProductNotFoundException;
import br.com.supera.game.store.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        return cartRepository.findById(id).orElse(new Cart(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO, new ArrayList<>()));
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

        return "Insert Cart " + cartSave.getId() + ": " + HttpStatus.OK;
    }

    public List<Cart> listAll() {
        return cartRepository.findAll();
    }
}
