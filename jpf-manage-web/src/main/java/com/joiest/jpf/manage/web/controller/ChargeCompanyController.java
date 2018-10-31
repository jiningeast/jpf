package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.util.SmsUtils;
import org.apache.commons.lang3.StringUtils;
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

    @RequestMapping("resetPwd")
    @ResponseBody
    public JpfResponseDto resetPwd(String id) {
        if(StringUtils.isBlank(id)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "ID不能为空");
        }
        ChargeCompanyInfo companyInfo = new ChargeCompanyInfo();
        companyInfo.setId(id);
        ChargeCompanyInfo record = chargeCompanyServiceFacade.getOne(companyInfo);
        if(record == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到记录");
        }
        String concatPhone = record.getContactPhone();

        Integer randPwd = ToolUtils.getRandomInt(100000,999999);
        String newPwd = Md5Encrypt.md5(randPwd.toString());
        String content = "尊敬的"+record.getContactName()+",您的商户"+record.getCompanyName()+"已重置密码为："+randPwd+".";

        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        chargeCompanyInfo.setId(id);

        chargeCompanyInfo.setPassword(randPwd.toString());

        chargeCompanyInfo.setUpdatetime(new Date());
        int updateRes = chargeCompanyServiceFacade.updateColumnByPrimaryKey(chargeCompanyInfo);
        if(updateRes == 1 ){
            //发送短信并记录操作记录
            Map<String,String> responParan = SmsUtils.send(concatPhone,content);
            if(responParan != null && responParan.size()>0){
                String requestUrl = responParan.get("requestUrl"); //请求的地址
                String requestParam = responParan.get("requestParam"); //请求的参数
                String response =  responParan.get("response");//返回的数据
                //json---转换代码---
                Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
                if( responseMap !=null && responseMap.containsKey("code") && responseMap.get("code").equals("10000")){


                }else{
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "短信发送失败");
                }

            }else{
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "短信发送异常");
            }

        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }
        JpfResponseDto jpfResponseDto = new JpfResponseDto();

        return jpfResponseDto;
    }
}
