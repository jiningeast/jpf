<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>门店管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        td, th{ text-align: center; }
        #searchForm td:nth-child(odd) { text-align: right; }
        #searchForm td:nth-child(even) { text-align: left; }
        #searchForm td { width: 5%;  }
    </style>
    <script>
        $(function () {
            var toolbar = [
                {
                    text:'删除',
                    iconCls:'icon-remove',
                    handler:function () {
                        var rows = $("#dg").datagrid('getSelections');
                        if ( rows.length != 1 ) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return false;
                        }
                        var id = rows[0].id;
                        $.post("delMerShop",{id:id},function (msg) {
                            if (msg.retCode != '0000') {
                                $.messager.alert('消息提示','操作失败[' + msg.retMsg + ']!','error');
                                return false;
                            } else {
                                $.messager.alert('消息提示','操作成功!','info');
                                $('#dg').datagrid('reload');
                            }
                        })
                    }
                }
            ];

            $("#dg").datagrid({
                title:"门店管理",
                toolbar:toolbar,
                url:'list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                columns:[[
                    {field:"id", title:"ID", width:"10%"},
                    {field:"mtsid", title:"商户ID", width:"10%"},
                    {field:"mtsName", title:"商户名称", width:"10%"},
                    {field:"pid", title:"上线ID", width:"10%"},
                    {field:"parMtsName", title:"上线商户名称", width:"10%"},
                    {field:"path", title:"路径", width:"10%"},
                    /*{field:"isDel", title:"是否删除", width:"10%",
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return "未删除";
                            }else if ( value == 1 ){
                                return "<span style='color:red;'>已删除</span>";
                            }
                        }},*/
                    {field:"created", title:"生成时间", width:"10%", formatter:formatDateStr},
                    {field:"updated", title:"修改时间", width:"10%", formatter:formatDateStr}
                ]]
            })

            // 点击搜索按钮
            $('#searchBtn').linkbutton({
                onClick: function(){
                    var queryArray = $('#searchForm').serializeArray();
                    var postData = parsePostData(queryArray);
                    $('#dg').datagrid('reload', postData);
                }
            });

            // 点击重置按钮
            $('#searchRestBtn').linkbutton({
                onClick: function(){
                    $('#searchForm').form('reset');
                }
            });
        })
    </script>
</head>
<body>
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'" style="padding: 20px;">
        <form id="searchForm" method="post">
            <table cellpadding="5" width="100%">
                <tr>
                    <td>商户ID：</td>
                    <td><input id="mtsid" name="mtsid" class="easyui-textbox" type="text" ></td>
                    <td>上线商户ID：</td>
                    <td><input id="pid" name="pid" class="easyui-textbox" type="text" /></td>
                    <td>生成时间：</td>
                    <td>
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                               name="addtimeStart"
                               onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        -
                        <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                               name="addtimeEnd"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="dg"></table>
</body>
</html>
