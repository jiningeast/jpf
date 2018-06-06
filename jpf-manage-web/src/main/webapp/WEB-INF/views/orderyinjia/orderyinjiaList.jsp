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
                    $('#infoDiv').window("open").window('refresh', 'cpinfo?signOrderid=' + rows[0].signOrderid + "&orderid=" + rows[0].orderid ).window('setTitle','详情');
                }
            }];

            $("#dg").datagrid({
                title:'订单列表',
                toolbar:toolbar,
                url:'../orderyinjia/list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'orderid',title:'订单ID',width:'9%'},
                    {field:'foreignOrderid',title:'商户订单号',width:'9%'},
                    {field:'mtsid',title:'商户ID',width:'3%'},
                    {field:'merchName',title:'商户名称',width:'8%'},
                    {field:'companyname',title:'企业名称',width:'8%'},
                    {field:'cat',title:'支付方式',width:'8%'},
                    {field:'orderPayPrice',title:'实付',width:'5%', formatter:formatPrice},
                    {field:'orderStdPrice',title:'订单原价',width:'5%', formatter:formatPrice},
                    {field:'productAccount',title:'商品数量',width:'5%'},
                    {field:'payDetail',title:'分期信息',width:'20%', formatter:formatJSON},
                    {field:'payStatus',title:'支付状态',width:'5%',
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return '未支付';
                            }else if ( value == 1 ){
                                return '支付成功';
                            }else if ( value == 2 ){
                                return '支付失败';
                            }
                        }},
                    {field:'paytime',title:'支付时间',width:'9%',formatter: formatDateStr},
                    {field:'refundStatus_cn',title:'退单状态',width:'8%'},
                    {field:'userOperateStatus_cn',title:'用户流程',width:'8%'},
                    {field:'addtime',title:'下单时间',width:'9%',formatter: formatDateStr},
                    // {field:'updatetime',title:'修改时间',width:'9%',formatter: formatDateStr}
                ]],
                onLoadSuccess: function (msg) {
                    // $("#allOrdersCount").text(msg.allOrdersCount);
                    // $("#allOrdersMoney").text(msg.allOrdersMoney);
                    // $("#allRefundMoney").text(msg.allRefundMoney);
                    // $("#profitMoney").text(msg.profitMoney);
                }
            })

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
                width:'1024px',
                height:'550px',
                closed:true,
                modal:true
            });

        })
    </script>
</head>
<body>
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'" style="padding: 20px;">
        <form id="searchForm" method="post">
            <table cellpadding="5" width="100%">
                <tr>
                    <td>订单ID：</td>
                    <td><input id="orderid" name="orderid" class="easyui-textbox" type="text" ></td>
                    <td>商户ID：</td>
                    <td><input id="mtsid" name="mtsid" class="easyui-textbox" type="text" /></td>
                    <td>产品ID：</td>
                    <td><input id="pid" name="pid" class="easyui-textbox" type="text" /></td>
                </tr>
                <tr>
                    <td>支付方式：</td>
                    <td>
                        <input id="paytype" name="paytype" class="easyui-combobox" style="width: 150px;" type="text">
                    </td>
                    <td>支付状态：</td>
                    <td>
                        <select id="orderstatus" name="orderstatus" class="easyui-combobox">
                            <option value="">全部</option>
                            <option value="0">未支付</option>
                            <option value="1">已支付</option>
                            <option value="2">支付失败</option>
                        </select>
                    </td>
                    <td>退单状态：</td>
                    <td>
                        <select id="singlestatus" name="singlestatus" class="easyui-combobox">
                            <option value="">全部</option>
                            <option value="1">正常订单</option>
                            <option value="2">用户申请退单</option>
                            <option value="3">用户撤销退单</option>
                            <option value="4">运营已审核，待财务审核</option>
                            <option value="5">财务已审核，退款中</option>
                            <option value="6">审核驳回</option>
                            <option value="7">退款成功</option>
                            <option value="8">退款失败</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>支付时间：</td>
                    <td>
                        <input type="text" class="Wdate" style="width:100px;" id="paytimeStart"
                               name="paytimeStart"
                               onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'paytimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        -
                        <input type="text" class="Wdate" style="width:100px;" id="paytimeEnd"
                               name="paytimeEnd"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'paytimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
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
