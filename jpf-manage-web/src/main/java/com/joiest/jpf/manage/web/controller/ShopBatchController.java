package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.SendMailUtil;
import com.joiest.jpf.dto.ShopBatchCouponResponse;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.ShopBatchCouponInfo;
import com.joiest.jpf.entity.ShopBatchInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopBatchCouponServiceFacade;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public JpfResponseDto submitBatch(ShopBatchRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());

        return shopBatchServiceFacade.addBatchCoupon(request,httpResponse);
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
    public Map<String,Object> detailData(String batchId, int page, int rows){
        ShopBatchCouponResponse response =  shopBatchCouponServiceFacade.getCouponByBatchId(batchId, page, rows);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }

    /**
     * 发送含有excel的压缩包
     * @Params
     * Subject : 主题
     * sendNAME : 发送人名称
     * Recipients : 接收邮箱地址
     * RecipienName : 接收人姓名
     * FilePath : 发送附件地址全路径
     * fileName : 文件名字携带文件格式的如a.xls;
     * html : 发送的html内容
     */
    @RequestMapping("/sendZip")
    @ResponseBody
    public JpfResponseDto sendZip(String batchId) throws Exception{
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if (StringUtils.isBlank(batchId) ){
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("未传入批次id");

            return jpfResponseDto;
        }

        ShopBatchInfo shopBatchInfo = shopBatchServiceFacade.getBatchById(batchId);
        if ( StringUtils.isBlank(shopBatchInfo.getReceiveEmail()) ) {
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("该企业尚未设置接收邮箱");

            return jpfResponseDto;
        }
        if ( StringUtils.isBlank(shopBatchInfo.getReceivePhone()) ) {
            jpfResponseDto.setRetCode("10003");
            jpfResponseDto.setRetMsg("该企业尚未设置接收手机");

            return jpfResponseDto;
        }

        return shopBatchServiceFacade.sendEmailSms(batchId);
    }
}
