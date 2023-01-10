package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		Cookie[] cookies = request.getCookies();
		
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
			//int x = (Integer)session.getAttribute("value");
			int x = 0;
			
			for(Cookie c :cookies) 
				if(c.getName().equals("value")) { //쿠키검색 name 이 value	인것.
					x = Integer.parseInt(c.getValue());
					break;
				}
			
			//지금 계산할 것 
			int y = v;
			//String operator = String.valueOf(application.getAttribute("op"));
			//String operator = String.valueOf(session.getAttribute("op"));
			
			String operator = "";
			for(Cookie c :cookies) 
				if(c.getName().equals("op")) { //쿠키 이름이 op인것 찾아서 value 를 operator에 부
					operator = c.getValue();
					break;
				}
			//현재 x에는 value operator 에는 op값이 들어있음.
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
			
			//session.setAttribute("value",v);
			//session.setAttribute("op",op);
			
			Cookie valueCookie = new Cookie("value",String.valueOf(v));
			Cookie opCookie = new Cookie("op",op);
			valueCookie.setPath("/calc2"); //쿠키가쓰일 경로 설정 전체면 '/' url명을 씀.
			valueCookie.setMaxAge(60 * 60 * 24); //가독성 60분. --> 하루.(만료날짜)
			opCookie.setPath("/calc2");
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			//쿠키는 기본적으로 브라우저가 닫히면 사라짐.
			//기간을 설정시 브라우저 닫혀도 생존 (브라우저 메모리에 있다가 외부파일(영구저장소)로 저장. 쿠키파일.
			
			//어플리케이션은 전역 ,세션은 세션범주(현재접속-사용자별로 공간을 부여받)에서만 쓸수 있다.
			
			response.sendRedirect("calc2.html"); //사용자가 요청한것 처럼.
		}
		
	}
	
}


// 1.application
// 1)사용범위 : 전역
// 2)생명주기 : WAS가 시작해서 종료할 때 까지.
// 3)저장위치 : WAS서버의 메모리.
//
// 2.Session
// 1)사용범위 : 세션범위에서 사용하는 저장공간.
// 2)생명주기 : 세션이 시작해서 종료할 때 까지.
// 3)저장위치 : WAS서버의 메모리.
//
//
// 3.Cookie
// 1)사용범위 : Web Browser별 지정한 path 범주 공간.
// 2)생명주기 : Browser에 전달한 시간부터 만료시간까지.
// 3)저장위치 : Web Browser의 메모리 또는 파일.
// -기간이 길면 무조건 쿠키 사용.









