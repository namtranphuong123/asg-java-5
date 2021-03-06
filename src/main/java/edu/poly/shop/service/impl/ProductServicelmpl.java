package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Product;

import edu.poly.shop.repository.ProductRepository;
import edu.poly.shop.service.ProductService;
@Service
public class ProductServicelmpl implements ProductService{
ProductRepository productRepository;

public ProductServicelmpl(ProductRepository productRepository) {
	
	this.productRepository = productRepository;
}

@Override
public List<Product> findByNameContaining(String name) {
	return productRepository.findByNameContaining(name);
}

@Override
public Page<Product> findByNameContaining(String name, Pageable pageable) {
	return productRepository.findByNameContaining(name, pageable);
}

@Override
public <S extends Product> S save(S entity) {
	return productRepository.save(entity);
}

@Override
public List<Product> findByNameLike(String name) {
	return productRepository.findByNameLike(name);
}

@Override
public Page<Product> findAll(Pageable pageable) {
	return productRepository.findAll(pageable);
}

@Override
public List<Product> findAll() {
	return productRepository.findAll();
}

@Override
public List<Product> findAll(Sort sort) {
	return productRepository.findAll(sort);
}

@Override
public List<Product> findAllById(Iterable<Integer> ids) {
	return productRepository.findAllById(ids);
}

@Override
public Optional<Product> findById(Integer id) {
	return productRepository.findById(id);
}

@Override
public <S extends Product> List<S> saveAll(Iterable<S> entities) {
	return productRepository.saveAll(entities);
}



@Override
public void flush() {
	productRepository.flush();
}

@Override
public <S extends Product> S saveAndFlush(S entity) {
	return productRepository.saveAndFlush(entity);
}

@Override
public boolean existsById(Integer id) {
	return productRepository.existsById(id);
}

@Override
public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
	return productRepository.saveAllAndFlush(entities);
}

@Override
public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
	return productRepository.findAll(example, pageable);
}

@Override
public <S extends Product> boolean exists(Example<S> example) {
	return productRepository.exists(example);
}

@Override
public void deleteAllInBatch(Iterable<Product> entities) {
	productRepository.deleteAllInBatch(entities);
}

@Override
public long count() {
	return productRepository.count();
}

@Override
public void deleteById(Integer id) {
	productRepository.deleteById(id);
}

@Override
public void deleteAllByIdInBatch(Iterable<Integer> ids) {
	productRepository.deleteAllByIdInBatch(ids);
}

@Override
public void delete(Product entity) {
	productRepository.delete(entity);
}


@Override
public void deleteAllById(Iterable<? extends Integer> ids) {
	productRepository.deleteAllById(ids);
}

@Override
public void deleteAllInBatch() {
	productRepository.deleteAllInBatch();
}

@Override
public void deleteAll(Iterable<? extends Product> entities) {
	productRepository.deleteAll(entities);
}

@Override
public void deleteAll() {
	productRepository.deleteAll();
}

@Override
public Product getById(Integer id) {
	return productRepository.getById(id);
}






}
