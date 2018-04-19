<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <title>编辑银行</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
    <%--编辑弹出窗口--%>
    <div id="editWin" class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px;">
            <form method="post" id="editForm" enctype="multipart/form-data">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <td width="30%" align="right">银行名称</td>
                        <td width="70%"><input type="text" id="paybankname" name="paybankname" style="width: 90%;" class="easyui-textbox" required="true" value="${bankInfo.paybankname}"/></td>
                    </tr>
                    <tr>
                        <td align="right">银行类型</td>
                        <td>
                            <input id="tpid" class="easyui-combobox" name="tpid" data-options="required:true,
                                                                                     url:'../param/getType?pid=17',
                                                                                     method:'get',
                                                                                     valueField:'catpath',
                                                                                     textField:'cat',
                                                                                     select:'${bankInfo.tpid}'+':',
                                                                                     panelHeight:'auto'">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">银行编码</td>
                        <td><input type="text" id="bankcode" name="bankcode" style="width: 90%;" class="easyui-textbox" required="true" value="${bankInfo.bankcode}"></td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" border="false"
             style="text-align: center; margin-bottom: 20px;">
            <a id="editSaveBtn" class="easyui-linkbutton" icon="icon-ok"
               href="javascript:void(0)">确定</a>
            <a id="editCancelBtn" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>
    </div>

    <script>
        // 初始化编辑信息
        function initData(){
            $('#tpid').combobox('select', '${bankInfo.tpid}'+':');
        }
        $(function(){
            // 编辑窗口的关闭按钮
            $("#editCancelBtn").linkbutton({
                onClick : function(){
                    $("#edit").window('close');
                }
            })

            // 编辑的确定按钮
            $("#editSaveBtn").linkbutton({
                onClick : function(){
                    var isValid = $("#editForm").form('enableValidation').form('validate');
                    if ( isValid ) {
                        var param = {};
                        param['id'] = '${bankInfo.id}';
                        param['paybankname'] = $("#paybankname").val();
                        param['tpid'] = $("input[name='tpid']").val();
                        param['bankcode'] = $("#bankcode").val();
                        $.ajax({
                            type : 'post',
                            url : 'editSave',
                            data : param,
                            dataType : 'json',
                            success:function(msg) {
                                if ( msg.retCode != '0000' ){
                                    $.messager.alert('消息提示','保存失败[' + msg.retMsg + ']!','error');
                                }else{
                                    $('#edit').window('close');
                                    $('#dg').datagrid('reload');
                                    $.messager.alert('消息提示','保存成功','error');
                                }
                            },
                            error:function() {
                                $.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
                            }
                        });
                    }
                }
            })

            // 执行初始化编辑信息
            setTimeout("initData()", 500);
        })
    </script>
</body>
</html>
