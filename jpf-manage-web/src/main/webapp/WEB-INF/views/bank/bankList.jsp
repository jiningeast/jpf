<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银行管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function(){
            var toolbar = [
                {
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#edit").window('open').window('refresh','add').window('setTitle','新增银行');
                    },
                },{
                text : '编辑',
                iconCls : 'icon-edit',
                handler : function(){
                    var rows = $("#dg").datagrid('getSelections');
                    if ( rows.length != 1 ) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $("#edit").window('open').window('refresh','edit?id='+rows[0].id).window('setTitle','编辑银行');
                }
                },{
                text : '删除',
                iconCls : 'icon-remove',
                handler : function () {
                    var rows = $("#dg").datagrid('getSelections');
                    if ( rows.length != 1 ) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $.messager.confirm('删除','确认删除操作？',function(r){
                        if (r){
                            $.ajax({
                                type : 'get',
                                url : 'delBank?id='+rows[0].id,
                                dataType:"json",
                                contentType:"application/json",
                                success : function(msg){
                                    if (msg.retCode != '0000') {
                                        $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                    } else {
                                        $.messager.alert('消息提示','操作成功!','info');
                                        $('#dg').datagrid('reload');
                                    }
                                },
                                error : function () {
                                    $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                }
                            })
                        }
                    })
                }
                }
            ];

            $("#dg").datagrid({
                title : '银行管理',
                toolbar:toolbar,
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                url : 'list',
                columns : [[
                    {field:'id', title:'ID',width:'14%'},
                    {field:'paybankname', title:'银行名称',width:'14%'},
                    {field:'tpid', title:'银行类型',width:'14%'},
                    {field:'bankcode', title:'银行编码',width:'14%'},
                    {field:'created', title:'创建时间',width:'14%',formatter: formatDateStr},
                    {field:'updated', title:'修改时间',width:'14%',formatter: formatDateStr}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');

            $('#edit').window({
                width:'400px',
                height:'200px',
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
