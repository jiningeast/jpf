<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统日志</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        td, th { text-align: center; }
    </style>
    <script>
        $(function () {
            $("#dg").datagrid({
                title : '系统日志',
                pagination : true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect : true,
                multiselect : false,
                selectOnCheck : true,
                url : 'list',
                columns : [[
                    {field:'id',title:'ID', width:'5%'},
                    {field:'logtype', title:'来源',width:'5%',
                        formatter : function (value, row, index) {
                            if ( value == '0' ){
                                return '前台';
                            }else if ( value == '1' ){
                                return '后台';
                            }
                        }},
                    {field:'operatorUid', title:'操作者uid', width:'5%'},
                    {field:'operatorName', title:'操作者账号', width:'5%'},
                    {field:'ip', title:'IP地址', width:'6%'},
                    {field:'ip1', title:'用户设备识别码', width:'5%'},
                    {field:'clients', title:'客户端', width:'5%'},
                    {field:'tablename', title:'表名', width:'5%'},
                    {field:'record', title:'操作人ID', width:'5%'},
                    {field:'action', title:'操作类型', width:'5%'},
                    {field:'content', title:'SQL语句', width:'42%'},
                    {field:'created', title:'创建时间', width:'8%',formatter: formatDateStr}
                ]]
            })
        })
    </script>
</head>
<body>
    <table id="dg"></table>
</body>
</html>
