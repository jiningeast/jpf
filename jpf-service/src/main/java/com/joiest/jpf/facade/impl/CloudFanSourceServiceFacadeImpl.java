package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayCloudCompanyCustom;
import com.joiest.jpf.common.custom.PayCloudFansourceCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudFansource;
import com.joiest.jpf.common.po.PayCloudFansourceExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudFansourceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudFansourceMapper;
import com.joiest.jpf.dto.GetCloudFansourceRequest;
import com.joiest.jpf.dto.GetCloudFansourceResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudFanSourceInfo;
import com.joiest.jpf.facade.CloudFanSourceServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CloudFanSourceServiceFacadeImpl implements CloudFanSourceServiceFacade {


    @Autowired
    private PayCloudFansourceMapper payCloudFansourceMapper;

    @Autowired
    private PayCloudFansourceCustomMapper payCloudFansourceCustomMapper;
    /**
     * 添加粉丝来源
     * */
    public int addFanSource(Map<String,String> map){

        PayCloudFansource payCloudFansource = new PayCloudFansource();

        payCloudFansource.setCatid(new Integer(map.get("catId")));
        payCloudFansource.setCat(map.get("cat"));
        payCloudFansource.setMobile(map.get("mobile"));
        payCloudFansource.setName(map.get("name"));
        payCloudFansource.setCreated(new Date());

        return payCloudFansourceMapper.insertSelective(payCloudFansource);
    }


    /**
     * 获取粉丝信息通过手机号
     * */
    public CloudFanSourceInfo getFanSourceByMobile(String mobile){

        PayCloudFansourceExample example = new PayCloudFansourceExample();
        PayCloudFansourceExample.Criteria c= example.createCriteria();
        c.andMobileEqualTo(mobile);

        List<PayCloudFansource> getPayCloudFansources = payCloudFansourceMapper.selectByExample(example);
        if(getPayCloudFansources == null || getPayCloudFansources.isEmpty()) return null;

        PayCloudFansource payCloudFansource = getPayCloudFansources.get(0);

        CloudFanSourceInfo cloudFanSourceInfo = new CloudFanSourceInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudFansource.class,CloudFanSourceInfo.class,false);

        beanCopier.copy(payCloudFansource,cloudFanSourceInfo,null);

        return cloudFanSourceInfo;
    }
    /**
     * 代理公司列表---后台
     */
    public GetCloudFansourceResponse getFansourceList(GetCloudFansourceRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayCloudFansourceExample example = new PayCloudFansourceExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("ID DESC");

        PayCloudFansourceExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getMobile()))
        {
            c.andMobileEqualTo(request.getMobile() );
        }
        if( StringUtils.isNotBlank(request.getName())){
            c.andNameEqualTo(request.getName());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andCreatedGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andCreatedLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        List<PayCloudFansourceCustom> list = payCloudFansourceCustomMapper.selectFansourceList(example);
        List<CloudFanSourceInfo> infoList = new ArrayList<>();
        for (PayCloudFansourceCustom one : list)
        {
            CloudFanSourceInfo info = new CloudFanSourceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudFansourceCustom.class, CloudFanSourceInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetCloudFansourceResponse response = new GetCloudFansourceResponse();
        response.setList(infoList);
        int count = payCloudFansourceCustomMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    @Override
    public CloudFanSourceInfo getFansource(String id)
    {
        if ( StringUtils.isBlank(id))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayCloudFansource payCloudFansource = payCloudFansourceMapper.selectByPrimaryKey(id);
        CloudFanSourceInfo cloudFanSourceInfo = new CloudFanSourceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayCloudFansource.class, CloudFanSourceInfo.class, false);
        beanCopier.copy(payCloudFansource, cloudFanSourceInfo, null);

        return cloudFanSourceInfo;
    }
    //备注添加
    @Override
    public JpfResponseDto editPass(GetCloudFansourceRequest request,String id) throws  Exception{
        //根据id查询当前记录是否存在
        if ( StringUtils.isBlank(request.getId()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空！");
        }

        PayCloudFansourceExample e = new PayCloudFansourceExample();
        PayCloudFansourceExample.Criteria c = e.createCriteria();
        c.andIdEqualTo(request.getId());

        List<PayCloudFansource> getPayCloudFansource = payCloudFansourceMapper.selectByExample(e);
        if(getPayCloudFansource==null || getPayCloudFansource.isEmpty()){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条记录不存在！");
        }else if(request.getRemark()==null || StringUtils.isBlank(request.getRemark())){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "备注不能为空！");
        }

        //根据数据更新备注
        PayCloudFansource payCloudFansource =new PayCloudFansource();
        payCloudFansource.setRemark(request.getRemark());
        payCloudFansource.setRemarkuid(id);

       int res= payCloudFansourceMapper.updateByExampleSelective(payCloudFansource,e);
       if(res!=1){
           throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "审核失败！");
       }
        return  new JpfResponseDto();

    }
}
