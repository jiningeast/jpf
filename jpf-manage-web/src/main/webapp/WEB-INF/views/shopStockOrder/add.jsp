<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <title>商品采购申请</title>
</head>
<body>
<div class="contentDiv" style="padding: 10px;">
    <div id="formDiv"  class="easyui-panel" title="采购申请" data-options="footer:'#batch_ft'" style="padding: 10px;">
        <form id="addBatchFormP">
            <input type="hidden" id="productsAll" name="productsAll">
            <input type="hidden" id="countProducts" name="countProducts">
            <input type="hidden" id="countMoneys" name="countMoneys">
            <table id="addCoupons" cellpadding="5"  >
                <tr>
                    <td style="text-align: right; width: 100px">付款方式：</td>
                    <td >
                        <select id="typeId" name="typeId" data-options="required:true" class="easyui-combobox" style="width:100px" ></select>
                        <input type="hidden" name="typeName" id="typeName" value="" />
                        <input type="hidden" name="typeNum" id="typeNum" value="" />
                    </td>

                    <td style="text-align: right; width: 100px">付款周期：</td>
                    <td>
                        <select id="supplierId" name="supplierId" data-options="required:true" class="easyui-combobox" style="width:100px" ></select>
                        <input type="hidden" name="supplierName" id="supplierName" value="" />
                        <input type="hidden" name="supplierNum" id="supplierNum" value="" />
                    </td>
                </tr>

                <tr >
                    <td style="text-align: right;width: 100px">备注：</td>
                    <td >
                        <textarea id="memo" name="memo" style="width:300px"></textarea>
                    </td>
                </tr>

                <tr>

                    <td style="text-align: right;width: 100px">选定商品列表</td>
                    <td align="right" colspan="6" style="text-align: left;"><a id="searchProducts" class="easyui-linkbutton" href="javascript:void(0)" data-options="iconCls:'icon-search'">添加商品</a></td>
                </tr>

            </table>
            <div id="couponDG"></div>
            <br>
            <table width="100%">
                <tr>

                    <td width="200" align="right" style="color: red">采购数量总计：</td>
                    <td id="totalCount" style="color: red">0</td>
                    <td width="200" align="right" style="color: red">采购金额总计：</td>
                    <td  id="totalMoney" style="color: red">0</td>
                </tr>
            </table>

        </form>
    </div>
    <div id="batch_ft" style="padding:5px;">
        <a id="confirmBatch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存采购申请</a>
    </div>
    <div id="products"></div>
