package com.poscoict.mysite.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {
		int count = 0;
		sqlSession.insert("board.insert",vo);
		return count == 1;
	}

	public boolean replyWrite(BoardVo vo) {
		int count = 0;
		sqlSession.insert("board.replyWrite",vo);
		return count == 1;
	}

	public List<BoardVo> search(String keyword) { // listAll ,keywod
		return sqlSession.selectList("board.search",keyword);
	}
		
	public boolean delete(Long no,Long userNo) {
		int count = 0;
		Map<String,Long> map = new HashMap<>();
		map.put("no", no);
		map.put("userNo", userNo);
		
		sqlSession.selectOne("board.delete", map);
		return count == 1;
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public boolean updateHit(Integer hit, Long no) {
		int count = 0;
		Map<String,Object> map = new HashMap<>();
		map.put("hit", hit);
		map.put("no", no);
		
		sqlSession.update("board.updateHit", map);
		return count == 1;
	}

	public boolean updateReply(Integer orderno , Integer groupno) {
		int count = 0;
		Map<String,Integer> map = new HashMap<>();
		map.put("orderno", orderno);
		map.put("groupno", groupno);
		
		sqlSession.update("board.updateReply",map);
		return count == 1;
		
	}


	public boolean update(BoardVo vo) {
		int count = 0;
		sqlSession.update("board.update",vo);
		return count == 1;
	}
}
