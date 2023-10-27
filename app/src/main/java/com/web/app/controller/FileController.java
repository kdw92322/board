package com.web.app.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.web.app.file.service.FileService;
import com.web.app.file.service.FileVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.io.FileUtils;

@Controller
@RequestMapping("file")
public class FileController {
	
	@Autowired
	private FileService fileservice;
	
	@Value("${spring.servlet.multipart.location}")
    private String uploadPath;
	
	@GetMapping("fileview")
	public String fileview(Model model) {
		return "file/fileview";
	}
	
	@GetMapping("fileList")
	public String fileList(Model model) {
		List<FileVo> selectfilelist = fileservice.selectfilelist(null);
    	model.addAttribute("fileList", selectfilelist);
		return "file/fileList";
	}

	@PostMapping(value = "upload", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	@ResponseBody
	public void FileUpload(MultipartHttpServletRequest request, @RequestHeader(value="userId") String userId) {
		Map<String, List<MultipartFile>> paramMap = request.getMultiFileMap();
		List<MultipartFile> files = paramMap.get("file");
		fileservice.save(files, uploadPath, userId);
	}
	
	@GetMapping("download")
	public ModelAndView FileDownload(HttpServletResponse response, @RequestParam String uuid){
		ModelAndView mav = new ModelAndView();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uuid", uuid);
		List<FileVo> fileList = fileservice.selectfilelist(paramMap);
		
		if(fileList.size() < 1) {
			mav.addObject("result","File Not Found!!");
			mav.addObject("msg", "파일이 없습니다.");
		}else {
			FileVo fileInfo = fileList.get(0);
			String FilePath = fileInfo.getFilePath();
			String FileName = fileInfo.getOriginName();
			byte[] fileByte;
			try {
				fileByte = FileUtils.readFileToByteArray(new File(FilePath));
				response.setContentType("application/octet-stream;charset=UTF-8");
			    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(FileName, "UTF-8")+"\";");
			    response.setHeader("Content-Transfer-Encoding", "binary");

			    response.getOutputStream().write(fileByte);
			    response.getOutputStream().flush();
			    response.getOutputStream().close();	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mav.addObject("result","File Not Found!!");
				mav.addObject("msg", e.toString());
			}		
		}
		
		mav.setViewName("redirect:/file/fileview");
		return mav;
	}
}
