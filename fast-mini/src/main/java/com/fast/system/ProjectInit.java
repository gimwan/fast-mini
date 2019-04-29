package com.fast.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

/*
 * 系统初始化
 * 
 */
public class ProjectInit implements ServletContextListener {

	HttpServletRequest request;

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("==========初始化信息进行销毁==========");
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		heart(15,0.9,"love");
		
		// 初始化缓存系统
		/*
		 * MemcachedClientTools.ConnectMemcachedService(arg0.getServletContext()
		 * .getInitParameter("memcached-services"));
		 * MybatisMemcache.InitCache(arg0.getServletContext());
		 * CommonCache.InitCache(arg0.getServletContext());
		 */
		// 初始化系统全局变量
		// ...

		// 加载自定义配置文件
		// ...

		// 启动定时任务
		// ...
	}

	private static void heart(int r, double size, String req) {
		size = 1 / (1.5 * r * size);
		StringBuilder sb = new StringBuilder();
		for (int y = r; y > -r; y--, sb.append("\n"))
			for (int x = -2 * r; x < 2 * r; x++) {
				char msg = (req + req).charAt((x - y) % req.length() + req.length());
				sb.append((inHeart(size, x, y) ? msg + " " : " "));
			}
		System.err.println(sb.toString());
	}

	private static boolean inHeart(double size, int x, int y) {
		return Math.pow(Math.pow(x * size, 2) + Math.pow(y * 2 * size, 2) - 1, 3)
				- Math.pow(x * size, 2) * Math.pow(y * 2 * size, 3) <= 0;
	}
}