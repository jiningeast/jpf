package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayCloudDfMoneyCustom;
import com.joiest.jpf.common.custom.PayCloudDfMoneyInterfaceCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompany;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.common.po.PayCloudInterfaceStream;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudDfMoneyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudDfMoneyInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfMoneyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudInterfaceStreamMapper;
import com.joiest.jpf.dto.CloudDfMoneyRequest;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class CloudDfMoneyServiceFacadeImpl implements CloudDfMoneyServiceFacade {

    @Autowired
    private PayCloudDfMoneyMapper payCloudDfMoneyMapper;

    @Autowired
    private PayCloudDfMoneyInterfaceCustomMapper payCloudDfMoneyInterfaceCustomMapper;

    @Autowired
    private PayCloudDfMoneyCustomMapper payCloudDfMoneyCustomMapper;

    @Autowired
    private PayCloudInterfaceStreamMapper payCloudInterfaceStreamMapper;

    @Autowired
    private PayCloudCompanyMapper payCloudCompanyMapper;


    @Override
    public GetCloudMoneyDfResponse getDfMoneyList(String start, String end, String uid, long pageNo, long pageSize, int flag)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        if (flag == 1 )
        {
            e.setPageNo(pageNo);
            e.setPageSize(pageSize);
        }
        e.setOrderByClause("addtime DESC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(start,DateUtils.DATEFORMATLONG));
        c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(end,DateUtils.DATEFORMATLONG));
        c.andBusstaffidEqualTo(Long.parseLong(uid));
        c.andMontypeEqualTo(2);

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            listnew.add(info);

            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getCommoney().toString()), monthTotal);
        }
        GetCloudMoneyDfResponse response = new GetCloudMoneyDfResponse();
        response.setList(listnew);
        response.setMonthTotal(new BigDecimal(monthTotal));
        return response;
    }

    @Override
    public Double getUserDfTotalMoney(String uid)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        e.setOrderByClause("addtime DESC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andBusstaffidEqualTo(Long.parseLong(uid));
        c.andMontypeEqualTo(2);

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getCommoney().toString()), monthTotal);
        }
        return monthTotal;
    }

    @Override
    public List<CloudDfMoneyInterfaceInfo> getUserMonthList(Long uid)
    {
        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        example.setOrderByClause("addtime DESC");
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andBusstaffidEqualTo(uid);
        c.andMontypeEqualTo(2);

        List<PayCloudDfMoneyInterfaceCustom> list = payCloudDfMoneyInterfaceCustomMapper.getUserMonthList(example);

        List<CloudDfMoneyInterfaceInfo> response = new ArrayList<>();
        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        for (PayCloudDfMoneyInterfaceCustom one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyInterfaceCustom.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            response.add(info);
        }

        return response;
    }

    /*
     **根据订单号更新 代付明细状态
     * fid  订单号
     */
    @Override
    public JpfResponseDto updateDfRecordsByFid(PayCloudDfMoney record,String fid){

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();

        if( StringUtils.isBlank(fid) || fid==null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单号不能为空");
        }
        c.andFidEqualTo(fid);

        int count = payCloudDfMoneyMapper.updateByExample(record,example);
        if(count !=1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据更新失败");
        }

        return new JpfResponseDto();


    }


    //获取充值记录数据通过主键
    public CloudDfMoneyInterfaceInfo getDfMoneyById(Long id){


        PayCloudDfMoney payCloudDfMoney = payCloudDfMoneyMapper.selectByPrimaryKey(id);

        if(payCloudDfMoney == null) return null;

        CloudDfMoneyInterfaceInfo cloudDfMoneyInterfaceInfo = new CloudDfMoneyInterfaceInfo();

        BeanCopier beanCopier = BeanCopier.create( PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);

        beanCopier.copy(payCloudDfMoney, cloudDfMoneyInterfaceInfo, null);

        return cloudDfMoneyInterfaceInfo;
    }
    //更新代付状态
    public int updateDfMoneyActive(Map<String,String> dfMoney, Long id){

        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();

        payCloudDfMoney.setIsActive(new Integer(dfMoney.get("is_active")));
        payCloudDfMoney.setUpdatetime(new Date());
        payCloudDfMoney.setId(id);


        return payCloudDfMoneyMapper.updateByPrimaryKeySelective(payCloudDfMoney);
    }

    //更新代付状态
    public int updateDfMoneyActiveById(CloudDfMoneyRequest request,Long id){

        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();
        if( request.getMontype() != null ){
            payCloudDfMoney.setMontype(request.getMontype());
        }
        payCloudDfMoney.setUpdatetime(new Date());
        payCloudDfMoney.setId(id);

        return payCloudDfMoneyMapper.updateByPrimaryKeySelective(payCloudDfMoney);
    }

    //根据主键字符串更新代付明细
    public JpfResponseDto updateDfRecordsByids(PayCloudDfMoney record, List<Long> ids){

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andIdIn(ids);


        int count = payCloudDfMoneyMapper.updateByExampleSelective(record,example);
        if( count <= 0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付操作失败");
        }
        return new JpfResponseDto();
    }

    @Override
    public GetCloudMoneyDfResponse getDfDetailList(String batchId, String dfid, long pageNo, long pageSize, int flag)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        if (flag == 1 )
        {
            e.setPageNo(pageNo);
            e.setPageSize(pageSize);
        }

        e.setOrderByClause("id ASC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andCompanyMoneyIdEqualTo(batchId);
        c.andIsActiveEqualTo(1);
        if ( !dfid.equals("0") )
        {
            String[] dfid_arr = dfid.split(",");
            List<String> dfidList_str = Arrays.asList(dfid_arr);
            List<Long> dfidList_Long = new ArrayList<>();
            for (String one : dfidList_str)
            {
                dfidList_Long.add(Long.parseLong(one));
            }
            c.andIdIn(dfidList_Long);
        }

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            listnew.add(info);

            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getCommoney().toString()), monthTotal);
        }
        GetCloudMoneyDfResponse response = new GetCloudMoneyDfResponse();
        response.setList(listnew);
        response.setMonthTotal(new BigDecimal(monthTotal.toString()));
        response.setCount(list.size());
        return response;
    }


    @Override
    public long addDfMoney(CloudDfMoneyInfo cloudDfMoneyInfo){
        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();
        BeanCopier beanCopier = BeanCopier.create(CloudDfMoneyInfo.class, PayCloudDfMoney.class, false);
        beanCopier.copy(cloudDfMoneyInfo, payCloudDfMoney, null);

        payCloudDfMoneyCustomMapper.insert(payCloudDfMoney);

        return payCloudDfMoney.getId();
    }

    @Override
    public List<CloudDfMoneyInfo> getAllBySective(CloudDfMoneyRequest request){

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        if( request.getCompanyMoneyId() != null ){
            c.andCompanyMoneyIdEqualTo(request.getCompanyMoneyId());
        }
        if( request.getIdsStr() != null ){
            c.andIdIn(request.getIdsStr());
        }
        //状态
        if( request.getMontype() != null ){
            c.andMontypeEqualTo(request.getMontype());
        }

        List<PayCloudDfMoneyCustom> list = payCloudDfMoneyCustomMapper.selectJoinCompanyStaff(example);
        List<CloudDfMoneyInfo> infos = new ArrayList<>();
        for (PayCloudDfMoneyCustom payCloudDfMoneyCustom : list) {
            CloudDfMoneyInfo cloudDfMoneyInfo = new CloudDfMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyCustom.class, CloudDfMoneyInfo.class, false);
            beanCopier.copy(payCloudDfMoneyCustom, cloudDfMoneyInfo, null);

            infos.add(cloudDfMoneyInfo);
        }
        /*List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(example);
        List<CloudDfMoneyInfo> infos = new ArrayList<>();
        if(list.isEmpty() || list==null ){
            return null;
        }
        for(PayCloudDfMoney payCloudDfMoney:list){
            CloudDfMoneyInfo cloudDfMoneyInfo = new CloudDfMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class,CloudDfMoneyInfo.class,false);
            beanCopier.copy(payCloudDfMoney,cloudDfMoneyInfo,null);
            infos.add(cloudDfMoneyInfo);
        }*/

        return infos;

    }

    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto dfMoneyApplyWaitPay(Map<String,String> responseParam,CloudCompanyInfo companyInfo,Map<Long,CloudDfMoneyInfo> realPayMapData, String companyMoneyId, String dfIds){
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        Date date = new Date();

        if( companyInfo == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司信息不能为空");
        }

        String[] ids_str = dfIds.split(",");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < ids_str.length; i++) {
            ids.add(Long.parseLong(ids_str[i]));
        }
        if( ids.isEmpty() || ids.size() <=0 ){//未选择代付明细ID
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择代付信息");
        }

        //查询公司账号信息
        String companyId = companyInfo.getId(); //公司ID
        BigDecimal cloudMoney = companyInfo.getCloudmoney(); //账户金额
        String cloudcode = companyInfo.getCloudcode(); //金额校验码

        // 接口数据
        String response = responseParam.get("response");  //接口返回数据
        String requestUrl = responseParam.get("requestUrl");  //请求接口连接
        String requestParam = responseParam.get("requestParam");  //请求参数

        //解析返回参数
        JSONObject responseMap = JSONObject.fromObject(response);
        if( responseMap.isEmpty() || responseMap == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付接口异常");
        }
        String code = responseMap.get("code").toString();


        //存储日志记录
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();
        String logPath = "/logs/jpf-manage-web/log/";
        String fileName = "dfMoneyPay";
        logContent.append("\n\nTime:" + myfmt.format(date));
        logContent.append("\n接口返回信息:" + response);

        if( code.equals("10000") || code.equals("30004") ){ //10000=代付成功  30004=无代付请求数据（支付限额）

            //记录pay_cloud_interface_stream表操作记录
            //CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
            PayCloudInterfaceStream payCloudInterfaceStream = new PayCloudInterfaceStream();
            //存取短信接口调用记录
            payCloudInterfaceStream.setRequestUrl(requestUrl);
            payCloudInterfaceStream.setRequestContent(requestParam);
            payCloudInterfaceStream.setType((byte)2);
            payCloudInterfaceStream.setResponseContent(response);
            payCloudInterfaceStream.setCompanyMoneyId(companyMoneyId);
            payCloudInterfaceStream.setTaskId("0");
            payCloudInterfaceStream.setStaffId("0");
            payCloudInterfaceStream.setAddtime(date);
            //cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);
            payCloudInterfaceStreamMapper.insertSelective(payCloudInterfaceStream);

            BigDecimal applyFailMoney = new BigDecimal("0");
            //是否存在data变量
            if( responseMap.has("data") && StringUtils.isNotBlank(responseMap.get("data").toString()) ){
                JSONObject data = JSONObject.fromObject(responseMap.getString("data"));
                if( data.has("filterdata") ){ //接口返回 代付限额明细数据
                    JSONObject  filterdata = JSONObject.fromObject(data.getString("filterdata"));
                    JSONArray finalData = JSONArray.fromObject(filterdata.getString("data"));
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
                            //List<Long> filterDfIdArr = new ArrayList<>();
                            //filterDfIdArr.add(filterDfId);
                            recordData.setMontype(5); //更新为支付限制
                            recordData.setId(filterDfId);
                            recordData.setUpdatetime(date);
                            //jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,filterDfIdArr);
                            int count = payCloudDfMoneyMapper.updateByPrimaryKeySelective(recordData);
                            if( count <= 0 ){
                                logContent.append("\n代付数据更新失败：代付明细ID:"+dfIds);
                                LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
                                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付数据更新失败");
                            }
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
                    //int upCompanyCount = cloudCompanyServiceFacade.updateSetiveById(payCloudCompany);
                    int upCompanyCount = payCloudCompanyMapper.updateByPrimaryKeySelective(payCloudCompany);
                    if( upCompanyCount <=0 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司账户信息更新失败");
                    }
                    logContent.append("\n打款金额：代付明细ID:"+cloudRets.getId()+"\t收款人："+cloudRets.getBanknickname()+"\t金额："+cloudRets.getCommoney());

                    //更新订单下对应的代付明细状态为：打款中
                    PayCloudDfMoney recordData = new PayCloudDfMoney();
                    //recordData.setMontype(4); //更新为打款中
                    //List<Long> currDfIdArr = new ArrayList<>();
                    //currDfIdArr.add(key);
                    //jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,currDfIdArr);

                    recordData.setMontype(4); //更新为支付限制
                    recordData.setId(key);
                    recordData.setUpdatetime(date);
                    //jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,filterDfIdArr);
                    int count = payCloudDfMoneyMapper.updateByPrimaryKeySelective(recordData);
                    if( count <= 0 ){
                        logContent.append("\n代付数据更新失败：代付明细ID:"+dfIds);
                        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付数据更新失败");
                    }

                }

            }else{
                // 30004 提交单条数据到接口由于支付限制 接口无返回data参数
                if(code.equals("30004")){

                    if(  realPayMapData.containsKey(dfIds)  ){//存在限制代付ID 删除
                        CloudDfMoneyInfo cloudInfos = realPayMapData.get(dfIds);
                        applyFailMoney.add(cloudInfos.getCommoney());
                        realPayMapData.remove(dfIds);
                        logContent.append("\n支付限额：代付明细ID:"+dfIds+"\t收款人："+cloudInfos.getBanknickname()+"\t金额："+cloudInfos.getCommoney());
                    }else{
                        logContent.append("\n数据操作异常：代付明细ID:"+dfIds);
                        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据操作异常");
                    }

                    //更新订单下对应的代付明细状态为：打款中
                    PayCloudDfMoney recordData = new PayCloudDfMoney();
                    recordData.setMontype(5); //更新为支付限制
                    recordData.setId(Long.parseLong(dfIds));
                    recordData.setUpdatetime(date);

                    //jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByids(recordData,ids);
                    int count = payCloudDfMoneyMapper.updateByPrimaryKeySelective(recordData);
                    if( count <= 0 ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付数据更新失败");
                    }
                }
            }

        }else{

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付请求失败,状态码:"+code);

        }

        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

        return jpfResponseDto;

    }



    // 更新某批次订单号的id相关联的打款信息为可代付
    @Override
    public int updateDfMontype(String companyMoneyId){
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andCompanyMoneyIdEqualTo(companyMoneyId);

        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();
        payCloudDfMoney.setMontype(1);
        return payCloudDfMoneyMapper.updateByExampleSelective(payCloudDfMoney,e);
    }
    //根据商户号和批次id查询出当前批次详情

    @Override
    public GetCloudMoneyDfResponse getBatchList(String pactNo,String merchNo) {

        if ( StringUtils.isBlank(pactNo) ||   StringUtils.isBlank(merchNo))
        {
            return null;
        }

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        example.setOrderByClause("addtime ASC");
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merchNo);
        c.andPactnoEqualTo(pactNo);

        List<PayCloudDfMoneyInterfaceCustom> list = payCloudDfMoneyInterfaceCustomMapper.selectListDf(example);

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        List<CloudDfMoneyInterfaceInfo> resultList = new ArrayList<>();

        for (PayCloudDfMoneyInterfaceCustom one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyInterfaceCustom.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            //敏感数据星号代替
            info.setUsername(ToolUtils.getStarString(one.getUsername(),3,7));//手机号
            info.setBankphone(ToolUtils.getStarString(one.getBankphone(),3,7));//手机号
            info.setBankno(ToolUtils.getStarString2(one.getBankno(),4,4));
            info.setIdno(ToolUtils.getStarString2(one.getIdno(),4,4));
            resultList.add(info);

        }
        GetCloudMoneyDfResponse response = new GetCloudMoneyDfResponse();
        response.setList(resultList);
        int count = payCloudDfMoneyInterfaceCustomMapper.countByExampleList(example);
        response.setCount(count);
        return response;
    }

}
