package com.poscoict.mysite.service;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private ServletContext servletContext;

	// 보기
	public SiteVo getSite() {
		SiteVo vo = siteRepository.view();
		
		return vo;
	}
	
	// 업데이트
	public Boolean updateSite(SiteVo siteVo) {
		servletContext.setAttribute("siteVo", siteVo);
		return siteRepository.update(siteVo);
	}
}
