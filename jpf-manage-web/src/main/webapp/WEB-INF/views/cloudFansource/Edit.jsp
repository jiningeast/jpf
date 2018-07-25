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
            <h2 align="center">审核</h2>
            <input type="hidden" id="id_m" name="id" value="${cloudFanSourceInfo.id}">
            <input type="hidden" id="merchNo" name="merchNo" value="${cloudFanSourceInfo.cat}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">姓名：</td>
                    <td>
                        <span>${cloudFanSourceInfo.name}</span>

                    </td>
                </tr>
                <tr>
                    <td  style="text-align: right;width:40%" bgcolor="#f1f1f1">手机号：</td>
                    <td>
                        <span>${cloudFanSourceInfo.mobile}</span>
                        <%--<input id="mobile" name="mobile" type="text" style="width:150px" class="easyui-textbox"--%>
                               <%--required="true" value="${cloudFanSourceInfo.mobile}"/>--%>
                    </td>
                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">地区：</td>
                    <td>
                        <span>${cloudFanSourceInfo.cat}</span>
                        <%--<input id="cat" data-options="required:true"  name="cat" type="text" style="width:150px" class="easyui-textbox"--%>
                               <%--value="${cloudFanSourceInfo.cat}" />--%>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1; width: 7%">备注：</td>
                    <td>

                            <textarea id="remark" name="remark"   style="width:500px; height:60px;">${cloudFanSourceInfo.remark}</textarea>
                    </td>
                </tr>

            </table>



        </form>
              <!--上传图片、文件的代码-->
           <%--   <form action="${pageContext.request.contextPath}/cloudCompany/upload" method="post" enctype="multipart/form-data" >
                  <input type="file" name="file">
                  <input type="text" name="filesss" value="${pageContext.request.contextPath}/uploads">
                  <input type="submit">
              </form>
              <!--接收-->
              <img src="${pageContext.request.contextPath}/${img}"/>--%>
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
        }
    });

    function initData() {
        $('#status_p').combobox('select', '${rolesInfo.status}');
        <%--$('#paytype').combobox('select', '${rolesInfo.zftype}');--%>

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
                    url: '../cloudFansource/edit/action',
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
