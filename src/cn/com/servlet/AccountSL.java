package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.bean.Account;
import cn.com.business.BusinessManager;

public class AccountSL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AccountSL() {
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
			int count=5;
			String np=request.getParameter("nowPage");
			if(np==null){
				np="1";
			}
			int nowPage=Integer.parseInt(np);
			int startIndex=(nowPage-1)*count;
			try {
				List<Account> li=bm.getAccountMessage(id);
				List<Account> lii=bm.getPageAcc(id, startIndex, count);
				request.setAttribute("page", bm.getPaging(li, count, nowPage, "account",null,null));
				double balance=bm.getAccountBlance(id);
				request.setAttribute("balance", balance);
				request.setAttribute("lii", lii);
				request.getRequestDispatcher("account.jsp").forward(request, response);
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
