<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>充值订单管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {
            var toolbar = [
                {
                    text : '申请退款',
                    iconCls : 'icon-ok',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');

                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }else{
                            $.messager.confirm('确认','是否申请退款',function(r){
                                if (r){
                                    $.ajax({
                                        type: 'post',
                                        url: "applyTuikuan" ,
                                        data: "id="+rows[0].id,
                                        dataType: 'json',
                                        success: function (msg) {
                                            if (msg.retCode != '0000') {
                                                $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                                            } else {
                                                $.messager.alert('消息提示', msg.retMsg, 'info');
                                                $('#infoDiv').window('close');
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

                    }
                }
            ];

            $('#dg').datagrid({
                title:'充值平台产品列表',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'list',
                columns:[[
                    {field:'id',title:'ID',width:"5%"},
                    {field:'orderNo',title:'订单号',width:"5%"},
                    {field:'foreignOrderNo',title:'外来订单号',width:"5%"},
                    {field:'companyId',title:'商户id',width:"5%"},
                    {field:'companyName',title:'商户名称',width:"5%"},
                    {field:'merchNo',title:'商户号',width:"5%"},
                    {field:'chargePhone',title:'充值号码',width:"5%"},
                    {field:'productId',title:'产品id',width:"5%"},
                    {field:'productName',title:'产品名称',width:"5%"},
                    {field:'productPrice',title:'产品单价',width:"5%"},
                    {field:'interfaceType',title:'接口类型',width:"5%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ) { return "欧飞"; }
                            if ( value == 1 ) { return "威能"; }
                        }},
                    {field:'interfaceOrderNo',title:'上游订单号',width:"5%"},
                    {field:'status',title:'订单状态',width:"5%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ) { return "下单成功"; }
                            if ( value == 1 ) { return "充值中"; }
                            if ( value == 2 ) { return "充值成功"; }
                            if ( value == 3 ) { return "<font color='red'>充值失败</font>"; }
                            if ( value == 4 ) { return "退款申请中"; }
                            if ( value == 5 ) { return "退款成功"; }
                            if ( value == 6 ) { return "拒绝退款"; }
                        }},
                    {field:'requestParams',title:'下游请求参数',width:"5%"},
                    {field:'notifyUrl',title:'异步回调地址',width:"5%"},
                    {field:'notifyParams',title:'异步回调参数',width:"5%"},
                    {field:'notifyTime',title:'异步回调时间',width:"8%",formatter:formatDateStr},
                    {field:'addtime',title:'添加时间',width:"8%",formatter:formatDateStr},
                    {field:'updatetime',title:'更新时间',width:"8%",formatter:formatDateStr}
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

            $('#infoDiv').window({
                width:'1024px',
                height:'550px',
                closed:true,
                modal:true
            });
            
            //导出excel
            $('#importChargeOrder').linkbutton({
                onClick: function(){
                    var queryArray = $('#searchForm').serialize();
                    var importChargeOrder = "./exportExcel?"+queryArray;
                    window.location.href = importChargeOrder;
                }
            });
        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
            <div style="padding:10px 60px 20px 60px">
                <form id="searchForm" method="post">
                    <table cellpadding="5" width="75%">
                        <tr>
                            <td>订单号:</td>
                            <td><input id="orderNo" name="orderNo" class="easyui-textbox" type="text" /></td>
                            <td>外来订单号：</td>
                            <td><input id="foreignOrderNo" name="foreignOrderNo" class="easyui-textbox" type="text" /></td>
                            <td>商户id：</td>
                            <td><input id="companyId" name="companyId" class="easyui-textbox" type="text" /></td>
                            <td>商户名称：</td>
                            <td><input id="companyName" name="companyName" class="easyui-textbox" type="text" ></td>
                        </tr>
                        <tr>
                            <td>商户号：</td>
                            <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" ></td>
                            <td>充值号码：</td>
                            <td><input id="chargePhone" name="chargePhone" class="easyui-textbox" type="text" ></td>
                            <td>产品id：</td>
                            <td><input id="productId" name="productId" class="easyui-textbox" type="text" ></td>
                            <td>产品名称：</td>
                            <td><input id="productName" name="productName" class="easyui-textbox" type="text" ></td>
                        </tr>
                        <tr>
                            <td>接口类型：</td>
                            <td>
                                <select id="interfaceType" name="interfaceType" class="easyui-combobox" style="width:120px;">
                                    <option value="">全部</option>
                                    <option value="0">欧飞</option>
                                    <option value="1">威能</option>
                                </select>
                            </td>
                            <td>订单状态：</td>
                            <td>
                                <select id="status" name="status" class="easyui-combobox" style="width:120px;">
                                    <option value="">全部</option>
                                    <option value="1">订单生成</option>
                                    <option value="2">充值成功</option>
                                    <option value="3">充值失败</option>
                                    <option value="4">退款申请中</option>
                                    <option value="5">退款成功</option>
                                    <option value="6">拒绝退款</option>
                                </select>
                            </td>
                            <td>添加时间:</td>
                            <td>
                                <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                       name="addtimeStart"
                                       onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                -
                                <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                       name="addtimeEnd"
                                       onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>上游订单号:</td>
                            <td>
                                <input id="interfaceOrderNo" name="interfaceOrderNo" class="easyui-textbox" type="text" >
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="ft" style="padding:5px;">
            <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
            <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
            <a id="importChargeOrder" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-download'">导出</a>
        </div>
        <br/>
        <div id="dg"></div>
    </div>
    <div id="infoDiv"></div>
</body>
</html>
