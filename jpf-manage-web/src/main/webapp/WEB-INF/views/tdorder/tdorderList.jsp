<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退单列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {
            $("#dg").datagrid({
                title:'退单列表',
                pagination : true,
                singleSelect : false,
                multiselect : true,
                selectOnCheck : true,
                url:'list',
                columns:[[
                    {field:'id',title:'ID'},
                    {field:'tdorderid',title:'退单ID'},
                    {field:'orderid',title:'订单ID'},
                    {field:'tdorderprice',title:'退单金额'},
                    {field:'mtsid',title:'商户ID'},
                    {field:'singletype',title:'退单类型',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '支付后退款';
                            }else if ( value == 2 ){
                                return '未支付撤单';
                            }
                        }},
                    {field:'singlestatus',title:'运营审核',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '已审核';
                            }else if ( value == 2 ){
                                return '已驳回';
                            }
                        }},
                    {field:'urla',title:'图片'},
                    {field:'content',title:'退单理由'},
                    {field:'addtime',title:'添加时间',formatter:formatDateStr},
                ]]
            })
        })
    </script>
</head>
<body>
    <table id="dg"></table>
</body>
</html>
