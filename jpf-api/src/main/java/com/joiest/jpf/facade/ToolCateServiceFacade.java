package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ToolCateResponse;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ToolCateServiceFacade {

    //base64转图片
    public Map<String,String> baseToImage(HttpServletRequest request, String imgStr, String perfix);

    //流信息获取
    public ToolCateResponse convert(HttpResponse response);

    /**
     * 短信发送
     * */
    public int SendSms(String url,String strUserId, String strPwd, String strMobiles, String strMessage);

    /**
     *生成指定范围内的随机数
     * */
    public int getRandomInt(int min, int max);

    /**
    *阿里云身份证、姓名实名认证
    * */
    public JSONObject idenAuth(String name, String idCard);

    /**
     *阿里云OCR 身份证识别
     * */
    public String idCardOcr(String side,String img);
}
