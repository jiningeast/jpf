package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayMerchants;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.MerchantBankInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.entity.MerchantPayTypeInfo;
import com.joiest.jpf.entity.MerchantShopInfo;
import com.joiest.jpf.facade.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    private static final Logger logger = LogManager.getLogger(MerchantController.class);

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private MerShopServiceFacade merShopServiceFacade;

    @Autowired
    private MerPayTypeServiceFacade merPayTypeServiceFacade;

    //添加发送短信
    @Autowired
    private PcaServiceFacade pcaServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("merchant/merchantList");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetMerchsRequest request) {
        GetMerchsResponse response = merchantServiceFacade.getMerchInfoList(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    @RequestMapping("/modify/page")
    public ModelAndView modifyPage(String id,ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        //商户对公帐户信息
        MerchantBankInfo merchantBankInfo = merchantServiceFacade.getMerchBank(Long.valueOf(id));
        //商户的商店信息
        MerchantShopInfo merShopInfo = merShopServiceFacade.getOneMerShopInfo(Long.parseLong(id));
        byte isHeadShop;
        if ( merShopInfo == null )
        {
            isHeadShop = 0;
        } else
        {
            isHeadShop = 1;
        }
        modelMap.put("isHeadShop", isHeadShop);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantBankInfo", merchantBankInfo);
        return new ModelAndView("merchant/merchantModify",modelMap);
    }

    @RequestMapping("/modify/action")
    @ResponseBody
    public JpfResponseDto modifyAction(ModifyMerchRequest request){
        return merchantServiceFacade.modifyMerchant(request);
    }

    @RequestMapping("/detail")
    public ModelAndView detail(String id,ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        MerchantBankInfo merchantBankInfo = merchantServiceFacade.getMerchBank(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantBankInfo", merchantBankInfo);
        return new ModelAndView("merchant/merchantDetail",modelMap);
    }

    @RequestMapping("/audit/page")
    public ModelAndView audit(String id,ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        MerchantBankInfo merchantBankInfo = merchantServiceFacade.getMerchBank(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantBankInfo", merchantBankInfo);
        return new ModelAndView("merchant/merchantAudit",modelMap);
    }

    @RequestMapping("/audit/action")
    @ResponseBody
    public JpfResponseDto auditAction(AuditMerchRequest request){
        // 审核前先判断这个商户配置的支付方式有没有违规
        GetMerchPayTypeResponse response = merPayTypeServiceFacade.getOneMerPayTypes(request.getId());
        List<MerchantPayTypeInfo> list = response.getPayTypeInfos();
        if ( list.isEmpty() ){
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"该商户尚未配置支付方式");
        }
        for (MerchantPayTypeInfo merchantPayTypeInfo:list){
            switch (merchantPayTypeInfo.getTpid()){
                case 6:
                    // 中银消费金融分期支付
                    if ( StringUtils.isBlank(merchantPayTypeInfo.getBankcatid()) ){
                        throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"中银消费金融分期支付配置有误，请检查");
                    }
                    break;

                case 7:
                    // 银联信用卡分期支付
                    if ( StringUtils.isBlank(merchantPayTypeInfo.getBankcatid()) || StringUtils.isBlank(merchantPayTypeInfo.getParam()) ){
                        throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"银联信用卡分期支付配置有误，请检查");
                    }
                    Map<String,String> jsonMap = JsonUtils.toCollection(merchantPayTypeInfo.getParam(), new TypeReference<HashMap<String,String>>(){});
                    if ( StringUtils.isBlank(jsonMap.get("CP_Acctid")) || StringUtils.isBlank(jsonMap.get("CP_MerchaNo")) || StringUtils.isBlank(jsonMap.get("CP_Code")) || StringUtils.isBlank(jsonMap.get("CP_Salt")) ){
                        throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"银联信用卡分期支付配置有误，请检查。");
                    }
                    break;

                case 8:
                    // 花呗分期
                    if (StringUtils.isBlank(merchantPayTypeInfo.getBankcatid()) && StringUtils.isBlank(merchantPayTypeInfo.getParam()) ){
                        throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"花呗分期支付配置有误，请检查");
                    }
                    break;

                case 9:
                    // 微信全额支付微信商户号必须配置
                    if ( StringUtils.isBlank(merchantPayTypeInfo.getParam()) ){
                        throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"微信支付配置有误，请检查");
                    }
                    Map<String,String> jsonMapWX = JsonUtils.toCollection(merchantPayTypeInfo.getParam(), new TypeReference<HashMap<String,String>>(){});
                    if ( StringUtils.isBlank(jsonMapWX.get("merSubMchid")) ){
                        throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"银联信用卡分期支付配置有误，请检查。");
                    }
                    break;
            }
        }
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(request.getId());
        if ( StringUtils.isBlank(merchantInfo.getPrivateKey()) )
        {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND,"商户密钥尚配置有误，请检查。");
        }
        JpfResponseDto jpfResponseDto = merchantServiceFacade.auditMerchant(request);

        String sendSmsUrl =  "http://testapi.7shengqian.com/index.php?r=Sms/YinjiaStageSend";
        Map<String,Object> smsMap = new HashMap<>();
        smsMap.put("mobile",merchantInfo.getLinkphone());
        String content = "";
        String return_content = "";
        if ( jpfResponseDto.getRetCode().equals("0000") )
        {
            //发送信息
            if ( request.getAttestation() )
            {
                //通过
                String success_format = "尊敬的用户：%s，您在银嘉分期平台的对接信息如下：商户号：%s；密钥为：%s；请妥善保存。";
                content = String.format(success_format,merchantInfo.getUsername(), merchantInfo.getMerchNo(),merchantInfo.getPrivateKey());
            } else if ( !request.getAttestation() )
            {
                String success_format = "尊敬的用户：%s，您在银嘉分期平台的对接商户信息未能通过审核，请联系客服人员。";
                content = String.format(success_format,merchantInfo.getUsername(), merchantInfo.getMerchNo(),merchantInfo.getPrivateKey());
            }
            smsMap.put("content",content);
            String smsRes = OkHttpUtils.postForm(sendSmsUrl,smsMap);
            Map<String,String> resSmsMap = JsonUtils.toCollection(smsRes, new TypeReference<HashMap<String,String>>(){});
            AddSmsMessageRequest smsRequest = new AddSmsMessageRequest();
            if ( resSmsMap.get("flag").equals("1") )
            {
                smsRequest.setReturnContent("发送成功");
                jpfResponseDto.setRetMsg("操作成功！ 短信发送成功");
            } else
            {
                smsRequest.setReturnContent("发送失败");
                jpfResponseDto.setRetMsg("操作成功！ 短信发送失败");
            }

            smsRequest.setMtsid(merchantInfo.getId().toString());
            smsRequest.setContent(content);
            byte ptype = 2;
            byte sendtype = 3;
            smsRequest.setPtype(ptype);
            smsRequest.setPtypeCn("产品类型描述");
            smsRequest.setSendtype(sendtype);
            smsRequest.setSendtypeCn("后台商户密钥发送");
            smsRequest.setAddtime(new Date());
            smsRequest.setReturnContent(smsRes);
            int res = pcaServiceFacade.addSmsMessage(smsRequest);
        }
        return jpfResponseDto;
    }
}
