package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.GetShopBargainOrderRequest;
import com.joiest.jpf.dto.GetShopBargainOrderResponse;
import com.joiest.jpf.dto.GetShopBargainRequestRequest;
import com.joiest.jpf.entity.ShopBargainOrderInfo;
import com.joiest.jpf.entity.ShopBargainRequestInfo;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.market.api.util.ToolsUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bargainBuyer")
public class BargainBuyerController {

    private String uid;

    private String openId;

    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopBargainRequestServiceFacade shopBargainRequestServiceFacade;

    @Autowired
    private ShopBargainOrderServiceFacade shopBargainOrderServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerServiceFacade shopCustomerServiceFacade;

    @RequestMapping("/becomeBuyer")
    @ResponseBody
    public String becomeBuyer(){
        if ( userInfo.getIsBargainBuyer() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"您已经是买家了",null);
        }
        String buyerSwitch = ConfigUtil.getValue("BECOME_BUYER_SWITCH");
        if ( buyerSwitch.equals("0") ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"买家数量已满，请稍后再试",null);
        }
        userInfo.setIsBargainBuyer((byte)1);
        userInfo.setUpdatetime(new Date());
        int customerRes = shopCustomerInterfaceServiceFacade.updateCustomerById(userInfo);
        if ( customerRes > 0 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",null);
        }else{
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"操作失败",null);
        }
    }

    /*
    *  买家发布页面
    * */
    @RequestMapping(value = "/startBuyDou",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String startBuyDou(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,String> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, String>>(){});

        //判断请求参数是否合法
        String offRate = requestMap.get("offRate");
        String minDou = requestMap.get("minDou");
        String status = requestMap.get("status");
        if( StringUtils.isBlank(offRate) || StringUtils.isBlank(minDou) || StringUtils.isBlank(status)  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请求参数错误", null);
        }

        Boolean RateFlag = ToolsUtils.isInterger(offRate);// 折扣率
        Boolean minDouFlag = ToolsUtils.isInterger(minDou);//豆
        Boolean statusFlag = ToolsUtils.isInterger(status);
        if( !RateFlag  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"折扣率需填写整数",null);
        }
        if( Integer.parseInt(offRate) < 0 || Integer.parseInt(offRate) >= 100 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"折扣率需大于0且小于100",null);
        }
        /*if( !minDouFlag || Integer.valueOf(minDou)%10 !=0 || Integer.valueOf(minDou) <= 0   ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"最低限额需大于0且必须为10的整数倍",null);
        }*/

        Byte statusNew = Byte.valueOf( status); //回收状态
        if( !statusFlag || (statusNew !=0 && statusNew !=1)   ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"回收状态值填写错误",null);
        }
        GetShopBargainRequestRequest request = new GetShopBargainRequestRequest();
        request.setCustomerId(uid);
        request.setOffRate(Double.parseDouble(offRate));
        request.setMinDou(Integer.parseInt(minDou));
        request.setStatus(  statusNew );


        //先查询当前用户是否发布
        ShopBargainRequestInfo infos =shopBargainRequestServiceFacade.getOne(request);
        if( infos!=null  ){
            request.setId(infos.getId());
        }

        //添加 或更新 买家信息
        JpfResponseDto jpfResponseDto = shopBargainRequestServiceFacade.add(request);
        if( !jpfResponseDto.getRetCode().equals("0000") ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"操作失败",null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",null);
    }

    /*
     *  查询买家发布
     * */
    @RequestMapping(value = "/searchCustom",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String searchCustom(){

        //查询是否为买家
        if( userInfo.getIsBargainBuyer() ==null || userInfo.getIsBargainBuyer() == 0 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.BARGAIN_BUYER_TYPE.getCode(),JpfInterfaceErrorInfo.BARGAIN_BUYER_TYPE.getDesc(),null);
        }else{//买家信息

            GetShopBargainRequestRequest request = new GetShopBargainRequestRequest();
            request.setCustomerId(uid);

            //先查询当前用户发布信息
            ShopBargainRequestInfo infos = shopBargainRequestServiceFacade.getOne(request);
            if( infos!=null ){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",infos);
            }

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.BARGAIN_BUYER_NOINFO.getCode(),"未添加记录",null);
        }
    }

    /**
     *买家发布信息
     * */
    @RequestMapping(value = "/buyInfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String buyInfo(HttpServletRequest request){

        List<ShopBargainRequestInfo> list = shopBargainRequestServiceFacade.getBuyInfo();
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"SUCCESS",list);
    }


    /**
     * 获取买家发布单条信息
     * */
    @RequestMapping(value = "/buyInfoSigle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String buyInfoSigle(HttpServletRequest request){

        JSONObject requestParam = _filter(request.getParameter("data"));

        if(requestParam.get("code").toString().equals("10008"))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),requestParam.get("info").toString(),null);

        if(StringUtils.isBlank(requestParam.get("bargainRequestId").toString()))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"参数信息有误",null);

        //获取服务发布订单
        ShopBargainRequestInfo shopBargainRequestInfo = shopBargainRequestServiceFacade.getBargainById(requestParam.get("bargainRequestId").toString());
        if(shopBargainRequestInfo == null)
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"未获取买家信息",null);


        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"SUCCESS",shopBargainRequestInfo);
    }

    /**
     * 我买到的（买家购买服务转让列表）
     * */
    @RequestMapping(value = "/buyList",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String buyList(String data){

        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,String> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, String>>(){});

        //判断请求参数是否合法
        String page = requestMap.get("page");
        String type = requestMap.get("type");

        if( StringUtils.isBlank(page)  || ( !type.equals("1") && !type.equals("2") ) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请求参数错误", null);
        }

        GetShopBargainOrderRequest bargainOrderRequest = new GetShopBargainOrderRequest();
        // 1=我转让的   2=我买到的
        if( type.equals("1") ){
            bargainOrderRequest.setSellerCustomerId(uid);
        }
        if(type.equals("2")){
            bargainOrderRequest.setBuyerCustomerId(uid);
        }
        bargainOrderRequest.setPage(Integer.parseInt(page));
        //bargainOrderRequest.setRows(10);  //默认10条

        GetShopBargainOrderResponse response = shopBargainOrderServiceFacade.getFrontList(bargainOrderRequest);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",response);
    }
    /**
     * 订单详情
     * */
    @RequestMapping(value = "/orderDetail",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderDetail(HttpServletRequest request){

        JSONObject requestParam = _filter(request.getParameter("data"));
        JSONObject responsParam = new JSONObject();

        if(requestParam.get("code").toString().equals("10008"))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),requestParam.get("info").toString(),null);

        if(StringUtils.isBlank(requestParam.get("orderNo").toString()))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"参数信息有误",null);

        ShopBargainOrderInfo shopBargainOrderInfo = shopBargainOrderServiceFacade.getBargainOrderByNo(requestParam.get("orderNo").toString());
        if(shopBargainOrderInfo == null)
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"未获取到此单信息，请检查订单号是否正确",null);

        responsParam = JSONObject.fromObject(shopBargainOrderInfo);

        if (requestParam.containsKey("type") && requestParam.get("type").toString().equals("1")){

            //判断订单是否隶属于当前用户
            if(!shopBargainOrderInfo.getSellerCustomerId().equals(uid)){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"卖家信息不匹配",null);
            }
            responsParam.put("realName",ToolUtils.getStarString2(shopBargainOrderInfo.getRealName(),1,0));
            responsParam.put("bankNo",ToolUtils.getStarString2(shopBargainOrderInfo.getBankNo(),4,3));
            responsParam.put("phone",ToolUtils.getStarString2(shopBargainOrderInfo.getPhone(),1,0));
            responsParam.put("idno",ToolUtils.getStarString2(shopBargainOrderInfo.getIdno(),3,3));
            responsParam.put("bankBrank",ToolUtils.getStarString2(shopBargainOrderInfo.getBankBrank(),4,2));
            //PayShopCustomer buyerInfo = shopCustomerServiceFacade.getCustomerById(shopBargainOrderInfo.getBuyerCustomerId());
            //PayShopCustomer sellerInfo = shopCustomerServiceFacade.getCustomerById(shopBargainOrderInfo.getSellerCustomerId());
        }else if(requestParam.containsKey("type") && requestParam.get("type").toString().equals("2")){
            //判断订单是否隶属于当前用户
            if(!shopBargainOrderInfo.getBuyerCustomerId().equals(uid)){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"买家信息不匹配",null);
            }
        }
        responsParam.put("addtime", DateUtils.dateToString(shopBargainOrderInfo.getAddtime()));
        if(shopBargainOrderInfo.getPaytime()!=null)
            responsParam.put("paytime", DateUtils.dateToString(shopBargainOrderInfo.getPaytime()));

        if(shopBargainOrderInfo.getUpdatetime()!=null)
            responsParam.put("updatetime", DateUtils.dateToString(shopBargainOrderInfo.getUpdatetime()));


        JSONObject allRespon = new JSONObject();
        allRespon.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        allRespon.put("info","SUCCESS");
        allRespon.put("data",responsParam);

        String base64Str = Base64CustomUtils.base64Encoder(allRespon.toString());
        base64Str = base64Str.replaceAll("\r","");
        base64Str = base64Str.replaceAll("\n","");
        return base64Str;
    }
    /**
     * 基础参数格式化
     * */
    private JSONObject _filter(String data)
    {

        JSONObject res = new JSONObject();
        res.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
        res.put("info","信息不能为空");

        if (StringUtils.isBlank(data)) return res;

        String baseDecode = Base64CustomUtils.base64Decoder(data);
        JSONObject requestParam = JSONObject.fromObject(baseDecode);

        res.putAll(requestParam);
        res.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        res.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());

        return res;
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            List<ShopCustomerInterfaceInfo> info = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
            if( info != null && !info.isEmpty()  ){
                userInfo = info.get(0);
                uid = userInfo.getId();
            }
        }
    }

}
