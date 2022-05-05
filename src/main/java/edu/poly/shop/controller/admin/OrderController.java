package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.OrderDtailsSevice;
import edu.poly.shop.service.OrderService;
import edu.poly.shop.service.ProductService;

@Controller
@RequestMapping("admin/orders")

public class OrderController {
	@Autowired
	OrderService OrderService;

	@Autowired
	ProductService productService;
	
	@Autowired
	OrderDtailsSevice OrderDtailsSevice;
	
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Order> list = OrderService.findAll();
		model.addAttribute("s", list);
		return "admin/orders/search";
	}
	@GetMapping("delete/{orderId}")
	public ModelAndView delete(ModelMap model ,@PathVariable("orderId") Integer orderId) {
		OrderService.deleteById(orderId);
		model.addAttribute("message","orders is deleted !");
		
		
		return new ModelAndView("forward:/admin/orders",model);
	}
	
	@RequestMapping("oderDetails/{orderId}")
	public String shoppingCartOrderDetails(Model model, @PathVariable("orderId") Integer orderId) {
		Order od = OrderService.findById(orderId).get();
		List<OrderDetail> item = OrderDtailsSevice.findByOrder(od);
		model.addAttribute("OrderDetailss", item);
		return "admin/orders/details";
	}
	

}

