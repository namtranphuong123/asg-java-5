	package edu.poly.shop.interceptor;
	
	import javax.mail.Session;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;
	import org.springframework.web.servlet.HandlerInterceptor;
	
	@Component
	public class AdminAuthenticationInterceptor implements HandlerInterceptor {
		@Autowired
	 private HttpSession session;
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			//nếu khác null thì người ufngd đã đăng nhập và trả về giá trị true  và cho phép tiếp tục thwucj hiện yêu câif
			if(session.getAttribute("username")!= null) {
				return true;
			} // ngược lại nếu người dùng chưa đăng nhập sẽ thực hiện det astribuate
			session.setAttribute("redirect-uri", request.getRequestURI());
			// sử dụng redirect tới traang log in của phần quản trị và trae về giá trị false không tiếp tục thực hiện các địa chỉ 
			//được yêu câu từ clien và chuyển hướng đến trang đăng nhập
			response.sendRedirect("/alogin");
			return false;
		}
	
	}
