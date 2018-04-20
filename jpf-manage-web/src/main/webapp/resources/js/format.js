Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()  
        // millisecond  
    }  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
            .substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
}  
function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
  
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
}

function formatDateStr(value) {
	if (value != null && value != "" && value.length == 14) {
		var year = value.substr(0, 4);
		var month = value.substr(4, 2);
		var day = value.substr(6, 2);
		var hour = value.substr(8, 2);
		var minute = value.substr(10, 2);
		var second = value.substr(12, 2);
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	} else if (value == null || value.length == 0) {
		return "-";
	} else {
		return "无效时间[" + value + "]";
	}
}

function parsePostData(array){
    var postObj = {};
    $.each(array, function(i, n){
        var value=$.trim(n["value"]);
        var name = n["name"];
        if(postObj[name]){
            var val = postObj[name];
            if(typeof(val)=="string"){
                postObj[name]=new Array(val,value);
            }else{
                val.push(value);
                postObj[name] = val;
            }
        }else{
            postObj[name]=value;
        }
    });
    return postObj;
}

function formatPrice(value) {
    if ( String(value).indexOf('.') < 0 )
    {
        // 如果是整数
        return value.toFixed(2);
    }else
    {
        // 如果是小数点后只有一位的小数
        var start = String(value).indexOf('.');
        start += 1;
        var lengthAfterDot = String(value).length - start;
        if ( lengthAfterDot == 1 )
        {
            return value.toFixed(2);
        } else if (lengthAfterDot == 2)
        {
            // 如果是小数点后有两位小数
            return value;
        }else
        {
            console.log('抱歉，价格字段只接受小数点后保留最多两位的数字');
        }
    }

}