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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    * 充值列表页
    * */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudRechargeRequest cloudRechargeRequest){


        //定义财务角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)0);  //已支付(已上传凭证)
        statusArr.add((byte)1); //已支付(已上传凭证)
        statusArr.add((byte)2); //已支付(已上传凭证)
        cloudRechargeRequest.setStatusArr(statusArr);

        CloudRechargeResponse cloudRechargeResponse = cloudRechargeServiceFacade.getRecords(cloudRechargeRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudRechargeResponse.getCount());
        map.put("rows", cloudRechargeResponse.getList());

        return map;
    }


    /*
     * 财务充值列表页
     * */
    @RequestMapping("/caiwuIndex")
    public String caiwuIndex(){
        return "cloudRecharge/cloudCaiwuList";
    }

    /*
     * 财务充值列表页
     * */
    @RequestMapping("/caiwuList")
    @ResponseBody
    public Map<String, Object> caiwuList(CloudRechargeRequest cloudRechargeRequest){

        //定义财务角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)3);  //已支付(已上传凭证)
        statusArr.add((byte)4); //已支付(已上传凭证)
        cloudRechargeRequest.setStatusArr(statusArr);

        CloudRechargeResponse cloudRechargeResponse = cloudRechargeServiceFacade.getCaiwuRecords(cloudRechargeRequest);
        ModelMap modelMap = new ModelMap();
        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudRechargeResponse.getCount());
        map.put("rows", cloudRechargeResponse.getList());

        return map;
    }

    /*
     * 审核操作页面
     * */
    @RequestMapping("/caiwu/audit/page")
    @ResponseBody
    public ModelAndView caiwuAudit(Long id,ModelMap modelMap){
        CloudRechargeInfo cloudRechargeInfo = cloudRechargeServiceFacade.getRecharge(id);
        cloudRechargeInfo.setStatus_cn(ManageConstants.STATUSLIST.get(cloudRechargeInfo.getStatus().toString()));
        modelMap.addAttribute("cloudRechargeInfo", cloudRechargeInfo);

        return new ModelAndView("cloudRecharge/cloudRechargeAudit",modelMap);

    }

    /*
     * 审核操作页面
     * */
    @RequestMapping("/caiwu/audit/action")
    @ResponseBody
    public JpfResponseDto caiwuAuditAction(CloudRechargeRequest request){
        CloudRechargeInfo cloudRechargeInfo = cloudRechargeServiceFacade.getRecharge(request.getId());
        request.setStatus_cn(ManageConstants.STATUSLIST.get(cloudRechargeInfo.getStatus().toString()));
        JpfResponseDto jpfResponseDto = cloudRechargeServiceFacade.getCaiwuAuditRecharge(request);


        return jpfResponseDto;

    }

    /*
     * 审核操作页面
     * */
    @RequestMapping("/audit/page")
    @ResponseBody
    public ModelAndView audit(Long id,ModelMap modelMap){
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
        CloudRechargeInfo cloudRechargeInfo = cloudRechargeServiceFacade.getRecharge(request.getId());
        request.setStatus_cn(ManageConstants.STATUSLIST.get(cloudRechargeInfo.getStatus().toString()));
        JpfResponseDto jpfResponseDto = cloudRechargeServiceFacade.getAuditRecharge(request);


        return jpfResponseDto;

    }

}
