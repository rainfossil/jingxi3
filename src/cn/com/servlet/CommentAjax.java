package cn.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.com.bean.Comment;
import cn.com.business.BusinessManager;

public class CommentAjax extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CommentAjax() {
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
		//处理缓存问题
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires", "-1");
		
		BusinessManager bm=new BusinessManager();
		HttpSession session=request.getSession(true);
		String id=(String)session.getAttribute("acc");
		
		String searchvalue=request.getParameter("searchvalue");
		String textarea=request.getParameter("textarea");
		searchvalue=URLDecoder.decode(searchvalue, "UTF-8");   //对url中文参数进行解码
		textarea=URLDecoder.decode(textarea, "UTF-8");
		//System.out.println(searchvalue+"$$$$$$$$$$$$$$$$$$$$$$"+textarea);
		List<Comment> li=null;
		try {
			if(textarea!=null){
				textarea=textarea.trim();
				if(textarea.length()>=133){
					textarea=textarea.substring(0, 133);
				}
				if(!"".equals(textarea)){
				//System.out.println("ceshi........................1");
				bm.addDataToComment(searchvalue, textarea, id);
				}
			}
			li=bm.getComment(searchvalue);
			//System.out.println(li.size()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		Gson gs=new GsonBuilder().setDateFormat("yy-MM-dd hh:mm:ss").disableHtmlEscaping().create();
		String gc=gs.toJson(li);
		out.write(gc);
		out.flush();
		out.close();
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
