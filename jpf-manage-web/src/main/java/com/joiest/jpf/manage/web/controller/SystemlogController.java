package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.SystemlogServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joiest.jpf.manage.web.util.ServletUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/systemlog")
public class SystemlogController {

    @Autowired
    private SystemlogServiceFacade systemlogServiceFacade;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("systemlog/systemlogList");
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(SystemlogRequest request){
        SystemlogResponse response = systemlogServiceFacade.SystemlogList(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }

    @RequestMapping("add")
    @ResponseBody
    public void add(Integer logtype, Integer clients, String action, String tablename, String content, HttpServletRequest request ){

        // ip
        String ip = ServletUtils.getIpAddr(request);

        // 获取用户信息
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        systemlogServiceFacade.sysLog(logtype,userInfo,ip,"",clients,tablename,action,content);
    }
}
