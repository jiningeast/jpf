package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.ShopBatchCouponInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopBatchCouponServiceFacade;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopBatch")
public class ShopBatchController {

    @Autowired
    private ShopBatchServiceFacade shopBatchServiceFacade;

    @Autowired
    private ShopBatchCouponServiceFacade shopBatchCouponServiceFacade;

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
    @ResponseBody
    public JpfResponseDto submitBatch(ShopBatchRequest request, HttpServletRequest httpRequest){
        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());
        shopBatchServiceFacade.addBatchCoupon(request);

        return new JpfResponseDto();
    }

    /**
     * 查看批次详情页面
     */
    @RequestMapping("/detail")
    public ModelAndView detail(String batchId, ModelMap modelMap){
        modelMap.addAttribute("batchId",batchId);
        return new ModelAndView("shopBatch/detail",modelMap);
    }

    /**
     * 查看批次详情数据
     */
    @RequestMapping("/detailData")
    @ResponseBody
    public Map<String,Object> detailData(String batchId){
        List<ShopBatchCouponInfo> list =  shopBatchCouponServiceFacade.getCouponByBatchId(batchId);
        Map<String,Object> map = new HashMap<>();
        map.put("total",list.size());
        map.put("rows",list);

        return map;
    }
}
