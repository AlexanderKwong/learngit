package myDBUtil;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBFactory {
	//数据库地址，若不存在会报错
	private static String dbUrl = "jdbc:mysql://localhost:3306/studentbd";
	//用户名
	private static String dbUsername = "root";
	//密码
	private static String dbPassword = "123456";
	//驱动名称
	private static String jdbcName = "com.mysql.jdbc.Driver";
	
	public static java.sql.Connection getCon() throws Exception{
		Class.forName(jdbcName);
		java.sql.Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		return con;
	}
	
	public static void close(java.sql.Connection conn ) throws Exception{
		
			if(conn !=null)
				conn.close();
		
	}
}
