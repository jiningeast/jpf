package com.joiest.jpf.facade.impl;
import com.fasterxml.jackson.core.type.TypeReference;

import com.joiest.jpf.common.custom.PayCloudCompanyCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.common.util.SendMailUtil;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudCompanyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyAgentMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanySalesMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudEmployeeMapper;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import com.joiest.jpf.dto.GetCloudCompanyResponse;
import com.joiest.jpf.dto.GetCloudCompanysRequest;
import com.joiest.jpf.dto.GetCloudCompanysResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

public class CloudCompanyServiceFacadeImpl implements CloudCompanyServiceFacade {

    @Autowired
    private PayCloudCompanyCustomMapper payCloudCompanyCustomMapper;

    @Autowired
    private PayCloudCompanyMapper payCloudCompanyMapper;

    @Autowired
    private PayCloudCompanySalesMapper payCloudCompanySalesMapper;

    @Autowired
    private PayCloudCompanyAgentMapper payCloudCompanyAgentMapper;

    @Autowired
    private PayCloudEmployeeMapper payCloudEmployeeMapper;

  /*  @Autowired
    private ToolCateServiceFacade toolCateServiceFacade;*/
    /**
     * 代理公司列表---后台
     */
    public GetCloudCompanyResponse getAgentList(GetCloudCompanyRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayCloudCompanyExample example = new PayCloudCompanyExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("created DESC");

        PayCloudCompanyExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getMerchNo()))
        {
            c.andMerchNoEqualTo(request.getMerchNo() );
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

        List<PayCloudCompanyCustom> list = payCloudCompanyCustomMapper.selectCompanyAgent(example);
        List<CloudCompanyInfo> infoList = new ArrayList<>();
        for (PayCloudCompanyCustom one : list)
        {
            CloudCompanyInfo info = new CloudCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyCustom.class, CloudCompanyInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetCloudCompanyResponse response = new GetCloudCompanyResponse();
        response.setList(infoList);
        int count = payCloudCompanyCustomMapper.countByExampleAgent(example);
        response.setCount(count);
        return response;
    }

    /**
     * 业务公司列表---后台
     */
    public GetCloudCompanyResponse getSaleList(GetCloudCompanyRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayCloudCompanyExample example = new PayCloudCompanyExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("created DESC");

        PayCloudCompanyExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getMerchNo()))
        {
            c.andMerchNoEqualTo(request.getMerchNo() );
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
        List<PayCloudCompanyCustom> list = payCloudCompanyCustomMapper.selectCompanySales(example);
        List<CloudCompanyInfo> infoList = new ArrayList<>();
        for (PayCloudCompanyCustom one : list)
        {
            CloudCompanyInfo info = new CloudCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyCustom.class, CloudCompanyInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetCloudCompanyResponse response = new GetCloudCompanyResponse();
        response.setList(infoList);
        int count = payCloudCompanyCustomMapper.countByExampleSales(example);
        response.setCount(count);
        return response;
    }
    /**
     * 公司添加
     */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto addCloudCompany(GetCloudCompanyRequest request,int account) throws Exception {

        if(StringUtils.isBlank(request.getName())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司名称不能为空");
        }
        if(StringUtils.isBlank(request.getPhonename())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人姓名不能为空");
        }
        if(StringUtils.isBlank(request.getPhone())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不能为空");
        }
        String pattern = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        boolean isphone = Pattern.matches(pattern, request.getPhone());

        if(isphone==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不正确");
        }
        if(StringUtils.isBlank(request.getLinkemail())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "邮箱不能为空");
        }
        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, request.getLinkemail());

        if(isemail==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "邮箱不正确");
        }
        if(StringUtils.isBlank(request.getBslicense())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须上传营业执照");
        }
        if(StringUtils.isBlank(request.getAptitude())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须上传企业资质");
        }

        if(request.getSalesRate().equals("")){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须填写服务费");
        }
        //查询当前添加的是否存在
        PayCloudCompanyExample example= new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria c = example.createCriteria();

        c.andLinkemailEqualTo(request.getLinkemail());

        List<PayCloudCompany> payCloudCompanyList = payCloudCompanyMapper.selectByExample(example);

        if(payCloudCompanyList != null && !payCloudCompanyList.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "邮箱已经存在");
        }
        //查询公司名称是否存在
        PayCloudCompanyExample examplename= new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria cname = examplename.createCriteria();
        cname.andNameEqualTo(request.getName());
        List<PayCloudCompany> payCloudCompanyName = payCloudCompanyMapper.selectByExample(examplename);

        if(payCloudCompanyName != null && !payCloudCompanyName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "公司名称已经存在");
        }
       /* PayCloudCompany payCloudCompany = payCloudCompanyList.get(0);
        String mefefe=payCloudCompany.getName();*/

        //生成商户编号
        String MerchnoNew= "CY"+ ToolUtils.createID();
        //查询商户号是否已经存在
        PayCloudCompanyExample exampleMerchno= new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria cMerno = exampleMerchno.createCriteria();
        cMerno.andMerchNoEqualTo(MerchnoNew);
        List<PayCloudCompany> payCloudCompanyListMerno = payCloudCompanyCustomMapper.selectByExample(exampleMerchno);
        if(payCloudCompanyListMerno != null && !payCloudCompanyListMerno.isEmpty()){
            //重新生成商户编号
             MerchnoNew= "CY"+ ToolUtils.createID();
        }
        //用户登录密码默认

        //String passlogin="abc123";
        //随机生成六位字母加数字
        String passlogin=ToolUtils.CreatSixNum();
        //生成默认密码
        String  companypass= SHA1.getInstance().getMySHA1Code(passlogin);

        //验证通过执行插表操作

        PayCloudCompany Company = new PayCloudCompany();
        Company.setName(request.getName());
        Company.setPhonename(request.getPhonename());
        Company.setPhone(request.getPhone());
        Company.setLinkemail(request.getLinkemail());
        Company.setAttestation(request.getAttestation());
        Company.setAptitude(request.getAptitude());
        Company.setBslicense(request.getBslicense());
        Company.setMerchNo(MerchnoNew);
        Company.setAddadminid(Integer.toString(account));
        Company.setTipstype(request.getTipstype());
        BigDecimal loanAmount = new BigDecimal("0.00");
        String accountReturn=loanAmount.toString();
        Company.setCloudmoney(loanAmount);
        Company.setCloudcode("");
        Company.setCloudpaypwd("");
        Date date = new Date();
        Company.setCreated(date);
        if(!request.getServiclinkuser().isEmpty()){
            Company.setServiclinkuser(request.getServiclinkuser());
        }
        if(!request.getLinkphone().isEmpty()){
            Company.setLinkphone(request.getLinkphone());
        }
        //插入基本信息表
        int res = payCloudCompanyCustomMapper.insertSelective(Company);
        String sprimatkey = Company.getId();
        PayCloudCompany Companyup = new PayCloudCompany();
        //
        PayCloudCompanyExample examplea= new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria ca = examplea.createCriteria();
        ca.andIdEqualTo(sprimatkey);
        Companyup.setCloudcode(Md5Encrypt.md5(res+accountReturn+"test","UTF-8"));
        Companyup.setId(sprimatkey);

        payCloudCompanyCustomMapper.updateByExampleSelective(Companyup,examplea);
         //更新当前人的金额校验值
        //条件判断插入代理还是业务公司

        if(request.getType().equals("1")){
            //插入代理公司表
            PayCloudCompanyAgent agent= new PayCloudCompanyAgent();
            agent.setAgentNo(MerchnoNew);
            agent.setAgentRate(request.getSalesRate());
            agent.setCreated(date);
            agent.setUpdated(date);
            int resAgent=payCloudCompanyAgentMapper.insertSelective(agent);
        }else{
            //插入业务公司表
            PayCloudCompanySales Sales= new PayCloudCompanySales();
            Sales.setSalesNo(MerchnoNew);
            Sales.setCreated(date);
            Sales.setUpdated(date);
            Sales.setSalesRate(request.getSalesRate());
            int resSales=payCloudCompanySalesMapper.insertSelective(Sales);
        }
            //插入公司登录表
            PayCloudEmployee Employee= new PayCloudEmployee();
            Employee.setMerchNo(MerchnoNew);
            Employee.setLinkphone(request.getPhone());
            Employee.setLinkemail(request.getLinkemail());
            Employee.setStatus(request.getStatus());
            Employee.setCreated(date);
            Employee.setRoles("");
            Employee.setRegip("");
            Employee.setRegdate(1);
            Employee.setCloudloginpwd(companypass);
            int resEmploee=payCloudEmployeeMapper.insertSelective(Employee);
           //所有都插入成功执行发送短信或者发送邮件的代码
           if(request.getTipstype() == (byte)1){
               //发送邮件
               String email=request.getLinkemail();
               String html="<div style='width: 600px;margin: 0 auto'><h3 style='color:#003E64; text-align:center; '>欣享科技结算系统帐号开通</h3><p style=''>尊敬的用户您好：</p><p style='text-indent: 2em'>感谢您使用欣享科技结算系统，您的帐号已经开通,帐号信息如下：</p><p style='text-indent: 2em'>用户名："+email+"</p><p style='text-indent: 2em'>登录密码："+passlogin+"</p><p style='text-align: right; color:#003E64; font-size: 20px;'>欣享科技团队</p></div>";
               Boolean eamilres=SendMailUtil.sendHtmlEmail(email,html );
               if(eamilres==false){
                   throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "添加失败");
               }


           }else if(request.getTipstype() == (byte)2){
               //发送短信
               String mobile = request.getPhone();
               String content ="尊敬的用户您的账号已经开通：账号为"+request.getLinkemail()+"密码为"+passlogin;
               String dateTime = date.toString();
               Map<String,Object> map = new HashMap<>();
               map.put("mobile",mobile);
               map.put("content",content);
               map.put("dateTime",dateTime);

               //排序转换
               Map<String,Object> treeMap = new TreeMap<>();
               treeMap.putAll(map);

               String respos = ToolUtils.mapToUrl(treeMap);

               //调用配置文件ConfigUtil.getValue("API_SECRET")

               String selfSign = Md5Encrypt.md5(respos+ ConfigUtil.getValue("API_SECRET")).toUpperCase();

               map.put("sign",selfSign);

               String response = OkHttpUtils.postForm(ConfigUtil.getValue("MESSAGE_URL")+"sendSmsApi",map);

               //json---转换代码---
               Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
               String result=responseMap.get("code");
               //返回值解密进行json转换
               if(!result.equals("10000")){//返回值为0，代表成功5

                   throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "添加失败");

               }
           }


            return  new JpfResponseDto();
    }
    /**
     * 云账户金额校验
     */
    public Boolean checkCompanyMoneyVerify(String id){

        PayCloudCompany payCloudCompany= payCloudCompanyMapper.selectByPrimaryKey(id);
        CloudCompanyInfo cloudCompanyInfo = new CloudCompanyInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudCompany.class,CloudCompanyInfo.class,false);
        beanCopier.copy(payCloudCompany,cloudCompanyInfo,null);

        //金额校验
        String cloudMoney = cloudCompanyInfo.getCloudmoney().toString();
        String cloudCode = cloudCompanyInfo.getCloudcode();

        String newCloudCode = Md5Encrypt.md5(id+cloudMoney+"test","UTF-8");   //加密规则：  id+金额+key
        Boolean res = cloudCode.equals(newCloudCode); //校验加密是否一致

        return  res;
    }

    /**
     * 云账户公司修改
     */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto editCloudCompany(GetCloudCompanyRequest request,int account) throws Exception {

        if(StringUtils.isBlank(request.getName())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司名称不能为空");
        }
        if(StringUtils.isBlank(request.getPhonename())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系人姓名不能为空");
        }
        if(StringUtils.isBlank(request.getPhone())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不能为空");
        }
        String pattern = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        boolean isphone = Pattern.matches(pattern, request.getPhone());

        if(isphone==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "联系电话不正确");
        }
       /* if(StringUtils.isBlank(request.getLinkemail())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "邮箱不能为空");
        }*/
      /*  String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, request.getLinkemail());*/

       /* if(isemail==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "邮箱不正确");
        }*/
        if(StringUtils.isBlank(request.getBslicense())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须上传营业执照");
        }
        if(StringUtils.isBlank(request.getAptitude())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须上传企业资质");
        }

        if(request.getSalesRate().equals("")){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "必须填写服务费");
        }
       //查询当前添加的是否存在
        PayCloudCompanyExample examplename= new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria cname = examplename.createCriteria();
        cname.andNameEqualTo(request.getName());
        cname.andIdNotEqualTo(request.getId());
        List<PayCloudCompany> payCloudCompanyName = payCloudCompanyMapper.selectByExample(examplename);

        if(payCloudCompanyName != null && !payCloudCompanyName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "公司名称重复");
        }
        //获取商户编号
        String MerchNo=request.getMerchNo();
        //验证通过执行插表操作

        PayCloudCompany Company = new PayCloudCompany();
        Company.setName(request.getName());
        Company.setPhonename(request.getPhonename());
        Company.setPhone(request.getPhone());
