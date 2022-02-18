package br.com.supera.game.store.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {

    public Cart(BigDecimal subtotal, BigDecimal total, BigDecimal shippingCost) {
        this.subtotal = subtotal;
        this.total = total;
        this.shippingCost = shippingCost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private BigDecimal subtotal;

    @NotBlank
    private BigDecimal total;

    @NotBlank
    private BigDecimal shippingCost;

    @OneToMany
    private List<Product> products;
}
