package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.facade.BankServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/ofpay")
public class OfPayController {

    @Autowired
    private BankServiceFacade bankServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index(ModelMap modelMap){
        Date date = new Date();
        String starttime = new SimpleDateFormat("yyyyMMdd").format(date);
        Date tomtomorrowDay = new Date(date.getTime() + 86400000L);
        String endtime = new SimpleDateFormat("yyyyMMdd").format(tomtomorrowDay);

        modelMap.addAttribute("starttime",starttime);
        modelMap.addAttribute("endtime",endtime);
        return new ModelAndView("ofpay/list",modelMap);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list( long page, long rows,String isSearch,HttpServletRequest request  ){

        String starttime = request.getParameter("startAddTime");
        String endtime = request.getParameter("endAddTime");
        String paymenttype = request.getParameter("paymenttype");
        String pagenum = String.valueOf(page);
        String pagesize = String.valueOf(rows);

        Date date = new Date();

        if( StringUtils.isBlank(paymenttype) ){
            paymenttype = null;
        }

        if(StringUtils.isBlank(starttime)){
            starttime = new SimpleDateFormat("yyyyMMdd").format(date);
        }else{
            //starttime = new SimpleDateFormat("yyyyMMdd").format(starttime);
        }
        if(StringUtils.isBlank(endtime)){
            Date tomtomorrowDay = new Date(date.getTime() + 86400000L);
            endtime = new SimpleDateFormat("yyyyMMdd").format(tomtomorrowDay);
        }else{
           //endtime = new SimpleDateFormat("yyyyMMdd").format(endtime);
        }
        Map<String, Object> map = new HashMap<>();

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("starttime",starttime);// 开始时间
        queryMap.put("endtime",endtime); //结束时间
        queryMap.put("paymenttype",paymenttype); //结束时间
        queryMap.put("pagenum",pagenum); // 当前页码
        queryMap.put("pagesize",pagesize); // 每页条数

        String requestParam = ToolUtils.mapToUrl(queryMap); // 请求参数
        String requestUrl = ConfigUtil.getValue("ouFei_caiwuDetailUrl")+"/flowQuery/financeQuery";
        String response = OkHttpUtils.postForm(requestUrl,queryMap);

        //Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});

        JSONObject responJson = JSONObject.fromObject(response);
        JSONObject result = JSONObject.fromObject(responJson.get("responseParam"));
        JSONObject financeInfo = JSONObject.fromObject(result.get("financeInfo"));
        if( financeInfo.get("retcode").toString().equals("1") ){

            JSONObject financesData = JSONObject.fromObject(financeInfo.get("finances"));
            if( financesData != null ){
                //Integer pageSize = Integer.valueOf(financesData.get("pageSize").toString());
                Integer totalCount = Integer.valueOf(financesData.get("totalCount").toString().replace(",",""));
                Integer pageNum = Integer.valueOf(financesData.get("pageNum").toString());
                JSONArray finance = JSONArray.fromObject(financesData.get("finance"));

                map.put("total", totalCount);
                map.put("rows", finance);

//                for (int pageSize = 1; pageSize <= Math.ceil(totalCount/pageNum) ; pageSize++) {
//
//                }

            }

        }else{
            map.put("total", 0);
            map.put("rows", "");
        }


        // resultXml


        return map;
    }


}
