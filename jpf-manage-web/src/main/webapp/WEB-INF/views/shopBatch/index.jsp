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
                width:'1300px',
                height:'550px',
                closed:true,
                modal:true
            });

            // 发送邮件选项
            $("#sendEmail").window({
                width:'900px',
                height:'250px',
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
            },{
                text:'发送...',
                iconCls:'icon-redo',
                handler:function(){
                    var rows = $('#batchDG').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $("#sendEmail").window("open").window('refresh','../shopBatch/sendEmail?companyId='+rows[0].companyId+'&batchId='+rows[0].id).window('setTitle','发送选项');
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
                fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                url:'list',
                columns:[[
                    {field:'id',title:'批次id',width:"5%"},
                    {field:'companyName',title:'商户名称',width:"10%"},
                    {field:'batchNo',title:'批次号',width:"15%"},
                    {field:'money',title:'批次总金额',width:"10%"},
                    {field:'count',title:'券数量',width:"10%"},
                    {field:'activetedNum',title:'已激活',width:"10%"},
                    {field:'expireMonth',title:'有效期',width:"10%",formatter:function(value,row,index){
                        return value+"个月";
                        }},
                    {field:'sendType',title:'发送方式',width:"10%",formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "<font style='color: #0e90d2'>Email发送</font>";
                            }else if ( value == 1 ){
                                return "<font style='color: #0e90d2'>群发给个人</font>";
                            }else {
                                return "未发送";
                            }
                        }},
                    {field:'sendTime',title:'发送时间',width:"11%",formatter:formatDateStr}
                ]]
            });
        })

        //采用jquery easyui loading css效果
        function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }
        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }
    </script>
</head>
<body>
<div name="contentDiv">
    <div id="batchDG"></div>
    <div id="addWindow" style="padding: 5px;"></div>
    <div id="detailWindow" style="padding: 10px;"></div>
    <div id="sendEmail"></div>
</div>
</body>