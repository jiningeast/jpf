<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>批量打款</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script type="text/javascript" src="${basePath}/resources/js/Base64.js"></script>
    <style>
        .notice{ padding: 10px; background: rgb(255,251,230); border: solid 1px rgb(255,229,143); margin-bottom: 20px; }
        .notice h6 { margin: 0; }
        .notice p:last-child { margin-bottom: 0; }
        table.payment tr { line-height: 3; }
        table.payment tr td:first-child { text-align: right;}
        table.companys tr td:nth-child(odd) { text-align: right; }
        .datagrid tr td { text-align: center; }
    </style>
    <script>
        $(function(){

            // 选取公司弹窗大小
            $('#addWindow').window({
                width:'800px',
                height:'350px',
                closed:true,
                modal:true
            });

            var toolbar = [{
                text:'新增打款任务',
                iconCls:'icon-add',
                handler:function(){
                    $("#addWindow").window("open").window('refresh','../cloudTask/addTask').window('setTitle','新增打款任务');
                }
            }];

            // 任务数据
            $('#taskDataGrid').datagrid({
                title:'任务信息列表',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'list',
                columns:[[
                    {field:'id',title:'任务Id',width:"10%"},
                    {field:'oprator_name',title:'操作人',width:"10%"},
                    {field:'company_name',title:'企业名称',width:"10%"},
                    {field:'agent_no',title:'代理号',width:"10%"},
                    {field:'merch_no',title:'商户号',width:"10%"},
                    {field:'batchno',title:'批次号',width:"10%"},
                    {field:'status',title:'状态',width:"10%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "未处理";
                            }else if ( value == 1 ){
                                return "处理中";
                            }else if ( value == 2 ){
                                return "完成";
                            }else if ( value == 3 ){
                                return "失败";
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
<div name="contentDiv">
    <div id="taskDataGrid"></div>
    <div id="addWindow" style="padding: 10px;"></div>
</div>
</body>
