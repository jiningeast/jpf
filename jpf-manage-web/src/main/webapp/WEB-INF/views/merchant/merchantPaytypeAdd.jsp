<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form id="addForm" method="post">
            <input type="hidden" id="id_m" name="id" value="">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商户Id：</td>
                    <td><input id="mtsid_a" name="mtsid" type="text" readonly="readonly" style="width:220px" class="easyui-textbox"/></td>
                    <td style="text-align: right;background-color: #f1f1f1;">付类型：</td>
                    <td>
                        <select id="tpid_a" name="tpid" class="easyui-combobox" style="width:100px;">
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_a" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">确定</a>
        <a id="cancelBtn_a" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    function initData() {
        $('#merProperty_a').combobox('select', '${merchInfo.merProperty}');
        $('#merCorporationPaperType_a').combobox('select', '${merchInfo.merCorporationPaperType}');
        $('#manageScope_a').combobox('select', '${merchInfo.manageScope}');
        $('#virtualGoodsIsExist_a').combobox('select', '${merchInfo.virtualGoodsIsExist}');
        $('#settleBankType_a').combobox('select', '${merchInfo.settleBankType}');
        $('#proFtAcctNoType_a').combobox('select', '${merchInfo.proFtAcctNoType}');
        $('#merStauts_a').combobox('select', '${merchInfo.merStauts}');
        $('#merLevel_a').combobox('select', '${merchInfo.merLevel}');
        $('#merTransType_a').combobox('select', '${merchInfo.merTransType}');
        $('#merServiceType_a').combobox('select', '${merchInfo.merServiceType}');
        $('#settleType_a').combobox('select', '${merchInfo.settleType}');
        $('#settleMode_a').combobox('select', '${merchInfo.settleMode}');
        $('#settlePeriod_a').combobox('select', '${merchInfo.settlePeriod}');
        $('#settleFeePeriod_a').combobox('select', '${merchInfo.settleFeePeriod}');
        $('#settleFeeMode_a').combobox('select', '${merchInfo.settleFeeMode}');
        $('#parentMerIsExist_a').combobox('select', '${merchInfo.parentMerIsExist}');
    }

    $(function () {
        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        // setInterval("initData()", 500);
        // initData();
        $("#saveBtn_a").linkbutton({
            onClick: function () {
                var isValid = $("#form_a").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $('#form_a').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: '/info/oper',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '更新失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '添加成功！', 'error');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });
    })
</script>
</body>
</html>
