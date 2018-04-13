<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <title>新增银行</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
    <!-- 添加弹出窗口 -->
    <div id="addWin" class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px;">
            <form method="post" id="addForm" enctype="multipart/form-data">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <td width="30%" align="right">银行名称</td>
                        <td width="70%"><input type="text" id="paybankname" name="paybankname" style="width: 90%;" class="easyui-textbox" required="true"/></td>
                    </tr>
                    <tr>
                        <td align="right">银行类型</td>
                        <td>
                            <input id="tpid" class="easyui-combobox" name="tpid" data-options="required:true,
                                                                                     url:'../param/getType?pid=17',
                                                                                     method:'get',
                                                                                     valueField:'catpath',
                                                                                     textField:'cat',
                                                                                     panelHeight:'auto'">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">银行编码</td>
                        <td><input type="text" id="bankcode" name="bankcode" style="width: 90%;" class="easyui-textbox" required="true"></td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" border="false"
             style="text-align: center; margin-bottom: 20px;">
            <a id="addSaveBtn" class="easyui-linkbutton" icon="icon-ok"
               href="javascript:void(0)">确定</a>
            <a id="addCancelBtn" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>
    </div>

    <script>
        $(function(){
            // 新增窗口的关闭按钮
            $("#addCancelBtn").linkbutton({
                onClick : function(){
                    $("#edit").window('close');
                }
            })

            // 新增的确定按钮
            $("#addSaveBtn").linkbutton({
                onClick : function(){
                    var isValid = $("#addForm").form('enableValidation').form('validate');
                    alert(isValid);
                    if ( isValid ) {
                        var param = {};
                        param['paybankname'] = $("#paybankname").val();
                        param['tpid'] = $("input[name='tpid']").val();
                        param['bankcode'] = $("#bankcode").val();
                        $.ajax({
                            type : 'post',
                            url : 'save',
                            data : param,
                            dataType : 'json',
                            success:function(msg) {
                                if ( msg.retCode != '0000' ){
                                    $.messager.alert('消息提示','添加失败[' + msg.retMsg + ']!','error');
                                }else{
                                    $('#edit').window('close');
                                    $('#dg').datagrid('reload');
                                    $.messager.alert('消息提示','添加成功','error');
                                }
                            },
                            error:function() {
                                $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                            }
                        });
                    }
                }
            })
        })
    </script>
</body>
</html>
