<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商户信息管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>

    <style>
        #searchFormP table tr td:nth-child(odd) { text-align: right; }
        #searchFormP table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" >
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ftp'">
        <div >
            <form id="searchFormP" method="post">
                <table class="products" cellpadding="5" width="100%">
                    <tr>
                        <td>商品名称:</td>
                        <td><input id="product_name" name="name" class="easyui-textbox" type="text" /></td>
                        <td>
                        <td>商品状态:</td>
                         <td>
                            <select id="status_s" name="status" class="easyui-combobox" style="width:120px;">
                                <option value="">全部</option>
                                <option value="1">上架</option>
                                <option value="0">下架</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="ftp" style="padding:5px;">
        <a id="searchBtnproducts" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtnProduct" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="dgproducts"></table>
</div>

<div id="infoDiv"></div>
<script>
    $(function() {
        function isInArray(arr,value){
            for(var i = 0; i < arr.length; i++){
                if(value === arr[i]){
                    return true;
                }
            }
            return false;
        }
        var toolbar = [{
            text:'确定',
            iconCls:'icon-ok',
            handler:function(){

                var rows = $('#dgproducts').datagrid('getSelections');
                if (rows.length != 1) {
                    $.messager.alert('消息提示','请选择一条数据！','info');
                    return false;
                }
                //判断当前商品是否已经选择
                var arr = $("#couponDG").datagrid("getData").rows;
                var myArray=new Array()
                $.each(arr,function (i,n) {
                    myArray.push(n.id)

                });
                if(isInArray(myArray,rows[0].id)){
                    $.messager.alert('消息提示','当前商品已经添加','error');
                    return false;
                }
                //追加数据到定义的商品列表中
                $('#couponDG').datagrid('appendRow',{
                    id: rows[0].id,
                    Pname: rows[0].name,
                    supplierName: rows[0].supplierName,
                    supplierId: rows[0].supplierId,
                    brandName: rows[0].brandName,
                    brandId: rows[0].brandId,
                    productBid: rows[0].bid,
                    bidOld: rows[0].bid,
                    moneyOld: rows[0].money,
                    stored: rows[0].stored,
                    bid: '<input id="bid'+rows[0].id+'" data-id="'+rows[0].id+'" name="bid" type="text" data-options="required:true"  style="color: #ff2810;">',
                    amount: '<input id="amount'+rows[0].id+'"  data-id="'+rows[0].id+'"  name="amount" type="text"  data-options="required:true" style="color: #ff2b0e;">',
                    money: '<input id="money'+rows[0].id+'" type="text" class="easyui-textbox" disabled="disabled"> ',
                    cardType:'  <select id="cardType'+rows[0].id+'" class="easyui-combobox" style="width: 100px">\n' +
                    '            <option value="1">直冲</option>\n' +
                    '            <option value="2" selected="selected">卡密</option>\n' +
                    '        </select>\n',
                    delete: '<a class="delCoupon easyui-linkbutton" style="cursor: pointer;color: #0e90d2">删除</a>'
                });
                $("#products").window("close");


            }
        },
            {
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                        $("#products").window('close');
                    }

            }];

        $('#dgproducts').datagrid({
            title:'旅游产品列表',
            toolbar:toolbar,
            // rownumbers:true,//如果为true，则显示一个行号列。
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
            singleSelect:true,
            multiselect:true,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            // width:500,
            url:'plist',
            columns:[[
                {field:'id',title:'商品ID',width:80},
                {field:'name',title:'商品名称',width:200},
                {field:'bid',title:'商品进价',width:200},
                {field:'money',title:'商品售价',width:200},
                {field:'rechargeMoney',title:'充值面额(元)',width:150},
                {field:'dou',title:'所需豆',width:150},
                {field:'brandName',title:'商品品牌',width:200},
                {field:'supplierName',title:'供应商',width:200},
                {field:'typeName',title:'商品分类',width:200},
                {field:'stored',title:'当前库存',width:200},
                {field:'storedSafe',title:'安全库存',width:200},
                // {field:'pdpicture',title:'产品图片',width:150,
                //     formatter:function(value,row,index){return '<img style="height:80px;width:100px;" src="'+ value +'" />';}
                // },
                {field:'status',title:'商品状态',width:100,
                    formatter: function(value,row,index){
                        if (value == '0'){
                            return "下架";
                        } else if (value == '1') {
                            return "上架";
                        }
                    }
                },

            ]],
            onLoadSuccess: function () {   //隐藏表头的checkbox
                $("#dgproducts").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]").eq(0).attr("style", "display:none;");
            }
        });

        $('#searchBtnproducts').linkbutton({
            onClick: function(){
                // var param = {};
                // param["username"]=$('#username_s').textbox('getValue');
                // param["status"]=$('#status_s').combobox('getValue');
                // $('#dgproducts').datagrid('reload', param);

                var queryArray = $('#searchFormP').serializeArray();
                var postData = parsePostData(queryArray);
                $('#dgproducts').datagrid('reload', postData);
            }
        });

        $('#searchRestBtnProduct').linkbutton({
            onClick: function(){
                $('#searchFormP').form('reset');
            }
        });

        $('#infoDiv').window({
            width:'800px',
            height:'500px',
            closed:true,
            modal:true
        });
    });

    $(window).resize(function() {
        var width = $(window).width() - 20;
        $("div[name='contentDiv']").css("width", width);

        // 加上这个，form面板和grid面板右侧不会出现残缺
        $("#formDiv").panel().width=1;
        $('#dgproducts').datagrid().width=1;
    });

    $(window).load(function() {
        var width = $(window).width() - 20;
        $("div[name='contentDiv']").css("width", width);

        // 加上这个，form面板和grid面板右侧不会出现残缺
        $("#formDiv").panel().width=1;
        $('#dgproducts').datagrid().width=1;
    });

    function get_time() {
        return new Date().getTime();
    }
</script>

</body>
</html>