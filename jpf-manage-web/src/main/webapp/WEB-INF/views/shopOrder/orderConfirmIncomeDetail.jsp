<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>确认收入订单详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="auditForm" method="post">
            <input type="hidden" id="id_audit" name="id" value="${shopOrderInfo.id ? "" : shopOrderInfo.id}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th colspan="4">确认收入订单详细信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单序列号：</td>
                    <td>
                        ${shopOrderInfo.id}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">订单号：</td>
                    <td>
                        ${shopOrderInfo.orderNo}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单金额：</td>
                    <td>
                        ${shopOrderInfo.totalMoney}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">支付方式：</td>
                    <td>
                        <c:if  test="${shopOrderInfo.payWay == 0 }">豆支付</c:if>
                        <c:if  test="${shopOrderInfo.payWay == 1 }">微信支付</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商品名称: </td>
                    <td>
                        ${shopOrderInfo.productName}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">下单时间：</td>
                    <td>
                        <fmt:formatDate value="${shopOrderInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单状态：</td>
                    <td >
                        <c:if  test="${shopOrderInfo.status == 0 }">待支付</c:if>
                        <c:if  test="${shopOrderInfo.status == 1 }">已支付</c:if>
                        <c:if  test="${shopOrderInfo.status == 2 }">支付失败</c:if>
                        <c:if  test="${shopOrderInfo.status == 3 }">已取消</c:if>
                        <c:if  test="${shopOrderInfo.status == 4 }">充值成功</c:if>
                        <c:if  test="${shopOrderInfo.status == 5 }">充值失败</c:if>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">售价：</td>
                    <td >
                        ${shopOrderInfo.productMoney}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">购买数量：</td>
                    <td>
                        ${shopOrderInfo.amount}
                    </td>
                    <td></td>
                    <td></td>
                </tr>
                <%--<tr>--%>
                    <%--<td style="text-align: right;background-color: #f1f1f1;">券消费详情：</td>--%>
                    <%--<td colspan="3">--%>
                        <%--${shopOrderInfo.couponDetail}--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </table>
            <div id="productView"></div>
        </form>
    </div>
</div>
<script>
    $(function () {
        var status = ${shopOrderInfo.status};
        if(status == 1){
            $("#productView").datagrid({
                title:'订单欣券列表',
                // toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                //pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'orderConfirmIncomeCouponDetailList?orderNo='+"${shopOrderInfo.orderNo}",
                columns:[[
                    {field:'dou',title:'欣豆',width:"5%",align:"center"},
                    {field:'couponNo',title:'欣券号',width:"15%",align:"center"},
                    {field:'couponBanlance',title:'欣券余额',width:"10%",align:"center",formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'contractNo',title:'合同编号',width:"10%",align:"center"},
                    {field:'merchCouponOrderNo',title:'商户端欣券订单号',width:"15%",align:"center"},
                    {field:'merchCouponOrderBanlance',title:'商户端欣券订单内余额',width:"10%",align:"center",formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'companyName',title:'公司',width:"30%",align:"center"},
                    {field:'rate',title:'费率(%)',width:"5%",align:"center",formatter: function (value, row, index) {
                            if (row != null) {
                                if(value == null){
                                    return "";
                                }else{
                                    return parseFloat(value).toFixed(2);
                                }
                            }
                        }}
                ]]
            });
        }else{
            return false;
        }
    })
</script>
</body>
</html>
