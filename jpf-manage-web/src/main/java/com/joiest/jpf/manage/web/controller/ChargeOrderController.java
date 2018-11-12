package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chargeOrder")
public class ChargeOrderController {

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "chargeOrder/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetChargeOrderRequest request){
        GetChargeOrderResponse response = chargeOrderServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return  map;
    }

    @RequestMapping("caiwuIndex")
    public String caiwuIndex(){
        return "chargeOrder/caiwuList";
    }

    /**
     * 运营申请退款
     * */
    @RequestMapping("caiwuList")
    @ResponseBody
    public Map<String,Object> caiwuList(GetChargeOrderRequest request){
        GetChargeOrderResponse response = chargeOrderServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return  map;
    }

    @RequestMapping("applyTuikuan")
    @ResponseBody
    public JpfResponseDto applyTuikuan(PayChargeOrder request, HttpSession httpSession){
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(request);
        if( chargeOrderInfo == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到该数据");
        }
        // 上游充值失败=3
        if( chargeOrderInfo.getStatus() != 3 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无法申请退款");
        }
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);

        ChargeOrderInfo orderInfo = new ChargeOrderInfo();
        //申请退款
        orderInfo.setStatus((byte)4);
        orderInfo.setId(chargeOrderInfo.getId());
        orderInfo.setCheckId(userInfo.getId().toString());
        orderInfo.setCheckName(userInfo.getUserName());
        int count  = chargeOrderServiceFacade.upOrderInfo(orderInfo);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }

        return  new JpfResponseDto();
    }

    /**
    * 财务审核退款
    *
    * */
    @RequestMapping("auditTuikuan")
    @ResponseBody
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto auditTuikuan(PayChargeOrder request, HttpSession httpSession){
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(request);
        if( chargeOrderInfo == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到该数据");
        }
        // 申请退款=4
        if( chargeOrderInfo.getStatus() != 4 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无法申请退款");
        }
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);

        //操作标识
        int ret = 0;

        ChargeOrderInfo orderInfo = new ChargeOrderInfo();
        //退款成功
        orderInfo.setStatus((byte)5);
        orderInfo.setId(chargeOrderInfo.getId());
        orderInfo.setCheckId(userInfo.getId().toString());
        orderInfo.setCheckName(userInfo.getUserName());
        int count  = chargeOrderServiceFacade.upOrderInfo(orderInfo);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }else{
            ChargeCompanyInfo companyInfo = new ChargeCompanyInfo();
            String companyId = chargeOrderInfo.getCompanyId();
            companyInfo.setId(companyId);
            ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(companyId);
            if( chargeCompanyInfo == null ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到商户信息");
            }
            if( chargeCompanyInfo.getIsFreeze() == 1 || chargeCompanyInfo.getIsDel()==1 ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "账户冻结或关闭，请联系管理员");
            }



                BigDecimal preMoney  = chargeCompanyInfo.getMoney();
                String preCode = chargeCompanyInfo.getMoneyCode();
                //退款金额
                BigDecimal money = chargeOrderInfo.getTotalMoney();
                String keyStr = ConfigUtil.getValue("MERCH_VALIDE_CODE");
                Boolean flag = ToolUtils.ValidateCode(preCode,companyId,preMoney.toString(),keyStr);
                if( !flag ){
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "金额校验失败");
                }
                //开始退款
                BigDecimal afterMoney = preMoney.add(money);
                String newCode = ToolUtils.CreateCode(afterMoney.toString(),companyId,keyStr);

                PayChargeCompany chargeCompany = new PayChargeCompany();
                chargeCompany.setId(companyId);
                chargeCompany.setMoney(afterMoney);
                chargeCompany.setMoneyCode(newCode);
                JpfResponseDto jpfResponseDto = chargeCompanyServiceFacade.updateCompanyRecord(chargeCompany);
                if(jpfResponseDto == null || !jpfResponseDto.getRetCode().equals("0000") ){
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
                }else{
                    BigDecimal zeroNum = new BigDecimal("0");
                    Date curretDate = new Date();
                    PayChargeCompanyMoneyStream streamData = new PayChargeCompanyMoneyStream();
                    streamData.setStreamNo("MS"+ToolUtils.createOrderid());//流水号
                    streamData.setCompanyId(companyId);//商户id
                    streamData.setCompanyName(chargeCompanyInfo.getCompanyName());//商户名称
                    streamData.setMerchNo(chargeCompanyInfo.getMerchNo());//商户号
                    streamData.setOrderId(chargeOrderInfo.getId());//订单id 可能是消费订单、充值订单、退款订单
                    streamData.setOrderNo(chargeOrderInfo.getOrderNo()); // 订单号可能是消费订单、充值订单、退款订单
                    streamData.setProductId(chargeOrderInfo.getProductId());//产品Id
                    streamData.setProductName(chargeOrderInfo.getProductName());//产品名称

                    streamData.setProductValue(chargeOrderInfo.getProductValue()); //产品面值
                    streamData.setProductBidPrice(chargeOrderInfo.getProductBidPrice());//产品成本价
                    streamData.setProductSalePrice(chargeOrderInfo.getProductPrice());//产品标准售价 默认null
                    streamData.setProductInterfacePrice(chargeOrderInfo.getProductBidPrice());//产品接口价同成本价
                    streamData.setProductAmount(chargeOrderInfo.getProductAmount());//产品数量
                    streamData.setTotalMoney(chargeOrderInfo.getTotalMoney());//总价

                    streamData.setInterfaceType(chargeOrderInfo.getInterfaceType());//接口类型 0=欧非 1=威能 默认null
                    streamData.setInterfaceOrderNo(chargeOrderInfo.getInterfaceOrderNo());//接口订单号 默认null
                    streamData.setStatus((byte)3);//流水类型 1=充值 2=下单 3=退款
                    streamData.setStreamType((byte)0);//流水类型 0=收入 1=支出
                    streamData.setNewMoney(afterMoney);//变动后的余额
                    streamData.setMemo("");//流水备注
                    streamData.setIsDel((byte)0);//删除标记 0=未删除 1=已删除
                    int streamCou = chargeCompanyMoneyStreamServiceFacade.create(streamData);
                    if( streamCou != 1 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "流水记录失败");
                    }else{
                        ret = 1;
                    }
                }


        }
        if( ret != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }

        return  new JpfResponseDto();
    }

}
