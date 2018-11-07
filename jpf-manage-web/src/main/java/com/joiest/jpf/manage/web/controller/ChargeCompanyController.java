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
import com.joiest.jpf.entity.ChargeInterfaceStreamInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeInterfaceStreamFacade;
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

    @Autowired
    private ChargeInterfaceStreamFacade chargeInterfaceStreamFacade;

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
    public JpfResponseDto addAction(String companyName, String contactName, String contactPhone, byte isFreeze,String password, HttpServletRequest httpRequest){
        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        chargeCompanyInfo.setCompanyName(companyName);
        String pwdStr = Md5Encrypt.md5(password);
        chargeCompanyInfo.setPassword(pwdStr);
        chargeCompanyInfo.setContactName(contactName);
        chargeCompanyInfo.setContactPhone(contactPhone);
        chargeCompanyInfo.setOperatorId(""+userInfo.getId());
        chargeCompanyInfo.setOperatorName(userInfo.getUserName());
        chargeCompanyInfo.setIsFreeze(isFreeze);
        int record = chargeCompanyServiceFacade.addRecord(chargeCompanyInfo);
        if(record ==1 ){
            int flag = 0; //屏蔽短信接口
            if( flag == 1 ){
                //发送短信并记录操作记录 短信内容需要调整 目前仅测试使用 ???????????????????????????
                String content = "尊敬的"+contactName+"，您的商户"+companyName+"已重置密码为："+password+"";
                Map<String,String> responParan = SmsUtils.send(contactPhone,content);
                if(responParan != null && responParan.size()>0){
                    String requestUrl = responParan.get("requestUrl"); //请求的地址
                    String requestParam = responParan.get("requestParam"); //请求的参数
                    String response =  responParan.get("response");//返回的数据
                    //json---转换代码---
                    Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
                    if( responseMap !=null && responseMap.containsKey("code") && responseMap.get("code").equals("10000")){
                        ChargeInterfaceStreamInfo interfaceStream = new ChargeInterfaceStreamInfo();
                        // 充值平台商户密码重置
                        interfaceStream.setType((byte)2);
                        interfaceStream.setRequestUrl(requestUrl);
                        interfaceStream.setRequestParam(requestParam);
                        interfaceStream.setResponse(response);
                        interfaceStream.setAddtime(new Date());
                        int count = chargeInterfaceStreamFacade.addStream(interfaceStream);
                        if( count != 1 ){
                            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "短信日志记录失败");
                        }
                    }else{
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "短信发送失败");
                    }

                }else{
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "短信发送异常");
                }
            }
        }
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
    public JpfResponseDto editAction(String id,String companyName, String contactName, String contactPhone, byte isFreeze, byte isDel,String password){
        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        chargeCompanyInfo.setId(id);
        chargeCompanyInfo.setCompanyName(companyName);
        chargeCompanyInfo.setContactName(contactName);
        if(StringUtils.isNotBlank(password)){
            String pwdStr = Md5Encrypt.md5(password);
            chargeCompanyInfo.setPassword(pwdStr);
        }
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
        ChargeCompanyInfo record = chargeCompanyServiceFacade.getRecordByPrimaryKey(id);
        if(record == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到记录");
        }
        String concatPhone = record.getContactPhone();
        if(!ToolUtils.checkPhone(concatPhone)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "手机号["+concatPhone+"]验证失败");
        }

        Integer randPwd = ToolUtils.getRandomInt(100000,999999);
        String newPwd = Md5Encrypt.md5(randPwd.toString());
        String content = "尊敬的"+record.getContactName()+"，您的商户"+record.getCompanyName()+"已重置密码为："+randPwd+"";

        Date date = new Date();
        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        chargeCompanyInfo.setId(id);
        chargeCompanyInfo.setPassword(newPwd);
        chargeCompanyInfo.setUpdatetime(date);
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
                    ChargeInterfaceStreamInfo interfaceStream = new ChargeInterfaceStreamInfo();
                    // 充值平台商户密码重置
                    interfaceStream.setType((byte)2);
                    interfaceStream.setRequestUrl(requestUrl);
                    interfaceStream.setRequestParam(requestParam);
                    interfaceStream.setResponse(response);
                    interfaceStream.setAddtime(date);
                    int count = chargeInterfaceStreamFacade.addStream(interfaceStream);
                    if( count != 1 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "短信日志记录失败");
                    }
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
