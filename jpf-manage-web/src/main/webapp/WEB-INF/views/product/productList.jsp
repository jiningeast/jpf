<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商户信息管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            var toolbar = [
                {
                    text:'上架',
                    iconCls:'icon-ok',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $.messager.confirm('上架', '确定上架此产品：' + rows[0].pname, function (r) {
                            if (r) {
                                var param = {};
                                param["pname"] = rows[0].pname;
                                param["status"] = '1';
                                $.ajax({
                                    type: 'post',
                                    url: 'alertStatus',
                                    data: param,
                                    dataType: 'json',
                                    success: function (msg) {
                                        //alert(msg.retCode);
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']!', 'error');
                                        } else {
                                            $.messager.alert('消息提示', '操作成功!', 'info');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error: function () {
                                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                                    }
                                });
                            }
                        });

                    }
                },
                {
                    text:'下架',
                    iconCls:'icon-no',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $.messager.confirm('下架', '确定下架此产品：' + rows[0].pname, function (r) {
                            if (r) {
                                var param = {};
                                param["pname"] = rows[0].pname;
                                param["status"] = '0';
                                $.ajax({
                                    type: 'post',
                                    url: 'alertStatus',
                                    data: param,
                                    dataType: 'json',
                                    success: function (msg) {
                                        //alert(msg.retCode);
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']!', 'error');
                                        } else {
                                            $.messager.alert('消息提示', '操作成功!', 'info');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error: function () {
                                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                                    }
                                });
                            }
                        });

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
                        $('#infoDiv').window("open").window('refresh', 'modify/page?pid='+rows[0].pid).window('setTitle','编辑');
                    }
                }];

            $('#dg').datagrid({
                title:'旅游产品列表',
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
                    {field:'id',checkbox:true },
                    {field:'pid',title:'产品ID',width:80},
                    {field:'mtsid',title:'商户编号',width:80},
                    {field:'companyname',title:'商户名称',width:80},
                    {field:'pname',title:'产品名称',width:200},
                    {field:'pintro',title:'产品简介',width:200},
                    {field:'pmoney',title:'产品价格',width:150,formatter:formatPrice},
                    {field:'pdpicture',title:'产品图片',width:150,
                        formatter:function(value,row,index){return '<img style="height:80px;width:100px;" src="'+ value +'" />';}
                    },
                    {field:'zftype',title:'支付方式',width:200},
                    {field:'status',title:'商品状态',width:100,
                        formatter: function(value,row,index){
                            if (value == '0'){
                                return "下架";
                            } else if (value == '1') {
                                return "上架";
                            }
                        }
                    },
                    {field:'created',title:'创建时间',width:150,formatter: formatDateStr},
                    {field:'updated',title:'更新时间',width:150,formatter: formatDateStr}
                ]],
                onLoadSuccess: function () {   //隐藏表头的checkbox
                    $("#dg").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]").eq(0).attr("style", "display:none;");
                }
            });

            $('#searchBtn').linkbutton({
                onClick: function(){
                    var param = {};
                    param["pid"]=$('#pid').textbox('getValue');
                    param["mtsid"]=$('#mtsid').textbox('getValue');
                    param["pname"]=$('#pname').textbox('getValue');
                    param["status"]=$('#status_s').combobox('getValue');
                    $('#dg').datagrid('reload', param);
                }
            });

            $('#searchRestBtn').linkbutton({
                onClick: function(){
                    $('#searchForm').form('reset');
                }
            });

            $('#infoDiv').window({
                width:'800px',
                height:'500px',
                closed:true,
                modal:true
            });
        });

        $(window).resize(function() {
            var width = $(window).width() - 20;
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
                        <td>商户ID:</td>
                        <td><input id="mtsid" name="mtsid" class="easyui-textbox" type="text" /></td>
                        <td>产品ID:</td>
                        <td><input id="pid" name="pid" class="easyui-textbox" type="text" /></td>
                        <td>产品名称:</td>
                        <td><input id="pname" name="pname" class="easyui-textbox" type="text" /></td>
                    </tr>
                    <tr>
                        <td>产品状态:</td>
                        <td>
                            <select id="status_s" name="status" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="1">上架</option>
                                <option value="0">下架</option>
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
</body>
</body>
</html>