//        Company.setLinkemail(request.getLinkemail());
        Company.setAttestation(request.getAttestation());
        Company.setAptitude(request.getAptitude());
        Company.setBslicense(request.getBslicense());
        Company.setEditadminid(Integer.toString(account));
        Company.setTipstype(request.getTipstype());
        Date date = new Date();
        Company.setUpdated(date);
        if(!request.getServiclinkuser().isEmpty()){
            Company.setServiclinkuser(request.getServiclinkuser());
        }
        if(!request.getLinkphone().isEmpty()){
            Company.setLinkphone(request.getLinkphone());
        }
        //修改基本信息表
        String sprimatkey = request.getId();
        PayCloudCompanyExample exampleup= new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria ca = exampleup.createCriteria();
        ca.andIdEqualTo(sprimatkey);
        payCloudCompanyCustomMapper.updateByExampleSelective(Company,exampleup);
        //条件判断修改代理还是业务公司

        if(request.getType().equals("1")){
            //修改代理公司表
            PayCloudCompanyAgent agent= new PayCloudCompanyAgent();
            agent.setAgentRate(request.getSalesRate());
            agent.setUpdated(date);
            PayCloudCompanyAgentExample ExampleGent=new PayCloudCompanyAgentExample();
            PayCloudCompanyAgentExample.Criteria cagent = ExampleGent.createCriteria();
            cagent.andAgentNoEqualTo(MerchNo);
            payCloudCompanyAgentMapper.updateByExampleSelective(agent,ExampleGent);

        }else{
            //修改业务公司表
            PayCloudCompanySales SalesUp = new PayCloudCompanySales();
            SalesUp.setUpdated(date);
            SalesUp.setSalesRate(request.getSalesRate());
            PayCloudCompanySalesExample ExampleSales = new PayCloudCompanySalesExample();
            PayCloudCompanySalesExample.Criteria caSales= ExampleSales.createCriteria();
            caSales.andSalesNoEqualTo(MerchNo);
            payCloudCompanySalesMapper.updateByExampleSelective(SalesUp,ExampleSales);

        }
        //修改公司登录表
        PayCloudEmployee Employee= new PayCloudEmployee();
        Employee.setLinkphone(request.getPhone());
        Employee.setStatus(request.getStatus());
        Employee.setRegdate(1);
        PayCloudEmployeeExample ExamplePloyee = new PayCloudEmployeeExample();
        PayCloudEmployeeExample.Criteria caEmploee = ExamplePloyee.createCriteria();
        caEmploee.andMerchNoEqualTo(MerchNo);
        payCloudEmployeeMapper.updateByExampleSelective(Employee,ExamplePloyee);
        return  new JpfResponseDto();
    }

    @Override
    public CloudCompanyInfo getCompanyOne(String id,int type)
    {
        if ( StringUtils.isBlank(id))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayCloudCompanyCustom payCloudCompanyCustom ;
        if(type==1){
            payCloudCompanyCustom= payCloudCompanyCustomMapper.selectCompanyOneAgent(id);
        }else{
            payCloudCompanyCustom = payCloudCompanyCustomMapper.selectCompanyOneSales(id);
        }
        CloudCompanyInfo cloudCompanyInfo = new CloudCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyCustom.class, CloudCompanyInfo.class, false);
        beanCopier.copy(payCloudCompanyCustom,cloudCompanyInfo,null);
        return cloudCompanyInfo;



    }

    /**
     * 锁定公司 更改状态 1已删除 0正常
     */
    @Override
    public JpfResponseDto delCompany(String merchNo,int type)
    {
        if ( StringUtils.isBlank(merchNo) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "商户编号不能为空");
        }
        //查询当前添加的是否存在
        PayCloudEmployeeExample example= new PayCloudEmployeeExample();
        PayCloudEmployeeExample.Criteria c = example.createCriteria();

        c.andMerchNoEqualTo(merchNo);

        List<PayCloudEmployee> payCloudEmployeeList = payCloudEmployeeMapper.selectByExample(example);
        if(payCloudEmployeeList.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "公司不存在");
        }
        PayCloudEmployee payCloudEmployee = new PayCloudEmployee();
        //System.out.print(type);
        Byte status=-1;
        if(type==2){
             status = -1;
        }else if (type==1){
             status = 1;
        }
        payCloudEmployee.setStatus(status);
        payCloudEmployeeMapper.updateByExampleSelective(payCloudEmployee,example);
        return new JpfResponseDto();
    }


    @Override
    public GetCloudCompanysResponse getAllCompanys(GetCloudCompanysRequest request){
        GetCloudCompanysResponse response = new GetCloudCompanysResponse();

        PayCloudCompanyExample e = new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria c = e.createCriteria();

        // 聚合商户号
        if ( StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        // 商户id
        if ( StringUtils.isNotBlank(request.getmId()) ){
            c.andIdEqualTo(request.getmId());
        }
        // 商户名称
        if ( StringUtils.isNotBlank(request.getName()) ){
            c.andNameLike("%"+request.getName()+"%");
        }

        response.setCount(payCloudCompanyMapper.countByExample(e));
        List<PayCloudCompany> list = payCloudCompanyMapper.selectByExample(e);

        List<CloudCompanyInfo> infos = new ArrayList<>();
        for(PayCloudCompany payCloudCompany:list){
            CloudCompanyInfo cloudCompanyInfo = new CloudCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompany.class,CloudCompanyInfo.class,false);
            beanCopier.copy(payCloudCompany,cloudCompanyInfo,null);
            infos.add(cloudCompanyInfo);
        }
        response.setList(infos);

        return response;
    }

    /**
     * cloudCompanyInfo中type变量代表商户类型：0=业务商户 1=代理商户
     */
    @Override
    public CloudCompanyInfo getRecById(String id){
        PayCloudCompanyCustom payCloudCompanyCustom = payCloudCompanyCustomMapper.selectCompanyOne(id);
        CloudCompanyInfo cloudCompanyInfo = new CloudCompanyInfo();
        if ( StringUtils.isNotBlank(payCloudCompanyCustom.getAgentNo()) ){
            cloudCompanyInfo.setType((byte)1);
        }else{
            cloudCompanyInfo.setType((byte)0);
        }

        BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyCustom.class,CloudCompanyInfo.class,false);
        beanCopier.copy(payCloudCompanyCustom,cloudCompanyInfo,null);

        return cloudCompanyInfo;
    }
}
