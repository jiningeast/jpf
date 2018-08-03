package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopBatch;
import com.joiest.jpf.common.po.PayShopBatchCoupon;
import com.joiest.jpf.common.po.PayShopBatchExample;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchCouponMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyMapper;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.ShopBatchInfo;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

public class ShopBatchServiceFacadeImpl implements ShopBatchServiceFacade {

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Autowired
    private PayShopCompanyMapper payShopCompanyMapper;

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    @Override
    public ShopBatchResponse getBatches(ShopBatchRequest shopBatchRequest){
        ShopBatchResponse shopBatchResponse = new ShopBatchResponse();

        PayShopBatchExample e = new PayShopBatchExample();
        PayShopBatchExample.Criteria c = e.createCriteria();
        if ( shopBatchRequest.getId() != null ){
            c.andIdEqualTo(shopBatchRequest.getId());
        }
        if ( shopBatchRequest.getBatchNo() != null ){
            c.andBatchNoEqualTo(shopBatchRequest.getBatchNo());
        }
        if ( shopBatchRequest.getCompanyName() != null ){
            c.andCompanyNameEqualTo(shopBatchRequest.getCompanyName());
        }
        if ( shopBatchRequest.getReceiveName() != null ){
            c.andReceiveNameEqualTo(shopBatchRequest.getReceiveName());
        }
        if ( shopBatchRequest.getSalesName() != null ){
            c.andSalesNameEqualTo(shopBatchRequest.getSalesName());
        }
        c.andStatusEqualTo(shopBatchRequest.getStatus());

        List<PayShopBatch> list = payShopBatchMapper.selectByExample(e);
        shopBatchResponse.setCount(payShopBatchMapper.countByExample(e));
        List<ShopBatchInfo> infos = new ArrayList<>();
        ShopBatchInfo shopBatchInfo = new ShopBatchInfo();
        for (PayShopBatch payShopBatch:list){
            BeanCopier beanCopier = BeanCopier.create(PayShopBatch.class, ShopBatchInfo.class, false);
            beanCopier.copy(payShopBatch, shopBatchInfo, null);

            infos.add(shopBatchInfo);
        }

        shopBatchResponse.setList(infos);

        return shopBatchResponse;
    }

    @Override
    @Transactional
    /**
     * 新增批次及券
     */
    public int addBatchCoupon(ShopBatchRequest shopBatchRequest){
        // 查询商户信息
        PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(shopBatchRequest.getCompanyId());

        PayShopBatch payShopBatch = new PayShopBatch();
        payShopBatch.setCompanyId(shopBatchRequest.getCompanyId());
        payShopBatch.setCompanyName(shopBatchRequest.getCompanyName());
        payShopBatch.setBatchNo( createBatchNo() );
        // 计算总价
        String couponsJSON = shopBatchRequest.getRows();
        List<Map<String,String>> list = JsonUtils.toObject(couponsJSON,ArrayList.class);
        int totalMoney = 0;
        int totalCount = 0;
        for ( Map<String,String> single:list ){
            Integer singgleTotal = Integer.parseInt( single.get("money") ) * Integer.parseInt( single.get("amount") );
            totalMoney += singgleTotal;

            totalCount += Integer.parseInt(single.get("amount"));
        }
        payShopBatch.setMoney(new BigDecimal(totalMoney));
        payShopBatch.setScale(1.00);
        payShopBatch.setCount(totalCount);
        payShopBatch.setExpireMonth(shopBatchRequest.getExpireMonth());
        payShopBatch.setStatus((byte)1);
        payShopBatch.setActivetedNum(0);
        payShopBatch.setSalesName(payShopCompany.getSaleName());
        payShopBatch.setReceiveName(payShopBatch.getReceiveName());
        payShopBatch.setReceiveEmail(payShopBatch.getReceiveEmail());
        payShopBatch.setReceivePhone(payShopBatch.getReceivePhone());
        payShopBatch.setEmailStatus((byte)0);
        payShopBatch.setSmsStatus((byte)0);
        payShopBatch.setOperatorId(shopBatchRequest.getOperatorId());
        payShopBatch.setOperatorName(shopBatchRequest.getOperatorName());
        payShopBatch.setAddtime(new Date());
        payShopBatchMapper.insert(payShopBatch);
        String batchId = payShopBatch.getId();

        // 添加券
        PayShopBatchCoupon payShopBatchCoupon = new PayShopBatchCoupon();
        for ( Map<String,String> single:list ){
            for ( int i=0; i<Integer.parseInt(single.get("amount")); i++){
                payShopBatchCoupon.setBatchId(batchId);
                payShopBatchCoupon.setCompanyId(shopBatchRequest.getCompanyId());
                payShopBatchCoupon.setCompanyName(shopBatchRequest.getCompanyName());
                payShopBatchCoupon.setCouponNo(createCouponNo());
                payShopBatchCoupon.setActiveCode(getRandomString(32));

                payShopBatchCouponMapper.insert(payShopBatchCoupon);
            }
        }
        return 1;
    }

    /**
     * 创建唯一的批次号
     */
    public String createBatchNo(){
        UUID uuid = UUID.randomUUID();
        String md5UUID = Md5Encrypt.md5(uuid.toString());

        return "BA" + md5UUID;
    }

    /**
     * 创建唯一的券号
     */
    public String createCouponNo(){
        return "CP" + ToolUtils.getRandomInt(100,999) + System.currentTimeMillis() + ToolUtils.getRandomInt(100,999);
    }

    /**
     * 创建唯一的激活码 a-z,A-Z,0-9
     * @param length    整个字符串长度
     */
    public static String getRandomString(int length) {
        String str = "abcdefghjklmnpqrtuvwxyABCDEFGHJKLMNPQRTUVWXY346789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            sb.append(str.charAt(random.nextInt(50)));
        }
        return sb.toString();
    }
}
