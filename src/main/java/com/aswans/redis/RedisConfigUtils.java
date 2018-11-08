package com.aswans.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * @功能    读取redis配置文件
 * @作者 zhangsanjie add 2018-8-13 下午5:20:30
 */
public class RedisConfigUtils {
	private static Properties REDIS_CONFIG_PROPERTIES = new Properties();
	static {		
		InputStream is = null;
		try {
			is = JedisUtil.class.getClassLoader().getResourceAsStream("RedisConfig.properties");
			REDIS_CONFIG_PROPERTIES.load(is);
		} catch (IOException e) {
		}finally{
			try {
				if(is != null)	is.close();
			} catch (IOException e) {
			}
		}			
	}
	/**
	 * 获取配置信息
	 * @param key 关键字
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getProperty(String key, String defaultValue){
		String property = getProperty(key);
		if(property == null || property.equals("")) return defaultValue;
		else return property;
	}
	
	/**
	 * 从属性文件中获取键对应的值
	 * @param key
	 * @return key对应的value
	 */
	public static String getProperty(String key){
		String val = REDIS_CONFIG_PROPERTIES.getProperty(key);
		//return val == null ? null : new String(val.getBytes("ISO8859_1") , "utf-8");
		return val == null ? null : new String(val);
	}
}
