package com.joiest.jpf.manage.web.controller;


import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cloudCompanyMoney")
public class CloudCompanyMoneyController {

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "cloudCompanyMoney/companyList";
    }

    /**
     * 批次管理页
     * */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){
        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = cloudCompanyMoneyServiceFacade.getRecords(cloudCompanyMoneyRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudCompanyMoneyResponse.getCount());
        map.put("rows", cloudCompanyMoneyResponse.getList());

        return map;
    }

    /*
     * 财务审核订单列表
     * */
    @RequestMapping("/caiwu/index")
    public String caiwuIndex(){
        return "cloudCompanyMoney/companyCaiwuList";
    }

    /**
     * 财务审核订单列表
     * */
    @RequestMapping("/caiwu/list")
    @ResponseBody
    public Map<String, Object> caiwuList(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){

        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = cloudCompanyMoneyServiceFacade.getCaiwuRecords(cloudCompanyMoneyRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudCompanyMoneyResponse.getCount());
        map.put("rows", cloudCompanyMoneyResponse.getList());
        // 统计汇总
        map.put("allCount", cloudCompanyMoneyResponse.getAllCount());
        BigDecimal allMoney = cloudCompanyMoneyResponse.getAllMoney();
        allMoney = allMoney == null ? new BigDecimal(0) : allMoney;
        map.put("allMoney", allMoney);
        BigDecimal allServiceMoney = cloudCompanyMoneyResponse.getAllServiceMoney();
        allServiceMoney = allServiceMoney == null ? new BigDecimal(0) : allServiceMoney;
        map.put("allServiceMoney", allServiceMoney);
        BigDecimal addedMoney = cloudCompanyMoneyResponse.getAddedMoney();
        addedMoney = addedMoney == null ? new BigDecimal(0) : addedMoney;
        map.put("addedMoney", addedMoney);
        BigDecimal addedMoneyPay = cloudCompanyMoneyResponse.getAddedMoneyPay();
        addedMoneyPay = addedMoneyPay == null ? new BigDecimal(0) : addedMoneyPay;
        map.put("addedMoneyPay", addedMoneyPay);
        BigDecimal totalGross = cloudCompanyMoneyResponse.getTotalGross();
        totalGross = totalGross == null ? new BigDecimal(0) : totalGross;
        map.put("totalGross", totalGross);
        return map;
    }

    /**
     * 查询公司页
     */
    @RequestMapping("/dfdetail/page")
    public ModelAndView dfdetailPage(String companyMoneyId, ModelMap modelMap){
        modelMap.addAttribute("companyMoneyId",companyMoneyId);
        return new ModelAndView("cloudCompanyMoney/companyDfdetail",modelMap);
    }

    /**
     * 代付明细
     */
    //@RequestMapping("/dfdetail")
    @RequestMapping(value = "/dfDetail", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String ,Object> dfDetail(String companyMoneyId){

        //GetCloudMoneyDfResponse getCloudMoneyDfResponse = cloudCompanyMoneyServiceFacade.getAllByfid(fid);
        GetCloudMoneyDfResponse getCloudMoneyDfResponse = cloudCompanyMoneyServiceFacade.getAllBycompanyMoneyId(companyMoneyId);
        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("total",getCloudMoneyDfResponse.getCount());
        responseMap.put("rows",getCloudMoneyDfResponse.getList());

        return responseMap;
    }

}
