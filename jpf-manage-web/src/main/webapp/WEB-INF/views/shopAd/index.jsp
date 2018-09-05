<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欣券管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        #addCoupons tr td:nth-child(odd) { text-align: right; }
        #addCoupons tr td:nth-child(even) { text-align: left; }
        .datagrid-mask { z-index: 9998; }
        .datagrid-mask-msg { z-index: 9999; }
    </style>
    <script>
        $(function () {
            // 新增券批次弹窗大小
            $('#infoDiv').window({
                width:'1024px',
                height:'500px',
                closed:true,
                modal:true
            });


            var toolbar = [{
                text:'新增',
                iconCls:'icon-add',
                handler:function(){
                    $("#infoDiv").window("open").window('refresh','../shopAd/add/page').window('setTitle','新增欣券批次');
                }
            },/*{
                text:'查看详情',
                iconCls:'icon-view-detail',
                handler:function(){
                    var rows = $('#batchDG').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $("#infoDiv").window("open").window('refresh','../shopAd/view/page?batchId='+rows[0].id).window('setTitle','欣券批次详情');
                }
            }*/{
                text:'编辑',
                iconCls:'icon-edit',
                handler:function(){
                    var rows = $('#batchDG').datagrid('getSelections');
                    if (rows.length != 1) {
                        $.messager.alert('消息提示','请选择一条数据！','info');
                        return false;
                    }
                    $("#infoDiv").window("open").window('refresh','../shopAd/edit/page?id='+rows[0].id).window('setTitle','编辑');
                }
            }];

            // 欣券数据
            $('#batchDG').datagrid({
                title:'图片信息列表',
                toolbar:toolbar,
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                singleSelect:true,
                multiselect:false,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                url:'list',
                columns:[[
                    {field:'id',title:'批次id',width:"5%"},
                    {field:'title',title:'图片文字',width:"10%"},
                    {field:'imgUrl',title:'图片',width:"15%",
                        formatter:function (value,row,index) {
                                return "<a href='"+row['url']+"' target='_blank' ><img src='"+value+"' width='50' height='50' alt='"+row['title']+"' ></a>";
                        }
                    },
                    //{field:'url',title:'链接地址',width:"15%"},
                    {field:'weight',title:'排序',width:"10%"},
                    {field:'type',title:'广告类型',width:"10%",formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "首页图标";
                            }else if ( value == 1 ){
                                return "首页轮播图";
                            }else if ( value == 2 ){
                                return "首页banner图片";
                            }
                        }
                    },
                    {field:'status',title:'是否显示',width:"10%",formatter:function (value,row,index) {
                            if ( value == 0 ){
                                return "不显示";
                            }else if ( value == 1 ){
                                return "显示";
                            }
                        }
                    }
                ]]
            });


            $('#searchBtn').linkbutton({
                onClick: function(){

                    var queryArray = $('#searchForm').serializeArray();
                    var postData = parsePostData(queryArray);
                    $('#batchDG').datagrid('reload', postData);
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
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="55%">
                    <tr>
                        <td>图标文字:</td>
                        <td><input id="title" name="title" class="easyui-textbox" type="text" /></td>
                        <td>充值状态:</td>
                        <td>
                            <select id="type_s" name="type" class="easyui-combobox">
                                <option value="">全部</option>
                                <option value="0">首页图标</option>
                                <option value="1">首页轮播图</option>
                                <option value="2">首页banner图片</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>添加起止时间:</td>
                        <td colspan="3">
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeStart"
                                   name="addtimeStart"
                                   onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addtimeStart\');}',startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                            -
                            <input type="text" class="Wdate" style="width:100px;" id="addtimeEnd"
                                   name="addtimeEnd"
                                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addtimeEnd\');}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div></br>
    <div id="batchDG"></div>
    <div id="infoDiv" style="padding: 5px;"></div>
</div>
</body>