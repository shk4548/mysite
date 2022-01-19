package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ViewAction implements Action {
	private static final int COOKIE_LIFETIME = 10;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		if (no != null) {

			Long num = Long.parseLong(no);

			BoardDao dao = new BoardDao();
			BoardVo vo = dao.findByNo(num);
			request.setAttribute("vo", vo);

			Cookie[] cookies = request.getCookies();
			Cookie viewCookie = null;

			
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (no.equals(cookie.getName())) {
						viewCookie = cookie;
						break;
					}
				}
				if (viewCookie == null) {
					Cookie cookie = new Cookie(no, String.valueOf(vo.getHit() + 1));
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(COOKIE_LIFETIME); // 1minuate
					response.addCookie(cookie);

					dao.updateHit(vo.getHit() + 1, num);
				}	
				MvcUtil.forward("board/view", request, response);
			} else {
				MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			}
		}
	}

}
