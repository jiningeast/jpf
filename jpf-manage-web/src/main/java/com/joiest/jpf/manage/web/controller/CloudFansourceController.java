package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetCloudFansourceRequest;
import com.joiest.jpf.dto.GetCloudFansourceResponse;
import com.joiest.jpf.entity.CloudFanSourceInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理公司管理
 */
@Controller
@RequestMapping("/cloudFansource")
public class CloudFansourceController {

    @Autowired
    private CloudFanSourceServiceFacade cloudFanSourceServiceFacade;

    @RequestMapping("/index")
    public String index() {

        return "cloudFansource/list";

    }

    //前台需求列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(GetCloudFansourceRequest request) {
        GetCloudFansourceResponse response = cloudFanSourceServiceFacade.getFansourceList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }
    /**
     * 编辑-页面
     */
    @RequestMapping("editFansource/page")
    public ModelAndView editView(String id, ModelMap modelMap) {
        //取出当前公司的信息
        CloudFanSourceInfo cloudFanSourceInfo = cloudFanSourceServiceFacade.getFansource(id);
        //商户对公帐户信息
        modelMap.addAttribute("cloudFanSourceInfo", cloudFanSourceInfo);
        return new ModelAndView("cloudFansource/Edit", modelMap);
    }

    /**
     * 审核操作
     */
    @RequestMapping("edit/action")
    @ResponseBody

    public JpfResponseDto edit(GetCloudFansourceRequest request, HttpSession httpSession) throws Exception{
        //获取登录帐号
        UserInfo userInfo =(UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        String id=userInfo.getId().toString();
        return cloudFanSourceServiceFacade.editPass(request,id);
    }

}
