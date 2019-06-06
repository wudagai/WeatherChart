package com.neusof.weatheruntil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeatherUntil {
	/*
	 *{"future":{"day_20190606":{"date":"20190606","weather_id":{"fa":"01","fb":"02"},"week":"星期四","temperature":"19℃~29℃","weather":"多云转阴","wind":"北风微风"},"day_20190605":{"date":"20190605","weather_id":{"fa":"01","fb":"02"},"week":"星期三","temperature":"18℃~31℃","weather":"多云转阴","wind":"北风微风"},"day_20190608":{"date":"20190608","weather_id":{"fa":"00","fb":"02"},"week":"星期六","temperature":"22℃~33℃","weather":"晴转阴","wind":"东南风微风"},"day_20190607":{"date":"20190607","weather_id":{"fa":"02","fb":"02"},"week":"星期五","temperature":"20℃~29℃","weather":"阴","wind":"东风3-5级"},"day_20190602":{"date":"20190602","weather_id":{"fa":"02","fb":"04"},"week":"星期日","temperature":"22℃~34℃","weather":"阴转雷阵雨","wind":"西风微风"},"day_20190604":{"date":"20190604","weather_id":{"fa":"02","fb":"02"},"week":"星期二","temperature":"20℃~29℃","weather":"阴","wind":"东风3-5级"},"day_20190603":{"date":"20190603","weather_id":{"fa":"00","fb":"02"},"week":"星期一","temperature":"22℃~33℃","weather":"晴转阴","wind":"东南风微风"}},"today":{"weather_id":{"fa":"02","fb":"04"},"week":"星期日","city":"天津","dressing_index":"炎热","travel_index":"较不宜","wash_index":"较适宜","comfort_index":"","exercise_index":"较不宜","dressing_advice":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","uv_index":"弱","drying_index":"","temperature":"22℃~34℃","weather":"阴转雷阵雨","date_y":"2019年06月02日","wind":"西风微风"},"sk":{"temp":"26","humidity":"41%","wind_direction":"西风","time":"10:05","wind_strength":"5级"}}
	 */
	//存储apikey
	private static final String APIKEY="aab9072f78191e924508155931cde9bd";
	
	//存储api地址	
	private static final String APIURL="http://v.juhe.cn/weather/index";
	
	//定义一个全局访问点
	public static String getResult(String cityname) {
		//统一编码需要utf8 urlencode
		try {
			cityname=URLEncoder.encode(cityname, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//拼接url
		/*
		 * http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=您申请的KEY
		 * 返回json格式数据
		 */
		String url=APIURL+"?key="+APIKEY+"&cityname="+cityname;
		
		//使用apache的HttpClient模块开发
		//创建HttpGet对象,封装请求头
		HttpGet request=new HttpGet(url);
		
		String result="";
		try {
			//使用execute发送http请求,返回HttpResponse对象
			HttpResponse response=HttpClients.createDefault().execute(request);
			
			//获取响应码
			int code=response.getStatusLine().getStatusCode();
			
			//判断响应码，如果code等于200说明请求成功
			if(code==200) {
				//获取数据
				HttpEntity entity=response.getEntity();
				result=EntityUtils.toString(entity);
				//将json字符串转成json对象，我们就可以拿到想要的数据了
				JSONObject sJsonObject=JSON.parseObject(result);
				result=sJsonObject.get("result").toString();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
		}
	}