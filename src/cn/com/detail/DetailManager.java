package cn.com.detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.com.bean.Account;
import cn.com.bean.Cch;
import cn.com.bean.Comment;
import cn.com.bean.Forum;
import cn.com.bean.User;
import cn.com.bean.Ware;
import cn.com.jdbc.dao.PublicDao;
import cn.com.stringmanager.StringManager;
import cn.com.stringmanager.TimeManager;

public class DetailManager {
	PublicDao pd=new PublicDao();
	ResultSet rs=null;
	StringManager sm=new StringManager();
	TimeManager tm=new TimeManager();
	//显示相应页面
	public List<Ware> getPage(int startIndex,int count,String target) throws SQLException{
		
		List<Ware> li=new ArrayList<Ware>();		
		List<Object> pmli=new ArrayList<Object>();
		pmli.add(startIndex);
		pmli.add(count);
		rs=(ResultSet)pd.publicMethod("select * from ware where number like '%"+target+"%' limit ?,?",pmli, true);
		Ware we=null;
		while(rs.next()){
			we=new Ware();
			we.setNumber(rs.getString("number"));
			we.setName(rs.getString("name"));
			we.setDescription(rs.getString("description"));
			we.setDayTime(rs.getDate("dayTime").toString());
			we.setPrice(rs.getDouble("price"));
			we.setAddress1(rs.getString("address1"));
			we.setAddress2(rs.getString("address2"));
			we.setAddress3(rs.getString("address3"));
			li.add(we);
		}
		pd.free(rs);
		return li;
	}
	//显示主页请求数据集合
	public List<Ware> getPageList(String target) throws SQLException{
		
		List<Ware> li=new ArrayList<Ware>();		
		rs=(ResultSet)pd.publicMethod("select * from ware where number like '%"+target+"%'",null, true);
		Ware we=null;
		while(rs.next()){
			we=new Ware();
			we.setNumber(rs.getString("number"));
			we.setName(rs.getString("name"));
			we.setDescription(rs.getString("description"));
			we.setDayTime(rs.getDate("dayTime").toString());
			we.setPrice(rs.getDouble("price"));
			we.setAddress1(rs.getString("address1"));
			we.setAddress2(rs.getString("address2"));
			we.setAddress3(rs.getString("address3"));
			li.add(we);
		}
		pd.free(rs);
		return li;
	}
	
	//返回搜索集合
	public List<Ware> getSearchList(String source) throws SQLException{
		List<Ware> li=new ArrayList<Ware>();
		rs=(ResultSet)pd.publicMethod("select * from ware",null, true);	
		Ware we=null;
		if(rs==null||source==null||"".equals(source)){
			li=null;
		}else{
			while(rs.next()){
				if(rs.getString("name").contains(source)){
					we=new Ware();
					we.setNumber(rs.getString("number"));
					we.setName(rs.getString("name"));
					we.setDescription(rs.getString("description"));
					we.setDayTime(rs.getDate("dayTime").toString());
					we.setPrice(rs.getDouble("price"));
					we.setAddress1(rs.getString("address1"));
					we.setAddress2(rs.getString("address2"));
					we.setAddress3(rs.getString("address3"));
					li.add(we);	
				}
			}
		}
		pd.free(rs);
		return li;
	}
	
