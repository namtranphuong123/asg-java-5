package edu.poly.shop.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.poly.shop.domain.CartItem;
import edu.poly.shop.service.ShopingCartService;
@Service
public class ShopingCartServiceImpl implements ShopingCartService {
	Map<Integer, CartItem> maps = new HashMap<>();// giỏ hàng

	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductId());
		if (cartItem == null) {
			maps.put(item.getProductId(), item);
		} else {
			cartItem.setQty(cartItem.getQty() + 1);
		}
	}

	@Override
	public void remove(int productId) {
		maps.remove(productId);
	}

	@Override
	public CartItem update(int productId, int qty) {
		CartItem cartItem = maps.get(productId);
		cartItem.setQty(qty);
		if(cartItem.getQty() <= 0) {
			maps.remove(productId);
		}
		return cartItem;
	}
	 @Override
	public void clear() {
		 maps.clear();
		 }
	 @Override
	public Collection<CartItem> getAllItem() {
		 return maps.values();
		 }
	 @Override
	public int getCount() {
		 return maps.values().size();
		
	}
	 @Override
	public double   getAmount() {
		 
		 return maps.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
		
	}
}
