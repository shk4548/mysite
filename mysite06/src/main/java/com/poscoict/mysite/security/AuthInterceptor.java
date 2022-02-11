package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Handler Method @Auth 가 없다면 Type에 있는지 확인(과제)
		if(auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			// auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		//5. (4)type과 (3)method에 @Auth 가 적용이 안되어 있는 경우
		if(auth == null) {
			return true;
		}

		//6. @Auth가 적용이 되어 있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 7. 권한 체크를위해 @AUTH의 role 가져오기 ("USER", "ADMIN") 
		String role = auth.role();
		
		// 8 @AUTH 의 role이 "USER인 경우 authUser 의 role은 상관이 없다
		if("USER".equals(role)) {
			return true;
		}
		//9 @Auth 의 role 이 ADMIN인경우 authUser은 ADMIN이여야 한다
		if("ADMIN".equals(authUser.getRole()) == false ) {
				response.sendRedirect(request.getContextPath());
				return false;
			}
		// 10 . 옳은 관리자 @Auth role = "ADMIN"
		// authUser 의 role : "ADMIN"
		return true;
	}

}