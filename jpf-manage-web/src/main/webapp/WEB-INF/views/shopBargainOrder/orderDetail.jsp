<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>收购订单详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table  class="table table-bordered">
                    <h2 align="center" >详细信息</h2>
                <tr>
                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">订单号：</td>
                    <td>
                        ${shopBargainOrderInfo.orderNo}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">买家昵称：</td>
                    <td>
                        ${shopBargainOrderInfo.buyerCustomerNickname}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">卖家昵称：</td>
                    <td>
                        ${shopBargainOrderInfo.sellerCustomerNickname}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">最低限额豆数：</td>
                    <td>
                        ${shopBargainOrderInfo.minDou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">折损率：</td>
                    <td>
                        ${shopBargainOrderInfo.offRate}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">使用豆数：</td>
                    <td>
                        ${shopBargainOrderInfo.dou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">转让价：</td>
                    <td>
                        ${shopBargainOrderInfo.transferPrice}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">收款人姓名：</td>
                    <td>
                        ${shopBargainOrderInfo.realName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">手机号：</td>
                    <td >
                        ${shopBargainOrderInfo.phone}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">银行名称：</td>
                    <td >
                        ${shopBargainOrderInfo.bankName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支行名称：</td>
                    <td >
                        ${shopBargainOrderInfo.bankBrank}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">银行卡号：</td>
                    <td >
                        ${shopBargainOrderInfo.bankNo}
                    </td>

                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">身份证号：</td>
                    <td >
                        ${shopBargainOrderInfo.idno}
                    </td>
                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单状态：</td>
                    <td >
                        <c:if  test="${shopBargainOrderInfo.status eq 0 }">待支付</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 1 }">已审核</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 2 }">打款中</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 3 }">打款成功</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 4 }">打款失败</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 5 }">已取消</c:if>
                    </td>
                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">下单时间：</td>
                    <td  >
                        <fmt:formatDate value="${shopBargainOrderInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付时间：</td>
                    <td  >
                        <fmt:formatDate value="${shopBargainOrderInfo.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">运营审核人：</td>
                    <td >
                        ${shopBargainOrderInfo.operatorName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">财务审核人：</td>
                    <td >
                        ${shopBargainOrderInfo.financeName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">付款凭证：</td>
                    <td><img src="${shopBargainOrderInfo.payImg}" width="200" height="200" /></td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;"  >备注：</td>
                    <td  >
                        <textarea title=' ${shopBargainOrderInfo.memo}'style="width: 30%;" rows="5">${shopBargainOrderInfo.memo}</textarea>
                    </td>
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
