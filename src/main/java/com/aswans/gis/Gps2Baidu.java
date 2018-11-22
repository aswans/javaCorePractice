package com.aswans.gis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import com.aswans.bean.BaiduCoords;


public class Gps2Baidu {
   //private static final String convertPath="http://api.map.baidu.com/geoconv/v1/";//放到配置文件里
   private static Properties SYSTEM_CONFIG_PROPERTIES = new Properties();
	/**
	 * @param args
	 * @author zsj add 2015-7-22
	 * @throws IOException
	*/
	public static void main(String[] args) throws IOException {
		InputStream in = Gps2Baidu.class.getClassLoader().getResourceAsStream("sysconfig.properties");
		SYSTEM_CONFIG_PROPERTIES.load(in);
		// TODO Auto-generated method stub
		String jsonStr = getJsonArray("114.21892734521,29.575429778924");
		JSONObject jsonO = JSONObject.fromObject(jsonStr);
		
		Map map_result = JSONObject.fromObject(jsonStr);
		
		System.out.println(map_result);
		
		List list_rlt = (List) map_result.get("result");
		for(int i=0;i < list_rlt.size();i++){
			Map map = (Map) list_rlt.get(i);
			String x =  String.valueOf((Double)map.get("x"));
			String y =  String.valueOf((Double)map.get("y"));
			System.out.println(x);
		}
		
		JSONArray jsonArray = jsonO.getJSONArray("result");
		List list = (List) jsonArray.toCollection(jsonArray,BaiduCoords.class);
		for(int i=0;i<list.size();i++){
			BaiduCoords baiduCoords = (BaiduCoords) list.get(i);
			String x =  String.valueOf((Double)baiduCoords.getX());
			String y =  String.valueOf((Double)baiduCoords.getY());
		}
		
		//System.out.println(jsonO.getString("status"));
		//System.out.println(jsonO.getJSONArray("result").getJSONObject(0).getString("x"));
		//System.out.println(jsonO.getJSONArray("result").getJSONObject(0).getString("y"));
		
		//System.out.println(jsonStr.substring(jsonStr.indexOf("{")));
		//String x = JSONArray.fromObject(jsonArray).getJSONObject(0).getString("x");
		//System.out.println(x);
	}
	/**
	 * @author 王庆 2013-9-10 上午10:53:23
	 * @功能：将GPS坐标转换为百度地图坐标
	 * @param 坐标数组字符串
	 * @return
	*/
	public static String getJsonArray(String gpszbs){
		HttpClient httpclient = new HttpClient();//创建一个客户端，类似打开一个浏览器   
	
		String path = SYSTEM_CONFIG_PROPERTIES.getProperty("gps2bd_url") + "?coords="+ gpszbs + "&from=1&to=5&output=json&ak=mYOVOiaW3gRvGYMNGWoEw8pG";
		GetMethod getMethod = new GetMethod(path);//创建一个get方法，类似在浏览器地址栏中输入一个地址   
		String stringJson = "";
		try {
			httpclient.executeMethod(getMethod); 
			stringJson = getMethod.getResponseBodyAsString();
			//System.out.println(stringJson);
		} catch (HttpException e) {
			System.out.print("坐标偏移换算异常");
		} catch (IOException e) {
			System.out.print("坐标偏移换算传输异常!");
		}finally{
			getMethod.releaseConnection(); 
		}
		return stringJson;
	}
}
