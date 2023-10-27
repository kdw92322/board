package com.web.app.screen.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebViewMapper {
	List<Map<String,Object>> selectViewList(Map<String,Object> paramMap);
	List<Map<String,Object>> selectViewAuthList(Map<String,Object> paramMap);
}
