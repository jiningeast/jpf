package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudIdcardInfo;
import net.sf.json.JSONObject;

public interface CloudIdcardServiceFacade {

    /*
     * 新增退单记录
     * */
    public int addCloudIdcard(JSONObject faceResult, JSONObject backResult);

    /*
     * 查询身份证信息通过身份证号
     * */
    public CloudIdcardInfo getCloudIdcardByCardNo(String cardNo);
    /*
     * 查询身份证信息通过身份证号
     * */
    public CloudIdcardInfo sendSms(String cardNo);

    /*
     * 查询身份证信息通过主键
     * */
    public CloudIdcardInfo getCloudIdcardById(String id);
}
