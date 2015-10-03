package cn.com.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionInstance {
	private static JdbcConnectionInstance jci;
	private Connection connection=null;	
	private String url="jdbc:mysql://localhost:3308/jx?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
	private String driver="com.mysql.jdbc.Driver";
	private String un="root";
	private String pw="1234";
	
	private JdbcConnectionInstance(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static JdbcConnectionInstance getInstance(){
		if(jci==null){
			jci=new JdbcConnectionInstance();
		}
		return jci;
	}
	public Connection getConnection(){
		try {
			connection=DriverManager.getConnection(url,un,pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;	
	}
}
