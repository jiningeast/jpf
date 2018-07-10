<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员列表</title>
</head>
<body>
<div name="contentDiv" style="padding: 15px;">
    <div id="dg"></div>
</div>
<script>
    $(function () {
        var data = getQueryString("data");
        console.log(data);
        $("#dg").datagrid({
            title:'人员信息',
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'personsData?data='+${data},
            columns:[[
                {field:'type',title:'类型',width:"10%"},
                {field:'bankName',title:'总行名称',width:"10%"},
                {field:'province',title:'开户行省',width:"10%"},
                {field:'city',title:'开户行市',width:"10%"},
                {field:'bankNo',title:'收款账号',width:"10%"},
                {field:'name',title:'收款户名',width:"10%"},
                {field:'IdNo',title:'身份证号',width:"10%"},
                {field:'phone',title:'手机号',width:"10%"},
                {field:'money',title:'打款金额',width:"10%"},
                {field:'memo',title:'备注',width:"10%"},
            ]]
        })
    })

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        var q = window.location.pathname.substr(1).match(reg_rewrite);
        if(r != null){
            return unescape(r[2]);
        }else if(q != null){
            return unescape(q[2]);
        }else{
            return null;
        }
    }
</script>
</body>
</html>
