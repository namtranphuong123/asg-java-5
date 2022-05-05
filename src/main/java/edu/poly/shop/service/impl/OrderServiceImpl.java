package edu.poly.shop.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.repository.OrderRepository;
import edu.poly.shop.service.OrderDtailsSevice;
import edu.poly.shop.service.OrderService;
@Repository
public class OrderServiceImpl implements OrderService{
OrderRepository orderRepository;




public OrderServiceImpl(OrderRepository orderRepository) {
	
	this.orderRepository = orderRepository;
}

@Override
public <S extends Order> S save(S entity) {
	return orderRepository.save(entity);
}

@Override
public Page<Order> findAll(Pageable pageable) {
	return orderRepository.findAll(pageable);
}

@Override
public List<Order> findAll() {
	return orderRepository.findAll();
}

@Override
public List<Order> findAll(Sort sort) {
	return orderRepository.findAll(sort);
}


@Override
public List<Order> findAllById(Iterable<Integer> ids) {
	return orderRepository.findAllById(ids);
}

@Override
public Optional<Order> findById(Integer id) {
	return orderRepository.findById(id);
}

@Override
public <S extends Order> List<S> saveAll(Iterable<S> entities) {
	return orderRepository.saveAll(entities);
}

@Override
public void flush() {
	orderRepository.flush();
}

@Override
public <S extends Order> S saveAndFlush(S entity) {
	return orderRepository.saveAndFlush(entity);
}

@Override
public boolean existsById(Integer id) {
	return orderRepository.existsById(id);
}

@Override
public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
	return orderRepository.saveAllAndFlush(entities);
}

@Override
public void deleteInBatch(Iterable<Order> entities) {
	orderRepository.deleteInBatch(entities);
}

@Override
public void deleteAllInBatch(Iterable<Order> entities) {
	orderRepository.deleteAllInBatch(entities);
}

@Override
public long count() {
	return orderRepository.count();
}

@Override
public void deleteById(Integer id) {
	orderRepository.deleteById(id);
}

@Override
public void deleteAllByIdInBatch(Iterable<Integer> ids) {
	orderRepository.deleteAllByIdInBatch(ids);
}

@Override
public void delete(Order entity) {
	orderRepository.delete(entity);
}

@Override
public void deleteAllById(Iterable<? extends Integer> ids) {
	orderRepository.deleteAllById(ids);
}

@Override
public void deleteAllInBatch() {
	orderRepository.deleteAllInBatch();
}

@Override
public Order getOne(Integer id) {
	return orderRepository.getOne(id);
}

@Override
public void deleteAll(Iterable<? extends Order> entities) {
	orderRepository.deleteAll(entities);
}

@Override
public void deleteAll() {
	orderRepository.deleteAll();
}

@Override
public Order getById(Integer id) {
	return orderRepository.getById(id);
}

@Override
public List<Order> findAllById(int i) {
	// TODO Auto-generated method stub
	return null;
}


}
