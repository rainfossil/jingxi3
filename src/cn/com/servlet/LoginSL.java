package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.bean.User;
import cn.com.business.BusinessManager;

public class LoginSL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public LoginSL() {
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

		String account=request.getParameter("account");
		String pass=request.getParameter("pass");
		User us=null;
		try {
			us=bm.getLoginUser(account, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(us==null){
			String message="帐号或密码错误";
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			HttpSession session=request.getSession(true);
			session.setAttribute("acc", account);
			int rightx=0;
			try {
				rightx = bm.getUserById(account).getRightx();
				session.setAttribute("rx", rightx);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ch=request.getParameter("ch");
			System.out.println(ch+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			if(ch==null||"1".equals(ch)||"".equals(ch)){
				response.sendRedirect("jx");
			}else if("2".equals(ch)){
				response.sendRedirect("forum");
			}else if("3".equals(ch)){
				request.setAttribute("status", "1");
				request.getRequestDispatcher("mycar").forward(request, response);
			}else if("4".equals(ch)){
				request.setAttribute("status", "2");
				request.getRequestDispatcher("mycar").forward(request, response);
			}else{
				request.setAttribute("number",ch);
				request.getRequestDispatcher("sware").forward(request, response);
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
