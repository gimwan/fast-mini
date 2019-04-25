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

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("==========初始化信息进行销毁==========");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("==========系统初始化==========");
		System.out.println("==========..........==========");

		// 初始化缓存系统 
		/*MemcachedClientTools.ConnectMemcachedService(arg0.getServletContext()
				.getInitParameter("memcached-services"));
		MybatisMemcache.InitCache(arg0.getServletContext());
		CommonCache.InitCache(arg0.getServletContext());*/
		// 初始化系统全局变量
		// ...

		
		// 加载自定义配置文件
		// ...

		// 启动定时任务
		// ...
	}
}