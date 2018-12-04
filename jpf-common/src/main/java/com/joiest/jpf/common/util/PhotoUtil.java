package com.joiest.jpf.common.util;

import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/29.
 */
public class PhotoUtil {

	public static String saveFile2( MultipartFile filedata,String savePre,String newFileName) {

		// 根据配置文件获取服务器图片存放路径
		String saveFilePath = savePre;
		/* 构建文件目录 */
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {

			fileDir.mkdirs();
		}
		//上传的文件名
		String filename=filedata.getOriginalFilename();
		//文件的扩展名
		String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
		try {

			String imgPath = saveFilePath + newFileName + "." +extensionName;
			FileOutputStream out = new FileOutputStream(imgPath);
			// 写入文件
			out.write(filedata.getBytes());
			out.flush();
			out.close();

			//本地服务器地址
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 功能描述   保存图片
	 * @param filedata
	 *  文件数据
	 *　返回图片位置
	 */
	public static String saveFile2( MultipartFile filedata,String savePre,String newFileName) {

		String saveFilePath = savePre;
		/* 构建文件目录 */
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {

			fileDir.mkdirs();
		}
		//上传的文件名
		String filename=filedata.getOriginalFilename();
		//文件的扩展名
		String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
		try {

			String imgPath = saveFilePath + newFileName + "." +extensionName;
			FileOutputStream out = new FileOutputStream(imgPath);
			// 写入文件
			out.write(filedata.getBytes());
			out.flush();
			out.close();

			//本地服务器地址
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述   保存图片
	 * @param filedata
	 *  文件数据
	 *　返回图片位置
	 */
	public static String saveFile( MultipartFile filedata,String savePre) {

		// 根据配置文件获取服务器图片存放路径
		String newFileName = String.valueOf( System.currentTimeMillis());
		String saveFilePath = savePre;
		/* 构建文件目录 */
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {

			fileDir.mkdirs();
		}
		//上传的文件名
		String filename=filedata.getOriginalFilename();
		//文件的扩展名
		String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
		try {

			String imgPath = saveFilePath + newFileName + "." +extensionName;
			FileOutputStream out = new FileOutputStream(imgPath);
			// 写入文件
			out.write(filedata.getBytes());
			out.flush();
			out.close();

			 //本地服务器地址
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 功能描述：删除图片
	 * @param oldPic
	 */
    public static void deleteFile(String oldPic,HttpServletRequest request) {

        String pathval = request.getSession().getServletContext().getRealPath("/")+"/../../src/main/webapp/resources/";
		/* 构建文件目录 */
		File fileDir = new File(pathval+oldPic);
		if (fileDir.exists()) {
			//把修改之前的图片删除 以免太多没用的图片占据空间
			fileDir.delete();
		}

	}

}