package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopStockLogCustom;
import com.joiest.jpf.common.po.PayShopStockLog;
import com.joiest.jpf.common.po.PayShopStockLogExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopStockLogCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockLogMapper;
import com.joiest.jpf.dto.GetShopStockLogRequest;
import com.joiest.jpf.dto.GetShopStockLogResponse;
import com.joiest.jpf.entity.ShopStockLogInfo;
import com.joiest.jpf.facade.ShopStockLogServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopStockLogServiceFacadeImpl implements ShopStockLogServiceFacade {

    @Autowired
    private PayShopStockLogMapper payShopStockLogMapper;


    @Autowired
    private PayShopStockLogCustomMapper payShopStockLogCustomMapper;
    /**
     * 进销存列表---后台
     */
    @Override
    public GetShopStockLogResponse getList(GetShopStockLogRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopStockLogExample example = new PayShopStockLogExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("ID DESC");

        PayShopStockLogExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getProductName()))
        {
            c.andProductNameLike("%" + request.getProductName() +"%");
        }

        if( StringUtils.isNotBlank(request.getStockOrderId())){
            c.andStockOrderIdEqualTo(request.getStockOrderId());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        List<PayShopStockLogCustom> list = payShopStockLogCustomMapper.selectCustom(example);
        List<ShopStockLogInfo> infoList = new ArrayList<>();


        for (PayShopStockLogCustom one : list)
        {

            ShopStockLogInfo info = new ShopStockLogInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopStockLogCustom.class, ShopStockLogInfo.class, false);
            beanCopier.copy(one, info, null);
            //循环添加info里面的值
            if(info!=null){
                //取出第一条数据
                PayShopStockLogExample exampleasc = new PayShopStockLogExample();
                exampleasc.setOrderByClause("ID ASC");
                PayShopStockLogExample.Criteria casc = exampleasc.createCriteria();
                // 添加时间搜索
                if (StringUtils.isNotBlank(request.getAddtimeStart()))
                {
                    casc.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
                }
                if (StringUtils.isNotBlank(request.getAddtimeEnd()))
                {
                    casc.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
                }
                //取出最后一条数据
                PayShopStockLogExample exampledesc = new PayShopStockLogExample();
                exampledesc.setOrderByClause("ID DESC");
                PayShopStockLogExample.Criteria cdesc = exampledesc.createCriteria();
                // 添加时间搜索
                if (StringUtils.isNotBlank(request.getAddtimeStart()))
                {
                    cdesc.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
                }
                if (StringUtils.isNotBlank(request.getAddtimeEnd()))
                {
                    cdesc.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
                }
                casc.andProductIdEqualTo(info.getProductId());
                List<PayShopStockLogCustom> listasc = payShopStockLogCustomMapper.selectCustomAsc(exampleasc);
                cdesc.andProductIdEqualTo(info.getProductId());
                List<PayShopStockLogCustom> listdesc = payShopStockLogCustomMapper.selectCustomAsc(exampledesc);
                //设置值
                if(listasc !=null && !listasc.isEmpty()){
                    info.setBeginCount(listasc.get(0).getInitAmount());//期初
                    info.setBeginMoney(listasc.get(0).getInitMoney());//期初
                }
                if(listdesc !=null && !listdesc.isEmpty()){
                    info.setFinishCount(listdesc.get(0).getFinalAmount());//期末
                    info.setFinishMoney(listdesc.get(0).getFinalMoney());//期末
                }

             }
            infoList.add(info);
        }

        GetShopStockLogResponse response = new GetShopStockLogResponse();
        response.setList(infoList);
        if(list.size()>0){
            response.setCount(list.get(0).getCount());
        }
        return response;
    }





}
