package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.PhotoUtil;
import com.joiest.jpf.dto.GetChargeCompanyChargeRequest;
import com.joiest.jpf.dto.GetChargeCompanyChargeResponse;
import com.joiest.jpf.entity.ChargeCompanyChargeInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ChargeCompanyChargeServiceFacade;
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

@Controller
@RequestMapping("chargeCompanyCharge")
public class ChargeCompanyChargeController {

    @Autowired
    private ChargeCompanyChargeServiceFacade chargeCompanyChargeServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "chargeCompanyCharge/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetChargeCompanyChargeRequest request){
        GetChargeCompanyChargeResponse response = chargeCompanyChargeServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }

    /**
     * 新增充值页面
     */
    @RequestMapping("addPage")
    @ResponseBody
    public ModelAndView addPage(){
        return new ModelAndView("chargeCompanyCharge/chargeAdd");
    }

    /**
     * 欣豆公司列表页
     */
    @RequestMapping("/chargeCompanys")
    public ModelAndView companys(ModelMap modelMap){
        return new ModelAndView("chargeCompanyCharge/companys", modelMap);
    }

    /**
     * 新增充值动作
     */
    @RequestMapping("addAction")
    @ResponseBody
    public JpfResponseDto addAction(ChargeCompanyChargeInfo info,HttpServletRequest httpRequest){
        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        info.setOperatorId(""+userInfo.getId());
        info.setOperatorName(userInfo.getUserName());
        info.setStatus((byte)0);
        info.setAddtime(new Date());
        int insertRes = chargeCompanyChargeServiceFacade.addRecord(info);
        if ( insertRes > 0 ){
            return new JpfResponseDto();
        }else{
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("新增失败");

            return jpfResponseDto;
        }
    }

    /**
     * 上传文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file
            , HttpServletRequest request) throws UnknownHostException {

        String savePre = com.joiest.jpf.common.util.ConfigUtil.getValue("ROOT_PATH");
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
     * 充值订单财务
     */
    @RequestMapping("indexCaiwu")
    public String indexCaiwu(){
        return "chargeCompanyCharge/indexCaiwu";
    }

    /**
     * 充值订单财务审核页
     */
    @RequestMapping("/caiwuAudit")
    @ResponseBody
    public ModelAndView caiwuAuditPage(String id,ModelMap modelMap){
        ChargeCompanyChargeInfo chargeCompanyChargeInfo= chargeCompanyChargeServiceFacade.getOne(id);
       // shopCompanyChargeInfo.setStatusCn(ManageConstants.COMPANYCHARGELIST.get(shopCompanyChargeInfo.getStatus().toString()));
        modelMap.addAttribute("chargeCompanyChargeInfo",chargeCompanyChargeInfo);
        modelMap.addAttribute("auditPageType",2);

        return new ModelAndView("chargeCompanyCharge/audit", modelMap);
    }
    /**
     * 财务审核功能
     */
    @RequestMapping("/caiwuAction")
    @ResponseBody
    public JpfResponseDto caiwuAction(HttpServletRequest httpRequest,GetChargeCompanyChargeRequest request){

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setCheckOperatorId(userInfo.getId().toString());
        request.setCheckOperatorName(userInfo.getUserName());

        JpfResponseDto jpfResponseDto = chargeCompanyChargeServiceFacade.caiWuCompanyCharge(request);

        return jpfResponseDto;

    }


}
