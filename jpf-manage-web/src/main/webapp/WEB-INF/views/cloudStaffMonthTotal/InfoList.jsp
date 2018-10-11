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
                <th>备注详情</th>
            </tr>
            <tr>

                <td style="text-align: left;background-color: #f1f1f1;" width="7%">订单号：</td>
                <td style="text-align: left;background-color: #f1f1f1;" width="7%">发起订单号：</td>
                <td style="text-align: left;background-color: #f1f1f1;" width="4%">类型：</td>
                <td style="text-align: left;background-color: #f1f1f1;" width="10%">内容：</td>
                <td style="text-align: left;background-color: #f1f1f1;" width="4%">订单金额：</td>
                <td style="text-align: left;background-color: #f1f1f1;" width="4%">之前金额：</td>
                <td style="text-align: left;background-color: #f1f1f1;" width="4%">之后金额：</td>
            </tr>
            <c:if test="${!empty listinfo}" >
                <c:forEach items="${listinfo}" var="one" varStatus="status" >
                    <tr >
                        <td  style="word-break: break-all;" >
                                ${one.dfmoney_orderid}
                        </td>
                        <td  style="word-break: break-all;">
                                ${one.api_orderid}
                        </td>
                        <td style="word-break: break-all;"  >
                                ${one.type}
                        </td>
                        <td style="word-break: break-all;"  >
                                ${one.content}
                        </td>

                        <td style="word-break: break-all;"  >
                                ${one.before}
                        </td>

                        <td style="word-break: break-all;" >
                                ${one.after}
                        </td>
                        <td  style="word-break: break-all;">
                                ${one.money}
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>

    </div>
    <div region="south" border="false"
         style="text-align: right; height: 30px; line-height: 30px;">
        <a id="closeBtn_detail" class="easyui-linkbutton" icon="icon-cancel"
           href="javascript:void(0)">关闭</a>
    </div>
</div>
<!-- /添加弹出窗口 -->
<style>
    .column1{width:13%;}
    .column2{width:30%;}
    .border-top{border-top: 1px double red;}
</style>
<script>
    function initData() {
        <%--$('#province_detail').combobox('select', '${orderCpInfo.province}');--%>
        <%--$('#city_detail').combobox('select', '${orderCpInfo.city}');--%>
        <%--$('#status_detail').combobox('select', '${orderCpInfo.status}');--%>
        <%--$('#attestation_detail').combobox('select', '${orderCpInfo.attestation==true?0:1}');--%>
        <%--$('#banktype_detail').combobox('select', '${merchantBankInfo.banktype}');--%>
        <%--$('#bankProvince_detail').combobox('select', '${merchantBankInfo.province}');--%>
        <%--$('#bankCity_detail').combobox('select', '${merchantBankInfo.city}');--%>
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
