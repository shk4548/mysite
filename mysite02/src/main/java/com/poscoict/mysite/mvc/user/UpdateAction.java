package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근 제어 
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null ) {
			MvcUtil.forward("user/loginform", request, response);
			return; // 없으면 여기서 끝내야함 
		}
		

		Long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
	
		UserVo userVo = new UserVo();
		userVo.setNo(no);
		userVo.setName(name);
		userVo.setPassword(password);
		userVo.setGender(gender);		
		
		boolean vo = new UserDao().update(userVo);  // 회원 정보 수정!
		
		if(vo) {
			MvcUtil.redirect(request.getContextPath(), request, response);
		}
	}

}
