package br.com.supera.game.store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Cart {

    public Cart(BigDecimal subtotal, BigDecimal total, BigDecimal shippingCost) {
        this.subtotal = subtotal;
        this.total = total;
        this.shippingCost = shippingCost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal subtotal;

    private BigDecimal total;

    private BigDecimal shippingCost;

    @OneToMany
    private List<Product> products;
}
