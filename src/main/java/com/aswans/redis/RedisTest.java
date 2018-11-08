package com.aswans.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisTest {

	public static void main(String[] args) {
		/*
		 * 基本的测试
		 
		Jedis jedis = new Jedis("192.168.20.199", 6379);
		//jedis.auth("123456");
		jedis.set("foo", "bar1");
		String value1 = jedis.get("foo");
		System.out.println(value1);
		*/
		/**
		 * redis池测试
		 */
		/*List strlist = new ArrayList();
		strlist.add("aa");
		strlist.add("bb");
		JedisUtil.setList("strlist", strlist, 0);
		List slist_value = JedisUtil.getList("strlist", String.class);
		System.out.println(slist_value);
		*/
		List<Mark> beanlist = new ArrayList<Mark>();
		beanlist.add(new Mark("zhang", 11));
		beanlist.add(new Mark("lisi", 45));
		JedisUtil.setList("beanlist", beanlist, 0);
		List value_beanlist = JedisUtil.getList("beanlist", Mark.class);
		Mark mark = (Mark)value_beanlist.get(0);
		System.out.println(mark.getName());
		
		String aa = "{\"initial\":\"R\",\"likes\":\"Apple Pie\",\"name\":\"reiz\"}";
		JedisUtil.set("jsonKey", aa, 0);
		System.out.println(JedisUtil.get("jsonKey"));

		/*List maplist = new ArrayList();
		Map hashMap = new HashMap();
		hashMap.put("111", "dddd");
		Map hashMap1 = new HashMap();
		hashMap1.put("222", "eeee");
		maplist.add(hashMap);
		maplist.add(hashMap1);
		JedisUtil.setList("maplist", maplist, 0);
		List value_maplist = JedisUtil.getList("maplist", Map.class);
		Map map = (Map)value_maplist.get(0);
		System.out.println(map.get("111"));*/
		
		/**
		 * redis集群测试
		 
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		// Jedis Cluster will attempt to discover cluster nodes automatically
		jedisClusterNodes.add(new HostAndPort("192.168.20.199", 9001));
		jedisClusterNodes.add(new HostAndPort("192.168.20.199", 9002));
		jedisClusterNodes.add(new HostAndPort("192.168.20.199", 9003));
		jedisClusterNodes.add(new HostAndPort("192.168.20.199", 9004));
		jedisClusterNodes.add(new HostAndPort("192.168.20.199", 9005));
		jedisClusterNodes.add(new HostAndPort("192.168.20.199", 9006));
		JedisCluster jc = new JedisCluster(jedisClusterNodes);
		jc.set("qq", "22");
		String value_c = jc.get("qq");
		System.out.println(value_c);*/
	}
}
