package com.web.app.boardreview.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardReviewMapper {
	List<Map<String,Object>> selectreviewList(Map<String,Object> paramMap);
	
	int insertBoardReview(Map<String, Object> saveMap);

	int updateBoardReview(Map<String, Object> saveMap);
	int updateReviewLike(Map<String,Object> saveMap);
	int updateReviewHate(Map<String,Object> saveMap);
	
	void deleteBoardReview(Map<String, Object> saveMap);
	void deleteReviewLog(Map<String, Object> saveMap);
	
	//리뷰에 대한 로그
	int insertLikeAndHateLog(Map<String, Object> saveMap);
}
