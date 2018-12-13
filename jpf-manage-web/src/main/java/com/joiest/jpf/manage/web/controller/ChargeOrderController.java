package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.common.util.exportExcel;
import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("chargeOrder")
public class ChargeOrderController {

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "chargeOrder/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetChargeOrderRequest request){
        GetChargeOrderResponse response = chargeOrderServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return  map;
    }

    @RequestMapping("caiwuIndex")
    public String caiwuIndex(){
        return "chargeOrder/caiwuList";
    }

    /**
     * 运营申请退款
     * */
    @RequestMapping("caiwuList")
    @ResponseBody
    public Map<String,Object> caiwuList(GetChargeOrderRequest request){
        GetChargeOrderResponse response = chargeOrderServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return  map;
    }

    @RequestMapping("applyTuikuan")
    @ResponseBody
    public JpfResponseDto applyTuikuan(PayChargeOrder request, HttpSession httpSession){
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(request);
        if( chargeOrderInfo == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到该数据");
        }
        // 上游充值失败=3
        if( chargeOrderInfo.getStatus() != 3 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无法申请退款");
        }
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);

        ChargeOrderInfo orderInfo = new ChargeOrderInfo();
        //申请退款
        orderInfo.setStatus((byte)4);
        orderInfo.setId(chargeOrderInfo.getId());
        orderInfo.setCheckId(userInfo.getId().toString());
        orderInfo.setCheckName(userInfo.getUserName());
        int count  = chargeOrderServiceFacade.upOrderInfo(orderInfo);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }

        return  new JpfResponseDto();
    }

    /**
    * 财务审核退款
    *
    * */
    @RequestMapping("auditTuikuan")
    @ResponseBody
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto auditTuikuan(PayChargeOrder request, HttpSession httpSession){
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(request);
        if( chargeOrderInfo == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到该数据");
        }
        // 申请退款=4
        if( chargeOrderInfo.getStatus() != 4 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无法申请退款");
        }
        UserInfo userInfo = (UserInfo) httpSession.getAttribute(ManageConstants.USERINFO_SESSION);

        //操作标识
        int ret = 0;

        ChargeOrderInfo orderInfo = new ChargeOrderInfo();
        //退款成功
        orderInfo.setStatus((byte)5);
        orderInfo.setId(chargeOrderInfo.getId());
        orderInfo.setCheckId(userInfo.getId().toString());
        orderInfo.setCheckName(userInfo.getUserName());
        int count  = chargeOrderServiceFacade.upOrderInfo(orderInfo);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }else{
            ChargeCompanyInfo companyInfo = new ChargeCompanyInfo();
            String companyId = chargeOrderInfo.getCompanyId();
            companyInfo.setId(companyId);
            ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(companyId);
            if( chargeCompanyInfo == null ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到商户信息");
            }
            if( chargeCompanyInfo.getIsFreeze() == 1 || chargeCompanyInfo.getIsDel()==1 ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "账户冻结或关闭，请联系管理员");
            }



                BigDecimal preMoney  = chargeCompanyInfo.getMoney();
                String preCode = chargeCompanyInfo.getMoneyCode();
                //退款金额
                BigDecimal money = chargeOrderInfo.getTotalMoney();
                String keyStr = ConfigUtil.getValue("MERCH_VALIDE_CODE");
                Boolean flag = ToolUtils.ValidateCode(preCode,companyId,preMoney.toString(),keyStr);
                if( !flag ){
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "金额校验失败");
                }
                //开始退款
                BigDecimal afterMoney = preMoney.add(money);
                String newCode = ToolUtils.CreateCode(afterMoney.toString(),companyId,keyStr);

                PayChargeCompany chargeCompany = new PayChargeCompany();
                chargeCompany.setId(companyId);
                chargeCompany.setMoney(afterMoney);
                chargeCompany.setMoneyCode(newCode);
                JpfResponseDto jpfResponseDto = chargeCompanyServiceFacade.updateCompanyRecord(chargeCompany);
                if(jpfResponseDto == null || !jpfResponseDto.getRetCode().equals("0000") ){
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
                }else{
                    BigDecimal zeroNum = new BigDecimal("0");
                    Date curretDate = new Date();
                    PayChargeCompanyMoneyStream streamData = new PayChargeCompanyMoneyStream();
                    streamData.setStreamNo("MS"+ToolUtils.createOrderid());//流水号
                    streamData.setCompanyId(companyId);//商户id
                    streamData.setCompanyName(chargeCompanyInfo.getCompanyName());//商户名称
                    streamData.setMerchNo(chargeCompanyInfo.getMerchNo());//商户号
                    streamData.setOrderId(chargeOrderInfo.getId());//订单id 可能是消费订单、充值订单、退款订单
                    streamData.setOrderNo(chargeOrderInfo.getOrderNo()); // 订单号可能是消费订单、充值订单、退款订单
                    streamData.setProductId(chargeOrderInfo.getProductId());//产品Id
                    streamData.setProductName(chargeOrderInfo.getProductName());//产品名称

                    streamData.setProductValue(chargeOrderInfo.getProductValue()); //产品面值
                    streamData.setProductBidPrice(chargeOrderInfo.getProductBidPrice());//产品成本价
                    streamData.setProductSalePrice(chargeOrderInfo.getProductPrice());//产品标准售价 默认null
                    streamData.setProductInterfacePrice(chargeOrderInfo.getProductBidPrice());//产品接口价同成本价
                    streamData.setProductAmount(chargeOrderInfo.getProductAmount());//产品数量
                    streamData.setTotalMoney(chargeOrderInfo.getTotalMoney());//总价

                    streamData.setInterfaceType(chargeOrderInfo.getInterfaceType());//接口类型 0=欧非 1=威能 默认null
                    streamData.setInterfaceOrderNo(chargeOrderInfo.getInterfaceOrderNo());//接口订单号 默认null
                    streamData.setStatus((byte)3);//流水类型 1=充值 2=下单 3=退款
                    streamData.setStreamType((byte)0);//流水类型 0=收入 1=支出
                    streamData.setNewMoney(afterMoney);//变动后的余额
                    streamData.setMemo("");//流水备注
                    streamData.setIsDel((byte)0);//删除标记 0=未删除 1=已删除
                    int streamCou = chargeCompanyMoneyStreamServiceFacade.create(streamData);
                    if( streamCou != 1 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "流水记录失败");
                    }else{
                        ret = 1;
                    }
                }


        }
        if( ret != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }

        return  new JpfResponseDto();
    }
    
    /**
     * 充值平台订单管理-Excel导出
     * @param request
     * @param response
     */
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(GetChargeOrderRequest request, HttpServletResponse response){
        Map<String,String> requestInterfaceTypeMap = new HashMap<>(2);
        requestInterfaceTypeMap.put("0","欧飞");
        requestInterfaceTypeMap.put("1","威能");
        
        Map<String,String> requestStatusMap = new HashMap<>(6);
        requestStatusMap.put("0","订单生成");
        requestStatusMap.put("2","充值成功");
        requestStatusMap.put("3","充值失败");
        requestStatusMap.put("4","退款申请中");
        requestStatusMap.put("5","退款成功");
        requestStatusMap.put("6","拒绝退款");

        request.setInterfaceTypeParam(requestInterfaceTypeMap);
        request.setStatusParam(requestStatusMap);
        request.setPage(0);
        request.setRows(0);
        GetChargeOrderResponse chargeOrderResponse = chargeOrderServiceFacade.getRecords(request);
        if(chargeOrderResponse.getList() == null || chargeOrderResponse.getList().isEmpty()){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到记录");
        }
        try {
            JSONObject jsonObject = exportExcelByInfoNew(response, chargeOrderResponse.getList(), 1, "");
        } catch (Exception e) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据导出异常");
        }
    }

    /**
     * 导出充值订单数据格式到Excel文件
     * @param response 响应头
     * @param data 数据
     * @param type 1 下载  2 生成文件
     * @param path
     * @return
     */
    private JSONObject exportExcelByInfoNew(HttpServletResponse response, List<ChargeOrderInfo> data, int type, String path){
        type = type < 1 ? 1 : type;
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        String interfaceType = "",status = "";
        Sheet sheet = xssfWorkbook.getSheet("sheet1");
        if (sheet == null) {
            sheet = xssfWorkbook.createSheet("sheet1");
        }
        exportExcel.genSheetHead(sheet, 0, generateTitle());
        for (int rownum = 1; rownum <= data.size(); rownum++) {
            Row row = sheet.createRow(rownum);
            int k = -1;
            exportExcel.createCell(row, ++k, data.get(rownum-1).getOrderNo());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getForeignOrderNo());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getCompanyId());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getCompanyName());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getMerchNo());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getChargePhone());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getProductId());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getProductName());
            exportExcel.createCell(row, ++k, data.get(rownum - 1).getProductPrice() == null ? "" : String.valueOf(data.get(rownum-1).getProductPrice()));
            if(data.get(rownum - 1).getInterfaceType() == null){
                interfaceType = "";
            }else if(data.get(rownum-1).getInterfaceType() == 0){
                interfaceType = "欧飞";
            }else if(data.get(rownum-1).getInterfaceType() == 1){
                interfaceType = "威能";
            }
            exportExcel.createCell(row, ++k, interfaceType);
            exportExcel.createCell(row, ++k, data.get(rownum - 1).getInterfaceOrderNo());
            if(data.get(rownum - 1).getStatus() == null){
                status = "";
            }else{
                switch (data.get(rownum - 1).getStatus()){
                    case 0: status = "下单成功";break;
                    case 1: status = "充值中";break;
                    case 2: status = "上游充值成功";break;
                    case 3: status = "上游充值失败";break;
                    case 4: status = "申请退款";break;
                    case 5: status = "退款成功";break;
                    case 6: status = "拒绝退款";break;
                    case 7: status = "退款失败";break;
                }
            }
            exportExcel.createCell(row, ++k, status);
            exportExcel.createCell(row, ++k, data.get(rownum-1).getRequestParams());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getNotifyUrl());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getNotifyParams());
            exportExcel.createCell(row, ++k, data.get(rownum-1).getNotifyTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum-1).getNotifyTime()));
            exportExcel.createCell(row, ++k, data.get(rownum-1).getAddtime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum-1).getAddtime()));
            exportExcel.createCell(row, ++k, data.get(rownum-1).getUpdatetime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum-1).getUpdatetime()));
        }
        String fileName = "充值平台订单列表-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        return exportExcel.writeIntoExcel(fileName, response, xssfWorkbook, path, type, res);
    }

    /**
     * 定义标题
     * @return
     */
    private Map<Integer,Object> generateTitle(){
        Map<Integer, Object> firstTitles = new HashMap<>(18);
        firstTitles.put(0, "订单号");
        firstTitles.put(1, "外来订单号");
        firstTitles.put(2, "商户id");
        firstTitles.put(3, "商户名称");
        firstTitles.put(4, "商户号");
        firstTitles.put(5, "充值号码");
        firstTitles.put(6, "产品id");
        firstTitles.put(7, "产品名称");
        firstTitles.put(8, "产品单价");
        firstTitles.put(9, "接口类型");
        firstTitles.put(10, "上游订单号");
        firstTitles.put(11, "订单状态");
        firstTitles.put(12, "下游请求参数");
        firstTitles.put(13, "异步回调地址");
        firstTitles.put(14, "异步回调参数");
        firstTitles.put(15, "异步回调时间");
        firstTitles.put(16, "添加时间");
        firstTitles.put(17, "更新时间");
        return firstTitles;
    }
}
