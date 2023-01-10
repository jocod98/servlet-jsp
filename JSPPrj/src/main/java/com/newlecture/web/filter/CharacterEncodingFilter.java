package com.newlecture.web.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request
			, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//filter 은 WAS 와 서블릿 사이에서 실행
		request.setCharacterEncoding("UTF-8");
		
	
		chain.doFilter(request,response); //흐름 원래대

	}

}
