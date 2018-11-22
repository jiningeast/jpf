package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/market-manager/managerLogin")
public class ManagerLoginController {

    private static final Logger logger = LogManager.getLogger(ManagerLoginController.class);

    @Autowired
    private ShopCompanyServiceFacade shopCompanyServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    /**
     * 登录方法
     * @param request
     * @return
     */
    @RequestMapping(value="loginIn",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String LoginIn(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //验证用户名密码不能为空
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USERINFO_VALID_FAIL.getCode(),JpfInterfaceErrorInfo.USERINFO_VALID_FAIL.getDesc(),null);
        }
        //查询用户信息  待完善
        PayShopCompany company = shopCompanyServiceFacade.getCompanyByUserNamnAndPasswd(Base64CustomUtils.base64Decoder(userName),Base64CustomUtils.base64Decoder(password));
        if(company==null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_PASSWD_ERROR.getCode(),JpfInterfaceErrorInfo.USER_PASSWD_ERROR.getDesc(),null);
        }else{
            if(company.getStatus()==0){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ABNORMAL_STATUS.getCode(),JpfInterfaceErrorInfo.ABNORMAL_STATUS.getDesc(),null);
            }else{
                //生成token
                String token =  AESUtils.encrypt(company.getMerchNo()+company.getId(),ConfigUtil.getValue("AES_KEY"));
                String value = AESUtils.encrypt(company.getId(), ConfigUtil.getValue("AES_KEY"));
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("companyId",company.getId());
                map.put("companyName",company.getCompanyName());
                map.put("merchNo",company.getMerchNo());
                map.put("token",token);
               // map.put("isFirst",company.get)
                redisCustomServiceFacade.set(ConfigUtil.getValue("MARKETMANGER_LOGIN_KEY") + token, value,30*60);
                //更新登录的状态值
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),map);
            }
        }

    }

    /**
     * 未登录，请重新登录
     * @return
     */
    @RequestMapping(value="loginFail",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String loginFail(){
        logger.info("===============未登录处理的方法 start===============");
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(),JpfInterfaceErrorInfo.NOTlOGIN.getDesc(),null);
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping(value="editPass",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String editPass(HttpServletRequest request){
        String companyId= request.getParameter("companyId");
        String oldPass= request.getParameter("oldPass");
        String newPass= request.getParameter("newPass");
        PayShopCompany company = shopCompanyServiceFacade.getById(Base64CustomUtils.base64Decoder(companyId));
        if(StringUtils.equals(SHA1.getInstance().getMySHA1Code(Base64CustomUtils.base64Decoder(oldPass)),company.getLoginPwd())){
            //判断密码
            company.setLoginPwd(SHA1.getInstance().getMySHA1Code(Base64CustomUtils.base64Decoder(newPass)));
            shopCompanyServiceFacade.updateCompanyRecord(company);
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),null);
        }else{
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.OLDPASSERROR.getCode(),JpfInterfaceErrorInfo.OLDPASSERROR.getDesc(),null);
        }
    }

    /**
     * 登出接口
     * @param request
     * @return
     */
    @RequestMapping(value="logout",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String logout(HttpServletRequest request){
        String token = request.getParameter("token");
        redisCustomServiceFacade.remove(ConfigUtil.getValue("MARKETMANGER_LOGIN_KEY") + token);
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.LOGOUT.getCode(),JpfInterfaceErrorInfo.LOGOUT.getDesc(),null);
    }
}
