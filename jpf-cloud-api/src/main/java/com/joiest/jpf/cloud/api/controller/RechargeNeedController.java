package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.CloudRechargeNeedReleaseRequest;
import com.joiest.jpf.dto.GetRechargeNeedRequest;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.facade.CloudEmployeeServiceFacade;
import com.joiest.jpf.facade.CloudRechargeServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 需求中心
 */
@Controller
@RequestMapping("/rechargeNeed")
public class RechargeNeedController {

    private static final Logger logger = LogManager.getLogger(RechargeNeedController.class);

    @Autowired
    private CloudRechargeServiceFacade cloudRechargeServiceFacade;

    @Autowired
    private CloudEmployeeServiceFacade cloudEmployeeServiceFacade;

    /**
     * 获取企业需求列表
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getRechargeNeedInfo(GetRechargeNeedRequest requestParam, String pageNo, String pageSize, HttpServletRequest request) {
        logger.debug("token={},requsetParam={},pageNo={},pageSize={}",request.getHeader("token"),requestParam,pageNo,pageSize);
        CloudEmployeeInfo cloudEmployeeInfo = cloudEmployeeServiceFacade.companyIsLogin(request.getHeader("token"));
        requestParam.setMerchNo(cloudEmployeeInfo.getMerchNo());
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.getRechargeNeedInfo(requestParam,pageNo,pageSize));
    }

    /**
     * 发布需求接口
     */
    @RequestMapping(value = "/release", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedRelease(CloudRechargeNeedReleaseRequest requestParam, HttpServletRequest request) {
        logger.debug("token={},requsetParam={}",request.getHeader("token"),requestParam);
        CloudEmployeeInfo cloudEmployeeInfo = cloudEmployeeServiceFacade.companyIsLogin(request.getHeader("token"));
        requestParam.setMerchNo(cloudEmployeeInfo.getMerchNo());
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedRelease(requestParam));
    }

    /**
     * 删除发布需求接口 status=1时可以有此操作
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedDelete(String agentNo, String id, String fid, HttpServletRequest request) {
        logger.debug("token={},agentNo={},id={},fid={}",request.getHeader("token"),agentNo,id,fid);
        CloudEmployeeInfo cloudEmployeeInfo = cloudEmployeeServiceFacade.companyIsLogin(request.getHeader("token"));
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedDelete(cloudEmployeeInfo.getMerchNo(),agentNo,id,fid));
    }

    /**
     * 上传凭证接口 status=2时可以有此操作
     */
    @RequestMapping(value = "/voucher", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedVoucher(String agentNo, String id, String fid, String imgurl, HttpServletRequest request) {
        logger.debug("token={},agentNo={},id={},fid={},imgurl={}",request.getHeader("token"),agentNo,id,fid,imgurl);
        CloudEmployeeInfo cloudEmployeeInfo = cloudEmployeeServiceFacade.companyIsLogin(request.getHeader("token"));
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedVoucher(cloudEmployeeInfo.getMerchNo(), agentNo, id, fid, imgurl));
    }

    /**
     * 确认验收接口 pacttime时间到了、pactstatus=1、status=4或5或6时可以有此操作
     */
    @RequestMapping(value = "/affirm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedAffirm(String agentNo,String id,String fid, HttpServletRequest request) {
        logger.debug("token={},agentNo={},id={},fid={}",request.getHeader("token"),agentNo,id,fid);
        CloudEmployeeInfo cloudEmployeeInfo = cloudEmployeeServiceFacade.companyIsLogin(request.getHeader("token"));
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedAffirm(cloudEmployeeInfo.getMerchNo(),agentNo,id,fid));
    }

    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {
        // 跨域
        String originHeader = httpRequest.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Method", "POST");
        response.setHeader("Access-Control-Allow-Origin", originHeader);

    }

}
