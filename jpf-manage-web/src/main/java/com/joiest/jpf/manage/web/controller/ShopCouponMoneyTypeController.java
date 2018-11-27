package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCouponMoneyType;
import com.joiest.jpf.facade.ShopCouponMoneyTypeServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/shopCouponMoneyType")
public class ShopCouponMoneyTypeController {

    private static final Logger logger = LogManager.getLogger(ShopCouponMoneyTypeController.class);
    @Autowired
    private ShopCouponMoneyTypeServiceFacade shopCouponMoneyTypeServiceFacade;

    /**
     * 欣券面值列表页面
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(Model model){
        //查询面值。查询状态
        List<BigDecimal> moneyList = shopCouponMoneyTypeServiceFacade.getMoneyList();
        model.addAttribute("moneyList",moneyList);
        return new ModelAndView("shopCouponMoneyType/couponMoneyTypeList");
    }

    /**
     * 欣券金额列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(PayShopCouponMoneyType payShopCouponMoneyType,long page, long rows) {
        Map<String, Object> map = new HashMap<>();
        map.put("rows", shopCouponMoneyTypeServiceFacade.getList(payShopCouponMoneyType,page, rows));
        map.put("total", shopCouponMoneyTypeServiceFacade.getCount(payShopCouponMoneyType));
        return map;
    }

    /**
     * 跳转到欣券添加页面
     * @return
     */
    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        return new ModelAndView("shopCouponMoneyType/couponMoneyTypeAdd");
    }

    /**
     * 新增欣券面值
     * @param payShopCouponMoneyType
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(PayShopCouponMoneyType payShopCouponMoneyType){
        JpfResponseDto jpfResponseDto =new JpfResponseDto();
        try{
            //查询是否已经添加了
            List<PayShopCouponMoneyType> list = shopCouponMoneyTypeServiceFacade.getByMoney(payShopCouponMoneyType);
            if(list!=null&&list.size()>0){
                jpfResponseDto.setResponseError("-1","欣券面值已经存在，不能重复添加");
            }
            shopCouponMoneyTypeServiceFacade.add(payShopCouponMoneyType);
        }catch (Exception e){
            logger.error("欣券面值添加失败"+e.getMessage());
            jpfResponseDto.setResponseError("-1","欣券面值添加失败");
        }
        return jpfResponseDto;
    }

    /**
     * 跳转到欣券添加页面
     * @return
     */
    @RequestMapping("/goUpdate")
    public ModelAndView goUpdate(String id, HttpServletRequest request, Model model){
        PayShopCouponMoneyType payShopCouponMoneyType =shopCouponMoneyTypeServiceFacade.getById(id);
        model.addAttribute("payShopCouponMoneyType",payShopCouponMoneyType);
        return new ModelAndView("shopCouponMoneyType/couponMoneyTypeEdit");
    }

    /**
     * 编辑欣券面值
     * @param payShopCouponMoneyType
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public JpfResponseDto edit(PayShopCouponMoneyType payShopCouponMoneyType){
        JpfResponseDto jpfResponseDto =new JpfResponseDto();
        try{
            //查询是否已经存在,排除本身
            List<PayShopCouponMoneyType> list = shopCouponMoneyTypeServiceFacade.getByMoney(payShopCouponMoneyType);
            if((list!=null&&list.size()>0)&&(!list.get(0).getId().equals(payShopCouponMoneyType.getId()))){
                jpfResponseDto.setResponseError("-1","欣券面值已经存在，不能重复");
            }
            shopCouponMoneyTypeServiceFacade.edit(payShopCouponMoneyType);
        }catch (Exception e){
            logger.error("欣券面值编辑失败"+e.getMessage());
            jpfResponseDto.setResponseError("-1","欣券面值编辑失败");
        }
        return jpfResponseDto;
    }
}
