package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.bean.Cch;
import cn.com.business.BusinessManager;

public class MyCar extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyCar() {
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
			String ch=request.getParameter("ch");
			System.out.println(ch+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			request.setAttribute("ch", ch);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
		}else{
			String sta=request.getParameter("status");
			if(sta==null){
				sta=(String)request.getAttribute("status");
			}
			String np=request.getParameter("nowPage");
			if(np==null){
				np="1";
			}
			int status=3;
			String change=(String)request.getAttribute("change");
			if("car".equals(change)){
				status=1;
			}else if("col".equals(change)){
				status=2;
			}else{
				status=Integer.parseInt(sta);
			}
			
			int count=5;
			int nowPage=Integer.parseInt(np);
			int startIndex=(nowPage-1)*count;
			try {
				List<Cch> li=bm.getMyCar(id, status);
				Map<Cch,String> lii=bm.getPageCCH(id, status, startIndex, count);
				request.setAttribute("lii", lii);
				request.setAttribute("page", bm.getPaging(li, count, nowPage, "mycar","status",sta));
				if(status==2){
					//request.setAttribute("page", bm.getPaging(li, count, nowPage, "mycar","status",sta));
					request.getRequestDispatcher("collect.jsp").forward(request, response);
				}else if(status==1){
					//request.setAttribute("page", bm.getPaging(li, count, nowPage, "mycar","status",sta));
					double tp=bm.getTotalPrice(id);
					request.setAttribute("tp", tp);
					request.setAttribute("bo", lii.size());
					request.getRequestDispatcher("cart.jsp").forward(request, response);
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
