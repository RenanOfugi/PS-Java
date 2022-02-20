package br.com.supera.game.store.service;

import br.com.supera.game.store.controller.CartController;
import br.com.supera.game.store.entity.Cart;
import br.com.supera.game.store.entity.Product;
import br.com.supera.game.store.repository.CartRepository;
import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CartServiceTest {

    @Autowired
    private CartService service;

    @Autowired
    private CartRepository repository;

    public CartServiceTest(){
    }

    @Test
    public void testInsertCart() {
        Cart cart = new Cart(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, new ArrayList<>());
        String message = service.insertCart(cart);
        Assert.assertEquals("200 OK", message);
    }

    @Test
    public void testSortNameProductCart(){

        Cart cart = new Cart(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, new ArrayList<>());

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("gama");
        product1.setPrice(new BigDecimal("18.50"));
        product1.setScore((short) 80);
        product1.setImage("Test");

        Product product2 = new Product();
        product1.setName("beta");
        product1.setPrice(new BigDecimal("19"));
        product1.setScore((short) 10);
        product1.setImage("Test");

        Product product3 = new Product();
        product1.setName("alpha");
        product1.setPrice(new BigDecimal("11"));
        product1.setScore((short) 50);
        product1.setImage("Test");

        products.add(product1);
        products.add(product2);
        products.add(product3);

        cart.setProducts(products);


        Cart cartsave = repository.save(cart);
        Cart cartSort = service.findByIdSort(cartsave.getId(),"name", "asc");
        Hibernate.initialize(cartSort.getProducts());
        System.out.println(cartSort);

        //Assert.assertEquals("alpha", cartSort.getProducts().get(1).getName());
    }

    @Test
    public void findByIdTest(){
        Cart cart = new Cart(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, new ArrayList<>());
        Cart cartsave = repository.save(cart);
        Cart cartFind = service.findById(cartsave.getId());

        Assert.assertEquals(1L, cartFind.getId());
    }
}
