package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.CompressUtil;
import com.joiest.jpf.common.util.ExcelDealUtils;
import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.facade.DemoServiceFacade;
import com.joiest.jpf.facade.OrderServiceFacade;
import net.lingala.zip4j.exception.ZipException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("demo")

public class DemoController {

    @Autowired
    private DemoServiceFacade demoServiceFacade;

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    @RequestMapping(value = "/getValue")
    @ResponseBody
    public GetValueResponse getValue(@Valid GetValueRequest request){
        return demoServiceFacade.getValue(request);
    }

    /**
     * 导入excel DEMO
     * */
    @RequestMapping(value = "/exportEcel", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject exportEcel(HttpServletResponse response, OrderRequest orderRequest){

        //测试地址  http://localhost:8080/cloud-api/demo/exportEcel?orderid=2320607521780671
        JSONArray res = new JSONArray();
        res.add("订单号");
        res.add("商户编号");
        res.add("企业名称");
        res.add("添加时间");

        JSONArray filed = new JSONArray();
        filed.add("orderid");
        filed.add("mtsid");
        filed.add("companyname");
        filed.add("addtime");

        OrderResponse orderResponse = orderServiceFacade.getOrders(orderRequest);

        JSONObject excel = null;
        try {
            excel = new ExcelDealUtils().exportExcel(response,res.toString(),filed.toString(),orderResponse.getList(),2, ConfigUtil.getValue("EXCEL_PATH"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excel;
    }


    /**
     * excel上传 获取excel表中数据
     * 普通form提交
     * */
    @RequestMapping(value = "/uploadEcelByForm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadEcelByForm(HttpServletRequest request,@RequestParam(value="file",required = true) MultipartFile file) throws Exception {

        JSONObject jsonRes = new JSONObject();
        InputStream in = null;
        Map<Object,Object> rowoOb = new HashMap<>();

        if (file.isEmpty()) {

            jsonRes.put("code","10008");
            jsonRes.put("info","请选择文件");
        }
        in = file.getInputStream();
        rowoOb = new com.joiest.jpf.cloud.api.util.ExcelDealUtils().getImportExcel(in, file.getOriginalFilename());
        //循环行
        for (Map.Entry<Object, Object> hang : rowoOb.entrySet()) {

            //循环行中具体的列
            Map<Object,Object> cellOb = (Map<Object,Object>)hang.getValue();
            for (Map.Entry<Object, Object> lie : cellOb.entrySet()) {

                //此处执行具体的逻辑操作（如：入库）
                System.out.println(lie.getKey());
                System.out.println(lie.getValue());
            }
        }
        in.close();
        // 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        return "result";
    }
    /**
     * excel上传 获取excel表中数据
     * 普通文件提交
     * */
    @RequestMapping(value = "/uploadEcelByFile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadEcelByFile(HttpServletRequest request) throws Exception {

        File files=new File("C:\\Users\\admin\\Desktop\\test.xls");
        String aa = files.getName();
        FileInputStream file = new FileInputStream("C:\\Users\\admin\\Desktop\\test.xls");

        Map<Object,Object> rowoOb = new HashMap<>();

        rowoOb = new com.joiest.jpf.cloud.api.util.ExcelDealUtils().getImportExcel(file, files.getName());
        return null;
    }
    /**
     *zip 压缩文件
     * */
    @RequestMapping(value = "/compressDeal", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String compressDeal(){

        /**
         * 删除
         * */
        try {

            //Boolean boo = new CompressUtil().removeDirFromZipArchive("E:\\my.zip","wode\\test");//删除压缩文件=》目录
            Boolean boo = new CompressUtil().removeFileFromZipArchive("E:\\my.zip","wode\\test.xlsx");//删除压缩文件=》目录中的文件
            //Boolean boo = new CompressUtil().removeFileFromZipArchive("E:\\test.zip","test.xlsx");//删除压缩文件里的文件
            return null;
        } catch (ZipException e) {
            e.printStackTrace();
        }


        /**
         * 压缩
         * */
        //String filenanme = null;
        //filenanme = new CompressUtil().zip("E:\\测试.xlsx");//压缩文件到当前目录 无密码
        //filenanme = new CompressUtil().zip("E:\\wode");//压缩目录=》当前目录 无密码
        //filenanme =new CompressUtil().zip("E:\\test.xlsx","123456");//压缩文件到=》当前目录 指定密码
        //filenanme = new CompressUtil().zip("E:\\test.xlsx","E:\\home\\cc.zip",null);//压缩文件=》指定压缩目录 无密码
        //filenanme = new CompressUtil().zip("E:\\test.xlsx","E:\\home\\cc.zip","123456");//压缩文件=》指定压缩目录 指定密码
        //filenanme = new CompressUtil().zip("E:\\wode","E:\\my.zip","123456");//压缩目录=》指定压缩目录 指定密码
        //return filenanme;

        /**
         *解压
         * */
		/*try {

            File[] files = new CompressUtil().unzip("E:\\测试.zip",null); //解压文件到=》当前目录 无密码
            //File[] files = new CompressUtil().unzip("E:\\test.zip","E://home","null"); //解压文件到=》指定目录 无密码
            //File[] files = new CompressUtil().unzip("E:\\home\\my.zip","123456");//解压文件到=》当前目录 指定密码
            //File[] files = new CompressUtil().unzip("E:\\home\\my.zip","E:\\","123456");//解压文件到=》指定目录 指定密码
			for (int i = 0; i < files.length; i++) {
				System.out.println("解压文名："+files[i]);
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}*/
        return null;
    }

    /**
     *
     * */
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        String idno = "41071119870116153x";
        idno = idno.toUpperCase();

        return idno;
    }

    @RequestMapping("test2")
    @ResponseBody
    public String test2(){
        String str = "1";
        String lastNum = StringUtils.substring(String.valueOf(str),-1,String.valueOf(str).length());

        return lastNum;
    }
}
