<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银行管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function(){
            var toolbar = [{
                text : '新增',
                iconCls : 'icon-add',
                handler : function(){
                    $("#edit").window("open").window('refresh','add').window('setTitle','新增银行');
                }
            }]

            $("#dg").datagrid({
                title : '银行管理',
                toolbar:toolbar,
                pagination:true,
                multiselect:true,
                selectOnCheck:true,
                url : 'list',
                columns : [[
                    {field:'id', title:'ID',width:'14%'},
                    {field:'paybankname', title:'银行名称',width:'14%'},
                    {field:'tpid', title:'银行类型',width:'14%'},
                    {field:'bankcode', title:'银行编码',width:'14%'},
                    {field:'created', title:'创建时间',width:'14%'},
                    {field:'updated', title:'修改时间',width:'14%'}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');

            $('#edit').window({
                width:'400px',
                height:'400px',
                closed:true,
                modal:true
            });
        })
    </script>
</head>
<body>
    <table id="dg"></table>
    <div id="edit"></div>
</body>
</html>
