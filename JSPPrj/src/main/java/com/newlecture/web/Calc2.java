package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; cahrset = UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v= 0;
		if (!v_.equals("")) v = Integer.parseInt(v_);
		
		//계
		if(op.equals("=")) {
			//앞까지의 
			//int x = (Integer)application.getAttribute("value");
			int x = (Integer)session.getAttribute("value");
			//지금 계산할 것 
			int y = v;
			//String operator = String.valueOf(application.getAttribute("op"));
			String operator = String.valueOf(session.getAttribute("op"));
			int result = 0;
		
			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;
			
			out.printf("result is %d", result);
		}
		else {
		//값을 저
			//application.setAttribute("value",v);
			//application.setAttribute("op",op);
			session.setAttribute("value",v);
			session.setAttribute("op",op);
			
			//어플리케이션은 전역 ,세션은 세션범주(현재접속-사용자별로 공간을 부여받)에서만 쓸수 있다.
		}
		
	}
	
}
