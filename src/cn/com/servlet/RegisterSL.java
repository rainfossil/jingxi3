package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.business.BusinessManager;

public class RegisterSL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public RegisterSL() {
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
//		HttpSession session=request.getSession(true);
//		String acc=(String)session.getAttribute("acc");
		
		String name=request.getParameter("id");
		String pass=request.getParameter("pass");
		String pass1=request.getParameter("pass1");
		String email=request.getParameter("email");
//		int flag=0;
		request.setAttribute("na", name);
		if(pass1.equals(pass)&&bm.verifyName(name)&&bm.verifyPass(pass)&&bm.verifyEmail(email)){
			String id=null;
			try {
				id = bm.getAccount("user","id",7, true, 999999, "3");
				bm.addUser(id, name, pass, email);
				request.setAttribute("un", name);
				request.setAttribute("acco", id);
				request.getRequestDispatcher("success.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			if(!bm.verifyName(name)){
				name="";
				request.setAttribute("me1", "可用字母、汉字、数字，不小于3位");
			}else if(!bm.verifyPass(pass)){
				pass="";
				request.setAttribute("me2", "需同时包含字母和数字，不小于7位");
			}else if(!pass1.equals(pass)){
				request.setAttribute("me3", "两次输入不同");
			}else if(!bm.verifyEmail(email)){
				email="";
				request.setAttribute("me4", "邮箱格式错误");
			}
			request.setAttribute("pa1", "");
			request.setAttribute("pa", pass);
			request.setAttribute("em", email);
			request.getRequestDispatcher("register.jsp").forward(request, response);
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
