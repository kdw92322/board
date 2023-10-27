package com.web.app.file.service;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileVo {
	private int rownum;
	private String uuid;
	private String content;
	private String originName;
	private String fileExt;
	private String storedFileNm;
	private String filePath;
	private int size;
	private String uploadBy;
	private Date uploadDt;
}
