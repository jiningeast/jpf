<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>北京中欣消金运营管理系统 </title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
	<script type="text/javascript" src="${basePath}/resources/easyui144/outlook.js"> </script>
    <style type="text/css">
    	body{
			background:#D2E0F2; 
			overflow-x : hidden;   
		    overflow-y : hidden; 
		}
    </style>
    <script type="text/javascript">
    var _menus = {
    		"basic" : [ {
    			"menuid" : "1",
    			"icon" : "icon-sys",
    			"menuname" : "系统管理",
    			"menus" : [{
    				"menuid" : "101",
    				"menuname" : "用户管理",
    				"icon" : "icon-nav",
    				"url" : "user/index"
    			},{
                    "menuid" : "102",
                    "menuname" : "角色管理",
                    "icon" : "icon-nav",
                    "url" : "roles/index"
                    },{
                    "menuid" : "103",
                    "menuname" : "系统日志",
                    "icon" : "icon-nav",
                    "url" : "systemlog/index"
                },{
                    "menuid" : "104",
                    "menuname" : "虚拟分类管理",
                    "icon" : "icon-nav",
                    "url" : "virtual/index"
                },{
                    "menuid" : "105",
                    "menuname" : "地区管理",
                    "icon" : "icon-nav",
                    "url" : "pca/index"
                },{
    			    "menuid" : "106",
                    "menuname" : "银行管理",
                    "icon" : "icon-nav",
                    "url" : "bank/index"
                }]
    		},{
    			"menuid" : "2",
    			"icon" : "icon-sys",
    			"menuname" : "商户管理",
    			"menus" : [{
    				"menuid" : "201",
    				"menuname" : "商户信息管理",
    				"icon" : "icon-nav",
    				"url" : "merchant/index"
    			},{
                    "menuid" : "202",
                    "menuname" : "商户支付配置管理",
                    "icon" : "icon-nav",
                    "url" : "merchant/paytype/index"
                },{
                    "menuid" : "203",
                    "menuname" : "门店管理",
                    "icon" : "icon-nav",
                    "url" : "merShop/index"
                }]
    		},{
                "menuid" : "3",
                "icon" : "icon-sys",
                "menuname" : "产品管理",
                "menus" : [{
                    "menuid" : "301",
                    "menuname" : "产品信息管理",
                    "icon" : "icon-nav",
                    "url" : "product/index"
                }]
            },{
                "menuid" : "4",
                "icon" : "icon-sys",
                "menuname" : "旅游分期订单管理",
                "menus" : [{
                    "menuid" : "401",
                    "menuname" : "旅游分期订单信息管理",
                    "icon" : "icon-nav",
                    "url" : "order/index"
                },{
                    "menuid" : "402",
                    "menuname" : "旅游分期退单信息管理",
                    "icon" : "icon-nav",
                    "url" : "tdorder/index"
                }]
            },{
                "menuid" : "5",
                "icon" : "icon-sys",
                "menuname" : "财务管理",
                "menus" : [{
                    "menuid" : "501",
                    "menuname" : "旅游分期退单管理",
                    "icon" : "icon-nav",
                    "url" : "orderCpsingle/index"
                },{
                    "menuid" : "501",
                    "menuname" : "平台流水管理",
                    "icon" : "icon-nav",
                    "url" : "orderCpsingle/index"
                }]
            }]
    	};

  	//设置登录窗口
    function openPwd() {
        $('#w').window({
            title: '修改密码',
            width: 300,
            modal: true,
            shadow: true,
            closed: true,
            resizable:false
        });
    }
    //关闭登录窗口
    function closePwd() {
        $('#w').window('close');
    }

    

    //修改密码
    function serverLogin() {
    	var oldpass = $('#txtOldPass').val();
        var newpass = $('#txtNewPass').val();
        var rePass = $('#txtRePass').val();

        if (newpass == '') {
        	$.messager.alert('系统提示', '请输入密码！', 'warning');
            return false;
        }
        if (rePass == '') {
        	$.messager.alert('系统提示', '请在一次输入密码！', 'warning');
            return false;
        }

        if (newpass != rePass) {
        	$.messager.alert('系统提示', '两次密码不一至！请重新输入', 'warning');
            return false;
        }

        var param = {}; 
		param["oldPwd"]=oldpass;
		param["newPwd"]=rePass;
        
        $.ajax({
			type:'post',
			url:'user/modifyPwd',
			data:param,
			dataType:'json',
			success:function(msg){
				//var jsonData = JSON.parse(msg);
				//alert(msg.retCode);
				if (msg.retCode != '0000') {
					$.messager.alert('消息提示','修改失败[' + msg.retMsg + ']!','error');
				} else {
					$('#w').window('close');
					$.messager.alert('消息提示','修改登录密码成功!','info');
				}
			},
			error:function(){
				$.messager.alert('消息提示','连接网络失败，请您检查您的网络!','error');
			}
		});
    }

    $(function() {
		//alert($('iframe').parent().css());
	
        openPwd();

        $('#editpass').click(function() {
            $('#w').window('open');
        });

        $('#btnEp').click(function() {
            serverLogin();
        })

		$('#btnCancel').click(function(){closePwd();})

        $('#loginOut').click(function() {
            $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                if (r) {
                    location.href = 'logout';
                }
            });
        })
    });
    </script>
<style>
	#css3menu li{ float:left; list-style-type:none;}
	#css3menu li a{	color:#fff; padding-right:20px;}
	#css3menu li a.active{color:yellow;}
</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
    <div data-options="region:'north'" border="false" style="overflow: hidden; height: 60px;
        background: url(${basePath}/resources/images/top.png) no-repeat right center; color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;padding-top:10px" class="head">欢迎 ${sessionScope.SESSION_OPERATORUSER.userName} <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>

        <span style="padding-left:10px; padding-top:5px; font-size: 16px; float:left; width: 385px; height: 50px">
        	<%-- <img src="${basePath}/resources/images/seller1.png" align="absmiddle" /> --%>&nbsp;&nbsp;&nbsp;&nbsp;消费金融运营管理系统
        </span>
    </div>
    <div data-options="region:'south'" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">CopyRight &copy; 2015-2016 北京中欣银嘉服务有限公司</div>
    </div>
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:180px;" id="west">
		<div id='wnav' class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
		</div>
    </div>
    <div id="mainPanle" data-options="region:'center'"  style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				<h1>欢迎使用运营管理系统!</h1>
				<h2>。。。。。。。。。。。。。。。。。。。。。。。。。。</h2>
			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 180px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                	<tr>
                        <td>原密码：</td>
                        <td><input id="txtOldPass" type="Password" class="easyui-textbox" /></td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="easyui-textbox" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="easyui-textbox" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>