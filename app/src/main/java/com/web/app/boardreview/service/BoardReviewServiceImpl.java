package com.web.app.boardreview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardReviewServiceImpl implements BoardReviewService{
	
	@Autowired
	private BoardReviewMapper boardreviewmapper;
	
	@Override
	public List<Map<String,Object>> selectreviewList(Map<String,Object> paramMap) {
		List<Map<String,Object>> saveReviewList = boardreviewmapper.selectreviewList(paramMap);
		for(int i=0; i<saveReviewList.size(); i++) {
			//Blob -> String 변환
			byte[] temp = (byte[]) saveReviewList.get(i).get("revTxt");
			if(temp != null) {
				String strRevTxt = new String(temp);
				saveReviewList.get(i).put("revTxt", strRevTxt);
			}
		}
		return saveReviewList;
	}

	@Override
	public int insertBoardReview(Map<String, Object> saveMap) throws Exception {
		return boardreviewmapper.insertBoardReview(saveMap);
	}

	@Override
	public int updateBoardReview(Map<String, Object> saveMap) throws Exception {
		return boardreviewmapper.updateBoardReview(saveMap);
	}
	
	@Override
	public int updateReviewLike(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return boardreviewmapper.updateReviewLike(saveMap);
	}

	@Override
	public int updateReviewHate(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return boardreviewmapper.updateReviewHate(saveMap);
	}

	@Override
	public void deleteBoardReview(Map<String, Object> saveMap) throws Exception {
		boardreviewmapper.deleteBoardReview(saveMap);	
		boardreviewmapper.deleteReviewLog(saveMap);
	}

	@Override
	public int insertLikeAndHateLog(Map<String, Object> saveMap) throws Exception {
		// TODO Auto-generated method stub
		return boardreviewmapper.insertLikeAndHateLog(saveMap);
	}
}
