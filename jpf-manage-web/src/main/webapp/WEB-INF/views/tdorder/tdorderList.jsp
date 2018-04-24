<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>退单列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        td,th{ text-align: center; }
    </style>
    <script>
        $(function () {
            var toolbar = [
                {
                    text:'通过审核',
                    iconCls:'icon-ok',
                    handler:function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        if( rows[0].singlestatus !== 0 ){
                            $.messager.alert('消息提示','此订单已处理，请不要重复处理','info');
                            return false;
                        }
                        var id = rows[0].id;
                        var tdorderid = rows[0].tdorderid;
                        var orderid = rows[0].orderid;
                        var tdorderprice = rows[0].tdorderprice;
                        var mtsid = rows[0].mtsid;
                        var singletype = rows[0].singletype;

                        $.post("checkOk",{
                            id:id,
                            tdorderid:tdorderid,
                            orderid:orderid,
                            tdorderprice:tdorderprice,
                            mtsid:mtsid,
                            singletype:singletype,
                            singlestatus:0
                        },function (msg) {
                            if (msg.retCode != '0000') {
                                $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                return false;
                            } else {
                                $.messager.alert('消息提示','操作成功!','info');
                                $('#dg').datagrid('reload');
                            }
                        })
                    }
                },{
                    text:'驳回审核',
                    iconCls:'icon-no',
                    handler:function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        if( rows[0].singlestatus !== 0 ){
                            $.messager.alert('消息提示','此订单已处理，请不要重复处理','info');
                            return false;
                        }
                        $("#opeCon").window('setTitle','驳回理由').window('refresh').window('open');
                    }
                }
            ]

            $("#dg").datagrid({
                title : '退单列表',
                toolbar : toolbar,
                pagination : true,
                singleSelect : true,
                multiselect : false,
                selectOnCheck : true,
                url:'list',
                columns:[[
                    {field:'id', title:'ID', width:'3%'},
                    {field:'tdorderid',title:'退单ID', width:'10%'},
                    {field:'orderid',title:'订单ID', width:'10%'},
                    {field:'tdorderprice',title:'退单金额', width:'5%',formatter:formatPrice},
                    {field:'mtsid',title:'商户ID', width:'5%'},
                    {field:'singletype',title:'退单类型', width:'5%',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '支付后退款';
                            }else if ( value == 2 ){
                                return '未支付撤单';
                            }
                        }},
                    {field:'singlestatus',title:'运营审核', width:'5%',
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return '未审核';
                            }else if ( value == 1 ){
                                return '<span style="color:blue">已审核</span>';
                            }else if ( value == 2 ){
                                return '<span style="color:red">已驳回</span>';
                            }else if ( value == 3 ){
                                return '<span style="color:gray">已撤单</span>';
                            }
                        }},
                    {field:'operateContent', title:'审核信息', width:'35%', align:'left', formatter:formatJSONOpCon},
                    {field:'urla',title:'图片', width:'10%', formatter:formatJSONImg, align:'left'},
                    {field:'content',title:'退单理由', width:'10%',
                        formatter:function (value, row, index) {
                            return JSON.parse(value);
                            // console.log( JSON.parse(value) );
                        }},
                    {field:'addtime',title:'添加时间',formatter:formatDateStr, width:'10%'}
                ]]
            })

            $('#opeCon').window({
                width:'400px',
                height:'300px',
                closed:true,
                minimizable:false,
                iconCls:'icon-no',
                modal:true
            });

            // 点击驳回弹窗的取消
            $("#opCancelBtn").linkbutton({
                onClick:function () {
                    $('#opeCon').window('close');
                }
            })

            // 点击驳回弹窗的确定
            $("#opSubmitBtn").linkbutton({
                onClick:function () {
                    var rows = $("#dg").datagrid('getSelections');
                    var id = rows[0].id;
                    var tdorderid = rows[0].tdorderid;
                    var orderid = rows[0].orderid;
                    var operateContent = $("#operateContent").val();
                    if ( operateContent == '' ){
                        $.messager.alert('消息提示','请输入驳回理由','info');
                    }
                    $.post("checkNo",{id:id, tdorderid:tdorderid, orderid:orderid, operateContent:operateContent},function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                            $("#opeCon").window('close');
                            return false;
                        } else {
                            $.messager.alert('消息提示','操作成功!','info');
                            $('#dg').datagrid('reload');
                            $("#opeCon").window('close');
                        }
                    })
                }
            })
        })
    </script>
</head>
<body>
    <table id="dg"></table>
    <%--驳回理由窗口--%>
    <div id="opeCon" class="easyui-window" title="驳回理由" style="padding: 10px;">
        <div region="center" border="false" style="padding: 10px;">
            <form method="post" id="opeConForm" enctype="multipart/form-data">
                <table class="table table-bordered">
                    <tr>
                        <td>驳回理由：</td>
                        <td><textarea id="operateContent" name="operateContent" style="width: 200px; height: 150px;"></textarea></td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" border="false"
             style="text-align: right; height: 30px; line-height: 30px;">
            <a id="opSubmitBtn" class="easyui-linkbutton" icon="icon-ok"
               href="javascript:void(0)">确定</a>
            <a id="opCancelBtn" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>
    </div>
</body>
</html>