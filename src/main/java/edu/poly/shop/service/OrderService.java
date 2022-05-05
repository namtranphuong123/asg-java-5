package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;

public interface OrderService {

	Order getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Order> entities);

	Order getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Order entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch(Iterable<Order> entities);

	void deleteInBatch(Iterable<Order> entities);

	<S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Integer id);

	<S extends Order> S saveAndFlush(S entity);

	void flush();

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	Optional<Order> findById(Integer id);

	List<Order> findAllById(int i);

	List<Order> findAll(Sort sort);

	List<Order> findAll();

	Page<Order> findAll(Pageable pageable);

	<S extends Order> S save(S entity);

	List<Order> findAllById(Iterable<Integer> ids);

	

}
