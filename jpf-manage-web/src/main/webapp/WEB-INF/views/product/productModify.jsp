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
                <input type="hidden" id="id_m" name="pid" value="${productInfo.pid}">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>基本信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">商户ID：</td>
                        <td>
                            ${productInfo.mtsid}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">商品状态：</td>
                        <td>
                            <select id="status_p" name="status" data-options="required:true" class="easyui-combobox" style="width:120px;">
                                <option value=""></option>
                                <option value="1">上架</option>
                                <option value="0">下架</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">产品名称：</td>
                        <td>
                            <input id="pname" name="pname" type="text" style="width:220px" class="easyui-textbox" required="true"  value="${productInfo.pname}"  />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">产品价格：</td>
                        <td>
                            <input id="pmoney" name="pmoney" type="text" style="width:220px" class="easyui-textbox" value="${productInfo.pmoney}" data-options="required:true,validType:'floatNumber'" />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">支付方式：</td>
                        <td>
                            <select id="paytype" name="zftype" class="easyui-combobox" style="width:150px;" data-options="required:true,multiple:true">
                            </select>
                        </td>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">创建时间：</td>
                        <td>
                            <fmt:formatDate value="${productInfo.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                        <td colspan="3">
                            <fmt:formatDate value="${productInfo.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">产品简介：</td>
                        <td colspan="3">
                            <input id="pintro" name="pintro" type="text" style="width:90%;height: 60px;" class="easyui-textbox" value="${productInfo.pintro}" data-options="required:true,multiline:true,formatter:formatPrice"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">产品图片：</td>
                        <td colspan="3">
                            <img width="150" height="150" src="${productInfo.pdpicture}">
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
    $('#pmoney').textbox({
        inputEvents: $.extend({},$.fn.textbox.defaults.inputEvents,{
            blur:function(event){
                if ( $(this).val() )
                {
                    var price = parseFloat($(this).val()).toFixed(2);
                    $(this).val(price);
                    $("#editForm tbody input[name='pmoney']").val(price);
                }
            }
        })
    });

    function initData() {
        $('#status_p').combobox('select', '${productInfo.status}');
    }
    $.extend($.fn.validatebox.defaults.rules, {
        floatNumber : {//数字（包括正整数、0、浮点数）
            validator: function(value, param){
                return isFloat(value);
            },
            message: '请输入正确的数字'
        }
    });
    function isFloat(z_check_value){
        var z_reg = /^((([0-9])|([1-9][0-9]+))(\.([0-9]{0,2}))?)$/;//.是特殊字符，需要转义
        return z_reg.test($.trim(z_check_value));
    }

    $(function () {
        $('#paytype').combobox({
            url:'../param/getType?pid=5',
            valueField:'catid',
            textField:'cat',
            onLoadSuccess: function () {  //下拉框数据加载成功调用
                $('#paytype').combobox('setValues', '${productInfo.zftype}');
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
                // console.log(postData);
                var param = {};
                param["pid"] = $("#id_m").val();
                param["status"] = $("#editForm tbody input[name='status']").val();
                param["pname"]  = $("#editForm tbody input[name='pname']").val();
                param["pintro"] = $("#editForm tbody input[name='pintro']").val();
                param["pmoney"] = $("#editForm tbody input[name='pmoney']").val();
                param['zftype'] = new Array();
                $("#editForm tbody input[name='zftype']").each(function (i) {
                    param['zftype'][i] = $(this).val()
                });
                $.ajax({
                    type: 'post',
                    url: '../product/modify/action',
                    data: JSON.stringify(param),
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
