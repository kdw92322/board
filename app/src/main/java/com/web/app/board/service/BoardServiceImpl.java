package com.web.app.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardmapper;
	
	@Override
	public List<Map<String,Object>> selectboardList(Map<String,Object> paramMap) {
		return boardmapper.selectboardList(paramMap);
	}

	@Override
	public int insertBoardList(Map<String, Object> paramMap) throws Exception {
		return boardmapper.insertBoardList(paramMap);
	}

	@Override
	public int updateBoardList(Map<String, Object> paramMap) throws Exception {
		return boardmapper.updateBoardList(paramMap);
	}

	@Override
	public int deleteBoardList(Map<String, Object> paramMap) throws Exception {
		return boardmapper.deleteBoardList(paramMap);
	}
}
