<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/7/11
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>代付明细</title>
        </head>
<body>
<div name="contentDiv" style="padding: 15px;">
    <div id="dgDetail"></div>
</div>

<script>
    $(function(){
        var toolbar = [{
            text:'确认打款',
            iconCls:'icon-ok',
            handler:function(){
                $("#addWindow").window("open").window('refresh','').window('setTitle','打款');
            }
        }];

        $("#dgDetail").datagrid({
            title : '云账户代付明细列表',
            toolbar:toolbar,
            pagination:false,
            singleSelect:true,
            multiselect:false,
            selectOnCheck:true,
            url : '../dfDetail?fid='+${fid},
            columns : [[
                {field:'id', title:'ID',width:'4%'},
                {field:'fid', title:'订单号',width:'15%'},
                {field:'banknickname', title:'收款人',width:'8%'},
                {field:'bankno', title:'银行卡号',width:'15%'},
                {field:'commoney', title:'打款金额',width:'8%'},
                {field:'isActive', title:'代付状态',width:'10%',
                    formatter:function (value, row, index) {
                        if ( value == 0 ){
                            return '不可代付';
                        }else if ( value == 1 ){
                            return '已激活';
                        }
                    }
                },
                {field:'addtime', title:'创建时间',width:'10%',formatter: formatDateStr},
                {field:'updatetime', title:'修改时间',width:'10%',formatter: formatDateStr}
            ]]
        });
        //$('#dg').datagrid().datagrid('getPager');


    })


</script>

</body>
</html>
