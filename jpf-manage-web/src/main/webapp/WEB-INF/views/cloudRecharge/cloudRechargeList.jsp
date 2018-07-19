<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>银行管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function(){

            var toolbar = [
                {
                    text : '审核',
                    iconCls : 'icon-key-add',
                    handler : function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'audit/page?id='+rows[0].id).window('setTitle','审核');
                    }
                },
            ];

            $('#infoDiv').window({
                width:'1024px',
                height:'550px',
                closed:true,
                modal:true
            });

            $("#dg").datagrid({
                title : '云账户充值列表',
                toolbar:toolbar,
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                url : 'list',
                columns : [[
                    {field:'id', title:'ID',width:'4%'},
                    {field:'fid', title:'充值单号',width:'10%'},
                    {field:'agent_no', title:'代理聚合商户编号',width:'10%'},
                    {field:'merch_no', title:'到账聚合商户编号',width:'10%'},
                    {field:'payway', title:'支付方式',width:'5%'},
                    {field:'pactno', title:'合同编号',width:'5%'},
                    {field:'money', title:'充值金额',width:'5%'},
                    {field:'realmoney', title:'实际到帐金额',width:'5%'},
                    {field:'feemoney', title:'手续费总额',width:'5%'},
                    {field:'agent_feemoney', title:'代理手续费',width:'5%'},
                    {field:'sales_feemoney', title:'服务平台手续费',width:'5%'},
                    {field:'status', title:'状态',width:'8%',
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return '已取消';
                            }else if ( value == 1 ){
                                return '申请中';
                            }else if ( value == 2 ){
                                return '<span style="color:blue">已审核(待上传付款凭证)</span>';
                            }else if ( value == 3 ){
                                return '<span style="color:red">已支付(已上传凭证)</span>';
                            }else if ( value == 4 ){
                                return '<span style="color:green">已充值开票中</span>';
                            }else if ( value == 5 ){
                                return '<span style="color:red">已充值已开票</span>';
                            }else if ( value == 6 ){
                                return '<span style="color:red">已发货</span>';
                            }else if ( value == 7 ){
                                return '<span style="color:red">已完成</span>';
                            }
                        }
                    },
                    {field:'addtime', title:'创建时间',width:'10%',formatter: formatDateStr},
                    {field:'updatetime', title:'修改时间',width:'10%',formatter: formatDateStr}
                ]]
            });
            $('#dg').datagrid().datagrid('getPager');

            $('#edit').window({
                width:'400px',
                height:'200px',
                closed:true,
                modal:true
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
        })

        $(window).resize(function() {
            var width = $(window).width() - 20;
            $("div[name='contentDiv']").css("width", width);

            // 加上这个，form面板和grid面板右侧不会出现残缺
            $("#formDiv").panel().width=1;
            $('#dg').datagrid().width=1;
        });

    </script>
    <style>
        #searchForm td:nth-child(odd) { text-align: right; }
        #searchForm td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>充值单号:</td>
                        <td><input id="fid" name="fid" class="easyui-textbox" type="text" /></td>
                        <td>代理聚合商户编号:</td>
                        <td><input id="agent_no" name="agent_no" class="easyui-textbox" type="text" /></td>
                        <td>手机号:</td>
                        <td><input id="linkphone" name="linkphone" class="easyui-textbox" type="text" /></td>
                    </tr>
                    <tr>
                        <td>充值状态:</td>
                        <td>
                            <select id="status_s" name="status" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">已取消</option>
                                <option value="1">申请中</option>
                                <option value="2">已审核</option>
                                <option value="3">已支付</option>
                                <option value="4">已充值开票中</option>
                                <option value="5">已充值已开票</option>
                                <option value=6">已发货</option>
                                <option value=7">已完成</option>
                            </select>
                        </td>
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
<table id="infoDiv"></table>

<script>

    function initData() {

        //$('#status_s').combobox('select', '2');

    }


    $(function () {

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);


    });

</script>
</body>
</html>
