<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欣券管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        #addCoupons tr td:nth-child(odd) { text-align: right; }
        #addCoupons tr td:nth-child(even) { text-align: left; }
        .datagrid-mask { z-index: 9998; }
        .datagrid-mask-msg { z-index: 9999; }
    </style>
    <script>
        $(function () {
            // 新增券批次弹窗大小
            $('#addWindow').window({
                width:'1024px',
                height:'500px',
                closed:true,
                modal:true
            });

            // 券批次详情弹窗大小
            $('#detailWindow').window({
                width:'1100px',
                height:'550px',
                closed:true,
                modal:true
            });

            var toolbar = [{
                text:'新增',
                iconCls:'icon-add',
                handler:function(){
                    $("#addWindow").window("open").window('refresh','../shopBatch/addBatch').window('setTitle','新增欣券批次');
                }
            },{
                text:'查看详情',
                iconCls:'icon-view-detail',
                handler:function(){
                    var rows = $('#batchDG').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $("#detailWindow").window("open").window('refresh','../shopBatch/detail?batchId='+rows[0].id).window('setTitle','欣券批次详情');
                }
            }/*,{
                text:'发送EMAIL',
                iconCls:'icon-redo',
                handler:function(){
                    var rows = $('#batchDG').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $.messager.confirm('确定发送含有压缩包的EMAIL吗？',function (r) {
                        if (r){
                            $.ajax({
                                type : 'get',
                                url : '',
                            })
                        }
                    })
                }
            }*/];

            // 欣券数据
            $('#batchDG').datagrid({
                title:'欣券信息列表',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                url:'list',
                columns:[[
                    {field:'id',title:'批次id',width:"5%"},
                    {field:'companyName',title:'商户名称',width:"10%"},
                    {field:'batchNo',title:'批次号',width:"15%"},
                    {field:'money',title:'金额',width:"10%"},
                    {field:'count',title:'券数量',width:"10%"},
                    {field:'activetedNum',title:'已激活',width:"10%"},
                    {field:'expireMonth',title:'有效期',width:"10%",formatter:function(value,row,index){
                        return value+"个月";
                        }},
                    {field:'status',title:'状态',width:"10%",formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "券生成中";
                            }else if ( value == 1 ){
                                return "券已生成，待发送";
                            }else if ( value == 2 ){
                                return "券已发送";
                            }else if ( value == 3 ){
                                return "已取消";
                            }
                        }}
                ]]
            });
        })
    </script>
</head>
<body>
<div name="contentDiv">
    <div id="batchDG"></div>
    <div id="addWindow" style="padding: 5px;"></div>
    <div id="detailWindow" style="padding: 10px;"></div>
</div>
</body>