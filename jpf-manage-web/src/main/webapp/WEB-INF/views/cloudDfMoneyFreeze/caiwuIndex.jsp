<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>云账户解冻审核</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {
            var toolbar = [
                {
                    text : '解冻',
                    iconCls : 'icon-ok',
                    handler : function(){
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $.messager.confirm("解冻","确定要解冻吗？",function (r) {
                            if (r) {
                                $.ajax({
                                    type : 'get',
                                    url : '../unfreeze?id='+rows[0].id,
                                    dataType : "json",
                                    contentType : "application/json",
                                    success : function(msg){
                                        console.log(msg);
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                        } else {
                                            $.messager.alert('消息提示','操作成功!','info');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error : function () {
                                        $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                    }
                                })
                            }
                        });
                    }
                }
            ];

            $("#dg").datagrid({
                title:'云账户冻结管理',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'../getRecords',
                columns:[[
                    {field:'id',title:'ID',width:'5%'},
                    {field:'companyId',title:'商户id',width:'5%'},
                    {field:'companyName',title:'商户名称',width:'10%'},
                    {field:'companyMoneyId',title:'打款批次id',width:'5%'},
                    {field:'dfMoneyId',title:'打款订单id',width:'5%'},
                    {field:'orderid',title:'打款订单号',width:'10%'},
                    {field:'freezeMoney',title:'冻结金额',width:'5%'},
                    {field:'returnContent',title:'接口返回',width:'10%'},
                    {field:'moneyStatus',title:'是否解冻',width:'5%',
                        formatter:function (value,row,index) {
                            if ( value == '1' ) { return '未解冻'; }
                            if ( value == '2' ) { return '已解冻'; }
                        }},
                    {field:'status',title:'总状态',width:'5%',
                        formatter:function (value,row,index) {
                            if ( value == '1' ) { return '已冻结'; }
                            if ( value == '2' ) { return '运营申请解冻'; }
                            if ( value == '3' ) { return '财务已解冻'; }
                            if ( value == '4' ) { return '财务拒绝解冻'; }
                        }},
                    {field:'remarks',title:'备注',width:'5%'},
                    {field:'operatorId',title:'运营人员id',width:'8%'},
                    {field:'operatorName',title:'运营人员姓名',width:'8%'},
                    {field:'operatorTime',title:'运营申请时间',width:'10%',formatter:formatDateStr},
                    {field:'financeId',title:'财务审核id',width:'8%'},
                    {field:'financeName',title:'财务审核姓名',width:'8%'},
                    {field:'financeTime',title:'财务审核时间',width:'10%',formatter:formatDateStr},
                    {field:'addtime',title:'添加时间',width:'10%',formatter:formatDateStr}
                ]]
            })
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="dg"></div>
    </div>
</body>
