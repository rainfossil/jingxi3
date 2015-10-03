package cn.com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public class JdbcBaseData {
	private String sql;
	private List<Object> paramList;
	private Connection connection;
	private PreparedStatement ps;
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	public void setParamList(List<Object> paramList) {
		this.paramList = paramList;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public PreparedStatement getPs() {
		return ps;
	}
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
	//设置预编译sql语句实参
	public void setSqlParamList(List<Object> paramList,PreparedStatement ps) throws SQLException{
			for(int i=0;i<paramList.size();i++){
				ps.setObject(i+1, paramList.get(i));
			}			
	}
	//返回预编译PreparedStatement对象
	public PreparedStatement getPreparedStatement() throws SQLException{
		ps=connection.prepareStatement(sql);		
		if(paramList==null||paramList.size()==0){
			paramList=null;
			return ps;
		}else{
			setSqlParamList(paramList,ps);
			paramList=null;
			return ps;
		}
	}
	//查询数据
	public ResultSet dataQuery() throws SQLException{	
		return getPreparedStatement().executeQuery();
	}
	//更新数据
	public int dataUpdate() throws SQLException{		
		return getPreparedStatement().executeUpdate();
	}
	//通用sql语句执行方法
	public boolean sqlExecute() throws SQLException{
		return getPreparedStatement().execute();
	}
	//释放资源（不能调此free()，getPs()空指针异常）
//	public void free(Object ob) throws SQLException{
//		if(ob!=null){
//			ResultSet rs=(ResultSet)ob;
//			if(rs!=null||!rs.isClosed()){				
//				rs.close();
//				rs=null;
//			} 
//		}
//		if(getPs()!=null||!getPs().isClosed()){			
//			getPs().close();
//			setPs(null);
//		}
//		if(connection!=null||!connection.isClosed()){
//			connection.close();
//			connection=null;
//		}			
//	}
	
}