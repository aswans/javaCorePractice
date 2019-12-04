package com.aswans.redis;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {
	/**
	 * @功能   判断对象是否为空
	 * @参数 @param obj
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午1:59:39
	 * @返回值类型 boolean
	 */
	public static boolean isEmpty(Object obj){
		if(obj==null){
			return true;
		}else{
			if(obj instanceof Collection){
				List list =  (List)obj;
				if(list.size() ==0){
					return true;
				}
			}else if(obj instanceof Map){
				Map map =  (Map)obj;
				if(map.size() ==0){
					return true;
				}
			}else if(obj instanceof String ){
				String str =  (String)obj;
				if("".equals(str)){
					return true;
				}
			}else{
				return false;
			}
		}
		return false;
	}
	/**
	 * @功能 json字符串转list
	 * @参数 @param str
	 * @参数 @param cl
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午3:04:58
	 * @返回值类型 List<T>
	 */
	public static <T> List<T> json2List(String str, Class<?> cl) {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, cl);
		List<T> list = null;
		try {
			list = mapper.readValue(str, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @功能 list转json字符串
	 * @参数 @param str
	 * @参数 @param cl
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午3:04:58
	 * @返回值类型 String
	 */
	public static String list2Json(List<?> list) {
		ObjectMapper mapper = new ObjectMapper();
		String str = "";
		try {
			str = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO 异常统一处理
			e.printStackTrace();
		}
		return str;
	}
}
