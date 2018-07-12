<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        .datagrid-body td,.datagrid-body th{ text-align: center; }
        #searchForm td:nth-child(odd) { text-align: right; }
        #searchForm td:nth-child(even) { text-align: left; }
        #searchForm td { width: 5%;  }
        .statistics td:nth-child(odd) { background-color: rgb(241,241,241); text-align: right;}
        .statistics td:nth-child(even) { text-align: left; }
        .datagrid-header td, .datagrid-body td, .datagrid-footer td{
            text-align: center;}
    </style>
    <script>
        $(function () {
            var toolbar = [{
                text:'支付详情',
                iconCls:'icon-view-detail',
                handler:function(){
                    var rows = $('#dg').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','list');
                        return
                    }
                    $('#infoDiv').window("open").window('refresh', 'paydetail?orderid=' + rows[0].orderid ).window('setTitle','详情');
                }
            }];

            $("#dg").datagrid({
                title:'聚合通道订单列表',
                toolbar:toolbar,
                url:'list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                columns:[[
                    {field:'id',title:'ID',width:'3%',},
                    {field:'orderid',title:'订单ID',width:'9%'},
                    {field:'mtsid',title:'商户ID',width:'3%'},
                    {field:'merchName',title:'商户名称',width:'13%'},
                    {field:'money',title:'金额',width:'8%', formatter:formatPrice},
                    {field:'cat',title:'支付方式',width:'10%'},
                    {field:'productId',title:'商品ID',width:'5%'},
                    {field:'productName',title:'商品名称',width:'13%'},
                    {field:'productAmount',title:'商品数量',width:'5%'},
                    {field:'productUnitPrice',title:'商品单价',width:'5%', formatter:formatPrice},
                    {field:'created',title:'添加时间',width:'9%',formatter: formatDateStr}
                ]],
                onLoadSuccess: function (msg) {
                }
            });

            // 搜索 - 支付类型初始化
            $('#paytype').combobox({
                url:'../param/getType?pid=5',
                valueField:'catid',
                textField:'cat'
            });

            // 点击搜索按钮
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

            $('#infoDiv').window({
                width:'1600px',
                height:'800px',
                closed:true,
                modal:true
            });

        });

    </script>
</head>
<body>
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'" style="padding: 20px;">
        <form id="searchForm" method="post">
            <table cellpadding="5" width="75%">
                <tr>
                    <td>平台订单ID：</td>
                    <td><input id="orderid" name="orderid" class="easyui-textbox" type="text" ></td>
                    <td>支付方式：</td>
                    <td>
                        <input id="paytype" name="paytype" class="easyui-combobox" style="width: 150px;" type="text">
                    </td>
                </tr>
                <tr>
                    <td>生成时间：</td>
                    <td>
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                               name="addtimeStart"
                               onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        -
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                               name="addtimeEnd"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="dg"></table>
    <div id="infoDiv"></div>
</body>
</html>
