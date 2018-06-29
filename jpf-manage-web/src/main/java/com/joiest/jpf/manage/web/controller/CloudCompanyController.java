package com.joiest.jpf.manage.web.controller;


//import com.joiest.jpf.common.util.PhotoUtil;
import com.joiest.jpf.common.util.PhotoUtil;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import com.joiest.jpf.dto.GetCloudCompanyResponse;
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
  /*  @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(String name, String intro){
        return CloudCompanyServiceFacade.addCloudCompany(name,intro);
    }*/
    /**
     * 添加公司-页面
     */
    @RequestMapping("add/page")
    public ModelAndView addView(){
        return  new ModelAndView("cloudCompany/companyAdd");
    }
   /* *//**
     * 上传文件
     */
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file
            , HttpServletRequest request){
        //第一种返回页面的方法
        //model.addAttribute("img",PhotoUtil.saveFile(file,request));
        //第二种返回页面的方法
        String cc=PhotoUtil.saveFile(file,request);
        PhotoUtil.deleteFile("images/uploadFile/1530233577554.jpg",request);
        return "test";
    }

}
