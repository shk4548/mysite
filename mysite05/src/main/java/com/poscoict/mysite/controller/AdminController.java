package com.poscoict.mysite.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.FileUploadService;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	
	
	@RequestMapping("")
	public String main() {
		SiteVo vo = siteService.getSite();

	//	model.addAttribute("vo",vo);
		
		return "admin/main";	
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	@RequestMapping(value="/main/update", method = RequestMethod.POST)
	public String update(SiteVo siteVo,
			@RequestParam(value = "inputFile") MultipartFile multipartFile) {
		System.out.println(siteVo  + " 흐에에 ");
		siteService.updateSite(siteVo,multipartFile);
		return "redirect:/admin";
	}
}
