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
            <form id="editForm" method="post"  enctype="multipart/form-data"accept="image/gif, image/jpeg,image/jpg, image/png">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>基本信息</th>
                    </tr>
                    <tr>
                                <td style="text-align: right;background-color: #f1f1f1;">公司名称：</td>
                                <td>
                                    <input id="name" name="name" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                                <td style="text-align: right;background-color: #f1f1f1;">联系人姓名：</td>
                                <td>
                                    <input id="phonename" name="phonename" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                                <td style="text-align: right;background-color: #f1f1f1;">联系人电话：</td>
                                <td>
                                    <input id="phone" name="phone" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                                <td style="text-align: right;background-color: #f1f1f1;">邮箱：</td>
                                <td>
                                    <input id="linkemail" name="linkemail" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>


                    </tr>
                    <tr>
                                <td style="text-align: right;background-color: #f1f1f1;">商户号：</td>
                                <td>
                                    <input id="merch_no" name="merch_no" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                                <td style="text-align:right; background-color: #f1f1f1;">账户通知方式：</td>
                                <td style="text-align:left">
                                    <span class="radioSpan">
                                        <input type="radio" name="tipsType" value="1" checked="checked">邮件</input>
                                        <input type="radio" name="tipsType" value="2">短信</input>
                                    </span>
                                </td>
                         <tr>
                                <td style="text-align: right;background-color: #f1f1f1;">营业执照：</td>
                                <td>
                                    <input id="bslicense" name="bslicense" type="file" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                                <td style="text-align: right;background-color: #f1f1f1;">企业资质：</td>
                                <td>
                                    <input id="aptitude" name="aptitude" type="file" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                        </tr>

                        <tr>
                         <td style="text-align: right;background-color: #f1f1f1;">企业认证：</td>
                                 <td>
                                    <select id="attestation" name="attestation" data-options="required:true" class="easyui-combobox" style="width:120px;">

                                        <option value="0">未认证</option>
                                        <option value="1">已认证</option>
                                    </select>
                                 </td>
                           <%--     <td style="text-align:right;">短信发送：</td>
                                <td style="text-align:left">
                                        <span class="radioSpan">
                                            <input type="radio" name="issms" value="0">不发送</input>
                                            <input type="radio" name="issms" value="2">发送</input>
                                        </span>
                                </td>--%>
                                <td style="text-align: right;background-color: #f1f1f1;">开户名称：</td>
                                <td>
                                    <input id="bankname" name="bankname" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                                <td style="text-align: right;background-color: #f1f1f1;">对公账户：</td>
                                <td>
                                    <input id="bankno" name="bankno" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                    <td style="text-align: right;background-color: #f1f1f1;">开户银行：</td>
                    <td>
                        <input id="banktype" name="banktype" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                    </td>
                         <tr>

                                 <td style="text-align: right;background-color: #f1f1f1;">客户经理：</td>
                                <td>
                                    <input id="serviclinkuser" name="serviclinkuser" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>
                                <td style="text-align: right;background-color: #f1f1f1;">手机号：</td>
                                <td>
                                    <input id="linkphone" name="linkphone" type="text" style="width:150px" class="easyui-textbox" required="true"  value=""  />
                                </td>

                          </tr>

                        </tr>


                    </tr>
                </table>
            </form>
            <!--上传图片、文件的代码-->
            <form action="${pageContext.request.contextPath}/cloudCompany/upload" method="post" enctype="multipart/form-data" >
                <input type="file" name="file">
                <input type="text" name="filesss" value="${pageContext.request.contextPath}/uploads">
                <input type="submit">
            </form>
            <!--接收-->
            <img src="${pageContext.request.contextPath}/${img}"/>
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
                    url: '../roles/add/action',
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
    })
</script>
</body>
</html>
