<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <title>新增打款任务</title>
</head>
<body>
<div class="contentDiv">
    <div class="notice">
        <h6>批量打款模板使用说明</h6>
        <p>1.单批次最大支持1000条订单</p>
        <p>2.文件名会作为批次号保存，文件名格式为[商户名称-日期-编号]，例如：XXX有限责任公司-20180509-01</p>
        <p><a href="#" style="color: #0e90d2;">点击下载Excel模板</a></p>
    </div>
    <div class="easyui-panel" title="批量打款" data-options="footer:'#ft'">
        <form id="taskForm" method="post" enctype="multipart/form-data">
            <table class="payment" cellpadding="5" width="100%">
                <tr>
                    <td width="70px">打款公司：</td>
                    <td width="180px">
                        <input id="name" name="company_name" type="text" class="easyui-textbox" style="width: 100%" disabled="disabled">
                        <input id="mid" name="company_id" type="hidden">
                    </td>
                    <td align="left"><a id="searchCompany" class="easyui-linkbutton" href="javascript:void(0)" data-options="iconCls:'icon-search'">选取公司</a></td>
                </tr>
                <tr>
                    <td>Excel文件：</td>
                    <td colspan="2"><input id="uploadfile" class="easyui-filebox" name="uploadfile" style="width: 238px;"></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="ft" style="padding:5px;">
        <a id="confirmBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
    </div>
    <div id="companys"></div>
</div>
<script>
    $(function(){
        // 选取公司按钮
        $("#searchCompany").linkbutton({
            onClick:function(){
                $('#companys').window("open").window('refresh', '../cloudCompanyMoney/companys').window('setTitle','选取公司');
            }
        });

        // 选取公司弹窗大小
        $('#companys').window({
            width:'1024px',
            height:'550px',
            closed:true,
            modal:true
        });

        // 上传文件input属性
        $("#uploadfile").filebox({
            buttonText:'选择文件',
            multiple:'false'
        });

        // form表单属性
        $("#taskForm").form({
            type:'post',
            url:"../cloudTask/submitTask",
            dataType:"json",
            contentType:"application/json",
            enctype:"multipart/form-data",
            onSubmit:function(){
                // 判断选择公司
                if ( $("#mid").val() == "" ){
                    $.messager.alert('提示', '请选择公司', 'info');
                    return false;
                }

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
            },
            success:function (msg) {
                console.log("data="+msg);
            }
        });

        // 点击确定
        $("#confirmBtn").linkbutton({
            onClick:function(){
                // 表单提交
                $("#taskForm").submit();
            }
        })
    })
</script>
</body>
</html>
