package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.com.bean.OrderForm;
import cn.com.business.BusinessManager;

public class ShopCartSL extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("ShopCartSL");
	/**
	 * Constructor of the object.
	 */
	public ShopCartSL() {
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
			String wn=request.getParameter("wn");
			request.setAttribute("ch", wn);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			String wn=request.getParameter("wn");
			String sta1=request.getParameter("sta");
			int sta=Integer.parseInt(sta1);
			request.setAttribute("number", wn);
			String collect=request.getParameter("collect");
			try {
				if("2".equals(sta1)){
					String orderNumber=bm.getOrderNumber(id, sta);
					if("yes".equals(collect)){
						bm.addOrder(-1, id, sta, orderNumber, wn);
						request.setAttribute("status", "2");
						request.getRequestDispatcher("mycar").forward(request, response);
					}else{
						bm.addOrder(1, id, sta, orderNumber, wn);
						request.getRequestDispatcher("sware").forward(request, response);
					}
				}else if("1".equals(sta1)){
					String orderNumber=bm.getOrderNumber(id, sta);
					bm.addOrder(1, id, sta, orderNumber, wn);
					
					if("yes".equals(collect)){
						request.setAttribute("change", "col");
						request.getRequestDispatcher("mycar").forward(request, response);
					}else{
						request.getRequestDispatcher("sware").forward(request, response);
					}
				}else if("0".equals(sta1)){
					OrderForm of=(OrderForm)session.getAttribute("of");
					double balance=bm.getAccountBlance(id);
					double total=bm.getTotalPrice(id);
					if(balance<total){
						response.sendRedirect("result.jsp");
					}else{
						String orderNumber=bm.getOrderNumber(id, sta);
						bm.addDataToOf(orderNumber, of.getCargo_receiver(), of.getConsignee(), of.getPhone(), of.getCompany(), of.getZip_code(), of.getReceipt(), of.getExpress(), of.getPaymethod());
						bm.addOrder(0, id, sta, orderNumber, null);
						bm.updateAccount(id, 0, total);
						log.debug(id+"支付订单"+orderNumber+"---"+total+"￥---");
						request.setAttribute("success", "yes");
						request.getRequestDispatcher("result.jsp").forward(request, response);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
