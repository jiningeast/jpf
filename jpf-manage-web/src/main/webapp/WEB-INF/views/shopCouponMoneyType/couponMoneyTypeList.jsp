<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>金额类型列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            $('#add').window({
                title:'新增面值',
                width:'50%',
                height:'300px',
                closed:true,
                modal:true,
                maximized:false,//弹出窗口最大化

            });
            $('#edit').window({
                title:'编辑',
                width:'50%',
                height:'300px',
                closed:true,
                modal:true,
                maximized:false,//弹出窗口最大化
            });
            var toolbar = [
                {
                    text:'新增',
                    iconCls:'icon-add',
                    handler:function(){
                        $('#add').window("open").window('refresh', '/shopCouponMoneyType/goAdd').window('setTitle','新增面值');
                    }
                },
                {
                    text : '编辑',
                    iconCls:'icon-edit',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $('#edit').window("open").window('refresh', '/shopCouponMoneyType/goUpdate?id='+rows[0].id).window('setTitle','编辑面值');
                    }
                }
            ];
            $('#dg').datagrid({
                title:'欣券面值信息',
                toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'/shopCouponMoneyType/list',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'money',title:'面值(元)',width:'8%'},
                    {field:'status',title:'状态',width:'5%',
                        formatter:function (value,row,index) {
                            if ( value==0 ){
                                return "显示";
                            }else if(value==1){
                                return "隐藏";
                            }else{
                                return "自定义";
                            }
                        }},
                    {field:'addtime',title:'新增时间',width:'12%',formatter: formatDateStr},
                    {field:'updatetime',title:'修改时间',width:'12%',formatter: formatDateStr}
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
                        <td>面值(元):</td>
                        <td>
                            <select name="money" id="money"   class="easyui-combobox"  style="width:120px;">
                                <option value="">--请选择--</option>
                                <C:forEach items="${moneyList}" var="money">
                                    <option value="${money}">${money}</option>
                                </C:forEach>
                            </select>
                        </td>
                        <td>状态:</td>
                        <td>
                            <select name="status" id="status"  class="easyui-combobox" style="width:120px;">
                                <option value="">--请选择--</option>
                                <option value="0">显示</option>
                                <option value="1">隐藏</option>
                                <option value="2">自定义</option>
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
<div id="add"></div>
<div id="edit"></div>
</body>
</html>