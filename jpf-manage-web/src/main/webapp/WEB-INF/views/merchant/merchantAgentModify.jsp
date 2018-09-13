<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商户信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
</head>
<body>
<!-- 添加弹出窗口 -->
    <div class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form id="editForm" method="post">
                <input type="hidden" id="id_m" name="mtsid" value="${merchantInfo.id}">
                <input type="hidden" id="id_a" name="id" value="${merchantAgentInfo.id}">
                <table cellpadding=3 class="table table-bordered">
                    <tr>
                        <th>代理信息</th>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户号：</td>
                        <td>
                            ${merchantInfo.merchNo}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">用户名：</td>
                        <td>
                            ${merchantInfo.username}
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">聚合商户名称：</td>
                        <td style="width: 20%">
                            ${merchantInfo.merchName}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;background-color: #f1f1f1;">代理等级：</td>
                        <input type="hidden" name="tpid_curr" value="${merchantAgentInfo.tpid}">
                        <td>
                            <select id="tpid" name="tpid" class="easyui-combobox" data-options="required:true" style="width:100px;">
                            </select>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">添加时间：</td>
                        <td>
                            <fmt:formatDate value="${merchantAgentInfo.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td style="text-align: right;background-color: #f1f1f1;">更新时间：</td>
                        <td>
                            <fmt:formatDate value="${merchantAgentInfo.updated}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr class="level1 trlevel">
                        <td style="text-align: right;background-color: #f1f1f1;">一级代理商户：</td>
                        <td colspan="5" id="level1" class="level" data-options="required:true">
                            <div class="div-level">
                            </div>
                        </td>
                    </tr>
                    <tr class="level2 trlevel">
                        <td style="text-align: right;background-color: #f1f1f1;">二级代理商户：</td>
                        <td colspan="5" id="level2" class="level">
                            <div class="div-level">
                            </div>
                        </td>
                    </tr>
                    <tr class="level3 trlevel">
                        <td style="text-align: right;background-color: #f1f1f1;">三级代理商户：</td>
                        <td colspan="5" id="level3" class="level">
                            <div class="div-level">
                            </div>
                        </td>
                    </tr>

                </table>
            </form>
        </div>
        <div region="south" border="false"
             style="text-align: right; height: 30px; line-height: 30px;">
            <a id="saveBtn_m" class="easyui-linkbutton" icon="icon-ok"
               href="javascript:void(0)">确定</a>
            <a id="cancelBtn_m" class="easyui-linkbutton" icon="icon-cancel"
               href="javascript:void(0)">取消</a>
        </div>
    </div>
<!-- /添加弹出窗口 -->
<style>
    /*.trLevel{display: none}*/
    td.level{
        height:200px;
        width: 85%;
    }
    .div-level{
        height: 200px;
        overflow-y: auto;
        width: 100%;
    }
    .div-level span label{
        margin-left: 5px;
    }
    .level span{
        display: inline-block;
        height: 30px;
        width:24%;
        word-break: keep-all;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
    }
