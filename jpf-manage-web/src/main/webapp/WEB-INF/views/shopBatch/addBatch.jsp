<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <title>新增打款任务</title>
</head>
<body>
<div class="contentDiv" style="padding: 10px;">
    <div id="formDiv"  class="easyui-panel" title="添加欣券" data-options="footer:'#batch_ft'" style="padding: 10px;">
        <form id="addBatchForm">
            <input type="hidden" id="total" name="total">
            <input type="hidden" id="coupons" name="coupons">
            <input type="hidden" id="companyName" name="companyName">
            <table id="addCoupons" cellpadding="5" width="100%">
                <tr>
                    <td colspan="8" style="text-align: left"><b>商户信息：</b></td>
                </tr>
                <tr>
                    <td width="70px">指定商户</td>
                    <td width="180px">
                        <input id="name" type="text" class="easyui-textbox" style="width: 100%" disabled="disabled">
                        <input id="mid" name="companyId" type="hidden">
                    </td>
                    <td align="left" colspan="6" style="text-align: left;"><a id="searchCompany" class="easyui-linkbutton" href="javascript:void(0)" data-options="iconCls:'icon-search'">选取商户</a></td>
                </tr>
                <tr>
                    <td colspan="8" style="text-align: left"><b>申请欣券：</b></td>
                </tr>
                <tr>
                    <td width="100" valign="top">面值</td>
                    <td width="190"><input id="money" name="money" type="text" class="easyui-textbox" data-options="onChange:getCalMoney"><br><span style="color: #c6c6c6">只支持整数</span></td>
                    <td width="100" valign="top">数量</td>
                    <td width="190"><input id="amount" name="amount" type="text" class="easyui-textbox" data-options="onChange:getCalMoney"><br><span style="color: #c6c6c6">只支持整数</span></td>
                    <td width="100" valign="top">小计</td>
                    <td width="190"><input id="calMoney" type="text" class="easyui-textbox" disabled="disabled"><br>&nbsp;</td>
                    <td width="100"><a id="addCoupon" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a></td>
                    <td width="190"></td>
                </tr>
            </table>
            <div id="couponDG"></div>
            <br>
            <table width="100%">
                <tr>
                    <td width="70" align="right">有效期&nbsp;</td>
                    <td width="190">
                        <select id="expireMonth" name="expireMonth" class="easyui-combobox" style="width: 90px">
                            <option value="3" selected="selected">3个月</option>
                            <option value="6">6个月</option>
                            <option value="9">9个月</option>
                            <option value="12">12个月</option>
                            <option value="24">24个月</option>
                            <option value="36">36个月</option>
                        </select>
                    </td>
                    <td width="260" colspan="2"></td>
                    <td width="70" align="right" style="color: red">总计：</td>
                    <td width="190" id="totalMoney" style="color: red">0</td>
                    <td width="260" colspan="2"></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="batch_ft" style="padding:5px;">
        <a id="confirmBatch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
    </div>
    <div id="companys"></div>
</div>
<script>
    $(function () {
        // 选取公司按钮
        $("#searchCompany").linkbutton({
            onClick:function(){
                $('#companys').window("open").window('refresh', '../shopBatch/companys').window('setTitle','选取公司');
            }
        });

        // 选取公司弹窗大小
        $('#companys').window({
            width:'1024px',
            height:'550px',
            closed:true,
            modal:true
        });

        // 新增券列表
        $("#couponDG").datagrid({
            singleSelect:true,
            multiselect:false,
            fitColumns:true,
            columns:[[
                {field:'money',title:'面值',width:"25%",align:"center"},
                {field:'amount',title:'数量',width:"25%",align:"center"},
                {field:'calculate',title:'小计',width:"25%",align:"center"},
                {field:'delete',title:'操作',width:"25%",align:"center"}
            ]]
        });

        // 点击新增
        $("#addCoupon").linkbutton({
            onClick : function () {
                var money = $("#money").textbox("getValue");
                var amount = $("#amount").textbox("getValue");
                if ( money == "" || amount == "" ){
                    return false;
                }

                var calculate = money * amount;

                $('#couponDG').datagrid('appendRow',{
                    money: money,
                    amount: amount,
                    calculate: calculate.toFixed(2),
                    delete: '<a class="delCoupon easyui-linkbutton" style="cursor: pointer;color: #0e90d2">删除</a>'
                });

                $("#money").textbox("setValue","");
                $("#amount").textbox("setValue","");
                $("#calMoney").textbox("setValue","");

                getTotalMoney();
            }
        })

        // 点击删除券
        $(".datagrid").on("click",".delCoupon",function () {
            var row = $('#couponDG').datagrid('getSelected');
            var rowIndex = $('#couponDG').datagrid('getRowIndex',row);
            $('#couponDG').datagrid('deleteRow',rowIndex);
        });

        // 新增批次form表单属性
        var data = new FormData($("#addBatchForm")[0]);
        $("#addBatchForm").form({
            type:'post',
            url:"../shopBatch/submitBatch",
            data:data,
            dataType:"json",
            contentType:"application/json",
            onSubmit:function(){
                // 判断选择公司
                if ( $("#mid").val() == "" ){
                    $.messager.alert('提示', '请选择公司', 'info');
                    return false;
                }

                // 判断券详情
                if ( $("#couponDG").datagrid("getData").total == 0 ){
                    $.messager.alert('提示', '请添加券', 'info');
                    return false;
                }

                ajaxLoading();
            },
            success:function (msg) {
                ajaxLoadEnd();
                msg = JSON.parse(msg);
                if (msg.retCode != "0000") {
                    $.messager.alert('消息提示','新增失败[' + msg.retMsg + ']!','error');
                } else {
                    $.messager.alert('消息提示','新增成功，请稍候刷新列表查看','info');
                }
                $("#batchDG").datagrid('reload');
                $("#addWindow").window("close");
                // window.location.reload();
            },
            error:function() {
                ajaxLoadEnd();
                $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
            }
        })

        // 点击确定
        $("#confirmBatch").linkbutton({
            onClick : function () {
                $("#total").val( $("#couponDG").datagrid("getData").total );
                $("#coupons").val( JSON.stringify($("#couponDG").datagrid("getData").rows) );
                $("#companyName").val( $("#name").textbox("getValue") );

                $("#addBatchForm").submit();
            }
        })

        //采用jquery easyui loading css效果
        function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }
        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }
    });

    // 计算小计
    function getCalMoney() {
        var money = parseInt( $("#money").val() );
        var amount = parseInt( $("#amount").val() );
        if ( !isNaN(money) && !isNaN(amount) ){
            var calculate = money * amount;
            $("#calMoney").textbox("setValue", calculate.toFixed(2))
        }
    }

    // 计算总计
    function getTotalMoney() {
        var totalMoney = 0;
        var arr = $("#couponDG").datagrid("getData").rows;
        $.each(arr,function (i,n) {
            $.each(this,function (i2,n2) {
                // alert(i2+"="+n2);
                if ( i2 == 'calculate' ){
                    totalMoney += parseFloat(n2);
                }
            })
        })
        $("#totalMoney").text(totalMoney.toFixed(2));
    }

</script>
</body>
