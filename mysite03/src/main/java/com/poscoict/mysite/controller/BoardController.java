package com.poscoict.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.service.BoardService;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	// 전체리스트
	@RequestMapping(value="")
	public String index(Model model,
			@RequestParam(value="keyword", required=true, defaultValue="") String keyword) {
		List<BoardVo> list =boardService.getContentsList(keyword);
		model.addAttribute("list",list);
		return "board/list";
	}
	// view - 글보기
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no")Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo",vo);
		return "board/view";
	}
	//글쓰기로 forward
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String insert() {
		return "board/write";
	}
	// 글쓰기
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String insert(HttpSession session,BoardVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		vo.setUserNo(authUser.getNo());
		
		boardService.addContents(vo);
		return "redirect:/board";
	}
	// 삭제
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(HttpSession session,@PathVariable("no") Long no, Long userNo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		boardService.deleteContents(no, authUser.getNo());
		
		return "redirect:/board";
	}
	
	// 수정으로 forward
	@RequestMapping(value="/update/{no}", method=RequestMethod.GET)
	public String update(HttpSession session, @PathVariable("no")Long no, Long userNo,
			Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		BoardVo vo = boardService.getContents(no, authUser.getNo());
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	// 수정
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		vo.setUserNo(authUser.getNo());
		boardService.updateContents(vo);
		return "redirect:/board/view/"+vo.getNo();
	}
	
	
	// 답글작성으로 forward
	@RequestMapping(value="reply/{no}", method = RequestMethod.GET)
	public String reply(@PathVariable("no")Long no,Model model) {
		
		BoardVo vo  = boardService.getContents(no);
		model.addAttribute("reply",vo);
		return "board/reply";
	}
	

	// 답글작성
	@RequestMapping(value="reply", method = RequestMethod.POST)
	public String reply(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		vo.setUserNo(authUser.getNo());
		boardService.addReply(vo);

		return "redirect:/board";
	}
	

}