	//搜索引擎
	public List<Ware> search(int startIndex,int count,String source) throws SQLException{

		List<Ware> li=getSearchList(source);
		List<Ware> lii=new ArrayList<Ware>();
		if(li==null){
			return lii;
		}else{
			int flag=startIndex+count;
			if(flag>li.size()){
				for(int i=startIndex;i<li.size();i++){
					lii.add(li.get(i));
				}
			}else{
				for(int i=startIndex;i<startIndex+count;i++){
					lii.add(li.get(i));
				}
			}
			return lii;
		}

		
	}
	//根据number返回单个商品
	public Ware getSingleWare(String number) throws SQLException{
		List<Object> pmli=new ArrayList<Object>();
		pmli.add(number);
		rs=(ResultSet)pd.publicMethod("select * from ware where number=?",pmli, true);
		Ware we=new Ware();
		if(rs!=null&&rs.next()){
			we.setNumber(rs.getString("number"));
			we.setName(rs.getString("name"));
			we.setDescription(rs.getString("description"));
			we.setDayTime(rs.getDate("dayTime").toString());
			we.setPrice(rs.getDouble("price"));
			we.setAddress1(rs.getString("address1"));
			we.setAddress2(rs.getString("address2"));
			we.setAddress3(rs.getString("address3"));
		}
		pd.free(rs);
		return we;
	}
	//根据帐号密码返回该用户对象
	public User getLoginUser(String id,String pass) throws SQLException{
		User us=getUserById(id);
		if(us!=null){
			if(pass==null||"".equals("pass")){
				us=null;
			}else{
				if(!pass.endsWith(us.getPass())){
					us=null;
				}
			}
		}
		return us;
	}
	//根据账号返回用户对象
	public User getUserById(String id) throws SQLException{
		List<Object> pmli=new ArrayList<Object>();
		pmli.add(id);
		rs=(ResultSet)pd.publicMethod("select * from user where id=?", pmli, true);
		User us=null;
		if(rs!=null&&rs.next()){
			us=new User();
			us.setEmail(rs.getString("email"));
			us.setId(rs.getString("id"));
			us.setName(rs.getString("name"));
			us.setPass(rs.getString("pass"));
			us.setRightx(rs.getInt("rightx"));
		}
		pd.free(rs);
		return us;
	}
	
