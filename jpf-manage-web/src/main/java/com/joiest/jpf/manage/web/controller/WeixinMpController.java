package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 代理公司管理
 */
@Controller
@RequestMapping("/weixinMp")
public class WeixinMpController {

    @Autowired
    private WeixinMpServiceFacade weixinMpServiceFacade;


    @RequestMapping("/index")
    public String index() {

        return "weixinMp/mplist";

    }

    /**
     * 公众号列表
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(GetWeixinMpRequest request) {

        GetWeixinMpResponse response = weixinMpServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 添加公众号信息页面
     */
    @RequestMapping("/addpage")
    public ModelAndView addView() {
        return new ModelAndView("weixinMp/weixinAdd");
    }

    /**
     * 添加公众号信息
     */
    @RequestMapping("/addsub")
    @ResponseBody
    public JpfResponseDto addsub(@Validated GetWeixinMpRequest request){

        return weixinMpServiceFacade.addsub(request);

    }

    /**
     * 修改公众号信息页面
     */
    @RequestMapping("/editpage")
    public ModelAndView editView(String id, ModelMap modelMap) {

        WeixinMpInfo weixinMpInfo =weixinMpServiceFacade.getOne(id);
        modelMap.addAttribute("weixinMpInfo",weixinMpInfo);
        return new ModelAndView("weixinMp/weixinEdit",modelMap);
    }

    /**
     * 修改公众号信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public JpfResponseDto edit(@Validated GetWeixinMpRequest request){

        return weixinMpServiceFacade.edit(request);

    }
}
