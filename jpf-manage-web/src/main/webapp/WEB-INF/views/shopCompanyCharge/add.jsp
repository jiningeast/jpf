<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商户充值信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div id="formDiv1" class="easyui-panel"  style="padding: 10px; background: #fff; border: 1px solid #ccc;overflow-y:auto;height: 450px" >
        <form id="auditForm" method="post" enctype="multipart/form-data">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>基本信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">充值公司：</td>
                    <td>
                        <input id="companyName" name="companyName" type="text"  data-options="required:true"  width="120" class="easyui-textbox" missingMessage="请选择充值公司"/>
                        <input id="companyId" name="companyId" type="hidden"  />
                        <a id="searchCompany" class="easyui-linkbutton" href="javascript:void(0)" data-options="iconCls:'icon-search'">选取商户</a>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">合同号：</td>
                    <td>
                        <input id="contractNo" name="contractNo" type="text"   data-options="required:true" missingMessage="请填写合同号"  width="120" class="easyui-textbox"  />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">合同到期时间：</td>
                    <td>
                        <input type="text" class="Wdate" style="width: 141px;border-radius: 5px;height: 18px;border: 1px solid #95B8E7;" id="duetime"  name="duetime" data-options="required:true" missingMessage="请填写合同到期时间"
                               onfocus="WdatePicker({minDate:new Date(),minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">欣券金额：</td>
                    <td>
                        <input id="couponMoney" name="couponMoney" type="text"   data-options="required:true,onChange:getFuMoney"  missingMessage="请填写欣券金额"  width="120" class="easyui-numberbox" precision="2" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">费率：</td>
                    <td>
                        <input id="rate" name="rate" type="text"
                               width="120" class="easyui-numberbox" precision="2"  value="0" data-options="required:true,onChange:getFuMoney"/>&nbsp;&nbsp;<span style="color: #FF2F2F">%</span>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">服务费金额：</td>
                    <td>
                        <input id="serviceMoney" name="serviceMoney" type="text"   data-options="required:true" readonly="readonly"  missingMessage="请填写服务费金额"  width="120" class="easyui-numberbox" precision="2" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">合同金额：</td>
                    <td>
                        <input id="contractMoney" name="contractMoney" type="text"  readonly="readonly"   missingMessage=""  width="120" class="easyui-numberbox" precision="2" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">实际到帐金额：</td>
                    <td>
                        <input id="moneyCopy" name="moneyCopy" class="easyui-textbox" disabled="disabled"/>
                        <input id="money" name="money" type="hidden"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">服务转让率：</td>
                    <td>
                        <input id="transferRate" name="transferRate" class="easyui-numberbox" missingMessage="请填写服务转让率"  data-options="required:true" value="30"/>&nbsp;&nbsp;<span style="color: #FF2F2F">%</span>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">服务内容：</td>
                    <td>
                        <input id="serviceContent" name="serviceContent" class="easyui-textbox" missingMessage="请填写服务内容"  data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">付款凭证：</td>
                    <td>
                        <p>上传文件： <input type="file" name="file" id="file2"></p>
                                     <input type="button" value="上传" onclick="doUpload()"/>
                        <div id="ap"></div>
                        <input id="imgUrl" name="imgUrl" type="hidden" style="width:150px"  required="true" value="" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false"     style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_audit" class="easyui-linkbutton" icon="icon-ok"    href="javascript:void(0)">确定</a>
    </div>
    <div id="companys"></div>
</div>
<!-- /添加弹出窗口 -->

<script>
    function doUpload() {
        var file = $("#file2").val();
        if( file == "" ){
            $.messager.alert('提示', '请先选择要上传文件!', 'info');
            return false;
        }
        var formData = new FormData();
        formData.append('file', $('#file2')[0].files[0]);console.log(formData);
        $.ajax({
            url: '../shopCompanyCharge/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (ret) {
                var c =   '<img width="200px" height="200px" src="'+ret+'"/>';
                $("#ap").html(c);
                $("#imgUrl").val(ret);
            },
            error: function (ret) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
    }

    function getFuMoney(){
        var fuMoney=$("#couponMoney").val();
        var rote = ( $("#rate").val() );
        var calculate = (fuMoney * rote)/100;
        //设置服务费
        $("#serviceMoney").textbox("setValue",calculate);
        $("#contractMoney").textbox("setValue",(parseFloat(calculate)+parseFloat(fuMoney)).toFixed(2));
        $("#moneyCopy").textbox("setValue",fuMoney);
        $("#money").val(fuMoney);
    }

    // 计算实际到帐金额
 /*   function getRealMoney() {

        var moneyrel = parseInt( $("#contractMoney").val() );
        var rote = ( $("#rate").val() );
        if ( !isNaN(moneyrel) && !isNaN(rote) ){
            var calculate = (moneyrel * rote)/100;
            var money=(moneyrel-calculate).toFixed(2);
            $("#moneyCopy").textbox("setValue",money);
            $("#couponMoney").textbox("setValue",money);
            $("#serviceMoney").textbox("setValue",calculate);
            $("#money").val(money);

        }
    }*/
    $(function () {
        // 选取公司按钮
        $("#searchCompany").linkbutton({
            onClick:function(){
                $('#companys').window("open").window('refresh', '../shopCompanyCharge/chargeCompanys').window('setTitle','选取公司');
            }
        });



        // 选取公司弹窗大小
        $('#companys').window({
            width:'1200px',
            height:'600px',
            closed:true,
            modal:true
        });

        // 点击确定
        $("#saveBtn_audit").linkbutton({
            onClick : function () {
                var isValid = $("#auditForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                if ( $("#contractNo").val() == ""  ){
                    $.messager.alert('提示', '请选择公司', 'info');
                    return false;
                }else {
                    var r = /^[\u0391-\uFFE5]+$/;
                    if(r.test($("#contractNo").val())){
                        $.messager.alert('提示','合同号不能输入中文字符' , 'info');
                        return false;
                    }
                }
                if ( $("#companyId").val() == ""  ){
                    $.messager.alert('提示', '请选择公司', 'info');
                    return false;
                }
                // 判断上传文件
                var duetime = $("#duetime").val();
                if( duetime == "" ){
                    $.messager.alert('提示', '请填写合同到期时间!', 'info');
                    return false;
                }
                // 判断上传文件
                var file = $("#imgUrl").val();
                if( file == "" ){
                    $.messager.alert('提示', '请先选择要上传文件!', 'info');
                    return false;
                }
                var type = file.slice(file.lastIndexOf(".")+1,file.length);
                var checkType = type.toUpperCase();
                /*if( checkType !== "XLS" && checkType !== "XLSX" ){
                    $.messager.alert('提示', '所选的文件格式不正确!', 'info');
                    return false;
                }*/
                var queryArray = $('#auditForm').serializeArray();
                var postData = parsePostData(queryArray);
                $.ajax({
                    type: 'post',
                    url: '../shopCompanyCharge/add/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if ( msg.retCode  != '0000' ) {
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
        })

    })
</script>
</body>
</html>
