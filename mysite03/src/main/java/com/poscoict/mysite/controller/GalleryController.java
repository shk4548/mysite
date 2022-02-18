package com.poscoict.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.FileUploadService;
import com.poscoict.mysite.service.GalleryService;
import com.poscoict.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	FileUploadService fileUploadService;
	
	@Autowired
	GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImages();
		model.addAttribute("list",list);
		
		System.out.println("지금 들어가있는 객체들" + list);
		return "gallery/index";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping(value="delete/{no}",  method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no) { 
		System.out.println("delete" + no);
		galleryService.removeImage(no);
		
		System.out.println("delete:" + no);
		return "redirect:/gallery";
	}
	
	@Auth
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile multipartFile,
						 @RequestParam(value="comments", required=true, defaultValue="") String comments,
						 GalleryVo vo) {
		String url = fileUploadService.restore(multipartFile);	
		vo.setUrl(url);
		vo.setComments(comments);
		
		galleryService.uploadImage(vo);
		System.out.println("comments :" + comments);
		return "redirect:/gallery";
	}
}
