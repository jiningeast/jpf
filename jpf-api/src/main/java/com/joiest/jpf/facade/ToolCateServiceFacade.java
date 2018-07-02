package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ToolCateResponse;
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
    public int SendSms(StringBuffer strPtMsgId, String strMobiles, String strMessage, String strSubPort, String strUserMsgId);
}
