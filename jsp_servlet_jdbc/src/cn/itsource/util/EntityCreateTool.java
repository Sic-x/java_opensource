package cn.itsource.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

public class EntityCreateTool {
	static Scanner s = new Scanner(System.in);
	private static DatabaseMetaData dbmd = null;

	public static void main(String[] args) {
		action();
	}

	/**
	 * 总控制器
	 */
	public static void action(){
		System.out.println("产品说明：此产品用于生产数据库中已经存在的表的实体类！");
		System.out.println("需要注意：\nA.属性配置文件db.properties放在src下 \nB.里面的url,user和pwd不能写错 \nC.在db那一项中写上需要生成实体类所对应的表的数据库名");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("请选择：1.生成数据库所有表的实体类  2.生成其中某个表的实体类");
		int log = s.nextInt();
		switch (log) {
		case 1:
			List<String> tables = getAllTables();
			for(String table : tables){
				entityCreater(getAllColumn(table),getAutoIncrementColumn(table),table);
			}
			System.out.println("请刷新你的工程...");
			break;
		case 2:
			System.out.println("亲！请输出你要生成的实体类对应的表名：");
			String tableName = s.next();
			entityCreater(getAllColumn(tableName),getAutoIncrementColumn(tableName),tableName);
			System.out.println("请刷新你的工程...");
			break;
		}
	}

