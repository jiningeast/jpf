<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>门店管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {
            $("#dg").datagrid({
                title:"门店管理",
                url:'list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                columns:[[
                    {field:"id", title:"ID", width:"10%"},
                    {field:"mtsid", title:"商户ID", width:"10%"},
                    {field:"mtsName", title:"商户名称", width:"10%"},
                    {field:"pid", title:"上线ID", width:"10%"},
                    {field:"parMtsName", title:"上线商户名称", width:"10%"},
                    {field:"path", title:"路径", width:"10%"},
                    {field:"isDel", title:"是否删除", width:"10%",
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return "未删除";
                            }else if ( value == 1 ){
                                return "<span style='color:red;'>已删除</span>";
                            }
                        }},
                    {field:"created", title:"创建时间", width:"10%", formatter:formatDateStr},
                    {field:"updated", title:"修改时间", width:"10%", formatter:formatDateStr}
                ]]
            })
        })
    </script>
</head>
<body>
    <table id="dg"></table>
</body>
</html>
