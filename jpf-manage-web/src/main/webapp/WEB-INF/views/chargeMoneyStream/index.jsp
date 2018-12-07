<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>充值平台商户管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <script>
        $(function () {

            $('#searchBtn').linkbutton({
                onClick: function(){
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

            $('#editCompany').window({
                title:'商户详情',
                width:'600px',
                height:'300px',
                closed:true,
                modal:true
            });

            var toolbar = [

            ];

            $('#dg').datagrid({
                title:'商户资金流水信息',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'list',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'merchNo',title:'流水号|交易号|订单号',width:'13%',
                        formatter:function (value,row,index) {
                            return row["id"]+"<br/>"+row["orderId"]+"<br/>"+row["orderNo"]+"<br/>";
                        }
                    },
                    {field:'productName',title:'商品',width:'10%'},
                    {field:'merchNo',title:'代理商号',width:'10%',
                        formatter:function (value,row,index) {
                            return row["merchNo"];
                        }
                    },
                    {field:'productValue',title:'收入金额|支出金额',width:'10%',
                        formatter:function (value,row,index) {
                            var banlanStr = "";
                            banlanStr += "收入：";
                            //1=充值   2=退款
                            banlanStr += (row["streamType"] == 0 ) ? row["productSalePrice"]: "";
                            banlanStr += "<br/>";
                            banlanStr += "支出：";
                            banlanStr += (row["streamType"] == 1) ? row["productSalePrice"] : "";
                            banlanStr += "<br/>";
                            return banlanStr;
                        }
                    },
                    {field:'streamType',title:'收支类型',width:'10%',formatter:function (value,row,index) {
                            if ( value == 1  ) { return "出账"; }
                            if ( value == 0 ) { return "入账"; }
                        }
                    },
                    {field:'status',title:'交易类型',width:'10%',
                        formatter:function (value,row,index) {
                            if ( value == 1 ) { return "充值"; }
                            if ( value == 2 ) { return "下单"; }
                            if ( value == 3 ) { return "退款"; }
                        }
                    },
                    {field:'addtime',title:'交易时间',width:'10%',formatter: formatDateStr},
                    {field:'isDel',title:'状态',width:'6%',
                        formatter:function (value,row,index) {
                            if ( value == 0 ) { return "正常"; }
                            if ( value == 1 ) { return "已删除"; }
                        }
                    }
                ]]
            });

            //导出excel
            $('#importExcelCaiwu').linkbutton({
                onClick: function(){

                    /*ar remarkHasDialog = dialog({
                        content: '正在导出，请稍等。。。',
                        width:140,
                        //drag:true,
                    });//.show();*/

                    var queryArray = $('#searchForm').serialize();
                    var importExcelCaiwu = "./exportExcel?"+queryArray;
                    window.location.href = importExcelCaiwu;
                }
            });

        })
    </script>
</head>
<body>
    <div id="contentDiv">
        <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#CompanyChargeft'">
            <div style="padding:10px 60px 20px 60px">
                <form id="searchForm" method="post">
                    <table cellpadding="5" width="75%">
                        <tr>
                            <td>商户号:</td>
                            <td><input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                       name="addtimeStart"
                                       onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                -
                                <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                       name="addtimeEnd"
                                       onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            </td>
                            <td>商户号:</td>
                            <td><input id="merchNo" name="merchNo" class="easyui-textbox" type="text" /></td>
                            <td>商户名称：</td>
                            <td><input id="companyName" name="companyName" class="easyui-textbox" type="text" /></td>
                        </tr>
                        <tr>
                            <td>交易类型：</td>
                            <td>
                                <select id="status" name="status" class="easyui-combobox">
                                    <option value="">全部</option>
                                    <option value="1">充值</option>
                                    <option value="2">下单</option>
                                    <option value="3">退款</option>
                                </select>
                            </td>
                            <td>收支类型：</td>
                            <td>
                                <select id="streamType" name="streamType" class="easyui-combobox">
                                    <option value="">全部</option>
                                    <option value="0">入账</option>
                                    <option value="1">出账</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="CompanyChargeft" style="padding:5px;">
            <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
            <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
            <a id="importExcelCaiwu" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-download'">导出</a>
        </div>
        <br>
        <div id="dg"></div>
    </div>
    <div id="editCompany"></div>
</body>