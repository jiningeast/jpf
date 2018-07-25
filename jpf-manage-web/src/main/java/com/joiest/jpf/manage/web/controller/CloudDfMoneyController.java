package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompany;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.dto.CloudDfMoneyRequest;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInfo;
import com.joiest.jpf.entity.CloudInterfaceStreamInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.facade.CloudInterfaceStreamServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
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
            if( onetimes.getIsActive() != 1 || (onetimes.getMontype() !=1 && onetimes.getMontype() !=3) ){ //过滤已打款或 不能打款 代付信息
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
                cloudRealPayMoney.add( dfCommoney ); //计算实际打款金额
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
                cloudRealPayMoney.add( dfCommoney ); //计算实际打款金额
                realPayMapData.put(dfMoneyId,onetimes); //可打款的订单数据
            }
        }

        //金额是否可够代付
        if( cloudMoney.compareTo(new BigDecimal(0) ) == -1 || cloudMoney.compareTo(cloudRealPayMoney) == -1 ){
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

        //调用代付接口
        Date date = new Date();
        String dateTime = date.toString();
        Map<String,Object> map = new HashMap<>();
        map.put("batchid",companyMoneyId);
        map.put("dfid",dfIds);
        String cloudWaitpayKeycode = ManageConstants.ClOUD_WAITPAY_KEYCODE; //校验码keycode
        //String requestUrl = ManageConstants.ClOUD_WAITPAY_URl; //请求地址
        String requestUrl = ConfigUtil.getValue("DFPAY_URL")+"/clouddf/dfApi";//请求地址

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        String respos = ToolUtils.mapToUrl(treeMap);
        String sign = Md5Encrypt.md5(respos+cloudWaitpayKeycode);
        map.put("sign",sign);

        String requestParam = ToolUtils.mapToUrl(map);//请求参数
        //请求接口
        String response = OkHttpUtils.postForm(requestUrl,map); //base64加密数据
        response = response.replace("\"","");
        response = Base64CustomUtils.base64Decoder(response);//解密后json串
        //json---转换代码---
        //Map<String,Object> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, Object>>() {});
        JSONObject responseMap = JSONObject.fromObject(response);
        if( responseMap.isEmpty() || responseMap == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付接口异常");
        }
        String code = responseMap.get("code").toString();

        //存储日志记录
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();
        String logPath = "/logs/jpf-manage-web/log/";
        String fileName = "dfRecord";
        logContent.append("\n\nTime:" + myfmt.format(date));
        logContent.append("\n接口返回信息:" + response);

        if( code.equals("10000") || code.equals("30004") ){ //10000=代付成功  30004=无代付请求数据（支付限额）

            //记录pay_cloud_interface_stream表操作记录
            CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
            //存取短信接口调用记录
            cloudInterfaceStreamInfo.setRequestUrl(requestUrl);
            cloudInterfaceStreamInfo.setRequestContent(requestParam);
            cloudInterfaceStreamInfo.setType((byte)2);
            cloudInterfaceStreamInfo.setResponseContent(response);
            cloudInterfaceStreamInfo.setCompanyMoneyId(companyMoneyId);
            cloudInterfaceStreamInfo.setTaskId("0");
            cloudInterfaceStreamInfo.setStaffId("0");
            cloudInterfaceStreamInfo.setAddtime(date);
            cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);

            BigDecimal applyFailMoney = new BigDecimal("0");
            //是否存在data变量
            if( responseMap.has("data") && StringUtils.isNotBlank(responseMap.get("data").toString()) ){
                JSONObject data = JSONObject.fromObject(responseMap.getString("data"));
                if( data.has("filterdata") ){ //接口返回 代付限额明细数据
                    JSONObject  filterdata = JSONObject.fromObject(data.getString("filterdata"));
                    JSONArray  finalData = JSONArray.fromObject(filterdata.getString("data"));
                    Long filterDfId = new Long(0);//json数组里的id值
                    if( finalData.size() > 0 ) {
                        for (int i = 0; i < finalData.size(); i++) {
                            JSONObject job = finalData.getJSONObject(i);//把每一个对象转成json对象
                            filterDfId = Long.parseLong(job.get("dfid").toString());
                            if(  realPayMapData.containsKey(filterDfId)  ){//存在限制代付ID 删除
                                CloudDfMoneyInfo cloudInfos = realPayMapData.get(filterDfId);
                                applyFailMoney.add(cloudInfos.getCommoney());
                                realPayMapData.remove(filterDfId);
                                logContent.append("\n支付限额：代付明细ID:"+filterDfId+"\t收款人："+cloudInfos.getBanknickname()+"\t金额："+cloudInfos.getCommoney());
                            }

                            //更新订单下对应的代付明细状态为：打款中
                            PayCloudDfMoney recordData = new PayCloudDfMoney();
                            List<Long> filterDfIdArr = new ArrayList<>();
                            filterDfIdArr.add(filterDfId);
                            recordData.setMontype(5); //更新为支付限制

                            jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,filterDfIdArr);

                        }
                    }
                }

                BigDecimal afterloudMoney = cloudMoney; //用户账户当前金额
                for (Long key : realPayMapData.keySet()) {
                    CloudDfMoneyInfo cloudRets = realPayMapData.get(key);

                    //开始扣除账户金额及校验码
                    afterloudMoney = afterloudMoney.subtract(cloudRets.getCommoney()); //账户金额
                    String checkCode = Md5Encrypt.md5(companyId+afterloudMoney+"test","UTF-8");   //加密规则：  id+金额+key
                    PayCloudCompany payCloudCompany = new PayCloudCompany();
                    payCloudCompany.setCloudcode(checkCode);
                    payCloudCompany.setCloudmoney(afterloudMoney);
                    payCloudCompany.setId(companyId);
                    int upCompanyCount = cloudCompanyServiceFacade.updateSetiveById(payCloudCompany);
                    if( upCompanyCount <=0 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司账户信息更新失败");
                    }
                    logContent.append("\n打款金额：代付明细ID:"+cloudRets.getId()+"\t收款人："+cloudRets.getBanknickname()+"\t金额："+cloudRets.getCommoney());

                    //更新订单下对应的代付明细状态为：打款中
                    PayCloudDfMoney recordData = new PayCloudDfMoney();
                    recordData.setMontype(4); //更新为打款中
                    List<Long> currDfIdArr = new ArrayList<>();
                    currDfIdArr.add(key);

                    jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,currDfIdArr);

                }



            }else{
                // 30004 提交单条数据到接口由于支付限制 接口无返回data参数
                if(code.equals("30004")){
                    //更新订单下对应的代付明细状态为：打款中
                    PayCloudDfMoney recordData = new PayCloudDfMoney();
                    recordData.setMontype(5); //更新为支付限制

                    jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,ids);
                }
            }





        }else{

                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付请求失败,状态码:"+code);

        }

        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);


        return jpfResponseDto;
    }

}
