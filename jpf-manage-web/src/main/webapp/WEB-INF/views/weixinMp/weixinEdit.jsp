<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>公众号信息</title>
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
            <h2 align="center">添加公众号信息</h2>
            <input type="hidden" id="id" name="id" value="${weixinMpInfo.id}">
            <table cellpadding=3 class="table table-bordered" align="center">

                <tr>
                    <td  style="text-align: right;width:40%" bgcolor="#f1f1f1">公众号名称：</td>
                    <td>
                        <input id="name" name="name" data-options="required:true" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${weixinMpInfo.name}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">开发者ID：</td>
                    <td>
                        <input id="appid" name="appid" type="text" style="width:150px" class="easyui-textbox"
                               value="${weixinMpInfo.appid}" data-options="required:true,validType:'erghtTenRex'"  />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">开发者密码：</td>
                    <td>
                        <input id="appsecret"  data-options="required:true"  name="appsecret" type="text" style="width:150px" class="easyui-textbox"
                               value="${weixinMpInfo.appsecret}" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">令牌token：</td>
                    <td>
                        <input id="token" data-options="required:true" data-options="required:true"  name="token" type="text" style="width:150px" class="easyui-textbox"
                               value="${weixinMpInfo.token}" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">二维码：</td>
                    <td>

                        <p>上传文件：  <input type="file" name="file" id="file1"></p>
                        <input type="button" value="上传" onclick="doUploadY()"/>
                        <div id="apy"><img style="width: 200px;height: 200px;" src="${weixinMpInfo.followqr}"></div>
                        <input id="followqr" name="followqr" type="hidden" style="width:150px"
                               required="true" value="${weixinMpInfo.followqr}"/>
                    </td>
                </tr>
                <tr>
                    <td  style="text-align: right;width:30%" bgcolor="#f1f1f1">关注回复：</td>
                    <td>

                        <textarea id="followreply" name="followreply" style="height:40px;width:200px" >${weixinMpInfo.followreply}</textarea>
                    </td>
                </tr>
                <tr>
                    <td  style="text-align: right;width:40%" bgcolor="#f1f1f1">商户号：</td>
                    <td>
                        <input id="merchant" name="merchant" data-options="required:true" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${weixinMpInfo.merchant}"/>
                    </td>
                </tr>
                <tr>
                    <td  style="text-align: right;width:40%" bgcolor="#f1f1f1">商户key：</td>
                    <td>
                        <input id="merkey" name="merkey" data-options="required:true" type="text" style="width:150px" class="easyui-textbox"
                               required="true" value="${weixinMpInfo.merkey}"/>
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
        phoneRex: {
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
        }, erghtTenRex: {
            validator: function(value){
                var rex=/[a-zA-Z0-9]{18}/;
                if(rex.test(value))
                {
                    return true;
                }else
                {
                    return false;
                }

            },
            message: '请输入18位数字或字母'
        },
    });

    function initData() {
        $('#status_p').combobox('select', '${rolesInfo.status}');
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
                $("#followqr").val(returndata);
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
                    url: '../weixinMp/edit',
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
            onClick: function () {
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>
