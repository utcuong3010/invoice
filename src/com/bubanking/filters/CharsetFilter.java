package com.bubanking.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;

public class CharsetFilter  implements Filter{
	
	private String encoding;
	
	
	

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");
		if(StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain next) throws IOException, ServletException {
		
		//set request
		if(null == request.getCharacterEncoding()) {
			request.setCharacterEncoding(encoding);
		}
		//set response
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding(encoding);
		
		next.doFilter(request, response);
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
