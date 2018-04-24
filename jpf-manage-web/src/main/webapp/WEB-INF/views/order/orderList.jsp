<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPEhtml>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单列表</title>
    <%@ include file="/WEB-INF/views/common/header_js.jsp" %>
    <style>
        td,th{ text-align: center; }
    </style>
    <script>
        $(function () {
            // 支付方式中文字段
            var payTypeArr = new Array();
            $.post("../param/getType",{'pid':'5'},function (res) {
                // console.log(res);
                var res = JSON.stringify(res);
                var newKsy;
                var newValue;
                JSON.parse(res,function(key, value){
                    if ( key == 'catid' ) {
                        newKsy = value;
                    }
                    if ( key == 'cat' ) {
                        newValue = value;
                    }
                    payTypeArr[newKsy] = String(newValue);
                });
                console.log(payTypeArr);
            })

            $("#dg").datagrid({
                title:'订单列表',
                url:'list',
                pagination:true,
                singleSelect:true,
                multiselect:false,
                electOnCheck:true,
                columns:[[
                    {field:'id',title:'ID',width:'3%'},
                    {field:'orderid',title:'订单ID',width:'9%'},
                    {field:'mtsid',title:'商户ID',width:'3%'},
                    {field:'uid',title:'用户uid',width:'3%'},
                    {field:'pid',title:'商品ID',width:'3%'},
                    {field:'paytype',title:'支付方式',width:'7%',
                        formatter:function (value, row, index) {
                            return payTypeArr[value];
                        }},
                    {field:'orderprice',title:'实付',width:'5%', formatter:formatPrice},
                    {field:'orderselprice',title:'实际金额',width:'5%', formatter:formatPrice},
                    {field:'ordernum',title:'商品数量',width:'5%'},
                    {field:'ordername',title:'分期信息',width:'30%', formatter:formatJSON},
                    {field:'orderstatus',title:'支付状态',width:'5%',
                        formatter:function (value, row, index) {
                            if ( value == 0 ){
                                return '未支付';
                            }else if ( value == 1 ){
                                return '支付成功';
                            }else if ( value == 2 ){
                                return '支付失败';
                            }
                        }},
                    {field:'singlestatus',title:'退单状态',width:'8%',
                        formatter:function (value, row, index) {
                            if ( value == 1 ){
                                return '正常订单';
                            }else if ( value == 2 ){
                                return '<span style="color: green">用户申请退单</span>';
                            }else if ( value == 3 ){
                                return '用户撤销退款';
                            }else if ( value == 4 ){
                                return '<span style="color: green">待审核</span>';
                            }else if ( value == 5 ){
                                return '<span style="color: blue">退单处理完毕</span>';
                            }else if ( value == 6 ){
                                return '<span style="color: green">审核驳回</span>';
                            }
                        }},
                    {field:'paytime',title:'支付时间',width:'9%',formatter: formatDateStr},
                    {field:'addtime',title:'添加时间',width:'9%',formatter: formatDateStr},
                    {field:'updatetime',title:'修改时间',width:'9%',formatter: formatDateStr}
                ]]
            })
        })
    </script>
</head>
<body>
<%--`id` bigint(11) NOT NULL COMMENT '自增ID',
`orderid` bigint(20) unsigned NOT NULL COMMENT '订单ID',
`mtsid` bigint(11) DEFAULT NULL COMMENT '商户ID',
`uid` bigint(10) NOT NULL COMMENT '用户uid',
`pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品ID',
`paytype` tinyint(1) NOT NULL DEFAULT '7' COMMENT '支付方式：pay_merchants_type ',
`orderPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单实际缴纳金额',
`orderselprice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单实际金额',
`ordernum` int(10) NOT NULL DEFAULT '1' COMMENT '订单商品数量',
`ordername` text NOT NULL COMMENT '分期付款序列（json格式）',
`paytime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '支付时间',
`orderstatus` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:未支付；1:支付成功；2:支付失败',
`singlestatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:正常订单；2:退单处理；3:退款撤销；4:退单受理中，5:退单处理完毕',
`addtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
`updatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',--%>
    <table id="dg"></table>
</body>
</html>
