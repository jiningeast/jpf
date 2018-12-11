<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商品采购管理</title>
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
                    text:'审核',
                    iconCls:'icon-key-add',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'audit/page?id='+rows[0].id).window('setTitle','编辑');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'商品采购管理',
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
                    {field:'companyName',title:'公司名称',width:'12%'},
                    {field:'contractNo',title:'合同号',width:'8%'},
                    {field:'duetime',title:'合同到期时间',width:'8%',formatter: formatDateStr},
                    {field:'couponMoney',title:'欣券金额',width:'8%',formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'serviceMoney',title:'服务金额',width:'8%',formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'contractMoney',title:'合同金额',width:'8%',formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'rate',title:'费率 (%)',width:'5%',  formatter: function (value, row, index) {
                            if (row != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'money',title:'实际到帐金额',width:'8%',formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'balance',title:'合同剩余金额',width:'8%',formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'transferRate',title:'服务转让率 (%)',width:'5%',  formatter: function (value, row, index) {
                            if (value != null) {
                                return parseFloat(value).toFixed(2);
                            }
                        }},
                    {field:'operatorName',title:'添加人',width:'8%'},
                    {field:'checkOperatorName',title:'审核人',width:'8%'},
                    {field:'addtime',title:'添加时间',width:'8%',formatter: formatDateStr},
                    {field:'updatetime',title:'更新时间',width:'8%',formatter: formatDateStr},
                    {field:'status',title:'状态',width:'6%',
                        formatter : function(value,row,index){
                            if(value=='-1'){return '已取消'}
                            else if(value=='0'){return '申请'}
                            else if(value=="1"){return '审核通过已充值'}
                            else if(value=="2"){return '审核驳回'}
                        },styler: function (value, row, index) {
                           if(value=="2"){
                               return 'color:red';

                           }
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
                        <td>采购时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                        <td>关键字:</td>
                        <td><input id="companyKeywords" name="companyName" class="easyui-textbox" type="text" /></td>
                    </tr>
                    <tr>
                        <td>状态:</td>
                        <td><select editable="false" id="status" name="status" class="easyui-combobox">
                            <option value="">全部</option>
                            <option value="-1">已取消</option>
                            <option value="0">申请</option>
                            <option value="1">审核并充值</option>
                            <option value="2">审批驳回</option>
                        </select>
                        </td>
                        <td></td>
                        <td></td>
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