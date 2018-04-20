<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退单列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        td,th{ text-align: center; }
    </style>
    <script>
        $(function () {
            $("#dg").datagrid({
                title:'退单列表',
                pagination : true,
                singleSelect : true,
                multiselect : false,
                selectOnCheck : true,
                url:'list',
                columns:[[
                    {field:'id', title:'ID', width:'3%'},
                    {field:'tdorderid',title:'退单ID', width:'10%'},
                    {field:'orderid',title:'订单ID', width:'10%'},
                    {field:'tdorderprice',title:'退单金额', width:'10%'},
                    {field:'mtsid',title:'商户ID', width:'10%'},
                    {field:'singletype',title:'退单类型', width:'10%',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '支付后退款';
                            }else if ( value == 2 ){
                                return '未支付撤单';
                            }
                        }},
                    {field:'singlestatus',title:'运营审核', width:'10%',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '已审核';
                            }else if ( value == 2 ){
                                return '已驳回';
                            }
                        }},
                    {field:'refuse_content', title:'驳回原因', width:'7%'},
                    {field:'urla',title:'图片', width:'10%'},
                    {field:'content',title:'退单理由', width:'10%'},
                    {field:'addtime',title:'添加时间',formatter:formatDateStr, width:'10%'},
                ]]
            })
        })
    </script>
</head>
<body>
    <table id="dg"></table>
</body>
</html>
