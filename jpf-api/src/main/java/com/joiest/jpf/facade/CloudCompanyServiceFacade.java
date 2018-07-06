package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import com.joiest.jpf.dto.GetCloudCompanyResponse;
import com.joiest.jpf.dto.GetCloudCompanysRequest;
import com.joiest.jpf.dto.GetCloudCompanysResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;

import java.util.List;

import javax.servlet.http.HttpSession;


public interface CloudCompanyServiceFacade {

    /**
     * 插入一条记录
     */
    //public int insRecord(CloudCompanyInfo ordersInfo);

    /**
     * 列表---后台
     */
    public GetCloudCompanyResponse getAgentList(GetCloudCompanyRequest request);

    /**
     * 云账户金额校验
     */
    public Boolean checkCompanyMoneyVerify(String id);

    /**
     * 添加公司基本信息
     */
    public JpfResponseDto addCloudCompany(GetCloudCompanyRequest request,int account) throws Exception;

    /**
     * 获取公司信息，不区分类型
     */
    public GetCloudCompanysResponse getAllCompanys(GetCloudCompanysRequest request);
}
