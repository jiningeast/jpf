<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>采购订单信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
<div class="easyui-layout" fit="true">
    <div region="center" border="false"
         style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="auditForm" method="post">
            <input type="hidden" id="id_audit" name="id" value="${shopBargainOrderInfo.id}">
            <table  class="table table-bordered">

                    <h2 style="text-align: center">详细信息</h2>

                <tr>
                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">订单号：</td>
                    <td>
                        ${shopBargainOrderInfo.orderNo}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">买家昵称：</td>
                    <td>
                        ${shopBargainOrderInfo.buyerCustomerNickname}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">卖家昵称：</td>
                    <td>
                        ${shopBargainOrderInfo.sellerCustomerNickname}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">最低限额豆数：</td>
                    <td>
                        ${shopBargainOrderInfo.minDou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">折损率：</td>
                    <td>
                        ${shopBargainOrderInfo.offRate}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">使用豆数：</td>
                    <td>
                        ${shopBargainOrderInfo.dou}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">转让价：</td>
                    <td>
                        ${shopBargainOrderInfo.transferPrice}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">收款人姓名：</td>
                    <td>
                        ${shopBargainOrderInfo.realName}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">手机号：</td>
                    <td >
                        ${shopBargainOrderInfo.phone}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">银行名称：</td>
                    <td >
                        ${shopBargainOrderInfo.bankBrank}
                    </td>

                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">银行卡号：</td>
                    <td >
                        ${shopBargainOrderInfo.bankNo}
                    </td>

                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">身份证号：</td>
                    <td >
                        ${shopBargainOrderInfo.idno}
                    </td>
                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单状态：</td>
                    <td >
                        <c:if  test="${shopBargainOrderInfo.status eq 0 }">待支付</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 1 }">已审核</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 2 }">打款中</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 3 }">打款成功</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 4 }">打款失败</c:if>
                        <c:if  test="${shopBargainOrderInfo.status eq 5 }">已取消</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">下单时间：</td>
                    <td  >
                        <fmt:formatDate value="${shopBargainOrderInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">支付时间：</td>
                    <td  >
                        <fmt:formatDate value="${shopBargainOrderInfo.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>

                    </td>
                </tr>
            </table>
            <table cellpadding=4 class="table table-bordered">

                    <h2 align="center">审核订单</h2>
            <c:if test="${type == 2 }">
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">上传付款凭证：</td>
                    <td>

                        <p>上传文件：  <input type="file" name="file" id="file1"></p>
                        <input type="button" value="上传" onclick="doUploadY()"/>
                        <div id="apy"></div>
                        <input    id="payImg" name="payImg" type="hidden" style="width:150px"
                               required="true" value=""/>
                    </td>
                </tr>
            </c:if>
                <tr>
                <tr>
                    <td style="text-align: right;width:40%" bgcolor="#f1f1f1">审核：</td>
                    <td colspan="4">
                        <select id="status_audit" name="status" class="easyui-combobox" style="width:120px;" data-options="">

                            <c:if test="${type == 1 }">

                                <option value="" selected="selected">请选择</option>
                                <option value="5"  <c:if  test="${shopBargainOrderInfo.status == '5' }">selected</c:if>>取消</option>
                                <option value="1" <c:if  test="${shopBargainOrderInfo.status == '1' }">selected</c:if> >审批</option>

                            </c:if>
                            <c:if test="${type == 2 }">

                                <option value="" selected="selected">请选择</option>
                                <%--<option value="2"  <c:if  test="${shopBargainOrderInfo.status == '2' }">selected</c:if>>打款中</option>--%>
                                <option value="3"  <c:if  test="${shopBargainOrderInfo.status == '3' }">selected</c:if>>打款成功</option>
                                <%--<option value="4"  <c:if  test="${shopBargainOrderInfo.status == '4' }">selected</c:if>>打款失败</option>--%>
                                <option value="5"  <c:if  test="${shopBargainOrderInfo.status == '5' }">selected</c:if>>取消订单</option>

                            </c:if>


                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">备注：</td>
                    <td colspan="4">
                        <textarea id="memo" name="memo"   style="width:200px; height:60px;">${shopBargainOrderInfo.memo}</textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="saveBtn_audit" class="easyui-linkbutton" icon="icon-ok"
           href="javascript:void(0)">确定</a>
        <a id="cancelBtn_audit" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">取消</a>
    </div>
</div>
<!-- /添加弹出窗口 -->

<script>

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
                $("#payImg").val(returndata);
            },
            error: function (returndata) {
                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
            }
        });
    }
    $(function () {

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。

        $("#saveBtn_audit").linkbutton({

            onClick: function () {
                //获取当前选中的值
                var  selectVelue=$("#status_audit").combobox('getValue');
                if (selectVelue== '') {
                    $.messager.alert('消息提示', '请选择审核状态', 'error');
                    return;
                }
                //获取当前选中的值
                var  isImg=$("#payImg").val();
                if (isImg== ''&& selectVelue==3) {
                    $.messager.alert('消息提示', '请上传付款凭证', 'error');
                    return;
                }
                var reqUrl = "audit/action";
                var isValid = $("#auditForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $('#auditForm').serializeArray();
                var postData = parsePostData(queryArray);
                var messagePass = $("#status_audit").combobox('getText');;
                var say="确定"+messagePass+"操作吗？"
                $.messager.confirm("系统提示",say,function(r){

                    $.ajax({
                        type: 'post',
                        url: reqUrl ,
                        data: postData,
                        dataType: 'json',
                        success: function (msg) {
                            if (msg.retCode != '0000') {
                                $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                            } else {
                                $.messager.alert('消息提示', msg.retMsg, 'info');
                                $('#dg').datagrid('reload');

                                $('#edit').window('close');

                            }
                        },
                        error: function () {
                            $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                        }
                    });
                });
            }
        });

        $('#cancelBtn_audit').linkbutton({
            onClick: function(){
                $('#edit').window('close');
            }
        });

    })
</script>
</body>
</html>
