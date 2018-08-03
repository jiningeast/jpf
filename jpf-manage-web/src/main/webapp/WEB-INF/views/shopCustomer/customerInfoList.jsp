<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>客户列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>


    <style>
        #searchForm1 table tr td:nth-child(odd) { text-align: right; }
        #searchForm1 table tr td:nth-child(even) { text-align: left; }
    </style>
</head>

<body>
<div style="width:100%;"><p style="text-align: left;"><span><h3>客户信息</h3></span></p><p style="text-align: right;font-size:30px;color:#333333;"><span >${phone}</span>&nbsp;&nbsp;&nbsp;<span style="color: #ff4114">当前欣豆 ${dou}个</span></p></div>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv2" class="easyui-panel" title="搜索条件" data-options="footer:'#ft2'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm1" method="post">
                <input id="customerId" type="hidden" name="customerId"  value="${id}"/>
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>内容:</td>
                        <td><input id="content" name="content" class="easyui-textbox" type="text" /></td>
                        <td>时间:</td>
                        <td>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <td>类型:</td>
                        <td>
                            <select id="type" name="type" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">激活</option>
                                <option value="1">消费</option>
                                <option value="2">退豆</option>
                                <option value="3">过期</option>
                            </select>
                        </td>

                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div id="ft2" style="padding:5px;">
        <a id="searchBtn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="dgac"></table>
</div>
<script>
    $(function() {
        $('#dgac').datagrid({
            title:'数据内容',
            toolbar:toolbar,
            // rownumbers:true,//如果为true，则显示一个行号列。
            pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
            // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
            singleSelect:true,
            multiselect:true,
            selectOnCheck:true,
            remoteSort: false, // 服务端排序
            // width:500,
            url:'listCouponList?id='+${id},
            columns:[[
                {field:'id',title:'ID',width:'3%'},
                {field:'customerName',title:'顾客姓名',width:'10%'},
                {field:'companyName',title:'企业名称',width:'10%'},
                {field:'batchNo',title:'批次号',width:'8%'},
                {field:'couponNo',title:'券号',width:'10%'},
                {field:'money',title:'面值',width:'10%'},
                {field:'dou',title:'豆数量',width:'10%',
                    formatter : function(value,row,index){

                        if(row['type']==1|| row['type']==3 ){
                            return "<span style='color: #FF2F2F;' >-"+value+"</span>";
                        }else{
                            return "<span >+"+value+"</span>";
                        }
                    }},
                {field:'content',title:'内容',width:'10%'},
                {field:'payWay',title:'方式',width:'8%',
                    formatter : function(value,row,index){
                        if(value=='0'){return '欣豆'}
                        else if(value=="1"){return '微信'}
                    }
                },{field:'type',title:'类型',width:'8%',
                    formatter : function(value,row,index){
                        if(value=='0'){return '激活'}
                        else if(value=="1"){return '消费'}
                        else if(value=="2"){return '退豆'}
                        else if(value=="3"){return '过期'}
                    },styler: function (value, row, index) {
                        if(value=='1' || value=='3'){
                            return 'color:red';
                        }

                    }
                },
                {field:'addtime',title:'添加时间',width:'10%',formatter: formatDateStr},
                {field:'updatetime',title:'到期时间',width:'10%',formatter: formatDateStr},

            ]]
        });

        $('#searchBtn1').linkbutton({
            onClick: function(){
                var queryArray = $('#searchForm1').serializeArray();
                var postData = parsePostData(queryArray);
                $('#dgac').datagrid('reload', postData);
            }
        });

        $('#searchRestBtn1').linkbutton({
            onClick: function(){
                $('#searchForm1').form('reset');
            }
        });
        $(window).resize(function() {
            var width = $(window).width() - 20;
            //console.info(width);
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
    });


</script>

</body>

</html>
