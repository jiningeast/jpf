package com.joiest.jpf.facade.impl;
import com.joiest.jpf.common.custom.PayCloudCompanyCustom;
import com.joiest.jpf.common.po.PayCloudCompanyExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudCompanyCustomMapper;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import com.joiest.jpf.dto.GetCloudCompanyResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class CloudCompanyServiceFacadeImpl implements CloudCompanyServiceFacade {


    @Autowired
    private PayCloudCompanyCustomMapper payCloudCompanyCustomMapper;

    /**
     * 代理公司列表---后台
     */
    public GetCloudCompanyResponse getAgentList(GetCloudCompanyRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayCloudCompanyExample example = new PayCloudCompanyExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("created DESC");

        PayCloudCompanyExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getMerchNo()))
        {
            c.andMerchNoEqualTo(request.getMerchNo() );
        }

        List<PayCloudCompanyCustom> list = payCloudCompanyCustomMapper.selectCompanyAgent(example);
        List<CloudCompanyInfo> infoList = new ArrayList<>();
        for (PayCloudCompanyCustom one : list)
        {
            CloudCompanyInfo info = new CloudCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyCustom.class, CloudCompanyInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetCloudCompanyResponse response = new GetCloudCompanyResponse();
        response.setList(infoList);
        int count = payCloudCompanyCustomMapper.countByExample(example);
        response.setCount(count);
        return response;
    }



}
