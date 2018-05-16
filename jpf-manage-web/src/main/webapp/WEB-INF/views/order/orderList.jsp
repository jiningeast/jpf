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
            // 支付方式中文字段
            var payTypeArr = new Array();
            $.post("../param/getType",{'pid':'5'},function (res) {
                // console.log(res);
                var res = JSON.stringify(res);
                var newKsy;
                var newValue;
                JSON.parse(res,function(key, value){
                    if ( key == 'catid' ) {
                        newKsy = value;
                    }
                    if ( key == 'cat' ) {
                        newValue = value;
                    }
                    payTypeArr[newKsy] = String(newValue);
                });
                // console.log(payTypeArr);
            })

            $("#dg").datagrid({
                title:'订单列表',
                url:'list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                columns:[[
                    // {field:'id',title:'ID',width:'3%'},
                    {field:'orderid',title:'订单ID',width:'9%'},
                    {field:'mtsid',title:'商户ID',width:'3%'},
                    {field:'uid',title:'用户uid',width:'3%'},
                    {field:'pid',title:'商品ID',width:'3%'},
                    {field:'paytype',title:'支付方式',width:'7%',
                        formatter:function (value, row, index) {
                            if ( value !== null ){
                                return payTypeArr[value];
                            }
                        }},
                    {field:'orderprice',title:'实付',width:'5%', formatter:formatPrice},
                    {field:'orderselprice',title:'实际金额',width:'5%', formatter:formatPrice},
                    {field:'ordernum',title:'商品数量',width:'5%'},
                    {field:'ordername',title:'分期信息',width:'30%', formatter:formatJSON},
                    {field:'orderstatus',title:'支付状态',width:'5%',
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return '未支付';
                            }else if ( value == 1 ){
                                return '支付成功';
                            }else if ( value == 2 ){
                                return '支付失败';
                            }
                        }},
                    {field:'singlestatus',title:'退单状态',width:'8%',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '正常订单';
                            }else if ( value == 2 ){
                                return '<span style="color: green">用户申请退单</span>';
                            }else if ( value == 3 ){
                                return '用户撤销退单';
                            }else if ( value == 4 ){
                                return '<span style="color: green">运营已审核,待财务审核</span>';
                            }else if ( value == 5 ){
                                return '<span style="color: green">财务已审核，退款中</span>';
                            }else if ( value == 6 ){
                                return '<span style="color: red">审核驳回</span>';
                            }else if ( value == 7 ){
                                return '<span style="color: green">退款成功</span>';
                            }else if ( value == 8 ){
                                return '<span style="color: red">退款失败</span>';
                            }
                        }},
                    {field:'paytime',title:'支付时间',width:'9%',formatter: formatDateStr},
                    {field:'addtime',title:'生成时间',width:'9%',formatter: formatDateStr},
                    // {field:'updatetime',title:'修改时间',width:'9%',formatter: formatDateStr}
                ]],
                onLoadSuccess: function (msg) {
                    $("#allOrdersCount").text(msg.allOrdersCount);
                    $("#allOrdersMoney").text(msg.allOrdersMoney);
                    $("#allRefundMoney").text(msg.allRefundMoney);
                    $("#profitMoney").text(msg.profitMoney);
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
    <div class="easyui-panel statistics" title="汇总统计">
        <table width="100%" cellpadding="5%">
            <tr>
                <td width="10%">订单总笔数：</td>
                <td width="10%" id="allOrdersCount"></td>
                <td width="10%">订单总金额：</td>
                <td width="10%" id="allOrdersMoney"></td>
                <td width="10%">退款总金额：</td>
                <td width="10%" id="allRefundMoney"></td>
                <td width="10%">结算总金额：</td>
                <td width="10%" id="profitMoney"></td>
            </tr>
        </table>
    </div>
    <br/>
    <table id="dg"></table>
</body>
</html>
