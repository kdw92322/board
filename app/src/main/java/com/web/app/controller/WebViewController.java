package com.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("webview")
public class WebViewController {
	
	@GetMapping("webviewInfo")
    public String webviewInfo(Model model) {
        return "webview/webviewInfo";
    }
}
