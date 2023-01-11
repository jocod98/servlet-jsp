package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";
		if(cookies!=null)
			for(Cookie c :cookies) 
				if(c.getName().equals("exp")) { //쿠키 이름이 op인것 찾아서 value 를 operator에 부
					exp = c.getValue();
					break;
				}
		
		if(operator != null && operator.equals("="))
		{
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else
		{
			exp += (value == null)?"":value; //null이면 빈문자
			exp += (operator == null)?"":operator; //null이면 빈문자
			exp += (dot == null)?"":dot; //null이면 빈문자
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		
		
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
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









