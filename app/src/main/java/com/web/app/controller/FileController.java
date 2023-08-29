package com.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("file")
public class FileController {
	@GetMapping("fileview")
	public String fileview() {
		return "file/fileview";
	}
}
