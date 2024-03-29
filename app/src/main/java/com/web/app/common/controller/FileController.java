package com.web.app.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.file.service.FileService;
import com.web.app.file.service.FileVo;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;

import org.springframework.core.io.Resource;

@Controller
@RequestMapping("file")
public class FileController {
	
	@Autowired
	private FileService fileservice;
	
	@Value("${spring.servlet.multipart.location}")
    private String uploadPath;
	
	@GetMapping("fileview")
	public String fileview(@RequestParam Map<String, Object> paramMap, Model model) {
		List<Map<String, Object>> selectfileTypeList = fileservice.selectfileTypeList(paramMap);
		List<String> fileTypeList = new ArrayList<String>();
		
		for(int i=0; i<selectfileTypeList.size(); i++) {
			String fileType = String.valueOf(selectfileTypeList.get(i).get("fileType"));
			fileTypeList.add(fileType);
		}
		
		model.addAttribute("fileTypeList", fileTypeList);
		return "file/fileview";
	}
	
	@GetMapping("getfileCommonKey")
	@ResponseBody
	public String getfileCommonKey(@RequestParam Map<String, Object> paramMap) {
		return fileservice.getfileCommonKey(paramMap);
	}
	
	@GetMapping("fileTypeList")
	public String fileThemeList(@RequestParam Map<String, Object> paramMap, Model model) {
		List<Map<String, Object>> selectfileTypeList = fileservice.selectfileTypeList(paramMap);
    	model.addAttribute("fileTypeList", selectfileTypeList);
		return "file/fileTypeList";
	}
	
	@GetMapping("fileList")
	public String fileList(@RequestParam Map<String, Object> paramMap, Model model) {
		List<FileVo> selectfilelist = fileservice.selectfilelist(paramMap);
    	model.addAttribute("fileList", selectfilelist);
		return "file/fileList";
	}

	@PostMapping(value = "upload", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	@ResponseBody
	public void FileUpload(MultipartHttpServletRequest request, @RequestHeader(value="userId") String userId, @RequestHeader(value="refWord") String refWord, @RequestHeader(value="refKey") String refKey) {
		Map<String, List<MultipartFile>> paramMap = request.getMultiFileMap();
		List<MultipartFile> files = paramMap.get("file");
		System.out.println("refWord : " + refWord);
		System.out.println("refKey : " + refKey);
		fileservice.save(files, uploadPath, userId, refWord, refKey);
	}
	
	@GetMapping("download/{uuid}")
	public ResponseEntity<Resource> FileDownload(@PathVariable String uuid) throws IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uuid", uuid);
		List<FileVo> fileList = fileservice.selectfilelist(paramMap);

		if(fileList.size() < 1) {
			System.out.println("존재하지 않는 파일 입니다.");
			return (ResponseEntity<Resource>) ResponseEntity.status(HttpStatus.NOT_FOUND);
		}else {
			FileVo fileInfo = fileList.get(0);
			String originFileName = fileInfo.getOriginName();
			//Encoding(안해주면 에러남...)
			originFileName = URLEncoder.encode(originFileName, "UTF-8");
			
			File file = new File(fileInfo.getFilePath()); 
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file)); 
			
			return ResponseEntity.ok() 
			      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + originFileName + "\"") 
			      .contentLength(file.length()) 
			      .contentType(MediaType.parseMediaType("application/octet-stream")) 
			      .body(resource); 
		}
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public int deleteBoardList(@RequestBody Map<String, Object> paramMap) throws Exception {
		return fileservice.delete(paramMap);
	}
}
