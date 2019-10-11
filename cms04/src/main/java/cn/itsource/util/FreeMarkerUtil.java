package cn.itsource.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {
	
	
	/**
	 * @param templatePath 	模板父目录
	 * @param templateName	模板名称
	 * @param obj			模板中使用的数据
	 * @param suffix		生成文件的后缀
	 * @return
	 */
	public static String createHtm(String templatePath,
			String templateName,Object obj,String suffix){
		//创建一个Configuration对象 ->创建模板对象
		Configuration config = new Configuration(Configuration.VERSION_2_3_28);
		PrintWriter out = null;
		String htmlurl = null;
		File file = null;
		
		try {
			file = new File(templatePath);
			//设置模板加载目录
			config.setDirectoryForTemplateLoading(file);
			//设置模板默认的编码
			config.setDefaultEncoding("UTF-8");
			//创建模板对象
			Template template = config.getTemplate(templateName);
			//使用uuid生成一个随机的名称,并拼接后缀
			htmlurl = UUID.randomUUID().toString().replaceAll("-", "");
			htmlurl += suffix;
			//获取流并指定输出文件
			out = new PrintWriter(new File(file,htmlurl));
			//生成文件
			template.process(obj, out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null)
				out.close();
		}
		return htmlurl;
	}
}
