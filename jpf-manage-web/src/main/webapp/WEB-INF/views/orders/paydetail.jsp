<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商户信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <table cellpadding=3 class="table table-bordered">
            <tr>
                <th>银行卡信息</th>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1" class="column1">商户ID：</td>
                <td class="column2">
                    ${merchantInfo.id}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;" class="column1">聚合商户名称：</td>
                <td class="column2">
                    ${merchantInfo.merchName}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">用户名：</td>
                <td>
                    ${orderCpInfo.signedname}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">证件类型：</td>
                <td>
                    <c:if test="${orderCpInfo.idtype == 1}">身份证</c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">身份证号：</td>
                <td>
                    ${orderCpInfo.idno}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">手机号：</td>
                <td>
                    ${orderCpInfo.mobileno}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">银行编码：</td>
                <td>
                    ${orderCpInfo.selectfinacode}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">银行卡类型：</td>
                <td>
                    <c:if test="${orderCpInfo.bankaccounttype == 1}">借记卡</c:if>
                    <c:if test="${orderCpInfo.bankaccounttype == 2}">贷记卡</c:if>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">银行名称：</td>
                <td>
                    ${orderCpInfo.bankname}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">银行卡号：</td>
                <td>
                    ${orderCpInfo.bankaccountnumber}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">cvn2：</td>
                <td>
                    ${orderCpInfo.cvn2}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">信用卡有效期：</td>
                <td>
                    ${orderCpInfo.validitycard}
                </td>
            </tr>
            <tr>

                <td style="text-align: right;background-color: #f1f1f1;">签约状态：</td>
                <td>
                    <c:if test="${orderCpInfo.signstatus == 1}">未完成</c:if>
                    <c:if test="${orderCpInfo.signstatus == 2}">成功</c:if>
                    <c:if test="${orderCpInfo.signstatus == 3}">失败</c:if>
                    <c:if test="${orderCpInfo.signstatus == 6}">关闭</c:if>
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">签约流水号：</td>
                <td>
                    ${orderCpInfo.tranno}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">签约时间：</td>
                <td>
                    <fmt:formatDate value="${orderCpInfo.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td style="text-align: right;background-color: #f1f1f1;"></td><td></td>
            </tr>
        </table>
        <table cellpadding=3 class="table table-bordered">
            <tr>
                <th>订单信息</th>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;" class="column1">订单号：</td>
                <td class="column2" >
                    ${apiInfo.orderid}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;" class="column1">商户订单号：</td>
                <td class="column2">
                    ${apiInfo.foreignOrderid}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">商品名称：</td>
                <td>
                    ${apiInfo.productName}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">聚合商户名称：</td>
                <td>
                    ${apiInfo.merchName}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">实付金额：</td>
                <td>
                    ${apiInfo.orderPayPrice}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">支付方式：</td>
                <td>
                    ${apiInfo.cat}
                </td>

            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">订单金额：</td>
                <td>
                    ${apiInfo.orderStdPrice}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">商品数量：</td>
                <td>
                    ${apiInfo.productAccount}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">支付时间：</td>
                <td>
                    <fmt:formatDate value="${apiInfo.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">支付详情：</td>
                <td>
                    ${payDetaiStr}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">支付状态：</td>
                <td>
                    <c:if test="${apiInfo.payStatus == 0}">未支付</c:if>
                    <c:if test="${apiInfo.payStatus == 1}">支付成功</c:if>
                    <c:if test="${apiInfo.payStatus == 2}">支付失败</c:if>
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">退款状态：</td>
                <td>
                    ${apiInfo.refundStatus_cn}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">订单状态：</td>
                <td>
                    ${apiInfo.userOperateStatus_cn}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                <td>
                    <fmt:formatDate value="${apiInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
        </table>
        <table cellpadding=3 class="table table-bordered">
            <tr>
                <th>费率信息</th>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;" class="column1">ID：</td>
                <td class="column2" >
                    ${ordersMoneyInfo.id}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;" class="column1">订单号：</td>
                <td class="column2">
                    ${ordersMoneyInfo.orderid}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">订单金额：</td>
                <td>
                    ${ordersMoneyInfo.money}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">分期期数：</td>
                <td>
                    ${ordersMoneyInfo.stageName}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">分期费率：</td>
                <td>
                    ${ordersMoneyInfo.stageRate}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">分期费率金额：</td>
                <td>
                    ${ordersMoneyInfo.stageMoney}
                </td>

            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">商户费率：</td>
                <td>
                    ${ordersMoneyInfo.merchRate}
                </td>
                <td style="text-align: right;background-color: #f1f1f1;">商户费率金额：</td>
                <td>
                    ${ordersMoneyInfo.merchMoney}
                </td>
            </tr>
            <tr>
                <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                <td>
                    <fmt:formatDate value="${ordersMoneyInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td style="text-align: right;background-color: #f1f1f1;"></td>
                <td></td>
            </tr>
        </table>
        <table cellpadding=3 class="table table-bordered">
            <tr>
                <th>支付信息</th>
            </tr>
            <c:if test="${!empty returnList}" >
                <c:forEach items="${returnList}" var="one" varStatus="status" >
                    <tr>

                        <td style="text-align: right;background-color: #d0e9c6;" class="column1 " >订单号：</td>
                        <td class="column2" style="word-break: break-all;" colspan="3">
                                ${one.orderid}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">请求信息：</td>
                        <td class="column2" style="word-break: break-all;" colspan="3">
                                ${one.content}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;"  class="column1" >同步流水号：</td>
                        <td class="column2" style="word-break: break-all;">
                                ${one.returnTranno}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;"  class="column1">同步返回信息：</td>
                        <td style="word-break: break-all;" class="column2" >
                                ${one.returnContent}
                        </td>

                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                        <td>
                            <fmt:formatDate value="${one.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                        <td>
                            <fmt:formatDate value="${one.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <table cellpadding=3 class="table table-bordered">
            <tr>
                <th>支付回调信息</th>
            </tr>
            <c:if test="${!empty notifyList}" >
                <c:forEach items="${notifyList}" var="one" varStatus="status" >
                    <tr >

                        <td style="text-align: right;background-color: #d0e9c6;" class="column1">ID：</td>
                        <td class="column2" style="word-break: break-all;" >
                                ${one.id}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;" class="column1" >商户发送信息ID：</td>
                        <td class="column2" style="word-break: break-all;">
                                ${one.mermessageId}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;"  class="column1" >订单号：</td>
                        <td class="column2" style="word-break: break-all;">
                                ${one.orderid}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;"  class="column1">异步流水号：</td>
                        <td style="word-break: break-all;" class="column2" >
                                ${one.notifyTranno}
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td style="text-align: right;background-color: #f1f1f1;"  class="column1" >同步流水号：</td>--%>
                        <%--<td class="column2" style="word-break: break-all;">--%>
                                <%--${one.returnTranno}--%>
                        <%--</td>--%>
                        <%--<td style="text-align: right;background-color: #f1f1f1;"  class="column1">同步返回信息：</td>--%>
                        <%--<td style="word-break: break-all;" class="column2" >--%>
                                <%--${one.returnContent}--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">异步返回信息：</td>
                        <td style="word-break: break-all;" colspan="3">
                                ${one.notifyContent}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                        <td>
                            <fmt:formatDate value="${one.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                        <td>
                            <fmt:formatDate value="${one.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <table cellpadding=3 class="table table-bordered">
            <tr>
                <th>支付--发送给商户的信息</th>
            </tr>
            <c:if test="${!empty payMerMsgList}" >
                <c:forEach items="${payMerMsgList}" var="one" varStatus="status" >
                    <tr >
                        <td style="text-align: right;background-color: #d0e9c6;" class="column1">ID：</td>
                        <td class="column2" style="word-break: break-all;" >
                                ${one.id}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;" class="column1" >订单ID：</td>
                        <td class="column2" style="word-break: break-all;">
                                ${one.orderid}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;"  class="column1" >商户订单号：</td>
                        <td class="column2" style="word-break: break-all;">
                                ${one.foreignOrderid}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;"  class="column1">商户返回信息：</td>
                        <td style="word-break: break-all;" class="column2" >
                                ${one.notifyResult}
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">发送信息：</td>
                        <td style="word-break: break-all;" colspan="3">
                                ${one.notifyContent}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                        <td>
                            <fmt:formatDate value="${one.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                        <td>
                            <fmt:formatDate value="${one.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="closeBtn_detail" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">关闭</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
    .column1{width:13%;}
    .column2{width:30%;}
    .border-top{border-top: 1px double red;}
</style>
<script>
    function initData() {
        <%--$('#province_detail').combobox('select', '${orderCpInfo.province}');--%>
        <%--$('#city_detail').combobox('select', '${orderCpInfo.city}');--%>
        <%--$('#status_detail').combobox('select', '${orderCpInfo.status}');--%>
        <%--$('#attestation_detail').combobox('select', '${orderCpInfo.attestation==true?0:1}');--%>
        <%--$('#banktype_detail').combobox('select', '${merchantBankInfo.banktype}');--%>
        <%--$('#bankProvince_detail').combobox('select', '${merchantBankInfo.province}');--%>
        <%--$('#bankCity_detail').combobox('select', '${merchantBankInfo.city}');--%>
    }

    $(function () {
        $('#province_detail').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#city_detail').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#bankProvince_detail').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#bankCity_detail').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#banktype_detail').combobox({
            url:'../param/getType?pid=17',
            valueField:'catid',
            textField:'cat'
        });

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
        // initData();

        $('#closeBtn_detail').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>