</style>
<script>
    function initData() {
        $('#tpid').combobox('select', '${merchantAgentInfo.tpid}');
    }

    $(function () {
        var levelArr = new Array();
        levelArr[2] = "1级";
        levelArr[3] = "2级";
        levelArr[4] = "3级";

        //隐藏所有
        $("tr.trlevel").hide();

        //等级信息
        $('#editForm #tpid').combobox({
            url:'../param/getType?pid=1',
            valueField:'catid',
            textField:'cat',
            onChange: function (n,o) {
                //选中的等级
                var tpid = $("input[name='tpid']").val();
                //去除所有选中属性
                $("tr.trlevel input[type='radio']").removeAttr("checked");
                if ( tpid )
                {
                    if ( tpid == 3 )
                    {
                        //当前2级，获取1级商户
                        $("tr.level2,tr.level3").hide();
                        getAgentInfo(2 ,$('tr.level1'));
                    }else if ( tpid == 4 )
                    {
                        //当前3级，获取2级商户
                        $("tr.level1,tr.level3").hide();
                        getAgentInfo(3, $("tr.level2"));
                    } else if ( tpid == 2 )
                    {
                        //当前1级，隐藏所有商户
                        $("tr.trlevel").hide();
                    }
                }
            }
        });

        //tpid 要获取的等级
        function getAgentInfo(tpid, obj){
            $.ajax({
                url : '../param/getAgentInfo?tpid=' + tpid,
                dataType : 'json',
                type: 'post',
                success : function (data) {
                    if ( !data )
                    {
                        var msg = '';
                        if ( tpid == 2 )
                        {
                            msg = "暂无" + levelArr[tpid] + "代理商户，请先设置1级代理商户";
                        }else if ( tpid == 3 )
                        {
                            msg = "暂无" + levelArr[tpid] + "代理商户，请先设置2级代理商户";
                        }
                        $.messager.alert('消息提示', msg);
                        return false;
                    }
                    var prevPath = '${merchantAgentInfo.superiorid}';   //当前path
                    var curr_tpid = '${merchantAgentInfo.tpid}';   //当前商户代理等级
                    var prevMtsid = 0;
                    //当前等级的上级商户默认选中
                    if ( curr_tpid == tpid + 1 )
                    {
                        //编辑 当前上级商户
                        prevPath = prevPath.substring(0,prevPath.length-1);
                        var str = prevPath.split(':');
                        if ( tpid == 2 )
                        {
                            //一级代理
                            prevMtsid = str[0];
                        }else if ( tpid == 3 )
                        {
                            prevMtsid = str[1];
                        }
                    }
                    if ( data )
                    {
                        var html = "";
                        //降级操作时：此等级只有1个且为当前商户时
                        if ( data.length == 1 && tpid == '${merchantAgentInfo.tpid}' )
                        {
                            $.messager.alert('消息提示', "请先设置其它" + levelArr[tpid] + "代理商户");
                            return false;
                        }
                        for ( var i=0; i<data.length; i++)
                        {
                            //代理等级 降级操作时 ： 商户列表过滤掉其本身
                            //当前代理等级 = 要获取的等级
                            if ( curr_tpid == tpid  )
                            {
                                if ( data[i]['id'] == '${merchantInfo.id}' )
                                {
                                    continue;
                                }
                            }
                            //上级商户默认选中
                            var str_check = "";
                            if ( prevMtsid )
                            {
                                if ( prevMtsid == data[i]['id'] )
                                {
                                    str_check = "checked=checked";
                                }
                            }
                            html += "<span>";
                            html += "<input class=\"easyui-combobox combox_style\" data-options=\"required:true\" type='radio' " + str_check + " value='" + data[i]['id']  + "' name='mtsidBelong' id='"+ data[i]['id']+"' /><label for='" + data[i]['id'] + "' title='" + data[i]['merchName'] + "'> " + data[i]['merchName'] + "</label>";
                            html += "</span>";
                        }
                        $(obj).find("td.level div.div-level").empty();
                        $(obj).find("td.level div.div-level").append(html);
                        $(obj).show();
                    }
                }
            });
        }

        // $.ajax({
        //     url : '../param/getAgentInfo?tpid=2',
        //     dataType : 'json',
        //     type: 'post',
        //     success : function (data) {
        //         console.log(data)
        //     }
        // });

        //必须延迟加载，因为easyui没有渲染完，执行就会抛出错误。TypeError: $.data(...) is undefined。试过js执行顺序也不可以。
        setTimeout("initData()", 500);
        // initData();

        $("#saveBtn_m").linkbutton({
            onClick: function () {

                var isValid = $("#editForm").form('enableValidation').form('validate');
                if (!isValid) {
                    return;
                }
                var tpid = $("input[name='tpid']").val();
                var mtsidBelong = $("input[name='mtsidBelong']:checked").val();

                if ( tpid != 2 )
                {
                    //排除1级的情况
                    var mtsidBelong = $("input[name='mtsidBelong']:checked").val();
                    if ( !mtsidBelong )
                    {
                        $.messager.alert('消息提示', "请选择" + levelArr[tpid-1] + "代理商户");
                        return false;
                    }
                }
                var queryArray = $('#editForm').serializeArray();
                var postData = parsePostData(queryArray);

                $.ajax({
                    type: 'post',
                    url: '../merchant/agent/modify/action',
                    data: postData,
                    dataType: 'json',
                    success: function (msg) {
                        if (msg.retCode != '0000') {
                            $.messager.alert('消息提示', '操作失败[' + msg.retMsg + ']！', 'error');
                        } else {
                            $.messager.alert('消息提示', '操作成功！', 'info');
                            $('#infoDiv').window('close');
                            $('#dg').datagrid('reload');
                        }
                    },
                    error: function () {
                        $.messager.alert('消息提示', '连接网络失败，请您检查您的网络!', 'error');
                    }
                });
            }
        });

        $('#cancelBtn_m').linkbutton({
            onClick: function(){
                $('#infoDiv').window('close');
            }
        });
    })
</script>
</body>
</html>
