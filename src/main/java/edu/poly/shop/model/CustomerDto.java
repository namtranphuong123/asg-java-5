package edu.poly.shop.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
private int customerId;
@NotEmpty
@Length(min = 5)
private String name;

private String email;
private String diachi;
private String password;
private String phone;
private Date registeredDate = new Date();
private short status;
private Boolean isEdit = false;
}
