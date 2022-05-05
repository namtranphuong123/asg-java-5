package edu.poly.shop.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {
private Integer productId;
private String Name ;
private double price;
 private  int qty =1;


}
