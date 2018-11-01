<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加商品</title>
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
                        <th>添加商品</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">商品名称：</td>
                        <td>
                            <input id="product_name" name="name" type="text" style="width:220px" class="easyui-textbox" value="" data-options="required:true"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">商品类型：</td>
                        <td width="100px">
                            <select id="typeId" name="typeId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="typeName" id="typeName" value="" />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">品牌：</td>
                        <td width="100px">
                            <select id="brandId" name="brandId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="brandName" id="brandName" value="" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">供应商：</td>
                        <td width="100px">
                            <select id="supplierId" name="supplierId" data-options="required:true" class="easyui-combobox" style="width:95%" ></select>
                            <input type="hidden" name="supplierName" id="supplierName" value="" />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">状态:</td>
                        <td>
                            <select id="isOnSale" name="isOnSale" data-options="required:true" class="easyui-combobox" style="width:120px;">
                                <option value="0">下架</option>
                                <option value="1">上架</option>
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">运营商类型:</td>
                        <td>
                            <select id="mobileType" name="mobileType" data-options="required:true" class="easyui-combobox" style="width:120px;">
                                <option value="1">移动</option>
                                <option value="2">联通</option>
                                <option value="3">电信</option>
                                <option value="4">中石化</option>
                                <option value="5">中石油</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">标准进价：</td>
                        <td>
                            <input id="bidPrice" name="bidPrice" type="text" style="width:220px" class="easyui-textbox" data-options="required:true,validType:'floatNumber'" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">商品售价：</td>
                        <td>
                            <input id="salePrice" name="salePrice" type="text" style="width:220px" class="easyui-textbox" data-options="required:true,validType:'floatNumber'" />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">面值：</td>
                        <td>
                            <input id="value" name="value" type="text" style="width:220px" class="easyui-numberbox" value="" data-options="required:true,min:0,precision:0,prompt:'请输入数字'" />
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">欧飞产品id：</td>
                        <td>
                            <input id="ofProductId" name="ofProductId" type="text" style="width:220px" class="easyui-numberbox" value="" data-options="min:0,precision:0,prompt:'请输入数字'" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">威能产品id：</td>
                        <td>
                            <input id="wnProductId" name="wnProductId" type="text" style="width:220px" class="easyui-numberbox" value="" data-options="min:0,precision:0,prompt:'请输入数字'" />
                        </td>
                    </tr>
                    <%--<tr>
                        <td style="text-align: right;background-color: #f1f1f1;">强制使用某接口：</td>
                        <td>
                            <select id="forceInterface" name="forceInterface" data-options="required:true" class="easyui-combobox" style="width:100px;">
                                <option value="0" selected>否</option>
                                <option value="1">是</option>
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">接口类型：</td>
                        <td>
                            <select id="interfaceType" name="interfaceType" data-options="required:true" class="easyui-combobox" style="width:100px;">
                                <option value="0" selected>欧非</option>
                                <option value="1">威能</option>
                            </select>
                        </td>
                    </tr>--%>
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
<script>
    $(function () {
        $("#typeId").combobox({
            url : 'getChargeProductType',
            valueField : 'id',
            textField : 'typeName',
            onSelect: function () {
                var typeName = $("#typeId").combobox("getText");
                $("#typeName").val(typeName)
            }
        });


        $("#supplierId").combobox({
            url : 'getChargeSuppliers',
            valueField : 'id',
            textField : 'supplierName',
            onSelect: function () {
                var supplierName = $("#supplierId").combobox("getText");
                $("#supplierName").val(supplierName)
            }
        });

        $("#brandId").combobox({
            url : 'getChargeBrandList',
            valueField : 'id',
            textField : 'brandName',
            onSelect: function () {
                var brandName = $("#brandId").combobox("getText");
                $("#brandName").val(brandName)
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
                    url: 'addAction',
                    data: postData,
                    dataType: 'json',
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
    });
</script>
</body>
</html>
