package com.web.app.file.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Autowired
	private FileMapper filemapper; 

	public List<FileVo> selectfilelist(Map<String, Object> paramMap){
		return filemapper.selectfilelist(paramMap);
		
	}
	
	public void save(List<MultipartFile> files, String uploadPath, String userId, String refWord, String refKey) {
		Map<String, Object> saveFileInfo = new HashMap<String, Object>();
		
		for (int i=0; i<files.size(); i++) {
			MultipartFile file = files.get(i);
			
			String path = uploadPath;
			String content = file.getContentType();
			String originName = file.getOriginalFilename();
			String ext = originName.substring(originName.lastIndexOf("."));
			String fileUUID = UUID.randomUUID().toString().replace("-", "");
			String storedFileName = fileUUID + ext;
			String fullFileName = path + storedFileName;
			long size = file.getSize();
			
			saveFileInfo.put("content", content);
			saveFileInfo.put("originName", originName);
			saveFileInfo.put("ext", ext.replace(".", ""));
			saveFileInfo.put("uuid", fileUUID);
			saveFileInfo.put("storedFile", storedFileName);
			saveFileInfo.put("filePath", fullFileName);
			saveFileInfo.put("size", size);
			saveFileInfo.put("uploadBy", userId);
			saveFileInfo.put("refWord", refWord);
			saveFileInfo.put("refKey", refKey);
			
			//Table에 file정보 insert
			try {
				filemapper.saveInfo(saveFileInfo);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return;
			}
			
			//지정한 경로에 파일 Upload
			File storedfile = new File(fullFileName);
			try {
				file.transferTo(storedfile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}
	
	public int delete(Map<String, Object> paramMap) {
		List<FileVo> fileList = this.selectfilelist(paramMap);
		if(fileList.size() > 0) {
			FileVo fileData = fileList.get(0);
			String filePath = fileData.getFilePath();
			
			//경로에 존재하는 파일 삭제
			File file = new File(filePath);
			if(file.delete()) {
				System.out.println("파일을 삭제하였습니다");
			}else {
				System.out.println("파일 삭제에 실패하였습니다");
			}
		}
		return filemapper.delete(paramMap);
	}
}
