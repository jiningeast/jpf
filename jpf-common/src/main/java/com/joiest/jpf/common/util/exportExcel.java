package com.joiest.jpf.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.Region;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

public class exportExcel {


    /*
    * 根据excel模板导出数据
    * path 表示模板路径
    * filename 表示文件名字
    * filds 表示字段
    * data 表示数据
    * */

    public JSONObject exportExcelcopy(HttpServletResponse response,String path,String filename,List data,String filds,String headerKey){

        File newFile=new exportExcel().createNewFile(path);
        //File newFile = createNewFile();
//        File newFile = new File(path);
        //新文件写入数据，并下载*****************************************************
        InputStream is = null;
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        try {
            is = new FileInputStream(newFile);
            workbook = new HSSFWorkbook(is);
            //获取第一个sheet
            sheet = workbook.getSheetAt(0);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        List dataExecl  = data;
        if(dataExecl.isEmpty()){
            return  null;
        }

        if(sheet != null){

            try {
                //写数据
                FileOutputStream fos = new FileOutputStream(newFile);
                //合并单元格添加数据
               // sheet.addMergedRegion(new Region(0, (short) 0, 1, (short) 0));
                /*HSSFRow row=sheet.getRow(0);
                row=sheet.createRow((short)(sheet.getLastRowNum()+1)); //在现有行号后追加数据
                row.createCell(0).setCellValue("leilei"); //设置第一个（从0开始）单元格的数据
                row.createCell(1).setCellValue(24); //设置第二个（从0开始）单元格的数据*/

                //写数据

                //循环添加数据
                //定义字段类型
                JSONArray fild =  JSONArray.fromObject(filds);
                //循环数据
                for(int i=0;i<dataExecl.size();i++){

                    Object info = dataExecl.get(i);
                    // 通过实例得到类
                    Class infoClass = info.getClass(); //studentClass
                    //HSSFRow nrow=sheet.createRow(j);//创建行
                    HSSFRow nrow=sheet.createRow((short)(sheet.getLastRowNum()+1));
                    //循环整体列
                    for (int m=0;m<fild.size();m++){

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

                }
                //循环填入表头信息
                JSONArray headParam =  JSONArray.fromObject(headerKey);
                if(headParam.size()!=0 && headParam !=null){
                    for (int k=0;k<headParam.size();k++){
                        //表头填数据
                        JSONObject valueParam=headParam.getJSONObject(k) ;
                        HSSFRow row = sheet.getRow(Integer.parseInt(valueParam.get("row").toString()));
                        HSSFCell cell = row.getCell(Integer.parseInt(valueParam.get("col").toString()));
                        cell.setCellValue(valueParam.get("value").toString());
                    }
                }

                workbook.write(fos);
                fos.flush();
                fos.close();

                //下载
                InputStream fis = new BufferedInputStream(new FileInputStream(newFile));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                response.reset();
                response.setContentType("text/html;charset=UTF-8");
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/x-msdownload");
                String newName = URLEncoder.encode(filename+System.currentTimeMillis()+".xls", "UTF-8");
                response.addHeader("Content-Disposition", "attachment;filename=\""+ newName + "\"");
                response.addHeader("Content-Length", "" + newFile.length());
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (null != is) {
                        is.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //删除创建的新文件
        this.deleteFile(newFile);
        return null;
    }

    /**
     * 复制文件
     *
     * @param s
     * 源文件
     * @param t
     * 复制到的新文件
     */

    public void fileChannelCopy(File s, File t) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(s),1024);
                out = new BufferedOutputStream(new FileOutputStream(t),1024);
                byte[] buffer = new byte[1024];
                int len;
                while ((len=in.read(buffer))!=-1) {
                    out.write(buffer,0,len);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel模板，并复制到新文件中供写入和下载
     * @return
     */
    public File createNewFile(String path){
        //读取模板，并赋值到新文件************************************************************
        //文件模板路径
        File file=  new File(path);

        //保存文件的路径
        String realPath = ConfigUtil.getValue("CACHE_PATH");
        //新的文件名
        String newFileName = "报税"+System.currentTimeMillis() + ".xls";
        //判断路径是否存在
        File dir = new File(realPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //写入到新的excel
        File newFile = new File(realPath, newFileName);
        try {
            newFile.createNewFile();
            //复制模板到新文件
            fileChannelCopy(file, newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 下载成功后删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
