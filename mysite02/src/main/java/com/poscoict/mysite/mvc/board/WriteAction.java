package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String No = request.getParameter("userNo");
		Long userNo = Long.parseLong(No);
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(userNo);
		
		boolean insert = dao.insert(vo);
		
		if(insert) {
			MvcUtil.redirect(request.getContextPath()+"/board", request, response);
		}
		
	}

}
