package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ModifyUserRequest;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.UserServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserServiceFacade userServiceFacade;

    /**
     * 用户列表首页
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "user/userList";
    }

    /**
     * 用户列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String userName, String status,long page,long rows){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", userServiceFacade.getUsersCount(userName, status));
        map.put("rows", userServiceFacade.getUsers(userName,status,page,rows));
        return map;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(String userName, String userPwd){
        return userServiceFacade.addUser(userName,userPwd);
    }

    /**
     * 禁用/启用状态
     */
    @RequestMapping("alertStatus")
    @ResponseBody
    public JpfResponseDto alertStatus(String userName,String status){
        return userServiceFacade.alterStatus(userName, status);
    }

    /**
     * 重置密码
     */
    @RequestMapping("resetPwd")
    @ResponseBody
    public JpfResponseDto resetPwd(String userName){
        return userServiceFacade.resetPwd(userName);
    }

    @RequestMapping("modifyPwd")
    @ResponseBody
    public JpfResponseDto modifyPwd(String oldPwd,String newPwd,HttpSession httpSession){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        String userName = userInfo.getUserName();
        return userServiceFacade.modifyPwd(userName,oldPwd,newPwd);
    }

    /**
     * 角色编辑-页面加载
     */
    @RequestMapping("editPwd")
    public ModelAndView editPwd(Integer id, ModelMap modelMap){
        UserInfo userInfo = userServiceFacade.getUserOne(id);
        modelMap.addAttribute("userInfo", userInfo);

        return new ModelAndView("user/userPwdModify", modelMap);
    }

    /**
     * 角色编辑-action
     */
    @RequestMapping("modify/action")
    @ResponseBody
    public JpfResponseDto modifyAction(ModifyUserRequest request)
    {
        return userServiceFacade.modifyUserPwd(request);
    }

}
