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

            $.ajax({

            });
            var toolbar = [
                {
                    text:'禁用',
                    iconCls:'icon-no',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $.messager.confirm('禁用', '确定禁用用户：' + rows[0].userName, function (r) {
                            if (r) {
                                var param = {};
                                param["userName"] = rows[0].userName;
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
                                            $.messager.alert('消息提示', '操作成功!', 'error');
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
                        $('#infoDiv').window("open").window('refresh', 'modify/page?id='+rows[0].id).window('setTitle','编辑');
                    }
                },
                {
                    text:'审核',
                    iconCls:'icon-key-add',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'audit/page?id='+rows[0].id).window('setTitle','审核');
                    }
                }];

            $('#dg').datagrid({
                title:'产品信息列表',
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
                    {field:'pid',title:'商品Id',width:50},
                    {field:'mtsid',title:'商户ID',width:150},
                    {field:'pname',title:'产品名称',width:150},
                    {field:'pintro',title:'产品简介',width:150},
                    {field:'pmoney',title:'产品价格',width:150},
                    {field:'pdpicture',title:'产品图片',width:150,
                        formatter:function(value,row,index){return '<img style="height:80px;width:100px;" src="'+ value +'" />';}
                    },
                    {field:'zftype',title:'支付方式',width:150},
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
                ]]
            });

            $('#searchBtn').linkbutton({
                onClick: function(){
                    var param = {};
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
                width:'1024px',
                height:'550px',
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
</head>
<body>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td>商户ID:</td>
                        <td><input id="mtsid" name="mtsid" class="easyui-textbox" type="text" /></td>
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