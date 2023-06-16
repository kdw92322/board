package com.web.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.calendar.service.CalendarService;

/*
	사용할 ModalView 입력 Controller
*/
@Controller
@RequestMapping("modals")
public class ModalController {
	
	@Autowired
	private CalendarService calendarservice;
	
	@GetMapping("modal1")
    public String modal1() {
        return "modal1";
    }

    @GetMapping("modal2")
    public String modal2(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "modal2";
    }
    
    //일정표 - 상세 View
    @PostMapping("dayInfo")
    public String dayInfo(@RequestBody Map<String, Object> paramObj, Model model) throws Exception {
    	//System.out.println("paramObj : " + paramObj);
    	model.addAttribute("ModalTitle", "일정 Modal");
    	
		if (paramObj.get("id") != null) { /* 기존일정 정보 조회 */
    		String id = String.valueOf(paramObj.get("id"));
			/* System.out.println("id : " + id); */
    		model.addAttribute("id", id);
    		
    		Map<String, Object> dayInfo = calendarservice.selectDayInfo(paramObj);
    		if(dayInfo != null) {
    			//System.out.println("dayInfo : " + dayInfo);
    			
    			model.addAttribute("groupId"        , dayInfo.get("groupId"));
    			model.addAttribute("title"          , dayInfo.get("title"));
    			model.addAttribute("content"        , dayInfo.get("content"));
    			model.addAttribute("startDate"      , dayInfo.get("startDate"));
    			model.addAttribute("startTime"      , dayInfo.get("startTime"));
    			model.addAttribute("endDate"        , dayInfo.get("endDate"));
    			model.addAttribute("endTime"        , dayInfo.get("endTime"));
    			model.addAttribute("allDay"         , dayInfo.get("allDay"));
    			model.addAttribute("textColor"      , dayInfo.get("textColor"));
    			model.addAttribute("backgroundColor", dayInfo.get("backgroundColor"));
    			model.addAttribute("borderColor"    , dayInfo.get("borderColor"));   			
    		} 
		} else { /* 신규일정 */
    		model.addAttribute("startDate", paramObj.get("startDate"));
        	model.addAttribute("endDate", paramObj.get("endDate"));
    	}
    	return "/modal/dayInfo";
    }
    
}
