<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商户充值信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div id="formDiv1" class="easyui-panel"  region="center"  style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="auditForm" method="post" enctype="multipart/form-data">
            <input type="hidden" id="id_audit" name="id" value="${shopCompanyChargeInfo.id}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值公司：</td>
                    <td>${shopCompanyChargeInfo.companyName}</td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">合同金额：</td>
                    <td>${shopCompanyChargeInfo.contractMoney}</td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">费率 (%)：</td>
                    <td>${shopCompanyChargeInfo.rate} </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">实际到帐：</td>
                    <td>${shopCompanyChargeInfo.money}</td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">付款凭证：</td>
                    <td><img src="${shopCompanyChargeInfo.imgUrl}" width="200" height="200" /></td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">添加人：</td>
                    <td>${shopCompanyChargeInfo.operatorName}</td>
                </tr>
                <c:if test="${shopCompanyChargeInfo.checkOperatorName}" >
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">审核人：</td>
                        <td>${shopCompanyChargeInfo.checkOperatorName}</td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">审核时间：</td>
                        <td><fmt:formatDate value="${shopCompanyChargeInfo.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                </c:if>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">状态：</td>
                    <td>${shopCompanyChargeInfo.statusCn}</td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                    <td><fmt:formatDate value="${shopCompanyChargeInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                    <td><fmt:formatDate value="${shopCompanyChargeInfo.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">审核状态：</td>
                    <td colspan="4">
                        <select id="status_audit" name="status" class="easyui-combobox" style="width:120px;" data-options="">
                            <option value="">请选择</option>
                            <c:if test="${auditPageType == 1 }">
                                <option value=-1>已取消</option>
                                <%--<option value="0">申请</option>--%>
                            </c:if>
                                <%--<option value="0">新增</option>--%>
                            <c:if test="${auditPageType == 2 }">
                                <option value=1>审核通过并充值 </option>
                                <option value=2>审核拒绝</option>
                            </c:if>
                        </select>
                    </td>
            </table>
        </form>
    </div>
    <div region="south" border="false"     style=" height: 30px; line-height: 30px;">
        <a id="saveBtn_audit" class="easyui-linkbutton" icon="icon-ok"  href="javascript:void(0)">确定</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<script>

    function initData() {

        //$('#status_audit').combobox('select', '${shopCompanyChargeInfo.status}');

    }

    $(function () {

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);


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

                if ( $("#status_audit").combobox('getValue') =="" ) {
                    $.messager.alert('消息提示', "请选择审核状态", 'info');
                    return;
                }

                var queryArray = $('#auditForm').serializeArray();
                var postData = parsePostData(queryArray);
                if( postData.status==1 ){
                    $.messager.confirm('确认','确认充值吗',function(r){
                        if (r){
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
                }else{
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
                                $('#infoDiv').window('close');
                                $('#dg').datagrid('reload');
                            }
                        },
                        error: function () {
                            $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                        }
                    });
                }
            }
        });

    })
</script>

</body>
</html>
