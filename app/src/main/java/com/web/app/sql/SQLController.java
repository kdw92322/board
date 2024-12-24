package com.web.app.sql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sql")
public class SQLController {
	
	@GetMapping(path = "/dml")
	public String manageDml() {
		return "/sql/dml";
	}
	
}
