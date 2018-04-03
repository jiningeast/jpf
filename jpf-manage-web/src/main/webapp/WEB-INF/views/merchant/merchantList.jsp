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
                iconCls:'icon-add',
                handler:function(){
                    $('#addForm').form('reset');
                    $('#addWin').window('open');
                }
            },{
                text:'添加',
                iconCls:'icon-add',
                handler:function(){
                    // $('#addForm').form('reset');
                    // $('#addWin').window('open');
                    $('#infoDiv').window("open").window('refresh', 'modify/page');
                }
            },{
                text:'修改',
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
            }];

            $('#dg').datagrid({
                title:'商户信息列表',
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
                    // {field:'linkname',title:'联系人',width:150},
                    // {field:'linkphone',title:'联系电话',width:150},
                    // {field:'registerip',title:'注册IP',width:150},
                    // {field:'lastloginip',title:'最后登录IP',width:150},
                    {field:'status',title:'状态',width:100,
                        formatter: function(value,row,index){
                            console.info("status="+value);
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
                    {field:'attestation',title:'企业认证',width:100,
                        formatter: function(value,row,index){
                            console.info("attestation=" + value);
                            if (value == '0'){
                                return "未认证";
                            } else if (value == '1') {
                                return "已认证";
                            }
                        }
                    },
                    {field:'addtime',title:'创建时间',width:150,formatter: formatDateStr}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');

            $('#searchBtn').linkbutton({
                onClick: function(){
                    // var param = {};
                    // param["username"]=$('#username_s').textbox('getValue');
                    // param["status"]=$('#status_s').combobox('getValue');
                    // console.info("param="+param);
                    // $('#dg').datagrid('reload', param);

                    var queryArray = $('#searchForm').serializeArray();
                    var postData = parsePostData(queryArray);
                    console.info("postData="+postData);
                    $('#dg').datagrid('reload', postData);
                }
            });

            $('#searchRestBtn').linkbutton({
                onClick: function(){
                    $('#searchForm').form('reset');
                }
            });

            $('#infoDiv').window({
                title:'详情',
                width:'1024px',
                height:'512px',
                closed:true,
                modal:true
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
                        <td>聚合商户号:</td>
                        <td><input id="merchNo_s" name="merchNo" class="easyui-textbox" type="text" /></td>
                        <td>聚合商户名称:</td>
                        <td><input id="merchName_s" name="merchName" class="easyui-textbox" type="text" /></td>
                        <td>用户名:</td>
                        <td><input id="username_s" name="username" class="easyui-textbox" type="text" /></td>
                        <td>企业名称:</td>
                        <td><input id="companyname_s" name="companyname" class="easyui-textbox" type="text" /></td>
                        <td>营业执照:</td>
                        <td><input id="bslicense_s" name="bslicense" class="easyui-textbox" type="text" /></td>
                        <td>注册时间：</td>
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