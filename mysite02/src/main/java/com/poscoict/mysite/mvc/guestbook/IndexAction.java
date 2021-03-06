package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.GuestbookDao;
import com.poscoict.mysite.vo.GuestbookVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> list = dao.findAll();
		request.setAttribute("list", list);
		MvcUtil.forward("guestbook/index", request, response);
	}
	

}