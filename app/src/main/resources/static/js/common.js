/*
	Modal 생성
	result : html 호출
*/
var tokenIndex = "Bearer ";

var openModalPop = function(modalId, url, param){
    var rtnMap = new Object();
    $.ajax({
		type:'post',	
	    dataType:"html", //서버에서 html타입을 반환받는다.
	    url: url,
	    async : false,
	    contentType: "application/json; charset=utf-8",
	    data : JSON.stringify(param),
	    success : function(resultHtml) {
	        $(modalId).find('.modal-content').html(resultHtml);
        	$(modalId).modal('show');
        	rtnMap.result = 0;
	    },error : function(xhr, ajaxSettings, thrownError){
			rtnMap.result = 1;
		}
	});
	
	return rtnMap;
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
		rtnStrDate = rtnStrDate.replace("T", " ");
		rtnStrDate = rtnStrDate.trim();
	}
	
	return rtnStrDate;
}

/*
    dayCode를 문자(한글, 영어로 변환)
    code : (0:일, 1:월, 2:화, 3:수, 4:목, 5:금, 6:토)
	lang : 'kr', 'en'
*/
var codeToday = function(code, lang){
	var rtnvalue = "";
	
	code = String(code);
	if(code == "" || code == null || code == undefined){
		console.log("날짜 코드를 입력하세요.(" + code + ") (0:일, 1:월, 2:화, 3:수, 4:목, 5:금, 6:토)");
		return;
	} 
	if(lang == "" || lang == null || lang == undefined){
		console.log("언어 코드를 입력하세요.(" + lang + ") ('kr' : 한글, 'en' : 영어)");
		return;
	}
	
	switch(code){
		case "0" : 
		  rtnvalue = lang == 'kr' ? '일' : 'Sun'; 
		  break;
		case "1" : 
		  rtnvalue = lang == 'kr' ? '월' : 'Mon'; 
		  break;
		case "2" :
		  rtnvalue = lang == 'kr' ? '화' : 'Tue'; 
		  break;
		case "3" :
		  rtnvalue = lang == 'kr' ? '수' : 'Wed'; 
		  break;
		case "4" :
		  rtnvalue = lang == 'kr' ? '목' : 'Thu'; 
		  break;	
		case "5" :
	      rtnvalue = lang == 'kr' ? '금' : 'Fri'; 
		  break;		 
		case "6" :
		  rtnvalue = lang == 'kr' ? '토' : 'Sat'; 
		  break;	 	
	}
	
	return rtnvalue; 
}


/*
    value : serviceParam(Object)
    desc : Service 호출을 위한 paramObject
    Default로 우선 setting
*/
var serviceParam = {
	"type" : "",
	"url" : "",
	"async" : false,
	"dataType" : "",
	"data" : null,
	"isReturnValue" : false,
	"timeout" : 0 ,
	"isLoadingBar" : false
};

/*
    method : callService
    parameter : (type, url, async, dataType, data, isReturnValue, timeout, isLoadingBar)
    desc : Service 호출 function
*/
var callService = function(param){
	var type = param.type;
	var url = param.url;
	var async = param.async;
	var dataType = param.dataType;
	var data = param.data;
	var isReturnValue = param.isReturnValue;
	var timeout = param.timeout;
	var isLoadingBar = param.isLoadingBar;
	
	//Return Object
	var returnObject = new Object();
	
	/* type : GET, POST, PUT, DELETE */
	if(type == null && type == "" && type == undefined){
		type = type.toUpperCase();
		console.log("type is null, parameter order : 1");
		return;
	}
	
	/* Service URL */
	if(url == null && url == "" && url == undefined){
		console.log("url is null, parameter order : 2");
		return;
	} 
	
	/* 동기/비동기 여부(기본 비동기) */
	if(async == null && async == "" && async == undefined){
		console.log("async is null(default : true), parameter order : 3");
		async = true;
	}
	
	/* 서버에서 return 되는 DataType : text, html, xml, json, jsonp, script */
	if(dataType == null && dataType == "" && dataType == undefined){
		console.log("dataType is null(default : text), parameter order : 4");
		dataType = "text";
	}
	
	/* 서버에 전송할 데이터 */
	if(data == null && data == "" && data == undefined){
		console.log("dataType is null(default : {}), parameter order : 5");
		data = new Object();
	}
	
	/* Call Service 후 Return 여부 */
	if(isReturnValue == null && isReturnValue == "" && isReturnValue == undefined){
		console.log("isReturnValue is null(default : true), parameter order : 6");
		isReturnValue = false;//default
	}
	
	/* 타임아웃 설정 */
	if(timeout == null && timeout == "" && timeout == undefined){
		console.log("timeout is null(default : 0), parameter order : 7");
		timeout = 0;//default
	}
	
	/* loading Bar 표시여부 */
	if(isLoadingBar == null && isLoadingBar == "" && isLoadingBar == undefined){
		console.log("isLoadingBar is null(default : 0), parameter order : 8");
		isLoadingBar = false;
	}
	
	let ContentType = 'application/x-www-form-urlencoded; charset=utf-8';
	if(dataType == "json") {
		ContentType = 'application/json; charset=utf-8';
	}
	
	$.ajax({
		type: type,
		url: url,
		async: async,
		dataType: dataType,
		data: data,
		contentType: ContentType,
		timeout : timeout,
		beforeSend:function(xhr){
			xhr.setRequestHeader("Authorization", tokenIndex + localStorage.getItem("access-Token"));
			if(isLoadingBar){
				$('.layout_loader').show(); // loading bar show	
			}
	    },
	    complete:function(){
			setTimeout(() => {
				if(isLoadingBar){
					$('.layout_loader').hide(); // loading bar hide	
				}		
			}, 1000);
	    },
	})
	.done(function(data, textStatus, xhr){
		if(isReturnValue){
			returnObject = {
				"value" : data,
				"Status" : textStatus,
				"xhr" : xhr
			};
		}
	})
	.fail(function(xhr, textStatus, errorThrown){
		if(isReturnValue){
			returnObject = {
				"xhr" : xhr,
				"Status" : textStatus,
				"Thrown" : errorThrown,
			};
		}
	});
	
	//common service Object value 초기화
	for(let key of Object.keys(serviceParam)){
		serviceParam[key] = "";
	}

	return returnObject;
}

//파일관리 파일 Upload시 COMMON에 대한 Key 조회
var getfileCommonKey = function(){
	serviceParam.type = "GET";
	serviceParam.url = "/file/getfileCommonKey";
	serviceParam.async = false;
	serviceParam.dataType = "text";
	serviceParam.data = {"refWord" : "COMMON"};
	serviceParam.isReturnValue = true;
	serviceParam.timeout = 0;
	serviceParam.isLoadingBar = false;
	var result = callService(serviceParam);
	var returnValue = result.value;
	return returnValue;
}

/*
var getJwtTokenKey = function(){
	var userId = $("#loginUserId").val();
	var token = null;
	
	$.ajax({
		type: "get",
		url: "/jwt/selectJwtToken",
		data:{"userId" : "ADMIN"},
		async: false,
		dataType : "json",
		contentType: "application/json; charset=utf-8",
		success: function(data, textStatus, xhr){
			//console.log(data);
			token = data["token"];
		}
	});
	
	return token;
}
*/
