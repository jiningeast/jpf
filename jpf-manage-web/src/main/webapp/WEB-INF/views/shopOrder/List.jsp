<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>代理公司管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            $('#infoDiv').window({
                title:'详情',
                width:'80%',
                height:'500px',
                closed:true,
                modal:true
            });
           /* var toolbar = [
                {
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#infoDiv").window("open").window('refresh', 'addCompany/page').window('setTitle','新增');
                    }
                },
                {
                    text:'编辑',
                    iconCls:'icon-edit',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'edit/page?id='+rows[0].id).window('setTitle','编辑');
                    }
                },
                {
                    text : '停用',
                    iconCls:'icon-no',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $.messager.confirm('停用','确认停用操作？',function(r){
                            if (r){
                                $.ajax({
                                    type : 'get',
                                    url :'delCompanyShop?merchNo='+rows[0].merchNo+'&type=2',
                                    dataType:"json",
                                    contentType:"application/json",
                                    success : function(msg){
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                        } else {
                                            $.messager.alert('消息提示','操作成功!','info');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error : function () {
                                        $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                    }
                                })
                            }
                        })
                    }
                },
                {
                    text : '启用',
                    iconCls:'icon-ok',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $.messager.confirm('启用','确认启用操作？',function(r){
                            if (r){
                                $.ajax({
                                    type : 'get',
                                    url :'delCompanyShop?merchNo='+rows[0].merchNo+'&type=1',
                                    dataType:"json",
                                    contentType:"application/json",
                                    success : function(msg){
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                        } else {
                                            $.messager.alert('消息提示','操作成功!','info');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error : function () {
                                        $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                    }
                                })
                            }
                        })
                    }
                }
            ];*/

            $('#dg').datagrid({
                title:'欣豆市场订单列表',
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
                    {field:'orderNo',title:'订单号',width:'10%',
                        formatter : function(value,row,index){
                            return  "<a onclick='goActiveOrder("+row['orderNo']+")'>"+value+" </a>";
                        }
                    },
                    {field:'addtime',title:'下单时间',width:'10%',formatter: formatDateStr},
                    {field:'customerName',title:'客户名字',width:'8%'},
                    {field:'productName',title:'产品名称',width:'10%'},
                    {field:'typeName',title:'产品类型',width:'15%'},
                    {field:'amount',title:'数量',width:'10%'},
                    {field:'totalMoney',title:'总金额',width:'15%'},
                    {field:'totalDou',title:'总欣豆',width:'10%'},
                    {field:'paytime',title:'支付时间',width:'10%',formatter: formatDateStr},
                    {field:'status',title:'状态',width:'8%',
                        formatter : function(value,row,index){
                            if(value=='0'){return '待支付'}
                            else if(value=="1"){return '已支付'}
                            else if(value=="2"){return '支付失败'}
                            else if(value=="3"){return '已取消'}
                        },styler: function (value, row, index) {
                            return 'color:red';
                        }
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
        function goActiveOrder(orderNo) {
            $('#infoDiv').window("open").window('refresh', 'orderInfo?orderNo='+orderNo).window('setTitle','欣豆详情');
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
                        <td>产品名称:</td>
                        <td><input id="productName" name="productName" class="easyui-textbox" type="text" /></td>
                        <td>客户名称:</td>
                        <td><input id="customerName" name="customerName" class="easyui-textbox" type="text" /></td>
                        </tr>
                    <tr>
                        <td>订单号:</td>
                        <td><input id="orderNo" name="orderNo" class="easyui-textbox" type="text" /></td>
                        <td>下单时间:</td>
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
                        <td>支付时间:</td>

                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="paytimeStart"
                                   name="paytimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'paytimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:100px;" id="paytimeEnd"
                                   name="paytimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'paytimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                        <td>订单状态:</td>
                        <td>
                            <select id="status" name="status" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">待支付</option>
                                <option value="1">已支付</option>
                                <option value="2">支付失败</option>
                                <option value="3">已取消</option>
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
<div id="infoDiv"></div>
<div id="edit"></div>
</body>
</html>