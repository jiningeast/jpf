<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>公司信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="editForm" method="post" enctype="multipart/form-data"
              accept="image/gif, image/jpeg,image/jpg, image/png">
            <h2 align="center">编辑基本信息</h2>
            <table cellpadding=3 class="table table-bordered" align="center">
                <input type="hidden" name="id" value="${payShopAd.id}" />
                <tr>
                    <td  style="text-align: right;width:40%" bgcolor="#f1f1f1">图片文字：</td>
                    <td>
                        <input id="title" name="title" data-options="required:true,validType:'chinese'" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${payShopAd.title}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">上传图片：</td>
                    <td>

                        <p>上传文件：  <input type="file" name="file" id="file1"></p>
                        <input type="button" value="上传" onclick="doUploadY()"/>
                        <div id="apy"><img width="200px" height="200px" src="${payShopAd.imgUrl}"/></div>
                        <input id="imgUrl" name="imgUrl" type="hidden" style="width:150px"
                               required="true" value="${payShopAd.imgUrl}"/>
                    </td>
                    </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">图片链接：</td>
                    <td>
                        <input id="url" name="url" type="text" style="width:150px" class="easyui-textbox"
                               value="${payShopAd.url}" data-options=""/> 图片链接地址
                    </td>
                </tr>
                    <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">排序：</td>
                    <td>
                        <input id="weight"  type="text" name="weight" value="${payShopAd.weight}" /><span style="color:red"> (提示：数字越大排序靠前)</span>
                    </td>
                    </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">广告类型：</td>
                    <td>
                        <select editable="false" id="type" name="type" data-options="required:true" class="easyui-combobox"   style="width:120px;">
                            <option value="0" selected="selected">首页图标</option>
                            <option value="1" >首页轮播图</option>
                            <option value="2" >首页banner图片</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">是否显示：</td>
                    <td>
                        <select editable="false" id="status" name="status" data-options="required:true" class="easyui-combobox"  style="width:120px;">
                            <option value="0" >不显示</option>
                            <option value="1" selected="selected">显示</option>
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

    //自定义验证
    $.extend($.fn.validatebox.defaults.rules, {
        /*phoneRex: {
            validator: function(value){
                var rex=/^1[3-8]+\d{9}$/;
                //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
                //电话号码：7-8位数字： \d{7,8
                //分机号：一般都是3位数字： \d{3,}
                //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/
                var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                if(rex.test(value)||rex2.test(value))
                {
                    // alert('t'+value);
                    return true;
                }else
                {
                    //alert('false '+value);
                    return false;
                }

            },
            message: '请输入正确电话或手机格式'
        },
        intOrFloat : {// 验证整数或小数
            validator : function(value) {
                return /^\d+(\.\d+)?$/i.test(value);
            },
            message : '请输入数字，并确保格式正确'
        },
        chinese : {// 验证中文
            validator : function(value) {
                return /^[\u0391-\uFFE5]+$/i.test(value);
            },
            message : '请输入中文'
        },engOrChineseAndLength : {// 可以是中文或英文
            validator : function(value) {
                var len = $.trim(value).length;
                if (len >= param[0] && len <= param[1]) {
                    return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
                }
            },
            message : '请输入中文或英文'
        },*/
    });

    function initData() {
        $('#type').combobox('select', '${payShopAd.type}');
        $('#status').combobox('select', '${payShopAd.status}');
        <%--$('#paytype').combobox('select', '${rolesInfo.zftype}');--%>

    }
    function doUploadY() {
        var formData = new FormData();
        formData.append('file', $('#file1')[0].files[0]);

        $.ajax({
            url: '../cloudCompany/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                var yc=   '<img width="200px" height="200px" src="'+returndata+'"/>';
             console.log(yc);
                $("#apy").html(yc);
                $("#imgUrl").val(returndata);
            },
            error: function (returndata) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
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
                    url: '../shopAd/add/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            $('#infoDiv').window('close');
                            $('#batchDG').datagrid('reload');
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