	/**
	 * 获取指定表中所有的自增长列的相关信息
	 * @return
	 */
	public static List<String> getAutoIncrementColumn(String table){
		List<String> autoIncrementColumnMessage = new ArrayList<String>();
		try {
			dbmd = getConnectionByConfigFile().getMetaData();
			ResultSet colRet = dbmd.getColumns(null,"%",table,"%");
			while(colRet.next()) { 
				if("YES".equals(colRet.getString("IS_AUTOINCREMENT"))){
					autoIncrementColumnMessage.add(colRet.getString("COLUMN_NAME"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return autoIncrementColumnMessage;
	}

	/**
	 * 获取指定表中所有的列的相关信息
	 * @return
	 */
	public static HashMap<String,String> getAllColumn(String table){
		HashMap<String,String> columnMessage = new LinkedHashMap<String,String>();
		PreparedStatement ps;
		try {
			ps = getConnectionByConfigFile().prepareStatement("select * from "+table);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData meta =  rs.getMetaData();
			for(int i = 1;i <= meta.getColumnCount();i++){
				columnMessage.put(meta.getColumnName(i), meta.getColumnClassName(i));
			}
		} catch (SQLException e1) {
			System.out.println("语句对象创建异常!");
			e1.printStackTrace();
		}
		return columnMessage;
	}

	/**
	 * 获取该数据库中所有的表组成的集合，保存的是数据库表的名称
	 * @return
	 */
	public static List<String> getAllTables(){
		List<String> tables = new ArrayList<String>();
		try {
			dbmd = getConnectionByConfigFile().getMetaData();
			ResultSet rs = dbmd.getTables(null, "%","%",new String[]{"TABLE"}); 
			while(rs.next()){
				tables.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			System.out.println("获取表元数据异常!");
			e.printStackTrace();
		}
		return tables;
	}

	/**
	 * 通过属性配置文件.properties获取连接
	 * @return
	 */
	public static Connection getConnectionByConfigFile(){
		Properties prop = new Properties();
		Connection conn = null;
		try {
			prop.load(EntityCreateTool.class.getClassLoader().getResourceAsStream("db.properties"));
			String driver = prop.getProperty("driverClassName");
			String url = prop.getProperty("url");
			String user = prop.getProperty("username");
			String pwd = prop.getProperty("password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (IOException e) {
			System.out.println("配置文件加载异常：是否配置文件路径有误？文件名写错？");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载异常：是否忘记导入驱动包？ 驱动名写错？");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("获取连接异常：用户名和密码写错？ mysql服务尚未打开？ url格式错误？");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 生成实体类
	 * @param columnMessage
	 * @param autoIncrementColumnMessage
	 * @param tableName
	 */
	public static void entityCreater(HashMap<String,String> columnMessage,List<String> autoIncrementColumnMessage,String tableName){
		File file = new File("./src/cn/itsource/domain");
		if(!file.exists()){
			file.mkdirs(); 
		}	
		try{
			FileOutputStream fos = new FileOutputStream("./src/cn/itsource/domain/"+new Character(tableName.charAt(0)).toString().toUpperCase()+tableName.substring(1)+".java");
			fos.write("package cn.itsource.domain;\n\n".getBytes());
			fos.write(("public class "+new Character(tableName.charAt(0)).toString().toUpperCase()+tableName.substring(1)+"{ \n").getBytes());

			for(Entry<String, String> entry:columnMessage.entrySet()){
				fos.write(("\t"+"private "+entry.getValue().substring(entry.getValue().lastIndexOf(".")+1)+" "+entry.getKey()+";\n").getBytes());
			}

			//无参构造
			fos.write(("\t"+"public "+tableName.toUpperCase().charAt(0)+tableName.substring(1)+"() {\n").getBytes());
			fos.write("\t\tsuper();\n".getBytes());
			fos.write("\t}\n".getBytes());

			//全参数构造器
			fos.write(("\t"+"public "+tableName.toUpperCase().charAt(0)+tableName.substring(1)+"(").getBytes());
			String word1 = "";
			for(Entry<String, String> entry:columnMessage.entrySet()){
				word1 += entry.getValue().substring(entry.getValue().lastIndexOf(".")+1)+" "+entry.getKey()+", ";
			}
			word1 = word1.substring(0, word1.lastIndexOf(","));
			fos.write((word1+") {\n").getBytes());	
			fos.write("\t\tsuper();\n".getBytes());
			word1 = "";
			for(Entry<String, String> entry:columnMessage.entrySet()){
				word1 += "\t\tthis."+entry.getKey()+" = "+entry.getKey()+";\n";
			}
			word1 += "\t}\n";
			fos.write(word1.getBytes());

			//不带主键的构造器
			if(autoIncrementColumnMessage.size()!=0){
				HashMap<String,String> tempColumnMessage = new LinkedHashMap<String,String>();
				tempColumnMessage = clone(columnMessage);
				//Java中有深复制(深克隆)与浅复制(浅克隆)之分，下边两种都是浅拷贝，数据的引用都指向了之前的map
				/*
				 * 1.浅复制（浅克隆）:被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象。
				 * 	换言之，浅复制仅仅复制所考虑的对象，而不复制它所引用的对象。
				 * 2.深复制（深克隆）:被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。那些引用其他对象
				 * 	的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象。换言之，深复制把要复制的对象所引用的对象都复制了一遍。
				 * 3.继承自java.lang.Object类的clone()方法是浅复制
				 * 4.利用串行化来做深复制:把对象写到流里的过程是串行化（Serilization）过程，但是在Java程序师圈子里又非常形象地
				 * 	称为“冷冻”或者“腌咸菜（picking）”过程；而把对象从流中读出来的并行化（Deserialization）过程则叫做 “解冻”或者“回鲜(depicking)”过程
				 */
				//tempColumnMessage = columnMessage;
				/*for(Entry<String, String> entry:columnMessage.entrySet()){
					tempColumnMessage.put(new String(entry.getKey()), new String(entry.getValue()));
				}*/
				fos.write(("\t"+"public "+tableName.toUpperCase().charAt(0)+tableName.substring(1)+"(").getBytes());
				word1 = "";

				Iterator<Entry<String, String>> it = tempColumnMessage.entrySet().iterator();
				while(it.hasNext()){
					Entry<String, String> entry= it.next();
					for(String autoIncrementColumn : autoIncrementColumnMessage){
						if(entry.getKey().equals(autoIncrementColumn)){
							it.remove();
						}
					}
				}


				for(Entry<String, String> entry:tempColumnMessage.entrySet()){
					word1 += entry.getValue().substring(entry.getValue().lastIndexOf(".")+1)+" "+entry.getKey()+", ";
				}
				word1 = word1.substring(0, word1.lastIndexOf(","));
				fos.write((word1+") {\n").getBytes());	
				fos.write("\t\tsuper();\n".getBytes());
				word1 = "";
				for(Entry<String, String> entry:tempColumnMessage.entrySet()){
					word1 += "\t\tthis."+entry.getKey()+" = "+entry.getKey()+";\n";
				}
				word1 += "\t}\n";
				fos.write(word1.getBytes());
			}

			//getter和setter方法
			word1 = "";
			for(Entry<String, String> entry:columnMessage.entrySet()){
				word1 += "\tpublic "+entry.getValue().substring(entry.getValue().lastIndexOf(".")+1)+
						" get"+new Character(entry.getKey().charAt(0)).toString().toUpperCase()+entry.getKey().substring(1)+
						"() {\n\t\treturn "+entry.getKey()+";\n\t}\n"+
						"\tpublic void set"+
						new Character(entry.getKey().charAt(0)).toString().toUpperCase()+entry.getKey().substring(1)+
						"("+entry.getValue().substring(entry.getValue().lastIndexOf(".")+1)+" "+
						entry.getKey()+") {\n\t\t"+
						"this."+entry.getKey()+" = "+entry.getKey()+";\n\t}\n";
			}
			fos.write(word1.getBytes());

			//toString()
			word1 = "";
			word1 += "\t@Override\n\tpublic String toString() {\n\t\treturn \""+
					new Character(tableName.charAt(0)).toString().toUpperCase()+tableName.substring(1)+
					"[";
			for(Entry<String, String> entry:columnMessage.entrySet()){
				word1 += entry.getKey()+"=\" + "+entry.getKey()+" + \", ";
			}
			word1 = word1.substring(0, word1.lastIndexOf(","))+"]\";\n\t}\n}";	
			fos.write(word1.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件没有找到!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("配置文件加载失败!");
			e.printStackTrace();
		} 
	}

	/**
	 * 利用串行化来做深复制或深克隆
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj){
		T clonedObj = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			clonedObj = (T) ois.readObject();
			ois.close();

		}catch (Exception e){
			e.printStackTrace();
		}
		return clonedObj;
	}


}
