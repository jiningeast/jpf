package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.util.ExcelDealUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.common.util.exportExcel;
import com.joiest.jpf.dto.CloudDfMoneyRequest;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.facade.CloudInterfaceStreamServiceFacade;
import com.joiest.jpf.manage.web.util.ServicePayUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cloudDfMoney")
public class CloudDfMoneyController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;

    @Autowired
    private CloudInterfaceStreamServiceFacade cloudInterfaceStreamServiceFacade;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;
    /**
     * 代付开始打款
     * dfIds  代付明细主键ID串 例： 1,2
     * fid 订单号
     */
    @RequestMapping(value = "/batchMoney", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JpfResponseDto batchMoney(HttpServletRequest request){

        String companyMoneyId = request.getParameter("companyMoneyId");//订单表ID
        String dfIds = request.getParameter("dfIds");//代付ID字符串
        if(StringUtils.isBlank(dfIds) || dfIds == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "没有可代付明细");
        }

        //更新订单表 为打款中
        CloudCompanyMoneyInfo companyMoneyInfo = cloudCompanyMoneyServiceFacade.getRecById(companyMoneyId);
        if( companyMoneyInfo.getMontype() == 3 ){//已发起打款
            //throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单已发起打款，请勿重复点击");
        }
        if( companyMoneyInfo.getMontype() != 1 && companyMoneyInfo.getMontype() != 3 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单不能发起打款请求，请联系管理员");
        }

        String[] ids_str = dfIds.split(",");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < ids_str.length; i++) {
            ids.add(Long.parseLong(ids_str[i]));
        }
        if( ids.isEmpty() || ids.size() <=0 ){//未选择代付明细ID
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择代付信息");
        }


        if( companyMoneyInfo.getMontype() != 3 ) {
            PayCloudCompanyMoney comMoneyData = new PayCloudCompanyMoney();
            comMoneyData.setMontype((byte) 3);
            JpfResponseDto jpfcomMoneyDto = cloudCompanyMoneyServiceFacade.updateRecById(comMoneyData, companyMoneyId);
        }

        //查询公司账号信息
        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getRecById(companyMoneyInfo.getUid());
        if( companyInfo == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到公司信息");
        }

        if ( companyInfo.getIsFreeze() == (byte)2 )
        {
            throw new JpfException(JpfErrorInfo.COMPANY_IS_FREEZE, "企业被冻结");

        }
        String companyId = companyInfo.getId(); //公司ID
        BigDecimal cloudMoney = companyInfo.getCloudmoney(); //账户金额
        String cloudcode = companyInfo.getCloudcode(); //金额校验码
        Boolean checkMoneyVerify = cloudCompanyServiceFacade.checkCompanyMoneyVerify(companyId);
        if( !checkMoneyVerify ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "金额校验失败");
        }


        JpfResponseDto jpfResponseDto = new JpfResponseDto();

        //根据 ids  查询代付明细列表
        CloudDfMoneyRequest dfMoneyRequest= new CloudDfMoneyRequest();
        dfMoneyRequest.setIdsStr(ids);
        List<CloudDfMoneyInfo> infos = cloudDfMoneyServiceFacade.getAllBySective(dfMoneyRequest);

        Integer lenNum = 24;
        List<Long> limitData = new ArrayList<>(); //不能打款的订单数据
        Map<Long,CloudDfMoneyInfo> realPayMapData = new HashMap<>(); //可打款的订单数据
        BigDecimal cloudRealPayMoney = new BigDecimal("0"); //实际发放金额
        for(CloudDfMoneyInfo onetimes:infos){
            Long dfMoneyId = onetimes.getId();
            BigDecimal dfCommoney = onetimes.getCommoney(); //发放金额
            if( onetimes.getIsActive() != 1 || (onetimes.getMontype() !=1 && onetimes.getMontype() !=3) || onetimes.getIsFreeze() != (byte)1 ){ //过滤已打款或 不能打款 代付信息  添加冻结校验
                limitData.add(dfMoneyId);
            }


            if( onetimes.getOrderid().equals("") || onetimes.getOrderid() == null ) {//生成新代付订单号
                PayCloudDfMoney retData = new PayCloudDfMoney();
                String orderid = ToolUtils.createDfOrderid(String.valueOf(System.currentTimeMillis()),onetimes.getId().toString(),lenNum);
                retData.setOrderid(orderid); //生成打款单号
                List<Long> dfIdArr = new ArrayList<>();
                dfIdArr.add(onetimes.getId());
                jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(retData,dfIdArr);
                if( !jpfResponseDto.getRetCode().equals("0000") ){
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单生成异常");
                }
                cloudRealPayMoney = cloudRealPayMoney.add( dfCommoney ); //计算实际打款金额
                realPayMapData.put(dfMoneyId,onetimes); //可打款的订单数据
            }else{//二次打款 新单号处理
                PayCloudDfMoney retData = new PayCloudDfMoney();
                String orderid = ToolUtils.createDfOrderid(String.valueOf(System.currentTimeMillis()),onetimes.getId().toString(),lenNum);
                retData.setOrderid(orderid); //生成新打款单号
                String orderIds = onetimes.getOrderids() != null ? onetimes.getOrderids()+onetimes.getOrderid()+"," : onetimes.getOrderid()+",";
                retData.setOrderids(orderIds); //记录之前打款单号
                List<Long> dfIdArr = new ArrayList<>();
                dfIdArr.add(onetimes.getId());
                jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(retData,dfIdArr);
                if( !jpfResponseDto.getRetCode().equals("0000") ){
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单生成异常");
                }
                cloudRealPayMoney = cloudRealPayMoney.add( dfCommoney ); //计算实际打款金额
                realPayMapData.put(dfMoneyId,onetimes); //可打款的订单数据
            }
        }

        //金额是否可够代付
        if( cloudMoney.compareTo(new BigDecimal(0) ) < 0 || cloudMoney.compareTo(cloudRealPayMoney) < 0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "账户金额不足，请先充值");
        }

        //不能打款数据
        if( !limitData.isEmpty() || limitData.size() > 0 ){
            String jsonData = JsonUtils.toJson(limitData);
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "不能申请打款,编号："+jsonData);
        }

        //可打款订单数据
        if( realPayMapData.isEmpty() || realPayMapData == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无可打款记录或打款记录已发起");
        }

        //开始扣除账户金额及校验码
        /*BigDecimal afterloudMoney = cloudMoney.subtract(cloudRealPayMoney); //账户金额
        String checkCode = Md5Encrypt.md5(companyId+afterloudMoney+"test","UTF-8");   //加密规则：  id+金额+key
        PayCloudCompany payCloudCompany = new PayCloudCompany();
        payCloudCompany.setCloudcode(checkCode);
        payCloudCompany.setCloudmoney(afterloudMoney);
        payCloudCompany.setId(companyId);
        int upCompanyCount = cloudCompanyServiceFacade.updateSetiveById(payCloudCompany);
        if( upCompanyCount <=0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司账户信息更新失败");
        }*/


        //调用打款接口
        Map<String,String> responseParam = ServicePayUtils.waitPay(companyMoneyId,dfIds);
        if(responseParam.size()>0){
            jpfResponseDto = cloudDfMoneyServiceFacade.dfMoneyApplyWaitPay(responseParam,companyInfo,realPayMapData,companyId,dfIds);
        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接口请求异常");
        }


        return jpfResponseDto;
    }

    /**
     * 财务报税列表导出
     **/
    @RequestMapping("/ExcelCaiwu")
    @ResponseBody
    public String  ExcelCaiwu(CloudDfMoneyRequest cloudDfMoneyRequest, HttpServletResponse response){

        //查询出代付详细数据
        cloudDfMoneyRequest.setMontype(2);

        List<CloudDfMoneyInfo> infos = cloudDfMoneyServiceFacade.getAllBySectiveToCaiwu(cloudDfMoneyRequest);
        if(infos.size()==0 || infos.isEmpty()){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "所筛选的数据不存在！");
        }
        //如果有购买的卡密放到Excel
         //设置字段对应表头
        JSONArray filed = new JSONArray();
        filed.add("idno");
        filed.add("banknickname");
        filed.add("ticketcontent");//卡号
        filed.add("aproject");
        filed.add("Collection");
        filed.add("subtitle");
        filed.add("startTime");
        filed.add("endTime");
        filed.add("realmoney");
        filed.add("tax");
        filed.add("taxMoney");
        filed.add("taxMoneyPass");
        filed.add("collentNum");
        //设置表头修改
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now=sDateFormat.format(new Date());
        String startTime=cloudDfMoneyRequest.getAddtimeStart();
        String endTime=cloudDfMoneyRequest.getAddtimeEnd();
        String taxName="欣享科技服务有限公司";
        String taxNumber="91632223MA758Q1EXA";
        JSONArray headerKey = new JSONArray();
        //设置表头第一个填的值
        JSONObject headerValA = new JSONObject();
        headerValA.put("row",1);//第几行
        headerValA.put("col",1);//第几列
        headerValA.put("value",taxNumber);//设置的值
        headerKey.add(headerValA);
        //第二个填的值
        JSONObject headerValB = new JSONObject();
        headerValB.put("row",2);
        headerValB.put("col",1);
        headerValB.put("value",startTime);
        headerKey.add(headerValB);
        //第三个填的值
        JSONObject headerValC = new JSONObject();
        headerValC.put("row",2);
        headerValC.put("col",4);
        headerValC.put("value",endTime);
        headerKey.add(headerValC);
        JSONObject excel;
        //第四个填的值
        JSONObject headerValD = new JSONObject();
        headerValD.put("row",1);
        headerValD.put("col",9);
        headerValD.put("value",taxName);
        headerKey.add(headerValD);
        //第五个填的值
        JSONObject headerValE= new JSONObject();
        headerValE.put("row",2);
        headerValE.put("col",9);
        headerValE.put("value",now);
        headerKey.add(headerValE);
        //获取当前模板的路径
        String path=ConfigUtil.getValue("EXCEL_PATH")+"module/"+ConfigUtil.getValue("FINANCIAL_PATH");
        try {
            excel=new exportExcel().exportExcelcopy(response,path,"财务报税导出",infos,filed.toString(),headerKey.toString());
        } catch (Exception e) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "导出失败！");
        }
       return  null;
    }
}
