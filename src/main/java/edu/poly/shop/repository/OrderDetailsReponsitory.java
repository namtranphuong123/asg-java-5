package edu.poly.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;


public interface OrderDetailsReponsitory extends JpaRepository<OrderDetail, Integer>{
	List<OrderDetail> findByOrder(Order order);
}
