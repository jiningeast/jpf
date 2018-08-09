package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.ShopBatchCouponResponse;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.ShopBatchInfo;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.entity.ShopInterfaceStreamInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.util.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopBatch")
public class ShopBatchController {

    @Autowired
    private ShopBatchServiceFacade shopBatchServiceFacade;

    @Autowired
    private ShopBatchCouponServiceFacade shopBatchCouponServiceFacade;

    @Autowired
    private ShopCompanyServiceFacade shopCompanyServiceFacade;

    @Autowired
    private ShopInterfaceStreamServiceFacade shopInterfaceStreamServiceFacade;

    @Autowired
    private ShopCouponRemainServiceFacade shopCouponRemainServiceFacade;

    private static final Logger logger = LogManager.getLogger(ShopBatchController.class);

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
    @Transactional
    public JpfResponseDto sendZip(String batchId) throws Exception{
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if (StringUtils.isBlank(batchId) ){
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("未传入批次id");

            return jpfResponseDto;
        }

        ShopBatchInfo shopBatchInfo = shopBatchServiceFacade.getBatchById(batchId);
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(shopBatchInfo.getCompanyId());
        if ( shopCompanyInfo.getStatus() != 1 ){
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("商户已停用，无法继续操作");

            return jpfResponseDto;
        }
        if ( shopBatchInfo.getEmailStatus() == 1 && shopBatchInfo.getSmsStatus() == 1 ){
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("EMAIL已发送过，无法再次发送");

            return jpfResponseDto;
        }
        if ( StringUtils.isBlank(shopCompanyInfo.getReceiveEmail()) ) {
            jpfResponseDto.setRetCode("10003");
            jpfResponseDto.setRetMsg("该企业尚未设置接收邮箱");

            return jpfResponseDto;
        }
        if ( StringUtils.isBlank(shopCompanyInfo.getReceivePhone()) ) {
            jpfResponseDto.setRetCode("10004");
            jpfResponseDto.setRetMsg("该企业尚未设置接收手机");

            return jpfResponseDto;
        }

        // 发送email
        int emailRes = shopBatchServiceFacade.sendEmail(batchId);
        if ( emailRes == 1 ){
            // email发送成功则发送短信
            String mobile = shopCompanyInfo.getReceivePhone();
            String content = "已将批次号为" + shopBatchInfo.getBatchNo() + "的欣券激活码发送至您的邮箱，附件压缩包的密码是：" + shopBatchInfo.getZipPassword();
            Map<String,String> smsResMap = SmsUtils.send(mobile,content);
            Map<String,String> responseMap = JsonUtils.toObject(smsResMap.get("response"),Map.class);
            if ( responseMap.get("code").equals("10000") ){
                // 短信发送成功更新批次表的状态为短信发送成功
                ShopBatchInfo shopBatchInfoUpdate = new ShopBatchInfo();
                shopBatchInfoUpdate.setId(batchId);
                shopBatchInfoUpdate.setReceivePhone(mobile);
                shopBatchInfoUpdate.setSmsContent(content);
                shopBatchInfoUpdate.setSmsStatus((byte)1);
                shopBatchInfoUpdate.setSmsTime(new Date());
                shopBatchInfoUpdate.setStatus((byte)2);

                shopBatchServiceFacade.updateColumnById(shopBatchInfoUpdate);

                // 添加短信流水
                ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
                shopInterfaceStreamInfo.setType((byte)1);
                shopInterfaceStreamInfo.setRequestUrl(smsResMap.get("requestUrl"));
                shopInterfaceStreamInfo.setRequestContent(smsResMap.get("requestParam"));
                shopInterfaceStreamInfo.setResponseContent(smsResMap.get("response"));
                shopInterfaceStreamInfo.setBatchId(batchId);
                shopInterfaceStreamInfo.setAddtime(new Date());
                shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);
            }
        }else if ( emailRes == -1 ){
            jpfResponseDto.setRetCode("10005");
            jpfResponseDto.setRetMsg("发送邮件失败");

            return jpfResponseDto;
        }

        return jpfResponseDto;
    }

    @RequestMapping("/checkExpire")
    @ResponseBody
    public int checkExpireCoupon(){
        int count = shopCouponRemainServiceFacade.dealAllExpiredCoupon();
        logger.info("处理了"+count+"个过期的券");

        return count;
    }
}
