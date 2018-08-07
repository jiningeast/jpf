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
     */
    @RequestMapping("/sendZip")
    @ResponseBody
    public JpfResponseDto sendZip(String batchId){
        if (StringUtils.isBlank(batchId) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("未传入批次id");

            return jpfResponseDto;
        }

        ShopBatchInfo shopBatchInfo = shopBatchServiceFacade.getBatchById(batchId);

        //发送邮件
        String Subject="测试主题";
        String sendName="欣享服务";
        String Recipients="1174355934@qq.com";
        String RecipientsName="蔡磊";
        String Filepath="/home/images/excel/1531962634202.xlsx";//全路径
        String Filename="1531962634202.xlsx";//携带文件类型。xlsx
        String html="这是我发布的测试内容";//可以使用标签拼装
//        Boolean a=  SendMailUtil.sendMultipleEmail(Subject,sendName,Recipients,RecipientsName,Filepath,Filename,html);

        return new JpfResponseDto();
    }
}
