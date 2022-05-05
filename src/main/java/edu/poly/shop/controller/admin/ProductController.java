package edu.poly.shop.controller.admin;

import java.io.File;
import java.io.IOException;
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

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.ProductService;

@Controller
@RequestMapping("admin/products")

public class ProductController { 
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	
	@GetMapping("add")
	public String add(Model model) {
		// đổ categories vào html
		Category category = new Category();
		// đổ categories vào html
		model.addAttribute("categories",  categoryService.findAll());
		/// đổ một product qua form, như một new model 
		model.addAttribute("product", new ProductDto());
		return "admin/products/addOrEdit";
		
	}
	//ánh xạ của productId giống với biến đã khai báo trong phương thức edit
	
	
	@GetMapping("edit/{productId}")	
	public ModelAndView edit(ModelMap model,@PathVariable("productId")Integer productId) {
		Category category = new Category();
		model.addAttribute("categories",  categoryService.findAll());

		Optional<Product> opt = productService.findById(productId);
		// kiểm tra nếu có giá tị trả về bằng isPrếnt
		ProductDto dto = new ProductDto();
		if(opt.isPresent()) {
			Product entity = opt.get();
			//nếu product có tồn tại thì sẽ gán thể hiện của product được tìm thấy coppy qua dto
			BeanUtils.copyProperties(entity, dto);
			
			//nếu trường edit có giá trị true thì sẽ thực thi truyền dto vào model để hiển thị trên form addOrEdit
			dto.setIsEdit(true);
			// thiết lập thuộc tính của product để giúp cho view có thể đọc thông tin của product đó
			model.addAttribute("product",dto);
			return new ModelAndView("admin/products/addOrEdit",model);
		}
		
		
model.addAttribute("message","product is not exited");
		return new ModelAndView("forward:/admin/products",model);
	}
	
	
	@PostMapping("saveOrUpdate")
	// các enotion để kiểm tra dữ liệu nếu có lỗi xảy rsa return về addoredit
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("product") ProductDto productDto, BindingResult result,
			@RequestParam("imageEdit")String imageEdit) throws  IOException {
		if(result.hasErrors()) {
			return new ModelAndView("admin/products/addOrEdit");
		}
	
		String baseDir = "D:\\SpringTools2021\\tech-polyshop\\src\\main\\resources\\static\\images\\";
		
		String filename;
		if(!productDto.getImageFile().isEmpty()) {
			// lấy nội dung tên file gốc do người dùng upload lên sever
			filename = productDto.getImageFile().getOriginalFilename();
			// sử lý nội udng của file filename upload vào thư mục đã định baseDir.
			productDto.getImageFile().transferTo(new File(baseDir + filename));
			
		}else {
			//ngược lại lấy filename = imageEdit trên form
			filename = imageEdit;
		}
		
		Product entity = new Product();
		productDto.setImage(filename);
		
		BeanUtils.copyProperties(productDto, entity);
		
		Category category = new Category();
		category.setCategoryId(productDto.getCategoryId());
		entity.setCategory(category);
		productService.save(entity);
		
		model.addAttribute("message","product is saved!");
		
		return new ModelAndView("forward:/admin/products",model);
	}
	
	
	@GetMapping("view/{productId}")	
	public String view(ModelMap model,@PathVariable("productId")Integer productId) {
		
		
		Optional<Product> opt = productService.findById(productId);
		// kiểm tra nếu có giá tị trả về bằng isPrếnt
		ProductDto dto = new ProductDto();
		if(opt.isPresent()) {
			Product entity = opt.get();
			//nếu product có tồn tại thì sẽ gán thể hiện của product được tìm thấy coppy qua dto
			BeanUtils.copyProperties(entity, dto);				
		}
		// thiết lập thuộc tính của product để giúp cho view có thể đọc thông tin của product đó
		model.addAttribute("product",dto);
		return "admin/products/view";
	}	
	
	
	@GetMapping("delete/{productId}")
	public ModelAndView delete(ModelMap model ,@PathVariable("productId") Integer productId) {
		productService.deleteById(productId);
		model.addAttribute("message","ProductId is deleted !");
		return new ModelAndView("forward:/admin/products/search",model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		
		
		List<Product> list = productService.findAll();
		model.addAttribute("s", list);
		return "admin/products/search";
	}
	@GetMapping("search")
	public String search(ModelMap model ,
			@RequestParam(name = "name", required = false)String name) {
		List<Product> list = null;
		if(StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name);
			
		}else {
			list = productService.findAll();
			
		}
		model.addAttribute("s",list);
		return "admin/products/search";
	}
	@GetMapping("searchpaginated")
	public String search(ModelMap model ,
			@RequestParam(name = "name", required = false)String name,
			@RequestParam ("page") Optional<Integer> page,
			@RequestParam ("size") Optional<Integer> size) {
		// nếu không n hập giá trị thì sử dụng giá trị ngầm định bằng 1 
		int currentPage = page.orElse(1);
		//giá trị ngầm định bằng 5 phgaanf tử 1 trang
		int pageSize = size.orElse(5);
		//gọi thực hiện phương thức of và truyền vào các đối số như trang và số lượng và cách sắp xêps
		Pageable pageable =PageRequest.of(currentPage - 1,pageSize, Sort.by("name"));
		Page<Product> resultPage = null;
		// nếu thông tin của name được tìm thấy thì chuyền pageable vào phương thức findByNameContaining
		if(StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name,pageable);
			model.addAttribute("name",name);
		}else {
			resultPage = productService.findAll(pageable);
			
		}
		//thực hiện tính số trang trên view 
		int totalPages = resultPage.getTotalPages();
		// nếu tatol lơn hơn 0 thì thwucj hiện tính toán phân trang 
		if(totalPages > 0) {
			int start =Math.max(1, currentPage-2);
			int end = Math.min(currentPage + 2, totalPages);
			// tiếp tục kiểm tra nếu totalPage lớn hơn 5 thì kiểm tra tiếp  end bằng totalpAge thì sẽ tính ngược lại giá trị của start = -5
			//ngược kaij nếu sart bằng 1 thì tính ra end bằng start + 5;
			if(totalPages > 5) {
				if(end == totalPages)start = end - 5;
				else if(start == 1) end = start + 5;
			
		}
			List<Integer> pageNumbers = IntStream.rangeClosed(start , totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers",pageNumbers);
		}
		// trả về kết quả phương thưc
		model.addAttribute("name",name);
		model.addAttribute("productPage",resultPage);
		return "admin/products/searchpaginated";
	}
}
