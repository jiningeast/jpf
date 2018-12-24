package com.joiest.jpf.charge.manage.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chargeCompany")
public class ChargeCompanyController {

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    private ChargeCompanyInfo chargeCompanyInfo;

    private String merchNo;

    /**
     * 个人登录验证
     */
    @RequestMapping(value ="loginMerch",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String loginMerch(String data){

        if ( StringUtils.isBlank(data) || data==null  )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

        Map<String,Object> requestMap = null;
        try {
            String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
            String requestStr = Base64CustomUtils.base64Decoder(dataStr);
            requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
            if(requestMap==null){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }
        //验证参数
        String loginName=(String) requestMap.get("name");
        String password=(String) requestMap.get("password");
        if(StringUtils.isBlank(loginName)|| StringUtils.isBlank(password)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "账号或密码不能为空", null);
        }
        ChargeCompanyInfo chargeCompanyInfo =chargeCompanyServiceFacade.getRecordByMerchNo(loginName);
        if(chargeCompanyInfo==null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "账户不存在", null);
        }

        if(!Md5Encrypt.md5(password).equals(chargeCompanyInfo.getPassword())){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), " 密码错误！", null);
        }
        if(chargeCompanyInfo.getIsFreeze().equals("1")){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), " 用户已冻结！", null);
        }
        Map<String,String>person= new HashMap<>();
        String token = AESUtils.encrypt(chargeCompanyInfo.getMerchNo(),ConfigUtil.getValue("AES_KEY"));//key值
        String val = chargeCompanyInfo.getMerchNo();//value值
        person.put("token",token);
        person.put("userName",chargeCompanyInfo.getCompanyName());
        person.put("merchNo",chargeCompanyInfo.getMerchNo());
        redisCustomServiceFacade.set(ConfigUtil.getValue("MANAGE_LOGIN_KEY") + token, val, Long.parseLong(ConfigUtil.getValue("WEIXIN_LOGIN_EXPIRE_30")) );
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "请求数据成功",person );
    }
    /**
     * 基本信息接口
     */
    @RequestMapping(value ="information",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String information(){

        Map<String,String>userInfo=new HashMap<>();

        userInfo.put("merchNo",chargeCompanyInfo.getMerchNo());
        userInfo.put("companyName",chargeCompanyInfo.getCompanyName());
        userInfo.put("money",chargeCompanyInfo.getMoney().toString());
        userInfo.put("contactName",chargeCompanyInfo.getContactName());
        userInfo.put("contactPhone",chargeCompanyInfo.getContactPhone());
        userInfo.put("isFreeze",chargeCompanyInfo.getIsFreeze().toString());

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",userInfo);

    }

    /**
     * 修改密码接口
     */
    @RequestMapping(value ="editPassword",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String editPassword(String data){

        if ( StringUtils.isBlank(data) || data==null  )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

        Map<String,Object> requestMap = null;
        try {
            String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
            String requestStr = Base64CustomUtils.base64Decoder(dataStr);
            requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
            if(requestMap==null){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }
        //接收参数
        String oldPass=(String) requestMap.get("oldPass");
        String newPass=(String) requestMap.get("newPass");
        String configPass=(String) requestMap.get("configPass");
        if(StringUtils.isBlank(oldPass)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"原密码不能为空！",null);
        }
        if(StringUtils.isBlank(newPass)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"新密码不能为空！",null);
        }
        if(StringUtils.isBlank(configPass)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"确认密码不能为空！",null);
        }
        if(!newPass.equals(configPass)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"两次密码不一致！",null);
        }
        if(newPass.equals(oldPass)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"新老密码相同无需修改！",null);
        }
        //验证字符串长度
       /* if(newPass.length()>6){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"新密码过长！",null);
        }*/
        //查询原密码是否输入正确==>
        if(chargeCompanyInfo==null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"用户不存在！",null);
        }
        if(!Md5Encrypt.md5(oldPass).equals(chargeCompanyInfo.getPassword())){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"原密码错误！",null);
        }
        JpfResponseDto jpfdto= null;
        try {
            jpfdto = chargeCompanyServiceFacade.updatePassword(merchNo,oldPass,newPass);
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"修改失败！",null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"修改成功",null);
    }

    /**
     * 退出接口
     */
    @RequestMapping(value ="logout",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String logout(HttpServletRequest request){
        //删除redis里面的数据
        String token = request.getHeader("Token");
        redisCustomServiceFacade.remove(ConfigUtil.getValue("MANAGE_LOGIN_KEY") + token);
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "退出成功",null );
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request, HttpServletResponse response)
    {

        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("MANAGE_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByMerchNo(openId_encrypt);
            merchNo = chargeCompanyInfo.getMerchNo();
        }
    }

}
