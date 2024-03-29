package com.web.app.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	List<Map<String,Object>> selectUserList(Map<String,Object> paramMap);
	
	int chkDupUserCnt(Map<String,Object> paramMap);
	
	UserForm selectUserInfo(Map<String,Object> paramMap);
	
	int deleteUser(Map<String,Object> paramMap);
	
	List<Map<String,Object>> getUserLog(Map<String,Object> paramMap);
	
	List<Map<String,Object>> getConnUserLogData1(Map<String,Object> paramMap);
	List<Map<String,Object>> getConnUserLogData2(Map<String,Object> paramMap);
}
