package edu.poly.shop.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import edu.poly.shop.domain.CartItem;

public interface ShopingCartService {

	double getAmount();

	int getCount();

	Collection<CartItem> getAllItem();

	void clear();

	CartItem update(int productId, int qty);

	void remove(int productId);

	void add(CartItem item);

}
