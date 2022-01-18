package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(contents);
				
		BoardDao dao = new BoardDao();
		boolean update = dao.update(vo);
		if(update) {
		MvcUtil.redirect(request.getContextPath()+"/board?a=view&no="+ no, request, response);
		}
	}
}
