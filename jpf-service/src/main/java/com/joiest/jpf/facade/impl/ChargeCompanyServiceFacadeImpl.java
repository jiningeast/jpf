package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyExample;
import com.joiest.jpf.common.po.PayShopCouponActive;
import com.joiest.jpf.common.po.PayShopCouponActiveExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponActiveMapper;
import com.joiest.jpf.dto.GetShopCouponActiveRequest;
import com.joiest.jpf.dto.GetShopCouponActiveResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ShopCouponActiveInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ShopCouponActiveServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ChargeCompanyServiceFacadeImpl implements ChargeCompanyServiceFacade {

    @Autowired
    private PayChargeCompanyMapper payChargeCompanyMapper;

    /**
     * 欣豆日志添加
     * */
    @Override
    public ChargeCompanyInfo getOne(ChargeCompanyInfo companyInfo){

        PayChargeCompanyExample example = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = example.createCriteria();
        if(companyInfo.getMerchNo() != null ){
            c.andMerchNoEqualTo(companyInfo.getMerchNo());
        }

        List<PayChargeCompany> list = payChargeCompanyMapper.selectByExample(example);
        if( list == null || list.size() <=0  ){
            return null;
        }
        ChargeCompanyInfo info = new ChargeCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
        beanCopier.copy(list.get(0),info,null);
        return info;
    }


}
