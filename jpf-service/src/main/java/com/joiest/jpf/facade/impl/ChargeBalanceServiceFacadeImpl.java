package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeBalance;
import com.joiest.jpf.common.po.PayChargeBalanceExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeBalanceMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMapper;
import com.joiest.jpf.dto.ChargeBalanceRequest;
import com.joiest.jpf.dto.ChargeBalanceResponse;
import com.joiest.jpf.entity.ChargeBalanceInfo;
import com.joiest.jpf.facade.ChargeBalanceServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeBalanceServiceFacadeImpl implements ChargeBalanceServiceFacade {

    @Autowired
    private PayChargeCompanyMapper payChargeCompanyMapper;

    @Autowired
    private PayChargeBalanceMapper payChargeBalanceMapper;

    /**
     * 获取商户列表
     */
    @Override
    public ChargeBalanceResponse getRecords(ChargeBalanceRequest request){
        ChargeBalanceResponse response = new ChargeBalanceResponse();

        PayChargeBalanceExample e = new PayChargeBalanceExample();
        PayChargeBalanceExample.Criteria c = e.createCriteria();
        if (StringUtils.isNotBlank(""+request.getPage()) && request.getPage() > 0 ){
            e.setPageNo(request.getPage());
        }else{
            e.setPageNo(1);
        }
        if (StringUtils.isNotBlank(""+request.getRows()) && request.getRows() > 0 ){
            e.setPageSize(request.getRows());
        }else{
            e.setPageSize(10);
        }

        e.setOrderByClause("id DESC");

        if(StringUtils.isNotBlank(request.getAlertPhone())){
            c.andAlertPhoneEqualTo(request.getAlertPhone());
        }
        if(request.getAlertSwitch()!=null && !request.getAlertSwitch().equals("")){
            c.andAlertSwitchEqualTo(request.getAlertSwitch());
        }
        if(request.getType()!=null && !request.getType().equals("")){
            c.andTypeEqualTo(request.getType());
        }
        List<PayChargeBalance> list =  payChargeBalanceMapper.selectByExample(e);
        response.setCount(payChargeBalanceMapper.countByExample(e));
        List<ChargeBalanceInfo> infos = new ArrayList<>();
        for (PayChargeBalance one:list){
            ChargeBalanceInfo info = new ChargeBalanceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeBalance.class,ChargeBalanceInfo.class,false);
            beanCopier.copy(one,info,null);
            infos.add(info);
        }
        response.setList(infos);
        return response;
    }

    /**
     * 根据主键id获取余额信息
     */
    @Override
    public ChargeBalanceInfo getRecordByPrimaryKey(String id){
        PayChargeBalance payChargeCompany = payChargeBalanceMapper.selectByPrimaryKey(id);
        ChargeBalanceInfo chargeCompanyInfo = new ChargeBalanceInfo();
        if ( payChargeCompany != null ){
            BeanCopier beanCopier = BeanCopier.create(PayChargeBalance.class,ChargeBalanceInfo.class,false);
            beanCopier.copy(payChargeCompany,chargeCompanyInfo,null);
        }
        return chargeCompanyInfo;
    }

    /**
     * 修改余额信息
     */
    @Override
    public JpfResponseDto edit(ChargeBalanceRequest request){
        if(StringUtils.isBlank(request.getId()) || request.getId()==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空！");
        }
        //查询当前修改信息是否存在
        PayChargeBalance payChargeCompany = payChargeBalanceMapper.selectByPrimaryKey(request.getId());
        if(payChargeCompany==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "当前编辑信息不存在！");
        }
        PayChargeBalance payChargeBalanceUp = new PayChargeBalance();
        payChargeBalanceUp.setId(request.getId());
        payChargeBalanceUp.setAlertLimit(request.getAlertLimit());
        payChargeBalanceUp.setAlertPhone(request.getAlertPhone());
        payChargeBalanceUp.setStopLimit(request.getStopLimit());
        payChargeBalanceUp.setType(request.getType());
        payChargeBalanceUp.setAlertSwitch(request.getAlertSwitch());
        payChargeBalanceUp.setUpdatetime(new Date());
        int updateRes = payChargeBalanceMapper.updateByPrimaryKeySelective(payChargeBalanceUp);
        if ( updateRes > 0 ){
            return new JpfResponseDto();
        }else{
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("修改失败");

            return jpfResponseDto;
        }
    }
    /**
     * 获取余额信息 one
     * */
    @Override
    public ChargeBalanceInfo getChargeBalanceOne(JSONObject param){

        PayChargeBalanceExample example = new PayChargeBalanceExample();
        PayChargeBalanceExample.Criteria c = example.createCriteria();

        if(param.containsKey("type") && StringUtils.isNotBlank(param.get("type").toString())){

            byte type = Byte.valueOf(param.get("type").toString());
            c.andTypeEqualTo(type);
        }
        List<PayChargeBalance> list = payChargeBalanceMapper.selectByExample(example);

        if(list == null || list.size() == 0) return null;

        ChargeBalanceInfo chargeBalanceInfo= new ChargeBalanceInfo();

        BeanCopier beanCopier = BeanCopier.create(PayChargeBalance.class,ChargeBalanceInfo.class,false);
        beanCopier.copy(list.get(0),chargeBalanceInfo,null);

        return chargeBalanceInfo;
    }
    /**
     * 根据主键修改余额信息
     * */
    @Override
    public int updateBalanceById(ChargeBalanceInfo chargeBalanceInfo){

        PayChargeBalance payChargeBalance = new PayChargeBalance();

        BeanCopier beanCopier = BeanCopier.create(ChargeBalanceInfo.class,PayChargeBalance.class,false);
        beanCopier.copy(chargeBalanceInfo,payChargeBalance,null);

        return payChargeBalanceMapper.updateByPrimaryKeySelective(payChargeBalance);
    }
}
