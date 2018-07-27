<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/7/11
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <!DOCTYPE html>
        <head>

            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>银行管理</title>
            <style>

                .statistics td:nth-child(odd) { background-color: rgb(241,241,241); text-align: right;}
                .statistics td:nth-child(even) { text-align: left; }
            </style>
            <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
            <script>
                $(function(){

                    $('#infoDiv').window({
                        width:'1024px',
                        height:'550px',
                        closed:true,
                        modal:true
                    });

                    var toolbar = [
                        {
                            text : '代付明细',
                            iconCls : 'icon-view-detail',
                            handler : function(){
                                var rows = $('#dg').datagrid('getSelections');
                                if (rows.length != 1) {
                                    $.messager.alert('消息提示','请选择一条数据！','info');
                                    return false;
                                }
                                $('#infoDiv').window("open").window('refresh', '../dfdetail/page?companyMoneyId='+rows[0].id).window('setTitle','代付明细');
                            }
                        }
                    ];

                    $("#dg").datagrid({
                        title : '云账户代付列表',
                        toolbar:toolbar,
                        pagination:true,
                        singleSelect:true,
                        multiselect:false,
                        selectOnCheck:true,
                        url : 'list',
                        columns : [[
                            {field:'id', title:'ID',width:'4%'},
                            {field:'fid', title:'合同编号',width:'10%'},
                            {field:'batchno', title:'批次号',width:'10%'},
                            {field:'agentNo', title:'代理商户号',width:'8%'},
                            {field:'merchNo', title:'企业商户号',width:'8%'},
                            {field:'uid', title:'企业名称',width:'5%'},
                            // {field:'commoney', title:'发放总金额',width:'5%'},
                            {field:'batchallmoney', title:'总金额',width:'5%'},
                            {field:'batchitems', title:'总笔数',width:'5%'},
                            {field:'feemoney', title:'服务费金额',width:'5%'},
                            {field:'taxmoney', title:'增值税金额',width:'5%'},
                            {field:'taxmoremoney', title:'增值税附加金额',width:'5%'},
                            {field:'profitmoney', title:'毛利金额',width:'5%'},
                            {field:'montype', title:'状态',width:'6%',
                                formatter:function (value, row, index) {
                                    if ( value == 0 ){
                                        return '待锁定';
                                    }else if ( value == 1 ){
                                        return '待付款';
                                    }else if ( value == 2 ){
                                        return '<span style="color:blue">处理完成</span>';
                                    }else if ( value == 3 ){
                                        return '<span style="color:red">处理完成(部分失败)</span>';
                                    }else if ( value == 4 ){
                                        return '<span style="color:green">处理失败</span>';
                                    }else if ( value == -1 ){
                                        return '<span style="color:red">已删除</span>';
                                    }
                                }
                            },
                            {field:'addtime', title:'创建时间',width:'8%',formatter: formatDateStr},
                            {field:'updatetime', title:'修改时间',width:'8%',formatter: formatDateStr}
                        ]],
                        onLoadSuccess: function (msg) {

                            $("#allCount").text(msg.allCount);
                            $("#allMoney").text(msg.allMoney);
                            $("#allServiceMoney").text(msg.allServiceMoney);
                            $("#addedMoney").text(msg.addedMoney);
                            $("#addedMoneyPay").text(msg.addedMoneyPay);
                            $("#totalGross").text(msg.totalGross);
                        }
                    });

                    $('#searchBtn').linkbutton({
                        onClick: function(){
                            // var param = {};
                            // param["username"]=$('#username_s').textbox('getValue');
                            // param["status"]=$('#status_s').combobox('getValue');
                            // $('#dg').datagrid('reload', param);

                            var queryArray = $('#searchForm').serializeArray();
                            var postData = parsePostData(queryArray);
                            $('#dg').datagrid('reload', postData);
                        }
                    });

                    $('#searchRestBtn').linkbutton({
                        onClick: function(){
                            $('#searchForm').form('reset');
                        }
                    });
                })

            </script>
            <style>
                #searchForm td:nth-child(odd) { text-align: right; }
                #searchForm td:nth-child(even) { text-align: left; }
            </style>
        </head>
<body>
<div name="contentDiv">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>合同编号:</td>
                        <td><input id="fid" name="fid" class="easyui-textbox" type="text" /></td>
                        <td>代理聚合商户编号:</td>
                        <td><input id="agent_no" name="agent_no" class="easyui-textbox" type="text" /></td>

                    </tr>
                    <tr>
                        <td>充值状态:</td>
                        <td>
                            <select id="status_s" name="montype" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">待锁定</option>
                                <option value="1">待打款</option>
                                <option value="2">处理完成</option>
                                <option value="3">处理完成（部分失败）</option>
                                <option value="4">处理失败</option>
                                <option value="-1">已删除</option>
                            </select>
                        </td>
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
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <div class="easyui-panel statistics" title="汇总统计">
        <table width="100%" cellpadding="5%">
            <tr>
                <td width="4%">总笔数：</td>
                <td width="4%" id="allCount"></td>
                <td width="4%">总金额：</td>
                <td width="4%" id="allMoney"></td>
                <td width="4%">总服务费：</td>
                <td width="4%" id="allServiceMoney"></td>
                <td width="4%">总增值税：</td>
                <td width="4%" id="addedMoney"></td>
                <td width="4%">总增值税附加：</td>
                <td width="4%" id="addedMoneyPay"></td>
                <td width="4%">总毛利：</td>
                <td width="4%" id="totalGross"></td>
            </tr>
        </table>
    </div>
    <br/>
    <table id="dg"></table>
    <div id="infoDiv"></div>
</div>

<script>

    function initData() {

        //$('#status_s').combobox('select', '2');

    }


    $(function () {

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);


    });

</script>
</body>
</html>
