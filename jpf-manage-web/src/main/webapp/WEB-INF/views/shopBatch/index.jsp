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

            var toolbar = [{
                text:'新增',
                iconCls:'icon-add',
                handler:function(){
                    $("#addWindow").window("open").window('refresh','../shopBatch/addBatch').window('setTitle','新增欣券批次');
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
                    {field:'id',title:'批次id',width:"10%"}
                ]]
            });
        })
    </script>
</head>
<body>
<div name="contentDiv">
    <div id="batchDG"></div>
    <div id="addWindow" style="padding: 5px;"></div>
</div>
</body>