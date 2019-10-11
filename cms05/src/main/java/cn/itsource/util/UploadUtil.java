package cn.itsource.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
	
	public static String fileUpload(MultipartFile file,HttpServletRequest req,String realPath){
		
		// 1.先判断上传的文件是否为空
		if (!file.isEmpty()) {// 用户上传了文件
			// 3.处理文件名
			String filename = file.getOriginalFilename();
			String prefix = UUID.randomUUID().toString().replaceAll("-", "");
			String fname = prefix + "_" + filename;
		
		//4.获取输入输出流
		InputStream in = null;
		FileOutputStream out = null;
		try {
			in = file.getInputStream();
			out = new FileOutputStream(new File(realPath, fname));
			IOUtils.copy(in, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				System.out.println("上传成功!");
				out.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fname;
		}
		return null;
	}
}
