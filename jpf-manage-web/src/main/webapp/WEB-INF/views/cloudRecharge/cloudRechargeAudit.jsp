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
        <form id="auditForm" method="post">
            <input type="hidden" id="id_audit" name="id" value="${cloudRechargeInfo.id}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">代理聚合商户号：</td>
                    <td>
                        ${cloudRechargeInfo.agentNo}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">到账聚合商户号：</td>
                    <td>
                        ${cloudRechargeInfo.merchNo}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">需求ID：</td>
                    <td>
                        ${cloudRechargeInfo.needid}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">合同编号：</td>
                    <td>
                        <c:if test="${cloudRechargeInfo.status=='1'}">
                            <input name="pactno" value="" type="text">
                        </c:if>
                        <c:if test="${cloudRechargeInfo.status!='1'}">
                            ${cloudRechargeInfo.pactno}
                        </c:if>

                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付方式：</td>
                    <td>
                        ${cloudRechargeInfo.payway}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">操作人ID：</td>
                    <td>
                        ${cloudRechargeInfo.employeeUid}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">手机号：</td>
                    <td>
                        ${cloudRechargeInfo.linkphone}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">邮箱：</td>
                    <td>
                        ${cloudRechargeInfo.linkemail}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值金额：</td>
                    <td>
                        ${cloudRechargeInfo.money}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">实际到账金额：</td>
                    <td>
                        ${cloudRechargeInfo.realmoney}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">手续费金额：</td>
                    <td colspan="4">
                        ${cloudRechargeInfo.feemoney}
                    </td>

                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">代理手续费：</td>
                    <td >
                        ${cloudRechargeInfo.agentFeemoney}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">服务平台手续费：</td>
                    <td >
                        ${cloudRechargeInfo.salesFeemoney}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">代理平台费率0.00:不收取费率：</td>
                    <td >
                        ${cloudRechargeInfo.agentRate}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">服务平台费费率：</td>
                    <td >
                        ${cloudRechargeInfo.salesRate}
                    </td>
                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付凭证：</td>
                    <td colspan="3">
                        <c:if test="${cloudRechargeInfo.imgurl!='' && cloudRechargeInfo.imgurl!=null }">
                            <img width="200" height="200" src="${cloudRechargeInfo.imgurl}" />
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                    <td>
                        <fmt:formatDate value="${cloudRechargeInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                    <td>
                        <fmt:formatDate value="${cloudRechargeInfo.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">审核时间：</td>
                    <td >
                        <fmt:formatDate value="${cloudRechargeInfo.shenhetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">充值时间：</td>
                    <td>
                        ${cloudRechargeInfo.chargetime}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值状态：</td>
                    <td >
                        ${cloudRechargeInfo.status_cn}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">需求确认时间：</td>
                    <td >
                        ${cloudRechargeInfo.pacttime}
                    </td>
                </tr>
            </table>
            <table cellpadding=4 class="table table-bordered">
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">审核：</td>
                    <td colspan="4">
                        <select id="status_audit" name="status" class="easyui-combobox" style="width:120px;" data-options="">
                            <option value="">请选择</option>
                            <option value="0">已取消</option>
                            <option value="1">已申请</option>
                            <option value="2">已审核(待上传付款凭证)</option>
                            <option value="3">已支付(已上传凭证)</option>
                            <option value="4">已充值开票中</option>
                            <option value="5">已充值已开票</option>
                            <option value="6">已发货</option>
                            <option value="7">已完成</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">备注：</td>
                    <td colspan="4">
                        <textarea id="kfremarks" name="kfremarks"   style="width:500px; height:60px;">${cloudRechargeInfo.kfremarks}</textarea>
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

        $('#status_audit').combobox('select', '${cloudRechargeInfo.status}');
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
                if( ${auditPageType == 2 } ){
                    reqUrl = "caiwu/audit/action";
                }
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
                            $.messager.alert('消息提示', msg.retMsg, 'error');
                            $('#infoDiv').window('close');
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
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>
