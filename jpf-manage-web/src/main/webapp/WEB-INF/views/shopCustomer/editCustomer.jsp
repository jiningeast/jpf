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
            <input type="hidden" id="id_audit" name="id" value="${payShopCustomer.id}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">注册号：</td>
                    <td>
                        ${payShopCustomer.phone}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">微信昵称：</td>
                    <td>
                        ${payShopCustomer.nickname}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">欣豆数量：</td>
                    <td>
                        ${payShopCustomer.dou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">登录状态：</td>
                    <td>
                        <c:if test="${payShopCustomer.status=='1'}">
                            正常
                        </c:if>
                        <c:if test="${payShopCustomer.status!='1'}">
                            冻结
                        </c:if>

                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">实名认证
                        ：</td>
                    <td>
                    <c:if test="${payShopCustomer.isVerify == 1 }">
                        已认证
                    </c:if>
                        <c:if test="${payShopCustomer.isVerify == 0 }">
                            未认证
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">用户类型：</td>
                    <td>
                        <c:if test="${payShopCustomer.type == 1 }">
                            特殊用户
                        </c:if>
                        <c:if test="${payShopCustomer.type == 0 }">
                            正常用户
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">注册时间：</td>
                    <td>
                        <fmt:formatDate value="${payShopCustomer.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">邮箱：</td>
                    <td>
                        <fmt:formatDate value="${payShopCustomer.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">审核用户类型：</td>
                    <td colspan="4">
                        <select id="type_audit" name="type" class="easyui-combobox" style="width:120px;" data-options="">
                            <option value="">请选择</option>
                            <option value="0">正常用户</option>
                            <option value="1">特殊用户</option>
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

        $('#type_audit').combobox('select', '${payShopCustomer.type}');
        <%--$('#attestation_audit').combobox('select', '${payShopCustomer.attestation==true?0:1}');--%>

    }

    $(function () {

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
        // initData();

        $("#saveBtn_audit").linkbutton({

            onClick: function () {
                var reqUrl = "editCustomer/action";

                var isValid = $("#auditForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                if( $("#type_audit").combobox("getValue") == '' ){
                    $.messager.alert('消息提示', '请选择用户类型', 'error');
                    return ;
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
                            $('#infoUpdate').window('close');
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
                $('#infoUpdate').window('close');
            }
        });
    })
</script>
</body>
</html>
