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
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">聚合商户号：</td>
                    <td>
                        ${merchantInfo.merchNo}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">聚合商户名称：</td>
                    <td>
                        ${merchantInfo.merchName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">用户名：</td>
                    <td>
                        ${merchantInfo.username}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">企业名称：</td>
                    <td>
                        ${merchantInfo.companyname}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">联系人：</td>
                    <td>
                        ${merchantInfo.linkname}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">联系电话：</td>
                    <td>
                        ${merchantInfo.linkphone}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">销售电话：</td>
                    <td colspan="3">
                        ${merchantInfo.salerphone}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">省份：</td>
                    <td>
                        <select id="province_detail" name="province" class="easyui-combobox" style="width:120px;" data-options="disabled:true">
                        </select>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">城市：</td>
                    <td>
                        <select id="city_detail" name="city" class="easyui-combobox" style="width:120px;" data-options="disabled:true">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">状态：</td>
                    <td>
                        <select id="status_detail" name="status" class="easyui-combobox" style="width:120px;" data-options="disabled:true">
                            <option value=""></option>
                            <option value="0">正常</option>
                            <option value="1">冻结余额</option>
                        </select>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">企业认证：</td>
                    <td>
                        <select id="attestation_detail" name="attestation" class="easyui-combobox" style="width:120px;" data-options="disabled:true">
                            <option value=""></option>
                            <option value="0">未认证</option>
                            <option value="1">已认证</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">用户Id：</td>
                    <td colspan="3">
                        ${merchantInfo.muserid}
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
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">最后登录IP：</td>
                    <td>
                        ${merchantInfo.lastloginip}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">注册时间：</td>
                    <td colspan="3">
                        <fmt:formatDate value="${merchantInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
                        <select id="banktype_detail" name="banktype" class="easyui-combobox" style="width:100px;" data-options="disabled:true">
                        </select>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">企业对公账户：</td>
                    <td>
                        ${merchantBankInfo.bankno}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">开户行全称：</td>
                    <td>
                        ${merchantBankInfo.banksubname}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">银行开户名称：</td>
                    <td>
                        ${merchantBankInfo.bankname}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">省份：</td>
                    <td>
                        <select id="bankProvince_detail" name="bankProvince" class="easyui-combobox" style="width:100px;" data-options="disabled:true">
                        </select>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">城市：</td>
                    <td>
                        <select id="bankCity_detail" name="bankCity" class="easyui-combobox" style="width:100px;" data-options="disabled:true">
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
        <a id="closeBtn_detail" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">关闭</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    function initData() {
        $('#province_detail').combobox('select', '${merchantInfo.province}');
        $('#city_detail').combobox('select', '${merchantInfo.city}');
        $('#status_detail').combobox('select', '${merchantInfo.status}');
        $('#attestation_detail').combobox('select', '${merchantInfo.attestation==true?0:1}');
        $('#banktype_detail').combobox('select', '${merchantBankInfo.banktype}');
        $('#bankProvince_detail').combobox('select', '${merchantBankInfo.province}');
        $('#bankCity_detail').combobox('select', '${merchantBankInfo.city}');
    }

    $(function () {
        $('#province_detail').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#city_detail').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#bankProvince_detail').combobox({
            url:'../param/getPca',
            valueField:'catid',
            textField:'cat',
            onSelect: function(record){
                $('#bankCity_detail').combobox({
                    url:'../param/getPca?pid=' + record.catid,
                    valueField:'catid',
                    textField:'cat'
                });
            }
        });

        $('#banktype_detail').combobox({
            url:'../param/getType?pid=17',
            valueField:'catid',
            textField:'cat'
        });

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
        // initData();

        $('#closeBtn_detail').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>
