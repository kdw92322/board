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
}
