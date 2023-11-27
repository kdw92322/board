package com.web.app.boardreview.service;

import java.util.List;
import java.util.Map;

public interface BoardReviewService {
	List<Map<String, Object>> selectreviewList(Map<String, Object> paramMap);
	public int insertBoardReview(Map<String,Object> saveMap) throws Exception;
	public int updateBoardReview(Map<String,Object> saveMap) throws Exception;
	
	public void deleteBoardReview(Map<String,Object> saveMap) throws Exception;
	public int updateReviewLike(Map<String, Object> saveMap) throws Exception;
	public int updateReviewHate(Map<String, Object> saveMap) throws Exception;
	
	public int insertLikeAndHateLog(Map<String,Object> saveMap) throws Exception;
}
