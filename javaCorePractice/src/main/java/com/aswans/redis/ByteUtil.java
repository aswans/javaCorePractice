package com.aswans.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class ByteUtil {

	/**
	 * @功能 String转换为byte[]
	 * @参数 @param str
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午2:14:14
	 * @返回值类型 byte[]
	 */
	public static byte[] toByte(String str,String charset) {
		if(ObjectUtil.isEmpty(charset)){
			charset = "UTF-8";
		}
		if (!ObjectUtil.isEmpty(str)) {
			try {
				return str.getBytes(charset);
			} catch (UnsupportedEncodingException e) {
				// TODO 异常暂时不处理
			}
		}

		return null;
	}

	/**
	 * @功能 Objet转换为byte[]
	 * @参数 @param obj
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午2:15:17
	 * @返回值类型 byte[]
	 */
	public static byte[] toByte(Object obj) {
		byte[] byteArr = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;

		if (!ObjectUtil.isEmpty(obj)) {
			try {
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);

				oos.writeObject(obj);

				byteArr = baos.toByteArray();
			} catch (IOException e) {
				// TODO 异常暂时不处理
			} finally {
				try {
					if (!ObjectUtil.isEmpty(baos)) {
						baos.close();
					}
				} catch (IOException e) {
					// TODO 异常暂时不处理
				}
				try {
					if (!ObjectUtil.isEmpty(oos)) {
						oos.close();
					}
				} catch (IOException e) {
					// TODO 异常暂时不处理
				}
			}
		}

		return byteArr;
	}

	/**
	 * @功能 byte[]转换为Objet
	 * @参数 @param byteArr
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午2:15:42
	 * @返回值类型 Object
	 */
	public static Object toObject(byte[] byteArr) {
		Object obj = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;

		if (!ObjectUtil.isEmpty(byteArr)) {
			try {
				bais = new ByteArrayInputStream(byteArr);
				ois = new ObjectInputStream(bais);

				obj = ois.readObject();
			} catch (IOException e) {
				// TODO 异常暂时不处理
			} catch (ClassNotFoundException e) {
				// TODO 异常暂时不处理
			} finally {
				try {
					if (!ObjectUtil.isEmpty(bais)) {
						bais.close();
					}
				} catch (IOException e) {
					// TODO 异常暂时不处理
				}
				try {
					if (!ObjectUtil.isEmpty(ois)) {
						ois.close();
					}
				} catch (IOException e) {
					// TODO 异常暂时不处理
				}
			}
		}

		return obj;
	}

	/**
	 * @功能 List转换为byte[]
	 * @参数 @param list
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午2:15:56
	 * @返回值类型 byte[]
	 */
	public static <T> byte[] toByte(List<T> list) {
		byte[][] byteArr = null;

		if (!ObjectUtil.isEmpty(list)) {
			byteArr = new byte[list.size()][];

			for (int i = 0; i < byteArr.length; i++) {
				byteArr[i] = toByte(list.get(i));
			}
		}

		// TODO 暂时处理
		int len = 0;
		for (byte[] b : byteArr) {
			len += b.length;
		}
		byte[] by = new byte[len];
		int index = 0;
		for (byte[] b : byteArr) {
			for (byte a : b) {
				by[index++] = a;
			}
		}

		return by;
	}

	/**
	 * @功能 byte[]转换为List
	 * @参数 @param list
	 * @参数 @return
	 * @作者 zhangsanjie add 2018-8-16 下午2:16:06
	 * @返回值类型 List<T>
	 */
	public static <T> List<T> toList(List<byte[]> list) {
		List<T> listObj = new ArrayList<T>();

		if (!ObjectUtil.isEmpty(list)) {
			for (byte[] b : list) {
				listObj.add((T) toObject(b));
			}
		}

		return listObj;
	}

}
