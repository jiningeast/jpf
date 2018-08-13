package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayWeixinMp;
import com.joiest.jpf.common.po.PayWeixinMpExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayWeixinMpCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayWeixinMpMapper;
import com.joiest.jpf.dto.GetWeixinMpRequest;
import com.joiest.jpf.dto.GetWeixinMpResponse;
import com.joiest.jpf.entity.WeixinMpInfo;
import com.joiest.jpf.facade.WeixinMpServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeixinMpServiceFacadeImpl implements WeixinMpServiceFacade {

    @Autowired
    private PayWeixinMpMapper payWeixinMpMapper;

    @Autowired
    private PayWeixinMpCustomMapper payWeixinMpCustomMapper;
    /**
     * 固定地址
     * */
    public static final String HTTPS_URL = "https://api.weixin.qq.com/";

    public String curTime = null;
    public Date dateTime = null;
    public WeixinMpServiceFacadeImpl(){

        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curTime = myfmt.format(date);
        dateTime = date;
    }

    /*
     * 公众号信息
     * */
    public WeixinMpInfo getWeixinMpByEncrypt(String encrypt){

        PayWeixinMpExample example = new PayWeixinMpExample();
        PayWeixinMpExample.Criteria c = example.createCriteria();
        c.andEncryptEqualTo(encrypt);

        List<PayWeixinMp> getPayWeixinMap = payWeixinMpMapper.selectByExample(example);

        if(getPayWeixinMap.isEmpty()) return null;

        PayWeixinMp payWeixinMp = getPayWeixinMap.get(0);

        WeixinMpInfo weixinMapInfo = new WeixinMpInfo();

        BeanCopier beanCopier = BeanCopier.create(PayWeixinMp.class,WeixinMpInfo.class,false);

        beanCopier.copy(payWeixinMp,weixinMapInfo,null);

        return weixinMapInfo;
    }
    /*
     * 更新公众号信息
     * */
    public int upWeixinMpByEncrypt(String encrypt, Map<String,String> res){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PayWeixinMpExample example = new PayWeixinMpExample();
        PayWeixinMpExample.Criteria c =  example.createCriteria();
        c.andEncryptEqualTo(encrypt);


        PayWeixinMp payWeixinMp = new PayWeixinMp();
        payWeixinMp.setAccesstoken(res.get("accessToken"));
        try {

            payWeixinMp.setTokenexpires(sdf.parse(res.get("tokenExpires")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return payWeixinMpMapper.updateByExampleSelective(payWeixinMp,example);
    }
    /**
     * 获取access_token
     * @param weixinMapInfo 公众号信息
     * */
    public String getAccessToken(WeixinMpInfo weixinMapInfo){

        String token = null;
        JSONObject res = new JSONObject();
        //组装数据库数据
        Map<String,String> weixinMp = new HashMap<>();
        if (weixinMapInfo.getTokenexpires() != null && dateTime.before(weixinMapInfo.getTokenexpires())){
            //表示str1date小于str2date

            token  = weixinMapInfo.getAccesstoken();
        }else{
            //调取接口获取access_token

            Map<String, Object> map = new HashMap<>();
            String url = HTTPS_URL+"cgi-bin/token?grant_type=client_credential&appid="+weixinMapInfo.getAppid()+"&secret="+weixinMapInfo.getAppsecret();
            res = JSONObject.fromObject(OkHttpUtils.postForm(url,map));

            token = res.get("access_token").toString();

            Date date = new Date();
            SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //计算失效时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, new Integer(res.get("expires_in").toString()));
            Date expiresTime = calendar.getTime();

            weixinMp.put("accessToken",token);
            weixinMp.put("tokenExpires",myfmt.format(expiresTime));

            StringBuilder sbf = new StringBuilder();
            sbf.append("\n\nTime:" + curTime);
            sbf.append("\n请求类型：微信公众号获取access_token");
            sbf.append("\n请求地址："+url);
            sbf.append("\n公众号参数："+JSONObject.fromObject(weixinMapInfo)).toString();
            sbf.append("\n获取access_token接口参数："+res);
            String fileName = "WeixinAccessTokenLog";
            LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);


            //更新对应公众号信息
            upWeixinMpByEncrypt(weixinMapInfo.getEncrypt(),weixinMp);
        }
        return token;
    }


    /**
     * 获取列表
     * */
    @Override
    public GetWeixinMpResponse getList(GetWeixinMpRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayWeixinMpExample example = new PayWeixinMpExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("created DESC");

        PayWeixinMpExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getName()))
        {
            c.andNameLike("%"+request.getName()+"%" );
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andCreatedGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andCreatedLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        List<PayWeixinMp> list = payWeixinMpMapper.selectByExample(example);
        List<WeixinMpInfo> infoList = new ArrayList<>();
        for (PayWeixinMp one : list)
        {
            WeixinMpInfo info = new WeixinMpInfo();
            BeanCopier beanCopier = BeanCopier.create(PayWeixinMp.class, WeixinMpInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetWeixinMpResponse response = new GetWeixinMpResponse();
        response.setList(infoList);
        int count = payWeixinMpMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    /**
     * 添加公众号
     * */
    @Override
    public JpfResponseDto addsub(GetWeixinMpRequest request){
      //查询公众号名称是否存在
        PayWeixinMpExample example=new PayWeixinMpExample();
        PayWeixinMpExample.Criteria c=example.createCriteria();
        c.andNameEqualTo(request.getName());
        List<PayWeixinMp> listName=payWeixinMpMapper.selectByExample(example);
        if(listName != null && !listName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "公众号名称已经存在");
        }
        //验证appid
        PayWeixinMpExample exampleappid=new PayWeixinMpExample();
        PayWeixinMpExample.Criteria cappid=exampleappid.createCriteria();
        cappid.andAppidEqualTo(request.getAppid());
        List<PayWeixinMp> listAppid=payWeixinMpMapper.selectByExample(exampleappid);
        if(listAppid != null && !listAppid.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "开发者id已经存在");
        }
        //验证令牌token
        PayWeixinMpExample exampletoken=new PayWeixinMpExample();
        PayWeixinMpExample.Criteria ctoken=exampletoken.createCriteria();
        ctoken.andTokenEqualTo(request.getToken());
        List<PayWeixinMp> listToken=payWeixinMpMapper.selectByExample(exampletoken);
        if(listToken != null && !listToken.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "令牌token已经存在");
        }
       //执行插表操作
        Date date = new Date();
        PayWeixinMp weixin=new PayWeixinMp();
        weixin.setName(request.getName());
        weixin.setAppid(request.getAppid());
        weixin.setAppsecret(request.getAppsecret());
        weixin.setToken(request.getToken());
        weixin.setFollowqr(request.getFollowqr());
        weixin.setFollowreply(request.getFollowreply());
        weixin.setMerchant(request.getMerchant());
        weixin.setMerkey(request.getMerkey());
        weixin.setCreated(date);
        //获取按插入的id
        int insertMp=payWeixinMpCustomMapper.insertSelective(weixin);
        Long  sprimatkey = weixin.getId();
        String NewId= Md5Encrypt.md5(sprimatkey+request.getToken(),"UTF-8").toUpperCase();
        //修改表中的值
        PayWeixinMpExample exampleUpdate= new PayWeixinMpExample();
        PayWeixinMpExample.Criteria ca = exampleUpdate.createCriteria();
        ca.andIdEqualTo(sprimatkey);
        PayWeixinMp updateEncrt=new PayWeixinMp();
        updateEncrt.setEncrypt(NewId);
        int ress=payWeixinMpCustomMapper.updateByExampleSelective(updateEncrt,exampleUpdate);
        if(insertMp!=1 && ress!=1){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "添加失败");
        }
        return new JpfResponseDto();
    }

    /**
     * 获取单条信息
     * */
      @Override
      public WeixinMpInfo getOne(String id){

          if ( org.apache.commons.lang3.StringUtils.isBlank(id))
          {
              throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
          }

          PayWeixinMp weixinMp=payWeixinMpMapper.selectByPrimaryKey(Long.parseLong(id));
          WeixinMpInfo weixinMpInfo =new WeixinMpInfo();
          BeanCopier  beanCopier=BeanCopier.create(PayWeixinMp.class,WeixinMpInfo.class,false);
          beanCopier.copy(weixinMp,weixinMpInfo,null);

          return weixinMpInfo;
      }

    /**
     * 修改公众号
     * */
    @Override
    public JpfResponseDto edit(GetWeixinMpRequest request){

        //查询公众号名称是否存在
        PayWeixinMpExample example=new PayWeixinMpExample();
        PayWeixinMpExample.Criteria c=example.createCriteria();
        c.andNameEqualTo(request.getName());
        c.andIdNotEqualTo(request.getId());

        List<PayWeixinMp> listName=payWeixinMpMapper.selectByExample(example);
        if(listName != null && !listName.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "公众号名称已经存在");
        }
        //验证appid
        PayWeixinMpExample exampleappid=new PayWeixinMpExample();
        PayWeixinMpExample.Criteria cappid=exampleappid.createCriteria();
        cappid.andAppidEqualTo(request.getAppid());
        cappid.andIdNotEqualTo(request.getId());
        List<PayWeixinMp> listAppid=payWeixinMpMapper.selectByExample(exampleappid);

        if(listAppid != null && !listAppid.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "开发者id已经存在");
        }
        //验证令牌token
        PayWeixinMpExample exampletoken=new PayWeixinMpExample();
        PayWeixinMpExample.Criteria ctoken=exampletoken.createCriteria();
        ctoken.andTokenEqualTo(request.getToken());
        ctoken.andIdNotEqualTo(request.getId());
        List<PayWeixinMp> listToken=payWeixinMpMapper.selectByExample(exampletoken);
        if(listToken != null && !listToken.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "令牌token已经存在");
        }

        //验证此条信息是否存在
        PayWeixinMpExample exampletid=new PayWeixinMpExample();
        PayWeixinMpExample.Criteria ctid=exampletid.createCriteria();
        ctid.andIdEqualTo(request.getId());
        List<PayWeixinMp> listid=payWeixinMpMapper.selectByExample(exampletid);
        if(listid == null && listToken.isEmpty()){

            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条信息不存在");
        }

        //执行插表操作
        Date date = new Date();
        PayWeixinMp weixin=new PayWeixinMp();
        weixin.setName(request.getName());
        weixin.setAppid(request.getAppid());
        weixin.setAppsecret(request.getAppsecret());
        weixin.setToken(request.getToken());
        weixin.setFollowqr(request.getFollowqr());
        weixin.setFollowreply(request.getFollowreply());
        weixin.setMerchant(request.getMerchant());
        weixin.setMerkey(request.getMerkey());
        weixin.setUpdated(date);
        //获取按插入的id
        //修改表中的值
        PayWeixinMpExample examplecomit= new PayWeixinMpExample();
        PayWeixinMpExample.Criteria cacomit = examplecomit.createCriteria();
        cacomit.andIdEqualTo(request.getId());
        int ress=payWeixinMpCustomMapper.updateByExampleSelective(weixin,examplecomit);

        if(ress!=1){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "修改失败");
        }
        return new JpfResponseDto();
    }
}
