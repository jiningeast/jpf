<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>业务公司管理</title>
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
            var toolbar = [
                {
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#infoDiv").window("open").window('refresh', 'add/page').window('setTitle','新增');
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
                        $('#infoDiv').window("open").window('refresh', 'edit/page?id='+rows[0].id+"&merchNo="+rows[0].merchNo+"&type=0").window('setTitle','编辑');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'业务商户信息',
                toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'listSale',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'merchNo',title:'商户编号',width:'10%'},
                    // {field:'merchName',title:'商户名称',width:'13%'},
                    {field:'name',title:'公司名称',width:'8%'},
                    {field:'phonename',title:'联系人姓名',width:'5%'},
                    {field:'phone',title:'联系电话',width:'8%'},
                    {field:'linkemail',title:'邮箱',width:'10%'},
                    {field:'salesRate',title:'代理平台费率',width:'5%'},
                    {field:'userName',title:'录入管理员',width:'5%'},
                    {field:'attestation',title:'状态',width:'5%',
                        formatter : function(value,row,index){
                            if(value=='0'){return '未认证'}
                            else if(value=='1'){return '已认证'}
                        },styler: function (value, row, index) {
                            if ( value == '1' ) { return 'color:blue'; }
                            else if ( value == '0' ) { return 'color:red'; }
                        }
                    },
                    {field:'created',title:'添加时间',width:'10%',formatter: formatDateStr},
                    {field:'status',title:'账户状态',width:'5%',
                        formatter : function(value,row,index){
                            if(value=='1'){return '正常'}
                            else if(value=='-1'){return '禁闭'}
                        },styler: function (value, row, index) {
                            if ( value == '1' ) { return 'color:blue'; }
                            else if ( value == '-1' ) { return 'color:red'; }
                        }
                    },
                    {field:'isFreeze',title:'冻结状态',width:'5%',
                        formatter : function (value,row,index) {
                            if ( value == '1' ){ return '未冻结'; }
                            else if ( value == '2' ) { return '冻结'; }
                        },styler:function (value,row,index) {
                            if ( value == '1' ) { return 'color:blue'; }
                            else if ( value == '2' ) { return 'color:red'; }
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
                        <td>商户编号:</td>
                        <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" /></td>
                        <td>公司名称:</td>
                        <td><input id="name" name="name" class="easyui-textbox" type="text" /></td>
                        <td>添加起止时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
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