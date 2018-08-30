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
            <input type="hidden" id="id_audit" name="id" value="${shopStockOrderInfo.id}">
            <table cellpadding=3 class="table table-bordered">
                <tr>
                    <th>采购订单详细信息</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">采购序列号：</td>
                    <td>
                        ${shopStockOrderInfo.id}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">采购订单号：</td>
                    <td>
                        ${shopStockOrderInfo.orderNo}
                    </td>

                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">采购订单金额：</td>
                    <td>
                        ${shopStockOrderInfo.money}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">付款方式：</td>
                    <td>
                        ${shopStockOrderInfo.paywayCn}
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">付款周期: </td>
                    <td>
                        ${shopStockOrderInfo.paytypeCn}
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                    <td>
                        <fmt:formatDate value="${shopStockOrderInfo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>

                </tr>

                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">订单状态：</td>
                    <td >
                        <c:if  test="${shopStockOrderInfo.status == 0 }">取消</c:if>
                        <c:if  test="${shopStockOrderInfo.status == 1 }">新建</c:if>
                        <c:if  test="${shopStockOrderInfo.status == 2 }">提交</c:if>
                        <c:if  test="${shopStockOrderInfo.status == 3 }">审批</c:if>
                        <c:if  test="${shopStockOrderInfo.status == 4 }">已付款</c:if>
                    </td>
                    <td style="text-align: right;background-color: #f1f1f1;">是否采购：</td>
                    <td >
                        <c:if  test="${shopStockOrderInfo.isUpload == 1 }">已采购</c:if>
                        <c:if  test="${shopStockOrderInfo.isUpload == 2 }">未采购</c:if>

                    </td>
                </tr>
            </table>
            <div id="productView"></div>
            <table cellpadding=4 class="table table-bordered">
                <tr>
                    <th>审核采购订单</th>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">审核：</td>
                    <td colspan="4">
                        <select id="status_audit" name="status" class="easyui-combobox" style="width:120px;" data-options="">

                            <c:if test="${type == 1 }">
                                <option value="" selected="selected">请选择</option>
                                <option value="0"  <c:if  test="${shopStockOrderInfo.status == '0' }">selected</c:if>>取消</option>
                                <%--<option value="1"  >新建,待提交</option>--%>
                                <option value="2" <c:if  test="${shopStockOrderInfo.status == '2' }">selected</c:if> >提交</option>
                                <option value="3" <c:if  test="${shopStockOrderInfo.status == '3' }">selected</c:if> >审批</option>
                            </c:if>
                            <c:if test="${type == 2 }">
                                <option value="" selected="selected">请选择</option>
                                <option value="0"  <c:if  test="${shopStockOrderInfo.status == '0' }">selected</c:if>>取消</option>
                                <option value="4"  <c:if  test="${shopStockOrderInfo.status == '4' }">selected</c:if>>已付款</option>
                            </c:if>


                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;background-color: #f1f1f1;">备注：</td>
                    <td colspan="4">
                        <textarea id="memo" name="memo"   style="width:500px; height:60px;">${shopStockOrderInfo.memo}</textarea>
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

    $(function () {

        $("#productView").datagrid({
            title:'商品列表',
            toolbar:toolbar,
            // rownumbers:true,//如果为true，则显示一个行号列。
            //pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
            singleSelect:true,
            multiselect:true,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            // width:500,
            url:'productsList?OrderNo='+"${shopStockOrderInfo.orderNo}",
            columns:[[
                {field:'productId',title:'商品ID',width:"6%",align:"center"},
                {field:'productName',title:'商品名称',width:"10%",align:"center"},
                {field:'productBid',title:'产品进价',width:"10%",align:"center"},
                {field:'stockAmount',title:'产品库存',width:"10%",align:"center"},
                {field:'supplierName',title:'供应商',width:"10%",align:"center"},
                {field:'brandName',title:'品牌',width:"10%",align:"center"},
                {field:'bid',title:'本次进价/件',width:"18%",align:"center"},
                {field:'amount',title:'采购数量',width:"18%",align:"center"},
                {field:'money',title:'总计金额(元)',width:"15%",align:"center"},
                {field:'addtime',title:'添加时间',width:"10%",align:"center"}
            ]]
        });


        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。

        $("#saveBtn_audit").linkbutton({

            onClick: function () {
                var reqUrl = "audit/action";
              /*  if( ${type == 2 } ){
                    reqUrl = "caiwu/audit/action";
                }*/
                var isValid = $("#auditForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var queryArray = $('#auditForm').serializeArray();
                var postData = parsePostData(queryArray);

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
                                $('#detailWindowP').window('close');
                                $('#dg').datagrid('reload');
                                window.location.reload();

                            }
                        },
                        error: function () {
                            $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                        }
                    });

            }
        });

        $('#cancelBtn_audit').linkbutton({
            onClick: function(){
                $('#detailWindowP').window('close');
            }
        });
    })
</script>
</body>
</html>
