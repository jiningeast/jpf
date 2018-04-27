package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.SystemlogServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joiest.jpf.manage.web.util.ServletUtils;

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
    public String index(){
        return "systemlog/systemlogList";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list( long page, long rows ){
        Map<String, Object> map = new HashMap();
        map.put("total", systemlogServiceFacade.getCount());
        map.put("rows", systemlogServiceFacade.getLogs(page, rows));

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
