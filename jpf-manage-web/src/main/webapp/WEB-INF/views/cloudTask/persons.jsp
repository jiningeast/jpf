<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员列表</title>
</head>
<body>
<div name="contentDiv" style="padding: 15px;">
    <%--成功失败列表提示信息--%>
        <c:if  test="${requestScope.code == '10000'}">
            <div class="notice" >
                <p style="margin-top: 0;">成功：Excel表格信息确认无误，共${requestScope.total }条，请点击确认</p>
            </div>
            <form id="confirmPersonsForm" method="post" enctype="multipart/form-data">
                <input id="companyId" name="companyId" type="hidden" value="">
                <input id="data" name="data" type="hidden" value=${data}>
                <%--<input id="file" type="file" value="" style="display: none;">--%>
            </form>
        </c:if>
        <c:if  test="${requestScope.code == '10001'}">
            <div class="notice" style="background-color: #ffe9e9;border-color: #ffa8a8">
                <p style="margin-top: 0px;">失败：Excel表格信息有误，共${requestScope.total}条，详情如下，请修改重新上传</p>
            </div>
        </c:if>
        <c:if  test="${requestScope.code == '10004'}">
            <div class="notice" style="background-color: #ffe9e9;border-color: #ffa8a8">
                <p style="margin-top: 0px;">失败：Excel表格信息有误，${requestScope.info}</p>
            </div>
        </c:if>
        <div id="dg"></div>
</div>
<script>
<c:if  test="${requestScope.code != '10004'}">
    $(function () {
        var toolbar = [{
            text:'确认',
            iconCls:'icon-ok',
            handler:function(){
                // 把上个页面的值拿过来
                $("#companyId").val( $("#mid").val() );
                // $("#file").val( $("#uploadfile").filebox("getValue") );


                $("#confirmPersonsForm").submit();
            }
        }];

        $("#dg").datagrid({
            title:'人员信息',
            toolbar:toolbar,
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            url:'personsData?data='+${data},
            columns:[[
                {field:'type',title:'类型',width:"10%",
                    formatter:function (value,row,index) {
                        if ( value == '0' ){
                            return "个人";
                        }else if ( value == '1' ){
                            return "企业";
                        }
                    }},
                {field:'bankName',title:'总行名称',width:"10%"},
                {field:'province',title:'开户行省',width:"10%"},
                {field:'city',title:'开户行市',width:"10%"},
                {field:'bankNo',title:'收款账号',width:"10%"},
                {field:'name',title:'收款户名',width:"10%"},
                {field:'idno',title:'身份证号',width:"10%"},
                {field:'phone',title:'手机号',width:"10%"},
                {field:'money',title:'打款金额',width:"10%"},
                {field:'memo',title:'备注',width:"10%"}
            ]]
        })

        $("#confirmPersonsForm").form({
            type:'post',
            url:"../cloudTask/confirmPersons",
            dataType:"json",
            contentType:"application/json",
            enctype:"multipart/form-data",
            onSubmit:function () {

            },
            success:function (msg) {
                var response = JSON.parse(msg);
                if (response.retCode != '0000') {
                    $.messager.alert('消息提示', response.retMsg, 'error');
                } else {
                    alert(response.retMsg);
                    window.location.reload();
                }
            }
        });
    })
</c:if>
</script>
</body>
</html>
