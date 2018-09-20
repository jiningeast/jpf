<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商品采购管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
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
        $(function() {
            $('#addWindowP').window({
                title:'添加',
                width:'100%',
                height:'500px',
                closed:true,
                modal:true,
                // maximized:true,//弹出窗口最大化
            });
            $('#infoDiv').window({
                title:'添加',
                width:'60%',
                height:'400px',
                closed:true,
                modal:true,
                // maximized:true,//弹出窗口最大化
            });

            $('#detailWindowP').window({
                title:'审核',
                width:'80%',
                height:'500px',
                closed:true,
                modal:true,
                // maximized:true,//弹出窗口最大化
            });
            $('#viewWindow').window({
                title:'详情',
                width:'80%',
                height:'500px',
                closed:true,
                modal:true,
                // maximized:true,//弹出窗口最大化
            });


           var toolbar = [
                {
                    text : '新增采购订单',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#addWindowP").window("open").window('refresh', '../shopStockOrder/add/page').window('setTitle','新增');
                    }
                },{
                   text : '订单详情',
                   iconCls : 'icon-view-detail',
                   handler : function(){

                       var rows = $('#dg').datagrid('getSelections');
                       if (rows.length != 1) {
                           $.messager.alert('消息提示','请选择一条数据！','info');
                           return
                       }
                       $('#viewWindow').window("open").window('refresh', '../shopStockOrder/view/page?orderNo='+rows[0].orderNo).window('setTitle','详情');
                   }
               },{
                   text:'商品采购',
                   iconCls:'icon-ok',
                   handler:function(){

                       var rows = $('#dg').datagrid('getSelections');
                       if (rows.length != 1) {
                           $.messager.alert('消息提示','请选择一条数据！','info');
                           return
                       }
                       if(rows[0].status!='4'){

                           $.messager.alert('消息提示','此单尚未付款','info');
                           return false;
                       }

                       if(rows[0].isUpload=='2')
                            $('#infoDiv').window("open").window('refresh', 'purchase?id='+rows[0].id).window('setTitle','商品采购');
                       else
                          $.messager.alert('消息提示','此单已采购，请勿重复操作','info');
                   }
               },{
                    text:'审核',
                    iconCls:'icon-key-add',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#detailWindowP').window("open").window('refresh', '../shopStockOrder/audit/page?orderNo='+rows[0].orderNo+'&type=1').window('setTitle','审核');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'商品采购管理',
                toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'list',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'orderNo',title:'采购订单号',width:'15%'},
                    {field:'addtime',title:'采购时间',width:'12%',formatter: formatDateStr},
                    {field:'cardtime',title:'采购入库时间',width:'10%',formatter: formatDateStr},
                    {field:'productAmount',title:'采购数量',width:'10%'},
                    {field:'isUpload',title:'采购状态',width:'6%',
                        formatter : function(value,row,index){
                            if(value=='1'){return '已采购'}
                            else if(value=="2"){return '未采购'}

                        }/*,styler: function (value, row, index) {
                            return 'color:red';
                        }*/
                    },
                    {field:'money',title:'采购预付金额',width:'7%',formatter: function (value, row, index) {
                            if (row != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'operatorName',title:'操作人',width:'7%'},
                    {field:'status',title:'订单状态',width:'6%',
                        formatter : function(value,row,index){
                            if(value=='0'){return '已取消'}
                            else if(value=="1"){return '新建'}
                            else if(value=="2"){return '已提交'}
                            else if(value=="3"){return '已审批'}
                            else if(value=="4"){return '已付款'}
                        }/*,styler: function (value, row, index) {
                            return 'color:red';
                        }*/
                    }

                ]]
            });

            $('#searchBtn').linkbutton({
                onClick: function(){
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
        });

        $(window).resize(function() {
            var width = $(window).width() - 20;
            //console.info(width);
            $("div[name='contentDiv']").css("width", width);

            // 加上这个，form面板和grid面板右侧不会出现残缺
            $("#formDiv").panel().width=1;
            $('#dg').datagrid().width=1;
        });

        $(window).load(function() {
            var width = $(window).width() - 20;
            $("div[name='contentDiv']").css("width", width);

            // 加上这个，form面板和grid面板右侧不会出现残缺
            $("#formDiv").panel().width=1;
            $('#dg').datagrid().width=1;
        });

        function get_time() {
            return new Date().getTime();
        }
    </script>
    <style>
        #searchForm table tr td:nth-child(odd) { text-align: right; }
        #searchForm table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>采购时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:158px;border-radius: 4px;border-color: #95B8E7;padding-left: 5px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:158px;border-radius: 4px;border-color: #95B8E7;padding-left: 5px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                        <td>采购订单号:</td>
                        <td><input id="orderNo" name="orderNo" class="easyui-textbox" type="text" /></td>
                    </tr>
                    <tr>
                        <%--<td>关键字:</td>
                        <td><input id="productName" name="productName" class="easyui-textbox" type="text" /></td>--%>
                        <td>订单状态:</td>
                        <td><select id="status" name="status" class="easyui-combobox" style="width:70px;">
                                <option value="">全部</option>
                                <option value="0">取消</option>
                                <option value="1">新建</option>
                                <option value="2">提交</option>
                                <option value="3">审批</option>
                                <option value="4">付款</option>
                            </select>
                        </td>
                            <td>采购状态:</td>
                            <td><select id="isUpload" name="isUpload" class="easyui-combobox" style="width:70px;">
                                <option value="">全部</option>
                                <option value="1">已采购</option>
                                <option value="2">未采购</option>
                            </select>
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
    <table id="dg"></table>
</div>
<div id="infoDiv" style="padding: 10px;"></div>
<div id="addWindowP" style="padding: 5px;"></div>
<div id="detailWindowP" style="padding: 10px;"></div>
<div id="viewWindow" style="padding: 10px;"></div>
<div id="edit"></div>

</body>
</html>