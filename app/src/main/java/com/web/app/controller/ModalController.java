package com.web.app.controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import com.web.app.calendar.service.CalendarVO;

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
			//System.out.println("id : " + id);
    		model.addAttribute("id", id);
 
    		List<CalendarVO> dayList = calendarservice.selectCalendarList(paramObj);
    		CalendarVO dayInfo = (dayList.size() != 0) ? dayList.get(0) : null;
    		if(dayInfo != null) {
    			model.addAttribute("groupId"        , dayInfo.getGroupId());
    			model.addAttribute("title"          , dayInfo.getTitle());
    			model.addAttribute("content"        , dayInfo.getContent());
    			model.addAttribute("startDate"      , dayInfo.getStartDt());
    			model.addAttribute("endDate"        , dayInfo.getEndDt());
    			model.addAttribute("startTime"      , dayInfo.getStartTi());
    			model.addAttribute("endTime"        , dayInfo.getEndTi());
    			model.addAttribute("allDay"         , dayInfo.getAllDay());
    			model.addAttribute("textColor"      , dayInfo.getTextColor());
    			model.addAttribute("backgroundColor", dayInfo.getBackgroundColor());
    			model.addAttribute("borderColor"    , dayInfo.getBorderColor());	
    		} 
		} else { /* 신규일정 */
			//Default 오늘날짜
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strNow = sdf.format(now);
			
			model.addAttribute("allDay", true);
    		model.addAttribute("start", strNow);
        	model.addAttribute("end", strNow);
    	}
    	return "/modal/dayInfo";
    }
    
}
