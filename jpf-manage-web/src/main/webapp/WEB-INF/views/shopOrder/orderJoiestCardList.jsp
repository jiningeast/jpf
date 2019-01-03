<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欣豆市场中欣卡订单管理</title>
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
                title:'审核',
                width:'80%',
                height:'500px',
                closed:true,
                modal:true
            });
            var toolbar = [{
                    text:'详情',
                    iconCls:'icon-view-detail',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }else{
                            if(rows[0].orderNo == null || rows[0].orderNo == ""){
                                $.messager.alert('消息提示','订单数据异常','info');
                                return
                            }else{
                                $('#infoDiv').window("open").window('refresh', 'joiestOrderInfo?orderNo='+rows[0].orderNo).window('setTitle','订单详情');
                            }
                        }
                    }
                },{
                    text:'审核',
                    iconCls:'icon-user',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#edit').window("open").window('refresh', 'joiestOrderInfo/audit?orderNo='+rows[0].orderNo).window('setTitle','审核');
                    }
                }];

            $('#dg').datagrid({
                title:'欣豆市场中欣卡订单列表',
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
                    {field:'orderNo',title:'订单号',width:'12%',
                     /*   formatter : function(value,row,index){
                            return  "<a onclick=\"goActiveOrder('"+value+" ')\">"+value+" </a>";
                        }*/
                    },
                    {field:'productName',title:'产品名称',width:'10%'},
                    {field:'typeName',title:'产品类型',width:'7%'},
                    {field:'amount',title:'数量',width:'5%'},
                    {field:'totalMoney',title:'总金额',width:'7%'},
                    {field:'totalDou',title:'总欣豆',width:'7%'},
                    {field:'customerName',title:'微信昵称',width:'8%',
                        formatter : function(value,row,index){
                            return  decodeURI(value);
                        }
                    },
                    {field:'addtime',title:'下单时间',width:'12%',formatter: formatDateStr},
                    {field:'paytime',title:'支付时间',width:'12%',formatter: formatDateStr},
                    {field:'status',title:'状态',width:'6%',
                        formatter : function(value,row,index){
                            if(value=='0'){return '待支付'}
                            else if(value=="1"){return '已支付'}
                            else if(value=="2"){return '支付失败'}
                            else if(value=="3"){return '已取消'}
                            else if(value=="4"){return '充值成功'}
                            else if(value=="5"){return '充值失败'}
                        },styler: function (value, row, index) {
                            if(value == "0"){
                                return 'color:orange';
                            }else if(value == "1"){
                                return 'color:green';
                            }else if(value == "2"){
                                return 'color:red';
                            }else if(value == "3"){
                                return 'color:black';
                            }else if(value == "4"){
                                return 'color:green';
                            }else if(value == "5"){
                                return 'color:red';
                            }
                        }
                    },
                    {field:'companyName',title:'企业名称',width:'5%'},
                    {field:'contractNo',title:'合同编号',width:'6%'}
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

            //导出excel
            $('#importExcelXindouMarket').linkbutton({
                onClick: function(){
                    ajaxLoading();
                    $.ajax({
                        url: "./exportExcel?" + $('#searchForm').serialize(),
                        type:"GET",
                        success:function () {
                            window.location.href = "./exportExcel?" + $('#searchForm').serialize();
                            ajaxLoadEnd();
                        }
                    });
                }
            });
        });

        function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在导出...").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }

        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }

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
        function goActiveOrder(orderNo) {
            $('#infoDiv').window("open").window('refresh', 'orderInfo?orderNo='+orderNo).window('setTitle','订单详情');
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
                        <td>订单号:</td>
                        <td>
                            <input id="orderNo" name="orderNo" class="easyui-textbox" type="text" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
        <a id="importExcelXindouMarket" href="#" class="easyui-linkbutton" disabled="disabled" data-options="iconCls:'icon-download'">导出</a>
    </div>
    <br/>
    <table id="dg"></table>
</div>
<div id="infoDiv"></div>
<div id="edit"></div>
</body>
</html>