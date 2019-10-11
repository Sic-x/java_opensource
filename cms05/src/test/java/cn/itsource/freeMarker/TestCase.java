package cn.itsource.freeMarker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.itsource.domain.City;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TestCase {
	
	Configuration config = null;
	File file = null;
	
	@Before
	public void before() throws IOException{
		//准备1.模板（*.ftl）
				//准备2.对象（map，实体）
				//1.导入jar包
				//2.创建一个配置对象并传递一个版本,创建一个模板对象(Template)
				config = new Configuration(Configuration.VERSION_2_3_28);
				//3.设置模板加载路径
				file = new File("D:\\itsource\\作业\\WebWorkSpace\\2019_09_11_cms04\\src\\main\\webapp\\templates");
				//获取配置对象
				config.setDirectoryForTemplateLoading(file);//设置模板加载文件夹
				//4.设置一个模板默认编码
				config.setDefaultEncoding("UTF-8");
	}
	
	@Test
	public void test() throws TemplateException, IOException {
		//5.获取一个模板对象
		Template template = config.getTemplate("01_test.ftl");
		//6.准备map数据
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> sonMap = new HashMap<>();
		map.put("name", "勇勇");
		map.put("pwd", "123");
		map.put("sonMap", sonMap);
		sonMap.put("address", "遂宁");
		//7.获取流,并指定输出文件
		PrintWriter out = new PrintWriter(new File(file,"01_test.html"));
		//8.生成文件
		template.process(map, out);
		//9.关闭流
		out.close();
	}
	
	@Test
	public void testObject() throws Exception{
		//准备模板
		Template template = config.getTemplate("02_test.ftl");
		//准备数据		生成了对象模板中el表达式直接使用对象的属性字段不需要再对象.字段
		City city = new City(19,"杭州");
		//7.获取流,并指定输出文件
		PrintWriter out = new PrintWriter(new File(file,"02_test.html"));
		
		//8.生成文件
		template.process(city, out);
		//9.关闭流
		out.close();
	}
	
	@Test
	public void testList() throws Exception{
		//准备模板
		Template template = config.getTemplate("03_test.ftl");
		//准备数据		生成了对象模板中el表达式直接使用对象的属性字段不需要再对象.字段
		City city1 = new City(10,"遂宁");
		City city2 = new City(19,"杭州");
		City city3 = new City(30,"达州");
		
		//第一种:直接将三个对象装在map
		//第二种:集合
		List<City> citys = new ArrayList<>();
		citys.add(city1);		
		citys.add(city2);		
		citys.add(city3);		
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", citys);
		
		//7.获取流,并指定输出文件 生成静态页面
		PrintWriter out = new PrintWriter(new File(file,"03_test.html"));
		
		//8.生成文件
		template.process(map, out);
		//9.关闭流
		out.close();
	}
}
