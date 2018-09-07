package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.dto.GetShopCompanyChargeRequest;
import com.joiest.jpf.dto.GetShopCompanyChargeResponse;
import com.joiest.jpf.entity.ShopCompanyChargeInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopCompanyChargeServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shopCompanyCharge")
public class ShopCompanyChargeController {

    @Autowired
    private ShopCompanyChargeServiceFacade shopCompanyChargeServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopCompanyCharge/list";
    }

    @RequestMapping("/caiwuIndex")
    public String caiwuIndex(){
        return "shopCompanyCharge/caiwuList";
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopCompanyChargeRequest request) {
        GetShopCompanyChargeResponse response= shopCompanyChargeServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/caiwuList")
    @ResponseBody
    public Map<String, Object> caiwuList(GetShopCompanyChargeRequest request) {
        GetShopCompanyChargeResponse response= shopCompanyChargeServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }

    /**
     * 添加页模板
     */
    @RequestMapping("/add/page")
    public ModelAndView addPage(ModelMap modelMap){

        return new ModelAndView("shopCompanyCharge/add", modelMap);

    }

    /**
     * 添加页功能
     */
    @RequestMapping(value ="/add/action", method = RequestMethod.POST)
    @ResponseBody
    public JpfResponseDto addAction( HttpServletRequest httpRequest,GetShopCompanyChargeRequest request) {

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());

        JpfResponseDto jpfResponseDto =  shopCompanyChargeServiceFacade.add(request);

        return jpfResponseDto;

    }

    /**
     * 上传文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file
            , HttpServletRequest request) throws UnknownHostException {

        String savePre = ConfigUtil.getValue("ROOT_PATH");
        String allpath = PhotoUtil.saveFile(file, savePre);

        // OSS上传excel文件
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("path",allpath);
        String url = ConfigUtil.getValue("CLOUD_API_URL")+"/oss/upload";
        String response = OkHttpUtils.postForm(url,requestMap);
        response = StringUtils.strip(response,"\"");
        response = StringUtils.stripEnd(response,"\"");

        return response;
    }


    /**
     * 欣豆公司列表页
     */
    @RequestMapping("/chargeCompanys")
    public ModelAndView companys(ModelMap modelMap){
        return new ModelAndView("shopCompanyCharge/companys", modelMap);

    }

    /**
     * 运营审核页
     */
    @RequestMapping("/audit/page")
    @ResponseBody
    public ModelAndView auditPage(String id,ModelMap modelMap){
        ShopCompanyChargeInfo shopCompanyChargeInfo= shopCompanyChargeServiceFacade.getOne(id);
        shopCompanyChargeInfo.setStatusCn(ManageConstants.COMPANYCHARGELIST.get(shopCompanyChargeInfo.getStatus().toString()));
        modelMap.addAttribute("shopCompanyChargeInfo",shopCompanyChargeInfo);
        modelMap.addAttribute("auditPageType",1);

        return new ModelAndView("shopCompanyCharge/audit", modelMap);

    }

    /**
     * 财务审核功能
     */
    @RequestMapping("/audit/action")
    @ResponseBody
    public JpfResponseDto auditAction(HttpServletRequest httpRequest,GetShopCompanyChargeRequest request){

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setCheckOperatorId(userInfo.getId().toString());
        request.setCheckOperatorName(userInfo.getUserName());

        request.setStatusCn(ManageConstants.COMPANYCHARGELIST.get(request.getStatus().toString()));
        JpfResponseDto jpfResponseDto = shopCompanyChargeServiceFacade.auditCompanyCharge(request);


        return jpfResponseDto;

    }


    /**
     * 财务审核页
     */
    @RequestMapping("/caiwu/audit/page")
    @ResponseBody
    public ModelAndView caiwuAuditPage(String id,ModelMap modelMap){
        ShopCompanyChargeInfo shopCompanyChargeInfo= shopCompanyChargeServiceFacade.getOne(id);
        shopCompanyChargeInfo.setStatusCn(ManageConstants.COMPANYCHARGELIST.get(shopCompanyChargeInfo.getStatus().toString()));
        modelMap.addAttribute("shopCompanyChargeInfo",shopCompanyChargeInfo);
        modelMap.addAttribute("auditPageType",2);

        return new ModelAndView("shopCompanyCharge/audit", modelMap);

    }

    /**
     * 财务审核功能
     */
    @RequestMapping("/caiwu/audit/action")
    @ResponseBody
    public JpfResponseDto caiwuAuditAction(HttpServletRequest httpRequest,GetShopCompanyChargeRequest request){

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setCheckOperatorId(userInfo.getId().toString());
        request.setCheckOperatorName(userInfo.getUserName());

        request.setStatusCn(ManageConstants.COMPANYCHARGELIST.get(request.getStatus().toString()));
        JpfResponseDto jpfResponseDto = shopCompanyChargeServiceFacade.caiWuCompanyCharge(request);


        return jpfResponseDto;

    }





}
