package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudIdcard;
import com.joiest.jpf.common.po.PayCloudIdcardExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudIdcardCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudIdcardMapper;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.facade.CloudIdcardServiceFacade;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;
import java.util.List;

public class CloudIdcardServiceFacadeImpl implements CloudIdcardServiceFacade {

    @Autowired
    private PayCloudIdcardMapper payCloudIdcardMapper;

    @Autowired
    private PayCloudIdcardCustomMapper payCloudIdcardCustomMapper;

    /*
     * 新增身份证信息
     * */
    @Override
    public int addCloudIdcard(JSONObject faceResult, JSONObject backResult,String type){

        PayCloudIdcard cloudId = new PayCloudIdcard();

        //正面信息
        cloudId.setName(faceResult.get("name").toString());
        cloudId.setNum(faceResult.get("num").toString());
        cloudId.setNationality(faceResult.get("nationality").toString());
        cloudId.setAddress(faceResult.get("address").toString());
        cloudId.setBirth(faceResult.get("birth").toString());
        cloudId.setSex(faceResult.get("sex").toString());
        cloudId.setFaceimglocal(faceResult.get("resourceUrl").toString());
        //cloudId.setFaceimgserver(faceResult.get("faceImgServer").toString());阿里云图片服务器地址
        cloudId.setFrequestId(faceResult.get("frequest_id").toString());


        //反面信息
        cloudId.setStartDate(backResult.get("start_date").toString());
        cloudId.setEndDate(backResult.get("end_date").toString());
        cloudId.setIssue(backResult.get("issue").toString());
        cloudId.setBrequestId(backResult.get("brequest_id").toString());
        cloudId.setBackimglocal(backResult.get("resourceUrl").toString());

        cloudId.setType(type);
        //cloudId.setBackimgserver(backResult.get("resourceUrl").toString());

        cloudId.setAddtime(new Date());

        int res = payCloudIdcardCustomMapper.insertSelective(cloudId);
        return new Integer(cloudId.getId());
    }
    /*
     * 查询身份证信息通过身份证号
     * */
    @Override
    public CloudIdcardInfo getCloudIdcardByCardNo(String cardNo){

        PayCloudIdcardExample example = new PayCloudIdcardExample();

        PayCloudIdcardExample.Criteria c = example.createCriteria();
        c.andNumEqualTo(cardNo);

        List<PayCloudIdcard> getIdcardInfo = payCloudIdcardCustomMapper.selectByExample(example);
        if(getIdcardInfo == null || getIdcardInfo.isEmpty()){

            return null;
        }
        PayCloudIdcard payCloudIdcard = getIdcardInfo.get(0);

        CloudIdcardInfo cloudIdcardInfo = new CloudIdcardInfo();
        BeanCopier beanCopier = BeanCopier.create( PayCloudIdcard.class, CloudIdcardInfo.class, false);
        beanCopier.copy(payCloudIdcard, cloudIdcardInfo, null);


        return cloudIdcardInfo;
    }

    /*
     * 查询身份证信息通过主键
     * */
    @Override
    public CloudIdcardInfo getCloudIdcardById(String id){

        PayCloudIdcard payCloudIdcard = payCloudIdcardCustomMapper.selectByPrimaryKey(id);;

        if(payCloudIdcard == null){

            return  null;
        }

        CloudIdcardInfo cloudIdcardInfo = new CloudIdcardInfo();
        BeanCopier beanCopier = BeanCopier.create( PayCloudIdcard.class, CloudIdcardInfo.class, false);
        beanCopier.copy(payCloudIdcard, cloudIdcardInfo, null);

        return cloudIdcardInfo;
    }
}
