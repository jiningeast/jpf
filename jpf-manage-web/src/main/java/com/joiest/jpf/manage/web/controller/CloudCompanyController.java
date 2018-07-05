package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.PhotoUtil;
import com.joiest.jpf.common.util.SendMailUtil;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import com.joiest.jpf.dto.GetCloudCompanyResponse;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.sun.tools.internal.ws.processor.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 代理公司管理
 */
@Controller
@RequestMapping("/cloudCompany")
public class CloudCompanyController {

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @RequestMapping("/index")
    public String index()
    {

        return "cloudCompany/agentlist";

    }

    //列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetCloudCompanyRequest request)
    {
        GetCloudCompanyResponse response = cloudCompanyServiceFacade.getAgentList(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }
    //添加公司
    @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(GetCloudCompanyRequest request,HttpSession httpSession)throws Exception{

        //获取登录帐号
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        int account=userInfo.getId();
        return cloudCompanyServiceFacade.addCloudCompany(request,account);
    }
    /**
     * 添加公司-页面
     */
    @RequestMapping("add/page")
    public ModelAndView addView(){
        return  new ModelAndView("cloudCompany/companyAdd");
    }
   /**
     * 上传文件
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file
            , HttpServletRequest request) throws UnknownHostException {
        String address = InetAddress.getLocalHost().getHostAddress().toString();

        String savePre = "images/uploadFile/";
        String cc=PhotoUtil.saveFile(file,request,savePre);
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        String  YOU="1530514343788.jpg";
       // String strBackUrl = "http://" + request.getServerName()+":"+request.getServerPort()+httpRequest.getContextPath()+"/resources/"+cc; //服务器地址
          String strBackUrl ="http://"+address+":"+request.getServerPort()+httpRequest.getContextPath()+"/resources/"+cc;
        return strBackUrl;
    }

}
