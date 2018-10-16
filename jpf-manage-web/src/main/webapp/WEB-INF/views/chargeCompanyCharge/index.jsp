<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>充值平台商户充值</title>
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

            $('#addWindow').window({
                title:'充值详情',
                width:'800px',
                height:'500px',
                closed:true,
                modal:true
            });

            var toolbar = [
                {
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#addWindow").window("open").window('refresh', 'addPage').window('setTitle','新增');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'充值平台充值',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'list',
                columns:[[
                    {field:'id',title:'ID',width:"5%"},
                    {field:'companyId',title:'商户id',width:"5%"},
                    {field:'companyName',title:'商户名称',width:"8%"},
                    {field:'operatorId',title:'发起人id',width:"5%"},
                    {field:'operatorName',title:'发起人姓名',width:"5%"},
                    {field:'rate',title:'费率',width:"5%"},
                    {field:'contractMoney',title:'合同金额',width:"5%"},
                    {field:'money',title:'实际充值金额',width:"5%"},
                    {field:'checkOperatorId',title:'财务审核人id',width:"5%"},
                    {field:'checkOperatorName',title:'财务审核人姓名',width:"5%"},
                    {field:'checkTime',title:'财务审核时间',width:"10%",formatter: formatDateStr},
                    {field:'status',title:'状态',width:"8%",
                        formatter:function (value, row, index) {
                            if ( value == -1 ) { return "运营取消"; }
                            if ( value == 0 ) { return "新增待审核"; }
                            if ( value == 1 ) { return "审核通过已充值"; }
                            if ( value == 2 ) { return "审核驳回"; }
                    }}
                ]]
            });
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
            <div style="padding:10px 60px 20px 60px">
                <form id="searchForm" method="post">
                    <table cellpadding="5" width="75%">
                        <tr>
                            <td>商户id:</td>
                            <td><input id="companyId" name="companyId" class="easyui-textbox" type="text" /></td>
                            <td>商户名称：</td>
                            <td><input id="companyName" name="companyName" class="easyui-textbox" type="text" /></td>
                            <td>状态：</td>
                            <td>
                                <select id="status" name="status" class="easyui-combobox">
                                    <option value="">全部</option>
                                    <option value="0">新增待审核</option>
                                    <option value="1">审核通过已充值</option>
                                    <option value="2">审核驳回</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="ft" style="padding:5px;">
            <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
            <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
        </div>
        <br>
        <div id="dg"></div>
    </div>
    <div id="addWindow"></div>
</body>
</html>
