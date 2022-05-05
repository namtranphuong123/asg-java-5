package edu.poly.shop.controller.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.CustomerService;

@Controller
@RequestMapping("all/accounts")
public class AccountUserController {
	@Autowired
	CustomerService customerService;
	
	
	@Autowired
	HttpSession Session;
	
	
	
	
	
	@GetMapping("add")
	public String add(Model model) {
		
		model.addAttribute("customer",  new CustomerDto());
		return "all/accounts/addOrEditUser";
	}
	//ánh xạ của catoryId giống với biến đã khai báo trong phương thức edit

	@PostMapping("saveOrUpdate")
	// các enotion để kiểm tra dữ liệu nếu có lỗi xảy rsa return về addoredit
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("all/accounts/addOrEditUser");
		}
		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);
		
		customerService.save(entity);
		
		model.addAttribute("message","Customer is saved!");
		
		return new ModelAndView("forward:/all/accounts",model);
	}
	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model ,@PathVariable("customerId") Integer customerId) {
		customerService.deleteById(customerId);
		model.addAttribute("message","customerId is deleted !");
		
		
		return new ModelAndView("forward:/all/accounts/search",model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		
		List<Customer> list = customerService.findAll();
		model.addAttribute("customers", list);
		return "all/accounts/search";
	}
	@GetMapping("search")
	public String search(ModelMap model ,
			@RequestParam(name = "name", required = false)String name) {
		List<Customer> list = null;
		if(StringUtils.hasText(name)) {
			list = customerService.findByNameContaining(name);
			
		}else {
			list = customerService.findAll();
			
		}
		model.addAttribute("customers",list);
		return "all/accounts/search";
	}

	@GetMapping("edit/{customerId}")
	public ModelAndView edit(ModelMap model,@PathVariable("customerId")Integer customerId) {
		
		Optional<Customer> opt = customerService.findById(customerId);
		// kiểm tra nếu có giá tị trả về bằng isPrếnt
		CustomerDto dto = new CustomerDto();
		if(opt.isPresent()) {
			Customer entity = opt.get();
			//nếu Customer có tồn tại thì sẽ gán thể hiện của Customer được tìm thấy coppy qua dto
			
			BeanUtils.copyProperties(entity, dto);
			
			//nếu trường edit có giá trị true thì sẽ thực thi truyền dto vào model để hiển thị trên form addOrEdit
			dto.setIsEdit(true);
			// thiết lập thuộc tính của Customer để giúp cho view có thể đọc thông tin của customer đó
			model.addAttribute("customer",dto);
			return new ModelAndView("all/accounts/addOrEditUser",model);
		}
		
		
model.addAttribute("message","Customer is not exited");
		return new ModelAndView("forward:/all/accounts",model);
	}
}
