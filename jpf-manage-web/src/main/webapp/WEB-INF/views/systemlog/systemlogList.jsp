<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统日志</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        td, th { text-align: center; }
    </style>
    <script>
        $(function () {
            // 获取客户端中文字段
            var typeArr = new Array();
            $.ajax({
                type : 'post',
                url : '../param/getType?pid=31',
                dataType: 'json',
                success : function (res) {
                    var res = JSON.stringify(res);
                    // console.log(res);
                    var newKsy;
                    var newValue;
                    JSON.parse(res,function(key, value){
                        if ( key == 'catid' ) {
                            newKsy = value;
                        }
                        if ( key == 'cat' ) {
                            newValue = value;
                        }
                        typeArr[newKsy] = String(newValue);
                    })
                    // console.log(typeArr);
                }
            });

            $("#dg").datagrid({
                title : '系统日志列表',
                pagination : true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect : true,
                multiselect : false,
                selectOnCheck : true,
                url : 'list',
                columns : [[
                    {field:'id',title:'ID', width:'5%'},
                    {field:'logtype', title:'来源',width:'5%',
                        formatter : function (value, row, index) {
                            if ( value == '0' ){
                                return '前台';
                            }else if ( value == '1' ){
                                return '后台';
                            }
                        }},
                    {field:'operatorUid', title:'操作者uid', width:'5%'},
                    {field:'operatorName', title:'操作者账号', width:'5%'},
                    {field:'ip', title:'IP地址', width:'6%'},
                    {field:'ip1', title:'用户设备识别码', width:'5%'},
                    {field:'clients', title:'客户端', width:'5%',
                        formatter : function ( value, row, index ) {
                            return typeArr[value];
                        }},
                    {field:'tablename', title:'表名', width:'5%'},
                    {field:'record', title:'操作人ID', width:'5%'},
                    {field:'action', title:'操作类型', width:'5%'},
                    {field:'content', title:'SQL语句', width:'42%'},
                    {field:'created', title:'创建时间', width:'8%',formatter: formatDateStr}
                ]]
            })
            $('#dg').datagrid().datagrid('getPager');
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
        })
    </script>
</head>
<body>
<div name="contentDiv">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'" style="width: 100%;">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td style="text-align: right">状态:</td>
                        <td style="text-align: left; width: 100px">
                            <select id="logtype" name="logtype" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">前台</option>
                                <option value="1">后台</option>
                            </select>
                        </td>
                        <td>操作者帐号:</td>
                        <td style="width: 180px; text-align: left"><input id="operatorName" name="operatorName" class="easyui-textbox" type="text" /></td>
                        <td>创建时间：</td>
                        <td>
                            <input type="text" class="Wdate" style="width:158px;" id="startAddTime_s"
                                   name="startAddTime"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endAddTime_s\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:158px;" id="endAddTime_s"
                                   name="endAddTime"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startAddTime_s\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
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
</div>
    <table id="dg"></table>
</body>


<div id="infoDiv"></div>
</body>
</html>
