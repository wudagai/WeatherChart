package com.neusof.test;

import java.util.Scanner;

import com.neusof.weatheruntil.WeatherUntil;

public class Test {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String cityname=scanner.nextLine();
		String result=WeatherUntil.getResult(cityname);
		System.out.println(result);
	}

}
