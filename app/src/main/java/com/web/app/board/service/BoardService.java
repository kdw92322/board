package com.web.app.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	public List<Map<String,Object>> selectboardList(Map<String,Object> paramMap) throws Exception;
	public int insertBoardList(Map<String,Object> paramMap) throws Exception;
	public int updateBoardList(Map<String,Object> paramMap) throws Exception;
	public int deleteBoardList(Map<String,Object> paramMap) throws Exception;
	
}
