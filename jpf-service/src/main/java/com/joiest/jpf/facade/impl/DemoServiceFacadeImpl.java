package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.SysParaInfo;
import com.joiest.jpf.common.po.SysParaInfoExample;
import com.joiest.jpf.dao.repository.mapper.generate.SysParaInfoMapper;
import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.facade.DemoServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DemoServiceFacadeImpl implements DemoServiceFacade {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DemoServiceFacadeImpl.class);
    @Autowired
    private SysParaInfoMapper paraInfoMapper;

    @Override
    public GetValueResponse getValue(GetValueRequest request){
        SysParaInfoExample exa = new SysParaInfoExample();
        SysParaInfoExample.Criteria c = exa.createCriteria();
        c.andParaCodeEqualTo(request.getCode());
        c.andIsUsedEqualTo(true);
        List<SysParaInfo> paraInfos = paraInfoMapper.selectByExample(exa);
        if(paraInfos == null || paraInfos.size() != 1){
            logger.error("获取值, code:{}, 未找到或者异常", request.getCode());
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未找到或者异常");
        }
        SysParaInfo paraInfo = paraInfos.get(0);
        GetValueResponse response = new GetValueResponse();
        response.setRemark(paraInfo.getParaValue());
        return response;
    }
}
