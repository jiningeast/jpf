<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
    <div class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form id="editForm" method="post">
                <input type="hidden" id="id_m" name="id" value="${rolesInfo.id}">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>基本信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">角色ID：</td>
                        <td>
                            ${rolesInfo.id}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">角色状态：</td>
                        <td>
                            <select id="status_p" name="status" data-options="required:true" class="easyui-combobox" style="width:120px;">
                                <option value=""></option>
                                <option value="0">正常</option>
                                <option value="1">禁用</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">角色名称：</td>
                        <td>
                            <input id="name" name="name" type="text" style="width:150px" class="easyui-textbox" required="true"  value="${rolesInfo.name}"  />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">角色简介：</td>
                        <td>
                            <input id="intro" name="intro" type="text" style="width:220px" class="easyui-textbox" value="${rolesInfo.intro}" data-options="required:true" />
                        </td>
                    </tr>
                    <tr>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">创建时间：</td>
                        <td>
                            <fmt:formatDate value="${rolesInfo.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
    function initData() {
        $('#status_p').combobox('select', '${rolesInfo.status}');
        <%--$('#paytype').combobox('select', '${rolesInfo.zftype}');--%>
    }

    $(function () {
        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
        // initData();

        $("#saveBtn_m").linkbutton({
            onClick: function () {
                var isValid = $("#editForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $('#editForm').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: '../roles/modify/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'error');
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

        $('#cancelBtn_m').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>
