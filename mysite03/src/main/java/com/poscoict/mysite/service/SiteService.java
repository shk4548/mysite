package com.poscoict.mysite.service;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FileUploadService fileUploadService;
	

	// 보기
	public SiteVo getSite() {
		SiteVo vo = siteRepository.view();
		return vo;
	}
	
	// 업데이트
	public Boolean updateSite(SiteVo siteVo,
			@RequestParam(value = "inputFile") MultipartFile multipartFile) {
		
		String url = fileUploadService.restore(multipartFile);
		siteVo.setProfile(url);
		
		servletContext.setAttribute("siteVo", siteVo);
		
		System.out.println(siteVo);
		return siteRepository.update(siteVo);
	}
}
