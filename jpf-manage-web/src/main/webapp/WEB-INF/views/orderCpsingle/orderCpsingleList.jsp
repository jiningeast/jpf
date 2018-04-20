<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPEhtml>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退单记录</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {
            $("#dg").datagrid({
                title:'订单列表',
                url:'list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                electOnCheck:true,
                columns:[[
                    {field:'id', title:'ID'},
                    {field:'tdorderid', title:'退单ID'},
                    {field:'orderid', title:'订单ID'},
                    {field:'tdorderprice', title:'订单金额'},
                    {field:'mtsid', title:'商户ID'},
                    {field:'singletype', title:'退单类型'},
                    {field:'singlestatus', title:'退款状态'},
                    {field:'addtime', title:'添加时间', formatter:formatDateStr}
                ]]
            })
        })
    </script>
</head>
<body>
    <table id="dg"></table>
</body>
</html>
