<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>余额管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {

            $('#searchBtn').linkbutton({
                onClick: function(){
                    var queryArray = $('#searchForm').serializeArray();
                    var postData = parsePostData(queryArray);
                    $('#dg').datagrid('reload', postData);
                }
            });

            $('#searchRestBtn').linkbutton({
                onClick: function(){
                    $('#searchForm').form('reset');
                }
            });

            $('#editCompany').window({
                title:'商户详情',
                width:'600px',
                height:'300px',
                closed:true,
                modal:true
            });

            var toolbar = [
             /*   {
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#editCompany").window("open").window('refresh', 'addPage').window('setTitle','新增');
                    }
                },*/
                {
                    text:'编辑',
                    iconCls:'icon-edit',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $('#editCompany').window("open").window('refresh', '../chargeBalance/editPage?id='+rows[0].id).window('setTitle','编辑');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'余额列表',
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
                    {field:'balance',title:'余额',width:'5%'},
                    {field:'alertLimit',title:'报警阀值',width:'5%'},
                    {field:'alertSwitch',title:'报警开关',width:'5%',
                        formatter:function (value,row,index) {
                            if ( value == 0 ) { return "关闭"; }
                            if ( value == 1 ) { return "打开"; }
                        }},
                    {field:'alertPhone',title:'报警电话',width:'8%'},
                    {field:'stopLimit',title:'停用阀值',width:'6%'},
                    {field:'type',title:'接口商家',width:'6%',
                        formatter:function (value,row,index) {
                            if ( value == 0 ) { return "欧非"; }
                            if ( value == 1 ) { return "威能"; }
                        }},
                    {field:'updatetime',title:'更新时间',width:'10%',formatter: formatDateStr}
                ]]
            });
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#CompanyChargeft'">
            <div style="padding:10px 60px 20px 60px">
                <form id="searchForm" method="post">
                    <table cellpadding="5" width="50%">
                        <tr>
                            <td>报警电话:</td>
                            <td><input id="alertPhone" name="alertPhone" class="easyui-textbox" type="text" /></td>
                            <td>接口商家：</td>
                            <td >
                                <select id="type" name="type" class="easyui-combobox" style="width: 70px;">
                                    <option value="">全部</option>
                                    <option value="0">欧非</option>
                                    <option value="1">威能</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>状态：</td>
                            <td style="width: 200px;">
                                <select id="alertSwitch" name="alertSwitch" class="easyui-combobox" style="width: 70px;">
                                    <option value="">全部</option>
                                    <option value="0">关闭</option>
                                    <option value="1">打开</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="CompanyChargeft" style="padding:5px;">
            <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
            <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
        </div>
        <br>
        <div id="dg"></div>
    </div>
    <div id="editCompany"></div>
</body>