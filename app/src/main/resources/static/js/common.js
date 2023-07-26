/*
	Modal Popup생성
	result : html 호출
*/
var openModalPop = function(modalId, url, param){
    $.ajax({
		type:'post',	
	    dataType:"html", //서버에서 html타입을 반환받는다.
	    url: url,
	    contentType: "application/json; charset=utf-8",
	    data : JSON.stringify(param),
	    success : function(resultHtml) {
	        $(modalId).find('.modal-content').html(resultHtml);
        	$(modalId).modal('show');
	    },
	});    
};

/*
	form 데이터를 Object로 변환
	name속성이 존재하는 tag의 값만 가져옴
*/
jQuery.fn.serializeObject = function() {
    var obj = new Object;
    try {
        if ( this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
            var arr = this.serializeArray();
            if ( arr ) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });            
            }
        }
    }catch(e) {
		alert(e.message);
	}finally  {
		
	}
     
    return obj;
};

var initDateTimePicker = function(id, formatType, optionObj){
	//defaultOption
	var defaultOption = {
		lang   : "kr",           
		format : 'Y-m-d H:i',
		step   : '30',
		weeks  : true,          
		todayButton : true    
	};
	
	if(formatType == "D"){
		defaultOption.format = 'Y-m-d';
		defaultOption.timepicker = false;
	}else if(formatType == "T"){
		defaultOption.format = 'H:i';
		defaultOption.datepicker = false;
	};
	
	if(optionObj != null && optionObj != undefined){
		var keys = Object.keys(optionObj);
		if(keys.length == 0){ optionObj = defaultOption; }	
	}else {
		optionObj = defaultOption;
	}
	
	$("#"+id).datetimepicker(optionObj);	
}

/*
    문자열의 모든 searchStr에 대해 replaceStr로 대체
*/
var replaceAll = function(str, searchStr, replaceStr) {
   return str.split(searchStr).join(replaceStr);
}

/*
    Date to String(format : yyyy-mm-dd hh:mi:ss)
*/
var DateToStringFormat = function(date){
	var rtnStrDate = "";
	if(typeof date == 'object'){
		date.setHours(date.getHours() + 9); //한국 시간대로 setting
		rtnStrDate = date.toISOString().substring(0, 19);
	}
	return rtnStrDate;
}
