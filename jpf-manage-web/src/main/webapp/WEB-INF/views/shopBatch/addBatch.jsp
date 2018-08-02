<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <title>新增打款任务</title>
</head>
<body>
<div class="contentDiv">
    <div id="formDiv"  class="easyui-panel" title="搜索条件" data-options="footer:'#tasks_ft'">
        <form id="searchForm">
            <table>
                <tr>
                    <td width="70px">打款公司：</td>
                    <td width="180px">
                        <input id="name" name="company_name" type="text" class="easyui-textbox" style="width: 100%" disabled="disabled">
                        <input id="mid" name="company_id" type="hidden">
                    </td>
                    <td align="left"><a id="searchCompany" class="easyui-linkbutton" href="javascript:void(0)" data-options="iconCls:'icon-search'">选取公司</a></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="companys"></div>
</div>
<script>
    $(function () {
        // 选取公司按钮
        $("#searchCompany").linkbutton({
            onClick:function(){
                $('#companys').window("open").window('refresh', '../cloudTask/companys').window('setTitle','选取公司');
            }
        });

        // 选取公司弹窗大小
        $('#companys').window({
            width:'1024px',
            height:'550px',
            closed:true,
            modal:true
        });
    })
</script>
</body>
</html>
