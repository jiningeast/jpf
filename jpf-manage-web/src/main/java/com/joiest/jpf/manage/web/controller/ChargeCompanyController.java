package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chargeCompany")
public class ChargeCompanyController {

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "chargeCompany/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetChargeCompanyRequest request){
        GetChargeCompanyResponse response = chargeCompanyServiceFacade.getRecords(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }

    @RequestMapping("addPage")
    @ResponseBody
    public ModelAndView addPage(){
        return new ModelAndView("chargeCompany/companyAdd");
    }

    @RequestMapping("addAction")
    @ResponseBody
    public JpfResponseDto addAction(String companyName, String contactName, String contactPhone, byte isFreeze, HttpServletRequest httpRequest){
        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        chargeCompanyInfo.setCompanyName(companyName);
        chargeCompanyInfo.setContactName(contactName);
        chargeCompanyInfo.setContactPhone(contactPhone);
        chargeCompanyInfo.setOperatorId(""+userInfo.getId());
        chargeCompanyInfo.setOperatorName(userInfo.getUserName());
        chargeCompanyInfo.setIsFreeze(isFreeze);
        chargeCompanyServiceFacade.addRecord(chargeCompanyInfo);

        return new JpfResponseDto();
    }

    @RequestMapping("editPage")
    @ResponseBody
    public ModelAndView editPage(String id,ModelMap modelMap){
        //取出当前公司的信息
        ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(id);
        modelMap.addAttribute("chargeCompanyInfo", chargeCompanyInfo);

        return new ModelAndView("chargeCompany/companyEdit");
    }

    @RequestMapping("editAction")
    @ResponseBody
    public JpfResponseDto editAction(String id,String companyName, String contactName, String contactPhone, byte isFreeze, byte isDel){
        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        chargeCompanyInfo.setId(id);
        chargeCompanyInfo.setCompanyName(companyName);
        chargeCompanyInfo.setContactName(contactName);
        chargeCompanyInfo.setContactPhone(contactPhone);
        chargeCompanyInfo.setIsFreeze(isFreeze);
        chargeCompanyInfo.setIsDel(isDel);
        chargeCompanyInfo.setUpdatetime(new Date());
        int updateRes = chargeCompanyServiceFacade.updateColumnByPrimaryKey(chargeCompanyInfo);
        if ( updateRes > 0 ){
            return new JpfResponseDto();
        }else{
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("更新失败");

            return jpfResponseDto;
        }
    }
}
