package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.ExcelDealUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.OuFeiPayRequest;
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
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


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
        modelMap.addAttribute("merchNo",ConfigUtil.getValue("userid"));

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

        JSONObject financesData = this.OfBlanceDetail(queryMap);
        if( financesData != null && financesData.size() > 0  ){
                //Integer pageSize = Integer.valueOf(financesData.get("pageSize").toString());
                Integer totalCount = Integer.valueOf(financesData.get("totalCount").toString().replace(",",""));
                Integer pageNum = Integer.valueOf(financesData.get("pageNum").toString());
                JSONArray finance = JSONArray.fromObject(financesData.get("finance"));

                map.put("total", totalCount);
                map.put("rows", finance);
        }else{
            map.put("total", 0);
            map.put("rows", "");
        }

        return map;
    }

    @RequestMapping("/exprot")
    @ResponseBody
    public void exprot( long page, HttpServletRequest request ,HttpServletResponse response ){


        String starttime = request.getParameter("startAddTime");
        String endtime = request.getParameter("endAddTime");
        String paymenttype = request.getParameter("paymenttype");
        String pagenum = String.valueOf(page);
        String pagesize = "50";//默认每页条数

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

        String path = "";
        //如果有购买的卡密放到Excel
//        设置excel标题以及字段
        JSONArray titles = new JSONArray();
        titles.add("流水号");
        titles.add("收支类型");
        titles.add("业务类型");
        titles.add("收入金额");
        titles.add("支出金额");
        titles.add("账户余额");
        titles.add("业务单号");
        titles.add("交易时间");
        titles.add("公司名称");

        JSONArray filed = new JSONArray();
        filed.add("detailId");//卡号
        filed.add("inOutType");
        filed.add("fundsType");
        filed.add("inComeCost");
        filed.add("expenditureCost");
        filed.add("leftBalance");
        filed.add("billId");
        filed.add("occurTime");
        filed.add("company");

        // 初始化财务明细数据
        List<OuFeiPayRequest> list = new ArrayList<>();


        JSONObject financesData = this.OfBlanceDetail(queryMap);
        if( financesData != null  && financesData.size() > 0  ){
            Integer totalCount = Integer.valueOf(financesData.get("totalCount").toString().replace(",",""));
            JSONArray finance = JSONArray.fromObject(financesData.get("finance"));
            JSONObject data = new JSONObject();
            Integer num = 0;
            Integer pageNo = Integer.valueOf(pagesize);
            Double totaoPage = (Math.ceil(1.0*totalCount/pageNo));
            for (Integer pageNum = 1; pageNum <= totaoPage ; pageNum++) {
                queryMap.put("pagenum",pageNum.toString());
                financesData = this.OfBlanceDetail(queryMap);
                if( financesData != null && financesData.size() > 0  ){
                    //取出当前页的数据 放入list中
                    finance = JSONArray.fromObject(financesData.get("finance"));
                    if( finance !=null && finance.size() > 0 ){
                        for (int i = 0; i < finance.size() ; i++) {
                            data = JSONObject.fromObject(finance.get(i));
                            OuFeiPayRequest ouFeiPayRequest = new OuFeiPayRequest();
                            ouFeiPayRequest.setBillId(data.get("billId").toString());
                            ouFeiPayRequest.setCompany(data.get("company").toString());
                            ouFeiPayRequest.setDetailId(data.get("detailId").toString());
                            ouFeiPayRequest.setExpenditureCost(data.get("expenditureCost").toString());
                            ouFeiPayRequest.setFundsType(data.get("fundsType").toString());
                            ouFeiPayRequest.setInComeCost(data.get("inComeCost").toString());
                            ouFeiPayRequest.setInOutType(data.get("inOutType").toString());
                            ouFeiPayRequest.setOccurTime(data.get("occurTime").toString());
                            ouFeiPayRequest.setLeftBalance(data.get("leftBalance").toString());
                            list.add(num++,ouFeiPayRequest);
                        }
                    }else{
                        break;
                    }

                }else{
                    break;
                }
            }
        }
        if( list.size() <= 0){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据为空");
        }
        // 开始导出
        try {
            JSONObject excel = new ExcelDealUtils().exportExcelByInfo(response, titles.toString(), filed.toString(), list, 1,path);
        } catch (Exception e) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据导出异常");
        }
    }

    /**
     * 欧飞财务明细信息
     * requestMap 接口参数map
     */
    public JSONObject OfBlanceDetail(Map<String,Object> requestMap){


        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("starttime",requestMap.get("starttime"));// 开始时间
        queryMap.put("endtime",requestMap.get("endtime")); //结束时间
        queryMap.put("paymenttype",requestMap.get("paymenttype")); //结束时间
        queryMap.put("pagenum",requestMap.get("pagenum")); // 当前页码
        queryMap.put("pagesize",requestMap.get("pagesize")); // 每页条数

        String requestParam = ToolUtils.mapToUrl(queryMap); // 请求参数
        String requestUrl = ConfigUtil.getValue("ouFei_caiwuDetailUrl")+"/flowQuery/financeQuery";
        String response = OkHttpUtils.postForm(requestUrl,queryMap);

        JSONObject responJson = JSONObject.fromObject(response);
        JSONObject result = JSONObject.fromObject(responJson.get("responseParam"));
        JSONObject financeInfo = JSONObject.fromObject(result.get("financeInfo"));
        JSONObject financesData = new JSONObject();

        if( financeInfo.get("retcode").toString().equals("1") ){

            financesData = JSONObject.fromObject(financeInfo.get("finances"));

        }
        return financesData;

    }


}
