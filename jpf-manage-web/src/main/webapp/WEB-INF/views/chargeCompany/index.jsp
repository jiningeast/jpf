<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>充值平台商户管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {

            $('#editCompany').window({
                title:'商户详情',
                width:'600px',
                height:'300px',
                closed:true,
                modal:true
            });

            var toolbar = [
                {
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#editCompany").window("open").window('refresh', 'addPage').window('setTitle','新增');
                    }
                },
                {
                    text:'编辑',
                    iconCls:'icon-edit',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $('#editCompany').window("open").window('refresh', 'editPage?id='+rows[0].id).window('setTitle','编辑');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'业务商户信息',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'list',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'merchNo',title:'商户号',width:'10%'},
                    {field:'companyName',title:'商户名称',width:'10%'},
                    {field:'privateKey',title:'商户私钥',width:'10%'},
                    {field:'money',title:'余额',width:'10%'},
                    {field:'contactName',title:'联系人姓名',width:'10%'},
                    {field:'contactPhone',title:'联系人手机号',width:'10%'},
                    {field:'isFreeze',title:'是否冻结',width:'10%',
                        formatter:function (value,row,index) {
                            if ( value == 0 ) { return "未冻结"; }
                            if ( value == 1 ) { return "已冻结"; }
                        }},
                    {field:'addtime',title:'添加时间',width:'10%',formatter: formatDateStr},
                    {field:'updatetime',title:'更新时间',width:'10%',formatter: formatDateStr}
                ]]
            });
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="dg"></div>
    </div>
    <div id="editCompany"></div>
</body>