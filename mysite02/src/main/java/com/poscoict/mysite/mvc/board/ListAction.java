package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int pageCount = 10;
//		int currentPage = 2;
//		int nextPage = -1;
//		int startPage = 3;
//		int prePage = 2;
//		
//		Map m;
//		m.put();
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.findall();
		request.setAttribute("list", list);
		
		MvcUtil.forward("board/list", request, response);
	}

}
