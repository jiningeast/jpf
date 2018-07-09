<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员列表</title>
</head>
<body>
<div name="contentDiv">
    <div id="dg"></div>
</div>
<script>
    $(function () {
        $("#dg").datagrid({
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
        })
    })
</script>
</body>
</html>
