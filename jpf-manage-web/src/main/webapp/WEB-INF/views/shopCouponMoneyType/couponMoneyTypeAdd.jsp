<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加企业信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="addForm" method="post">
            <h2 align="center">添加面值信息</h2>
            <table cellpadding=3 class="table table-bordered" align="center">
                <tr>
                    <td  style="text-align: right;width:40%" bgcolor="#f1f1f1">面值(元)：</td>
                    <td>
                        <input id="money" name="money" data-options="required:true,validType:'money'" type="text" style="width:150px" class="easyui-textbox" required="true" value=""/>
                    </td>
                </tr>
                <tr>
                    <td  style="text-align: right;width:30%" bgcolor="#f1f1f1">状态：</td>
                    <td>
                        <select id="status" name="status" data-options="required:true" class="easyui-combobox" style="width:120px;">
                            <option value="" selected="selected">--请选择--</option>
                            <option value="0">显示</option>
                            <option value="1">隐藏</option>
                            <option value="2">自定义</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>

    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_m" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">确定</a>
        <a id="cancelBtn_m" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    $(function () {
        $("#saveBtn_m").linkbutton({
            onClick: function () {
                var isValid = $("#addForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                $.ajax({
                    type: 'post',
                    url: '/shopCouponMoneyType/add',
                    data: $('#addForm').serialize(),
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            $('#add').window('close');
                            $('#dg').datagrid('reload');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $('#cancelBtn_m').linkbutton({
            onClick: function () {
                $('#add').window('close');
            }
        });
    })
</script>
</body>
</html>
