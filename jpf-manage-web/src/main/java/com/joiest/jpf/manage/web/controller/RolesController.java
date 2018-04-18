package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ModifyRoleRequest;
import com.joiest.jpf.entity.RolesInfo;
import com.joiest.jpf.facade.RolesServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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
        return "roles/rolesList";
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

    /**
     * 角色编辑-页面加载
     */
    @RequestMapping("modify/page")
    public ModelAndView modifyView(String id, ModelMap modelMap){
        RolesInfo rolesInfo = rolesServiceFacade.getRoleOne(Integer.valueOf(id));
        modelMap.addAttribute("rolesInfo", rolesInfo);
        return new ModelAndView("roles/roleModify", modelMap);
    }

    /**
     * 角色编辑-action
     */
    @RequestMapping("modify/action")
    @ResponseBody
    public JpfResponseDto modifyAction(ModifyRoleRequest request)
    {
        return rolesServiceFacade.modifyRole(request);
    }

    /**
     * 添加角色-页面
     */
    @RequestMapping("add/page")
    public ModelAndView addView(){
        return  new ModelAndView("roles/roleAdd");
    }

    /**
     * 添加角色-action
     */
    @RequestMapping("add/action")
    @ResponseBody
    public JpfResponseDto addAction(ModifyRoleRequest request){
        return rolesServiceFacade.ModifyRoleRequest(request);
    }

    /**
     * 删除角色
     */
    @RequestMapping("delRole")
    @ResponseBody
    public JpfResponseDto delRole(String id )
    {
        return rolesServiceFacade.delRole(id);
    }
}
