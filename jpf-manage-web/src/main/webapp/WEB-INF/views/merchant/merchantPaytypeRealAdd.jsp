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
            <input type="hidden" name="mtsid" id="mtsid" value="${merchantInfo.id}">
            <input type="hidden" name="tpid" id="tpid" value="${merchantTypeInfo.catid}">
            <table cellpadding=3 class="table table-bordered" id="wx" class="paramTable" style="display: none">
                <tr>
                    <th>支付配置</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td>${merchantTypeInfo.cat}</td>
                    <td></td><td></td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数1:
                    </td>
                    <td>
                        <input id="wx_merSubMchid" name="wx_merSubMchid" type="text" style="width:220px" class="easyui-numberbox" value="" data-options="min:0,precision:0,prompt:'请输入数字'" />
                    </td>
                    <td style="text-align: right;width:25%" > </td>
                    <td style="width:25%"></td>
                </tr>
                <tr class="wx_param">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                        <%--<input id="key3" name="key3" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="wx_param3" name="wx_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                        <%--<input id="key4" name="key4" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="wx_param4" name="wx_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr  class="wx_param">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                        <%--<input id="key5" name="key5" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="wx_param5" name="wx_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                        <%--<input id="key6" name="key6" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="wx_param6" name="wx_param6" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
            <table cellpadding=3 class="table table-bordered" id="hb" class="paramTable" style="display: none">
                <tr>
                    <th>支付配置</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td>${merchantTypeInfo.cat}</td>
                    <td style="width:25%;"></td><td style="width:25%;"></td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数1:
                        <%--<input id="key1" name="key1" type="text" style="text-align: right;width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'" />--%>
                    </td>
                    <td>
                        <input id="hb_param1" name="hb_param1" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数2:
                        <%--<input id="key2" name="key2" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'" />--%>
                    </td>
                    <td>
                        <input id="hb_param2" name="hb_param2" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                        <%--<input id="key3" name="key3" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param3" name="hb_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                        <%--<input id="key4" name="key4" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param4" name="hb_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                        <%--<input id="key5" name="key5" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param5" name="hb_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                        <%--<input id="key6" name="key6" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param6" name="hb_param6" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
            <table cellpadding=3 class="table table-bordered" id="yl" class="paramTable" style="display: none">
                <tr>
                    <th>支付配置</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td>${merchantTypeInfo.cat}</td>
                    <td style="width:25%;"></td><td style="width:25%;"></td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数1:
                        <%--<input id="key1" name="key1" type="text" style="text-align: right;width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'" />--%>
                    </td>
                    <td>
                        <input id="yl_param1" name="yl_param1" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数2:
                        <%--<input id="key2" name="key2" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'" />--%>
                    </td>
                    <td>
                        <input id="yl_param2" name="yl_param2" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                        <%--<input id="key3" name="key3" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="yl_param3" name="yl_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                        <%--<input id="key4" name="key4" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="yl_param4" name="yl_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                        <%--<input id="key5" name="key5" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="yl_param5" name="yl_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                        <%--<input id="key6" name="key6" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="yl_param6" name="yl_param6" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
            <table cellpadding=3 class="table table-bordered" id="zy" class="paramTable" style="display: none">
                <tr>
                    <th>支付配置</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td>${merchantTypeInfo.cat}</td>
                    <td style="width:25%;"></td><td style="width:25%;"></td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数1:
                        <%--<input id="key1" name="key1" type="text" style="text-align: right;width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'" />--%>
                    </td>
                    <td>
                        <input id="zy_param1" name="zy_param1" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数2:
                        <%--<input id="key2" name="key2" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'" />--%>
                    </td>
                    <td>
                        <input id="zy_param2" name="zy_param2" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                        <%--<input id="key3" name="key3" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="zy_param3" name="zy_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                        <%--<input id="key4" name="key4" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="zy_param4" name="zy_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                        <%--<input id="key5" name="key5" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="zy_param5" name="zy_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                        <%--<input id="key6" name="key6" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="zy_param6" name="zy_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_m" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">确定</a>
        <a id="cancelBtn_m" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<div id="infoDiv2"></div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    function initData() {
        // $('#tpid_a').combobox('showPanel');
        // $('.key').combobox({prompt:"参数名称"})
    }
    $(function () {
        setTimeout("initData()", 500);
        $("table.paramDiv").hide();
        $("table tr.paramTr").hide();
        //暂时隐藏除wx之外的类型的参数
        var catid = '${merchantTypeInfo.catid}';
        if ( catid == 9 )
        {
            $("#wx").show();
        } else if ( catid == 8 )
        {
            $("#hb").show();
        } else if ( catid == 7 )
        {
            $("#yl").show();
        } else if ( catid == 6 )
        {
            $("#zy").show();
        }
        $(".wx_param").hide();  //微信其它参数暂时隐藏
        $("#saveBtn_m").linkbutton({
            onClick: function () {
                var isValid = $("#addForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $("#addForm").serializeArray();
                var postData = parsePostData(queryArray);
                var mtsid = '${merchantInfo.id}';
                $.ajax({
                    type: 'post',
                    url: '../merchant/paytype/add/addMerPayTypeOne',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $('#infoDiv').window("open").window('refresh', '../merchant/paytype/add/page?id=' + mtsid).window('setTitle','配置支付类型');
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');

                        } else {
                            $('#infoDiv2').window('close');

                            $('#infoDiv').window("open").window('refresh', '../merchant/paytype/add/page?id=' + mtsid).window('setTitle','配置支付类型');
                            $.messager.alert('消息提示', '操作成功！', 'info');
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
                // $('#addForm').form('clear');
                $('#infoDiv2').window('close');
            }
        });

    })
</script>
</body>
</html>
