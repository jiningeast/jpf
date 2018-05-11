<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>设置支付类型</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="addForm" method="post">
            <input type="hidden" name="mtsid" id="mtsid" value="${merchantInfo.id}">
            <input type="hidden" name="tpid" id="tpid" value="${merpayTypeInfoOne.tpid}">
            <input type="hidden" name="id" id="id" value="${merpayTypeInfoOne.id}">
            <table cellpadding=3 class="table table-bordered" id="wx" class="paramTable" style="display: none">
                <tr>
                    <th>支付编辑</th>
                </tr>
                <tr>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td width="30%">${TypeInfoOne.cat}</td>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;" >分期设置：</td>
                    <td width="30%">
                        <select name="bankcatid" class="easyui-combobox bankcatid" data-options="required:true,multiple:true" style="width:100px;">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        微信商户号:
                    </td>
                    <td>
                        <input id="wx_merSubMchid" name="wx_merSubMchid" type="text" style="width:220px" class="easyui-numberbox" value="${merpayTypeInfoOne.param}" data-options="required:true,min:0,precision:0,prompt:'请输入数字'" />
                    </td>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;" > </td>
                    <td width="30%" ></td>
                </tr>
                <tr class="wx_param paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                    </td>
                    <td>
                        <input id="wx_param3" name="wx_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                    </td>
                    <td>
                        <input id="wx_param4" name="wx_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="wx_param paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                    </td>
                    <td>
                        <input id="wx_param5" name="wx_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                    </td>
                    <td>
                        <input id="wx_param6" name="wx_param6" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
            <table cellpadding=3 class="table table-bordered" id="hb" class="paramTable" style="display: none">
                <tr>
                    <th>支付配置</th>
                </tr>
                <tr>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td width="30%">${TypeInfoOne.cat}</td>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;">分期设置</td>
                    <td width="30%">
                        <select name="bankcatid" class="easyui-combobox bankcatid" data-options="required:true,multiple:true" style="width:100px;">
                        </select>
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数1:
                    </td>
                    <td>
                        <input id="hb_param1" name="hb_param1" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数2:
                    </td>
                    <td>
                        <input id="hb_param2" name="hb_param2" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                        <%--<input id="key3" name="key3" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param3" name="hb_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                        <%--<input id="key4" name="key4" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param4" name="hb_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                        <%--<input id="key5" name="key5" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param5" name="hb_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                        <%--<input id="key6" name="key6" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="hb_param6" name="hb_param6" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
            <table cellpadding=3 class="table table-bordered" id="yl" class="paramTable" style="display: none">
                <tr>
                    <th>支付配置</th>
                </tr>
                <tr>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td width="30%" >${TypeInfoOne.cat}</td>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;">分期设置</td>
                    <td width="30%" >
                        <select name="bankcatid" class="easyui-combobox bankcatid" data-options="required:true,multiple:true" style="width:100px;">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商户号：</td>
                    <td><input type="text" name="cp_MerchaNo" class="easyui-textbox" value="${CP_MerchaNo}"></td>
                    <td style="text-align: right;background-color: #f1f1f1;">渠道编码：</td>
                    <td><input type="text" name="cp_Code" class="easyui-textbox" value="${CP_Code}"></td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">渠道账户编号：</td>
                    <td><input type="text" name="cp_Acctid" class="easyui-textbox" value="${CP_Acctid}"></td>
                    <td style="text-align: right;background-color: #f1f1f1;">商户签发密钥：</td>
                    <td><input type="text" name="cp_Salt" class="easyui-textbox" value="${CP_Salt}"></td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数1:
                    </td>
                    <td>
                        <input id="yl_param1" name="yl_param1" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数2:
                    </td>
                    <td>
                        <input id="yl_param2" name="yl_param2" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                    </td>
                    <td>
                        <input id="yl_param3" name="yl_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                    </td>
                    <td>
                        <input id="yl_param4" name="yl_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                    </td>
                    <td>
                        <input id="yl_param5" name="yl_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                    </td>
                    <td>
                        <input id="yl_param6" name="yl_param6" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
            <table cellpadding=3 class="table table-bordered" id="zy" class="paramTable" style="display: none">
                <tr>
                    <th>支付配置</th>
                </tr>
                <tr>
                    <td width="15%" style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                    <td width="30%">${TypeInfoOne.cat}</td>
                    <td width="15%">分期设置</td>
                    <td width="30%">
                        <select name="bankcatid" class="easyui-combobox bankcatid" data-options="required:true,multiple:true" style="width:100px;">
                        </select>
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数1:
                    </td>
                    <td>
                        <input id="zy_param1" name="zy_param1" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数2:
                    </td>
                    <td>
                        <input id="zy_param2" name="zy_param2" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数3:
                        <%--<input id="key3" name="key3" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="zy_param3" name="zy_param3" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数4:
                    </td>
                    <td>
                        <input id="zy_param4" name="zy_param4" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
                <tr class="paramTr">
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数5:
                    </td>
                    <td>
                        <input id="zy_param5" name="zy_param5" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">
                        参数6:
                        <%--<input id="key6" name="key6" type="text" style="width:150px" class="easyui-textbox key" value="" data-options="prompt:'参数名称',validType:'englishCheckSub'"/>--%>
                    </td>
                    <td>
                        <input id="zy_param6" name="zy_param6" type="text" style="width:220px" class="easyui-textbox" value="" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_m" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">确定</a>
        <a id="cancelBtn_m" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<div id="infoDiv2"></div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    function initData() {
        //等级信息
        var tpid = "${TypeInfoOne.catid}";
        if ( tpid == 9 )
        {
            $('select.bankcatid').combobox({
                url: '../param/getOneType?catid=35',
                valueField: 'catid',
                textField: 'cat',
                onLoadSuccess : function (data) {
                    $('select.bankcatid').combobox('setValues', "${merpayTypeInfoOne.bankcatid}");
                }
            });
        } else if ( tpid != 9 )
        {
            $('select.bankcatid').combobox({
                url:'../param/getType?pid=24',
                valueField:'catid',
                textField:'cat',
                onLoadSuccess : function (data) {
                    $('select.bankcatid').combobox('setValues', "${merpayTypeInfoOne.bankcatid}");
                }
            });
        }
    }
    $(function () {
        setTimeout("initData()", 500);
        $("table tr.paramTr").hide();   //非微信之外的参数行，隐藏
        $("table tr.wx_param").hide();  //微信其它参数暂时隐藏
        var catid = '${merpayTypeInfoOne.tpid}';
        //清空其它类型table
        //添加其它类型参数时，去掉tr类
        if ( catid == 9 )
        {
            $("#wx").show();
            $("#wx tr.paramTr").remove();
            doempty("wx");
        } else if ( catid == 8 )
        {
            $("#hb").show();
            $("#hb tr.paramTr").remove();
            doempty("hb");
        } else if ( catid == 7 )
        {
            $("#yl").show();
            $("#yl tr.paramTr").remove();
            doempty("yl");
        } else if ( catid == 6 )
        {
            $("#zy").show();
            $("#zy tr.paramTr").remove();
            doempty("zy");
        }
        function doempty(TableId) {
            $("#addForm table").each(function (i) {
                if ( $(this).attr('id') != TableId )
                {
                    $(this).empty();
                }
            })
        }

        var param = '${merpayTypeInfoOne.param}';
        if ( param != '' )
        {
            JSON.parse(param, function (k,v) {
                if (  k !== '' && typeof(v) !== 'object' )
                {
                    switch (k)
                    {
                        case 'merSubMchid':
                            $("#wx_merSubMchid").val(v);
                            break;
                    }
                }
            });
        }
        $("#saveBtn_m").linkbutton({
            onClick: function () {

                var isValid = $("#addForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var mtsid = '${merchantInfo.id}';
                var queryArray = $("#addForm").serializeArray();
                var postData = parsePostData(queryArray);
                var bankcatid = new Array;
                $("#addForm table input[name='bankcatid']").each(function (i) {
                    bankcatid[i] = $(this).val();
                });
                postData['bankcatid'] = bankcatid;
                $.ajax({
                    type: 'post',
                    url: '../merchant/paytype/modify/modifyMerPayTypeOne',
                    data: JSON.stringify(postData),
                    dataType: 'json',
                    contentType : 'application/json;charset=utf-8',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $('#infoDiv2').window('close');
                            $('#infoDiv').window("open").window('refresh', '../merchant/paytype/add/page?id=' + mtsid).window('setTitle','配置支付类型');
                            $.messager.alert('消息提示', '操作成功！', 'error');
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
                // $('#addForm').form('clear');
                $('#infoDiv2').window('close');
            }
        });

    })
</script>
</body>
</html>