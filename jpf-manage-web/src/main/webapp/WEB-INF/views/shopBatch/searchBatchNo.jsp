<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>选取公司</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<div name="contentDiv" style="margin: 10px;">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#companys_ft'">
        <form id="searchForm" method="post">
            <input id="status" name="status" type="hidden" value="4">
            <table class="companys" cellpadding="5" width="100%">
                <tr>
                    <td>商户ID:</td>
                    <td><input id="merchId" name="id" class="easyui-textbox" type="text" /></td>
                    <td>商户号:</td>
                    <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" /></td>
                    <td>企业名称:</td>
                    <td><input id="merchName" name="companyName" class="easyui-textbox" type="text" /></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="companys_ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="batchDataGrid"></table>
</div>
<script>
    $(function(){
        var toolbar = [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
                var rows = $('#batchDataGrid').datagrid('getSelections');
                if (rows.length != 1) {
                    $.messager.alert('消息提示','请选择一条数据！','info');
                    return false;
                }
                $("#batchNo").textbox("setValue",rows[0].batchNo);
                $("#batchId").val(rows[0].id);
                $("#batchWin").window("close");
                $("#name").textbox("setValue",rows[0].companyName);
                $("#mid").val(rows[0].id);

                //同事调用查询券的信息
            }
        }];

        $('#batchDataGrid').datagrid({
            title:'商户信息列表',
            toolbar:toolbar,
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'/shopBatch/list',//此处只查询申请中的批次号
            queryParams:{
                status:4
            },
            columns:[[
                {field:'id',title:'批次id',width:"5%"},
                {field:'companyName',title:'商户名称',width:"10%"},
                {field:'batchNo',title:'批次号',width:"15%"},
                {field:'money',title:'金额',width:"10%"},
                {field:'count',title:'券数量',width:"10%"}
            ]]
        });

        $('#searchBtn').linkbutton({
            onClick: function(){
                var queryArray = $('#searchForm').serializeArray();
                var postData = parsePostData(queryArray);
                $('#batchDataGrid').datagrid('reload', postData);
            }
        });

        $('#searchRestBtn').linkbutton({
            onClick: function(){
                $('#searchForm').form('reset');
            }
        });
    })
</script>
</body>
