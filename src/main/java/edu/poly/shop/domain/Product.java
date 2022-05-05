package edu.poly.shop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	@Column(columnDefinition = "nvarchar(100) not null")

	private String name;
	@Column(unique = false)
	private int quanltity;
	@Column(unique = false)
	private double unitPrice;
	@Column(length = 200)
	private String image;
	@Column(columnDefinition = "nvarchar(500) not null")
	private String description;
	@Column(unique = false)
	private double discount;
	@Temporal(TemporalType.DATE)
	private Date enteredDate;
	@Column(unique = false)
	private short status;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
// định ngĩa cho trường product khi xóa order thì cũng xóa theo ... mối quan hệ 1 nhiều
	//@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
	private Set<OrderDetail> orderDetails;
	

}
