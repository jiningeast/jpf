<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>进销存统计</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            $('#dg').datagrid({
                title:'数据内容',
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
                    {field:'productId',title:'商品ID',rowspan:2,width:'10%',align:'center'},
                    {field:'productName',title:'商品名称',rowspan:2,width:'10%',align:'center'},
                   // {field:'stockOrderId',title:'采购ID',rowspan:2,width:80,align:'center'},
                    {title:'期初结存',colspan:2},
                    {title:'日常进货',colspan:2},
                    {title:'日常出货',colspan:2},
                    {title:'期末结存',colspan:2},
                ],[
                    {field:'beginCount',title:'数量',width:'10%',align:'center'},
                    {field:'beginMoney',title:'金额',width:'10%',align:'center'},
                    {field:'inAmount',title:'数量',width:'10%',align:'center'},
                    {field:'inMoney',title:'金额',width:'10%',align:'center'},
                    {field:'outAmount',title:'数量',width:'10%',align:'center'},
                    {field:'outMoney',title:'金额',width:'10%',align:'center'},
                    {field:'finishCount',title:'数量',width:'10%',align:'center'},
                    {field:'finishMoney',title:'金额',width:'10%',align:'center'},

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
        function goActive(id,phone,dou) {
            //var rows = $('#dg').datagrid('getSelections');
            $('#infoDiv').window("open").window('refresh', 'info?id='+id+'&phone='+phone+'&dou='+dou).window('setTitle','欣豆详情');
        }

    </script>
    <style>
        #searchForm table tr td:nth-child(odd) { text-align: right; }
        #searchForm table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" >
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" >
                    <tr>
                        <td>关键字:</td>
                        <td><input id="productName" name="productName" class="easyui-textbox" type="text" /></td>

                        <td>采购id:</td>
                        <td><input id="stockOrderId" name="stockOrderId" class="easyui-textbox" type="text" /></td>
                        </tr>
                    <tr>
                        <td>统计时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
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