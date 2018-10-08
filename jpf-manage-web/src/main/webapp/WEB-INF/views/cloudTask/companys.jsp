<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>选取公司</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<div name="contentDiv" style="margin: 8px;">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#companys_ft'">
        <form id="searchForm" method="post">
            <table class="companys" cellpadding="5" width="100%">
                <tr>
                    <td>商户ID:</td>
                    <td><input id="merchId" name="id" class="easyui-textbox" type="text" /></td>
                    <td>聚合商户号:</td>
                    <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" /></td>
                    <td>企业名称:</td>
                    <td><input id="merchName" name="name" class="easyui-textbox" type="text" /></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="companys_ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="companysDataGrid"></table>
</div>
<script>
    $(function(){

        var toolbar = [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
                var rows = $('#companysDataGrid').datagrid('getSelections');
                if (rows.length != 1) {
                    $.messager.alert('消息提示','请选择一条数据！','info');
                    return false;
                }
                $("#name").textbox("setValue",rows[0].name);
                $("#mid").val(rows[0].id);
                $("#companys").window("close");
            }
        }];

        $('#companysDataGrid').datagrid({
            title:'商户信息列表',
            toolbar:toolbar,
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'../cloudCompany/listSale',
            columns:[[
                {field:'id',title:'商户Id',width:"10%"},
                {field:'merchNo',title:'聚合商户号',width:"20%"},
                {field:'name',title:'企业名称',width:"10%"},
                {field:'phonename',title:'联系人姓名',width:"10%"},
                {field:'phone',title:'联系人手机号',width:"10%"},
                {field:'attestation',title:'认证',width:"10%",
                    formatter:function (value,row,index) {
                        if ( value == '1' ){
                            return "已认证";
                        }else if ( value == '0' ){
                            return "未认证";
                        }
                    }},
                {field:'cloudmoney',title:'余额',width:"10%"},
                {field:'created',title:'创建时间',width:"20%",formatter: formatDateStr}
            ]]
        });

        $('#searchBtn').linkbutton({
            onClick: function(){
                var queryArray = $('#searchForm').serializeArray();
                var postData = parsePostData(queryArray);
                $('#companysDataGrid').datagrid('reload', postData);
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
