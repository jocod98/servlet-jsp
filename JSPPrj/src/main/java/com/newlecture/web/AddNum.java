package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddNum extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; cahrset = UTF-8");
		// 출력 전에만 설정해주면 됨.
		// request 에 대한 것은 필터에 있음.
		
		PrintWriter out = response.getWriter();
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");
		
		//빈문자열 혹은 숫자 submit 하면 null은 올 수 없음.
		int x = 0;
		int y = 0;
		
		if(!x_.equals("")) x = Integer.parseInt(x_); 
		if(!y_.equals("")) y = Integer.parseInt(y_);

		int result = x+y;
		out.printf("result is %d", result);
		
		
	}
	
}
