<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>欣豆市场中欣卡订单审核</title>
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
                    <th>中欣卡订单基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单号：</td>
                    <td>
                        ${shopOrderInfo.orderNo}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">商品名称: </td>
                    <td>
                        ${shopOrderInfo.productName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">欣豆数量：</td>
                    <td>
                        ${shopOrderInfo.productDou}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">购买数量：</td>
                    <td>
                        ${shopOrderInfo.amount}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商品进价：</td>
                    <td>
                        ${shopOrderInfo.bid}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">商品价格: </td>
                    <td>
                        ${shopOrderInfo.productMoney}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">客户名称：</td>
                    <td>
                        ${shopOrderInfo.customerName}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">客户联系电话：</td>
                    <td >
                        ${shopOrderInfo.phone}
                    </td>
                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单金额：</td>
                    <td>
                        ${shopOrderInfo.totalMoney}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">订单总豆数量：</td>
                    <td >
                        ${shopOrderInfo.totalDou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付方式：</td>
                    <td>
                        <c:if  test="${shopOrderInfo.payWay == 0 }">豆支付</c:if>
                        <c:if  test="${shopOrderInfo.payWay == 1 }">微信支付</c:if>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">订单状态：</td>
                    <td >
                        <c:if  test="${shopOrderInfo.status == 0 }">待支付</c:if>
                        <c:if  test="${shopOrderInfo.status == 1 }">已支付</c:if>
                        <c:if  test="${shopOrderInfo.status == 2 }">支付失败</c:if>
                        <c:if  test="${shopOrderInfo.status == 3 }">已取消</c:if>
                        <c:if  test="${shopOrderInfo.status == 4 }">充值成功</c:if>
                        <c:if  test="${shopOrderInfo.status == 5 }">充值失败</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">下单时间：</td>
                    <td>
                        <fmt:formatDate value="${shopOrderInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">支付时间：</td>
                    <td  >
                        <fmt:formatDate value="${shopOrderInfo.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值号：</td>
                    <td >
                        ${shopOrderInfo.chargeNo}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">合同编号：</td>
                    <td>
                        <%--${shopOrderInfo.contractNo}--%>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">企业名称：</td>
                    <td colspan="3">
                        <%--${shopOrderInfo.companyName}--%>
                    </td>
                </tr>
                
            </table>
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>中欣卡订单审核</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">审核：</td>
                    <td colspan="3">
                        <select id="status_audit" name="status" class="easyui-combobox" style="width:120px;" data-options="">
                            <option value="">请选择</option>
                            <c:if test="${type == 1 }">
                                <option value="3"  <c:if  test="${shopOrderInfo.status == '3' }">selected</c:if>>取消</option>
                                <option value="1" <c:if  test="${shopOrderInfo.status == '1' }">selected</c:if> >审批</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_audit" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">确定</a>
        <a id="cancelBtn_audit" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<!-- /添加弹出窗口 -->

<script>
    function initData() {

        $('#status_audit').combobox('select', '${shopOrderInfo.status}');
        <%--$('#attestation_audit').combobox('select', '${cloudRechargeInfo.attestation==true?0:1}');--%>

    }

    $(function () {
        /*$('#auditForm #province_audit').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#auditForm #city_audit').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });*/


        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
        // initData();

        $("#saveBtn_audit").linkbutton({
            onClick: function () {
                var reqUrl = "audit/action";
                var isValid = $("#auditForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $('#auditForm').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: reqUrl ,
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', msg.retMsg, 'info');
                            $('#edit').window('close');
                            $('#dg').datagrid('reload');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $('#cancelBtn_audit').linkbutton({
            onClick: function(){
                $('#edit').window('close');
            }
        });
    })
</script>
</body>
</html>
