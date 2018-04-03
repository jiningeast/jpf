<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <form id="addForm" method="post">
                <input type="hidden" id="id_m" name="id" value="">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>基本信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户编号：</td>
                        <td><input id="merchNo_m" name="merchNo" type="text" readonly="readonly" style="width:220px" class="easyui-textbox"/></td>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户名称：</td>
                        <td><input id="merchName_m" name="merchName" type="text" data-options="required:true,width:'220px'"
                                   missingMessage="请输入商户名称" class="easyui-textbox"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">用户名：</td>
                        <td><input id="username_m" name="username" type="text" style="width:220px" class="easyui-textbox"/></td>
                        <td style="text-align: right;background-color: #f1f1f1;">注册时间：</td>
                        <td><input id="addtime_m" name="addtime" type="text" style="width:220px" class="easyui-textbox"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">注册IP：</td>
                        <td><input id="registerip_m" name="registerip" type="text" style="width:220px" class="easyui-textbox"/></td>
                        <td style="text-align: right;background-color: #f1f1f1;">最后登录IP：</td>
                        <td><input id="lastloginip_m" name="lastloginip" type="text" style="width:220px" class="easyui-textbox"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">企业名称：</td>
                        <td><input id="companyname_m" name="companyname" type="text" style="width:220px" class="easyui-textbox"/></td>
                        <td style="text-align: right;background-color: #f1f1f1;">联系人：</td>
                        <td><input id="linkname_m" name="linkname" type="text" style="width:220px" class="easyui-textbox"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">联系电话：</td>
                        <td><input id="linkphone_m" name="linkphone" type="text" style="width:220px" class="easyui-textbox"/></td>
                        <td style="text-align: right;background-color: #f1f1f1;">销售电话：</td>
                        <td><input id="salerphone_m" name="salerphone" type="text" style="width:220px" class="easyui-textbox"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">省份：</td>
                        <td>
                            <select id="province_m" name="province" class="easyui-combobox" style="width:120px;">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">城市：</td>
                        <td>
                            <select id="city_m" name="city" class="easyui-combobox" style="width:120px;">
                                <option value="">请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">用户状态：</td>
                        <td>
                            <select id="status_m" name="status" class="easyui-combobox" style="width:120px;">
                                <option value="">请选择</option>
                                <option value="0">正常</option>
                                <option value="1">冻结余额</option>
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">企业认证：</td>
                        <td>
                            <select id="attestation_m" name="attestation" class="easyui-combobox" style="width:120px;">
                                <option value="">请选择</option>
                                <option value="0">未认证</option>
                                <option value="1">已认证</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">营业执照：</td>
                        <td colspan="3">
                            <img src="">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">企业资质：</td>
                        <td colspan="3">
                            <img src="">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">企业logo：</td>
                        <td colspan="3">
                            <img src="">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">用户Id：</td>
                        <td colspan="3">
                            <input id="muserid_m" name="muserid" type="text" style="width:220px" class="easyui-textbox"/>
                        </td>
                    </tr>
                </table>
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th colspan="6">结算信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">开户银行所在地：</td>
                        <td colspan="3">
                            <select id="bankProvince_m" name="bankProvince" onChange="getCityModify(this.options[this.selectedIndex].value)" style="width:100px;">
                                <option selected value="">-省份-</option>
                            </select>
                            <select id="bankCity_m" name="bankCity" style="width:100px;">
                                <option selected value="">-市-</option>
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">银行开户名称：</td>
                        <td><input id="bankname_m" name="bankname" type="text" class="easyui-textbox" style="width:175px;"></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">开户银行类型：</td>
                        <td>
                            <select id="banktype_m" name="banktype" style="width:100px;">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">企业对公账户：</td>
                        <td><input id="bankno_m" name="bankno" type="text" data-options="width:'175px'"
                                   missingMessage="请输入收款人账号" width="120" class="easyui-textbox"/></td>
                        <td style="text-align: right;background-color: #f1f1f1;">开户行全称：</td>
                        <td><input id="banksubname_m" name="banksubname" type="text" data-options="width:'175px'"
                                   missingMessage="请输入收款人姓名" width="120" class="easyui-textbox"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">创建时间：</td>
                        <td><input id="created_m" name="created" type="text" data-options="width:'175px'"
                                   missingMessage="请输入收款人账号" width="120" class="easyui-textbox"/></td>
                        <td style="text-align: right;background-color: #f1f1f1;">修改时间：</td>
                        <td><input id="updated_m" name="updated" type="text" data-options="width:'175px'"
                                   missingMessage="请输入收款人姓名" width="120" class="easyui-textbox"/></td>
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
        $('#merProperty_a').combobox('select', '${merchInfo.merProperty}');
        $('#merCorporationPaperType_a').combobox('select', '${merchInfo.merCorporationPaperType}');
        $('#manageScope_a').combobox('select', '${merchInfo.manageScope}');
        $('#virtualGoodsIsExist_a').combobox('select', '${merchInfo.virtualGoodsIsExist}');
        $('#settleBankType_a').combobox('select', '${merchInfo.settleBankType}');
        $('#proFtAcctNoType_a').combobox('select', '${merchInfo.proFtAcctNoType}');
        $('#merStauts_a').combobox('select', '${merchInfo.merStauts}');
        $('#merLevel_a').combobox('select', '${merchInfo.merLevel}');
        $('#merTransType_a').combobox('select', '${merchInfo.merTransType}');
        $('#merServiceType_a').combobox('select', '${merchInfo.merServiceType}');
        $('#settleType_a').combobox('select', '${merchInfo.settleType}');
        $('#settleMode_a').combobox('select', '${merchInfo.settleMode}');
        $('#settlePeriod_a').combobox('select', '${merchInfo.settlePeriod}');
        $('#settleFeePeriod_a').combobox('select', '${merchInfo.settleFeePeriod}');
        $('#settleFeeMode_a').combobox('select', '${merchInfo.settleFeeMode}');
        $('#parentMerIsExist_a').combobox('select', '${merchInfo.parentMerIsExist}');
    }

    $(function () {
        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        // setInterval("initData()", 500);
        // initData();
        $("#saveBtn_m").linkbutton({
            onClick: function () {
                var isValid = $("#form_a").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $('#form_a').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: '/info/oper',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '更新失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '添加成功！', 'error');
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
