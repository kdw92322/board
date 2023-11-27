package com.web.app.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	List<Map<String,Object>> selectboardList(Map<String,Object> paramMap);
	
	List<Map<String,Object>> selectreviewList(Map<String,Object> paramMap);
	
	int insertBoardList(Map<String, Object> paramMap);

	int updateBoardList(Map<String, Object> paramMap);
	
	int deleteBoardList(Map<String, Object> paramMap);
}
