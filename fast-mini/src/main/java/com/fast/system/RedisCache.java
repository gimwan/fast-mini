package com.fast.system;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义缓存类
 * 
 * @author J
 *
 */
public class RedisCache implements Cache {

	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private static RedisTemplate<String, Object> redisTemplate;

	private final String id;

	/**
	 * The {@code ReadWriteLock}.
	 */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	public static void setRedisTemplate(RedisTemplate redisTemplate) {
		RedisCache.redisTemplate = redisTemplate;
	}

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug("MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		try {
			if (null != value)
				redisTemplate.opsForValue().set(key.toString(), value, 1, TimeUnit.HOURS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis保存数据异常！");
		}
	}

	@Override
	public Object getObject(Object key) {
		try {
			if (null != key)
				return redisTemplate.opsForValue().get(key.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis获取数据异常！");
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		try {
			if (null != key)
				return redisTemplate.expire(key.toString(), 1, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis获取数据异常！");
		}
		return null;
	}

	@Override
	public void clear() {
		Long size = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
				Long size = redisConnection.dbSize();
				// 连接清除数据
				redisConnection.flushDb();
				redisConnection.flushAll();
				return size;
			}
		});
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>clear: 清除了" + size + "个对象");
	}

	@Override
	public int getSize() {
		Long size = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
		return size.intValue();
	}
	
	public static void set(Object key, Object value) {
		try {
			if (null != value)
				redisTemplate.opsForValue().set(key.toString(), value, 30, TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis保存数据异常！");
		}
	}
	
	public static void set(Object key, Object value, Long seconds) {
		try {
			if (null != value)
				redisTemplate.opsForValue().set(key.toString(), value, seconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis保存数据异常！");
		}
	}
	
	public static Object get(Object key) {
		try {
			if (null != key) {
				return redisTemplate.opsForValue().get(key.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis刷新数据异常！");
		}
		return null;
	}
	
	public static Object retake(Object key) {
		try {
			if (null != key) {
				Object object = redisTemplate.opsForValue().get(key.toString());
				if (object != null) {
					redisTemplate.opsForValue().set(key.toString(), object, 30, TimeUnit.MINUTES);
					return object;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis刷新数据异常！");
		}
		return null;
	}
	
	public static void remove(Object key) {
		try {
			if (null != key)
				redisTemplate.expire(key.toString(), 1, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis删除数据异常！");
		}
	}
}
