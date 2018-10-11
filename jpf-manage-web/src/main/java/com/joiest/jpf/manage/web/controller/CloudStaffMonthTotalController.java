package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetCloudStaffMonthTotalRequest;
import com.joiest.jpf.dto.GetCloudStaffMonthTotalResponse;
import com.joiest.jpf.entity.CloudStaffMonthTotalInfo;
import com.joiest.jpf.facade.CloudStaffMonthTotalServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *用户限额列表
 */
@Controller
@RequestMapping("/cloudStaffMonthTotal")
public class CloudStaffMonthTotalController {

    @Autowired
    private CloudStaffMonthTotalServiceFacade cloudStaffMonthTotalServiceFacade;


    @RequestMapping("/index")

    public String index() {
        return "cloudStaffMonthTotal/list";
    }

    //获取限额列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(GetCloudStaffMonthTotalRequest request) {

        GetCloudStaffMonthTotalResponse response = cloudStaffMonthTotalServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }


    /**
     * 备注详情页面
     */
    @RequestMapping("/infoRemark")
    @ResponseBody
    public ModelAndView info(String id, ModelMap modelMap){


        CloudStaffMonthTotalInfo info= cloudStaffMonthTotalServiceFacade.getOne(id);
        JSONArray arr=new JSONArray();
        if(StringUtils.isNotBlank(info.getRemarks())&& info.getRemarks() !=null){
             arr= ToolUtils.JsonTOarray(info.getRemarks());
        }
        modelMap.addAttribute("listinfo",arr);
        return new ModelAndView("cloudStaffMonthTotal/InfoList", modelMap);
    }


}
