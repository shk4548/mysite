package com.poscoict.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(SiteVo vo) {
		System.out.println(vo.getTitle() + "        " + vo.getWelcome() + "        " + vo.getDescription());
		return sqlSession.update("site.update",vo)==1;
	}
	
	public SiteVo view() {
		
		return sqlSession.selectOne("site.view");
	}
	
}
