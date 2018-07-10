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
        urlParams = GetRequest();
        $("#dg").datagrid({
            title:'人员信息',
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'personsData?data='+urlParams[data],
            columns:[[
                {field:'id',title:'任务Id',width:"10%"},
            ]]
        })
    })

    function GetRequest() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }
</script>
</body>
</html>
