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
                <input type="hidden" id="id_m" name="id" value="${merchantInfo.id}">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>基本信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户号：</td>
                        <td>
                            ${merchantInfo.merchNo}
                            <%--<input id="merchNo_m" name="merchNo" type="text" readonly="readonly" style="width:220px" class="easyui-textbox"/>--%>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户名称：</td>
                        <td>
                            ${merchantInfo.merchName}
                            <%--<input id="merchName_m" name="merchName" type="text" data-options="width:'220px'" class="easyui-textbox"/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">用户名：</td>
                        <td>
                            ${merchantInfo.username}
                            <%--<input id="username_m" name="username" type="text" style="width:220px" class="easyui-textbox"/>--%>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">企业名称：</td>
                        <td>
                            <input id="companyname_m" name="companyname" type="text" style="width:220px" class="easyui-textbox" value="${merchantInfo.companyname}" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">联系人：</td>
                        <td>
                            <input id="linkname_m" name="linkname" type="text" style="width:220px" class="easyui-textbox" value="${merchantInfo.linkname}" data-options="required:true"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">联系电话：</td>
                        <td>
                            <input id="linkphone_m" name="linkphone" type="text" style="width:220px" class="easyui-textbox" value="${merchantInfo.linkphone}" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">销售电话：</td>
                        <td colspan="3">
                            <input id="salerphone_m" name="salerphone" type="text" style="width:220px" class="easyui-textbox" value="${merchantInfo.salerphone}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">省份：</td>
                        <td>
                            <select id="province_m" name="province" class="easyui-combobox" style="width:120px;" data-options="required:true">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">城市：</td>
                        <td>
                            <select id="city_m" name="city" class="easyui-combobox" style="width:120px;" data-options="required:true">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">状态：</td>
                        <td>
                            <select id="status_m" name="status" class="easyui-combobox" style="width:120px;">
                                <option value=""></option>
                                <option value="0">正常</option>
                                <option value="1">冻结余额</option>
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">企业认证：</td>
                        <td>
                            <c:if test="${merchantInfo.attestation == true}">已认证</c:if>
                            <c:if test="${merchantInfo.attestation == false}">未认证</c:if>
                            <%--<select id="attestation_m" name="attestation" class="easyui-combobox" style="width:120px;">--%>
                                <%--<option value=""></option>--%>
                                <%--<option value="0">未认证</option>--%>
                                <%--<option value="1">已认证</option>--%>
                            <%--</select>--%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">用户Id：</td>
                        <td colspan="3">
                            ${merchantInfo.muserid}
                            <%--<input id="muserid_m" name="muserid" type="text" style="width:220px" class="easyui-textbox"/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">营业执照：</td>
                        <td colspan="3">
                            <img src="${merchantInfo.bslicense}">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">企业资质：</td>
                        <td colspan="3">
                            <img src="${merchantInfo.aptitude}">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">企业logo：</td>
                        <td colspan="3">
                            <img src="${merchantInfo.logo}">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">注册IP：</td>
                        <td>
                            ${merchantInfo.registerip}
                            <%--<input id="registerip_m" name="registerip" type="text" style="width:220px" class="easyui-textbox"/>--%>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">最后登录IP：</td>
                        <td>
                            ${merchantInfo.lastloginip}
                            <%--<input id="lastloginip_m" name="lastloginip" type="text" style="width:220px" class="easyui-textbox"/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">注册时间：</td>
                        <td colspan="3">
                            <%--${merchantInfo.addtime}--%>
                            <fmt:formatDate value="${merchantInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            <%--<input id="addtime_m" name="addtime" type="text" style="width:220px" class="easyui-textbox"/>--%>
                        </td>
                    </tr>
                </table>
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th colspan="6">结算信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">开户银行类型：</td>
                        <td>
                            <select id="banktype_m" name="banktype" class="easyui-combobox" style="width:100px;">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">企业对公账户：</td>
                        <td>
                            <input id="bankno_m" name="bankno" type="text" data-options="width:'175px'" width="120" class="easyui-textbox" value="${merchantBankInfo.bankno}"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">开户行全称：</td>
                        <td>
                            <input id="banksubname_m" name="banksubname" type="text" data-options="width:'175px'" width="120" class="easyui-textbox" value="${merchantBankInfo.banksubname}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">银行开户名称：</td>
                        <td>
                            <input id="bankname_m" name="bankname" type="text" class="easyui-textbox" style="width:175px;" value="${merchantBankInfo.bankname}" data-options="required:true">
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">省份：</td>
                        <td>
                            <select id="bankProvince_m" name="bankProvince" class="easyui-combobox" style="width:100px;" data-options="required:true">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">城市：</td>
                        <td>
                            <select id="bankCity_m" name="bankCity" class="easyui-combobox" style="width:100px;" data-options="required:true">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">创建时间：</td>
                        <td>
                            <fmt:formatDate value="${merchantBankInfo.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">修改时间：</td>
                        <td>
                            <fmt:formatDate value="${merchantBankInfo.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
    function initData() {
        $('#province_m').combobox('select', '${merchantInfo.province}');
        $('#city_m').combobox('select', '${merchantInfo.city}');
        $('#status_m').combobox('select', '${merchantInfo.status}');
        <%--$('#attestation_m').combobox('select', '${merchantInfo.attestation==true?0:1}');--%>
        $('#banktype_m').combobox('select', '${merchantBankInfo.banktype}');
        $('#bankProvince_m').combobox('select', '${merchantBankInfo.province}');
        $('#bankCity_m').combobox('select', '${merchantBankInfo.city}');
    }

    $(function () {
        $('#editForm #province_m').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#editForm #city_m').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#editForm #bankProvince_m').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#editForm #bankCity_m').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#editForm #banktype_m').combobox({
            url:'../param/getType?pid=17',
            valueField:'catid',
            textField:'cat'
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
                    url: '/modify/action',
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
