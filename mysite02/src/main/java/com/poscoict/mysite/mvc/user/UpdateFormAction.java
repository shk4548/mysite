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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근제어 (Acess Control)
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) { // 로그인을 안한 상태
			MvcUtil.redirect(request.getContextPath() + "/user?a=loginform", request, response);
			return; // 여기서끝내야함
		}
		
		
		UserVo vo = new UserDao().findByNo(authUser.getNo());
		request.setAttribute("userVo",  vo);
		MvcUtil.forward("user/updateform", request, response);
	}

}
