package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.repository.OrderDetailsReponsitory;
import edu.poly.shop.service.OrderDtailsSevice;
import edu.poly.shop.service.OrderService;
@Service
public class OrderDetailsSeviceImpl implements OrderDtailsSevice {
OrderDetailsReponsitory orderDetailsReponsitory;


@Autowired
OrderService orderService;
@Autowired
OrderDtailsSevice orderDetailsService;




@Override
public void create( Order order , List<OrderDetail> details) {
	orderService.save(order);
	for(OrderDetail orderDetail : details) {
		orderDetailsService.save(orderDetail);
	}
}

@Override
public List<OrderDetail> findByOrder(Order order) {
	return orderDetailsReponsitory.findByOrder(order);
}

public OrderDetailsSeviceImpl(OrderDetailsReponsitory orderDetailsReponsitory) {
	super();
	this.orderDetailsReponsitory = orderDetailsReponsitory;
}

@Override
public <S extends OrderDetail> S save(S entity) {
	return orderDetailsReponsitory.save(entity);
}

@Override
public Page<OrderDetail> findAll(Pageable pageable) {
	return orderDetailsReponsitory.findAll(pageable);
}

@Override
public List<OrderDetail> findAll() {
	return orderDetailsReponsitory.findAll();
}

@Override
public List<OrderDetail> findAll(Sort sort) {
	return orderDetailsReponsitory.findAll(sort);
}

@Override
public List<OrderDetail> findAllById(Iterable<Integer> ids) {
	return orderDetailsReponsitory.findAllById(ids);
}

@Override
public Optional<OrderDetail> findById(Integer id) {
	return orderDetailsReponsitory.findById(id);
}

@Override
public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
	return orderDetailsReponsitory.saveAll(entities);
}

@Override
public void flush() {
	orderDetailsReponsitory.flush();
}

@Override
public <S extends OrderDetail> S saveAndFlush(S entity) {
	return orderDetailsReponsitory.saveAndFlush(entity);
}

@Override
public boolean existsById(Integer id) {
	return orderDetailsReponsitory.existsById(id);
}

@Override
public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
	return orderDetailsReponsitory.saveAllAndFlush(entities);
}

@Override
public void deleteInBatch(Iterable<OrderDetail> entities) {
	orderDetailsReponsitory.deleteInBatch(entities);
}

@Override
public void deleteAllInBatch(Iterable<OrderDetail> entities) {
	orderDetailsReponsitory.deleteAllInBatch(entities);
}

@Override
public long count() {
	return orderDetailsReponsitory.count();
}

@Override
public void deleteById(Integer id) {
	orderDetailsReponsitory.deleteById(id);
}

@Override
public void deleteAllByIdInBatch(Iterable<Integer> ids) {
	orderDetailsReponsitory.deleteAllByIdInBatch(ids);
}

@Override
public void delete(OrderDetail entity) {
	orderDetailsReponsitory.delete(entity);
}

@Override
public void deleteAllById(Iterable<? extends Integer> ids) {
	orderDetailsReponsitory.deleteAllById(ids);
}

@Override
public void deleteAllInBatch() {
	orderDetailsReponsitory.deleteAllInBatch();
}

@Override
public OrderDetail getOne(Integer id) {
	return orderDetailsReponsitory.getOne(id);
}

@Override
public void deleteAll(Iterable<? extends OrderDetail> entities) {
	orderDetailsReponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	orderDetailsReponsitory.deleteAll();
}

@Override
public OrderDetail getById(Integer id) {
	return orderDetailsReponsitory.getById(id);
}

}
