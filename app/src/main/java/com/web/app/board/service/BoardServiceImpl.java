package com.web.app.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mysql.cj.exceptions.ExceptionInterceptor;
import com.mysql.cj.jdbc.Blob;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	
	@Autowired
	private BoardMapper boardmapper;
	
	@Override
	public List<Map<String,Object>> selectboardList(Map<String,Object> paramMap) {
		
		BoundSql boundSql = sqlSession.getConfiguration().getMappedStatement("selectboardList").getBoundSql(paramMap); 
		String query = boundSql.getSql();
		
		Gson gson = new Gson();
		
		List<ParameterMapping> parameterMapping = boundSql.getParameterMappings();
		for(ParameterMapping pm : parameterMapping){
			 //pm.getProperty() >> "param.session.userid"와 같이 나옵니다.
			 //param.session.userid 을 .으로 split(쪼개면)합니다. tParam.length=2
			 String[] tParam = pm.getProperty().split("\\.");
			 Object tValue = paramMap; //오브젝트에 param대입
			 //쪼개진 tParam[]을 순차적으로 돌면서 tValue를 얻고자 합니다.
			 for(int i=0, il=tParam.length; i<il; i++){
			  try{
			   //toJson으로 텍스트형으로 바꾸고, 다시 HashMap 형태로 fromJson 해줬습니다.
			   tValue = gson.fromJson(gson.toJson(((Map<String,Object>) tValue).get(tParam[i])), HashMap.class);
			  }catch(Exception e){
			   tValue = (String) ((Map<String,Object>) tValue).get(tParam[i]); //마지막의 String이 어갈 겁니다.
			  }
			 }
			 //구해진 tValue를 query에다 넣어 줍니다.
			 query = query.replaceFirst("\\?", "'"+ tValue +"'");
		}
		System.out.println("query : " + query);	  
		
		return boardmapper.selectboardList(paramMap);
	}

	@Override
	public List<Map<String,Object>> selectreviewList(Map<String,Object> paramMap) {
		List<Map<String,Object>> selectreviewList = boardmapper.selectreviewList(paramMap);
		for(int i=0; i<selectreviewList.size(); i++) {
			Map<String, Object> review = selectreviewList.get(i);
			byte[] temp = (byte[]) review.get("revTxt");
			
			String strRevTxt = new String(temp);
			selectreviewList.get(i).put("revTxt", strRevTxt);
		}
		return selectreviewList;
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
