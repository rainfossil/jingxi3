package cn.com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.bean.Forum;
import cn.com.business.BusinessManager;

public class ForumSL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ForumSL() {
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

		BusinessManager bm=new BusinessManager();
		HttpSession session=request.getSession(true);
		
		String id=(String)session.getAttribute("acc");
		if(id==null){
			String ch=request.getParameter("ch");
			System.out.println(ch+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			request.setAttribute("ch", ch);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			
				String content=request.getParameter("content");
				if(content!=null){
					content=content.trim();
					if(content.length()>=253){
						content=content.substring(0, 253);
					}
					if(!"".equals(content)){
						String contentnumber=null;
						try {
							contentnumber = bm.getAccount("forum", "contentnumber", 11, true, 999999999, "cc");
							bm.addDataToForum(contentnumber, id, content);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			
				int count=5;
				String np=request.getParameter("nowPage");
				if(np==null){
					np="1";
				}
				int nowPage=Integer.parseInt(np);
				int startIndex=(nowPage-1)*count;
				try {
					List<Forum> li=bm.getForumPage(startIndex, count);
					List<Forum> lii=bm.getForum();
					//List<Comment> liii=bm.getComment(contentnumber)
					request.setAttribute("page", bm.getPaging(lii, count, nowPage, "forum", null, null));
					request.setAttribute("li", li);
					//System.out.println(li.size()+"@@@@@@@@@@@@@@@@@@@@@@@");
					request.getRequestDispatcher("forum.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
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
