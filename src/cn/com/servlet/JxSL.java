package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.bean.Ware;
import cn.com.business.BusinessManager;

public class JxSL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public JxSL() {
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
			
			String target=request.getParameter("target");
			if(target==null){
				target="";
			}
			String np=request.getParameter("nowPage");
			if(np==null){
				np="1";
			}
			int nowPage=Integer.parseInt(np);
			int count=9;
			int startIndex=(nowPage-1)*count;
			try {
				String search=request.getParameter("search");
				List<Ware> li=null;
				String page=null;
				if(search==null){
					li=bm.getPage(startIndex, count,target);
					if("".equals(target)){
						page=bm.getPaging("ware", count, nowPage, "jx");
					}else{
						List<Ware> lii=bm.getPageList(target);
						page=bm.getPaging(lii, count, nowPage, "jx",null,null);
					}
				}else{
					li=bm.search(startIndex, count, search);
					List<Ware> lii=bm.getSearchList(search);
					page=bm.getPaging(lii, count, nowPage, "jx",null,null);
				}
				
				request.setAttribute("li", li);
				request.setAttribute("page", page);
				request.getRequestDispatcher("jx.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
