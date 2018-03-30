<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        var id = 0;

        $(function() {

            var toolbar = [{
                text:'新增',
                iconCls:'icon-add',
                handler:function(){
                    $('#addForm').form('reset');
                    $('#addWin').window('open');
                }
            },{
                text:'密码重置',
                iconCls:'icon-key-add',
                handler:function(){
                    var rows = $('#dg').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return
                    }
                    $.messager.confirm('重置密码','确定要重置密码？',function(r){
                        if(r){
                            var param = {};
                            param["userName"]=rows[0].userName;
                            $.ajax({
                                type:'post',
                                url:'resetPwd',
                                data:param,
                                dataType:'json',
                                success:function(msg){
                                    if (msg.retCode != '0000') {
                                        $.messager.alert('消息提示','重置密码失败[' + msg.retMsg + ']!','error');
                                    } else {
                                        $('#addWin').window('close');
                                        $('#dg').datagrid('reload');
                                        $.messager.alert('消息提示','密码成功重置!','info');
                                    }
                                },
                                error:function(){
                                    $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                                }
                            });
                        }
                    });
                }
            },{
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
            },{
                text:'启用',
                iconCls:'icon-no',
                handler:function(){
                    var rows = $('#dg').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return
                    }
                    $.messager.confirm('启用', '确定启用用户：' + rows[0].userName, function (r) {
                        if (r) {
                            var param = {};
                            param["userName"] = rows[0].userName;
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
            }];

            $('#dg').datagrid({
                title:'用户列表',
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
                    {field:'id',hidden:true},
                    {field:'userName',title:'用户名',width:150},
                    {field:'status',title:'状态',width:100,
                        formatter: function(value,row,index){
                            if (value == '0'){
                                return "正常";
                            } else if (value == '1') {
                                return "禁用";
                            }
                        }
                    },
                    {field:'createTime',title:'创建时间',width:150,formatter: formatDateStr}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');

            $('#searchBtn').linkbutton({
                onClick: function(){
                    var param = {};
                    param["userName"]=$('#userName_s').textbox('getValue');
                    param["status"]=$('#status_s').combobox('getValue');
                    $('#dg').datagrid('reload', param);
                }
            });

            $('#searchRestBtn').linkbutton({
                onClick: function(){
                    $('#searchForm').form('reset');
                }
            });

            $('#addSubmitBtn').linkbutton({
                onClick: function(){
                    var isValid = $("#addForm").form('enableValidation').form('validate');
                    if (isValid) {
                        var param = {};
                        param["userName"]=$('#userName_a').textbox('getValue');
                        param["userPwd"]=$('#confirmUserPwd_a').textbox('getValue');
                        $.ajax({
                            type:'post',
                            url:'add',
                            data:param,
                            dataType:'json',
                            success:function(msg){
                                alert(msg.retCode);
                                if (msg.retCode != '0000') {
                                    $.messager.alert('消息提示','添加失败[' + msg.retMsg + ']!','error');
                                } else {
                                    $('#addWin').window('close')
                                    $('#dg').datagrid('reload');
                                    $.messager.alert('消息提示','添加成功!','error');
                                }
                            },
                            error:function(){
                                $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                            }
                        });
                    }
                }
            });

            $('#addCancelBtn').linkbutton({
                onClick: function(){
                    $('#addWin').window('close')
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
<input id="id" name="id" type="hidden"></input>
<input id="merchantId" name="merchantId" type="hidden"></input>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td>用户名:</td>
                        <td><input id="userName_s" name="userName" class="easyui-textbox" type="text" /></td>
                    </tr>
                    <tr>
                        <td>状态:</td>
                        <td>
                            <select id="status_s" name="status" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">正常</option>
                                <option value="1">禁用</option>
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

<!-- 添加弹出窗口 -->
<div id="addWin" class="easyui-window" title="新增用户"
     data-options="iconCls:'icon-save',closed:true,modal:true,minimizable:false"
     style="width: 600px; height: 230px; padding: 10px;">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px; background: #fff; ">
            <form id="addForm" method="post" enctype="multipart/form-data">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <td style="text-align: right;width:30%" bgcolor="#f1f1f1">用户名：</td>
                        <td>
                            <input id="userName_a" name="userName" data-options="required:true"
                                   missingMessage="请输入用户名" type="text" width="120" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr id="userPwd_a_tr">
                        <td style="text-align: right;width:30%" bgcolor="#f1f1f1">登录密码：</td>
                        <td>
                            <input id="userPwd_a" type="password" data-options="required:true"
                                   missingMessage="请输入登录密码" width="120" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr id="confirmUserPwd_a_tr">
                        <td style="text-align: right;width:30%" bgcolor="#f1f1f1">确认密码：</td>
                        <td>
                            <input id="confirmUserPwd_a" name="userPwd" type="password"
                                   required="required" validType="equals['#userPwd']" width="120" class="easyui-textbox"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" border="false"
             style="text-align: right; height: 30px; line-height: 30px;">
            <a id="addSubmitBtn" class="easyui-linkbutton" icon="icon-ok"
               href="javascript:void(0)">确定</a>
            <a id="addCancelBtn" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>
    </div>
</div>
<!-- /添加弹出窗口 -->
</body>
</html>