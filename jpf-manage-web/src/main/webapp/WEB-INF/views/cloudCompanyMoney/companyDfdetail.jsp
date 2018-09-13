<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/7/11
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>代付明细</title>
        </head>
<body>
<div name="contentDiv" style="padding: 15px;">
    <div id="dgDetail"></div>
</div>
<script>
    $(function(){
        var toolbar = [{
            text:'确认打款',
            iconCls:'icon-ok',
            handler:function(){
                var rows = $('#dgDetail').datagrid('getChecked'); // 取得checkbox选择行的数据，返回元素记录的数组数据。
                if (rows.length <= 0) {
                    $.messager.alert('消息提示','请选择一条数据！','info');
                    return
                }
                $.messager.confirm('Confirm','点击确定开始打款',function(r){
                    if (r){
                        //$("#infoDiv").window("open").window('refresh','../../cloudDfMoney/batchMoney?fid='+${fid}).window('setTitle','打款');
                        var param = {};
                        param["dfIds"] = "";
                        if (rows.length > 0) {
                            //循环判断操作为新增的不能选择
                            for (var i = 0; i < rows.length; i++) {
                                //根据operate让某些行不可选
                                if (rows[i].isActive == 1 && (rows[i].montype !=2 && rows[i].montype !=4 )) {
                                    param["dfIds"] += ","+rows[i].id;
                                }
                            }
                            param["dfIds"] = param["dfIds"].substring(1);
                        }
                        //param["dfIds"] = rows[0].id;
                        param["companyMoneyId"] = ${companyMoneyId};//alert(param["dfIds"]);
                        $.ajax({
                            type: 'post',
                            url: '../../cloudDfMoney/batchMoney',
                            data: param,
                            dataType: 'json',
                            success: function (msg) {
                                //alert(msg.retCode);
                                if (msg.retCode != '0000') {
                                    $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']!', 'error');
                                } else {
                                    $.messager.alert('消息提示', '操作成功!', 'info');
                                    $('#dgDetail').datagrid('reload');
                                }
                            },
                            error: function () {
                                $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                            }
                        });

                    }
                });

            }
        }];

        $("#dgDetail").datagrid({
            title : '云账户代付明细列表',
            toolbar:toolbar,
            pagination:false,
            //singleSelect:true,
            multiselect:true,
            selectOnCheck:false,
            url : '../dfDetail?companyMoneyId='+${companyMoneyId},
            columns : [[
                {field:'id', title:'ID',width:'4%',checkbox:true},
                {field:'dfid', title:'编号',width:'4%',
                    formatter:function (value, row, index) {
                        return row["id"];
                    }},
                {field:'banknickname', title:'收款人',width:'6%'},
                {field:'bankno', title:'银行卡号',width:'15%'},
                {field:'commoney', title:'打款金额(元)',width:'6%'},
                {field:'companyStaffIsActice', title:'是否签约',width:'8%',
                    formatter:function (value, row, index) {
                        if ( value == 0 ||  value == null ){
                            return '<span style="color:red">未签约</span>';
                        }else if ( value == 1 ){
                            return '已签约';
                        }
                    }
                },
                {field:'compactStaffCompactActive', title:'是否签合同',width:'8%',
                    formatter:function (value, row, index) {
                        if ( value == 0 ||  value == null  ){
                            return '<span style="color:red">未签协议合同</span>';
                        }else if ( value == 1 ){
                            return '已签协议合同';
                        }
                    }
                },
                {field:'isActive', title:'代付状态',width:'10%',
                    formatter:function (value, row, index) {
                        if ( value == 0 ||  value == null  ){
                            return '<span style="color:red">不可代付</span>';
                        }else if ( value == 1 ){
                            return '可代付';
                        }
                    }
                },//打款状态0:未申请打款 1:待打款，2=打款成功 3=打款失败，4=打款中
                {field:'montype', title:'打款状态',width:'10%',
                    formatter:function (value, row, index) {
                        if ( value == 0 ){
                            return '<span style="color:red">未申请打款</span>';
                        }else if ( value == 1 ){
                            return '待打款';
                        }else if ( value == 2 ){
                            return '打款成功';
                        }else if ( value == 3 ){
                            return '打款失败';
                        }else if ( value == 4 ){
                            return '打款中';
                        }else if ( value == 5 ){
                            return '打款限额';
                        }

                    }
                },
                {field:'addtime', title:'创建时间',width:'10%',formatter: formatDateStr},
                {field:'updatetime', title:'修改时间',width:'10%',formatter: formatDateStr}
            ]],
            onLoadSuccess: function(data){//加载完毕后获取所有的checkbox遍历
                /*if (data.rows.length > 0) {
                    //循环判断操作为新增的不能选择
                    for (var i = 0; i < data.rows.length; i++) {
                        //根据operate让某些行不可选
                        if (data.rows[i].isActive != 1) {
                            $("input[type='checkbox']")[i + 1].disabled = true;
                        }
                    }
                }*/
            },
            onClickRow: function(rowIndex, rowData){
                //加载完毕后获取所有的checkbox遍历
                /*$("input[type='checkbox']").each(function(index, el){
                    //如果当前的复选框不可选，则不让其选中
                    if (el.disabled == true) {
                        POSStockHeadTable.datagrid('unselectRow', index - 1);
                    }
                })*/
            }
        });



    })

</script>
</body>
</html>
