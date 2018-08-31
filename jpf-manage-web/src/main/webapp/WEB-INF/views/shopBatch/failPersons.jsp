<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>待发人员列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        table tr td { text-align: center; }
    </style>
</head>
<body>
    <div id="contentDiv" style="padding: 20px;">
        <div id="failPersonsDG"></div>
    </div>
    <script>
        $(function () {
            var toolbar = [{
                text : '群发',
                iconCls : 'icon-redo',
                handler : function(){
                    $.messager.confirm('请确认','确定要发送吗，该操作无法撤销',function (r) {
                        if (r){
                            $.post("sendCoupons",{fileName:'${fileName}'},function (msg) {
                                if (msg.retCode !== '0000'){
                                    $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']','error');
                                }else{
                                    $.messager.alert('消息提示','已分发并短信通知','info');
                                    $("#failWindow").window("close");
                                    $("#sendEmail").window("close");
                                    $("#batchDG").window("refresh","list");
                                }
                            })
                        }
                    });
                }
            }];

            $('#failPersonsDG').datagrid({
                title:'待发人员列表',
                toolbar:toolbar,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:false,
                remoteSort: false,  // 服务端排序
                url:'getFailPersons?fileName=${fileName}',
                columns:[[
                    {field:'name',title:'姓名',width:"10%"},
                    {field:'phone',title:'手机号',width:"20%"},
                    {field:'dou',title:'面值',width:"10%"},
                    {field:'idno',title:'身份证号',width:"10%"},
                    {field:'status',title:'账户冻结',width:"10%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "<font style='color:red'>账户已冻结</font>";
                            }else{
                                return "正常";
                            }
                        }},
                    {field:'isVerify',title:'可支付状态',width:"10%",
                        formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "<font style='color:red'>支付校验码错误</font>";
                            }else{
                                return "正常";
                            }
                        }}
                ]]
            });
        })
    </script>
</body>