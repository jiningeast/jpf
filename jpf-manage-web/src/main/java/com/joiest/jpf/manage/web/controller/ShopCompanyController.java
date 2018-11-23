package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.dto.GetShopCompanyRequest;
import com.joiest.jpf.dto.GetShopCompanyResponse;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shopCompany")
public class ShopCompanyController {

    @Autowired
    private ShopCompanyServiceFacade shopCompanyServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopCompany/companyList";
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopCompanyRequest request) {
        GetShopCompanyResponse response= shopCompanyServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 添加公司-页面
     */
    @RequestMapping("addCompany/page")
    public ModelAndView addView() {
        return new ModelAndView("shopCompany/companyAdd");
    }

    //添加公司
    @RequestMapping("/add")
    @ResponseBody

    public JpfResponseDto add(GetShopCompanyRequest request, HttpSession httpSession){

        //获取登录帐号
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        int account = userInfo.getId();
        //发送短信
        return shopCompanyServiceFacade.addCompany(request,account);
    }
    /**
     * 修改公司-页面
     */
    @RequestMapping("edit/page")
    public ModelAndView editView(String id, ModelMap modelMap) {
        //取出当前公司的信息
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(id);
        modelMap.addAttribute("shopCompanyInfo", shopCompanyInfo);
        return new ModelAndView("shopCompany/companyEdit", modelMap);
    }
    /**
     * 修改公司-方法
     */
    @RequestMapping("/edit")
    @ResponseBody
    public JpfResponseDto edit(GetShopCompanyRequest request, HttpSession httpSession){

        //获取登录帐号
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);
        int account = userInfo.getId();
        //发送短信
        return shopCompanyServiceFacade.editCompany(request,account);
    }

    /**
     * 停用和启用公司
     */
    @RequestMapping("/delCompanyShop")
    @ResponseBody
    public JpfResponseDto delCompanyShop(String merchNo,int type) {

        return shopCompanyServiceFacade.delCompanyShop(merchNo, type);
    }

    /**
     * 开通账户
     * @param id  商户id
     * @return
     */
    @RequestMapping(value="openAccount",method = RequestMethod.POST)
    @ResponseBody
    public JpfResponseDto openAccount(String id){
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        try{
            shopCompanyServiceFacade.openAccount(id);
        }catch (Exception e){
            jpfResponseDto.setResponseError("-1",JpfErrorInfo.OPENACCOUNTFAIL.desc());
            return jpfResponseDto;
        }
        return jpfResponseDto;
    }

}
