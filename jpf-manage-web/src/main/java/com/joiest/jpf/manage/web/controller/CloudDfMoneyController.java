package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cloudDfMoney")
public class CloudDfMoneyController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;


    /**
     * 代付开始打款
     */
    @RequestMapping("/batchMoney")
    @ResponseBody
    public JpfResponseDto batchMoney(String fid){

        //更新订单表 为打款中
        CloudCompanyMoneyInfo companyMoneyInfo = cloudCompanyMoneyServiceFacade.getRecByFid(fid);
        if( companyMoneyInfo.getMontype() == 3 ){//已发起打款
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单已发起打款，请勿重复点击");
        }
        if( companyMoneyInfo.getMontype() != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单不能发起打款请求，请联系管理员");
        }

        PayCloudCompanyMoney comMoneyData = new PayCloudCompanyMoney();
        comMoneyData.setMontype((byte)3);
        JpfResponseDto jpfcomMoneyDto = cloudCompanyMoneyServiceFacade.updateRecByFid(comMoneyData,fid);

        //更新订单下对应的代付明细状态为：打款中
        PayCloudDfMoney recordData = new PayCloudDfMoney();
        recordData.setMontype(4); //更新为打款中

        JpfResponseDto jpfResponseDto = cloudDfMoneyServiceFacade.updateDfRecordsByFid(recordData,fid);

        return jpfResponseDto;
    }



}
