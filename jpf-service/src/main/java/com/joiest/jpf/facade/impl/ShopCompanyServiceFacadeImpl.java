package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyMapper;
import com.joiest.jpf.dto.GetShopCompanyRequest;
import com.joiest.jpf.dto.GetShopCompanyResponse;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ShopCompanyServiceFacadeImpl implements ShopCompanyServiceFacade {

    @Autowired
    private PayShopCompanyMapper payShopCompanyMapper;

    /**
     * 公司列表---后台
     */
    public GetShopCompanyResponse getList(GetShopCompanyRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopCompanyExample example = new PayShopCompanyExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");

        PayShopCompanyExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getId()) ){
            c.andIdEqualTo(request.getId());
        }
        if ( StringUtils.isNotBlank(request.getMerchNo()))
        {
            c.andMerchNoEqualTo(request.getMerchNo() );
        }
        if( StringUtils.isNotBlank(request.getCompanyName())){
            c.andCompanyNameLike("%"+ request.getCompanyName() +"%");
        }
        if( StringUtils.isNotBlank(request.getSaleName())){
            c.andSaleNameEqualTo(request.getSaleName());
        }
        if(request.getStatus()!=null && request.getStatus().toString()!=""){
            c.andStatusEqualTo(request.getStatus());
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

        List<PayShopCompany> list = payShopCompanyMapper.selectByExample(example);
        List<ShopCompanyInfo> infoList = new ArrayList<>();
        for (PayShopCompany one : list)
        {
            ShopCompanyInfo info = new ShopCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCompany.class, ShopCompanyInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopCompanyResponse response = new GetShopCompanyResponse();
        response.setList(infoList);
        int count = payShopCompanyMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    /**
     * 公司添加
     */

    public JpfResponseDto addCompany(GetShopCompanyRequest request,int account){

        //验证参数
        if(StringUtils.isBlank(request.getCompanyName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "企业名称不能为空");

        }else if(StringUtils.isBlank(request.getContactName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人姓名不能为空");

        }else if(StringUtils.isBlank(request.getContactPhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人电话不能为空");

        }else if(StringUtils.isBlank(request.getReceiveName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人不能为空");

        }else if(StringUtils.isBlank(request.getReceivePhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不能为空");

        }else if(StringUtils.isBlank(request.getReceiveEmail())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不能为空");

        }
        String pattern = "^[1][3,4,5,7,8][0-9]{9}$";

        boolean isphone = Pattern.matches(pattern, request.getContactPhone());

        if(isphone==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不正确");
        }
        boolean isphonere = Pattern.matches(pattern, request.getReceivePhone());

        if(isphonere==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不正确");
        }
        boolean isphoneresale = Pattern.matches(pattern, request.getSalePhone());

        if(isphoneresale==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "销售电话不正确");
        }
        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, request.getReceiveEmail());

        if(isemail==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不正确");
        }
       //查询当前信息表中是否存在
        //查询当前添加的是否存在
        PayShopCompanyExample example= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c = example.createCriteria();

        c.andCompanyNameEqualTo(request.getCompanyName());

        List<PayShopCompany> CompanyList = payShopCompanyMapper.selectByExample(example);

        if(CompanyList != null && !CompanyList.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "企业名称已经存在");
        }

        PayShopCompanyExample exampleName= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c2 = exampleName.createCriteria();

        c2.andContactNameEqualTo(request.getContactName());

        List<PayShopCompany> CompanyListName = payShopCompanyMapper.selectByExample(exampleName);

        if(CompanyListName != null && !CompanyListName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系人已经存在");
        }

        PayShopCompanyExample examplePhone= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c1 = examplePhone.createCriteria();

        c1.andContactPhoneEqualTo(request.getContactPhone());

        List<PayShopCompany> CompanyListContact = payShopCompanyMapper.selectByExample(examplePhone);

        if(CompanyListContact != null && !CompanyListContact.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系电话已经存在");
        }

        //插表操作
        //生成商户编号
        Date date = new Date();
        String MerchnoNew=ToolUtils.createID();
        //查询商户号是否已经存在
        PayShopCompanyExample exampleMerchno= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria cMerno = exampleMerchno.createCriteria();
        cMerno.andMerchNoEqualTo(MerchnoNew);
        List<PayShopCompany> payCloudCompanyListMerno = payShopCompanyMapper.selectByExample(exampleMerchno);
        if(payCloudCompanyListMerno != null && !payCloudCompanyListMerno.isEmpty()){
            //重新生成商户编号
            MerchnoNew=ToolUtils.createID();
        }
        PayShopCompany payShopCompany =new PayShopCompany();
        payShopCompany.setCompanyName(request.getCompanyName());
        payShopCompany.setMerchNo(MerchnoNew);
        payShopCompany.setContactName(request.getContactName());
        payShopCompany.setContactPhone(request.getContactPhone());
        payShopCompany.setReceiveName(request.getReceiveName());
        payShopCompany.setReceivePhone(request.getReceivePhone());
        payShopCompany.setReceiveEmail(request.getReceiveEmail());
        payShopCompany.setSaleName(request.getSaleName());
        payShopCompany.setSalePhone(request.getSalePhone());
        payShopCompany.setAddtime(date);
        int res = payShopCompanyMapper.insertSelective(payShopCompany);
        if(res!=1){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "添加失败");
        }
        return new JpfResponseDto();
    }

    /**
     * 公司单条记录
     */
    @Override
    public ShopCompanyInfo getCompanyOne(String id)
    {
        if ( StringUtils.isBlank(id))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayShopCompany  payShopCompany= payShopCompanyMapper.selectByPrimaryKey(id);

        ShopCompanyInfo shopCompanyInfo = new ShopCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopCompany.class, ShopCompanyInfo.class, false);
        beanCopier.copy(payShopCompany,shopCompanyInfo,null);

        return shopCompanyInfo;
    }

    /**
     * 公司修改
     */

    public JpfResponseDto editCompany(GetShopCompanyRequest request,int account){

        //验证参数
        if(StringUtils.isBlank(request.getCompanyName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "企业名称不能为空");

        }else if(StringUtils.isBlank(request.getContactName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人姓名不能为空");

        }else if(StringUtils.isBlank(request.getContactPhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人电话不能为空");

        }else if(StringUtils.isBlank(request.getReceiveName())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人不能为空");

        }else if(StringUtils.isBlank(request.getReceivePhone())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不能为空");

        }else if(StringUtils.isBlank(request.getReceiveEmail())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不能为空");

        }
        String pattern = "^[1][3,4,5,7,8][0-9]{9}$";

        boolean isphone = Pattern.matches(pattern, request.getContactPhone());

        if(isphone==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不正确");
        }
        boolean isphonere = Pattern.matches(pattern, request.getReceivePhone());

        if(isphonere==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人电话不正确");
        }
        boolean isphoneresale = Pattern.matches(pattern, request.getSalePhone());

        if(isphoneresale==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "销售电话不正确");
        }
        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, request.getReceiveEmail());

        if(isemail==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "接收人邮箱不正确");
        }

        //查询当前信息表中是否存在
        PayShopCompanyExample example= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c = example.createCriteria();

        c.andIdEqualTo(request.getId());

        List<PayShopCompany> CompanyList = payShopCompanyMapper.selectByExample(example);

        if(CompanyList == null && CompanyList.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条信息不存在");
        }

        PayShopCompanyExample exampleName= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c2 = exampleName.createCriteria();

        c2.andContactNameEqualTo(request.getContactName());
        c2.andIdNotEqualTo(request.getId());
        List<PayShopCompany> CompanyListName = payShopCompanyMapper.selectByExample(exampleName);

        if(CompanyListName != null && !CompanyListName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系人已经存在");
        }

        PayShopCompanyExample examplePhone= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c1 = examplePhone.createCriteria();

        c1.andContactPhoneEqualTo(request.getContactPhone());
        c1.andIdNotEqualTo(request.getId());
        List<PayShopCompany> CompanyListContact = payShopCompanyMapper.selectByExample(examplePhone);

        if(CompanyListContact != null && !CompanyListContact.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "联系电话已经存在");
        }

        //修改表操作
        PayShopCompany payShopCompany =new PayShopCompany();
        payShopCompany.setCompanyName(request.getCompanyName());
        payShopCompany.setContactName(request.getContactName());
        payShopCompany.setContactPhone(request.getContactPhone());
        payShopCompany.setReceiveName(request.getReceiveName());
        payShopCompany.setReceivePhone(request.getReceivePhone());
        payShopCompany.setReceiveEmail(request.getReceiveEmail());
        payShopCompany.setSaleName(request.getSaleName());
        payShopCompany.setSalePhone(request.getSalePhone());

        //修改基本信息表
        String sprimatkey = request.getId();
        PayShopCompanyExample exampleup= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria ca = exampleup.createCriteria();
        ca.andIdEqualTo(sprimatkey);
        payShopCompanyMapper.updateByExampleSelective(payShopCompany,exampleup);

        return new JpfResponseDto();
    }

    /**
     * 停用公司 更改状态 1启用 0停用
     */
    @Override
    public JpfResponseDto delCompanyShop(String merchNo,int type)
    {
        if ( StringUtils.isBlank(merchNo) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "商户编号不能为空");
        }
        //查询当前添加的是否存在
        PayShopCompanyExample example= new PayShopCompanyExample();
        PayShopCompanyExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merchNo);

        List<PayShopCompany> payShopCompanyList = payShopCompanyMapper.selectByExample(example);
        if(payShopCompanyList.isEmpty() || payShopCompanyList==null){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "公司不存在");
        }
        Byte defaultStatus =payShopCompanyList.get(0).getStatus();
        PayShopCompany payShopCompany = new PayShopCompany();
        Byte status=-1;
        if(type==2){
            status = 0;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该企业已被停用");
            }
        }else if (type==1){
            status = 1;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该企业已被启用");
            }
        }
        payShopCompany.setStatus(status);
        payShopCompanyMapper.updateByExampleSelective(payShopCompany,example);
        return new JpfResponseDto();
    }

    /**
     * 公司单条记录
     */
    @Override
    public JpfResponseDto updateCompanyRecord(PayShopCompany payShopCompany)
    {
        if ( StringUtils.isBlank(payShopCompany.getId()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        int count = payShopCompanyMapper.updateByPrimaryKeySelective(payShopCompany);
        if(count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
        }

        return new JpfResponseDto();
    }

}
