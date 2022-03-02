package com.poscoict.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;

@RestController("/guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService; 
	
	@GetMapping("/list")
	public JsonResult read(@RequestParam(value="sn", required=true, 
		defaultValue="-1") Long no) {
		GuestbookVo vo = new GuestbookVo();
		
		List<GuestbookVo> list = guestbookService.getMessageList1(no);
		System.out.println(list);
		return JsonResult.success(list);
	}
	
	@PostMapping("/add")
	public JsonResult create(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		vo.setPassword("");
		
		
		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/delete/{no}")
	public JsonResult delete(@PathVariable("no") Long no,
			@RequestParam(value="password", required=true, defaultValue="") String password) {
		boolean result = guestbookService.deleteMessage(no, password);
		
		System.out.println("no : " + no);
		System.out.println("password : " + password);
		
		Long data = 0L;
		if(!result) {
		//1. 삭제가 안된 경우
		data = -1L;
		} else {
		//2. 삭제가 된 경우
		data = no;}
	
		return JsonResult.success(data);
	}
}