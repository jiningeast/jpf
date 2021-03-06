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
                },{
                    "menuid" : "107",
                    "menuname" : "微信公众号管理",
                    "icon" : "icon-nav",
                    "url" : "weixinMp/index"

                }]
    		},{
    			"menuid" : "2",
    			"icon" : "icon-sys",
    			"menuname" : "商户管理",
    			"menus" : [{
    				"menuid" : "201",
    				"menuname" : "旅游商户管理",
    				"icon" : "icon-nav",
    				"url" : "merchant/index"
    			},{
                    "menuid" : "202",
                    "menuname" : "旅游门店管理",
                    "icon" : "icon-nav",
                    "url" : "merShop/index"
                },{
                    "menuid" : "203",
                    "menuname" : "云账户代理商户管理",
                    "icon" : "icon-nav",
                    "url" : "cloudCompany/index"
                },{
                    "menuid" : "204",
                    "menuname" : "云账户业务商户管理",
                    "icon" : "icon-nav",
                    "url" : "cloudCompany/indexSale"
                },{
                    "menuid" : "205",
                    "menuname" : "欣豆市场商户管理",
                    "icon" : "icon-nav",
                    "url" : "shopCompany/index"
                },{
                    "menuid" : "206",
                    "menuname" : "充值平台商户管理",
                    "icon" : "icon-nav",
                    "url" : "chargeCompany/index"
                }]
    		},{
                "menuid" : "3",
                "icon" : "icon-sys",
                "menuname" : "产品管理",
                "menus" : [{
                    "menuid" : "301",
                    "menuname" : "旅游产品管理",
                    "icon" : "icon-nav",
                    "url" : "product/index"
                },{
                    "menuid" : "302",
                    "menuname" : "欣豆市场产品管理",
                    "icon" : "icon-nav",
                    "url" : "shopproduct/index"
                },{
                    "menuid" : "303",
                    "menuname" : "欣豆市场欣券面值管理",
                    "icon" : "icon-nav",
                    "url" : "shopCouponMoneyType/index"
                },{
                    "menuid" : "304",
                    "menuname" : "充值平台产品管理",
                    "icon" : "icon-nav",
                    "url" : "chargeProduct/index"
                },{
                    "menuid" : "304",
                    "menuname" : "欣豆市场基础信息管理",
                    "icon" : "icon-nav",
                    "url" : "shopproductinfo/index"
                }
                ]
            },{
                "menuid" : "4",
                "icon" : "icon-sys",
                "menuname" : "订单管理",
                "menus" : [{
                    "menuid" : "401",
                    "menuname" : "旅游订单管理",
                    "icon" : "icon-nav",
                    "url" : "order/index"
                },{
                    "menuid" : "402",
                    "menuname" : "欣豆市场订单管理",
                    "icon" : "icon-nav",
                    "url" : "shopOrder/index"
                },{
                    "menuid" : "403",
                    "menuname" : "欣豆转让订单管理",
                    "icon" : "icon-nav",
                    "url" : "shopBargainOrder/index"
                },{
                    "menuid" : "404",
                    "menuname" : "敬恒充值订单管理",
                    "icon" : "icon-nav",
                    "url" : "shopBargainRechargeOrder/index"
                },{
                    "menuid" : "405",
                    "menuname" : "充值平台订单管理",
                    "icon" : "icon-nav",
                    "url" : "chargeOrder/index"
                },{
                    "menuid" : "406",
                    "menuname" : "欣豆市场中欣卡订单管理",
                    "icon" : "icon-nav",
                    "url" : "shopJoiestCard/index"
                }]
            },{
                "menuid" : "5",
                "icon" : "icon-sys",
                "menuname" : "运营管理",
                "menus" : [{
                    "menuid" : "501",
                    "menuname" : "旅游退单管理",
                    "icon" : "icon-nav",
                    "url" : "tdorder/index"
                },{
                    "menuid" : "502",
                    "menuname" : "云账户充值管理",
                    "icon" : "icon-nav",
                    "url" : "cloudRecharge/index"
                },{
                    "menuid" : "503",
                    "menuname" : "云账户代付任务",
                    "icon" : "icon-nav",
                    "url" : "cloudTask/index"
                },{
                    "menuid" : "504",
                    "menuname" : "云账户冻结管理",
                    "icon" : "icon-nav",
                    "url" : "cloudDfMoneyFreeze/index"
                },{
                    "menuid" : "505",
                    "menuname" : "欣豆市场充值管理",
                    "icon" : "icon-nav",
                    "url" : "shopCompanyCharge/index"
                },{
                    "menuid" : "506",
                    "menuname" : "欣豆市场欣券管理",
                    "icon" : "icon-nav",
                    "url" : "shopBatch/index"
                },{
                    "menuid" : "507",
                    "menuname" : "欣豆市场采购管理",
                    "icon" : "icon-nav",
                    "url" : "shopStockOrder/index"
                },{
                    "menuid" : "508",
                    "menuname" : "欣豆市场广告管理",
                    "icon" : "icon-nav",
                    "url" : "shopAd/index"
                },{
                    "menuid" : "509",
                    "menuname" : "欣豆市场收购管理",
                    "icon" : "icon-nav",
                    "url" : "shopBargainRequest/index"
                },{
                    "menuid" : "510",
                    "menuname" : "充值平台充值管理",
                    "icon" : "icon-nav",
                    "url" : "chargeCompanyCharge/index"
                },{
                    "menuid" : "511",
                    "menuname" : "充值平台余额管理",
                    "icon" : "icon-nav",
                    "url" : "chargeBalance/index"
                },{
                    "menuid" : "512",
                    "menuname" : "充值平台流水管理",
                    "icon" : "icon-nav",
                    "url" : "chargeMoneyStream/index"
                }]
            },{
                    "menuid" : "6",
                    "icon" : "icon-sys",
                    "menuname" : "财务管理",
                    "menus" : [{
                        "menuid" : "601",
                        "menuname" : "聚合通道订单管理",
                        "icon" : "icon-nav",
                        "url" : "orders/index"
                    },{
                        "menuid" : "602",
                        "menuname" : "旅游退单审核",
                        "icon" : "icon-nav",
                        "url" : "orderCpsingle/index"
                    },{
                        "menuid" : "603",
                        "menuname" : "云账户充值审核",
                        "icon" : "icon-nav",
                        "url" : "cloudRecharge/caiwuIndex"
                    },{
                        "menuid" : "604",
                        "menuname" : "云账户代付到账",
                        "icon" : "icon-nav",
                        "url" : "cloudCompanyMoney/caiwu/index"
                    },{
                        "menuid" : "605",
                        "menuname" : "云账户解冻审核",
                        "icon" : "icon-nav",
                        "url" : "cloudDfMoneyFreeze/caiwu/index"
                    },{
                        "menuid" : "606",
                        "menuname" : "欣豆市场充值审核",
                        "icon" : "icon-nav",
                        "url" : "shopCompanyCharge/caiwuIndex"
                    },{
                        "menuid" : "607",
                        "menuname" : "欣豆市场采购审核",
                        "icon" : "icon-nav",
                        "url" : "shopStockOrder/indexCaiwu"
                    },{
                        "menuid" : "608",
                        "menuname" : "欣豆市场转让审核",
                        "icon" : "icon-nav",
                        "url" : "shopBargainOrder/indexCaiwu"
                    },{
                        "menuid" : "612",
                        "menuname" : "欣豆市场确认收入",
                        "icon" : "icon-nav",
                        "url" : "shopIncomeConfirm/index"
                    },{
                        "menuid" : "609",
                        "menuname" : "充值平台充值审核",
                        "icon" : "icon-nav",
                        "url" : "chargeCompanyCharge/indexCaiwu"
                    },{
                        "menuid" : "610",
                        "menuname" : "充值平台财务审核",
                        "icon" : "icon-nav",
                        "url" : "chargeOrder/caiwuIndex"
                    },{
                        "menuid" : "611",
                        "menuname" : "欧飞财务资金流水",
                        "icon" : "icon-nav",
                        "url" : "ofpay/index"
                    }]
            },{
                "menuid" : "7",
                "icon" : "icon-sys",
                "menuname" : "客服管理",
                "menus" : [{
                    "menuid" : "701",
                    "menuname" : "官网登记列表",
                    "icon" : "icon-nav",
                    "url" : "cloudFansource/index"
                }]
            },{
                "menuid" : "8",
                "icon" : "icon-sys",
                "menuname" : "客户管理",
                "menus" : [{
                    "menuid" : "801",
                    "menuname" : "欣券客户管理",
                    "icon" : "icon-nav",
                    "url" : "shopCustomer/index"
                },{
                        "menuid" : "802",
                        "menuname" : "云账户用户限额管理",
                        "icon" : "icon-nav",
                        "url" : "cloudStaffMonthTotal/index"
                    }]
            },{
                "menuid" : "9",
                "icon" : "icon-sys",
                "menuname" : "统计管理",
                "menus" : [{
                    "menuid" : "901",
                    "menuname" : "欣豆市场进销存统计",
                    "icon" : "icon-nav",
                    "url" : "shopStockLog/index"
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
					$.messager.alert('消息提示','修改登录密码成功!','list');
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
        <span style="float:right; padding-right:20px;padding-top:10px" class="head">欢迎 ${sessionScope["manage-userInfo"].userName} <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>

        <span style="padding-left:10px; padding-top:5px; font-size: 16px; float:left; width: 385px; height: 50px">
        	<%-- <img src="${basePath}/resources/images/seller1.png" align="absmiddle" /> --%>&nbsp;&nbsp;&nbsp;&nbsp;消费金融运营管理系统
        </span>
    </div>
    <div data-options="region:'south'" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">CopyRight &copy; 2015-2016 北京中欣银嘉服务有限公司</div>
    </div>
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:200px;" id="west">
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