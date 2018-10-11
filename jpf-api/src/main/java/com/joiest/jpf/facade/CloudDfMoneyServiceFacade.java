package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.dto.CloudDfMoneyRequest;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;

import java.util.List;
import java.util.Map;

public interface CloudDfMoneyServiceFacade {

    //获取指定日期的记录 15
    public GetCloudMoneyDfResponse getDfMoneyList(String start, String end, String uid, long pageNo, long pageSize, int flag);

    //获取用户总金额
    public Double getUserDfTotalMoney(String uid);

    //获取用户记录 按月份
    public List<CloudDfMoneyInterfaceInfo> getUserMonthList(Long uid);

    //根据订单号更新代付明细
    public JpfResponseDto updateDfRecordsByFid(PayCloudDfMoney record, String fid);

    //根据主键字符串更新代付明细
    public JpfResponseDto updateDfRecordsByids(PayCloudDfMoney record, List<Long> ids);


    //获取充值记录数据通过主键
    public CloudDfMoneyInterfaceInfo getDfMoneyById(Long id);

    //更新代付状态
    public int updateDfMoneyActive(Map<String,String> dfMoney,Long id);

    //根据主键ID更新数据库信息
    public int updateDfMoneyActiveById(CloudDfMoneyRequest request,Long id);

    /**
     * 插入记录
     */
    public long addDfMoney(CloudDfMoneyInfo cloudDfMoneyInfo);

    /**
     * 获取指定批次充值列表
     * @param batchId
     * @param dfif
     * @param pageNo
     * @param pageSize
     * @param flag
     * @return
     */
    public GetCloudMoneyDfResponse getDfDetailList(String batchId, String dfif, long pageNo, long pageSize, int flag);

    //查询代付明细信息
    public List<CloudDfMoneyInfo> getAllBySective(CloudDfMoneyRequest request);

    //财务查询报表
    public List<CloudDfMoneyInfo> getAllBySectiveToCaiwu(CloudDfMoneyRequest request);


    //发起申请代付请求
    public JpfResponseDto dfMoneyApplyWaitPay(Map<String,String> responseParam,CloudCompanyInfo companyInfo,Map<Long,CloudDfMoneyInfo> realPayMapData, String companyMoneyId, String dfIds);

    // 更新某批次订单号的id相关联的打款信息为可代付
    public int updateDfMontype(String companyMoneyId);


    /**
     * 获取指定批次充值列表
     * @param batchId
     */
    public GetCloudMoneyDfResponse getBatchList(String batchId, String merchNo);
}


