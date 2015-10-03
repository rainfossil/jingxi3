package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.bean.OrderForm;
import cn.com.business.BusinessManager;

public class ManagerFormSL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ManagerFormSL() {
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
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String zipcode=request.getParameter("zipcode");
			String phone=request.getParameter("phone");
			String express=request.getParameter("express");
			String paym=request.getParameter("paym");
			String receipt=request.getParameter("receipt");
			
			try {
				//double balance=bm.getAccountBlance(id);
				double total=bm.getTotalPrice(id);
				
				OrderForm of=new OrderForm();
				of.setCargo_receiver(name);
				of.setCompany("京西");
				of.setConsignee(address);
				of.setExpress(express);
				of.setPaymethod(paym);
				of.setPhone(phone);
				of.setReceipt(receipt);
				of.setZip_code(zipcode);
				request.setAttribute("total", total);
				session.setAttribute("of", of);
				//response.sendRedirect("of.jsp");
				request.getRequestDispatcher("of.jsp").forward(request, response);
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
