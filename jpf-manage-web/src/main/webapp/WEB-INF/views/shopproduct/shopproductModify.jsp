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
            <form id="editForm" method="post" enctype="multipart/form-data" accept="image/gif, image/jpeg,image/jpg, image/png">
                <input type="hidden" name="id" value="${productOne.id}" />
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>添加商品</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">商品基础信息：</td>
                        <td>
                            <select id="productInfoId" name="productInfoId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">状态:</td>
                        <td>
                            <select id="status_p" name="status" data-options="required:true" class="easyui-combobox" style="width:120px;">
                                <option value=""></option>
                                <option value="1">上架</option>
                                <option value="0">下架</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">商品名称：</td>
                        <td>
                            <input id="product_name" name="name" type="text" style="width:220px" class="easyui-textbox" value="${productOne.name}" data-options="required:true"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">商品编号：</td>
                        <td>
                            <input id="product_cardid" name="cardid" type="text" style="width:220px" class="easyui-textbox" value="${productOne.cardid}" />
                        </td>
                        <%--<td style="text-align: right;background-color: #f1f1f1;">接口形式：</td>--%>
                        <%--<td>--%>
                            <%--<select id="stored_type" name="storedType" data-options="required:true" class="easyui-combobox" style="width:120px;">--%>
                                <%--<option value="0">否</option>--%>
                                <%--<option value="1">是</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">标准进价：</td>
                        <td>
                            <input id="bid" name="bid" type="text" style="width:220px" class="easyui-textbox" value="${productOne.bid}" data-options="required:true,validType:'floatNumber'" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">商品售价：</td>
                        <td>
                            <input id="money" name="money" type="text" style="width:220px" class="easyui-textbox" value="${productOne.money}" data-options="required:true,validType:'floatNumber'" />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">充值面额：</td>
                        <td>
                            <input id="rechargeMoney" name="rechargeMoney" type="text" style="width:220px" class="easyui-numberbox" value="${productOne.rechargeMoney}" data-options="required:true,min:0,precision:0,prompt:'请输入数字'" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">欣豆：</td>
                        <td>
                            <input id="dou" name="dou" type="text" style="width:220px" class="easyui-numberbox" value="${productOne.dou}" data-options="required:true,min:0,precision:0,prompt:'请输入数字'" />
                        </td>

                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">安全库存：</td>
                        <td>
                            <input id="stored_safe" name="storedSafe" type="text" style="width:220px" class="easyui-numberbox" value="${productOne.storedSafe}" data-options="required:true,min:0,precision:0,prompt:'请输入数字'" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">当前库存：</td>
                        <td>
                            <input id="stored" name="stored" type="text" style="width:220px" class="easyui-numberbox" value="${productOne.stored}" data-options="required:true,min:0,precision:0,prompt:'请输入数字'" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;"></td>
                        <td> </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">商品描述：</td>
                        <td colspan="3">
                            <input id="intro" value="${productOne.intro}" name="intro" type="text" style="width:90%;height: 60px;" class="easyui-textbox" data-options="required:true,multiline:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">图片地址：</td>
                        <td colspan="3">
                            <%--<input id="uploadfile" class="easyui-filebox" name="uploadfile" style="width: 238px;" data-options="required:true">--%>
                            <p>
                                上传文件： <input type="file" name="file" id="uploadfile">
                                <input type="button" value="上传" onclick="doUploadImg()"/>
                            </p>
                            <input id="imgurl" name="image" type="hidden" style="width:150px" data-options="required:true" value=""/>
                                <%--https://yifuka.oss-cn-beijing.aliyuncs.com/clouds/1533103063417.jpg--%>
                            <div id="imgDiv"></div>
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
    //手机号码验证-------Begin
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
    //手机号码验证-------End

    function initData() {
        <%--$('#status_s').combobox('select', '${productOne.status}');--%>
        $('#status_p').combobox('select', '${productOne.status}');
        $('#productInfoId').combobox('select', '${productOne.productInfoId}');
        if ('${productOne.image}')
        {
            var c=   '<img width="200px" height="200px" src="'+'${productOne.image}' + '"/>';
            $("#imgDiv").html(c);
            $("#imgurl").val('${productOne.image}');
        }
    }

    $(function () {
        // $("#stored_type").combobox({
        //     onSelect: function () {
        //         var stored_type = $("#stored_type").combobox('getValue');
        //         if ( stored_type == 1 )
        //         {
        //             $("#stored").removeAttr('required');
        //             $("#stored").numberbox('disable',true);
        //         }
        //     }
        // });
        $("#productInfoId").combobox({
            url : '../shopproduct/getProductInfo',
            valueField : 'contentId',
            textField : 'content'
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
                // var stored_type = $("#stored_type").combobox('getValue');
                // var stored_safe = $("#stored_safe").numberbox("getValue");
                // console.log(postData)
                // alert(isValid);return false;
                $.ajax({
                    type: 'post',
                    url: 'modify/action',
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
    });

    function doUploadImg() {
        var formData = new FormData();
        formData.append('file', $('#uploadfile')[0].files[0]);
        console.log(formData);
        console.log($('#uploadfile')[0].files[0]);
        $.ajax({
            url: '../cloudCompany/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (ret) {
                console.log(ret);
                var c=   '<img width="200px" height="200px" src="'+ret+'"/>';
                $("#imgDiv").html(c);
                $("#imgurl").val(ret);
            },
            error: function (ret) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
    }

</script>
</body>
</html>
