package edu.poly.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable  {
private int productId;
@NotEmpty

@Length(min = 5)
private String name;

private int quanltity;

@Min(value = 0)
private double unitPrice;

private String image;
private MultipartFile imageFile;

@NotEmpty
private String description;
private double discount;

private Date enteredDate = new Date();

private short status;
private Long  categoryId;
private Boolean isEdit = false;
}
