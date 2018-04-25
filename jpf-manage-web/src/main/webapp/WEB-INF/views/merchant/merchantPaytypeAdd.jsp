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
        <form id="paytypeForm" method="post">
            <input id="mtsid_a" name="mtsid" type="hidden" value = "${merchantInfo.id}" />
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th colspan="4">商户信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">商户名称：</td>
                    <td>${merchantInfo.merchName}</td>
                    <td></td><td></td>
                </tr>
                <tr>
                    <th colspan="4">已有支付类型</th>
                </tr>
                <%--<tr>--%>
                <c:if test="${!empty payTypeList}">
                    <c:forEach items="${payTypeList}" var="one">
                        <tr>
                            <td style="text-align: right;background-color: #f1f1f1;">支付类型：</td>
                            <input type="hidden" value="${one.id}">
                            <td>${one.catpath_zh}</td>
                            <input type="hidden" name="tpid" value="${one.tpid}">
                            <td style="text-align: right;background-color: #f1f1f1;">操作：</td>
                            <td>
                                <span><a href="javascript:void(0);" onclick="modify('${merchantInfo.id}', '${one.id}', '${one.tpid}')">编辑</a></span>&nbsp;&nbsp;||
                                <span><a href="javascript:void(0);">删除</a></span>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </form>

    </div>
    <div id="downBar" region="south" border="false" style="text-align: right; height: 30px; line-height: 30px; padding: 0 10px 0 10px; overflow: visible;">
        <div  style="float: left;">
            <div class="wrap" style="position: relative;">
                <a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
                <span id="typeSelect" style="display: none;">
                    <select id="tpid_a" name="tpid" class="easyui-combobox" style="width:100px;"></select>
                </span>
            </div>
        </div>
        <div style="float: right;">
            <a id="cancelBtn_a" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>

    </div>
</div>
<div id="infoDiv2"></div>
<!-- /添加弹出窗口 -->
<style>
</style>
<script>
    function modify(mtsid, id, tpid) {
        $('#infoDiv2').window("open").window('setTitle','支付配置').window('refresh', '../merchant/paytype/add/realpage?mtsid=' + mtsid + '&id=' + id + "&tpid=" + tpid);
    }

    function initData() {
        $('#tpid_a').next(".combo").hide();
    }
    $(function () {
        setTimeout("initData()", 500);

        $("#typeSelect").hide();

        $("#tpid_a").combobox({
            // data:paytypes,
            url : '../param/getType?pid=5',
            valueField:'catid',
            textField:'cat',
            onSelect: function () {
                var catid = $('#tpid_a').combobox('getValue');
                $('#infoDiv2').window("open").window('setTitle','支付配置').window('refresh', '../merchant/paytype/add/realpage?catid=' + catid + '&id=' + "${mtsid}");
            }
        });

        $('#infoDiv2').window({
            width:'800',
            height:'500px',
            closed:true,
            modal:true
        });




        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        // setInterval("initData()", 500);
        // initData();
        $("#saveBtn_a").linkbutton({
            onClick: function () {
                // var queryArray = $('#savePaytype_From').serializeArray();
                // var postData = parsePostData(queryArray);;
                var param = {};
                param["mtsid"] = $("#mtsid_a").val();
                param['tpid'] = new Array();
                $("#savePaytypeShow_Table tbody input[name='tpid']").each(function (i) {
                    param['tpid'][i] = $(this).val()
                });
                $.ajax({
                    type: 'post',
                    url: '../merchant/paytype/add/action',
                    data: JSON.stringify(param),
                    dataType: 'json',
                    contentType : 'application/json;charset=utf-8',
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

        $('#cancelBtn_a').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });
        
        $('#addBtn').linkbutton({
            onClick: function () {
                $("#typeSelect").show();
                $('#tpid_a').next(".combo").show();
                $('#tpid_a').combobox('showPanel');
            }
        })
    })
</script>
</body>
</html>
