package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.DfUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.ClassUtil;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dto.CloudDfRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/clouddf")
public class CloudDfController {

    @RequestMapping(value = "/dfApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject dfApi(CloudDfRequest request)
    {
        ValidatorUtils.validateInterface(request);

        Map<String,Object> requestMap = ClassUtil.requestToMap(request);
        Map<String,Object> requestMapNew = new HashMap<String,Object>();
        requestMapNew.putAll(requestMap);
        String requestSign = requestMap.get("sign").toString();
        requestMap.remove("sign");
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);

        String requestUrl = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(requestUrl + ConfigUtil.getValue("DF_KEY")).toUpperCase();
        if ( !selfSign.equals(requestMapNew.get("sign").toString()) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_SIGN_ERROR.getCode(),JpfInterfaceErrorInfo.DF_SIGN_ERROR.getDesc());
        }
        JSONObject result = new DfUtils().applyAgentPay(requestMap);
        return result;
    }


}
