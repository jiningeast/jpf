<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欣豆收购管理</title>
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
            $('#edit').window({
                title:'详情',
                width:'80%',
                height:'500px',
                closed:true,
                modal:true
            });
            var toolbar = [

                {
                    text : '订单详情',
                    iconCls : 'icon-view-detail',
                    handler : function(){

                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', '../shopBargainOrder/orderInfo?orderNo='+rows[0].orderNo).window('setTitle','详情');
                    }
                },{
                    text:'审核',
                    iconCls:'icon-key-add',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#edit').window("open").window('refresh', '../shopBargainOrder/audit?orderNo='+rows[0].orderNo+'&type=2').window('setTitle','审核');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'订单信息',
                toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'listCaiwu',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'orderNo',title:'订单号',width:'7%'},
                    {field:'buyName',title:'买家昵称',width:'10%'},
                    {field:'saleName',title:'卖家昵称',width:'7%'},
                    {field:'offRate',title:'折损率',width:'5%'},
                    {field:'minDou',title:'最低限额豆数',width:'5%'},
                    {field:'dou',title:'使用豆数',width:'5%'},
                    {field:'transferPrice',title:'转让价',width:'5%'},
                    {field:'bankNo',title:'银行卡号',width:'5%'},
                    {field:'realName',title:'收款人姓名',width:'5%'},
                    {field:'idno',title:'身份证号',width:'10%'},
                    {field:'phone',title:'手机号',width:'8%'},
                    {field:'status',title:'状态',width:'5%',
                        formatter : function(value,row,index){
                            if(value=='0'){
                                return '未支付'
                            }else if(value=='1'){
                                return '已审核'
                            }else if(value=='2'){
                                return '打款中'
                            }else if(value=='3'){
                                return '打款成功'
                            }else if(value=='4'){
                                return '打款失败'
                            }else{
                                return '已取消'
                            }
                        },styler: function (value, row, index) {
                            return 'color:red';
                        }
                    },
                    {field:'addtime',title:'添加时间',width:'10%',formatter: formatDateStr},

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
        <div style="padding:10px 40px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>收款人姓名:</td>
                        <td><input id="realName" name="realName" class="easyui-textbox" type="text" /></td>
                        <td>订单号:</td>
                        <td><input id="orderNo" name="orderNo" class="easyui-textbox" type="text" /></td>
                    </tr>
                    <tr>

                        <td>下单时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                        <td>订单状态:</td>
                        <td>
                            <select id="status" name="status" class="easyui-combobox" style="width: 80px;">
                                <option value="">全部</option>
                                <option value="1">已审核</option>
                                <option value="2">打款中</option>
                                <option value="3">打款成功</option>
                                <option value="4">打款失败</option>
                                <option value="5">已取消</option>
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