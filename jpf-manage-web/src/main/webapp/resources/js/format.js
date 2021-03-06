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

function formatJSON(value) {
    if ( value !== '' ){
        var str = '';
        JSON.parse(value,function(k, v){
            if ( k !== '' && typeof(v) !== 'object' ){
                switch (k){
                    case 'payType':
                        k = '支付类型ID';
                        break;

                    case 'payType_cn':
                        k = '支付类型';
                        str += k+'：<span style="color: #777">'+v+'</span>;  ';
                        break;

                    case 'stageType':
                        k = '分期类型ID';
                        break;

                    case 'stageType_cn':
                        k = '分期类型';
                        str += k+'：<span style="color: #777">'+v+'</span>;  ';
                        break;

                    case 'payMoneyPerTerm':
                        k = '每期支付';
                        break;

                    case 'serviceFeePerTerm':
                        k = '每期服务费';
                        break;

                    case 'feePerMonth':
                        k = '每月费率';
                        break;

                    case 'uid':
                        k = '审核者uid';
                        break;

                    case 'username':
                        k = '审核者';
                        break;

                    case 'content':
                        k = '驳回原因';
                        break;

                    case 'date':
                        k = '日期';
                        break;
                }
            }
        })
        return str;
    }
}

function formatJSONOpCon(value) {
    if ( value !== '' && value !== undefined ){
        value = $.parseJSON(value);
        var resStr = '';
        $.each(value,function (key, val) {
            var num = key+1;
            resStr += '<p>'+num+'：';
            var valStr = JSON.stringify(val);
            var valJSON = $.parseJSON(valStr);
            $.each(valJSON,function (k, v) {
                switch (k){
                    case 'uid':
                        k = '审核者uid';
                        break;

                    case 'username':
                        k = '审核者';
                        break;

                    case 'content':
                        k = '内容';
                        break;

                    case '审核时间':
                        k = '日期';
                        break;

                    case 'applyTime':
                        k = '用户申请时间';
                        break;
                }
                resStr += k+':<span style="color: #777">'+v+'</span>;  &nbsp;&nbsp;';
            })
            resStr += '</p>';
        })

        return resStr;
    }
}


function formatJSONImg(value) {
    if ( value !== '' && value !== undefined ){
        value = $.parseJSON(value);
        var resStr = '';
        value.forEach(function(val, index, k){
            resStr += index+1+'：';
            var valStr = JSON.stringify(val);
            var valJSON = $.parseJSON(valStr);
            $.each(valJSON,function (key, val) {
                var num = key + 1;
                 resStr += '<a href="'+val+'" target="_blank">图片'+num+'</a>&nbsp;&nbsp;';
            })
            resStr += '<br/>';
        });

        return resStr;
    }

}