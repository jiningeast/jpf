<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>批次详情</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
    <div id="contentDiv" style="padding: 20px;">
        <form id="form" method="post" enctype="multipart/form-data">
            <input type="hidden" name="companyId" value="${companyId}">
            <div id="toReceiver">
                <input id="toReceiverRadio" name="sendType" value="0" type="radio" checked="checked"><label for="toReceiverRadio">发送给接收人</label>
                <div style="padding-left: 30px;margin: 15px 0;">
                    <span>接收人姓名：<input type="text" class="easyui-textbox" disabled="disabled" value="${receiveName}"></span>
                    <span style="padding-left: 40px;">接收人手机号：<input type="text" class="easyui-textbox" disabled="disabled" value="${receivePhone}"></span>
                    <span style="padding-left: 40px;">接收人邮箱：<input type="text" class="easyui-textbox" disabled="disabled" value="${receiveEmail}" style="width: 180px;"></span>
                </div>
            </div>
            <div id="toPersons">
                <input id="toPersonsRadio" name="sendType" value="1" type="radio"><label for="toPersonsRadio">发送给客户</label>
                <div style="padding-left: 30px; margin: 15px 0;">
                    <label for="uploadfile">上传客户excel文件：</label>
                    <input id="uploadfile" class="easyui-filebox" name="uploadfile" style="width: 238px;">
                    <span style="padding-left: 10px;"><a href="../shopBatch/download" style="color: blue;">下载excel模板文件</a></span>
                </div>
            </div>
            <a id="confirmBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-left: 30px;">确定</a>
        </form>
    </div>
    <div id="failWindow"></div>

    <script>
        $(function () {
            // 上传文件input属性
            $("#uploadfile").filebox({
                buttonText:'选择文件',
                multiple:'false'
            });

            // 发送邮件选项
            $("#failWindow").window({
                width:'900px',
                height:'300px',
                closed:true,
                modal:true
            });

            // 点击确定
            $("#confirmBtn").linkbutton({
                onClick:function(){
                    // 表单提交操作
                    var sendType = $("input[name='sendType']:checked").val();
                    var url = "";
                    var data;
                    var message = "";
                    var type = "";
                    if ( sendType == 0 ){
                        url = "sendReceiver?batchId=${batchId}";
                        data = null;
                        message = "确定发送含有压缩包的EMAIL吗？";
                        type = "get";
                    }else if ( sendType == 1 ){
                        $.messager.alert('提示', '功能开发中……', 'info');
                        return false;

                        // 判断上传文件
                        var file = $("#uploadfile").filebox('getValue');
                        if( file == "" ){
                            $.messager.alert('提示', '请先选择要上传文件', 'info');
                            return false;
                        }
                        var type = file.slice(file.lastIndexOf(".")+1,file.length);
                        var checkType = type.toUpperCase();
                        if( checkType !== "XLS" && checkType !== "XLSX" ){
                            $.messager.alert('提示', '所选的文件格式不正确', 'info');
                            return false;
                        }

                        url = "sendPersons";
                        message = "确定将此批次欣券群发给个人吗？";
                        type = "post";
                    }

                    // 表单提交
                    var data = new FormData($("#form")[0]);
                    $.messager.confirm('请确认',message,function (r) {
                        if (r){
                            ajaxLoading();
                            $("#form").form({
                                type : type,
                                url : url,
                                data: data,
                                dataType : "json",
                                contentType : "application/json",
                                enctype:"multipart/form-data",
                                success : function(msg){
                                    // console.log(msg);
                                    msg = JSON.parse(msg);
                                    ajaxLoadEnd();
                                    if (msg.retCode != '0000' && msg.retCode != '00001') {
                                        $('#dg').datagrid('reload');
                                        $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']','error');
                                    } else if ( msg.retCode == '00001' ){
                                        // 列出所有人员
                                        $("#failWindow").window("open").window('refresh','failPersons'+'?fileName='+msg.retMsg).window('setTitle','请确认');
                                    } else if ( msg.retCode == '0000' ) {
                                        $('#dg').datagrid('reload');
                                        $.messager.alert('消息提示','操作成功','info');
                                    }
                                },
                                error : function () {
                                    ajaxLoadEnd();
                                    $('#dg').datagrid('reload');
                                    $.messager.alert('消息提示','连接网络失败，请您检查您的网络','error');
                                }
                            })
                            $("#form").submit();
                        }
                    })
                }
            })
        })

        //采用jquery easyui loading css效果
        function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }
        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }
    </script>
</body>
