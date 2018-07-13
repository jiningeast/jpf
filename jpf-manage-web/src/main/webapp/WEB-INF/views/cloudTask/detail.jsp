<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <title>任务详情</title>
</head>
<body>
    <div name="contentDiv" style="padding: 5px;">
        <div id="detailDg"></div>
    </div>
    <script>
        $(function () {

            var toolbar = [{
                text:'锁定',
                iconCls:'icon-ok',
                handler:function(){
                    $.post("lockOrder?taskId=${taskId}",{},function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', msg.retMsg ,'error');
                        } else {
                            alert("锁定成功");
                            window.location.reload();
                        }
                    })
                }
            }];

            // 任务详情
            $('#detailDg').datagrid({
                title:'任务详情列表',
                toolbar:toolbar,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                url:'banksData?taskId='+${taskId},
                columns:[[
                    {field:'banknickname',title:'姓名',width:"15%"},
                    {field:'bankname',title:'银行',width:"15%"},
                    {field:'bankno',title:'卡号',width:"15%"},
                    {field:'bankphone',title:'手机号',width:"15%"},
                    {field:'bank_active',title:'状态',width:"15%",
                        formatter:function (value,row,index) {
                            if ( value == "0" ){
                                return "未激活";
                            }else{
                                return "已激活";
                            }
                        }},
                    {field:'bankacctattr',title:'类别',width:"15%",
                        formatter:function (value,row,index) {
                            if ( value == "0" ){
                                return "对私";
                            }else{
                                return "对公";
                            }
                        }},

                ]]
            });
        })
    </script>
</body>
