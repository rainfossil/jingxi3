package cn.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingF implements Filter {
	private String encoding;
	private String text;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		response.setContentType(text);
		response.setCharacterEncoding(encoding);
		request.setCharacterEncoding(encoding);
		System.out.println("进入编码设置过滤器");
		arg2.doFilter(request, response);
		System.out.println("退出编码设置过滤器");
	}

	public void init(FilterConfig arg0) throws ServletException {
		String encoding=arg0.getInitParameter("encoding");
		String text=arg0.getInitParameter("text");
		if(encoding!=null){
			this.encoding=encoding;
		}
		if(text!=null){
			this.text=text;
		}
	}

}
