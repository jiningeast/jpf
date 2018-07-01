package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.dto.GetUserBlanceRequest;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public String userlogin()
    {
        return "";
    }

    //登出
    @RequestMapping("/logout")
    @ResponseBody
    public String userlogout()
    {
        return "";
    }

    //月份
    @RequestMapping(value = "/userblance", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserCurMonthMoney(GetUserBlanceRequest request)
    {
        Map<Integer,String> userIsLogin = userIsLogin();
        if ( !userIsLogin.get(0).equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), JpfInterfaceErrorInfo.NOTlOGIN.getDesc(), null);
        }

        String[] date;
        int year,month;
        if (StringUtils.isNotBlank(request.getData()))
        {
            date = request.getData().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
        } else
        {
            Calendar calstar= Calendar.getInstance();
            year = calstar.get(Calendar.YEAR);
            month = calstar.get(Calendar.MONTH) + 1;
        }

        Map<String,String> map =  ToolUtils.getMonthStartAndEnd(year,month);

        //获取指定月份的信息
        long pageNo = 1;
        long pageSize = 15;
        int flag = 1;   //当前月份
        if ( StringUtils.isNotBlank(request.getData()) )
        {
            //指定月份
            flag = 2;
        }
        GetCloudMoneyDfResponse response = cloudDfMoneyServiceFacade.getDfMoneyList(map.get("start"),map.get("end"), userIsLogin.get(1),pageNo, pageSize, flag);
        if ( response == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "暂无数据", null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), response.getMonthTotal().toString(), response);
    }

    //判断用户是否登录
    private Map<Integer,String> userIsLogin()
    {
        Map<Integer,String> map = new HashMap<>();
        map.put(0,JpfInterfaceErrorInfo.SUCCESS.getCode());
        map.put(1,"67136");
        return map;
    }

    /**
     * 获取用户总金额
     * @return
     */
    @RequestMapping(value = "/userdf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserTotalMoney()
    {
        Map<Integer,String> userIsLogin = userIsLogin();
        if ( !userIsLogin.get(0).equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), JpfInterfaceErrorInfo.NOTlOGIN.getDesc(), null);
        }

        Double totalMoney = cloudDfMoneyServiceFacade.getUserDfTotalMoney(userIsLogin.get(1));

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), totalMoney);
    }

    /**
     * 月份汇总
     * @return
     */
    @RequestMapping(value = "/usermonth", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserMonthMoney()
    {
        Map<Integer,String> userIsLogin = userIsLogin();
        if ( !userIsLogin.get(0).equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), JpfInterfaceErrorInfo.NOTlOGIN.getDesc(), null);
        }
        List<CloudDfMoneyInterfaceInfo> list = cloudDfMoneyServiceFacade.getUserMonthList(Long.parseLong(userIsLogin.get(1)));

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), list);
    }

}
