package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopBatch")
public class ShopBatchController {

    @Autowired
    private ShopBatchServiceFacade shopBatchServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("shopBatch/index");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(ShopBatchRequest shopBatchRequest){
        ShopBatchResponse shopBatchResponse = shopBatchServiceFacade.getBatches(shopBatchRequest);

        Map<String,Object> map = new HashMap<>();
        map.put("total",shopBatchResponse.getCount());
        map.put("rows",shopBatchResponse.getList());

        return map;
    }

    @RequestMapping("/addBatch")
    public ModelAndView addBatch(){
        return new ModelAndView("shopBatch/addBatch");
    }

    /**
     * 查询公司页
     */
    @RequestMapping("/companys")
    public ModelAndView companys(){
        return new ModelAndView("shopBatch/companys");
    }

    /**
     * 提交券批次
     */
    @RequestMapping("/submitBatch")
    public int submitBatch(ShopBatchRequest request, HttpServletRequest httpRequest){
        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());
        shopBatchServiceFacade.addBatchCoupon(request);

        return 1;
    }
}
