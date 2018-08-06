<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>批次详情</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<div id="couponsDG"></div>
<script>
    $(function () {
        // 欣券详情
        $('#couponsDG').datagrid({
            title:'欣券详情',
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'detailData?batchId='+${requestScope.batchId},
            columns:[[
                {field:'id',title:'券id',width:"10%"},
                {field:'activeCode',title:'激活码',width:"10%"},
                {field:'money',title:'面值',width:"10%"},
                {field:'dou',title:'欣豆',width:"10%"},
                {field:'isActive',title:'是否激活',width:"10%",formatter:function (value,row,index) {
                        if ( value == 0 ){
                            return "<font style='color: #0e90d2'>未激活</font>";
                        }else if ( value == 1 ){
                            return "<font style='color: #942a25'>已激活</font>";
                        }
                    }},
                {field:'expireMonth',title:'有效期',width:"10%",formatter:function (value,row,index) {
                        return value+"个月";
                    }},
                {field:'activeTime',title:'激活时间',width:"10%",formatter:formatDateStr},
                {field:'expireTime',title:'过期时间',width:"10%",formatter:formatDateStr},
                {field:'addtime',title:'添加时间',width:"10%",formatter:formatDateStr}
            ]]
        });
    })
</script>
</body>
