package edu.poly.shop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.ProductService;

@Controller
@RequestMapping("all/index")
public class IndexAdminController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	JavaMailSender javaMailSender;
	
	@RequestMapping("lienhe")
	public String lienhe() {
		return "user/home/lienhe";
	}

	@RequestMapping("/send")

	public String sendMail(ModelMap model , @RequestParam("to") String to, @RequestParam("subject") String subject,
			@RequestParam("content") String content) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(content);
		javaMailSender.send(msg);
		return "user/home/lienhe";	
	}

	@GetMapping("")
	public String list(ModelMap model) {

		List<Product> list = productService.findByNameLike("%Phone%");
		model.addAttribute("s", list);
		List<Product> list1 = productService.findByNameLike("%Laptop%");
		model.addAttribute("a", list1);
		List<Product> list2 = productService.findByNameLike("%?????ng%");
		model.addAttribute("c", list2);

		Category category = new Category();
		// ????? categories v??o html
		model.addAttribute("categories", categoryService.findAll());
		return "all/index/products";

	}
	@GetMapping("view/{productId}")	
	public String view(ModelMap model,@PathVariable("productId")Integer productId) {
		
		
		Optional<Product> opt = productService.findById(productId);
		// ki???m tra n???u c?? gi?? t??? tr??? v??? b???ng isPr???nt
		ProductDto dto = new ProductDto();
		if(opt.isPresent()) {
			Product entity = opt.get();
			//n???u product c?? t???n t???i th?? s??? g??n th??? hi???n c???a product ???????c t??m th???y coppy qua dto
			
			BeanUtils.copyProperties(entity, dto);	
			
		}
		// thi???t l???p thu???c t??nh c???a product ????? gi??p cho view c?? th??? ?????c th??ng tin c???a product ????
		model.addAttribute("product",dto);
		return "all/index/view";
	}	
	
	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		Category category = new Category();
		// ????? categories v??o html
		model.addAttribute("categories", categoryService.findAll());
		List<Product> list = null;
		if (StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name);

		} else {
			list = productService.findAll();

		}
		model.addAttribute("s", list);
		return "all/index/products";

	}

	
}
