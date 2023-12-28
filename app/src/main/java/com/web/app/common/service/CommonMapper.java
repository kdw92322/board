package com.web.app.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
	List<Map<String, Object>> topMenuList();
	List<Map<String, Object>> topViewList(Map<String, Object> param);
	List<Map<String, Object>> selectCodeMstList(Map<String, Object> param);
	int insertCodeMst(Map<String, Object> saveMap);
	int updateCodeMst(Map<String, Object> saveMap);
	int deleteCodeMst(Map<String, Object> saveMap);
	List<Map<String, Object>> selectCodeDtlList(Map<String, Object> param);
}
