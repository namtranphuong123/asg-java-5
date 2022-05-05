package edu.poly.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.poly.shop.interceptor.AdminAuthenticationInterceptor;
import edu.poly.shop.interceptor.UserAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer{
@Autowired
private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
@Autowired
private UserAuthenticationInterceptor userAuthenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationInterceptor)
		// khi thực hiện cá yêu cầu có admin thì phải gọi adminAuthenticationInterceptor để kiểm tra
		.addPathPatterns("/admin/**")
		.excludePathPatterns("/user/**"); 
		
		
		registry.addInterceptor(userAuthenticationInterceptor)
		// khi thực hiện cá yêu cầu có admin thì phải gọi adminAuthenticationInterceptor để kiểm tra
		.addPathPatterns("/user/**")
		.excludePathPatterns("/admin/**");
		
		
	}
	
// lớp chưa các mô tả để cho phép kiểm tra xem người dùng đã đăng nhập hay chưa và yêu cầu nười dùng đang nhập trươc khi muốn thao tác 
	
}
