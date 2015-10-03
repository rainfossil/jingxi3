package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.business.BusinessManager;


public class CountControl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CountControl() {
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


		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BusinessManager bm=new BusinessManager();
		HttpSession session=request.getSession(true);
		
		String id=(String)session.getAttribute("acc");
		if(id==null){
			response.sendRedirect("login.jsp");
		}else{
			String orderNumber=request.getParameter("order_Number");
			String wareNumber=request.getParameter("ware_Number");
			System.out.println(wareNumber+"*****************"+orderNumber);
			String con=request.getParameter("con");
			int num=0;
				if("+".equals(con)){
					String add=request.getParameter("+");
					num=Integer.parseInt(add);
				}else if("-".equals(con)){
					String subtract=request.getParameter("-");
					num=-Integer.parseInt(subtract);
				}else if("x".endsWith(con)){
					num=-65535;
				}
				try {
					bm.addOrder(num, id, 1, orderNumber, wareNumber);
					request.setAttribute("change", "car");
					request.getRequestDispatcher("mycar").forward(request, response);
					//response.sendRedirect("mii.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
//		MiddleControl mc=new MiddleControl();
//		HttpSession session=request.getSession(false);
//		if(session==null){
//			session=request.getSession();
////			map=null;
//		}
//
//		Map<Integer,SingleRowsGoods> map=(Map<Integer,SingleRowsGoods>)session.getAttribute("shopcart");
//		String sid=request.getParameter("id");
//		int id=Integer.parseInt(sid);
//	
//		String con=request.getParameter("con");
//		if("+".equals(con)){
//			String add=request.getParameter("+");
//			int num=Integer.parseInt(add);
//			map=mc.getNumMap(id, map, num);
//		}else if("-".equals(con)){
//			String subtract=request.getParameter("-");
//			int num=-Integer.parseInt(subtract);
//			map=mc.getNumMap(id, map, num);
//		}else if("x".equals(con)){
//			map.remove(id);
//		}
//		String str=mc.getTotalPrice(map).toString();
//		request.setAttribute("tp",str);
//		request.getRequestDispatcher("shopcart.jsp").forward(request, response);
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

		doGet(request,response);
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
