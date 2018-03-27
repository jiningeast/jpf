/**
 * jquery.citys.js 1.0
 * http://jquerywidget.com
 */
;(function (factory) {
    if (typeof define === "function" && (define.amd || define.cmd) && !jQuery) {
        // AMD或CMD
        define([ "jquery" ],factory);
    } else if (typeof module === 'object' && module.exports) {
        // Node/CommonJS
        module.exports = function( root, jQuery ) {
            if ( jQuery === undefined ) {
                if ( typeof window !== 'undefined' ) {
                    jQuery = require('jquery');
                } else {
                    jQuery = require('jquery')(root);
                }
            }
            factory(jQuery);
            return jQuery;
        };
    } else {
        //Browser globals
        factory(jQuery);
    }
}(function ($) {
    $.support.cors = true;
    $.fn.citys = function(parameter,getApi) {
        if(typeof parameter == 'function'){ //重载
            getApi = parameter;
            parameter = {};
        }else{
            parameter = parameter || {};
            getApi = getApi||function(){};
        }
        var defaults = {
            // dataUrl:'http://passer-by.com/data_location/list.json',     //数据库地址
            dataUrl:'',     //数据库地址
            crossDomain: true,        //是否开启跨域
            dataType:'json',          //数据库类型:'json'或'jsonp'
            provinceField:'province', //省份字段名
            cityField:'city',         //城市字段名
            areaField:'area',         //地区字段名
            valueType:'code',         //下拉框值的类型,code行政区划代码,name地名
            code:0,                   //地区编码
            province:0,               //省份,可以为地区编码或者名称
            city:0,                   //城市,可以为地区编码或者名称
            area:0,                   //地区,可以为地区编码或者名称
            required: true,           //是否必须选一个
            nodata: 'hidden',         //当无数据时的表现形式:'hidden'隐藏,'disabled'禁用,为空不做任何处理
            onChange:function(){}     //地区切换时触发,回调函数传入地区数据
        };
        var options = $.extend({}, defaults, parameter);
        return this.each(function() {
            //对象定义
            var _api = {};
            // var $this = $(this);
            // var $province = $this.find('select[name="'+options.provinceField+'"]'),
            //     $city = $this.find('select[name="'+options.cityField+'"]'),
            //     $area = $this.find('select[name="'+options.areaField+'"]');
            $.ajax({
                url:options.dataUrl,
                type:'GET',
                crossDomain: options.crossDomain,
                dataType:options.dataType,
                jsonpCallback:'jsonp_location',
                success:function(data){
                    var province,city,area,hasCity;
                    if(options.code){   //如果设置地区编码，则忽略单独设置的信息
                        var c = options.code - options.code%1e4;
                        if(data[c]){
                            options.province = c;
                        }
                        c = options.code - (options.code%1e4 ? options.code%1e2 : options.code);
                        if(data[c]){
                            options.city = c;
                        }
                        c = options.code%1e2 ? options.code : 0;
                        if(data[c]){
                            options.area = c;
                        }
                    }
                    var updateData = function(){
                        province = {},city={},area={};
                        hasCity = false;       //判断是非有地级城市
                        for(var code in data){
                            if(!(code%1e4)){     //获取所有的省级行政单位
                                province[code]=data[code];
                                if(options.required&&!options.province){
                                    if(options.city&&!(options.city%1e4)){  //省未填，并判断为直辖市
                                        options.province = options.city;
                                    }else{
                                        options.province = code;
                                    }
                                }else if(isNaN(options.province)&&data[code].indexOf(options.province)>-1){
                                    options.province = code;
                                }
                            }else{
                                var p = code - options.province;
                                if(options.province&&p>0&&p<1e4){    //同省的城市或地区
                                    if(!(code%100)){
                                        hasCity = true;
                                        city[code]=data[code];
                                        if(options.required&&!options.city){
                                            options.city = code;
                                        }else if(isNaN(options.city)&&data[code].indexOf(options.city)>-1){
                                            options.city = code;
                                        }
                                    }else if(p>8000){                 //省直辖县级行政单位
                                        city[code] = data[code];
                                        if(options.required&&!options.city){
                                            options.city = code;
                                        }else if(isNaN(options.city)&&data[code].indexOf(options.city)>-1){
                                            options.city = code;
                                        }
                                    }else if(hasCity){                  //非直辖市
                                        var c = code-options.city;
                                        if(options.city&&c>0&&c<100){     //同个城市的地区
                                            area[code]=data[code];
                                            if(options.required&&!options.area){
                                                options.area = code;
                                            }else if(isNaN(options.area)&&data[code].indexOf(options.area)>-1){
                                                options.area = code;
                                            }
                                        }
                                    }else{
                                        area[code] = data[code];            //直辖市
                                        if(options.required&&!options.area){
                                            options.area = code;
                                        }else if(isNaN(options.area)&&data[code].indexOf(options.area)>-1){
                                            options.area = code;
                                        }
                                    }
                                }
                            }
                        }
                    };
                    var format = {
                        province:function(){
                            var provinceArray = new Array();
                            if(!options.required){
                                var provinceObj=new Object();
                                provinceObj.id='';
                                provinceObj.text='';
                                provinceArray.push(provinceObj);
                            }
                            for(var i in province){
                                // $province.append('<option value="'+(options.valueType=='code'?i:province[i])+'" data-code="'+i+'">'+province[i]+'</option>');
                                var provinceObj=new Object();
                                provinceObj.id=i;
                                provinceObj.text=province[i];
                                if(options.province&&options.province==i){
                                    provinceObj.selected = 'selected';
                                }
                                provinceArray.push(provinceObj);
                            }
                            $("#merProvince_a").combobox({
                                data: provinceArray,
                                valueField: 'id',
                                textField: 'text',
                                width: 140
                            });
                            this.city();
                        },
                        city:function(){
                            if(!hasCity){
                            }else{
                                var cityArray = new Array();
                                if(!options.required){
                                    var cityObj=new Object();
                                    cityObj.id='';
                                    cityObj.text='';
                                    cityArray.push(cityObj);
                                }
                                for(var i in city){
                                    var cityObj=new Object();
                                    cityObj.id=i;
                                    cityObj.text=city[i];
                                    if(options.city&&options.city==i){
                                        cityObj.selected = 'selected';
                                    }
                                    cityArray.push(cityObj);
                                }
                                $("#merCityp_a").combobox({
                                    data: cityArray,
                                    valueField: 'id',
                                    textField: 'text',
                                    width: 140
                                });
                            }
                            this.area();
                        },
                        area:function(){
                            var areaArray = new Array();
                            if(!options.required){
                                var areaObj=new Object();
                                areaObj.id='';
                                areaObj.text='';
                                areaArray.push(areaObj);
                            }
                            for(var i in area){
                                var areaObj=new Object();
                                areaObj.id=i;
                                areaObj.text=area[i];
                                if(options.area&&options.area==i){
                                    areaObj.selected = 'selected';
                                }
                                areaArray.push(areaObj);
                            }
                            $("#merArea_a").combobox({
                                data: areaArray,
                                valueField: 'id',
                                textField: 'text',
                                width: 140
                            });
                        }
                    };
                    //获取当前地理信息
                    _api.getInfo = function(){
                        var status = {
                            direct:!hasCity,
                            province:data[options.province]||'',
                            city:data[options.city]||'',
                            area:data[options.area]||'',
                            code:options.area||options.city||options.province
                        };
                        return status;
                    };
                    //事件绑定
                    $("#merProvince_a").combobox({
                        onSelect:function(record){
                            // $("#merCityp_a").combobox("clear");
                            // $("#merArea_a").combobox("clear");
                            $("#merCityp_a").combo("panel").empty();
                            $("#merArea_a").combo("panel").empty();
                            options.province = record.id||0; //选中节点的区划代码
                            options.city = 0;
                            options.area = 0;
                            updateData();
                            format.city();
                            options.onChange(_api.getInfo());
                        }
                    });
                    $("#merCityp_a").combobox({
                        onSelect:function(record){
                            // $("#merArea_a").combobox("clear");
                            $("#merArea_a").combo("panel").empty();
                            options.city = record.id||0; //选中节点的区划代码
                            options.area = 0;
                            updateData();
                            format.area();
                            options.onChange(_api.getInfo());
                        }
                    });
                    $("#merArea_a").combobox({
                        onSelect:function(record){
                            options.area = record.id||0; //选中节点的区划代码
                            options.onChange(_api.getInfo());
                        }
                    });
                    //初始化
                    updateData();
                    format.province();
                    if(options.code){
                        options.onChange(_api.getInfo());
                    }
                    getApi(_api);
                }
            });
        });
    };
}));