</div>
<script>
    $(function () {
        //付款方式以及付款周期
        $("#typeId").combobox({
            url:'../param/getType?pid=99',
            valueField:'catid',
            textField:'cat',
            onSelect: function () {
                var typeName = $("#typeId").combobox("getText");
                var typeNum = $("#typeId").combobox("getValue");
                $("#typeName").val(typeName)
                $("#typeNum").val(typeNum)
            }
        });

        $("#supplierId").combobox({
            url:'../param/getType?pid=105',
            valueField:'catid',
            textField:'cat',
            onSelect: function () {
                var supplierName = $("#supplierId").combobox("getText");
                var supplierNum = $("#supplierId").combobox("getValue");
                $("#supplierName").val(supplierName)
                $("#supplierNum").val(supplierNum)
            }
        });
        //===========
        // 选取公司按钮
        $("#searchProducts").linkbutton({
            onClick:function(){
                $('#products').window("open").window('refresh', '../shopStockOrder/products').window('setTitle','选取商品');
            }
        });

        // 选取公司弹窗大小
        $('#products').window({
            width:'1024px',
            height:'550px',
            closed:true,
            modal:true
        });

        // 定义新增商品列表
        $("#couponDG").datagrid({
            singleSelect:true,
            multiselect:false,
            fitColumns:true,
            columns:[[
                {field:'id',title:'商品ID',width:"6%",align:"center"},
                {field:'Pname',title:'商品名称',width:"10%",align:"center"},
                {field:'bidOld',title:'产品进价',width:"10%",align:"center",hidden:'true'},
                {field:'moneyOld',title:'产品售价',width:"10%",align:"center",hidden:'true'},
                {field:'stored',title:'产品库存',width:"10%",align:"center",hidden:'true'},
                {field:'supplierName',title:'供应商',width:"10%",align:"center"},
                {field:'supplierId',title:'供应商ID',width:"10%",align:"center",hidden:'true'},
                {field:'brandName',title:'品牌',width:"10%",align:"center"},
                {field:'brandId',title:'品牌ID',width:"10%",align:"center",hidden:'true'},
                {field:'productBid',title:'标准进价/件',width:"10%",align:"center"},
                {field:'bid',title:'本次进价/件(必填)',width:"18%",align:"center"},
                {field:'amount',title:'采购数量(必填)',width:"18%",align:"center"},
                {field:'money',title:'采购金额(元)',width:"15%",align:"center"},
                {field:'delete',title:'操作',width:"10%",align:"center"}
            ]]
        });


        //input失焦事件改变数值
        $(".datagrid").on("blur","input[id^='bid']",function () {


            var bid = $(this).val();//进价单件
            var id = $(this).data('id');
            var amount = $("#amount"+id).val();//数量
            var calculate = bid * amount;
            var reg=/^[1-9]{1}\d*(\.\d{1,2})?$/;
            if(reg.test(bid)==false){
                $.messager.alert('消息提示','请输入正确的金额','error');
                return false;
            }else{
                if(amount!=""){
                    $("#money"+id).val(calculate.toFixed(2));
                    //统计总额总数量
                    getTotalMoney();
                    return false;
                }

            }

        });

        $(".datagrid").on("blur","input[id^='amount']",function () {

            var amount = $(this).val();//进价单件
            var id = $(this).data('id');
            var bid = $("#bid"+id).val();//数量
            var calculate = bid * amount;
            var reg = /^[0-9]*$/;

            if(reg.test(amount)==false){
                $.messager.alert('消息提示','请输入正确的数量','error');
                return false;
            }else{
                if(bid !=''){
                    $("#money"+id).val(calculate.toFixed(2));
                    //统计总额总数量
                    getTotalMoney();
                    return false;
                }

            }

        });

        // 点击删除商品
        $(".datagrid").on("click",".delCoupon",function () {
            var row = $('#couponDG').datagrid('getSelected');
            var rowIndex = $('#couponDG').datagrid('getRowIndex',row);
            $('#couponDG').datagrid('deleteRow',rowIndex);
            getTotalMoney();
        });
        // 新增采购form表单属性
        var data = new FormData($("#addBatchFormP")[0]);

        $("#addBatchFormP").form({
            type:'post',
            url:"../shopStockOrder/submitProducts",
            data:data,
            dataType:"json",
            contentType:"application/json",

            onSubmit:function(){
                // 判断选择公司
                if ( $("#typeName").val() == "" ){
                    $.messager.alert('提示', '请选择付款方式', 'info');
                    return false;
                }
                if ( $("#supplierName").val() == "" ){
                    $.messager.alert('提示', '请选择付款周期', 'info');
                    return false;
                }
                var arr = $("#couponDG").datagrid("getData").rows;
                var myArray=new Array()
                $.each(arr,function (i,n) {
                    myArray.push(n.id)

                });
                if(myArray.length==0){
                    $.messager.alert('消息提示','请选择商品','error');
                    return false;
                }
                //判断总金额限制表单提交
                if($("#totalCount").text()==0 || $("#totalMoney").text()==0){
                    $.messager.alert('消息提示','请输入正确的进价或数量','error');
                    return false;
                }
            },
            success:function (msg) {
                msg = JSON.parse(msg);
                if (msg.retCode != "0000") {
                    $.messager.alert('消息提示','新增失败[' + msg.retMsg + ']!','error');
                } else {
                    $.messager.alert('消息提示','新增成功','info');
                    $("#batchDG").datagrid('reload');
                    $("#addWindow").window("close");
                    window.location.reload();
                }

            },
            error:function() {
                $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
            }
        })

        // 点击确定
        $("#confirmBatch").linkbutton({
            onClick : function () {

               var arrCopy=$("#couponDG").datagrid("getData").rows;
                //循环动态插入值
                var amountCopy=0;
                var bidCopy=0;
                var moneyCopy=0;
                //定义一个新数组
                var CopyArray={};

                $.each(arrCopy,function (i,n) {

                    //动态获取进价数量总金额
                    amountCopy=getVolue(n.id,"amount");
                    bidCopy=getVolue(n.id,"bid");
                    moneyCopy=getVolue(n.id,"money");
                    //定义数组对象把新数据追加进去
                    CopyArray[i]={};
                    CopyArray[i]['id']=n.id;//商品id
                    CopyArray[i]['pname']=n.Pname;//名称
                    CopyArray[i]['amount']=amountCopy;//总数
                    CopyArray[i]['bid']=bidCopy;//本次进价
                    CopyArray[i]['bidOld']=n.bidOld;//产品进价
                    CopyArray[i]['moneyOld']=n.moneyOld//产品售价
                    CopyArray[i]['stored']=n.stored;//产品售价
                    CopyArray[i]['money']=moneyCopy;//总金额
                    CopyArray[i]['brandName']=n.brandName;//品牌名称
                    CopyArray[i]['brandId']=n.brandId;//品牌id
                    CopyArray[i]['productBid']=n.productBid;//标准进价
                    CopyArray[i]['supplierName']=n.supplierName;//供应商名字
                    CopyArray[i]['supplierId']=n.supplierId;//供应商id

                })
                $("#productsAll").val( JSON.stringify(CopyArray));//json赋值商品信息
                $("#countProducts").val($("#totalCount").text());
                $("#countMoneys").val($("#totalMoney").text());
                $("#addBatchFormP").submit();
            }
        })

        //采用jquery easyui loading css效果
      /*  function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }
        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }*/

    });
    // 计算总计
    function getTotalMoney() {

        // 获取页面id为money的值
        var totalMoney = 0,totalCount = 0;

        $.each($("input[name='amount']"),function(i,n){

            var id = n.dataset.id;
            totalCount = totalCount+parseInt(n.value);
            totalMoney = totalMoney+parseFloat($("#money"+id).val());
        })
        $("#totalMoney").text(totalMoney.toFixed(2));
        $("#totalCount").text(totalCount);
    }
    // 计算小计
   // 动态获取当前值的函数
     function getVolue(id,type){
          if(type=="amount"){
              return $("#amount"+id).val();
          }else if(type=="bid"){
              return $("#bid"+id).val();
          }else if(type=="money"){
              return $("#money"+id).val();
          }

     }

</script>
</body>
