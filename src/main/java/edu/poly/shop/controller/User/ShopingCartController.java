package edu.poly.shop.controller.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.hibernate.loader.plan.build.internal.returns.CollectionFetchableElementCompositeGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.CustomerService;
import edu.poly.shop.service.OrderDtailsSevice;
import edu.poly.shop.service.OrderService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.ShopingCartService;

@Controller
@RequestMapping("user/cart")
public class ShopingCartController {
	@Autowired
	ProductService productService;
	@Autowired
	ShopingCartService ShopingCartService;
	@Autowired
	HttpSession session;
	@Autowired
	CustomerService customerService;
	@Autowired
	OrderService orderService;
	@Autowired 
	OrderDtailsSevice orderDtailsSevice;

	@GetMapping("views")
	public String viewCart(Model model) {
		model.addAttribute("s", ShopingCartService.getAllItem());
		model.addAttribute("t", ShopingCartService.getAmount());
		return "user/cart/cart-item";
	}

	@GetMapping("add/{productId}")
	public String addCart(@PathVariable("productId") Integer productId) {
		Optional<Product> product = productService.findById(productId);
		if (product != null) {
			CartItem item = new CartItem();
			item.setProductId(product.get().getProductId());
			item.setName(product.get().getName());
			item.setPrice(product.get().getUnitPrice()*(100 - product.get().getDiscount())*0.01);
			item.setQty(1);
			ShopingCartService.add(item);
		}

		return "redirect:/user/cart/views";
	}
	@GetMapping("list")
	public String list(ModelMap model ,
			@RequestParam(name = "name", required = false)String name) {
//		session.getAttribute("email");
//		
		List<Customer> list = null;
		if(StringUtils.hasText(name)) {
			list = customerService.findByNameContaining(name);
			
		}else {
			list = customerService.findAll();
			
		}
		model.addAttribute("customers",list);
		return "user/cart/list";
	}
	@GetMapping("clear")
	public String clearCart() {
		ShopingCartService.clear();
		return "redirect:/user/cart/views";
	}

	@GetMapping("checkout")
	public String checkout(Model model, @ModelAttribute("customer") CustomerDto customerDto) {
		session.getAttribute("email");
		if (session.getAttribute("email") == null) {
			return "forward:/user/accounts/login";
		}

		Optional<Customer> customer = customerService.findByEmail((String) session.getAttribute("email"));

		Order order = new Order();
		order.setAmount(ShopingCartService.getAmount());
		order.setOrderDate(new Date());
		order.setStatus((short) 1);
		order.setCustomer(customer.get());
//		orderService.save(order);
		Collection<CartItem> cartItems = ShopingCartService.getAllItem();

		List<OrderDetail> details = new ArrayList<>();

		for (CartItem cartItem : cartItems) {

			OrderDetail detail = new OrderDetail();

			detail.setOrder(order);
			Product product = new Product();
			product.setProductId(cartItem.getProductId());
			detail.setProduct(product);
			detail.setUnitPrice(cartItem.getPrice());
			detail.setQuantity(cartItem.getQty());

			details.add(detail);
			
			 
			Product product2 = productService.getById(cartItem.getProductId());
			product2.setQuanltity(product2.getQuanltity() - cartItem.getQty());
			productService.save(product2);
		}
			orderDtailsSevice.create(order, details);
			

			System.out.print("Thành công: Mã đơn hàng của bạn là " + order.getOrderId());
			
			model.addAttribute("a" , order);
			
			

			ShopingCartService.clear();
			
			return  "user/cart/success";
			

		
		
	}

	@GetMapping("delete/{productId}")
	public String removeCart(@PathVariable("productId") Integer productId) {
		ShopingCartService.remove(productId);

		return "redirect:/user/cart/views";
	}

}




