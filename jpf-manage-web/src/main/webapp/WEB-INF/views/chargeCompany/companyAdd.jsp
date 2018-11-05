<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
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
            <h2 align="center">添加基本信息</h2>
            <table cellpadding=3 class="table table-bordered" align="center">
                <tr>
                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">公司名称：</td>
                    <td>
                        <input id="companyName" name="companyName" data-options="required:true,validType:'chinese'" type="text"
                               style="width:150px" class="easyui-textbox"
                               required="true" value=""/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;width:30%" bgcolor="#f1f1f1">登录密码：</td>
                    <td>
                        <input id="password" name="password" data-options="required:true,validType:'pwdLength'" minlength="6" maxlength="16"
                               type="password" style="width:150px" class="easyui-textbox"
                               required="true" value=""/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;width:30%" bgcolor="#f1f1f1">联系人姓名：</td>
                    <td>
                        <input id="contactName" name="contactName" data-options="required:true,validType:'chinese'"
                               type="text" style="width:150px" class="easyui-textbox"
                               required="true" value=""/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">联系人电话：</td>
                    <td>
                        <input id="contactPhone" name="contactPhone" type="text" style="width:150px" class="easyui-textbox"
                               value="" data-options="required:true,validType:'phoneRex'"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">是否冻结：</td>
                    <td>
                        <select id="isFreeze" name="isFreeze" data-options="required:true" class="easyui-combobox"
                                style="width:120px;">
                            <option value="0" selected="selected">未冻结</option>
                            <option value="1">冻结</option>
                        </select>
                    </td>
                </tr>
                <%--<tr>
                    <td style="text-align: right;background-color: #f1f1f1;">是否删除：</td>
                    <td>
                        <select id="isDel" name="isDel" data-options="required:true" class="easyui-combobox"
                                style="width:120px;">
                            <option value="0" selected="selected">不删除</option>
                            <option value="1">删除</option>
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
    //自定义验证
    $.extend($.fn.validatebox.defaults.rules, {
        phoneRex: {
            validator: function (value) {
                var rex = /^1[3-9]\d{9}$/;
                //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
                //电话号码：7-8位数字： \d{7,8
                //分机号：一般都是3位数字： \d{3,}
                //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/
                var rex2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
                //if (rex.test(value) || rex2.test(value)) {
                if (rex.test(value) ) {
                    // alert('t'+value);
                    return true;
                } else {
                    //alert('false '+value);
                    return false;
                }

            },
            //message: '请输入正确电话或手机格式'
            message: '请输入正确的手机号码'
        },
        intOrFloat: {// 验证整数或小数
            validator: function (value) {
                return /^\d+(\.\d+)?$/i.test(value);
            },
            message: '请输入数字，并确保格式正确'
        },
        pwdLength: {// 密码长度
            validator: function (value) {
                var len = $.trim(value).length;
                if( len < 6 || len > 16 ){
                    return false;
                }else return true;
            },
            message: '密码必须为6到16位长度'
        },
        chinese: {// 验证中文
            validator: function (value) {
                return /^[\u0391-\uFFE5]+$/i.test(value);
            },
            message: '请输入中文'
        }, engOrChineseAndLength: {// 可以是中文或英文
            validator: function (value) {
                var len = $.trim(value).length;
                if (len >= param[0] && len <= param[1]) {
                    return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
                }
            },
            message: '请输入中文或英文'
        }
    });

    $(function () {

        // 点击关闭按钮
        $("#cancelBtn_m").linkbutton({
            onClick: function(){
                $('#editCompany').window("close");
            }
        });

        // 点击确定按钮
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
                            $('#editCompany').window('close');
                            $('#dg').datagrid('reload');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
    })
</script>
</body>