<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商户支付配置管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            $('#infoDiv').window({
                title:'详情',
                width:'1024px',
                height:'512px',
                closed:true,
                modal:true
            });

            var toolbar = [
                /*{
                    text:'删除',
                    iconCls:'icon-remove',
                    handler:function(){
                        $.messager.confirm('删除','确认删除操作？',function(r){
                            if(r){
                                var rows = $('#dg').datagrid('getSelections');
                                var param = [];
                                for(var i = 0 ;i<rows.length ;i++){
                                    param.push(rows[i].id);
                                }
                                $.ajax({
                                    type:'get',
                                    url:'delete/action',
                                    data:{"id":param},
                                    dataType:"json",
                                    contentType:"application/json",
                                    // data:JSON.stringify(param),
                                    success:function(msg){
                                        if (msg.retCode != '0000') {
                                            $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                        } else {
                                            $.messager.alert('消息提示','操作成功!','info');
                                            $('#infoDiv').window('close');
                                            $('#dg').datagrid('reload');
                                        }
                                    },
                                    error:function(){
                                        $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                    }
                                });
                            }
                        });
                    }
                },*/
                {
                    text:'配置分期类型',
                    iconCls:'icon-add',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'stage/page?id='+rows[0].id + "&mtsid=" + rows[0].mtsid).window('setTitle','配置支付类型');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'商户支付配置',
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
                    {field:'id',title:"ID", width:50},
                    {field:'mtsid',title:'商户Id',width:50},
                    {field:'catpath',title:'支付类型',width:150},
                    {field:'bankcatid',title:'分期类型',width:80},
                    {field:'created',title:'创建时间',width:150,formatter: formatDateStr},
                    {field:'updated',title:'变更时间',width:150,formatter: formatDateStr}
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
</head>
<body>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td>商户Id:</td>
                        <td><input id="mtsid" name="mtsid" class="easyui-textbox" type="text" /></td>
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
</html>