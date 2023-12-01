package com.web.app.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	List<Map<String,Object>> selectUserList(Map<String,Object> paramMap);

	UserForm selectUserInfo(Map<String,Object> paramMap);
	
	int deleteUser(Map<String,Object> paramMap);
	
	//접속시마다 로그인/아웃 이력 저장
	int saveUserLog(Map<String,Object> saveMap);
	
	List<Map<String,Object>> getUserLog(Map<String,Object> paramMap);
	
	List<Map<String,Object>> getConnUserLogData(Map<String,Object> paramMap);
}
