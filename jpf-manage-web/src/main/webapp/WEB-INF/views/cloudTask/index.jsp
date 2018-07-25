<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>代付任务</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script type="text/javascript" src="${basePath}/resources/js/Base64.js"></script>
    <style>
        .notice{ padding: 10px; background: rgb(255,251,230); border: solid 1px rgb(255,229,143); margin-bottom: 20px; }
        .notice h6 { margin: 0; }
        .notice p:last-child { margin-bottom: 0; }
        table.payment tr { line-height: 3; }
        table.payment tr td:first-child { text-align: right;}
        table.companys tr td:nth-child(odd) { text-align: right; }
        .datagrid tr td { text-align: center; }
        .datagrid-mask { z-index: 9998; }
        .datagrid-mask-msg { z-index: 9999; }
    </style>
    <script>
        $(function(){

            // 选取公司弹窗大小
            $('#addWindow').window({
                width:'800px',
                height:'350px',
                closed:true,
                modal:true
            });

            // 任务详情弹窗大小
            $('#detailWindow').window({
                width:'1024px',
                height:'500px',
                closed:true,
                modal:true
            });

            var toolbar = [{
                text:'新增',
                iconCls:'icon-add',
                handler:function(){
                    $("#addWindow").window("open").window('refresh','../cloudTask/addTask').window('setTitle','新增代付任务');
                }
            },{
                text:'详情',
                iconCls:'icon-view-detail',
                handler:function () {
                    var rows = $('#taskDataGrid').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $("#detailWindow").window("open").window('refresh','../cloudTask/taskDetail?taskId='+rows[0].id).window('setTitle','任务详情');
                }
            }];

            // 任务数据
            $('#taskDataGrid').datagrid({
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
                    {field:'opratorName',title:'操作人',width:"10%"},
                    {field:'companyName',title:'企业名称',width:"10%"},
                    {field:'agentNo',title:'代理号',width:"10%"},
                    {field:'merchNo',title:'商户号',width:"10%"},
                    {field:'batchno',title:'批次号',width:"10%"},
                    {field:'status',title:'状态',width:"10%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "未处理";
                            }else if ( value == 1 ){
                                return "部分失败";
                            }else if ( value == 2 ){
                                return "全部失败";
                            }else if ( value == 3 ){
                                return "全部成功";
                            }
                        }},
                    {field:'isLock',title:'锁定',width:"10%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "未锁定";
                            }else if ( value == 1 ){
                                return "已锁定";
                            }
                        }},
                    {field:'created',title:'创建时间',width:"10%",formatter: formatDateStr},
                    {field:'finishtime',title:'完成时间',width:"10%",formatter: formatDateStr}
                ]]
            });
        })
    </script>
</head>
<body>
<div name="contentDiv">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#tasks_ft'" style="padding: 15px;">
        <form id="searchForm" method="post">
            <table class="companys" cellpadding="5" width="75%">
                <tr>
                    <td>任务ID:</td>
                    <td><input id="id" name="id" class="easyui-textbox" type="text" /></td>
                    <td>操作人:</td>
                    <td><input id="operatorName" name="operatorName" class="easyui-textbox" type="text" /></td>
                    <td>企业名称:</td>
                    <td><input id="companyName" name="companyName" class="easyui-textbox" type="text" /></td>
                </tr>
                <tr>
                    <td>代理商户号:</td>
                    <td><input id="agentNo" name="agentNo" class="easyui-textbox" type="text" /></td>
                    <td>企业商户号:</td>
                    <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" /></td>
                    <td>批次号:</td>
                    <td><input id="batchNo" name="batchNo" class="easyui-textbox" type="text" /></td>
                </tr>
                <tr>
                    <td>鉴权状态:</td>
                    <td>
                        <select id="status" name="status" class="easyui-combobox">
                            <option value="">全部</option>
                            <option value="0">未处理</option>
                            <option value="1">部分失败</option>
                            <option value="2">全部失败</option>
                            <option value="3">全部成功</option>
                        </select>
                    </td>
                    <td>锁定状态:</td>
                    <td>
                        <select id="isLock" name="status" class="easyui-combobox">
                            <option value="">全部</option>
                            <option value="0">未锁定</option>
                            <option value="1">已锁定</option>
                        </select>
                    </td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td>创建时间:</td>
                    <td>
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                               name="addtimeStart"
                               onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        -
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                               name="addtimeEnd"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                    <td>完成时间:</td>
                    <td>
                        <input type="text" class="Wdate" style="width:100px;" id="finishStart"
                               name="finishStart"
                               onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'finishStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        -
                        <input type="text" class="Wdate" style="width:100px;" id="finishEnd"
                               name="finishEnd"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'finishEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                    <td colspan="2"></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="tasks_ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <div id="taskDataGrid"></div>
    <div id="addWindow" style="padding: 10px;"></div>
    <div id="detailWindow" style="padding: 10px;"></div>
</div>
</body>
