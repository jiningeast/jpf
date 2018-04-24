package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.TdorderRequest;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.TdorderServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tdorder")
public class TdorderController {

    @Autowired
    private TdorderServiceFacade tdorderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "tdorder/tdorderList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(long page, long rows){
        Map<String, Object> map = new HashMap<>();
        map.put("total", tdorderServiceFacade.getTdordersCount());
        map.put("rows", tdorderServiceFacade.getTdorders(page, rows));

        return map;
    }

    @RequestMapping("/checkOk")
    @ResponseBody
    public JpfResponseDto checkOk(TdorderRequest tdorderRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        return tdorderServiceFacade.checkOk(tdorderRequest,userInfo);
    }

    @RequestMapping("/checkNo")
    @ResponseBody
    public JpfResponseDto checkNo(TdorderRequest tdorderRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        return tdorderServiceFacade.checkNo(tdorderRequest, userInfo);
    }
}
