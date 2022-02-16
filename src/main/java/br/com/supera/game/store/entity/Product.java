package br.com.supera.game.store.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Product {

   @Id
   @GeneratedValue
   public long id;

   public String name;

   public BigDecimal price;

   public short score;

   public String image;

}