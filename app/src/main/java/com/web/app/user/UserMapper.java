package com.web.app.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	List<Map<String,Object>> selectUserList(Map<String,Object> paramMap);

	int updateUserList(Map<String, Object> paramMap);
	
	int deleteUserList(Map<String, Object> paramMap);
}
