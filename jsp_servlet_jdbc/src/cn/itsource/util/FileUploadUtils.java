package cn.itsource.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUploadUtils {
	//file1 -- 文件表单的的name属性值
	public static String fileUpload(HttpServletRequest req , String file1) throws IOException, ServletException {
		//获取到文件上传的绝对路径webapps/upload
		String webappPath = req.getServletContext().getRealPath("/upload");
		File newFile = new File(webappPath);
		if(!newFile.exists()){
			newFile.mkdir();
		}
		
		//Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
		//Part part = parts[0];//从上传的文件集合中获取Part对象
		//通过表单file控件(<input type="file" name="co_log">)的名字直接获取Part对象
		Part part = req.getPart(file1);
		if(part.getSize()!=0) {
			//Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
			//获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
			String header = part.getHeader("content-disposition");
			//获取文件名
			String fileName = getFileName(header);
			//解决上传时文件名重复的问题
			fileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
			//把文件写到指定路径
			part.write(newFile.getAbsolutePath() + File.separator + fileName);
			return fileName;
		}
		return null;
	}
	
	
	/**
	 * 根据请求头解析出文件名
	 * 请求头的格式：
	 * 火狐和google浏览器下：	form-data; name="file"; filename="snmp4j--api.zip"
	 * IE浏览器下：			form-data; name="file"; filename="E:\snmp4j--api.zip"
	 * @param header 请求头
	 * @return 文件名
	 */
	public static String getFileName(String header) {
		String[] tempArr1 = header.split(";");
		/**
		 * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
		 * 火狐或者google浏览器下：	tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE浏览器下：			tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		/**
		 *火狐或者google浏览器下：	tempArr2={filename,"snmp4j--api.zip"}  
		 *IE浏览器下：				tempArr2={filename,"E:\snmp4j--api.zip"}           
		 */
		//获取文件名，兼容各种浏览器的写法
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
		return fileName;//snmp4j--api.zip
	}	
}