	//通用随机生成id
	public String getAccount(String tableName,String column,int digitCapacity,boolean isString,int scope,String prefix) throws SQLException{
		rs=(ResultSet)pd.publicMethod("select "+column+" from "+tableName, null, true);
		String account=null;
		if(rs!=null){
			while(true){
				account=(String)sm.generateID(digitCapacity, isString, scope, prefix);
				boolean fg=true;
				while(rs.next()){
					if(account.equals(rs.getString(column))){
						fg=false;
						break;
					}
				}
				if(fg){
					break;
				}
			}
		}
		pd.free(rs);
		return account;
	}
	//通用随机生成id---改进版
	public String getAccountPM(String sql,int digitCapacity,boolean isString,int scope,String prefix,String column) throws SQLException{
		rs=(ResultSet)pd.publicMethod(sql, null, true);
		String account=null;
		if(rs!=null){
			while(true){
				account=(String)sm.generateID(digitCapacity, isString, scope, prefix);
				boolean fg=true;
				while(rs.next()){
					if(account.equals(rs.getString(column))){
						fg=false;
						break;
					}
				}
				if(fg){
					break;
				}
			}
		}
		pd.free(rs);
		return account;
	}
	//添加注册用户
	public int addUser(String id,String name,String pass,String email) throws SQLException{
		List<Object> pmli=new ArrayList<Object>();
		pmli.add(id);
		pmli.add(name);
		pmli.add(pass);
		pmli.add(email);
		pmli.add(0);
		int count=(Integer)pd.publicMethod("insert into user values(?,?,?,?,?)", pmli, false);
		pd.free(rs);
		return count;
	}
	//根据用户帐号和状态码返回或生成一个编号
	public String getOrderNumber(String id,int sta) throws SQLException{
		User us=null;
		String onr=null;
		if(id!=null){
			us=getUserById(id);
			if(us!=null){//生成可用编号
				System.out.println("ceshi 122222222222222222211");
				rs=(ResultSet)pd.publicMethod("select orderNumber from cch where status="+sta+" and userId="+id+" group by orderNumber", null, true);
				if(rs.next()){
					List<String> li=new ArrayList<String>();
					li.add(rs.getString("orderNumber"));
					while(rs.next()){
						li.add(rs.getString("orderNumber"));
					}
					if(sta==0){//历史订单编号不同
						System.out.println("ceshi 1111111111111111111111");
						while(true){
							boolean flag=true;
							onr=(String)sm.generateID(13, true, 999999999, "jx");		
							for(String lii:li){
								if(onr.equals(lii)){
									flag=false;
									break;
								}
							}
							if(flag){
								List<Object> pmlii=new ArrayList<Object>();
								pmlii.add(onr);
								pmlii.add(id);
								pmlii.add(sta);
								pd.publicMethod("insert into cch(orderNumber,userId,status) values(?,?,?)", pmlii, false);
								break;
							}
						}	
					}else{//返回已存在编号
						onr=li.get(0);
					}		
				}else{
					onr=(String)sm.generateID(13, true, 999999999, "jx");
					List<Object> pmlii=new ArrayList<Object>();
					pmlii.add(onr);
					pmlii.add(id);
					pmlii.add(sta);
					pd.publicMethod("insert into cch(orderNumber,userId,status) values(?,?,?)", pmlii, false);
					//pd.publicMethod("insert into cch(orderNumber,userId,sta) values("+onr+","+id+","+sta+")", null, false);
				}	
			}
		}
		pd.free(rs);
		return onr;
		//insert into cch(orderNumber) values('3353');
	}
	//添加数据到cch
	public void addDataToCch(int count,String id,int sta,String orderNumber,String wareNumber) throws SQLException{
		List<Object> pmli=new ArrayList<Object>();
		pmli.add(orderNumber);
		pmli.add(id);
		pmli.add(wareNumber);
		pmli.add(count);
		pmli.add(sta);
		pd.publicMethod("insert into cch values(null,?,?,?,?,?,now())", pmli, false);
//		pd.free(rs);
	}
	//从cch中删除数据
	public void deleteDataFromCch(String id,int sta,String orderNumber,String wareNumber) throws SQLException{
		List<Object> pmli=new ArrayList<Object>();
		pmli.add(sta);
		pmli.add(id);
		pmli.add(wareNumber);
		pmli.add(orderNumber);
		pd.publicMethod("delete from cch where status=? and userId=? and wareNumber=? and orderNumber=?", pmli, false);
//		pd.free(rs);
	}
	//根据订单编号、帐号、商品编号和状态码添加数据到chh
	public void addOrder(int wnchoice,String id,int sta,String orderNumber,String wareNumber) throws SQLException{
		//rs=(ResultSet)pd.publicMethod("select * from cch where status="+sta+" and userId="+id+" and wareNumber="+wareNumber, null, true);
		List<Object> pmli=new ArrayList<Object>();
		pmli.add(sta);
		pmli.add(id);
		pmli.add(wareNumber);
		rs=(ResultSet)pd.publicMethod("select * from cch where status=? and userId=? and wareNumber=?", pmli, true);
		if(sta==2){//收藏
			if(!rs.next()){
				addDataToCch(0,id,sta,orderNumber,wareNumber);
			}else{
				if(wnchoice<0){
					deleteDataFromCch(id,sta,orderNumber,wareNumber);
				}
			}
		}else if(sta==1){//加入购物车
			if(rs.next()){
				int cr=rs.getInt("wareCount");
				int count=cr+wnchoice;
				if(wnchoice==-65535){
					deleteDataFromCch(id,sta,orderNumber,wareNumber);
				}else{
					if(count<1){count=1;}
					//addDataToCch(count,id,sta,orderNumber,wareNumber);
					List<Object> pml=new ArrayList<Object>();
					pml.add(count);
					pml.add(sta);
					pml.add(orderNumber);
					pml.add(wareNumber);
					//pd.publicMethod("update cch set wareCount="+count+" where status=1 and orderNumber="+orderNumber+" and wareNumber="+wareNumber, null, false);
					pd.publicMethod("update cch set wareCount=? where status=? and orderNumber=? and wareNumber=?", pml, false);
				}	
			}else{
				addDataToCch(wnchoice,id,sta,orderNumber,wareNumber);
			}
		}else if(sta==0){//成功生成订单
			List<Object> pml=new ArrayList<Object>();
			pml.add(1);
			pml.add(id);
			rs=(ResultSet)pd.publicMethod("select * from cch where status=? and userId=?", pml, true);
			while(rs.next()){
				addDataToCch(rs.getInt("wareCount"),rs.getString("userId"),sta,orderNumber,rs.getString("wareNumber"));
				deleteDataFromCch(rs.getString("userId"),1,rs.getString("orderNumber"),rs.getString("wareNumber"));
			}
		}
		pd.free(rs);
	}
	//根据id、状态码返回cch信息
	public List<Cch> getMyCar(String id,int status) throws SQLException{
		Cch cch=null;
		List<Cch> li=new ArrayList<Cch>();
		
		List<Object> pml=new ArrayList<Object>();
		pml.add(status);
		pml.add(id);
		rs=(ResultSet)pd.publicMethod("select * from cch where status=? and userId=?", pml, true);
		rs.next();
		while(rs.next()){
			cch=new Cch();
			cch.setDateTime(rs.getString("dateTime"));
			cch.setFlag(rs.getInt("flag"));
			cch.setOrderNumber(rs.getString("orderNumber"));
			cch.setStatus(rs.getInt("status"));
			cch.setUserId(rs.getString("userId"));
			cch.setWareCount(rs.getInt("wareCount"));
			cch.setWareNumber(rs.getString("wareNumber"));
			li.add(cch);	
		}
		pd.free(null);
		return li;
	}
	//计算购物车中商品总价
	public double getTotalPrice(String id) throws SQLException{
		double tp=0;
		List<Cch> li=getMyCar(id,1);
		for(Cch cch:li){
			tp=tp+cch.getWe().getPrice()*cch.getWareCount();
		}
		return tp;
	}
	//根据cch返回当前页面信息
	public Map<Cch,String> getPageCCH(String id,int status,int startIndex,int count) throws SQLException{
		List<Cch> li=getMyCar(id,status);
		List<Cch> lii=new ArrayList<Cch>();
		if(li==null){
//			return lii;
		}else{
			int flag=startIndex+count;
			if(flag>li.size()){
				for(int i=startIndex;i<li.size();i++){
					lii.add(li.get(i));
				}
			}else{
				for(int i=startIndex;i<startIndex+count;i++){
					lii.add(li.get(i));
				}
			}
//			return lii;
		}
		Map<Cch,String> map=new HashMap<Cch,String>();
		for(Cch cch:lii){
			List<Object> pml=new ArrayList<Object>();
			pml.add(1);
			pml.add(id);
			pml.add(cch.getWareNumber());
			rs=(ResultSet)pd.publicMethod("select * from cch where status=? and userId=? and wareNumber=?", pml, true);
			if(rs.next()){
				map.put(cch, "yes");
			}else{
				map.put(cch, "no");
			}
		}
		pd.free(rs);
		return map;
	}
	//根据id、wareNumber、状态码判断是否存在
	public boolean verifyWareById(String id,String wareNumber,int status) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(id);
		pml.add(status);
		pml.add(wareNumber);
		rs=(ResultSet)pd.publicMethod("select * from cch where userId=? and status=? and wareNumber=?", pml, true);
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	//根据id返回个人账户信息
	public List<Account> getAccountMessage(String id) throws SQLException{
		List<Account> li=new ArrayList<Account>();
		Account acc=null;
		List<Object> pml=new ArrayList<Object>();
		pml.add(id);
		rs=(ResultSet)pd.publicMethod("select * from account where userId=?", pml, true);
		while(rs.next()){
			acc=new Account();
			acc.setDateTime(rs.getString("dateTime"));
			acc.setFlag(rs.getInt("flag"));
			acc.setIncome(rs.getDouble("income"));
			acc.setPay(rs.getDouble("pay"));
			acc.setUserId(rs.getString("userId"));
			li.add(acc);
		}
		pd.free(rs);
		return li;
	}

