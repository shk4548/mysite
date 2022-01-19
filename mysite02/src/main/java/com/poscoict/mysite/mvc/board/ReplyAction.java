package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Long no = Long.parseLong(request.getParameter("no"));
	String title = request.getParameter("title");
	String contents = request.getParameter("contents");

	BoardDao dao = new BoardDao();
	BoardVo vo = null;
	
	vo = dao.findByNo(no);
	dao.updateReply(vo.getOrderNo(), vo.getGroupNo());
	
	System.out.println(vo.getGroupNo() + "     " + vo.getOrderNo() + "     " + vo.getDepth());
	
	vo.setTitle(title);
	vo.setContents(contents);
	vo.setGroupNo(vo.getGroupNo());
	vo.setOrderNo(vo.getOrderNo()+1);
	vo.setDepth(vo.getDepth()+1);
	
	boolean insert = dao.replyWrite(vo);
	if(insert) {
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
		System.out.println(vo.getGroupNo() + "     " + vo.getOrderNo() + "     " + vo.getDepth());
	}
		
	}

}

