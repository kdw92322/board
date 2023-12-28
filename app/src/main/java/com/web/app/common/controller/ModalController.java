package com.web.app.common.controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.board.service.BoardService;
import com.web.app.calendar.service.CalendarService;
import com.web.app.calendar.service.CalendarVO;
import com.web.app.common.service.CommonService;
import com.web.app.webview.service.WebViewService;

/*
	사용할 ModalView 입력 Controller
*/
@Controller
@RequestMapping("modals")
public class ModalController {
	
	@Autowired
	private CalendarService calendarservice;
	
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private WebViewService webviewservice;
	
	@Autowired
	private CommonService commonservice;
	
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
    
    //파일업로드 Modal
    @PostMapping("uploadView")
    public String fileUpload(@RequestBody Map<String, Object> fileObj, Model model) {
    	model.addAttribute("refWord", fileObj.get("refWord"));
    	model.addAttribute("refKey", fileObj.get("refKey"));
    	model.addAttribute("ModalTitle", "파일 업로드 Modal");
    	return "/modal/fileupload";
    }
    
    //게시물 작성 Modal
    @PostMapping("boardModal")
    public String boardModal(@RequestBody Map<String, Object> paramObj, Model model) throws Exception {
    	if(paramObj.get("idx") != null) {
    		Map<String,Object> boardInfo = boardservice.selectboardList(paramObj).get(0);
    		model.addAttribute("idx", boardInfo.get("idx"));
    		model.addAttribute("boardNo", boardInfo.get("boardNo"));
    		model.addAttribute("title", boardInfo.get("title"));
    		model.addAttribute("content", boardInfo.get("content"));
    		model.addAttribute("writer", boardInfo.get("writer"));
    		model.addAttribute("regDate", boardInfo.get("regDate"));
    		model.addAttribute("uptDate", boardInfo.get("uptDate"));
    	}
    	
    	model.addAttribute("ModalTitle", "게시글 정보");
    	return "/board/boardModal";
    }
    
    //게시물 댓글 작성 Modal
    @PostMapping("boardReviewModal")
    public String boardReviewModal(@RequestBody Map<String, Object> paramObj, Model model) throws Exception {
    	System.out.println("paramObj : " + paramObj);
    	
    	List<Map<String,Object>> selectboardList = boardservice.selectboardList(paramObj);
    	Map<String,Object> boardInfo = selectboardList.get(0);
    	String boardNo = String.valueOf(boardInfo.get("boardNo"));
    	model.addAttribute("ModalTitle", "댓글");
    	model.addAttribute("boardNo", boardNo);
    	model.addAttribute("revLevel", String.valueOf(paramObj.get("revLevel")));
    	model.addAttribute("idx", String.valueOf(paramObj.get("idx")));
    	model.addAttribute("parentRevNo", String.valueOf(paramObj.get("parentRevNo")));
    	
    	return "/board/boardReviewModal";
    }
    
    @PostMapping("/menuModal")
	public String webviewMenuSaveModal(@RequestBody Map<String, Object> paramMap, Model model) throws Exception {
    	String type = String.valueOf(paramMap.get("type"));
    	model.addAttribute("type", type);
    	if(type.equals("I")) {
    		//신규
    		model.addAttribute("ModalTitle", "메뉴 신규 Modal");
    	} else if(type.equals("U")) {
    		//수정
    		model.addAttribute("ModalTitle", "메뉴 수정 Modal");
    		if(paramMap.get("menucode") != null) {
        		List<Map<String,Object>> selectMenuList = webviewservice.selectMenuList(paramMap);
        		model.addAttribute("menucode", paramMap.get("menucode"));
            	model.addAttribute("menuname", selectMenuList.get(0).get("menuname"));
            	model.addAttribute("menupath", selectMenuList.get(0).get("menupath"));
            	model.addAttribute("menutype", selectMenuList.get(0).get("menutype"));
            	model.addAttribute("menuorder", selectMenuList.get(0).get("menuorder"));
        	}
    	}
    	
    	return "/admin/webview/menuModal";
	}
    
