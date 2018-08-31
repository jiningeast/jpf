<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <title>新增打款任务</title>

</head>

<body>
<div class="contentDiv">
    <div class="notice">
        <h6>商品采购模板使用说明</h6>
        <p>1.单批次最大支持10000条订单</p>
        <p>2.文件名格式为[供应商名称-日期-采购订单Id]，例如：XXX供应商-20180825-01</p>
        <p><a href="../shopStockOrder/download" style="color: #0e90d2;">点击下载Excel模板</a></p>
    </div>
    <div class="easyui-panel" title="批量采购" data-options="footer:'#confirm'">
        <form id="taskForm" method="post" enctype="multipart/form-data">
            <input type="hidden" name="orderid" value="${id}">
            <table class="payment" cellpadding="5" width="100%">
                <tr>
                    <td>Excel文件：</td>
                    <td colspan="2"><input id="uploadfile" class="easyui-filebox" name="file" style="width: 238px;"></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="confirm" style="padding:5px;">
        <a id="confirmBtnPur" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
    </div>
    <div id="purcaseShow"></div>
</div>
<script>
    $(function(){

        // 上传文件input属性
        $("#uploadfile").filebox({
            buttonText:'选择文件',
            multiple:'false'
        });
         // 选取公司弹窗大小
        $('#purcaseShow').window({
            width:'1024px',
            height:'540px',
            closed:true,
            modal:true
        });
        // form表单属性
        var data = new FormData($("#taskForm")[0]);
        $("#taskForm").form({
            type:'post',
            url:"../shopStockOrder/submitPurchase",
            data:data,
            dataType:"json",
            contentType:"application/json",
            enctype:"multipart/form-data",
            onSubmit:function(){

                // 判断上传文件
                var file = $("#uploadfile").filebox('getValue');
                if( file == "" ){
                    $.messager.alert('提示', '请先选择要上传文件!', 'info');
                    return false;
                }
                var type = file.slice(file.lastIndexOf(".")+1,file.length);
                var checkType = type.toUpperCase();
                if( checkType !== "XLS" && checkType !== "XLSX" ){
                    $.messager.alert('提示', '所选的文件格式不正确!', 'info');
                    return false;
                }
                ajaxLoading();
            },
            success:function (msg) {

                ajaxLoadEnd();
                var obj = JSON.parse(msg);//转化为json对象
                if(obj.code == "10000"){

                    // 打开新窗口显示数据
                    $('#purcaseShow').window("open").window('refresh', 'purchaseData?fileUUid='+obj.data.fileUUid+'&id='+obj.data.id).window('setTitle','确认采购商品数据');

                }else{
                    $.messager.alert('提示',obj.info,'info')
                }
            }
        });

        // 点击确定
        $("#confirmBtnPur").linkbutton({
            onClick:function(){
                // 表单提交
                $("#taskForm").submit();
            }
        })

        //采用jquery easyui loading css效果
        function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",leconfirm:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }
        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }

    })
</script>
</body>
</html>
