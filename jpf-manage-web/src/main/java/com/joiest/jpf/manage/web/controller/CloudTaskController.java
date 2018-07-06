package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.ExcelDealUtils;
import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.facade.CloudTaskServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cloudTask")
public class CloudTaskController {

    @Autowired
    private CloudTaskServiceFacade cloudTaskServiceFacade;

    /**
     * 批量打款页
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("cloudTask/index");
    }

    /**
     * 任务列表页
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudTaskRequest request){
        CloudTaskResponse response = cloudTaskServiceFacade.getTasks(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }

    /**
     * 新建任务列表页
     */
    @RequestMapping("/addTask")
    @ResponseBody
    public ModelAndView addTask(){
        return new ModelAndView("cloudTask/addTask");
    }

    /**
     * 新任务提交操作
     */
    @RequestMapping("/submitTask")
    @ResponseBody
    public String submitTask(String company_id, String company_name, @RequestParam("uploadfile") MultipartFile uploadfile) throws Exception{
        // 解析xml
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();
        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        String test = "wow";

        // 上传

        return "";
    }
}
