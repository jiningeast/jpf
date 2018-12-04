package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.custom.PayShopOrderCustomExample;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.common.po.PayShopOrderExample;
import com.joiest.jpf.common.po.PayShopProductInfo;
import com.joiest.jpf.common.po.PayShopProductInfoExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductInfoMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceRequest;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceResponse;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;
import com.joiest.jpf.entity.ShopProductInfoInfo;
import com.joiest.jpf.facade.ShopOrderInfoInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;

public class ShopOrderInfoInterfaceServiceFacadeImpl implements ShopOrderInfoInterfaceServiceFacade {

    @Autowired
    private PayShopOrderCustomMapper payShopOrderCustomMapper;

    @Autowired
    private PayShopOrderMapper payShopOrderMapper;

    @Autowired
    private PayShopProductInfoMapper payShopProductInfoMapper;
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

        PayShopOrderCustomExample example = new PayShopOrderCustomExample();
        PayShopOrderCustomExample example1 = example;
        example.setPageNo(Long.parseLong(request.getPage()));
        example.setPageSize(Long.parseLong(request.getPageSize()));
        example.setOrderByClause("addtime DESC");

        PayShopOrderCustomExample.Criteria c =example.createCriteria();
        PayShopOrderCustomExample.Criteria c2 =example.createCriteria();
        c.andCustomerIdEqualTo(request.getUid());
        //目前只支持  订单号 、商品名称（商品类型） 搜索
        if ( StringUtils.isNotBlank(request.getKeyword()))
        {
            //c.andOrderNoEqualTo( request.getKeyword()  );
            //查询商品名称
            PayShopProductInfoExample payShopProductInfoExample = new PayShopProductInfoExample();
            PayShopProductInfoExample.Criteria shopC = payShopProductInfoExample.createCriteria();
            shopC.andTypeNameLike("%"+request.getKeyword()+"%");
            List<PayShopProductInfo> shopProductInfoList = payShopProductInfoMapper.selectByExample(payShopProductInfoExample);
            List<Integer> productInfoStr = new ArrayList();
            productInfoStr.add(0);
            if( shopProductInfoList.size() <=0 || shopProductInfoList == null){
                c2.andProductInfoIdIn(productInfoStr);
            }else{

                for (PayShopProductInfo one : shopProductInfoList)
                {
                    ShopProductInfoInfo info = new ShopProductInfoInfo();
                    BeanCopier beanCopier = BeanCopier.create(PayShopProductInfo.class, ShopProductInfoInfo.class, false);
                    beanCopier.copy(one, info, null);
                    productInfoStr.add(info.getId());
                }
                c2.andProductInfoIdIn(productInfoStr);
            }
            c2.andOrderNoEqualTo( request.getKeyword()   );
            //example.or(c2);
            example.and(c2);
        }

        List<PayShopOrderCustom> list = payShopOrderCustomMapper.selectByExampleInterfaceJoin(example);
        if( list.size() <=0 || list == null){
            return null;
        }
        List<ShopOrderInterfaceInfo> infoList = new ArrayList<>();

        for (PayShopOrderCustom one : list)
        {
            ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInterfaceInfo.class, false);
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
    public ShopOrderInterfaceInfo getOne(ShopOrderInfoInterfaceRequest request)
    {
        if ( StringUtils.isBlank(request.getOrderNo()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单编号不能为空");
        }
        PayShopOrderCustomExample example = new PayShopOrderCustomExample();
        PayShopOrderCustomExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(request.getUid());
        c.andOrderNoEqualTo(request.getOrderNo());
        PayShopOrderCustom payShopOrderCustom = payShopOrderCustomMapper.selectOrderInterfaceAll(example);

        if(payShopOrderCustom==null){
            return null;
        }
        ShopOrderInterfaceInfo shopOrderInfoInterface = new ShopOrderInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInterfaceInfo.class, false);
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

    /**
     * 检测订单并取消超时的订单(适用于系统启动之初由定时器监测到的超时订单)
     * @param time 当前时间24小时之前的时间
     */
    @Override
    public void timerDetectShopOrderAndCancel(Date time) {
        PayShopOrderExample payShopOrderExample = new PayShopOrderExample();
        payShopOrderExample.createCriteria().andStatusEqualTo((byte)0).andAddtimeLessThanOrEqualTo(time);
        PayShopOrder record = new PayShopOrder();
        record.setStatus((byte)3);
        record.setUpdatetime(new Date());
        payShopOrderMapper.updateByExampleSelective(record ,payShopOrderExample);
    }

    /**
     * 异常订单处理
     */
    @Override
    public List<ShopOrderInterfaceInfo>  getAbnormalOrders(ShopOrderInterfaceInfo request)
    {

        PayShopOrderExample example = new PayShopOrderExample();
        example.setOrderByClause("addtime ASC");
        example.setPageNo(1);
        example.setPageSize(1);

        PayShopOrderExample.Criteria c =example.createCriteria();
        c.andStatusEqualTo((byte)request.getStatus());
        c.andRechargeStatusEqualTo(request.getRechargeStatus());
        c.andInterfaceTypeEqualTo(request.getInterfaceType());
        c.andPaytimeLessThanOrEqualTo(request.getPaytime());
        c.andForeignOrderNoIsNull();

        List<PayShopOrder> list = payShopOrderMapper.selectByExample(example);
        if( list.size() <=0 || list == null){
            return null;
        }
        List<ShopOrderInterfaceInfo> infoList = new ArrayList<>();

        for (PayShopOrder one : list)
        {
            ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopOrder.class, ShopOrderInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }

        return infoList;
    }
}
