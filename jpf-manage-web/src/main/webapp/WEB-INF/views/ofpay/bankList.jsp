<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银行管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function(){
            var toolbar = [

            ];

            $("#dg").datagrid({
                title : '账务明细列表',
                toolbar:toolbar,
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                url : 'list',
                columns : [[
                    {field:'id', title:'编号',width:'6%'},
                    {field:'detailId', title:'流水号',width:'14%'},
                    {field:'inOutType', title:'收支类型',width:'14%'},
                    {field:'fundsType', title:'业务类型',width:'14%'},
                    {field:'inComeCost', title:'收入金额',width:'14%'},
                    {field:'expenditureCost', title:'支出金额',width:'14%'},
                    {field:'leftBalance', title:'账户余额',width:'14%'},
                    {field:'billId', title:'业务单号',width:'14%'},
                    {field:'occurTime', title:'修改时间',width:'14%',formatter: formatDateStr}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');

            $('#edit').window({
                width:'400px',
                height:'200px',
                closed:true,
                modal:true
            });
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#CompanyChargeft'">
            <div style="padding:10px 60px 20px 60px">
                <form id="searchForm" method="post">
                    <table cellpadding="5" width="50%">
                        <tr>
                            <td>创建时间：</td>
                            <td>
                                <input type="text" class="Wdate" style="width:158px;" id="startAddTime_s"
                                       name="startAddTime"
                                       onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endAddTime_s\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                -
                                <input type="text" class="Wdate" style="width:158px;" id="endAddTime_s"
                                       name="endAddTime"
                                       onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startAddTime_s\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>接口商家：</td>
                            <td >
                                <select id="paymenttype" name="paymenttype" class="easyui-combobox" style="width: 70px;">
                                    <option value="">全部</option>
                                    <option value="0">收入</option>
                                    <option value="1">支出</option>
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
    </div>
    <table id="dg"></table>
    <div id="edit"></div>
</body>
</html>
