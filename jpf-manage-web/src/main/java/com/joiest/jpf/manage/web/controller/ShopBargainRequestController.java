package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopBargainRequestRequest;
import com.joiest.jpf.dto.GetShopBargainRequestResponse;
import com.joiest.jpf.dto.GetShopCompanyRequest;
import com.joiest.jpf.dto.GetShopCompanyResponse;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopBargainRequestServiceFacade;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shopBargainRequest")
public class ShopBargainRequestController {

    @Autowired
    private ShopBargainRequestServiceFacade shopBargainRequestServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopBargainRequest/List";
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopBargainRequestRequest request) {
        GetShopBargainRequestResponse response= shopBargainRequestServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }


    /**
     * 开启关闭收购信息
     */
    @RequestMapping("/delShopBargain")
    @ResponseBody
    public JpfResponseDto delShopBargain(String id,int type) {

        return shopBargainRequestServiceFacade.delShopBargain(id, type);
    }

}
