package com.poscoict.mysite.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private  SqlSession sqlSession;
	
	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}
	
	public boolean delete(Long no, String password) {
		int count = 0;
		Map<String,Object> map = new HashMap<>();
		map.put("no", no);
		map.put("p", password);
		
		sqlSession.selectOne("guestbook.delete", map);
		return count == 1;
	}
	
	public int insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert",vo);
	}
	

}