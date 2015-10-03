package cn.com.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PublicDao extends JdbcBaseData{
	JdbcConnectionInstance jci=JdbcConnectionInstance.getInstance();
	private Connection connection=null;

	//实例化预编译语句
	private void sqlInitialization(String sql,List<Object> paramList){
		connection=jci.getConnection();
		setConnection(connection);
		setSql(sql);
		if(paramList==null||paramList.size()==0){
			setParamList(null);
		}else{
			setParamList(paramList);
		}
		paramList=null;
	}
	//综合查更数据
	public Object publicMethod(String sql,List<Object> paramList,boolean isGetSet) throws SQLException{
		sqlInitialization(sql,paramList);
		if(isGetSet){
			return dataQuery();	
		}else{
			return dataUpdate();
		}	
	}
	//通用sql语句执行方法
	public boolean sqlExecute(String sql,List<Object> paramList) throws SQLException{
		sqlInitialization(sql,paramList);
		return sqlExecute();
	}
	//释放资源
	public void free(Object ob) throws SQLException{
		if(ob!=null){
			ResultSet rs=(ResultSet)ob;
			if(rs!=null||!rs.isClosed()){	
				System.out.println("rs关闭");
				rs.close();
				rs=null;
			} 
		}
		if(getPs()!=null||!getPs().isClosed()){	
			System.out.println("ps关闭");
			getPs().close();
			//setPs(null);
		}
		if(connection!=null||!connection.isClosed()){
			System.out.println("connection关闭");
			connection.close();
			//connection=null;
		}			
	}
	public static void main(String[]args) throws SQLException{
		ResultSet rse=null;
		PublicDao pd=new PublicDao();
		rse=(ResultSet)pd.publicMethod("select * from user", null, true);
		while(rse.next()){
			System.out.println(rse.getString("id"));
		}
		System.out.println(rse.getFetchSize());
		System.out.println(rse.getFetchDirection()+"##########");
	
//		List<Object> li=new ArrayList<Object>();
//		li.add(333353);
//		ResultSet i=(ResultSet)new PublicDao().publicMethod("select u.id from user as u",null,true);
//		while(i.next()){
//			System.out.println(i.getInt("id"));
//		}
//		new PublicDao().free(i);
//		PublicDao pd=new PublicDao();
//		ResultSet rs=(ResultSet)pd.publicMethod("select * from ware where number='m103331'", null, true);
//		if(!rs.next()){
//			System.out.println("******");
//		}else{
//			System.out.println("############");
//			System.out.println(rs.getString("number")+"$$$@@@@@@@@@@$$$$$");
//			while(rs.next()){
//				System.out.println(rs.getString("number")+"$$$$$$$$$$$$$$$$$$$$$");
//			}
//		}
	}
}	