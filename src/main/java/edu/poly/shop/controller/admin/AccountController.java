package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CategoryService;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;
	@GetMapping("add")
	public String add(Model model) {
		
		model.addAttribute("account",  new AccountDto()); 
		
		return "admin/accounts/addOrEdit";
	}
	//ánh xạ của catoryId giống với biến đã khai báo trong phương thức edit
	
	@PostMapping("saveOrUpdate")
	// các enotion để kiểm tra dữ liệu nếu có lỗi xảy rsa return về addoredit
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		
		accountService.save(entity);
		
		model.addAttribute("message","account is saved!");
		
		return new ModelAndView("forward:/admin/accounts",model);
	}
	
	
	
	@GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model, @PathVariable("username")String username) {

		Optional<Account> opt = accountService.findById(username);
		// kiểm tra nếu có giá tị trả về bằng isPrếnt
		AccountDto dto = new AccountDto();
		if(opt.isPresent()) {
			Account entity = opt.get();
			//nếu category có tồn tại thì sẽ gán thể hiện của category được tìm thấy coppy qua dto
			BeanUtils.copyProperties(entity, dto);
			
			//nếu trường edit có giá trị true thì sẽ thực thi truyền dto vào model để hiển thị trên form addOrEdit
			dto.setIsEdit(true);
			//sau khi thiet lap se set lai pass va khong hien thi len form vaf chir duoc nhap trem form
			dto.setPassword("");
			// thiết lập thuộc tính của account để giúp cho view có thể đọc thông tin của category đó
			model.addAttribute("account",dto);
			return new ModelAndView("admin/accounts/addOrEdit",model);
		}
		
		
model.addAttribute("message","Account is not exited");
		return new ModelAndView("forward:/admin/accounts",model);
	}
//	
//	
//
	@GetMapping("delete/{username}")
	public ModelAndView delete(ModelMap model ,@PathVariable("username") String username) {
		accountService.deleteById(username);
		model.addAttribute("message","cagetoryId is deleted !");
		
		
		return new ModelAndView("forward:/admin/accounts",model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Account> list = accountService.findAll();
		model.addAttribute("accounts", list);
		return "admin/accounts/list";
	}
//	@GetMapping("search")
//	public String search(ModelMap model ,
//			@RequestParam(name = "name", required = false)String name) {
//		List<Category> list = null;
//		if(StringUtils.hasText(name)) {
//			list = categoryService.findByNameContaining(name);
//			
//		}else {
//			list = categoryService.findAll();
//			
//		}
//		model.addAttribute("accounts",list);
//		return "admin/accounts/search";
//	}
//	@GetMapping("searchpaginated")
//	public String search(ModelMap model ,
//			@RequestParam(name = "name", required = false)String name,
//			@RequestParam ("page") Optional<Integer> page,
//			@RequestParam ("size") Optional<Integer> size) {
//		// nếu không n hập giá trị thì sử dụng giá trị ngầm định bằng 1 
//		int currentPage = page.orElse(1);
//		//giá trị ngầm định bằng 5 phgaanf tử 1 trang
//		int pageSize = size.orElse(5);
//		//gọi thực hiện phương thức of và truyền vào các đối số như trang và số lượng và cách sắp xêps
//		Pageable pageable =PageRequest.of(currentPage - 1,pageSize, Sort.by("name"));
//		Page<Category> resultPage = null;
//		// nếu thông tin của name được tìm thấy thì chuyền pageable vào phương thức findByNameContaining
//		if(StringUtils.hasText(name)) {
//			resultPage = categoryService.findByNameContaining(name,pageable);
//			model.addAttribute("name",name);
//		}else {
//			resultPage = categoryService.findAll(pageable);
//			
//		}
//		//thực hiện tính số trang trên view 
//		int totalPages = resultPage.getTotalPages();
//		// nếu tatol lơn hơn 0 thì thwucj hiện tính toán phân trang 
//		if(totalPages > 0) {
//			int start =Math.max(1, currentPage-2);
//			int end = Math.min(currentPage + 2, totalPages);
//			// tiếp tục kiểm tra nếu totalPage lớn hơn 5 thì kiểm tra tiếp  end bằng totalpAge thì sẽ tính ngược lại giá trị của start = -5
//			//ngược kaij nếu sart bằng 1 thì tính ra end bằng start + 5;
//			if(totalPages > 5) {
//				if(end == totalPages)start = end - 5;
//				else if(start == 1) end = start + 5;
//			
//		}
//			List<Integer> pageNumbers = IntStream.rangeClosed(start , totalPages)
//					.boxed()
//					.collect(Collectors.toList());
//			model.addAttribute("pageNumbers",pageNumbers);
//		}
//		// trả về kết quả phương thưc
//		model.addAttribute("name",name);
//		model.addAttribute("categoryPage",resultPage);
//		return "admin/accounts/searchpaginated";
//	}
}
