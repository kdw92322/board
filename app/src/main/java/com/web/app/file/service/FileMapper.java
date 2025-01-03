package com.web.app.file.service;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
	public String getfileCommonKey(Map<String, Object> paramMap);	
	public List<Map<String, Object>> selectfileTypeList(Map<String, Object> paramMap);
	public List<FileVo> selectfilelist(Map<String, Object> paramMap);
	public int saveInfo(Map<String, Object> saveMap);
	public int delete(Map<String, Object> saveMap);
}
