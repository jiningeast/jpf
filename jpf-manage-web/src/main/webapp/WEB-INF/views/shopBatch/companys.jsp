<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>选取公司</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<div name="contentDiv" style="margin: 10px;">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#companys_ft'">
        <form id="searchForm" method="post">
            <table class="companys" cellpadding="5" width="100%">
                <tr>
                    <td>商户ID:</td>
                    <td><input id="merchId" name="id" class="easyui-textbox" type="text" /></td>
                    <td>商户号:</td>
                    <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" /></td>
                    <td>企业名称:</td>
                    <td><input id="merchName" name="companyName" class="easyui-textbox" type="text" /></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="companys_ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="companysDataGrid"></table>
</div>
<script>
    $(function(){
        var toolbar = [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
                var rows = $('#companysDataGrid').datagrid('getSelections');
                if (rows.length != 1) {
                    $.messager.alert('消息提示','请选择一条数据！','info');
                    return false;
                }
                $("#name").textbox("setValue",rows[0].companyName);
                $("#mid").val(rows[0].id);
                $("#companys").window("close");
                //查询可用合同
                getContracNo(rows[0].id)
            }
        }];


        $('#companysDataGrid').datagrid({
            title:'商户信息列表',
            toolbar:toolbar,
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'../shopCompany/list',
            columns:[[
                {field:'id',title:'商户Id',width:"5%"},
                {field:'merchNo',title:'聚合商户号',width:"10%"},
                {field:'companyName',title:'商户名称',width:"10%"},
                {field:'contactName',title:'联系人姓名',width:"10%"},
                {field:'contactPhone',title:'联系人手机号',width:"10%"},
                {field:'receiveName',title:'接收人姓名',width:"10%"},
                {field:'receivePhone',title:'接收人手机号',width:"10%"},
                {field:'receiveEmail',title:'接收人邮箱',width:"10%"},
                {field:'money',title:'余额',width:"5%"},
                {field:'addtime',title:'创建时间',width:"20%",formatter: formatDateStr}
            ]]
        });

        $('#searchBtn').linkbutton({
            onClick: function(){
                var queryArray = $('#searchForm').serializeArray();
                var postData = parsePostData(queryArray);
                console.log(postData);
                $('#companysDataGrid').datagrid('reload', postData);
            }
        });

        $('#searchRestBtn').linkbutton({
            onClick: function(){
                $('#searchForm').form('reset');
            }
        });
    })

    function getContracNo(companyId){
        $.ajax({
            type:"POST",
            url:"../shopCompanyCharge/getCompanyCharge",
            data:{
                "companyId":companyId
            },
            success:function(data){
                $("#contractId").empty();
                if(data.length==0){
                    $.messager.alert('消息提示','无可用合同!','info');
                    $("#contractId").combobox('loadData', {});
                    return false;
                }else{
                    $("#contractId").append("<option value=''>--请选择--</option>");
                    for(var i=0;i<data.length;i++){
                        $("#contractId").append("<option value="+data[i].id+">"+data[i].contractNo+"(余额:"+data[i].balance+")</option>");
                    }
                    $("#contractId").combobox({});
                }
            }
        });
    }
</script>
</body>
