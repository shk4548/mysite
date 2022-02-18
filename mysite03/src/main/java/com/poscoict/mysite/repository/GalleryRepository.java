package com.poscoict.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	// getImages, upload, delete
	public List<GalleryVo> getImages() { 
		return sqlSession.selectList("gallery.getImages");
	}
	
	public boolean delete(Long no) {
		int count = 0;
		sqlSession.delete("gallery.delete",no);
		return count == 1;
	}
	
	public boolean upload(GalleryVo vo) {
		int count = 0;
		sqlSession.insert("gallery.upload", vo);
		return count == 1;
	}
}
