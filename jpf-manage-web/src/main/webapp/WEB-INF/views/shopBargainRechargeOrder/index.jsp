<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>敬恒充值订单管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {
            $('#dg').datagrid({
                title:'订单信息',
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'list',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'boid',title:'转让订单id',width:'5%'},
                    {field:'orderNo',title:'转让订单号',width:'10%'},
                    {field:'orderType',title:'订单类型',width:'5%',
                        formatter:function (value,row,index) {
                            if ( value == 1 ) { return "中石化充值"; }
                            if ( value == 2 ) { return "中石油充值"; }
                            if ( value == 3 ) { return "话费充值"; }
                        }},
                    {field:'foreignOrderNo',title:'敬恒订单号',width:'5%'},
                    {field:'itemName',title:'商品名称',width:'10%'},
                    {field:'price',title:'单价',width:'5%'},
                    {field:'facePrice',title:'面值',width:'5%'},
                    {field:'amt',title:'数量',width:'5%'},
                    {field:'amount',title:'订单总金额',width:'5%'},
                    {field:'chargeNo',title:'充值号',width:'10%'},
                    {field:'addtime',title:'拉取时间',width:'8%',formatter:formatDateStr},
                    {field:'infoStatus',title:'绑定状态',width:'5%',
                        formatter:function (value,row,index) {
                            if ( value == 1 ) { return "未绑定"; }
                            if ( value == 2 ) { return "已绑定"; }
                        }
                    },
                    {field:'module',title:'敬恒返回信息',width:'19%',}
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
            $('#importExcelShopBargainRecharge').linkbutton({
                onClick: function(){
                    var queryArray = $('#searchForm').serialize();
                    var importExcelShopBargainRecharge = "./exportExcel?"+queryArray;
                    window.location.href = importExcelShopBargainRecharge;
                }
            });
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
            <div style="padding:10px 40px 20px 60px">
                <form id="searchForm" method="post">
                    <table cellpadding="5" width="75%">
                        <tr>
                            <td>转让订单号:</td>
                            <td><input id="orderNo" name="orderNo" class="easyui-textbox" type="text" /></td>
                            <td>订单类型:</td>
                            <td>
                                <select id="orderType" name="orderType" class="easyui-combobox" style="width: 80px;">
                                    <option value="">全部</option>
                                    <option value="1">中石化充值</option>
                                    <option value="2">中石油充值</option>
                                    <option value="3">话费充值</option>
                                </select>
                            </td>
                            <td>敬恒订单号:</td>
                            <td>
                                <input id="foreignOrderNo" name="foreignOrderNo" class="easyui-textbox" type="text" >
                            </td>
                            <td>商品名称:</td>
                            <td>
                                <input id="itemName" name="itemName" class="easyui-textbox" type="text" >
                            </td>
                        </tr>
                        <tr>
                            <td>面额:</td>
                            <td><input id="facePrice" name="facePrice" class="easyui-textbox" type="text"></td>
                            <td>拉取时间:</td>
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
                                <select id="infoStatus" name="infoStatus" class="easyui-combobox" style="width: 80px;">
                                    <option value="">全部</option>
                                    <option value="1">未绑定</option>
                                    <option value="2">已绑定</option>
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
            <a id="importExcelShopBargainRecharge" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-download'">导出</a>
        </div>
        <br/>
        <table id="dg"></table>
    </div>
</body>