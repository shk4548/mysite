package com.poscoict.mysite.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
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
	@Auth
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no")Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo",vo);
		return "board/view";
	}
	
	//글쓰기로 forward
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String insert() {
		return "board/write";
	}
	
	// 글쓰기
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String insert(@AuthUser UserVo authUser,BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardService.addContents(vo);
		return "redirect:/board";
	}
	
	// 삭제
	@Auth
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@AuthUser UserVo authUser,@PathVariable("no") Long no, Long userNo) {
		
		boardService.deleteContents(no, authUser.getNo());
		
		return "redirect:/board";
	}
	
	// 수정으로 forward
	@Auth
	@RequestMapping(value="/update/{no}", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, @PathVariable("no")Long no, Long userNo,
			Model model) {

		BoardVo vo = boardService.getContents(no, authUser.getNo());
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	// 수정
	@Auth
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardService.updateContents(vo);
		return "redirect:/board/view/"+vo.getNo();
	}
	
	
	// 답글작성으로 forward
	@Auth
	@RequestMapping(value="reply/{no}", method = RequestMethod.GET)
	public String reply(@PathVariable("no")Long no,Model model) {
		
		BoardVo vo  = boardService.getContents(no);
		model.addAttribute("reply",vo);
		return "board/reply";
	}
	

	// 답글작성
	@Auth
	@RequestMapping(value="reply", method = RequestMethod.POST)
	public String reply(@AuthUser UserVo authUser, BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardService.addReply(vo);

		return "redirect:/board";
	}
	

}


