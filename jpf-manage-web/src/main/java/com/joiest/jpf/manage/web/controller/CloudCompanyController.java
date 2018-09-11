package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import com.joiest.jpf.dto.GetCloudCompanyResponse;
import com.joiest.jpf.dto.GetCloudCompanysRequest;
import com.joiest.jpf.dto.GetCloudCompanysResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 代理公司管理
 */
@Controller
@RequestMapping("/cloudCompany")
public class CloudCompanyController {

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private BankCardServiceFacade bankCardServiceFacade;
    @Autowired
    private CloudCompanyBankServiceFacade cloudCompanyBankServiceFacade;
    @Autowired
    private BankServiceFacade bankServiceFacade;

    @Autowired
    private CloudInterfaceStreamServiceFacade cloudInterfaceStreamServiceFacade;
    @RequestMapping("/index")
    public String index() {

        return "cloudCompany/agentlist";

    }

    //代理公司列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(GetCloudCompanyRequest request) {
        GetCloudCompanyResponse response = cloudCompanyServiceFacade.getAgentList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    @RequestMapping("/indexSale")
    public String indexSale() {

        return "cloudCompany/salelist";

    }

    //列表
    @RequestMapping("listSale")
    @ResponseBody
    public Map<String, Object> listSale(GetCloudCompanyRequest request) {
        GetCloudCompanyResponse response = cloudCompanyServiceFacade.getSaleList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    //添加公司
    @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(GetCloudCompanyRequest request, HttpSession httpSession,HttpServletRequest serRequest) throws Exception {

        //获取登录帐号
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        int account = userInfo.getId();
        String ipAddress= ToolUtils.getIpAddr(serRequest);
        // 根据卡号查询银行编码
        //获取银行信息
        BankInfo bankInfos = bankServiceFacade.getBankInfo(request.getBankid());


        Date date =new Date();
        JpfResponseDto redDto =  cloudCompanyServiceFacade.addCloudCompany(request, account,ipAddress,bankInfos);
        //根据卡号获取银行卡所属信息



        //发送短信
        if(redDto.getRetCode().equals("10002")){
            String passlogin =redDto.getRemark();//获取登录密码
            String mobile = request.getPhone();
            String content ="尊敬的用户您的账号已经开通：账号为"+request.getLinkemail()+"密码为"+passlogin;
            String dateTime = date.toString();
            Map<String,Object> map = new HashMap<>();
            map.put("mobile",mobile);
            map.put("content",content);
            map.put("dateTime",dateTime);

            //排序转换
            Map<String,Object> treeMap = new TreeMap<>();
            treeMap.putAll(map);

            String respos = ToolUtils.mapToUrl(treeMap);

            //调用配置文件ConfigUtil.getValue("API_SECRET")

            String selfSign = Md5Encrypt.md5(respos+ com.joiest.jpf.common.util.ConfigUtil.getValue("API_SECRET")).toUpperCase();

            map.put("sign",selfSign);

            String response = OkHttpUtils.postForm(com.joiest.jpf.common.util.ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/sendSmsApi",map);

            //json---转换代码---
            Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
            String result=responseMap.get("code");

            // 增加==短信接口流水==
               CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
               cloudInterfaceStreamInfo.setType((byte)0);
               cloudInterfaceStreamInfo.setRequestUrl(ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/sendSmsApi");
               cloudInterfaceStreamInfo.setRequestContent(respos);
               cloudInterfaceStreamInfo.setResponseContent(result);
               cloudInterfaceStreamInfo.setAddtime(new Date());
               cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);

        }


        return new JpfResponseDto();
    }

    /**
     * 添加公司-页面
     */
    @RequestMapping("add/page")
    public ModelAndView addView() {
        return new ModelAndView("cloudCompany/companyAdd");
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
     * 修改公司-页面
     */
    @RequestMapping("edit/page")
    public ModelAndView editView(String id, int type, ModelMap modelMap) {
        //取出当前公司的信息
        CloudCompanyInfo cloudCompanyInfo = cloudCompanyServiceFacade.getCompanyOne(id, type);
        if (type == 1) {
            cloudCompanyInfo.setType((byte)1);
        } else {
            cloudCompanyInfo.setType((byte)0);
        }
        String Merchno=cloudCompanyInfo.getMerchNo();
        //商户对公帐户信息
        CloudCompanyBankInfo cloudCompanyBankInfo = cloudCompanyBankServiceFacade.getCompanyBankInfoByMerchNo(Merchno);
        modelMap.addAttribute("cloudCompanyInfo", cloudCompanyInfo);
        modelMap.addAttribute("cloudCompanyBankInfo", cloudCompanyBankInfo);
        return new ModelAndView("cloudCompany/companyEdit", modelMap);
    }

    /**
     * 获取公司信息，不区分类型
     */
    @RequestMapping("/getCompanys")
    @ResponseBody
    public Map<String, Object> getCompanys(GetCloudCompanysRequest request){
        GetCloudCompanysResponse response = cloudCompanyServiceFacade.getAllCompanys(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }
    /**
     * 修改公司-提交
     */
    @RequestMapping("edit/action")
    @ResponseBody
    public JpfResponseDto edit(GetCloudCompanyRequest request, HttpSession httpSession) throws Exception {
        //获取登录帐号

        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        int account = userInfo.getId();
        // 根据卡号查询银行编码
        //获取银行信息
        BankInfo bankInfos = bankServiceFacade.getBankInfo(request.getBankid());

        return cloudCompanyServiceFacade.editCloudCompany(request, account,bankInfos);
    }

    /**
     * 锁定公司
     */
    @RequestMapping("delCompany")
    @ResponseBody
    public JpfResponseDto delCompany(String merchNo, int type) {

        return cloudCompanyServiceFacade.delCompany(merchNo, type);
    }
}
