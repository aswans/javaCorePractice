package com.aswans.redis;

import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public class JedisUtil {
	private static JedisPool jedisPool = null;
	private static String redis_host=null;
	private static int redis_port;
	private static String redis_auth=null;
	private static int max_total;
	private static int max_idle;
	private static int max_waitMillis;
	static {
		if(jedisPool == null) {
			redis_host = RedisConfigUtils.getProperty("redis.host")==null?"localhost":RedisConfigUtils.getProperty("redis.host");
			redis_port = RedisConfigUtils.getProperty("redis.port")==null?6379:Integer.parseInt(RedisConfigUtils.getProperty("redis.port"));
			redis_auth = RedisConfigUtils.getProperty("redis.auth")==null?"":RedisConfigUtils.getProperty("redis.auth");
			max_total = RedisConfigUtils.getProperty("redis.max_total")==null?50:Integer.parseInt(RedisConfigUtils.getProperty("redis.max_total"));
			max_idle = RedisConfigUtils.getProperty("redis.max_idle")==null?5:Integer.parseInt(RedisConfigUtils.getProperty("redis.max_idle"));
			max_waitMillis = RedisConfigUtils.getProperty("redis.max_waitMillis")==null?5:Integer.parseInt(RedisConfigUtils.getProperty("redis.max_waitMillis"));
			JedisPoolConfig config = new JedisPoolConfig();
			//控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxTotal(max_total);
			//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(max_idle);
			//表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
			//小于零:阻塞不确定的时间,  默认-1
			config.setMaxWaitMillis(max_waitMillis);
			//在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			//return 一个jedis实例给pool时，是否检查连接可用性（ping()）
			config.setTestOnReturn(true);
			//connectionTimeout 连接超时（默认2000ms）
			//soTimeout 响应超时（默认2000ms）
			jedisPool = new JedisPool(config, redis_host, redis_port, 5000, null);
			if(jedisPool==null){
				throw new RuntimeException("创建redis连接池失败");
			}
		}
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getResource();
			if(jedis==null){
				throw new RuntimeException("获取redis实例失败");
			}
			if (exist(key)) {
				//value = (T) ByteUtil.toObject(jedis.get(ByteUtil.toByte(key)));
				value = jedis.get(key);
				//logger.debug("Jedis ===> get - key:{} - value:{}", key, value);
			}
		} catch (Exception ex) {
			//logger.warn("Jedis ===> get - key:{} - value:{}", key, value, ex);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	/**
	 * 设置缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSecond 超时时间，0为不超时
	 * @return
	 */
	public static String set(String key, String value, int cacheSecond) {
		String result = null;
		Jedis jedis = null;

		if (!ObjectUtil.isEmpty(value)) {
			try {
				jedis = JedisUtil.getResource();
				if(jedis==null){
					throw new RuntimeException("获取redis实例失败");
				}
				//result = jedis.set(ByteUtil.toByte(key), ByteUtil.toByte(value));
				result = jedis.set(key,value);
				if (cacheSecond != 0) {
					jedis.expire(key, cacheSecond);
				}
				//logger.debug("Jedis ===> get - key:{} - value:{}", key, value);
			} catch (Exception e) {
				//logger.warn("Jedis ===> get - key:{} - value:{}", key, value, e);
			} finally {
				close(jedis);
			}
		}

		return result;
	}

	/**
	 * 获取List缓存
	 * 
	 * @param key
	 * @return
	 */
	public static <T> List<T> getList(String key, Class<?> cl) {
		List<T> value = null;
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getResource();
			if(jedis==null){
				throw new RuntimeException("获取redis实例失败");
			}
			if (exist(key)) {
				value = ObjectUtil.json2List(jedis.get(key), cl);
				//logger.debug("Jedis ===> getList - key:{} - value:{}", key, value);
			}
		} catch (Exception ex) {
			//logger.warn("Jedis ===> getList - key:{} - value:{}", key, value, ex);
		} finally {
			close(jedis);
		}
		return value;
	}

	/**
	 * 设置List缓存
	 * 
	 * @param key
	 * @param value
	 * @param cacheSecond 超时时间，0为不超时
	 * @return
	 */
	public static <T> long setList(String key, List<T> value, int cacheSecond) {
		long result = 0;
		Jedis jedis = null;

		if (!ObjectUtil.isEmpty(value)) {
			try {
				jedis = JedisUtil.getResource();
				if(jedis==null){
					throw new RuntimeException("获取redis实例失败");
				}
				if (exist(key)) {
					jedis.del(key);
				}

				result = jedis.append(key, ObjectUtil.list2Json(value));

				if (cacheSecond != 0) {
					jedis.expire(key, cacheSecond);
				}
				//logger.debug("Jedis ===> setList - key:{} - value:{}", key, value);
			} catch (Exception ex) {
				//logger.warn("Jedis ===> setList - key:{} - value:{}", key, value, ex);
			} finally {
				close(jedis);
			}
		}

		return result;
	}

	/**
	 * 是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static  boolean exist(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getResource();
			if(jedis==null){
				throw new RuntimeException("获取redis实例失败");
			}
			result = jedis.exists(key);
			//logger.debug("Jedis ===> exist - key:{}", key);
		} catch (Exception ex) {
			//logger.warn("Jedis ===> exist - key:{}", key, ex);
		} finally {
			close(jedis);
		}
		return result;
	}

	/**
	 * 获取资源
	 * 
	 * @return
	 * @throws JedisException
	 */
	public static Jedis getResource() throws JedisException {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if(jedis==null){
				throw new RuntimeException("获取redis实例失败");
			}
			/*if (StringUtil.isNotBlank("")) {
				jedis.auth("");
			}*/
			//logger.debug("Jedis ===> getResource", jedis);
		} catch (JedisException ex) {
			close(jedis);
			//logger.warn("Jedis ===> getResource", ex);
		}
		return jedis;
	}

	/**
	 * 删除资源
	 * 
	 * @param key
	 * @return
	 */
	public static long del(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = JedisUtil.getResource();
			if(jedis==null){
				throw new RuntimeException("获取redis实例失败");
			}
			if (exist(key)) {
				result = jedis.del(key);
				//logger.debug("Jedis ===> del - key:{}", key);
			}
			else {
				//logger.debug("Jedis ===> del - key:{} not exist", key);
			}
		} catch (Exception e) {
			//logger.warn("del {}", key, e);
		} finally {
			close(jedis);
		}
		return result;
	}

	/**
	 * 释放资源
	 * 
	 * @param jedis
	 * @param isBroken
	 */
	public static void close(Jedis jedis) {
		if (!ObjectUtil.isEmpty(jedis)) {
			jedis.close();
			//logger.debug("Jedis ===> close", jedis);
		}
	}

}
