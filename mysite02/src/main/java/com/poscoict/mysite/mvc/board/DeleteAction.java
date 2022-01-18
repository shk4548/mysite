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

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath() + "/board", request, response);
			return ;
		}
		
		String num = request.getParameter("no");
		Long no = Long.parseLong(num);
		
		BoardVo vo = new BoardDao().listone(no);
		BoardDao dao = new BoardDao();
		
		
		if (authUser.getNo() == vo.getUserNo()) {
			dao.delete(no);
		}
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
	}

}
