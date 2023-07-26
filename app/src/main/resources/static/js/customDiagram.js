/**
 * sampleData(JSON Format)
 */
function getSampleData(setId){
	var dataObj = { 
		"class": "go.GraphLinksModel",
		"nodeDataArray": [
				/*대분류*/
		  		{"key":1 , "isGroup":true, "text":"Proposal &\nQuotation" , "horiz":false},
				{"key":2 , "isGroup":true, "text":"Project &\nManagement" , "horiz":false},
				{"key":3 , "isGroup":true, "text":"Engineering" , "horiz":false},
				{"key":4 , "isGroup":true, "text":"Procurement" , "horiz":false},
				{"key":5 , "isGroup":true, "text":"Inventory" , "horiz":false},
				{"key":10, "isGroup":true, "text":"수주계획관리", "group":1}, /*중분류*/
				{"key":11, "isGroup":true, "text":"입찰준비", "group":1},
				{"key":12, "isGroup":true, "text":"견적원가 산출", "group":1},
				{"key":13, "isGroup":true, "text":"입찰", "group":1},
				{"key":14, "isGroup":true, "text":"계약", "group":1},
				{"key":15, "isGroup":true, "text":"미확정 PJT/\n경영계획 수립", "group":1},	
				{"key":16, "isGroup":true, "text":"PJT 추진계획 수립", "group":2}, /*중분류*/
				{"key":17, "isGroup":true, "text":"일정 편성", "group":2},
				{"key":18, "isGroup":true, "text":"목표예산편성", "group":2},
				{"key":19, "isGroup":true, "text":"PJT 진도관리", "group":2},
				{"key":20, "isGroup":true, "text":"실행 PJT 전망관리", "group":2},
				{"key":21, "isGroup":true, "text":"정산", "group":2},	
				{"key":22, "isGroup":true, "text":"견적수행", "group":3},/*중분류*/
				{"key":23, "isGroup":true, "text":"기본도면작성/변경", "group":3},
				{"key":24, "isGroup":true, "text":"상세도면작성/변경", "group":3},
				{"key":25, "isGroup":true, "text":"도면관리", "group":3},
				{"key":26, "isGroup":true, "text":"설계변경 관리", "group":3},
				{"key":27, "isGroup":true, "text":"자재수급관리", "group":3},
				{"key":28, "isGroup":true, "text":"부가정보관리", "group":3},
				{"key":29, "isGroup":true, "text":"PD", "group":3},
				{"key":30, "isGroup":true, "text":"MDS", "group":3},
				{"key":31, "isGroup":true, "text":"PR관리", "group":3},
				{"key":32, "isGroup":true, "text":"견적", "group":4}, /*중분류*/
				{"key":33, "isGroup":true, "text":"구매발주", "group":4},
				{"key":34, "isGroup":true, "text":"구매사후관리", "group":4},
				{"key":35, "isGroup":true, "text":"대금지불", "group":4},
				{"key":36, "isGroup":true, "text":"공급사관리", "group":4},	
				{"key":37, "isGroup":true, "text":"입고", "group":5}, /*중분류*/
				{"key":38, "isGroup":true, "text":"출고", "group":5},
				{"key":39, "isGroup":true, "text":"재고", "group":5},	
				
				{"text":"Data01", "group":10, "key":40}, /*중분류*/
                {"text":"Data02", "group":11, "key":41},
                {"text":"Data03", "group":12, "key":42},
                {"text":"Data04", "group":13, "key":43},
                {"text":"Data05", "group":14, "key":44},
                {"text":"Data06", "group":15, "key":45},
                {"text":"Data07", "group":16, "key":46},
                {"text":"Data08", "group":17, "key":47},
                {"text":"Data09", "group":18, "key":48},
                {"text":"Data10", "group":19, "key":49},
                {"text":"Data11", "group":20, "key":50},
                {"text":"Data12", "group":21, "key":51},
                {"text":"Data13", "group":22, "key":52},
                {"text":"Data14", "group":23, "key":53},
                {"text":"Data15", "group":24, "key":54},
                {"text":"Data16", "group":25, "key":55},
                {"text":"Data17", "group":26, "key":56},
                {"text":"Data18", "group":27, "key":57},
                {"text":"Data19", "group":28, "key":58},
                {"text":"Data20", "group":29, "key":59},
                {"text":"Data21", "group":30, "key":60},
                {"text":"Data22", "group":31, "key":61},
                {"text":"Data23", "group":32, "key":62},
                {"text":"Data24", "group":33, "key":63},
                {"text":"Data25", "group":34, "key":64},
                {"text":"Data26", "group":35, "key":65},
                {"text":"Data27", "group":36, "key":66},
                {"text":"Data28", "group":37, "key":67},
                {"text":"Data29", "group":38, "key":68},
                {"text":"Data30", "group":39, "key":69}				
		],
	    "linkDataArray": []
	 }
	 //console.log("dataObj", JSON.stringify(dataObj));
	 //console.log("setId", setId);
	 
	 var strDataObj = JSON.stringify(dataObj);
	 
	 document.getElementById(setId).value = strDataObj;
}