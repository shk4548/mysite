package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	// 새글 , 답글 달기
//	public Boolean addContents(BoardVo vo) {
//		if(vo.getGroupNo() != null	) {
//			increaseGroupOrderNo(vo);
//		}	
//		// return boardRepository.insert(vo);
//		}
//	
	// 글쓰기
	public Boolean addContents(BoardVo vo) {
		return boardRepository.insert(vo);
	}

	// 답글 쓰기
	public Boolean addReply(BoardVo vo) {
		BoardVo boardVo = boardRepository.findByNo(vo.getNo());
		
		boardRepository.updateReply(boardVo.getOrderNo(), boardVo.getGroupNo());
		vo.setGroupNo(boardVo.getGroupNo());
		vo.setOrderNo(boardVo.getOrderNo()+1);
		vo.setDepth(boardVo.getDepth()+1);
		
		return boardRepository.replyWrite(vo);
	}
	// view - 글보기
	public BoardVo getContents(Long no){
		
		BoardVo vo = boardRepository.findByNo(no);
		boardRepository.updateHit(vo.getHit()+1, no);
		
		return vo;
	}
	
	// 글 수정 하기 전,
	public BoardVo getContents(Long no, Long userNo) {
		return boardRepository.findByNo(no);
	}
	
	// 글 수정
	public Boolean updateContents(BoardVo vo) {
		return boardRepository.update(vo);
	}
	
	// 글 삭제
	public Boolean deleteContents(Long no , Long userNo) {
		return boardRepository.delete(no,userNo);
	}
	
	// 전체 글 리스트(찾기랑 같음)
	
	public List<BoardVo> getContentsList(String keyword){
		return boardRepository.search(keyword);
	}
	
	
	
	
	// 검색

//	public Map<String, Object> getContentsList(int currentPage, String keyword) {
//		Map<String, Object> map = new HashMap<>();
//		
//		
//		
//		
//		
//		map.put("list",null);
//		map.put("totalCount", 0);
//		map.put("..", map);
//		
//		return map;
//		
//		
//	}

	
	
}
