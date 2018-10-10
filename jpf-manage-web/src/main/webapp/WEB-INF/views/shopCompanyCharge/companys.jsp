<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>代理公司管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>

    <style>
        #searchcompanyChargeForm table tr td:nth-child(odd) { text-align: right; }
        #searchcompanyChargeForm table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" style="margin: 10px; overflow: hidden;">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#CompanyChargeft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchcompanyChargeForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>商户编号:</td>
                        <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" /></td>
                        <td>公司名称:</td>
                        <td><input id="companyName" name="companyName" class="easyui-textbox" type="text" /></td>
                    </tr>
                    <tr>
                        <td>所属销售姓名:</td>
                        <td><input id="saleName" name="saleName" class="easyui-textbox" type="text" /></td>
                        <td>添加起止时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <td>登录状态:</td>
                        <td>
                            <select id="status" name="status" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">已停用</option>
                                <option value="1">已启用</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="CompanyChargeft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="companyChargedg"></table>
</div>
<div id="companyChargeInfoDiv"></div>
<div id="edit"></div>
<script>
    $(function() {
        $('#companyChargeInfoDiv').window({
            title:'详情',
            width:'80%',
            height:'300px',
            closed:true,
            modal:true
        });
        var toolbar = [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){
                var rows = $('#companyChargedg').datagrid('getSelections');
                if (rows.length != 1) {
                    $.messager.alert('消息提示','请选择一条数据！','info');
                    return false;
                }
                if( rows[0].status != 1 ){
                    $.messager.alert('消息提示','商户已停用,无法充值！','info');
                    return false;
                }
                $("#companyName").textbox("setValue",rows[0].companyName);
                $("#companyId").val(rows[0].id);
                $("#companys").window("close");
            }
        }];

        $('#companyChargedg').datagrid({
            title:'代理商户信息',
            toolbar:toolbar,
            // rownumbers:true,//如果为true，则显示一个行号列。
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
            singleSelect:true,
            multiselect:true,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            // width:500,
            url:'../shopCompany/list',
            columns:[[
                {field:'id',title:'ID',width:'3%'},
                {field:'merchNo',title:'商户编号',width:'11%'},
                {field:'companyName',title:'公司名称',width:'10%'},
                {field:'contactName',title:'联系人姓名',width:'8%'},
                {field:'contactPhone',title:'联系电话',width:'8%'},
                {field:'receiveName',title:'接收人姓名',width:'8%'},
                {field:'receivePhone',title:'接收人电话',width:'8%'},
                {field:'receiveEmail',title:'接收人邮箱',width:'10%'},
                {field:'saleName',title:'所属销售名字',width:'8%'},
                {field:'salePhone',title:'所属销售电话',width:'8%'},
                {field:'addtime',title:'添加时间',width:'10%',formatter: formatDateStr},
                {field:'money',title:'余额',width:"5%"},
                {field:'status',title:'登录状态',width:'5%',
                    formatter : function(value,row,index){
                        if(value=='1'){return '启用中'}
                        else{return '已停用'}
                    },styler: function (value, row, index) {
                        return 'color:red';
                    }
                }

            ]]
        });

        $('#searchBtn').linkbutton({
            onClick: function(){
                var queryArray = $('#searchcompanyChargeForm').serializeArray();
                var postData = parsePostData(queryArray);
                $('#companyChargedg').datagrid('reload', postData);
            }
        });

        $('#searchRestBtn').linkbutton({
            onClick: function(){
                $('#searchcompanyChargeForm').form('reset');
            }
        });
    });

    $(window).resize(function() {
        var width = $(window).width() - 20;
        //console.info(width);
        $("div[name='contentDiv']").css("width", width);

        // 加上这个，form面板和grid面板右侧不会出现残缺
        $("#formDiv").panel().width=1;
        $('#companyChargedg').datagrid().width=1;
    });

    $(window).load(function() {
        var width = $(window).width() - 20;
        $("div[name='contentDiv']").css("width", width);

        // 加上这个，form面板和grid面板右侧不会出现残缺
        $("#formDiv").panel().width=1;
        $('#companyChargedg').datagrid().width=1;
    });

    function get_time() {
        return new Date().getTime();
    }
</script>
</body>
</html>