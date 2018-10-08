package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetCloudDfMoneyFreezeRequest;
import com.joiest.jpf.dto.GetCloudDfMoneyFreezeResponse;
import com.joiest.jpf.entity.CloudDfMoneyFreezeInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.CloudDfMoneyFreezeServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cloudDfMoneyFreeze")
public class CloudDfMoneyFreezeController {

    @Autowired
    private CloudDfMoneyFreezeServiceFacade cloudDfMoneyFreezeServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "cloudDfMoneyFreeze/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(GetCloudDfMoneyFreezeRequest request){
        GetCloudDfMoneyFreezeResponse response = cloudDfMoneyFreezeServiceFacade.getRecords(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }

    /**
     * 运营申请解冻操作
     */
    @RequestMapping("/unfreezeApply")
    @ResponseBody
    public JpfResponseDto unfreezeApply(String id, HttpSession httpSession){
        // 获取冻结详情
        CloudDfMoneyFreezeInfo cloudDfMoneyFreezeInfo = cloudDfMoneyFreezeServiceFacade.getRecordByPrimaryKey(id);

        // 判断订单是不是已申请解冻或已经解冻成功
        if ( !cloudDfMoneyFreezeInfo.getStatus().equals(1) || !cloudDfMoneyFreezeInfo.getMoneyStatus().equals(1) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("该订单已申请解冻或已解冻成功");

            return jpfResponseDto;
        }

        // 更新订单信息
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        cloudDfMoneyFreezeInfo.setOperatorId(userInfo.getId());
        cloudDfMoneyFreezeInfo.setOperatorName(userInfo.getUserName());
        cloudDfMoneyFreezeInfo.setOperatorTime(new Date());
        cloudDfMoneyFreezeInfo.setStatus(2);    // 1:冻结default 2:运营申请解冻 3:财务审核通过 4:财务拒绝
        int updateRes = cloudDfMoneyFreezeServiceFacade.updateColumnByPrimaryKey(cloudDfMoneyFreezeInfo);
        if ( updateRes > 0 ){
            return new JpfResponseDto();
        }else{
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("更新失败");

            return jpfResponseDto;
        }
    }

    /**
     * 财务列表
     */
    @RequestMapping("/caiwu/index")
    public String caiwuIndex(){
        return "cloudDfMoneyFreeze/caiwuIndex";
    }

    /**
     * 解冻
     */
    @RequestMapping("/unfreeze")
    @ResponseBody
    public JpfResponseDto unfreeze(String id, HttpSession httpSession){
        // 获取冻结详情
        CloudDfMoneyFreezeInfo cloudDfMoneyFreezeInfo = cloudDfMoneyFreezeServiceFacade.getRecordByPrimaryKey(id);
        // 判断运营是否已提交或已解冻
        if ( cloudDfMoneyFreezeInfo.getStatus().equals(1) || cloudDfMoneyFreezeInfo.getMoneyStatus().equals(2) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("运营尚未申请解冻或已解冻");

            return jpfResponseDto;
        }else if ( cloudDfMoneyFreezeInfo.getStatus().equals(3) || cloudDfMoneyFreezeInfo.getMoneyStatus().equals(2) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("已解冻，请不要重复操作");

            return jpfResponseDto;
        }
        // 解冻操作
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        int updateRes = cloudDfMoneyFreezeServiceFacade.unfreeze(id,userInfo);
        if ( updateRes > 0 ){
            return new JpfResponseDto();
        }else{
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("更新失败");

            return jpfResponseDto;
        }
    }
}
