package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.business.BusinessManager;
import cn.com.fileload.LoadFile;
import cn.com.stringmanager.StringManager;

public class PublishSL extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PublishSL() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String saveDirectory=request.getRealPath("ceshi");
		//System.out.println(saveDirectory);
		BusinessManager bm=new BusinessManager();
		StringManager sm=new StringManager();
		int maxSize=3*10*1024*1024; 
		LoadFile lf=new LoadFile(request, maxSize, "utf-8", "ceshi");
		Map<Object,Object> map=lf.configLoad(null);
		String delete=(String)map.get("delete");
		String mark=(String)map.get("mark");
		String name=(String)map.get("name");
		String intro=(String)map.get("intro");
		String pri=(String)map.get("pri");
//		int k=pri.indexOf(".");
//		Map<Object, Integer> map1=sm.census(pri);
//		int m=map1.get(".");
		if(pri.length()==0||sm.digitNumber(pri)!=pri.length()){
			pri="0";
		}
		String ad1="";
		String ad2="";
		String ad3="";
		double price=Integer.parseInt(pri);
//		System.out.println(map);
		Object[]ke=map.keySet().toArray();
		for(Object ii:ke){
			String i=(String)ii;
			if(i.contains(".")){
				if(((String)map.get(i)).equals("0")){
					ad1="ceshi/"+i;
				}else if(((String)map.get(i)).equals("1")){
					ad2="ceshi/"+i;
				}else if(((String)map.get(i)).equals("2")){
					ad3="ceshi/"+i;
				}
			}
		}
//		System.out.println(map);
//		System.out.println(ad3);
		if(delete==null){
			try {
				String number=bm.getAccount("ware", "number", 7, true, 999999, mark);
				bm.addDataToWare(number, name, intro, price, ad1, ad2, ad3);
				response.sendRedirect("jx");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String waren=(String)map.get("waren");
			if("yes".equals(delete)){
				try {
					bm.deleteDataFromWare(waren);
					response.sendRedirect("jx");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					bm.updateToWare(waren, name, intro, price, ad1, ad2, ad3);
					response.sendRedirect("jx");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
