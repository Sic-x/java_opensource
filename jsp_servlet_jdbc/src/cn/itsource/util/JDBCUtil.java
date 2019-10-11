package cn.itsource.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JDBCUtil {
	static DataSource dataSource = null;

	static {
		//1.加载资源文件
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("db.properties"));
			//================================手动方式=============================//
//			//2.获取资源文件的类型
//			String driverName = prop.getProperty("driverName");
//			String url = prop.getProperty("url");
//			String user = prop.getProperty("user");
//			String password = prop.getProperty("password");
//			String maxWait = prop.getProperty("maxWait");
//			String initSize = prop.getProperty("initSize");
//			String maxActive = prop.getProperty("maxActive");
//			//3.创建一个连接池
//			dataSource = new BasicDataSource();
//			//4.为连接池设置连接数据的参数
//			dataSource.setDriverClassName(driverName);
//			dataSource.setUrl(url);
//			dataSource.setUsername(user);
//			dataSource.setPassword(password);
//			dataSource.setMaxWait(Long.parseLong(maxWait));
//			dataSource.setInitialSize(Integer.parseInt(initSize));
//			dataSource.setMaxActive(Integer.parseInt(maxActive));
			//================================自动方式=============================//
			dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (IOException e) {
			System.out.println("资源文件加载失败...");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("连接池创建失败...");
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		//通过连接池获取
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("获取连接失败...");
			e.printStackTrace();
		}
		return null;
	}

	//封装一个关闭连接的方法
	public static void closeConnection(ResultSet rs,Statement st,Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if(st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	}

	//封装增删改的方法
	public static void executeSql(String sql,Object... objs) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0 ;i<objs.length ;i++) {
				ps.setObject(i+1, objs[i]);  
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("语句对象创建失败...");
			e.printStackTrace();
		} finally {
			closeConnection(null,ps,conn);
		}
	}

	//封装查询单个对象的方法
	public static <T> T getSingleResult(String sql,Class<T> cls,Object... objs) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			T t = cls.newInstance();//默认调用类的公有无参构造
			ps = conn.prepareStatement(sql);
			for(int i =0;i<objs.length;i++) {
				ps.setObject(i+1, objs[i]);
			}
			//获取结果集
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData metaData = rs.getMetaData();
			//获取列的数量
			int columnCount = metaData.getColumnCount();
			if(rs.next()) {//如果有数据(一条数据)
				Map<String,Object> map = new HashMap<>();
				for(int i = 1;i<=columnCount;i++) {//遍历每一个列
					String columnName = metaData.getColumnName(i);//列的名称
					Object obj = rs.getObject(columnName);
					map.put(columnName, obj);
				}
				BeanUtils.populate(t, map);
				return t;
			}
		} catch (SQLException e) {
			System.out.println("语句对象获取失败...");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("反射创建对象失败");
			e.printStackTrace();
		} finally {
			closeConnection(rs, ps, conn);
		}
		return null;
	}

	//封装查询多个对象的方法
	public static <T> List<T> getResult(String sql,Class<T> cls,Object... objs){
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0;i<objs.length;i++) {
				ps.setObject(i+1, objs[i]);
			}
			//获取结果集
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData metaData = rs.getMetaData();
			//获取列的数量
			int columnCount = metaData.getColumnCount();
			
			List<T> lists = new ArrayList<>();
			
			while(rs.next()) {
				//有一条数据 -- 创建一个Map对象 -- 并将列的名词(元素据)和数据(结果集rs)设置到map对象 -- 将map对象的值转移到实体对象中
				Map<String ,Object> map = new HashMap<>();
				T t = cls.newInstance();
				for(int i = 1;i<=columnCount;i++) {//遍历获取每一列的名词和对应的数据
					map.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				BeanUtils.populate(t, map);
				lists.add(t);
			}
			return lists;
		} catch (SQLException e) {
			System.out.println("语句对象创建失败...");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("反射创建对象失败...");
			e.printStackTrace();
		} finally {
			closeConnection(rs, ps, conn);
		}
		return null;
	}
	
	
	
	
	
	
	
}
