<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商户信息管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function() {
            var toolbar = [
                {
                    text:'商品基础信息管理',
                    iconCls:'icon-redo',
                    handler:function(){
                        $('#infoDiv').window("open").window('refresh', 'chargeInfoPage').window('setTitle','添加商品基础信息');
                    }
                },
                {
                    text:'添加商品',
                    iconCls:'icon-add',
                    handler:function(){

                       $('#infoDiv').window("open").window('refresh', 'addPage').window('setTitle','新增');
                    }
                },
                {
                    text:'编辑',
                    iconCls:'icon-edit',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', 'editPage?id='+rows[0].id).window('setTitle','编辑');
                    }
                }];

            $('#dg').datagrid({
                title:'充值平台产品列表',
                toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'list',
                columns:[[
                    {field:'id',title:'商品ID',width:"5%"},
                    {field:'name',title:'商品名称',width:"7%"},
                    {field:'value',title:'面值',width:"5%"},
                    {field:'bidPrice',title:'商品进价',width:"5%"},
                    {field:'salePrice',title:'商品售价',width:"5%"},
                    {field:'brandName',title:'商品品牌',width:"5%"},
                    {field:'supplierName',title:'供应商',width:"5%"},
                    {field:'typeName',title:'商品分类',width:"5%"},
                    {field:'mobileType',title:'运营商类型',width:"7%",
                        formatter: function(value,row,index){
                            if (value == 1){
                                return "移动";
                            } else if (value == 2) {
                                return "联通";
                            } else if (value == 3) {
                                return "电信";
                            } else if (value == 4) {
                                return "中石化";
                            } else if (value == 5) {
                                return "中石油";
                            }
                        }
                    },
                    {field:'isOnSale',title:'商品状态',width:"5%",
                        formatter: function(value,row,index){
                            if (value == 0){
                                return "下架";
                            } else if (value == 1) {
                                return "上架";
                            }
                        }
                    },

                    {field:'addtime',title:'创建时间',width:"10%",formatter: formatDateStr},
                    {field:'updatetime',title:'更新时间',width:"10%",formatter: formatDateStr}
                ]],
                onLoadSuccess: function () {   //隐藏表头的checkbox
                    $("#dg").parent().find("div .datagrid-header-check").children("input[type=\"checkbox\"]").eq(0).attr("style", "display:none;");
                }
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

            $('#infoDiv').window({
                width:'800px',
                height:'400px',
                closed:true,
                modal:true
            });
        });

        $(window).resize(function() {
            var width = $(window).width() - 20;
            $("div[name='contentDiv']").css("width", width);

            // 加上这个，form面板和grid面板右侧不会出现残缺
            $("#formDiv").panel().width=1;
            $('#dg').datagrid().width=1;
        });

        $(window).load(function() {
            var width = $(window).width() - 20;
            $("div[name='contentDiv']").css("width", width);

            // 加上这个，form面板和grid面板右侧不会出现残缺
            $("#formDiv").panel().width=1;
            $('#dg').datagrid().width=1;
        });
    </script>
    <style>
        #searchForm table tr td:nth-child(odd) { text-align: right; }
        #searchForm table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>商品名称:</td>
                        <td><input id="name" name="name" class="easyui-textbox" type="text" /></td>
                        <td>
                            <select editable="false" id="isOnSale" name="isOnSale" class="easyui-combobox" style="width:120px;">
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
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="dg"></table>
</div>

<div id="infoDiv"></div>
<script>
    $(function () {
        $("#product_type").combobox({
            url : '../param/getShopProductType',
            valueField : 'pid',
            textField : 'pname'
        });
        $("#product_brand").combobox({
            url : '../param/getShopBrandList',
            valueField : 'id',
            textField : 'brandName'
        });
    });
</script>
</body>