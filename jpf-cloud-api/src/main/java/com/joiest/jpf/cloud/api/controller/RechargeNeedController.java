package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.CloudRechargeNeedReleaseRequest;
import com.joiest.jpf.dto.GetRechargeNeedRequest;
import com.joiest.jpf.facade.CloudRechargeServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * 需求中心
 */
@Controller
@RequestMapping("/rechargeNeed")
public class RechargeNeedController {

    @Autowired
    private CloudRechargeServiceFacade cloudRechargeServiceFacade;

    /**
     * 获取企业需求列表
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getRechargeNeedInfo(GetRechargeNeedRequest request, Long pageNo, Long pageSize) {
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.getRechargeNeedInfo(request,pageNo,pageSize));
    }

    /**
     * 发布需求接口
     */
    @RequestMapping(value = "/release", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedRelease(CloudRechargeNeedReleaseRequest request) {
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedRelease(request));
    }

    /**
     * 删除发布需求接口 status=1时可以有此操作
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedDelete(String merchNo,String agentNo,Long id,String fid) {
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedDelete(merchNo,agentNo,id,fid));
    }

    /**
     * 上传凭证接口 status=2时可以有此操作
     */
    @RequestMapping(value = "/voucher", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedVoucher(String merchNo,String agentNo,Long id,String fid,String imgurl) {
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedVoucher(merchNo,agentNo,id,fid,imgurl));
    }

    /**
     * 确认验收接口 pacttime时间到了、pactstatus=1、status=0或1或2或3时可以有此操作
     */
    @RequestMapping(value = "/affirm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String rechargeNeedAffirm(String merchNo,String agentNo,Long id,String fid,Byte pactstatus) {
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), cloudRechargeServiceFacade.rechargeNeedAffirm(merchNo,agentNo,id,fid,pactstatus));
    }

}
