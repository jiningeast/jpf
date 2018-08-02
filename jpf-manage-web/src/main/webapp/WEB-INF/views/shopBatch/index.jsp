<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欣券管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>

    </style>
    <script>
        $(function () {
            var toolbar = [{
                text:'新增批次',
                iconCls:'icon-add',
                handler:function(){
                    $("#addWindow").window("open").window('refresh','../shopBatch/addBatch').window('setTitle','新增欣券批次');
                }
            },{
                text:'详情',
                iconCls:'icon-view-detail',
                handler:function () {
                    var rows = $('#taskDataGrid').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $("#detailWindow").window("open").window('refresh','../cloudTask/taskDetail?taskId='+rows[0].id).window('setTitle','任务详情');
                }
            }];

            // 欣券数据
            $('#batchDG').datagrid({
                title:'欣券信息列表',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'list',
                columns:[[
                    {field:'id',title:'任务Id',width:"10%"},
                    {field:'opratorName',title:'操作人',width:"10%"},
                    {field:'companyName',title:'企业名称',width:"10%"},
                    {field:'agentNo',title:'代理号',width:"10%"},
                    {field:'merchNo',title:'商户号',width:"10%"},
                    {field:'batchno',title:'批次号',width:"10%"},
                    {field:'status',title:'状态',width:"10%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "未处理";
                            }else if ( value == 1 ){
                                return "部分失败";
                            }else if ( value == 2 ){
                                return "全部失败";
                            }else if ( value == 3 ){
                                return "全部成功";
                            }
                        }},
                    {field:'isLock',title:'锁定',width:"10%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "未锁定";
                            }else if ( value == 1 ){
                                return "已锁定";
                            }
                        }},
                    {field:'created',title:'创建时间',width:"10%",formatter: formatDateStr},
                    {field:'finishtime',title:'完成时间',width:"10%",formatter: formatDateStr}
                ]]
            });
        })
    </script>
</head>
<body>
<div class="contentDiv">
    <div id="batchDG"></div>
    <div id="addWindow" style="padding: 10px;"></div>
</div>
</body>