package br.com.supera.game.store.service;

import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final GameStoreService gameStoreService;

    @Autowired
    private final CartRepository cartRepository;

    public String addToCart(Long id, Long idCart) {
        Product product = gameStoreService.findById(id);

        Cart cart = findById(idCart);
        return "";
    }

    public Cart findById(Long id){
        return cartRepository.findById(id).orElse(new Cart(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO));
    }
}