    @PostMapping("/viewModal")
	public String webviewSaveModal(@RequestBody Map<String, Object> paramMap, Model model) throws Exception {
    	String type = String.valueOf(paramMap.get("type"));
    	model.addAttribute("type", type);
    	model.addAttribute("menucode", paramMap.get("menucode"));
    	if(type.equals("I")) {
    		//신규
    		model.addAttribute("ModalTitle", "화면 신규 Modal");
    	}else if(type.equals("U")) {
    		//수정
    		model.addAttribute("ModalTitle", "화면 수정 Modal");
    		if(paramMap.get("viewcode") != null) {
	    		List<Map<String,Object>> selectViewList = webviewservice.selectViewList(paramMap);
	    	    if(selectViewList.size() > 0) {
	    	    	model.addAttribute("viewcode", paramMap.get("viewcode"));
	    	    	model.addAttribute("path", selectViewList.get(0).get("path"));
	    	        model.addAttribute("level", selectViewList.get(0).get("level"));
	    	        model.addAttribute("useYn", selectViewList.get(0).get("useYn"));
	    	        model.addAttribute("name", selectViewList.get(0).get("name"));
	    	    }
    		}
    	}
    	
    	return "/admin/webview/viewModal";
	}
    
    @PostMapping("/codeMstModal")
	public String codeMstModal(@RequestBody Map<String, Object> paramMap, Model model) throws Exception {
    	String type = String.valueOf(paramMap.get("type"));
    	model.addAttribute("type", type);
    	commonservice.selectCodeMstList(paramMap);
    	if(type.equals("I")) {
    		//신규
    		model.addAttribute("ModalTitle", "대분류(CODE) 신규 Modal");
    	}else if(type.equals("U")) {
    		//수정
    		model.addAttribute("ModalTitle", "대분류(CODE) 수정 Modal");
    		
    		if(paramMap.get("mstCd") != null) {
	    		List<Map<String,Object>> codeMstList = commonservice.selectCodeMstList(paramMap);
	    		if(codeMstList.size() > 0) {
	    	    	Map<String,Object> codeMst = codeMstList.get(0);
	    	    	model.addAttribute("mstCd", paramMap.get("mstCd"));
	    	    	model.addAttribute("mstNm", codeMst.get("mstNm"));
	    	    	model.addAttribute("useYn", codeMst.get("useYn"));
	    	    	model.addAttribute("remark", codeMst.get("remark"));
	    	    	model.addAttribute("attr1", codeMst.get("attr1"));
	    	    	model.addAttribute("attr2", codeMst.get("attr2"));
	    	    	model.addAttribute("attr3", codeMst.get("attr3"));
	    	    }
    		}
    		
    	}
    	return "/admin/code/codeMstModal";
	}
    
    @PostMapping("/codeDtlModal")
	public String codeDtlModal(@RequestBody Map<String, Object> paramMap, Model model) throws Exception {
    	String type = String.valueOf(paramMap.get("type"));
    	model.addAttribute("type", type);
    	commonservice.selectCodeMstList(paramMap);
    	if(type.equals("I")) {
    		//신규
    		model.addAttribute("mstCd", paramMap.get("mstCd"));
    		model.addAttribute("ModalTitle", "소분류(CODE) 신규 Modal");
    	}else if(type.equals("U")) {
    		//수정
    		model.addAttribute("ModalTitle", "소분류(CODE) 수정 Modal");
    		
    		if(paramMap.get("dtlCd") != null) {
	    		List<Map<String,Object>> codeMstList = commonservice.selectCodeMstList(paramMap);
	    		if(codeMstList.size() > 0) {
	    	    	Map<String,Object> codeMst = codeMstList.get(0);
	    	    	model.addAttribute("mstCd", paramMap.get("mstCd"));
	    	    	model.addAttribute("mstNm", codeMst.get("mstNm"));
	    	    	model.addAttribute("useYn", codeMst.get("useYn"));
	    	    	model.addAttribute("remark", codeMst.get("remark"));
	    	    	model.addAttribute("attr1", codeMst.get("attr1"));
	    	    	model.addAttribute("attr2", codeMst.get("attr2"));
	    	    	model.addAttribute("attr3", codeMst.get("attr3"));
	    	    }
    		}
    		
    	}
    	return "/admin/code/codeMstModal";
	}
}
