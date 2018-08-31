<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员列表</title>
</head>
<body>
<div name="contentDiv" style="padding: 10px;">
    <%--成功失败列表提示信息--%>
    <c:if  test="${successSize != '0'}">
        <div class="notice" >
            <p style="margin-top: 0;">成功：Excel表格信息正确 ${successSize }条 <span id="success" class="viewData success" style="border: 2px solid #3398FF;
            padding: 3px;
            background: #3398FF;
            color: #FFFFFF;
            cursor: pointer;">点击查看</span></p>
        </div>
    </c:if>
        <form id="confirmPersonsForm" method="post" enctype="multipart/form-data">
            <input id="fileUUid" name="fileUUid" type="hidden" value=${fileUUid}>
            <input id="orderid" name="orderid" type="hidden" value=${id}>
            <input id="faildsize" name="faildsize" type="hidden" value=${faildSize}>
       </form>

    <c:if  test="${faildSize != '0'}">
        <div class="notice" style="background-color: #ffe9e9;border-color: #ffa8a8">
            <p style="margin-top: 0px;">失败：Excel表格错误信息共 ${faildSize} 条 <span class="viewData faild" style="border: 2px solid #3398FF;
            padding: 3px;
            background: #3398FF;
            color: #FFFFFF;
            cursor: pointer;">点击查看</span>，请修改后重新上传【可能原因：商品和采购订单商品不匹配、采购数量和订单商品数量不匹配、字段值为空、卡号重复】</p>
        </div>
    </c:if>
    <div id="purchaseShow"></div>
</div>
<script>


    $(function () {

        var toolbar = [{
            text:'确认',
            id:'textShow',
            iconCls:'icon-ok',
            handler:function(){

                $("#confirmPersonsForm").submit();
            }
        }];
        requestta();
        $('.viewData').click(function(){

            var _this = $(this),type=null;
            if(_this.hasClass('success')){

                type = 'success';
            }else{
                type = 'faild'
            }
            requestData(type,_this);
        });
        function requestData(type,_this) {

            $("#purchaseShow").datagrid({
                title:'商品信息',
                toolbar:toolbar,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false,
                url:"purchaseShow?type="+type+"&fileUUid=${fileUUid}",
                columns:[[
                    {field:'productId',title:'商品id',width:"15%"},
                    {field:'cardNo',title:'卡号',width:"15%"},
                    {field:'cardPass',title:'开户行省',width:"15%"},
                    {field:'expireMonth',title:'有效期',width:"15%"},
                    {field:'expireDate',title:'到期时间',width:"15%"},
                ]]
            })
            if(parseInt($('#faildsize').val())>=1){

                $('.datagrid-toolbar').remove()//根据下标删除
            }
        }
        $("#confirmPersonsForm").form({
            type:'post',
            url:"dealPurchaseData",
            dataType:"json",
            contentType:"application/json",
            enctype:"multipart/form-data",
            onSubmit:function () {

                ajaxLoading();
            },
            success:function (msg) {
                ajaxLoadEnd();
                var obj = JSON.parse(msg);//转化为json对象
                if(obj.code == "10000"){

                    $('#infoDiv').window('close');
                    $('#purcaseShow').window("close");

                    var queryArray = $('#searchForm').serializeArray();
                    var postData = parsePostData(queryArray);
                    $('#dg').datagrid('reload', postData);

                    $.messager.alert('提示',"导入成功",'info')
                    //window.location.reload();
                }else{
                    $.messager.alert('提示',obj.info,'info')
                }

            }
        });
        //采用jquery easyui loading css效果
        function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }
        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }

        function requestta() {

            $("#purchaseShow").datagrid({
                title:'商品信息',
                toolbar:toolbar,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false,
                url:"purchaseShow?type=success&fileUUid=${fileUUid}",
                columns:[[
                    {field:'productId',title:'商品id',width:"10%"},
                    {field:'cardNo',title:'卡号',width:"10%"},
                    {field:'cardPass',title:'开户行省',width:"10%"},
                    {field:'expireMonth',title:'有效期',width:"10%"},
                    {field:'expireDate',title:'到期时间',width:"10%"},
                ]]
            })
            if(parseInt($('#faildsize').val())>=1){

                $('.datagrid-toolbar').remove()//根据下标删除
            }
        }
    })



</script>
</body>
</html>
