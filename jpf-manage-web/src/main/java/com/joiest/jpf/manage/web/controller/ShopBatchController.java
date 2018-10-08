package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.po.PayShopBatch;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.ShopBatchCouponResponse;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.thread.AddBatchThread;
import com.joiest.jpf.manage.web.util.SmsUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

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

    @Autowired
    private ShopCustomerServiceFacade shopCustomerServiceFacade;

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

        // 查询商户信息
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(request.getCompanyId());
        if ( shopCompanyInfo.getStatus() != 1 ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("商户已停用，无法继续操作");
        }

        // 计算总价
        String couponsJSON = request.getCoupons();
        List<Map<String,String>> list = JsonUtils.toObject(couponsJSON,ArrayList.class);
        int totalMoney = 0;
        for ( Map<String,String> single:list ){
            Integer singgleTotal = Integer.parseInt( single.get("money") ) * Integer.parseInt( single.get("amount") );
            totalMoney += singgleTotal;
        }

        // 判断该企业有没有这些券的总余额
        if ( shopCompanyInfo.getMoney().compareTo(new BigDecimal(totalMoney)) < 0 ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("商户余额不足");

            return jpfResponseDto;
        }

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());

        // 未使用线程
        // return shopBatchServiceFacade.addBatchCoupon(request,httpResponse);

        // 使用线程
        Thread thread = new Thread(new AddBatchThread(request,httpResponse));
        thread.start();

        return new JpfResponseDto();
    }

    /**
     * 查看批次详情页面
     */
    @RequestMapping("/detail")
    public ModelAndView detail(String batchId, ModelMap modelMap){
        // 查询批次信息
        ShopBatchInfo shopBatchInfo = shopBatchServiceFacade.getBatchById(batchId);
        if ( shopBatchInfo.getSendType() != null && ( shopBatchInfo.getSendType() == 1 || shopBatchInfo.getSendType() == 2 ) ){
            // 如果批次发送方式是Email发送
            modelMap.addAttribute("batchId",batchId);
            return new ModelAndView("shopBatch/detail",modelMap);
        }else{
            modelMap.addAttribute("batchId",batchId);
            return new ModelAndView("shopBatch/detailEmail",modelMap);
        }
    }

    /**
     * 查看批次详情数据
     */
    @RequestMapping("/detailData")
    @ResponseBody
    public Map<String,Object> detailData(String batchId, int page, int rows){
        ShopBatchCouponResponse response =  shopBatchCouponServiceFacade.getCouponByBatchId(batchId, page, rows);
        List<ShopBatchCouponInfo> list = response.getList();
        for (ShopBatchCouponInfo info:list) {
            String start = StringUtils.substring(info.getActiveCode(),0,2);
            String end = StringUtils.substring(info.getActiveCode(),-2,info.getActiveCode().length());
            info.setActiveCode(start+"****"+end);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }

    /**
     * 下载excel模板
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download()throws Exception {
        //下载文件路径
        String path = ConfigUtil.getValue("EXCEL_PATH") + "module/";
        String filename = "欣券群发模板.xlsx";
        String filename2 = null;
        try {
            // 解决找到文件问题:
            // URLEonder把中文用UTF-8编码,然而,tomcat用iso-8859-1解码
            // 我们需要用iso-8859-1编码,再重新用utf-8解码才能匹配到硬盘文件的名字
            filename2 = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File file = new File(path + filename);
        HttpHeaders headers = new HttpHeaders();

        String filename3 = null;
        try {
            // 解决提示下载框的中文问题:
            // 所有浏览器都会使用本地编码，即中文操作系统使用GBK
            // 浏览器收到这个文件名后，会使用iso-8859-1来解码
            // 编码流程:把中文用GBK编码为字节数组,再用ISO-8859-1编码,浏览器先用ISO-8859-1解码为字节数组,在用GBK解码为中文
            filename3 = new String(filename.getBytes("GBK"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        headers.setContentDispositionFormData("attachment", filename3);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 发送email选项页
     */
    @RequestMapping("/sendEmail")
    public ModelAndView sendEmail(String companyId, String batchId, ModelMap modelMap){
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(companyId);
        modelMap.addAttribute("receiveName",shopCompanyInfo.getReceiveName());
        modelMap.addAttribute("receivePhone",shopCompanyInfo.getReceivePhone());
        modelMap.addAttribute("receiveEmail",shopCompanyInfo.getReceiveEmail());
        modelMap.addAttribute("batchId",batchId);
        modelMap.addAttribute("companyId",companyId);
        modelMap.addAttribute("companyName",shopCompanyInfo.getCompanyName());

        return new ModelAndView("shopBatch/sendEmail");
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
    @RequestMapping("/sendReceiver")
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
        if ( shopBatchInfo.getEmailStatus() == 1 || shopBatchInfo.getSmsStatus() == 1 || shopBatchInfo.getStatus() == 2 ){
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
            // 更新该批次下所有券的状态为email发送
            shopBatchCouponServiceFacade.updateCouponSendTypeByBatchId(batchId);

            // email发送成功则发送短信
            String mobile = shopCompanyInfo.getReceivePhone();
            // 短信内容
//            String content = "已将批次号为" + shopBatchInfo.getBatchNo() + "的欣券激活码发送至您的邮箱，附件压缩包的密码是：" + shopBatchInfo.getZipPassword();
            String content = "尊敬的客户您好，批次号为{" + shopBatchInfo.getBatchNo() + "}的解压密码为{"+ shopBatchInfo.getZipPassword() +"}，请您妥善保管。";
            Map<String,String> smsResMap = SmsUtils.send(mobile,content,"xinxiang");
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

    /**
     * 解析excel，检查每个人信息完整性等
     */
    @RequestMapping("/sendPersons")
    @ResponseBody
    public JpfResponseDto sendPersons(@RequestParam("uploadfile") MultipartFile uploadfile, String companyId) throws Exception{
        // 获取当前的文件名
        String fileNameAll = uploadfile.getOriginalFilename();
        String fileName = fileNameAll.substring(0,fileNameAll.lastIndexOf("."));

        // 获取企业信息
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(companyId);
        // 判断企业有没有停用
        if ( shopCompanyInfo.getStatus() == 0 ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg(JpfErrorInfo.COMPANY_NOT_AVAILABLE.desc());

            return jpfResponseDto;
        }

        // 将excel暂存到硬盘上
        String savePre = ConfigUtil.getValue("EXCEL_PATH");
        String path = PhotoUtil.saveFile(uploadfile, savePre);

        // 解析excel
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();
        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        List<Object> list = new ArrayList<>(map.values());
        int maxAmount = Integer.parseInt(ConfigUtil.getValue("MAX_SEND_AMOUNT"));

        // 最多数量限制
        if ( list.size() > maxAmount ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg(JpfErrorInfo.MAX_LIMIT.desc());

            return jpfResponseDto;
        }

        Map<Integer,String> excelInfo = (Map<Integer,String>)list.get(2);
        // 获取公司名称，判断其不为空
        String companyName = excelInfo.get(0);
        if ( !StringUtils.isNotBlank(companyName) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10006");
            jpfResponseDto.setRetMsg(JpfErrorInfo.EMPTY_COMPANY_NAME.desc());

            return jpfResponseDto;
        }
        // 判断选择的批次的企业和excel中的企业名称是否一致
        PayShopCompany payShopCompany = shopCompanyServiceFacade.getCompanyByName(companyName);
        if ( !payShopCompany.getId().equals(companyId) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10006");
            jpfResponseDto.setRetMsg(JpfErrorInfo.ERROR_COMPANY_NAME.desc());

            return jpfResponseDto;
        }
        // 获取批次号，判断其不为空
        String batchNo = excelInfo.get(1);
        if ( !StringUtils.isNotBlank(batchNo) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10003");
            jpfResponseDto.setRetMsg(JpfErrorInfo.EMPTY_BATCH_NO.desc());

            return jpfResponseDto;
        }
        // 判断该批次号是否存在
        PayShopBatch payShopBatch = shopBatchServiceFacade.getBatchByBatchNo(batchNo);
        if ( payShopBatch == null ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10005");
            jpfResponseDto.setRetMsg(JpfErrorInfo.BATCH_NOT_EXIST.desc());

            return jpfResponseDto;
        }
        // 获取总面值,判断其不为空
        String totalMoney = excelInfo.get(2);
        if ( !StringUtils.isNotBlank(totalMoney) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg(JpfErrorInfo.EMPTY_TOTAL_MONEY.desc());

            return jpfResponseDto;
        }

        // 验证该企业有没有excel上描述的券
        // 获取excel各面值券的数量
        List<ShopCustomerInfo> personsList = new ArrayList<>();    // 新建校验人数组
        Map<String,Integer> valueCount = new HashedMap();
        int totalValue = 0;
        for ( int i=4; i<list.size(); i++){
            // 获取每条信息详情
            Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);

            // 判断手机号合法性
            if ( !ToolUtils.checkPhone( singlePerson.get(1) ) ){
                JpfResponseDto jpfResponseDto = new JpfResponseDto();
                jpfResponseDto.setRetCode("10005");
                jpfResponseDto.setRetMsg("用户"+singlePerson.get(0)+"的手机号有误，请检查后重新上传");

                return jpfResponseDto;
            }

            // 姓名、手机号和面值必填
            String name = singlePerson.get(0);
            String phone = singlePerson.get(1);
            String value = singlePerson.get(2);
            String idno = StringUtils.isNotBlank(singlePerson.get(3)) ? singlePerson.get(3) : null;
            if ( !StringUtils.isNotBlank(name) || !StringUtils.isNotBlank(phone) || !StringUtils.isNotBlank(value) ){
                JpfResponseDto jpfResponseDto = new JpfResponseDto();
                jpfResponseDto.setRetCode("10004");
                jpfResponseDto.setRetMsg("Excel第"+i+"行的数据不完整，请修改后重新上传");

                return jpfResponseDto;
            }

            // 判断所有人的状态是不是已冻结
            PayShopCustomer existCustomer = shopCustomerServiceFacade.getCustomerByPhone(singlePerson.get(1));
            ShopCustomerInfo failCustomer = new ShopCustomerInfo();
            failCustomer.setName(singlePerson.get(0));
            failCustomer.setPhone(singlePerson.get(1));
            failCustomer.setDou(Integer.parseInt(singlePerson.get(2)));
            if ( StringUtils.isNotBlank(idno) ){
                failCustomer.setIdno(idno);
            }
            if ( existCustomer.getStatus() == 0 ){
                // 账户已冻结
                failCustomer.setStatus((byte)0);
            }
            // 检查校验码
            String checkCode = ToolUtils.CreateCode(existCustomer.getDou().toString(),existCustomer.getId());
            if ( !existCustomer.getCode().equals(checkCode) ){
                // 校验码错误
                failCustomer.setIsVerify((byte)0);
            }
            // 将校验过的人添加到数组中
            if ( failCustomer.getName() != null ){
                personsList.add(failCustomer);
            }
            // 统计总金额
            totalValue = totalValue + Integer.parseInt(value);

            // 统计各面值的数量
            if ( valueCount.get(value) == null ){
                valueCount.put( value,1);
            }else{
                valueCount.put( value,valueCount.get(value) + 1);
            }
        }

        // 判断总面值和各人员面值的总和是不是统一
        if ( Integer.parseInt(totalMoney) != totalValue ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg(JpfErrorInfo.ERROR_TOTAL_MONEY.desc());

            return jpfResponseDto;
        }

        // 判断数据库中有没有对应的这些个券
        for (Map.Entry<String, Integer> entry : valueCount.entrySet()) {
            int valueNum = shopBatchCouponServiceFacade.getCouponNumByValue( companyId, entry.getKey(), batchNo );
            if ( valueNum < entry.getValue() ){
                JpfResponseDto jpfResponseDto = new JpfResponseDto();
                jpfResponseDto.setRetCode("10002");
                jpfResponseDto.setRetMsg("面值"+entry.getKey()+"的库存数量少于Excel中的数量");

                return jpfResponseDto;
            }
        }

        // 输出校验的人集合
        if ( !personsList.isEmpty() ){
            UUID uuid = UUID.randomUUID();
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code","00001");
            responseMap.put("info","人员校验情况如下：");
            responseMap.put("companyId",companyId);
            responseMap.put("companyName",companyName);
            responseMap.put("batchNo",batchNo);
            responseMap.put("path",path);
            responseMap.put("data",JsonUtils.toJson(personsList));
            responseMap.put("totalMoney",totalMoney);
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);

            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("00001");
            jpfResponseDto.setRetMsg(uuid.toString());

            return jpfResponseDto;
        }

        return new JpfResponseDto();
    }

    /**
     * 群发券操作
     */
    @RequestMapping("/sendCoupons")
    @ResponseBody
    public JpfResponseDto sendCoupons(String fileName){
        // 发送欣券后是否直接激活
        int sendAndActive = 0;

        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+fileName+".txt","UTF-8");
        Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        List<LinkedHashMap<String,Object>> list = JsonUtils.toObject(jsonMap.get("data"),List.class);
        String companyId = jsonMap.get("companyId");
        String companyName = jsonMap.get("companyName");
        String batchNo = jsonMap.get("batchNo");
        String excelLocalUrl = jsonMap.get("path");
        String totalMoney = jsonMap.get("totalMoney");

        // 检查商户是否已禁用
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(companyId);
        if ( shopCompanyInfo.getStatus() == 0 ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("该商户已禁用");

            return jpfResponseDto;
        }

        // 检验金额验证码的正确性
        if ( !shopCompanyServiceFacade.checkMoneyCode(companyId) ){
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("金额校验码错误");

            return jpfResponseDto;
        }

        List<ShopCustomerInfo> sendedList = new ArrayList<>();
        List<ShopBatchCouponInfo> couponsList = new ArrayList<>();
        if ( sendAndActive == 1 ){
            // 开始群发欣券并激活
            sendedList = shopBatchCouponServiceFacade.sendCouponsToPersons(list,companyId,companyName,batchNo,excelLocalUrl);     // 发送短信并直接激活
        }else{
            // 获取待接收激活码的人的手机号
            couponsList = shopBatchCouponServiceFacade.getCoupons(list,companyId,batchNo,excelLocalUrl);
        }

        // 扣款
        shopCompanyServiceFacade.charge(companyId,0-Double.parseDouble(totalMoney));

        PayShopBatch payShopBatch = shopBatchServiceFacade.getBatchByBatchNo(batchNo);
        if ( sendAndActive == 1 ){
            // 更新批次记录已激活的券数量字段
            int newActivetedNum = payShopBatch.getActivetedNum() + sendedList.size();
            ShopBatchInfo shopBatchInfo = new ShopBatchInfo();
            shopBatchInfo.setId(payShopBatch.getId());
            shopBatchInfo.setActivetedNum(newActivetedNum);
            shopBatchServiceFacade.updateColumnById(shopBatchInfo);
        }else{
            // 更新批次发送状态为“已发送给个人”
            ShopBatchInfo shopBatchInfo = new ShopBatchInfo();
            shopBatchInfo.setId(payShopBatch.getId());
            shopBatchInfo.setStatus((byte)2);
            shopBatchInfo.setSendType((byte)2);
            shopBatchServiceFacade.updateColumnById(shopBatchInfo);
        }

        // 开始发短信
        String content;
        if ( sendAndActive == 1 ){
            content = "尊敬的用户，您的欣豆数量有变动，请微信搜索登录“欣享爱生活”查看。";
            for ( ShopCustomerInfo customerInfo:sendedList ){
                sendToPersonsSms(customerInfo.getPhone(),content,batchNo);
            }
        }else{
            for ( ShopBatchCouponInfo shopBatchCouponInfo:couponsList ){
                content = "您收到一个激活码："+shopBatchCouponInfo.getActiveCode()+"，请微信搜索“欣享爱生活”公众号登录使用。";
                sendToPersonsSms(shopBatchCouponInfo.getActivePhone(),content,batchNo);
            }
        }

        return new JpfResponseDto();
    }

    // 群发的短信
    public void sendToPersonsSms(String phone,String content,String batchNo){
        Map<String,String> smsResMap = SmsUtils.send(phone,content,"xinxiang");
        Map<String,String> responseMap = JsonUtils.toObject(smsResMap.get("response"),Map.class);
        if ( responseMap.get("code").equals("10000") ){
            // 添加短信流水
            ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
            shopInterfaceStreamInfo.setType((byte)1);
            shopInterfaceStreamInfo.setRequestUrl(smsResMap.get("requestUrl"));
            shopInterfaceStreamInfo.setRequestContent(smsResMap.get("requestParam"));
            shopInterfaceStreamInfo.setResponseContent(smsResMap.get("response"));
            shopInterfaceStreamInfo.setBatchNo(batchNo);
            shopInterfaceStreamInfo.setAddtime(new Date());
            shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);
        }
    }

    @RequestMapping("/failPersons")
    public ModelAndView failPersons(String fileName,String companyId,String companyName,ModelMap modelMap){
        modelMap.addAttribute("fileName",fileName);
        modelMap.addAttribute("companyId",companyId);
        modelMap.addAttribute("companyName",companyName);
        return new ModelAndView("shopBatch/failPersons",modelMap);
    }

    @RequestMapping("/getFailPersons")
    @ResponseBody
    public Map<String,Object> getFailPersons(String fileName){
        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+fileName+".txt","UTF-8");
        Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        List<String> list = JsonUtils.toObject(jsonMap.get("data"),List.class);
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total",list.size());
        responseMap.put("rows",list);

        return responseMap;
    }

    /**
     * 重新发送短信
     */
    @RequestMapping(value = "/sendSmsAgain", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JpfResponseDto sendSmsAgain(String couponIds){
        String content;
        String[] couponIdArr = couponIds.split(",");
        for (int i=0; i<couponIdArr.length; i++) {
            ShopBatchCouponInfo shopBatchCouponInfo = shopBatchCouponServiceFacade.getCouponById(couponIdArr[i]);
            content = "您收到一个激活码："+shopBatchCouponInfo.getActiveCode()+"，请微信搜索“欣享爱生活”公众号登录使用。";

            sendToPersonsSms(shopBatchCouponInfo.getActivePhone(),content,shopBatchCouponInfo.getBatchNo());
        }

        return new JpfResponseDto();
    }


    @RequestMapping("/checkExpire")
    @ResponseBody
    public int checkExpireCoupon(){
        int count = shopCouponRemainServiceFacade.dealAllExpiredCoupon();
        logger.info("处理了"+count+"个过期的券");

        return count;
    }
}
