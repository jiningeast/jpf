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
                            <input id="merchNo_m" name="merchNo" type="text" value="${merchantInfo.merchNo}" style="width:220px" class="easyui-textbox"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户名称：</td>
                        <td>
                            <input id="merchName_m" name="merchName" value="${merchantInfo.merchName}" type="text" data-options="width:'220px'" class="easyui-textbox"/>
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
                            <input id="linkphone_m" name="linkphone" type="text" style="width:220px" class="easyui-textbox" value="${merchantInfo.linkphone}" data-options="required:true,validType:'phone'"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">销售电话：</td>
                        <td colspan="3">
                            <input id="salerphone_m" name="salerphone" type="text" style="width:220px" class="easyui-textbox" value="${merchantInfo.salerphone}" data-options="validType:'phone'" />
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
                        <td>
                            ${merchantInfo.muserid}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">是否为总店：</td>
                        <td>
                            <select id="isHeadShop" name="isHeadShop" class="easyui-combobox " style="width:120px;">
                                <option value=""></option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">营业执照：</td>
                        <td colspan="3">
                            <img width="200" height="200" src="${merchantInfo.bslicense}">
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
                            <img width="200" height="200" src="${merchantInfo.logo}">
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
                            <select id="banktype_m" name="banktype" class="easyui-combobox" data-options="required:true" style="width:100px;">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">企业对公账户：</td>
                        <td>
                            <input id="bankno_m" name="bankno" type="text" data-options="width:'175px',required:true,validType:'isNumber'"  width="120" class="easyui-textbox" value="${merchantBankInfo.bankno}" />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">开户行全称：</td>
                        <td>
                            <input id="banksubname_m" name="banksubname" type="text" data-options="width:'175px',required:true" width="120" class="easyui-textbox" value="${merchantBankInfo.banksubname}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">银行开户名称：</td>
                        <td>
                            <input id="bankid" name="bankid" type="text" class="easyui-textbox" style="width:175px;" value="${merchantBankInfo.bankname}" data-options="required:true">
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
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th colspan="6">法人信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">法人姓名：</td>
                        <td>
                            <input id="legalname" name="legalname" type="text" data-options="width:'175px'" width="120" class="easyui-textbox" value="${merchantInfo.legalname}"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">法人身份证号：</td>
                        <td>
                            <input id="legalidcard" name="legalidcard" type="text" data-options="width:'175px'" width="120" class="easyui-textbox" value="${merchantInfo.legalidcard}"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">营业执照号码：</td>
                        <td>
                            <%--<fmt:formatDate value="${merchantBankInfo.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                            <%--<img width="200" height="200" src="${merchantInfo.logo}">--%>
                            <input id="certificate" name="certificate" type="text" class="easyui-textbox" style="width:175px;" value="${merchantInfo.certificate}" data-options="required:true">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">身份证有效期开始日期：</td>
                        <td>
                            <input id="idstartdate" name="idstartdate" type="text" class="Wdate" style="width:175px;" value="${merchantInfo.idstartdate}"
                                   data-options="required:true,dateFmt:'yyyy-M-d"
                                   onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
                            />
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">身份证有效期结束日期：</td>
                        <td>
                            <input id="idenddate" name="idenddate" type="text" class="Wdate" style="width:175px;"
                                   value="${merchantInfo.idenddate}"
                                   data-options="required:true"
                                   onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
                            />
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">法人身份证正面：</td>
                        <td colspan="5">
                            <input type="hidden" id="legalface" name="legalface" value="${merchantInfo.legalface}">
                            <img width="200" height="200" src="${merchantInfo.legalface}">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">法人身份证反面：</td>
                        <td colspan="5">
                            <input type="hidden" id="legalback" name="legalback" value="${merchantInfo.legalback}">
                            <img width="200" height="200" src="${merchantInfo.legalback}">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">法人手持身份证照片：</td>
                        <td colspan="5">
                            <input type="hidden" id="lefalhand" name="lefalhand" value="${merchantInfo.lefalhand}">
                            <img width="200" height="200" src="${merchantInfo.lefalhand}">
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
        isNumber : {
            validator: function(value, param){
                return CheckIsNumber(value);
            },
            message: '请输入正确的的帐号信息'
        }
    });
    function CheckIsNumber(z_check_value){
        var z_reg = /^\d+$/;
        return z_reg.test($.trim(z_check_value));
    }
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
        var reg_mobile = /^(13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|19[8|9]|16[6]\d{8}$/;
        return reg_mobile.test($.trim(z_check_value));
    }
    //手机号码验证-------End

    function initData() {
        $('#province_m').combobox('select', '${merchantInfo.province}');
        $('#city_m').combobox('select', '${merchantInfo.city}');
        $('#status_m').combobox('select', '${merchantInfo.status}');
        <%--$('#attestation_m').combobox('select', '${merchantInfo.attestation==true?0:1}');--%>
        $('#banktype_m').combobox('select', '${merchantBankInfo.banktype}');
        $('#bankProvince_m').combobox('select', '${merchantBankInfo.province}');
        $('#bankCity_m').combobox('select', '${merchantBankInfo.city}');
        $('#isHeadShop').combobox('select', '${isHeadShop}');
    }

    $(function () {
        $("#bankid").combobox({
            url : '../param/getBankAll',
            valueField : 'id',
            textField : 'paybankname',
            onLoadSuccess : function () {
                $('#bankid').combobox('setValue', '${merchantBankInfo.bankid}');
            }
        });
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
                    url: '../merchant/modify/action',
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
    })
</script>
</body>
</html>
