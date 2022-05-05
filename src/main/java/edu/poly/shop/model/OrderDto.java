package edu.poly.shop.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable{
private int orderId;
private Date orderDate =  new Date();
private int  customerId;
private double amount;
private short status;
private Boolean isEdit = false;
}
