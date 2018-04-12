package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.facade.RolesServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

/**
 * 用户角色管理
 */
@Controller
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesServiceFacade rolesServiceFacade;

    /**
     * 用户角色列表管理
     * @returnz
     */
    @RequestMapping("/index")
    public String index() {
        return "user/rolesList";
    }

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String name, String intro, String status,long page,long rows){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", rolesServiceFacade.getRoleCount(name, status));
        map.put("rows", rolesServiceFacade.getRole(name, intro,status,page,rows));
        return map;
    }

    /**
     * 添加角色
     */
    @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(String name, String intro){
        return rolesServiceFacade.addRole(name,intro);
    }



}
