<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品基础信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
    <div class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form id="editForm" method="post" enctype="multipart/form-data" accept="image/gif, image/jpeg,image/jpg, image/png">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>添加</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">商品类型：</td>
                        <td width="300px">
                            <select id="typeId" name="typeId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="typeName" id="typeName" value="" />
                        </td>
                        <td width="100px;">
                            <input id="typeName_add" name="typeName_add" type="text" style="width:220px" class="easyui-textbox" value="" />
                        </td>
                        <td><a style="margin-left: 5%;" id="saveBtn_typeName" class="easyui-linkbutton" icon="icon-add" href="javascript:void(0)">新增</a></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">供应商：</td>
                        <td>
                            <select id="supplierId" name="supplierId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="supplierName" id="supplierName" value="" />
                        </td>
                        <td>
                            <input id="supplierName_add" name="supplierName_add" type="text" style="width:220px" class="easyui-textbox" value="" />
                        </td>
                        <td><a style="margin-left: 5%;" id="saveBtn_supplierName" class="easyui-linkbutton" icon="icon-add" href="javascript:void(0)">新增</a></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">品牌：</td>
                        <td>
                            <select id="brandId" name="brandId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="brandName" id="brandName" value="" />
                        </td>
                        <td>
                            <input id="brandName_add" name="brandName_add" type="text" style="width:220px" class="easyui-textbox" value="" />
                        </td>
                        <td><a style="margin-left: 5%;" id="saveBtn_brandName" class="easyui-linkbutton" icon="icon-add" href="javascript:void(0)">新增</a></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">联系人：</td>
                        <td>
                            <input id="contactName" name="contactName" type="text" style="width:220px" class="easyui-textbox" value="" data-options="required:true" />
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">联系电话：</td>
                        <td>
                            <input id="contactPhone" name="contactPhone" type="text" style="width:220px" class="easyui-textbox" value="" data-options="required:true,validType:'phone'" />
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">邮箱：</td>
                        <td>
                            <input id="contactEmail" name="contactEmail" type="text" style="width:220px" class="easyui-textbox" value="" data-options="required:true,validType:'email'" />
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">状态：</td>
                        <td>
                            <select id="status_s" name="status" class="easyui-combobox" style="width:120px;">
                                <option value="0">显示</option>
                                <option value="1">不显示</option>
                            </select>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">备注：</td>
                        <td colspan="3">
                            <input id="remark" name="remark" type="text" style="width:90%;height: 60px;" class="easyui-textbox" data-options="multiline:true"/>
                        </td>
                    </tr>
                </table>

                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>旅游生活-携程信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">标题：</td>
                        <td colspan="3">
                            <input id="title" name="title" type="text"  class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">图片：</td>
                        <td colspan="3">
                            <p>
                                上传文件： <input type="file" name="file" id="uploadfile">
                                <input type="button" value="上传" onclick="doUploadImg()"/>
                            </p>
                            <!--保存图片OSS地址-->
                            <input id="imgurl" name="imgurl" type="hidden" style="width:150px" data-options="required:true" value=""/>
                            <!--图片展示-->
                            <div id="imgDiv"></div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">价格范围区间：</td>
                        <td colspan="3">
                            <input id="moneyscope" name="moneyscope" type="text" class="easyui-textbox"/>(例：100-1000)
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
        phone : {
            validator: function(value, param){
                return ChecnPhone(value);
            },
            message: '请输入正确的手机号码'
        },
        email: {
            validator : function (value, param) {
                return checkEmail(value)
            },
            message : '请输入正确的邮箱'
        }
    });

    function isEmpty(str){
        if(str == null || typeof str == "undefined" || str == ""){
            return true;
        }
        return false;
    }

    function ChecnPhone(z_check_value){
        if( isEmpty(z_check_value) || z_check_value.length != 11 ){
            return false;
        }
        var reg_mobile = /^(13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0-9])|19[1|8|9]|16[6]\d{8}$/;
        return reg_mobile.test($.trim(z_check_value));
    }

    function checkEmail(z_check_value){
        var z_reg =  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        return z_reg.test($.trim(z_check_value));
    }
    //手机号码验证-------End

    $(function () {
        $("#typeId").combobox({
            url : '../param/getShopProductType',
            valueField : 'pid',
            textField : 'pname',
            onSelect: function () {
                var typeName = $("#typeId").combobox("getText");
                $("#typeName").val(typeName)
            }
        });

        $("#supplierId").combobox({
            url : '../param/getShopSuppliers',
            valueField : 'id',
            textField : 'supplierName',
            onSelect: function () {
                var supplierName = $("#supplierId").combobox("getText");
                $("#supplierName").val(supplierName)
            }
        });

        $("#brandId").combobox({
            url : '../param/getShopBrandList',
            valueField : 'id',
            textField : 'brandName',
            onSelect: function () {
                var brandName = $("#brandId").combobox("getText");
                $("#brandName").val(brandName)
            }
        });

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);

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
                    url: 'pInfoAdd/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            $('#pinfoAddDiv').window('close');
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
                $('#pinfoAddDiv').window('close');
            }
        });

        $("#saveBtn_supplierName").linkbutton({
            onClick: function () {
                var supplierName = $("#supplierName_add").combobox("getText");
                if ( !supplierName )
                {
                    $.messager.alert('消息提示', '请填写[供应商名称]！', 'error');
                    return false;
                }
                var param = {};
                param["supplierName"] = supplierName;
                $.ajax({
                    type: 'post',
                    url: '/shopproduct/supplier/add',
                    data: param,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            $("#supplierId").combobox('reload',"../param/getShopSuppliers");
                            $("#supplierName_add").textbox('setValue','');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $("#saveBtn_typeName").linkbutton({
            onClick: function () {
                var typeName = $("#typeName_add").combobox("getText");
                if ( !typeName )
                {
                    $.messager.alert('消息提示', '请填写[商品类型]！', 'error');
                    return false;
                }
                var param = {};
                param["pname"] = typeName;
                $.ajax({
                    type: 'post',
                    url: '/shopproduct/producttype/add',
                    data: param,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            // $('#infoDiv').window('close');
                            // $('#dg').datagrid('reload');
                            $("#typeId").combobox('reload','../param/getShopProductType');
                            $("#typeName_add").textbox('setValue','');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $("#saveBtn_brandName").linkbutton({
            onClick: function () {
                var brandName = $("#brandName_add").combobox("getText");
                if ( !brandName )
                {
                    $.messager.alert('消息提示', '请填写[品牌名称]！', 'error');
                    return false;
                }
                var param = {};
                param["brandName"] = brandName;
                $.ajax({
                    type: 'post',
                    url: '/shopproduct/brand/add',
                    data: param,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            // $('#infoDiv').window('close');
                            // $('#dg').datagrid('reload');
                            $("#brandId").combobox('reload','../param/getShopBrandList');
                            $("#brandName_add").textbox('setValue','');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });



    });

    function doUploadImg() {
        var formData = new FormData();
        formData.append('file', $('#uploadfile')[0].files[0]);
        //console.log(formData);
        //console.log($('#uploadfile')[0].files[0]);
        $.ajax({
            url: '/cloudCompany/upload',
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
