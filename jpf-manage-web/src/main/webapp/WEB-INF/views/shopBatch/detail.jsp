<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>批次详情</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        table tr td { text-align: center; }
    </style>
</head>
<body>
<div id="couponsDG"></div>
<script>
    $(function () {
        var toolbar = [{
            text:'重发短信',
            iconCls:'icon-redo',
            handler:function(){
                $.messager.confirm('确认','确定要重发短信吗？',function(r){
                    var rows = $('#couponsDG').datagrid('getChecked'); // 取得checkbox选择行的数据，返回元素记录的数组数据。
                    if (rows.length <= 0) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    var param = {};
                    param["couponIds"] = "";
                    if (r){
                        for (var i = 0; i < rows.length; i++) {
                            if ( !rows[i].activePhone ){
                                $.messager.alert('消息提示','请选择已经发送过短信的用户进行重发','info');
                                return false;
                            }
                            param['couponIds'] += rows[i].id+',';
                        }
                        param['couponIds'] = param['couponIds'].substr(0,param['couponIds'].length-1);

                        $.ajax({
                            type : 'get',
                            url : 'sendSmsAgain?couponIds='+param["couponIds"],
                            dataType:"json",
                            contentType:"application/json",
                            success : function(msg){
                                if (msg.retCode != '0000') {
                                    $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                } else {
                                    $.messager.alert('消息提示','操作成功!','info');
                                    $('#couponsDG').datagrid('reload');
                                }
                            },
                            error : function () {
                                $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                            }
                        })
                    }
                })
            }
        }];

        // 欣券详情
        $('#couponsDG').datagrid({
            title:'欣券详情',
            toolbar:toolbar,
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:false,
            multiselect:true,
            selectOnCheck:false,
            remoteSort: false, // 服务端排序
            url:'detailData?batchId='+${requestScope.batchId},
            columns:[[
                {field:'id',title:'券id',width:"5%",checkbox:true},
                {field:'activeCode',title:'激活码',width:"8%"},
                {field:'money',title:'面值',width:"5%"},
                {field:'dou',title:'欣豆',width:"5%"},
                {field:'isActive',title:'是否激活',width:"6%",formatter:function (value,row,index) {
                        if ( value == 0 ){
                            return "<font style='color: #0e90d2'>未激活</font>";
                        }else if ( value == 1 ){
                            return "<font style='color: #942a25'>已激活</font>";
                        }
                    }},
                {field:'activePhone',title:'激活人手机号',width:"10%"},
                {field:'expireMonth',title:'有效期',width:"5%",formatter:function (value,row,index) {
                        return value+"个月";
                    }},
                {field:'activeTime',title:'激活时间',width:"11%",formatter:formatDateStr},
                {field:'expireTime',title:'过期时间',width:"11%",formatter:formatDateStr},
                {field:'sendType',title:'发送方式',width:"6%",formatter:function (value,row,index) {
                        if ( value == 0 ){
                            return "<font style='color: #0e90d2'>Email发送</font>";
                        }else if (value == 1){
                            return "<font style='color: #0e90d2'>群发给个人并激活<font>";
                        }else if (value == 2){
                            return "<font style='color: #0e90d2'>群发给个人不激活<font>";
                        } else {
                            return "未发送";
                        }
                    }},
                {field:'sendTime',title:'发送时间',width:"11%",formatter:formatDateStr},
                {field:'addtime',title:'添加时间',width:"11%",formatter:formatDateStr}
            ]]
        });
    })
</script>
</body>
