package su.spring.web.login;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InterceptorLogin implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession( ).getAttribute("admin") == null) { // 루트 경로로 리다이렉트한다.
				response.sendRedirect("/");
				return false; 
			}
			return true; 
		}
}
	

