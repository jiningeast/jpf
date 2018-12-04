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
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#companysb_ft'">
        <form id="searchOrderForm" method="post">
            <table class="companys" cellpadding="5" width="100%">
                <tr>
                    <td>商户ID:</td>
                    <td><input id="companyId" name="id" class="easyui-textbox" type="text" /></td>
                    <td>合同号:</td>
                    <td><input id="contractNo" name="contractNo" class="easyui-textbox" type="text" /></td>
                    <td>企业名称:</td>
                    <td><input id="companyName" name="companyName" class="easyui-textbox" type="text" /></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="companysb_ft" style="padding:5px;">
        <a id="searchOrderBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchOrderRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="orderDataGrid"></table>
</div>
<script>
    $(function(){
        var toolbar = [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
                var rows = $('#orderDataGrid').datagrid('getSelections');
                if (rows.length != 1) {
                    $.messager.alert('消息提示','请选择一条数据！','info');
                    return false;
                }
                $("#orderNo").textbox("setValue",rows[0].orderNo);
                $("#orderId").val(rows[0].id);
                $("#orderWin").window("close");
                $("#name").textbox("setValue",rows[0].companyName);
                $("#mid").val(rows[0].companyId);
                $("#contractId").combobox('loadData', {});
                //$("#contractId").val(rows[0].contractId);
                $("#contractId").empty();
                $("#contractId").append("<option value="+rows[0].contractId+">"+rows[0].contractNo+"</option>");
                $('#contractId').combobox('setValue',rows[0].contractNo);
                $("#contractId").combobox({});
                $("#contractNo").val(rows[0].contractNo);
                //同事调用订单的详细的信息
                $("#couponDG").datagrid("load",{orderId:rows[0].id});
                $("#totalMoney").text(rows[0].totalMoney.toFixed(2));
            }
        }];

        $('#orderDataGrid').datagrid({
            title:'商户信息列表',
            toolbar:toolbar,
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'../shopBatch/getOrderList',//此处只查询申请中的批次号
            columns:[[
                {field:'id',title:'批次id',width:"5%"},
                {field:'companyName',title:'商户名称',width:"10%"},
                {field:'companyId',title:'商户id',width:"10%"},
                {field:'orderNo',title:'订单号',width:"15%"},
                {field:'contractNo',title:'合同号',width:"15%"},
                {field:'totalMoney',title:'金额',width:"10%"},
                {field:'totalNum',title:'券数量',width:"10%"}
            ]]
        });

        $('#searchOrderBtn').linkbutton({
            onClick: function(){
                var queryArray = $('#searchOrderForm').serializeArray();
                var postData = parsePostData(queryArray);
                $('#orderDataGrid').datagrid('reload', postData);
            }
        });

        $('#searchOrderRestBtn').linkbutton({
            onClick: function(){
                $('#searchOrderForm').form('reset');
            }
        });
    })
</script>
</body>
