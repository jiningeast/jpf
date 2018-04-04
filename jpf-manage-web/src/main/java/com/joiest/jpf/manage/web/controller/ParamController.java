package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.entity.MerchantTypeInfo;
import com.joiest.jpf.entity.PcaInfo;
import com.joiest.jpf.facade.MerTypeServiceFacade;
import com.joiest.jpf.facade.PcaServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/param")
public class ParamController {

    private static final Logger logger = LogManager.getLogger(ParamController.class);

    @Autowired
    private PcaServiceFacade pcaServiceFacade;

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;

    private List<PcaInfo> pcaInfoList;

    @RequestMapping("/getPca")
    @ResponseBody
    public List<PcaInfo> getPca(String pid){
        pcaInfoList = pcaServiceFacade.getPcas(StringUtils.isBlank(pid)?"0":pid);
        logger.info("pcaInfoList="+pcaInfoList);
        return pcaServiceFacade.getPcas(StringUtils.isBlank(pid)?"0":pid);
    }

    @RequestMapping("/getType")
    @ResponseBody
    public List<MerchantTypeInfo> getType(String pid) {
        return merTypeServiceFacade.getMerTypes(pid);
    }


}
