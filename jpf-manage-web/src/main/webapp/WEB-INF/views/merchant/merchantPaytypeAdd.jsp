<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>设置支付类型</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="addForm" method="post">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>设置支付类型</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商户Id：</td>
                    <td>${mtsid}</td>
                    <td style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td>
                        <select id="tpid_a" name="tpid" class="easyui-combobox" style="width:100px;" data-options="required:true">
                        </select>
                    </td>
                    <td colspan="2">
                        <a id="affirmBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认</a>
                    </td>
                </tr>
            </table>
        </form>

        <form id="savePaytype_From" method="post">
            <input id="mtsid_a" name="mtsid" type="hidden" value = "${mtsid}" />
            <%--<input type="hidden" name="tpid" />--%>
            <table id="savePaytypeShow_Table" cellpadding=3 class="table table-bordered">
                <tr>
                    <td style="text-align: left;background-color: #f1f1f1;">支付类型</td>
                    <td style="text-align: left;background-color: #f1f1f1;">操作</td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_a" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">保存</a>
        <a id="cancelBtn_a" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    var paytypes;
    $.ajax({
        type: "POST",
        async: false,
        url: "../param/getType?pid=5",
        success: function(data){
            paytypes = data;
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){

        }
    });

    function removeInfo(tpid) {
        $("#" + tpid + "_input").remove();
        $("#" + tpid + "_text").remove();
    }

    $(function () {

        $("#tpid_a").combobox({
            data:paytypes,
            valueField:'catid',
            textField:'cat'
        });

        $("#affirmBtn").linkbutton({
            onClick:function(){

                var isValid = $("#addForm").form('enableValidation').form('validate');
                if(!isValid){
                    return;
                }
                var tpid = $("#tpid_a").combobox("getValue");

                var tpidText = $("#tpid_a").combobox('getText');

                var formInfo = "<input type='hidden' id='"+tpid+"_input' name='tpid' value='"+tpid+"' />";

                var textInfo = "<tr id='"+tpid+"_text'>"
                    +"<td style='text-align: left;'>"+tpidText+"</td>"
                    +"<td style='text-align: left;'><a class='easyui-linkbutton' icon='icon-ok' href='javascript:removeInfo("+tpid+")'>移除</a></td>"
                    +"</tr>";

                $("#" + tpid + "_input").remove();
                $("#" + tpid + "_text").remove();

                $("#savePaytypeShow_Table tr:first").after(textInfo).after(formInfo);
            }
        });


        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        // setInterval("initData()", 500);
        // initData();
        $("#saveBtn_a").linkbutton({
            onClick: function () {
                var queryArray = $('#savePaytype_From').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: '../merchant/paytype/add/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'error');
                            $('#infoDiv').window('close');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $('#cancelBtn_a').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>
