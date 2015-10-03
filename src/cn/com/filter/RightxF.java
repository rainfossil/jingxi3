package cn.com.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.business.BusinessManager;

public class RightxF implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		HttpSession session=request.getSession(true);	
		String id=(String)session.getAttribute("acc");
		BusinessManager bm=new BusinessManager();
		int rightx=0;
		if(id!=null){
			try {
				rightx = bm.getUserById(id).getRightx();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[]uri={"jx.jsp","account.jsp","cart.jsp","collect.jsp","of.jsp","result.jsp","s_ware.jsp","success.jsp","backstagemg.jsp","forum.jsp"};
		String urii=request.getRequestURI();
		boolean flag=false;
		for(String reui:uri){
			if(urii.contains(reui)){
				flag=true;
				if(reui.equals("of.jsp")&&id!=null){
					flag=false;
				}else if(reui.equals("backstagemg.jsp")&&rightx==3){
					flag=false;
				}
				break;
			}
		}
		System.out.println("进入访问控制过滤器");
		if(flag){
			response.sendRedirect("jx");
		}else{
			arg2.doFilter(request, response);
		}
		System.out.println("退出访问控制过滤器");
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
