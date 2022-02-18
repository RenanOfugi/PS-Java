package br.com.supera.game.store.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public long id;

   @NotBlank
   private String name;

   @NotBlank
   private BigDecimal price;

   @NotBlank
   private short score;

   private String image;

}