	//在orderform中插入数据
	public void addDataToOf(String formNumber,String cargo_receiver,String consignee,String phone,String company,String zip_code,String receipt,String express,String paymethod) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(formNumber);
		pml.add(cargo_receiver);
		pml.add(zip_code);
		pml.add(consignee);
		pml.add(phone);
		pml.add(company);
		pml.add(receipt);
		pml.add(express);
		pml.add(paymethod);
		pd.publicMethod("insert into orderform values(null,?,?,?,?,?,?,now(),?,?,?)", pml, false);
		pd.free(rs);
	}

	//根据id返回个人账户当前页面信息
	public List<Account> getPageAcc(String id,int startIndex,int count) throws SQLException{
		List<Account> li=getAccountMessage(id);
		List<Account> lii=new ArrayList<Account>();
		int flag=startIndex+count;
		if(flag>li.size()){
			for(int i=startIndex;i<li.size();i++){
				lii.add(li.get(i));
			}
		}else{
			for(int i=startIndex;i<startIndex+count;i++){
				lii.add(li.get(i));
			}
		}
		return lii;
	}
	//计算个人账户余额
	public double getAccountBlance(String id) throws SQLException{
		List<Account> li=getAccountMessage(id);
		double income=0;
		double pay=0;
		for(Account ac:li){
			income=income+ac.getIncome();
			pay=pay+ac.getPay();
		}
		System.out.println(income+"##################"+pay);
		double ab=income-pay;
		return ab;
	}
	//插入个人账户消费记录
	public void updateAccount(String id,double income,double pay) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(id);
		pml.add(income);
		pml.add(pay);
		pd.publicMethod("insert into account values(null,?,?,?,now())", pml, false);
		pd.free(rs);
	}
	//根据id返回个人购物车订单号
	public String getOrderNumberById(String id) throws SQLException{
		String orderNumber="";
		List<Object> pml=new ArrayList<Object>();
		pml.add(id);
		pml.add(1);
		rs=(ResultSet)pd.publicMethod("select * from cch where userId=? and status=? group by userId", pml, true);
		while(rs.next()){
			orderNumber=rs.getString("orderNumber");
		}
		pd.free(rs);
		return orderNumber;
	}
	//ware中插入数据
	public void addDataToWare(String number,String name,String des,double price,String ad1,String ad2,String ad3) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(number);
		pml.add(name);
		pml.add(des);
		pml.add(price);
		pml.add(ad1);
		pml.add(ad2);
		pml.add(ad3);
		pd.publicMethod("insert into ware values(?,?,?,now(),?,?,?,?)", pml, false);
		pd.free(rs);
	}
	//更改ware中指定行数据
	public void updateToWare(String number,String name,String des,double price,String ad1,String ad2,String ad3) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(name);
		pml.add(des);
		pml.add(price);
		pml.add(ad1);
		pml.add(ad2);
		pml.add(ad3);
		pml.add(number);
		pd.publicMethod("update ware set name=?,description=?,price=?,address1=?,address2=?,address3=? where number=?", pml, false);
		pd.free(rs);
	}
	//删除ware中指定行数据
	public void deleteDataFromWare(String number) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(number);
		pd.publicMethod("delete from ware where number=?", pml, false);
		pd.free(rs);
	}
	//在forum表中插入数据
	public void addDataToForum(String contentnumber,String id,String content) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(contentnumber);
		pml.add(id);
		pml.add(content);
		pd.publicMethod("insert into forum values(null,?,?,?,now())", pml, false);
		pd.free(rs);
	}
	//删除forum中某行数据
	public void deleteDataFromForum(String contentnumber,String id) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(contentnumber);
		pml.add(id);
		pd.publicMethod("delete from forum where contentnumber=? and id=?", pml, false);
		pd.free(rs);
	}
	//返回forum集合
	public List<Forum> getForum() throws SQLException, ParseException{
		List<Forum> li=new ArrayList<Forum>();
		rs=(ResultSet)pd.publicMethod("select * from forum order by flag desc limit 53", null, true);
		Forum fo=null;
		String dt="";
		List<Comment> lc=null;
		String cn=null;
		while(rs.next()){
			fo=new Forum();
			fo.setContent(rs.getString("content"));
			fo.setContentnumber(rs.getString("contentnumber"));
			dt=rs.getString("dateTime");
			dt=tm.getWant("yy-MM-dd HH:mm:ss", Locale.CHINA, dt);
			fo.setDateTime(dt);
//			System.out.println(dt);
//			System.err.println(fo.getDatetTime());
			fo.setId(rs.getString("id"));
//			lc=getComment(cn);
//			fo.setTimes(lc.size());
			li.add(fo);
		}
		List<Forum> lii=new ArrayList<Forum>();
		for(Forum fm:li){
			cn=fm.getContentnumber();
			lc=getComment(cn);
			fm.setTimes(lc.size());
			lii.add(fm);
			//System.out.println(lc.size()+"######@@@@@@@@@@@@@@##########");
		}
		
		pd.free(rs);
		return lii;
	}
	//返回forum当前页面信息
	public List<Forum> getForumPage(int startIndex,int count) throws SQLException, ParseException{
		List<Forum> li=getForum();
		List<Forum> lii=new ArrayList<Forum>();
		int flag=startIndex+count;
		if(flag>li.size()){
			for(int i=startIndex;i<li.size();i++){
				lii.add(li.get(i));
			}
		}else{
			for(int i=startIndex;i<startIndex+count;i++){
				lii.add(li.get(i));
			}
		}
		return lii;
	}
	//在comment表中插入数据
	public void addDataToComment(String cn,String commentary,String commentator) throws SQLException{
		List<Object> pml=new ArrayList<Object>();
		pml.add(cn);
		pml.add(commentary);
		pml.add(commentator);
		pd.publicMethod("insert into comment values(null,?,?,?,now())", pml, false);
		pd.free(rs);
	}
	//根据contentnumber查询该信息评论
	public List<Comment> getComment(String contentnumber) throws SQLException, ParseException{
		List<Comment> li=new ArrayList<Comment>();
		Comment co=null;
		List<Object> pml=new ArrayList<Object>();
		pml.add(contentnumber);
		rs=(ResultSet)pd.publicMethod("select * from comment where cn=?", pml, true);
		String dt="";
		while(rs.next()){
			co=new Comment();
			co.setCn(rs.getString("cn"));
			co.setCommentary(rs.getString("commentary"));
			co.setCommentator(rs.getString("commentator"));
			co.setFlag(rs.getInt("flag"));
			dt=rs.getString("dateTime");
			dt=tm.getWant("yy-MM-dd HH:mm:ss", Locale.CHINA, dt);
			co.setDateTime(dt);
			li.add(co);
		}
		//pd.free(rs);
		return li;
	}
	public static void main(String[]args) throws SQLException{
		
		DetailManager dm=new DetailManager();

		List<Ware> we=dm.search(0, 7, "x");
		StringBuffer names=new StringBuffer();
		for(int i=0;i<we.size();i++){
			if(i==(we.size()-1)){
				names.append(we.get(i).getName());
			}else{
				names.append(we.get(i).getName()+"&");
			}
		}
		System.out.println(we.size());
		System.out.println(names.toString());
//		System.out.println(dm.getAccountBlance("3335332"));
//		List<Account> li=dm.getAccountMessage("3335332");
//		for(Account ac:li){
//			System.out.println(ac.getDateTime());
//		}
		
//		Map<String,Integer> map=new HashMap<String,Integer>();
//		map.put("b", 3);
//		map.put("c", 6);
//		map.put("a", 1);
//		System.out.println(map);
//		Object[]ob=map.keySet().toArray();
//		for(Object o:ob){
//			System.out.println(map.get(o));
//		}
//		dm.addOrder(7, "3026438", 2, "jx00993702563", "p103353");
		//update cch set wareCount=5 where status=0 and orderNumber='jx00993702561' and wareNumber='p103351';
//		System.out.println(dm.getOrderNumber("3335332", 2));
//		Ware we=dm.getSingleWare("p103351");
//		System.out.println(we.getDayTime());
		
//		System.out.println(dm.getAccount("user","id",7, true, 999999, "3"));
		
//		List<Ware> li=dm.search(0,3,"寂寞");
//		for(Ware w:li){
//			System.out.println(w.getName());
//		}
//		PublicDao pd=new PublicDao();
//		String ceshi="image/music/jimo1.jpg";
//		String sql="insert into ware values('m103331','寂寞在唱歌','阿桑专辑',now(),13353,'image/music/jimo1.jpg','image/music/jimo2.jpg','image/music/jimo3.jpg'),('m103332','受了点伤','阿桑专辑',now(),13353,'image/music/shoushang1.jpg','image/music/shoushang2.jpg','image/music/shoushang3.jpg');";
//		pd.publicMethod(sql, null, false);
		
		
	}
}
