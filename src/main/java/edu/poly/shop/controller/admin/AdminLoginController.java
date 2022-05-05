package edu.poly.shop.controller.admin;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.service.AccountService;

@Controller

public class AdminLoginController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;

	@GetMapping("alogin")
	public String login(Model model) {
		model.addAttribute("account", new AdminLoginDto());
		return "/admin/accounts/login";

	}

	@PostMapping("alogin")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") AdminLoginDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/accounts/login", model);
		} // newwu su lys thanh cong thi tao ra doi tuong account vaf dung accservice . va
			// login
		Account account = accountService.login(dto.getUsername(), dto.getPassword());
		// kieem tra account bangf null thi that baij login
		if (account == null) {

			model.addAttribute("message", "InVaid username or password");

			return new ModelAndView("/admin/accounts/login", model);

		}

		// sau khi đăng nhập thành công thì sẽ lấy thông tin của người ùng đã đăng nhập

		session.setAttribute("username", account.getUsername());

		Object ruri = session.getAttribute("redirect-uri");
		// và kiểm tra mếu rủlr kahcs null có nghĩa là đã có giữ liệu đăng nhập thì sẽ
		// thiwcj hiện vào đường dẫn đã yêu cầu nếu chưa thì yêu cầu login
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		// ngược lại thì sẽ thực hiện đường dẫn ngầm định sẵn forward:/admin/products"

		// vaf trar ve trang minh muon
		return new ModelAndView("forward:/admin/products", model);
	}

	@GetMapping("logout")
	public ModelAndView logout(ModelMap model) {
		session.removeAttribute("username");
		return  new ModelAndView ("redirect:/all/index", model);
	
	}

}
