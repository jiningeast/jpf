package com.joiest.jpf.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelDealUtils {
    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param in,fileName
     * @return
     * @throws IOException
     */
    public Map<Object,Object> getImportExcel(InputStream in, String fileName) throws Exception{

        Map<Object,Object> map = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        //创建Excel工作薄
        Workbook work = this.getWorkbook(in,fileName);
        if(work == null){

            throw new Exception("创建Excel工作薄为空！");
        }
        map = new HashMap<>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {

            sheet = work.getSheetAt(i);
            if(sheet==null){

                continue;
            }
            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum()+1; j++) {

                row = sheet.getRow(j);
                if(row==null){
                    continue;
                }
                //遍历所有的列
                Map<Object,Object> li = new HashMap<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {

                    cell = row.getCell(y);
                    li.put(y,this.getCellValue(cell));
                }
                map.put(j,li);
            }
        }
        return map;
    }
    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{

        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){

            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){

            wb = new XSSFWorkbook(inStr);  //2007+
        }else{

            throw new Exception("解析Excel文件格式有误！");
        }
        return wb;
    }
    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public Object getCellValue(Cell cell) {

        String value = "";
        if (null == cell) {
            return value;
        }
        switch (cell.getCellType()) {

            //数值型
            case Cell.CELL_TYPE_NUMERIC:

                if (HSSFDateUtil.isCellDateFormatted(cell)) {

                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                } else {// 纯数字

                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:

                //读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串

                    value = cell.getStringCellValue().toString();
                }
                value = cell.getCellFormula().toString();
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:

                value = " " + cell.getBooleanCellValue();
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                value = "";

                break;
            // 故障
            case Cell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;
    }

    /**
     * 导出excel
     * @response 响应头
     * @param titles excel表头信息
     * @param filds 表头所对应的字段同时对应数据中的字段名
     *@param data 数据
     *@param type 1 下载  2 生成文件
     * */
    public JSONObject exportExcel(HttpServletResponse response, String titles, String filds, List data, int type, String path){

        if(titles.isEmpty() || filds.isEmpty() || data.isEmpty()) return null;
        if(type<1) type = 1;
        //组装数据
        JSONArray excelData  = JSONArray.fromObject(data);
        //定义字段类型
        JSONArray fild =  JSONArray.fromObject(filds);
        //定义表头
        JSONArray title=JSONArray.fromObject(titles);

        if(fild.isEmpty() || title.isEmpty() || excelData.isEmpty()) return null;
        if(fild.size()!=title.size()) return null;

        //定义返回信息
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");

        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet=workbook.createSheet();
        //创建第一行
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell=null;
        //插入第一行数据的表头
        for(int i=0;i<title.size();i++){
            cell=row.createCell(i);
            cell.setCellValue(title.get(i).toString());
        }
        int j=1;
        //循环数据
        for(int i=0;i<excelData.size();i++){

            HSSFRow nrow=sheet.createRow(j);//创建行
            JSONObject job = excelData.getJSONObject(i);//获取数据
            //循环整体列
            for (int m=0;m<title.size();m++){

                if(!job.containsKey(fild.get(m))) return null;

                //创建具体的列
                HSSFCell ncell=nrow.createCell(m);
                ncell.setCellValue(job.get(fild.get(m)).toString());
            }
            j++;
        }
        OutputStream output= null;
        try {
            String fileName = System.currentTimeMillis()+".xls";
            if(type == 1){

                response.reset();
                output = response.getOutputStream();

                response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "iso8859-1"));
                response.setContentType("application/vnd.ms-excel");
                workbook.write(output);
                output.close();
            }else{

                File fileDir = new File(path);
                if (!fileDir.exists()) {

                    fileDir.mkdirs();
                }
                String actual = path+fileName;
                File file = new File(actual);
                file.createNewFile();
                //将excel写入
                FileOutputStream stream= FileUtils.openOutputStream(file);
                workbook.write(stream);
                stream.close();

                JSONObject fileInfo = new JSONObject();
                fileInfo.put("localUrl",actual);//服务器实际路径
                fileInfo.put("fileName",fileName);//文件名

                res.put("data",fileInfo);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return res;
    }


    /**
     * 导出excel 数据格式为info 可加数据类型
     * @response 响应头
     * @param titles excel表头信息
     * @param filds 表头所对应的字段同时对应数据中的字段名
     *@param data 数据
     *@param type 1 下载  2 生成文件
     * */
    public JSONObject exportExcelByInfo(HttpServletResponse response, String titles, String filds, List data, int type, String path) throws Exception{

        if(titles.isEmpty() || filds.isEmpty() || data.isEmpty()) return null;
        if(type<1) type = 1;
        //组装数据
        //JSONArray excelData  = JSONArray.fromObject(data);
        List dataExecl  = data;

        //定义字段类型
        JSONArray fild =  JSONArray.fromObject(filds);
        //定义表头
        JSONArray title=JSONArray.fromObject(titles);

        if(fild.isEmpty() || title.isEmpty() || dataExecl.isEmpty()) return null;
        if(fild.size()!=title.size()) return null;

        //定义返回信息
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");

        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet=workbook.createSheet();
        //创建第一行
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell=null;
        //插入第一行数据的表头
        for(int i=0;i<title.size();i++){
            cell=row.createCell(i);
            cell.setCellValue(title.get(i).toString());
        }
        int j=1;
        //循环数据
        for(int i=0;i<dataExecl.size();i++){

            Object info = dataExecl.get(i);
            // 通过实例得到类
            //@SuppressWarnings("rawtypes")
            Class infoClass = info.getClass(); //studentClass

            //String findStr = fieldName.getType().toString();//获取字段类型
            //String fildType = StringUtils.substring(findStr,findStr.lastIndexOf(".")+1);

            HSSFRow nrow=sheet.createRow(j);//创建行
            //循环整体列
            for (int m=0;m<title.size();m++){

                //创建具体的列
                HSSFCell ncell=nrow.createCell(m);

                Field fieldName = infoClass.getDeclaredField(fild.get(m).toString());
                fieldName.setAccessible(true);
                Object fieldVal = fieldName.get(info);

                if(fieldVal==null) continue;

                String timeVal = "";
                if(fieldName.get(info) instanceof Date){

                    timeVal =  DateUtils.dateToString((Date) fieldName.get(info));
                    if(StringUtils.isBlank(timeVal)){
                        continue;
                    }
                    ncell.setCellValue(timeVal);
                }else{

                    ncell.setCellValue(fieldVal.toString());
                }
            }
            j++;
        }
        OutputStream output= null;
        try {
            String fileName = System.currentTimeMillis()+".xls";
            if(type == 1){

                response.reset();
                output = response.getOutputStream();

                response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "iso8859-1"));
                response.setContentType("application/vnd.ms-excel");
                workbook.write(output);
                output.close();
            }else{

                File fileDir = new File(path);
                if (!fileDir.exists()) {

                    fileDir.mkdirs();
                }
                String actual = path+fileName;
                File file = new File(actual);
                file.createNewFile();
                //将excel写入
                FileOutputStream stream= FileUtils.openOutputStream(file);
                workbook.write(stream);
                stream.close();

                JSONObject fileInfo = new JSONObject();
                fileInfo.put("localUrl",actual);//服务器实际路径
                fileInfo.put("fileName",fileName);//文件名

                res.put("data",fileInfo);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return res;
    }

    //根据excel模板添加数据
    public JSONObject exportExcelCusByInfoCopy(HttpServletResponse response, String titles, String filds, List data, int type, String path,String docPath,int startRow) throws Exception{

        FileInputStream fs=new FileInputStream(docPath);  //获取d://test.xls
        POIFSFileSystem ps=new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
        HSSFWorkbook wb=new HSSFWorkbook(ps);
        HSSFSheet sheet=wb.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表
        HSSFRow row=sheet.getRow(0);  //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
        System.out.println(sheet.getLastRowNum()+" "+row.getLastCellNum());  //分别得到最后一行的行号，和一条记录的最后一个单元格
        FileOutputStream out=new FileOutputStream(docPath);  //向d://test.xls中写数据
        row=sheet.createRow((short)(sheet.getLastRowNum()+1)); //在现有行号后追加数据
        row.createCell(0).setCellValue("leilei"); //设置第一个（从0开始）单元格的数据
        row.createCell(1).setCellValue(24); //设置第二个（从0开始）单元格的数据
        response.reset();
        OutputStream output= null;
        output = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename="+new String("test".getBytes("gbk"), "iso8859-1"));
        response.setContentType("application/vnd.ms-excel");
        out.flush();
        wb.write(out);
        out.close();
        System.out.println(row.getPhysicalNumberOfCells()+" "+row.getLastCellNum());

        return null;
    }
}