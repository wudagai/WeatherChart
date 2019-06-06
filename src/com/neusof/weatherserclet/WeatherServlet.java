package com.neusof.weatherserclet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusof.weatheruntil.WeatherUntil;

/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/Weather")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//处理字符集乱码
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	//接收客户端发送的请求参数
	String cityname=request.getParameter("city");
	//调用工具类中获取天气的方法，返回结果
	String weather=WeatherUntil.getResult(cityname);
	//给客户端（页面）传送天气
	PrintWriter writer=response.getWriter();
	writer.write(weather);
	writer.flush();
	writer.close();
	}
   

}
