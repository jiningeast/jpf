package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.GetCloudStaffMonthTotalRequest;
import com.joiest.jpf.dto.GetCloudStaffMonthTotalResponse;
import com.joiest.jpf.facade.CloudStaffMonthTotalServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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




}
