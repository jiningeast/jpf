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
            <form id="editForm" method="post">
                <input type="hidden" id="id_m" name="mtsid" value="${merchantInfo.id}">
                <input type="hidden" id="id_a" name="id" value="${merpayTypeInfoOne.id}">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>代理信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户号：</td>
                        <td>
                            ${merchantInfo.merchNo}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户名称：</td>
                        <td style="width: 20%">
                            ${merchantInfo.merchName}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">用户名：</td>
                        <td>
                            ${merchantInfo.username}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">分期信息：</td>
                        <%--<input type="hidden" name="bankcatid_curr" value="${merpayTypeInfoOne.bankcatid}">--%>
                        <td>
                            <select id="bankcatid" name="bankcatid" class="easyui-combobox" data-options="required:true,multiple:true" style="width:100px;">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                        <td>
                            <fmt:formatDate value="${merpayTypeInfoOne.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                        <td>
                            <fmt:formatDate value="${merpayTypeInfoOne.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
    /*.trLevel{display: none}*/
    td.level{
        height:200px;
        width: 85%;
    }
    .div-level{
        height: 200px;
        overflow-y: auto;
        width: 100%;
    }
    .div-level span label{
        margin-left: 5px;
    }
    .level span{
        display: inline-block;
        height: 30px;
        width:24%;
        word-break: keep-all;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
    }
</style>
<script>
    function initData() {
    }

    $(function () {
        //隐藏所有
        $("tr.trlevel").hide();

        //等级信息
        $('#editForm #bankcatid').combobox({
            url:'../../param/getType?pid=24',
            valueField:'catid',
            textField:'cat',
            onLoadSuccess: function () {  //下拉框数据加载成功调用
                $('#bankcatid').combobox('setValues', '${merpayTypeInfoOne.bankcatid}');
            }
        });

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
                    url: '../../merchant/paytype/stage/action',
                    data: JSON.stringify(postData),
                    dataType: 'json',
                    contentType : 'application/json;charset=utf-8',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
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
