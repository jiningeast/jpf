package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.common.po.PayShopOrderExample;
import com.joiest.jpf.common.po.PayShopProductType;
import com.joiest.jpf.common.po.PayShopProductTypeExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductTypeMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceRequest;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopOrderInfoInterface;
import com.joiest.jpf.entity.ShopProductTypeInfo;
import com.joiest.jpf.facade.ShopOrderInfoInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopOrderInfoInterfaceServiceFacadeImpl implements ShopOrderInfoInterfaceServiceFacade {

    @Autowired
    private PayShopOrderCustomMapper payShopOrderCustomMapper;

    @Autowired
    private PayShopOrderMapper payShopOrderMapper;

    @Autowired
    private PayShopProductTypeMapper payShopProductTypeMapper;
    /**
     * 订单列表---后台
     */
    public ShopOrderInfoInterfaceResponse getList(ShopOrderInfoInterfaceRequest request)
    {
        if ( request.getPageSize() ==null || Long.parseLong(request.getPageSize()) <= 0 || Long.parseLong(request.getPageSize()) > 10 )
        {
            request.setPageSize("10");
        }else{
            request.setPageSize(request.getPageSize());
        }

        if ( request.getPage() ==null || Long.parseLong(request.getPage()) <= 0 )
        {
            request.setPage("1");
        }

        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample example1 = new PayShopOrderExample();
        example.setPageNo(Long.parseLong(request.getPage()));
        example.setPageSize(Long.parseLong(request.getPageSize()));
        example.setOrderByClause("addtime DESC");

        PayShopOrderExample.Criteria c =example.createCriteria();
        PayShopOrderExample.Criteria c2 =example.createCriteria();
        PayShopOrderExample.Criteria c3 =example.createCriteria();
        c.andCustomerIdEqualTo(request.getUid());
        //目前只支持  订单号 、商品名称（商品类型） 搜索
        if ( StringUtils.isNotBlank(request.getKeyword()))
        {
            c.andOrderNoEqualTo( request.getKeyword()  );
            /*//查询商品名称
            PayShopProductTypeExample payShopProductTypeExample = new PayShopProductTypeExample();
            PayShopProductTypeExample.Criteria shopC = payShopProductTypeExample.createCriteria();
            shopC.andPnameLike("%"+request.getKeyword()+"%");
            List<PayShopProductType> shopProductTypesList = payShopProductTypeMapper.selectByExample(payShopProductTypeExample);
            List<Byte> productInfoStr = new ArrayList();
            productInfoStr.add((byte)0);
            if( shopProductTypesList.size() <=0 || shopProductTypesList == null){
                c2.andOrderTypeIn(productInfoStr);
            }else{

                for (PayShopProductType one : shopProductTypesList)
                {
                    ShopProductTypeInfo info = new ShopProductTypeInfo();
                    BeanCopier beanCopier = BeanCopier.create(PayShopProductType.class, ShopProductTypeInfo.class, false);
                    beanCopier.copy(one, info, null);
                    productInfoStr.add(Byte.parseByte(info.getPid().toString()));
                }
                c2.andOrderTypeIn(productInfoStr);
            }
            c2.andOrderNoEqualTo( request.getKeyword()   );
            example.or(c2);
            //example.or(c3);*/
        }

        List<PayShopOrderCustom> list = payShopOrderCustomMapper.selectByExampleInterfaceJoin(example);
        if( list.size() <=0 || list == null){
            return null;
        }
        List<ShopOrderInfoInterface> infoList = new ArrayList<>();

        for (PayShopOrderCustom one : list)
        {
            ShopOrderInfoInterface info = new ShopOrderInfoInterface();
            BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInfoInterface.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        ShopOrderInfoInterfaceResponse response = new ShopOrderInfoInterfaceResponse();
        response.setList(infoList);
        int count = payShopOrderCustomMapper.countByExample(example);
        response.setCount(count);

        return response;
    }


    /**
     * 订单详情
     */
    public ShopOrderInfoInterface getOne(ShopOrderInfoInterfaceRequest request)
    {
        if ( StringUtils.isBlank(request.getOrderNo()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单编号不能为空");
        }
        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(request.getUid());
        c.andOrderNoEqualTo(request.getOrderNo());
        PayShopOrderCustom payShopOrderCustom = payShopOrderCustomMapper.selectOrderInterfaceAll(example);

        if(payShopOrderCustom==null){
            return null;
        }
        ShopOrderInfoInterface shopOrderInfoInterface = new ShopOrderInfoInterface();
        BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInfoInterface.class, false);
        beanCopier.copy(payShopOrderCustom,shopOrderInfoInterface,null);

        return shopOrderInfoInterface;
    }


    /**
     * 订单详情
     */
    public JpfResponseDto shopOrderCancel(ShopOrderInfoInterfaceRequest request)
    {
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if ( StringUtils.isBlank(request.getOrderNo()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单编号不能为空");
        }
        //更新调教
        PayShopOrder record = new PayShopOrder();
        record.setStatus((byte)3);
        record.setUpdatetime(new Date());
        //查询条件
        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(request.getUid());
        c.andOrderNoEqualTo(request.getOrderNo());
        c.andStatusEqualTo((byte)0); //待支付状态
        int count = payShopOrderMapper.updateByExampleSelective(record ,example);

        if(count <= 0 ){
            return null;
        }

        return jpfResponseDto;
    }

}
