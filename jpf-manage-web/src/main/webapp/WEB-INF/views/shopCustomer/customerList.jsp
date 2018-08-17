<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>客户列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            $('#infoDiv').window({
                title:'详情',
                width:'1024px',
                height:'500px',
                closed:true,
                modal:true,
                maximized:true,//弹出窗口最大化

            });
            var toolbar = [
                {
                    text:'详情',
                    iconCls:'icon-edit',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'info?id='+rows[0].id+'&phone='+rows[0].phone+'&dou='+rows[0].dou).window('setTitle','欣豆详情');
                    }
                },
                {
                    text : '冻结',
                    iconCls:'icon-no',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $.messager.confirm('冻结','确认冻结操作？',function(r){
                            if (r){
                                $.ajax({
                                    type : 'get',
                                    url :'delCompanyCustomer?id='+rows[0].id+'&type=2',
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
                    text : '恢复',
                    iconCls:'icon-ok',
                    handler : function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        $.messager.confirm('恢复','确认恢复正常操作？',function(r){
                            if (r){
                                $.ajax({
                                    type : 'get',
                                    url :'delCompanyCustomer?id='+rows[0].id+'&type=1',
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
            ];

            $('#dg').datagrid({
                title:'客户信息',
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
                    {field:'phone',title:'注册号',width:'10%',
                        formatter : function(value,row,index){

                            return  "<a onclick='goActive("+row['id']+","+row['phone']+","+row['dou']+")'>"+value+" </a>";
                        }
                    },
                    // {field:'idno',title:'身份证号码',width:'15%'},
                    {field:'nickname',title:'微信昵称',width:'8%',
                        formatter : function(value,row,index){

                            return  decodeURI(value);
                        }
                    },
                    {field:'dou',title:'欣豆数量',width:'10%'},
                    // {field:'companyId',title:'所属公司id',width:'10%'},
                    // {field:'companyName',title:'注册所属公司',width:'15%'},
                    {field:'status',title:'登录状态',width:'8%',
                        formatter : function(value,row,index){
                            if(value=='1'){return '正常'}
                            else{return '已冻结'}
                        },styler: function (value, row, index) {
                            return 'color:red';
                        }
                    },{field:'isVerify',title:'实名认证',width:'8%',
                        formatter : function(value,row,index){
                            if(value=='1'){return '已实名认证'}
                            else{return '未实名认证'}
                        },styler: function (value, row, index) {
                            return 'color:red';
                        }
                    },
                    {field:'addtime',title:'注册时间',width:'15%',formatter: formatDateStr},

                    {field:'updatetime',title:'修改时间',width:'15%',formatter: formatDateStr},

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
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>注册号:</td>
                        <td><input id="phone" name="phone" class="easyui-textbox" type="text" /></td>

                        <td>微信昵称:</td>
                        <td><input id="nickname" name="nickname" class="easyui-textbox" type="text" /></td>
                        </tr>
                    <tr>
                        <td>所属公司名称:</td>
                        <td><input id="companyName" name="companyName" class="easyui-textbox" type="text" /></td>
                        <td>注册起止时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <td>登录状态:</td>
                        <td>
                            <select id="status" name="status" class="easyui-combobox" style="width: 60px;">
                                <option value="">全部</option>
                                <option value="0">冻结</option>
                                <option value="1">正常</option>
                            </select>
                        </td>
                        <td>实名认证状态:</td>
                        <td>
                            <select id="isVerify" name="isVerify" class="easyui-combobox">
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
<div id="edit"></div>
</body>
</html>