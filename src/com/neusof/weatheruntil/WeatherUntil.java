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
	 *{"future":{"day_20190606":{"date":"20190606","weather_id":{"fa":"01","fb":"02"},"week":"������","temperature":"19��~29��","weather":"����ת��","wind":"����΢��"},"day_20190605":{"date":"20190605","weather_id":{"fa":"01","fb":"02"},"week":"������","temperature":"18��~31��","weather":"����ת��","wind":"����΢��"},"day_20190608":{"date":"20190608","weather_id":{"fa":"00","fb":"02"},"week":"������","temperature":"22��~33��","weather":"��ת��","wind":"���Ϸ�΢��"},"day_20190607":{"date":"20190607","weather_id":{"fa":"02","fb":"02"},"week":"������","temperature":"20��~29��","weather":"��","wind":"����3-5��"},"day_20190602":{"date":"20190602","weather_id":{"fa":"02","fb":"04"},"week":"������","temperature":"22��~34��","weather":"��ת������","wind":"����΢��"},"day_20190604":{"date":"20190604","weather_id":{"fa":"02","fb":"02"},"week":"���ڶ�","temperature":"20��~29��","weather":"��","wind":"����3-5��"},"day_20190603":{"date":"20190603","weather_id":{"fa":"00","fb":"02"},"week":"����һ","temperature":"22��~33��","weather":"��ת��","wind":"���Ϸ�΢��"}},"today":{"weather_id":{"fa":"02","fb":"04"},"week":"������","city":"���","dressing_index":"����","travel_index":"�ϲ���","wash_index":"������","comfort_index":"","exercise_index":"�ϲ���","dressing_advice":"�������ȣ������Ŷ�������ȹ���̿㡢����T�����������ļ���װ��","uv_index":"��","drying_index":"","temperature":"22��~34��","weather":"��ת������","date_y":"2019��06��02��","wind":"����΢��"},"sk":{"temp":"26","humidity":"41%","wind_direction":"����","time":"10:05","wind_strength":"5��"}}
	 */
	//�洢apikey
	private static final String APIKEY="aab9072f78191e924508155931cde9bd";
	
	//�洢api��ַ	
	private static final String APIURL="http://v.juhe.cn/weather/index";
	
	//����һ��ȫ�ַ��ʵ�
	public static String getResult(String cityname) {
		//ͳһ������Ҫutf8 urlencode
		try {
			cityname=URLEncoder.encode(cityname, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//ƴ��url
		/*
		 * http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=�������KEY
		 * ����json��ʽ����
		 */
		String url=APIURL+"?key="+APIKEY+"&cityname="+cityname;
		
		//ʹ��apache��HttpClientģ�鿪��
		//����HttpGet����,��װ����ͷ
		HttpGet request=new HttpGet(url);
		
		String result="";
		try {
			//ʹ��execute����http����,����HttpResponse����
			HttpResponse response=HttpClients.createDefault().execute(request);
			
			//��ȡ��Ӧ��
			int code=response.getStatusLine().getStatusCode();
			
			//�ж���Ӧ�룬���code����200˵������ɹ�
			if(code==200) {
				//��ȡ����
				HttpEntity entity=response.getEntity();
				result=EntityUtils.toString(entity);
				//��json�ַ���ת��json�������ǾͿ����õ���Ҫ��������
				JSONObject sJsonObject=JSON.parseObject(result);
				result=sJsonObject.get("result").toString();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
		}
	}