package com.tangmo.emall.utils.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.util.Set;

public class JedisUtil {
	/** 操作Key的方法 */
	public Keys KEYS;
	/** 对存储结构为String类型的操作 */
	public Strings STRINGS;
	
	/** Redis连接池对象 */
	private JedisPool jedisPool;

	/**
	 * 获取redis连接池
	 * 
	 * @return
	 */
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	/**
	 * 设置redis连接池
	 * 
	 * @return
	 */
	public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
		this.jedisPool = jedisPoolWriper.getJedisPool();
	}

	/**
	 * 从jedis连接池中获取获取jedis对象
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	


	// *******************************************Keys*******************************************//
	public class Keys {

		/**
		 * 清空所有key
		 */
		public String flushAll() {
			Jedis jedis = getJedis();
			String stata = jedis.flushAll();
			jedis.close();
			return stata;
		}

        /**
         * 批量删除指定前缀的key
         */
        public void batchdel(String prefixKey) {
            Jedis jedis = getJedis();
            Set<String> keys = jedis.keys(prefixKey+"*");
            for (String key : keys) {
                jedis.del(key);
            }
            jedis.close();
        }

		/**
		 * 删除keys对应的记录,可以是多个key
		 * 
		 * @param
		 * @return 删除的记录数
		 */
		public long del(String... keys) {
			Jedis jedis = getJedis();
			long count = jedis.del(keys);
			jedis.close();
			return count;
		}

        /**
         * 删除keys对应的记录,可以是多个key
         *
         * @param
         * @return 删除的记录数
         */
        public long del(byte[]... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }
		/**
		 * 判断key是否存在
		 * 
		 * @param
		 *            key
		 * @return boolean
		 */
		public boolean exists(String key) {
			// ShardedJedis sjedis = getShardedJedis();
			Jedis sjedis = getJedis();
			boolean exis = sjedis.exists(key);
			sjedis.close();
			return exis;
		}

		/**
		 * 查找所有匹配给定的模式的键
		 * 
		 * @param
		 */
		public Set<String> keys(String pattern) {
			Jedis jedis = getJedis();
			Set<String> set = jedis.keys(pattern);
			jedis.close();
			return set;
		}
	}

	// *******************************************Strings*******************************************//
	public class Strings {
		/**
		 * 根据key获取记录
		 * 
		 * @param
		 *            key
		 * @return 值
		 */
		public String get(String key) {
			// ShardedJedis sjedis = getShardedJedis();
			Jedis sjedis = getJedis();
			String value = sjedis.get(key);
			sjedis.close();
			return value;
		}

        /**
         * 根据key获取记录
         *
         * @param key
         * @return 值
         * */
        public byte[] get(byte[] key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] value = sjedis.get(key);
            sjedis.close();
            return value;
        }


		/**
		 * 添加记录,如果记录已存在将覆盖原有的value
		 * 
		 * @param
		 * @param
		 * @return 状态码
		 */
		public String set(String key, String value) {
			return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
		}
		/**
		 * 添加记录,如果记录已存在将覆盖原有的value
		 * 
		 * @param
		 * @param
		 * @return 状态码
		 */
		public String set(byte[] key, byte[] value) {
			Jedis jedis = getJedis();
			String status = jedis.set(key, value);
			jedis.close();
			return status;
		}

        /**
         * 设置过期时间
         *
         * @author ruan 2013-4-11
         * @param key
         * @param seconds
         */
        public void expire(String key, int seconds) {
            if (seconds <= 0) {
                return;
            }
            Jedis jedis = getJedis();
            jedis.expire(key, seconds);
            jedis.close();
        }

        /**
         * 设置过期时间
         *
         * @author ruan 2013-4-11
         * @param key
         * @param seconds
         */
        public void expire(byte[] key, int seconds) {
            if (seconds <= 0) {
                return;
            }
            Jedis jedis = getJedis();
            jedis.expire(key, seconds);
            jedis.close();
        }
	}
}