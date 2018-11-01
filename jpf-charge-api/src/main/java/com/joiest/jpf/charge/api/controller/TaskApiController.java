package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OfpayUtils;
import com.joiest.jpf.entity.ChargeProductInfo;
import com.joiest.jpf.facade.ChargeProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("taskApi")
public class TaskApiController {

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    /**
    * 构造函数
    * */
    public TaskApiController(){

        System.out.println("访问构造函数");
    }

    /**
     * 静态方法
     */
    public static void visitStatic(){

        System.out.println("访问静态方法");
    }

    /**
    *欧非具体商品信息同步接口
    * */
    @RequestMapping("/syncCard")
    public void syncCardInfo(HttpServletRequest request){

        StringBuilder sbf = new StringBuilder();

        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n充值类型:" + "手机号和面值查询商品信息");
        sbf.append("\n请求地址：" + request.getRequestURL().toString());

        String fileName = "ChargeProductPrice";
        String path = "/logs/jpf-charge-api/log/";
        String sucPro = "";
        String faildPro = "";
        Map<String,String> response = new HashMap<>();
        PayChargeProduct payChargeProduct= new PayChargeProduct();
        List<ChargeProductInfo> productInfo = chargeProductServiceFacade.getList(payChargeProduct);

        for(int i=0;i<productInfo.size();i++){

            ChargeProductInfo chargeProductInfo = productInfo.get(i);
            switch ((int)chargeProductInfo.getMobileType()){
                case 1://移动

                    response = telCardDeal(ConfigUtil.getValue("phone_cmcc_number"),chargeProductInfo.getValue());
                    break;
                case 2://联通

                    response = telCardDeal(ConfigUtil.getValue("phone_cucc_number"),chargeProductInfo.getValue());
                    break;
                case 3://电信

                    response = telCardDeal(ConfigUtil.getValue("phone_ctc_number"),chargeProductInfo.getValue());
                    break;
                case 4:
                case 5:
                    if(StringUtils.isBlank(chargeProductInfo.getOfProductId()))
                        continue;

                    response = oilCardDeal(chargeProductInfo);
                    break;
                default:
                    break;
            }
            if(response.get("code").equals("10000")){

                ChargeProductInfo upCharProduct = new ChargeProductInfo();
                upCharProduct.setId(chargeProductInfo.getId());
                upCharProduct.setUpdatetime(new Date());
                upCharProduct.setOfProductPrice(new BigDecimal(response.get("inprice")));

                int isSuc = chargeProductServiceFacade.upChargeProduct(upCharProduct);

                sucPro+= chargeProductInfo.getId()+",";

            }else{

                faildPro+= chargeProductInfo.getId()+",";
            }
        }
        sbf.append("\n成功商品：" + sucPro);
        sbf.append("\n失败商品：" + faildPro);

        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
    }
    /**
     * 话费查询
     * */
    public Map<String,String> telCardDeal(String phone,BigDecimal value){

        Map<String,String> response = new HashMap<>();
        response.put("code", JpfInterfaceErrorInfo.FAIL.getCode());
        response.put("info", "参数错误");

        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("phoneno",phone);
        requestMap.put("pervalue",value.toString());
        response = new OfpayUtils().telquery(requestMap);

        if(response.get("retcode").equals("1")){

            response.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
            response.put("info", "SUCCESS");
        }
        return response;
    }
    /**
     * 油卡查询
     * **/
    public Map<String,String> oilCardDeal(ChargeProductInfo chargeProductInfo){

        Map<String,String> response = new HashMap<>();
        response.put("code", JpfInterfaceErrorInfo.FAIL.getCode());
        response.put("info", "参数错误");

        //油卡
        response = new OfpayUtils().queryCardInfo(chargeProductInfo.getOfProductId());
        if(response.get("retcode").equals("1")){

            response.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
            response.put("info", "SUCCESS");
        }
        return response;
    }
}

