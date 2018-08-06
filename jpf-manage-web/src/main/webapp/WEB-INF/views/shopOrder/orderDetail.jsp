<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table  class="table table-bordered">
                <tr>
                    <th>详细信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单号：</td>
                    <td>
                        ${shopOrderInfo.orderNo}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">客户名称：</td>
                    <td>
                        ${shopOrderInfo.customerName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商品名称：</td>
                    <td>
                        ${shopOrderInfo.productName}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">商品价格：</td>
                    <td>
                        ${shopOrderInfo.productMoney}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商品数量：</td>
                    <td>
                        ${shopOrderInfo.amount}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">欣豆数量：</td>
                    <td>
                        ${shopOrderInfo.productDou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商品图片：</td>
                    <td>
                        <img src=" ${shopOrderInfo.image}" style="width: 100px;height: 100px;">

                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">商品进价：</td>
                    <td>
                        ${shopOrderInfo.bid}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">客户联系电话：</td>
                    <td >
                        ${shopOrderInfo.phone}
                    </td>

                    <td style="text-align: right;background-color: #f1f1f1;">券消费详情：</td>
                    <td >
                        ${shopOrderInfo.couponDetail}
                    </td>

                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值号：</td>
                    <td >
                        ${shopOrderInfo.chargeNo}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">支付方式：</td>
                    <td colspan="3">
                        <c:if  test="${shopOrderInfo.payWay eq 0 }">欣豆支付</c:if>
                        <c:if  test="${shopOrderInfo.payWay eq 1 }">微信支付</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单总金额：</td>
                    <td >
                        ${shopOrderInfo.totalMoney}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">订单总豆数量：</td>
                    <td >
                        ${shopOrderInfo.totalDou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单状态：</td>
                    <td >
                        <c:if  test="${shopOrderInfo.status eq 0 }">待支付</c:if>
                        <c:if  test="${shopOrderInfo.status eq 1 }">已支付</c:if>
                        <c:if  test="${shopOrderInfo.status eq 2 }">支付失败</c:if>
                        <c:if  test="${shopOrderInfo.status eq 3 }">已取消</c:if>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">下单时间：</td>
                    <td  >
                        <fmt:formatDate value="${shopOrderInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付时间：</td>
                    <td  >
                        <fmt:formatDate value="${shopOrderInfo.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="closeBtn_detail" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">关闭</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>

    $(function () {

        $('#closeBtn_detail').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });

    })
</script>
</body>
</html>
