<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>地区管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function(){
            $('#dg').datagrid({
                title:'地区管理',
                // toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'list',
                columns:[[
                    {field:'catid',title:'catid'},
                    {field:'cat',title:'地区'},
                    {field:'catpathCn',title:'详细地区'},
                    {field:'postcode',title:'邮编'},
                    {field:'phonecode',title:'区号'}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');
        })
    </script>
</head>
<body>
    <div class="contentDiv">
        <table id="dg"></table>
    </div>
</body>
</html>
