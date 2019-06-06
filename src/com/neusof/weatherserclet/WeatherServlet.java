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
	//�����ַ�������
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	//���տͻ��˷��͵��������
	String cityname=request.getParameter("city");
	//���ù������л�ȡ�����ķ��������ؽ��
	String weather=WeatherUntil.getResult(cityname);
	//���ͻ��ˣ�ҳ�棩��������
	PrintWriter writer=response.getWriter();
	writer.write(weather);
	writer.flush();
	writer.close();
	}
   

}
