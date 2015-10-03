package cn.com.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cn.com.bean.Account;
import cn.com.bean.Cch;
import cn.com.bean.Comment;
import cn.com.bean.Forum;
import cn.com.bean.User;
import cn.com.bean.Ware;
import cn.com.detail.DetailManager;
import cn.com.jdbc.dao.PageManager;
import cn.com.stringmanager.StringManager;

public class BusinessManager {
	
	DetailManager dm=new DetailManager();
	PageManager pm=new PageManager();
	StringManager sm=new StringManager();
	//显示相应页面
	public List<Ware> getPage(int startIndex,int count,String target) throws SQLException{
		
		return dm.getPage(startIndex, count,target);
	}
	//显示主页请求数据集合
	public List<Ware> getPageList(String target) throws SQLException{
		return dm.getPageList(target);
	}
	//返回分页信息(基于数据库)
	public String getPaging(String tableName,int count,int nowPage,String url) throws SQLException{
		return pm.getPaging(tableName, count, nowPage, url);
	}
	//返回分页信息(基于集合)
	public String getPaging(List li,int count,int nowPage,String url,String param,String value){
		return pm.getPaging(li, count, nowPage, url,param,value);
	}
	//搜索引擎
	public List<Ware> search(int startIndex,int count,String source) throws SQLException{
		return dm.search(startIndex, count, source);
	}
	//返回搜索集合
	public List<Ware> getSearchList(String source) throws SQLException{
		return dm.getSearchList(source);
	}
	//根据number返回单个商品
	public Ware getSingleWare(String number) throws SQLException{
		return dm.getSingleWare(number);
	}
	//根据帐号密码返回改用户对象
	public User getLoginUser(String id,String pass) throws SQLException{
		return dm.getLoginUser(id,pass);
	}
	//根据账号返回用户对象
	public User getUserById(String id) throws SQLException{
		return dm.getUserById(id);
	}
	//为注册用户生成唯一账号
	public String getAccount(String tableName,String column,int digitCapacity,boolean isString,int scope,String prefix) throws SQLException{
		return dm.getAccount(tableName, column, digitCapacity, isString, scope, prefix);
	}
	//通用随机生成id---改进版
	public String getAccountPM(String sql,int digitCapacity,boolean isString,int scope,String prefix,String column) throws SQLException{
		return dm.getAccountPM(sql, digitCapacity, isString, scope, prefix,column);
	}
	//校验昵称
	public boolean verifyName(String name){
		boolean flag=false;
		if(name!=null){
			int lr=sm.allLetterNumber(name);
			int dt=sm.digitNumber(name);
			if(name.length()>=3&&lr+dt==name.length()){
				flag=true;
			}
		}
		return flag;
	}
	//校验密码
	public boolean verifyPass(String pass){
		boolean flag=false;
		if(pass!=null&&pass.length()>6){
			int dn=sm.digitNumber(pass);
			int ln=sm.letterNumber(pass);
			if(dn*ln!=0&&dn+ln==pass.length()){
				flag=true;
			}
		}
		return flag;
	}
	//校验邮箱
	public boolean verifyEmail(String email){
		return sm.emailVerify(email);
	}
	//添加注册用户
	public int addUser(String id,String name,String pass,String email) throws SQLException{
		return dm.addUser(id, name, pass, email);
	}
	//根据用户帐号和状态码返回或生成一个订单编号
	public String getOrderNumber(String id,int sta) throws SQLException{
		return dm.getOrderNumber(id, sta);
	}
	//根据订单编号、帐号、商品编号和状态码添加数据到chh
	public void addOrder(int wnchoice,String id,int sta,String orderNumber,String wareNumber) throws SQLException{
		dm.addOrder(wnchoice, id, sta, orderNumber, wareNumber);
	}
	//根据id返回cch信息
	public List<Cch> getMyCar(String id,int status) throws SQLException{
		return dm.getMyCar(id, status);
	}
	//计算购物车中商品总价
	public double getTotalPrice(String id) throws SQLException{
		return dm.getTotalPrice(id);
	}
	//根据cch返回当前页面信息
	public Map<Cch,String> getPageCCH(String id,int status,int startIndex,int count) throws SQLException{
		return dm.getPageCCH(id, status, startIndex, count);
	}
	//根据id、wareNumber、状态码判断是否存在
	public boolean verifyWareById(String id,String wareNumber,int status) throws SQLException{
		return dm.verifyWareById(id, wareNumber, status);
	}
	//根据id返回个人账户信息
	public List<Account> getAccountMessage(String id) throws SQLException{
		return dm.getAccountMessage(id);
	}
	//根据id返回个人账户当前页面信息
	public List<Account> getPageAcc(String id,int startIndex,int count) throws SQLException{
		return dm.getPageAcc(id, startIndex, count);
	}
	//计算个人账户余额
	public double getAccountBlance(String id) throws SQLException{
		return dm.getAccountBlance(id);
	}
	//根据id返回个人购物车订单号
	public String getOrderNumberById(String id) throws SQLException{
		return dm.getOrderNumberById(id);
	}
	//在orderform中插入数据
	public void addDataToOf(String formNumber,String cargo_receiver,String consignee,String phone,String company,String zip_code,String receipt,String express,String paymethod) throws SQLException{
		dm.addDataToOf(formNumber, cargo_receiver, consignee, phone, company, zip_code, receipt, express, paymethod);
	}
	//插入个人账户消费记录
	public void updateAccount(String id,double income,double pay) throws SQLException{
		dm.updateAccount(id, income, pay);
	}
	//ware中插入数据
	public void addDataToWare(String number,String name,String des,double price,String ad1,String ad2,String ad3) throws SQLException{
		dm.addDataToWare(number, name, des, price, ad1, ad2, ad3);
	}
	//更改ware中指定行数据
	public void updateToWare(String number,String name,String des,double price,String ad1,String ad2,String ad3) throws SQLException{
		dm.updateToWare(number, name, des, price, ad1, ad2, ad3);
	}
	//删除ware中指定行数据
	public void deleteDataFromWare(String number) throws SQLException{
		dm.deleteDataFromWare(number);
	}
	//在forum表中插入数据
	public void addDataToForum(String contentnumber,String id,String content) throws SQLException{
		dm.addDataToForum(contentnumber, id, content);
	}
	//删除forum中某行数据
	public void deleteDataFromForum(String contentnumber,String id) throws SQLException{
		dm.deleteDataFromForum(contentnumber, id);
	}
	//在comment表中插入数据
	public void addDataToComment(String cn,String commentary,String commentator) throws SQLException{
		dm.addDataToComment(cn, commentary, commentator);
	}
	//返回forum集合
	public List<Forum> getForum() throws SQLException, ParseException{
		return dm.getForum();
	}
	//返回forum当前页面信息
	public List<Forum> getForumPage(int startIndex,int count) throws SQLException, ParseException{
		return dm.getForumPage(startIndex, count);
	}
	//根据contentnumber查询该信息评论
	public List<Comment> getComment(String contentnumber) throws SQLException, ParseException{
		return dm.getComment(contentnumber);
	}
	public static void main(String[]args){
		BusinessManager bm=new BusinessManager();
		System.out.println(bm.verifyName("asfd"));
	}
}
