<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>代理公司管理</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>

    <script>
        $(function() {
            $('#infoDiv').window({
                title:'详情',
                width:'80%',
                height:'500px',
                closed:true,
                modal:true,
            //maximized:true,//弹出窗口最大化
            });
            var toolbar = [
                {
                    text : '自定义菜单',
                    iconCls : 'icon-save',
                    id:"customMenue",
                    /*handler : function(){

                        /!*var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return +rows[0].id
                        }*!/
                        window.open('weixinMp/customMenu?id=1');
                       // location.href ='weixinMp/customMenu?id=1';
                        //$("#infoDiv").window("open").window('refresh', '../weixinMp/customMenu?id=1').window('setTitle','自定义菜单管理');
                    }*/
                },
                {
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function(){
                        $("#infoDiv").window("open").window('refresh', '../weixinMp/addpage').window('setTitle','新增');
                    }
                },
                {
                    text:'编辑',
                    iconCls:'icon-edit',
                    handler:function(){
                        var rows = $('#dg').datagrid('getSelections');
                        if (rows.length != 1) {
                            $.messager.alert('消息提示','请选择一条数据！','info');
                            return
                        }
                        $('#infoDiv').window("open").window('refresh', '../weixinMp/editpage?id='+rows[0].id).window('setTitle','编辑');
                    }
                }
            ];

            $('#dg').datagrid({
                title:'微信公众号列表',
                toolbar:toolbar,
                // rownumbers:true,//如果为true，则显示一个行号列。
                pagination:true,//如果为true，则在DataGrid控件底部显示分页工具栏。
                // fitColumns:true,//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                singleSelect:true,
                multiselect:true,
                selectOnCheck:true,
                remoteSort: false, // 服务端排序
                // width:500,
                url:'list',
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'name',title:'公众号名称',width:'10%'},
                    {field:'followqr',title:'二维码',width:'8%',
                        formatter : function(value,row,index){
                           return "<img  style='with:40px;height:40px;'src="+value+" >";
                        }
                    },
                    {field:'encrypt',title:'令牌密钥',width:'10%'},
                    {field:'token',title:'令牌token',width:'10%'},
                    {field:'appid',title:'开发者ID',width:'8%'},
                    {field:'appsecret',title:'开发者密码',width:'10%'},
                    {field:'merchant',title:'商户号',width:'10%'},
                    {field:'merkey',title:'商户key',width:'10%'},
                    {field:'followreply',title:'关注回复',width:'10%'},
                    {field:'created',title:'创建时间',width:'10%',formatter: formatDateStr},
                    {field:'updated',title:'修改时间',width:'10%',formatter: formatDateStr},

                ]]
            });

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

        function get_time() {
            return new Date().getTime();
        }
    </script>
    <style>
        #searchForm table tr td:nth-child(odd) { text-align: right; }
        #searchForm table tr td:nth-child(even) { text-align: left; }
    </style>
</head>
<body>
<div name="contentDiv" style="width:1418px">
    <div id="formDiv" class="easyui-panel" title="搜索条件" data-options="footer:'#ft'">
        <div style="padding:10px 60px 20px 60px">
            <form id="searchForm" method="post">
                <table cellpadding="5" width="75%">
                    <tr>
                        <td>公众号名称:</td>
                        <td><input id="name" name="name" class="easyui-textbox" type="text" /></td>
                        <td>添加起止时间:</td>
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
    </div>
    <div id="ft" style="padding:5px;">
        <a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;
        <a id="searchRestBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
    </div>
    <br/>
    <table id="dg"></table>
</div>
<div id="infoDiv"></div>
<div id="edit"></div>
</body>
<script type="text/javascript" src="${basePath}/resources/js/dialog.js"> </script>
<%--
<script type="text/javascript" src="${basePath}/resources/easyui144/outlook.js?ts=234"> </script>
--%>

<script>
    function addTab(subtitle, url, icon) {

        if (!parent.$('#tabs').tabs('exists', subtitle)) {

            parent.$('#tabs').tabs('add', {
                title : subtitle,
                content : createFrame(url),
                closable : true,
                icon : icon
            });

        } else {

            parent.$('#tabs').tabs('select', subtitle);
            parent.$('#mm-tabupdate').click();
        }
        //alert("aaa");
        parent.$('#tabs').tabs('getSelected').css("overflow-y","hidden");
        var width = parent.$("div[name='formDiv']").css("width") - 10;
        parent.$("div[name='formDiv']").css("width", width);

        tabClose();
    }
    function tabClose() {

        /* 双击关闭TAB选项卡 */
        parent.$(".tabs-inner").dblclick(function() {
            var subtitle = parent.$(this).children(".tabs-closable").text();
            parent.$('#tabs').tabs('close', subtitle);
        });
        /* 为选项卡绑定右键 */
        parent.$(".tabs-inner").bind('contextmenu', function(e) {
            parent.$('#mm').menu('show', {
                left : e.pageX,
                top : e.pageY
            });
            var subtitle = parent.$(this).children(".tabs-closable").text();
            parent.$('#mm').data("currtab", subtitle);
            parent.$('#tabs').tabs('select', subtitle);
            return false;
        });
    }
    function createFrame(url) {

        var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
        return s;
    }
    $(function(){

        $(document).on("click","#customMenue",function () {

            var tabTitle = "自定义菜单";
            var url = 'weixinMp/customMenu?id=1';
            var icon = "icon icon-nav";//getIcon(menuid, icon);
            addTab(tabTitle, url, icon);
            //$('#wnav li div').removeClass("selected");
            //$(this).parent().addClass("selected");
            /*console.dir(11111111)

            var remarkDialog = dialog({
                title: '自定义菜单',
                width: 650,
                height: 600,
            })//.showModal();

            $.ajax({
                type:"get",
                url:"../weixinMp/customMenu?id=1",
                dataType:"json",
                data:{"a":"234"},
                success:function(re){
                    console.dir(re);
                    console.dir(2222222222222222)

                   remarkDialog.content(re).show();
                },
                error:function(er,co,rt){
                    console.dir(er);
                    console.dir(co);
                    console.dir(rt);
                    console.dir(44444444444);
                }

            })*/
        })
    })
</script>
</html>