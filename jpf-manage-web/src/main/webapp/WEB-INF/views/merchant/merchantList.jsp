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
            var toolbar = [{
                text:'详情',
                iconCls:'icon-view-detail',
                handler:function(){
                    var rows = $('#dg').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return
                    }
                    $('#infoDiv').window("open").window('refresh', 'detail?id='+rows[0].id).window('setTitle','详情');
                }
            },{
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
            },{
                text:'代理配置',
                iconCls:'icon-key-add',
                handler:function(){
                    var rows = $('#dg').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return
                    }
                    $('#infoDiv').window("open").window('refresh', '../merchant/agent/modify/page?id='+rows[0].id).window('setTitle','编辑');
                }
            },{
                text:'支付类型配置',
                iconCls:'icon-key-add',
                handler:function(){
                    var rows = $('#dg').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return
                    }
                    $('#infoDiv').window("open").window('refresh', '../merchant/paytype/add/page?id='+rows[0].id).window('setTitle','配置支付类型');
                }
            },{
                text:'审核',
                iconCls:'icon-ok',
                handler:function(){
                    var rows = $('#dg').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return
                    }
                    if(rows[0].attestation== '1'){
                        $.messager.alert('消息提示','不可以重复认证！','info');
                        return
                    }
                    $('#infoDiv').window("open").window('refresh', 'audit/page?id='+rows[0].id).window('setTitle','审核');
                }
            }];

            $('#dg').datagrid({
                title:'旅游商户列表',
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
                    {field:'id',title:'商户Id',width:50},
                    {field:'merchNo',title:'聚合商户号',width:150},
                    {field:'merchName',title:'聚合商户名称',width:150},
                    {field:'username',title:'用户名',width:150},
                    {field:'companyname',title:'企业名称',width:150},
                    // {field:'province',title:'省份',width:150},
                    // {field:'city',title:'城市',width:150},
                    {field:'linkname',title:'联系人',width:150},
                    {field:'linkphone',title:'联系电话',width:100},
                    {field:'salerphone',title:'销售电话',width:100},
                    {field:'status',title:'状态',width:70,
                        formatter: function(value,row,index){
                            if (value == '0'){
                                return "正常";
                            } else if (value == '1') {
                                return "禁用";
                            }
                        }
                    },
                    // {field:'bslicense',title:'营业执照',width:150},
                    // {field:'aptitude',title:'企业资质',width:150},
                    // {field:'logo',title:'企业logo',width:150},
                    {field:'attestation',title:'企业认证',width:70,hidden:true},
                    {field:'attestationDesc',title:'企业认证',width:70,
                        formatter: function(value,row,index){
                            if (row.attestation == '0'){
                                return "未认证";
                            } else if (row.attestation == '1') {
                                return "已认证";
                            }
                        }
                    },
                    {field:'content',title:'审核备注',width:150},
                    {field:'registerip',title:'注册IP',width:100},
                    {field:'lastloginip',title:'最后登录IP',width:100},
                    {field:'addtime',title:'创建时间',width:150,formatter: formatDateStr}
                ]]
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
    <style>
        #searchForm table tr td:nth-child(odd) { text-align: right; }
        #searchForm table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="100%">
                    <tr>
                        <td>聚合商户号:</td>
                        <td><input id="merchNo_s" name="merchNo" class="easyui-textbox" type="text" /></td>
                        <td>聚合商户名称:</td>
                        <td><input id="merchName_s" name="merchName" class="easyui-textbox" type="text" /></td>
                        <td>用户名:</td>
                        <td><input id="username_s" name="username" class="easyui-textbox" type="text" /></td>
                        <td>企业名称:</td>
                        <td><input id="companyname_s" name="companyname" class="easyui-textbox" type="text" /></td>
                        <td>创建时间：</td>
                        <td colspan="3">
                            <input type="text" class="Wdate" style="width:158px;" id="startAddTime_s"
                                   name="startAddTime"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endAddTime_s\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:158px;" id="endAddTime_s"
                                   name="endAddTime"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startAddTime_s\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <td>状态:</td>
                        <td>
                            <select id="status_s" name="status" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">正常</option>
                                <option value="1">冻结金额</option>
                            </select>
                        </td>
                        <td>企业认证:</td>
                        <td>
                            <select id="attestation_s" name="attestation" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">未认证</option>
                                <option value="1">已认证</option>
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