package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.CloudRechargeRequest;
import com.joiest.jpf.dto.CloudRechargeResponse;
import com.joiest.jpf.entity.CloudRechargeInfo;
import com.joiest.jpf.facade.CloudRechargeServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/cloudRecharge")
public class CloudRechargeController {


    @Autowired
    private CloudRechargeServiceFacade cloudRechargeServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "cloudRecharge/cloudRechargeList";
    }

    /*
    * 充值了列表页
    * */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudRechargeRequest cloudRechargeRequest){
        CloudRechargeResponse cloudRechargeResponse = cloudRechargeServiceFacade.getRecords(cloudRechargeRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudRechargeResponse.getCount());
        map.put("rows", cloudRechargeResponse.getList());

        return map;
    }

    /*
     * 审核操作页面
     * */
    @RequestMapping("/audit/page")
    @ResponseBody
    public ModelAndView audit(String id,ModelMap modelMap){
        CloudRechargeInfo cloudRechargeInfo = cloudRechargeServiceFacade.getRecharge(id);
        cloudRechargeInfo.setStatus_cn(ManageConstants.STATUSLIST.get(cloudRechargeInfo.getStatus().toString()));
        modelMap.addAttribute("cloudRechargeInfo", cloudRechargeInfo);

        return new ModelAndView("cloudRecharge/cloudRechargeAudit",modelMap);

    }

    /*
     * 审核操作页面
     * */
    @RequestMapping("/audit/action")
    @ResponseBody
    public JpfResponseDto auditAction(CloudRechargeRequest request){
        request.setStatus_cn(ManageConstants.STATUSLIST.get(request.getStatus().toString()));
        JpfResponseDto jpfResponseDto = cloudRechargeServiceFacade.getAuditRecharge(request);


        return jpfResponseDto;

    }

}
