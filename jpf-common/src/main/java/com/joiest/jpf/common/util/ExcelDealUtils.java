package com.joiest.jpf.common.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

}