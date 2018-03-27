/**
 * Created by mzq on 2017/12/7.
 */

var tranCodeArray = {'0101':'B2C网银支付','0102':'B2C网银退款','0201':'快捷支付','0202':'快捷退款',
    '0301':'协议支付','0401':'B2B网银支付','0402':'B2B网银退款','0501':'代付','0601':'代扣','0701':'实名认证','0901':'融合支付','0902':'融合支付退款'}

var tranCodeJsonStr = [{ 'tranCodeName':'','tranCodeText':'全部',"selected":"true"},
{'tranCodeName':'0101','tranCodeText':'B2C网银支付'},
{'tranCodeName':'0102','tranCodeText':'B2C网银退款'},
{'tranCodeName':'0201','tranCodeText':'快捷支付'},
{'tranCodeName':'0202','tranCodeText':'快捷退款'},
{'tranCodeName':'0301','tranCodeText':'协议支付'},
{'tranCodeName':'0401','tranCodeText':'B2B网银支付'},
{'tranCodeName':'0402','tranCodeText':'B2B网银退款'},
{'tranCodeName':'0501','tranCodeText':'代付'},
{'tranCodeName':'0601','tranCodeText':'代扣'},
{'tranCodeName':'0701','tranCodeText':'实名认证'},
{'tranCodeName':'0901','tranCodeText':'融合支付'},
{'tranCodeName':'0902','tranCodeText':'融合支付退款'}
];

//交易类型
var tranTypeArray = {'01':'B2C网银支付','02':'快捷支付','03':'协议支付','04':'B2B网银支付','05':'代付','06':'代扣','07':'实名认证','09':'融合支付'};

//交易属性
var tranAttrArray = {'DEBIT':'借记卡','CREDIT':'贷记卡','PRIVATE':'对私','PUBLIC':'对公','MICROPAY':'被扫','JSAPI':'公众号','NATIVE':'主扫','APP':'APP','WAP':'WAP','H5':'H5'};

//费率类型
var rateTypeArray = {"01":"每笔固定(元)","02":"每笔比率(千分比)"};

//结算周期
var settleCycleArray = {"T0":"T0","T1":"T1","D0":"D0","D1":"D1"};

//审核状态
var checkStatusArray = {"01":"未审核","02":"审核通过","03":"审核拒绝"};

//费率状态
var rateOpertypeArray = {"01":"设置","02":"删除"};
//交易类型json easyui combobox
function getTranTypeJsonStr(tranTypeTextNullText){
    var tranTypeJsonStr = [
        {'tranTypeName':'','tranTypeText':tranTypeTextNullText,"selected":true},
        {'tranTypeName':'01','tranTypeText':'B2C网银支付'},
        {'tranTypeName':'02','tranTypeText':'快捷支付'},
        {'tranTypeName':'03','tranTypeText':'协议支付'},
        {'tranTypeName':'04','tranTypeText':'B2B网银支付'},
        {'tranTypeName':'05','tranTypeText':'代付'},
        {'tranTypeName':'06','tranTypeText':'代扣'},
        {'tranTypeName':'07','tranTypeText':'实名认证'},
        // {'tranTypeName':'08','tranTypeText':''},
        {'tranTypeName':'09','tranTypeText':'融合支付'}];
    return tranTypeJsonStr;
}

//交易属性json easyui combobox
function getTranAttrJsonStr(tranTypeName,tranTypeTextNullText){
    var tranAttrJsonStr = "";
    if(tranTypeName==""||tranTypeName==undefined){
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
        ];
    }else if(tranTypeName=="01"){//B2C网银支付
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
            {'tranAttrName':'DEBIT','tranAttrText':'借记卡'},
            {'tranAttrName':'CREDIT','tranAttrText':'贷记卡'},
            ];
    }else if(tranTypeName=="02"){//快捷支付
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
            {'tranAttrName':'DEBIT','tranAttrText':'借记卡'},
            {'tranAttrName':'CREDIT','tranAttrText':'贷记卡'},
            ];
    }else if(tranTypeName=="03"){//协议支付
        tranAttrJsonStr = "";
    }else if(tranTypeName=="04"){//'B2B网银支付
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
            {'tranAttrName':'PUBLIC','tranAttrText':'对公'}
            ];
    }else if(tranTypeName=="05"){//代付
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
            {'tranAttrName':'PRIVATE','tranAttrText':'对私'},
            {'tranAttrName':'PUBLIC','tranAttrText':'对公'}];
    }else if(tranTypeName=="06"){//代扣
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
            {'tranAttrName':'DEBIT','tranAttrText':'借记卡'},
            {'tranAttrName':'CREDIT','tranAttrText':'贷记卡'},
        ];
    }else if(tranTypeName=="07"){//实名认证
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
            {'tranAttrName':'DEBIT','tranAttrText':'借记卡'},
            {'tranAttrName':'CREDIT','tranAttrText':'贷记卡'}];
    }else if(tranTypeName=="08"){//
        tranAttrJsonStr = ""
    }else if(tranTypeName=="09"){//融合支付
        tranAttrJsonStr = [
            {'tranAttrName':'','tranAttrText':tranTypeTextNullText,'selected':true},
            {'tranAttrName':'MICROPAY','tranAttrText':'被扫'},
            {'tranAttrName':'JSAPI','tranAttrText':'公众号'},
            {'tranAttrName':'NATIVE','tranAttrText':'主扫'},
            {'tranAttrName':'APP','tranAttrText':'APP'},
            {'tranAttrName':'WAP','tranAttrText':'WAP'},
            {'tranAttrName':'H5','tranAttrText':'H5'}];
    }
    return tranAttrJsonStr;
}