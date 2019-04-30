package com.fast.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统初始化
 * @author J
 *
 */
public class ProjectInit implements ServletContextListener {

	HttpServletRequest request;

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		circle();
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		heart();

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

	private static void heart() {
		for (float y = (float) 1.5; y > -1.5; y -= 0.1) {
			for (float x = (float) -1.5; x < 1.5; x += 0.05) {
				float a = x * x + y * y - 1;
				if ((a * a * a - x * x * y * y * y) <= 0.0) {
					System.out.print("^");
				} else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static void circle() {
		int r = 15;// 圆形的半径
		for (int y = 0; y <= 2 * r; y += 2) {// y的步长为2,改变y的步长可以将圆形变成椭圆
			int x = (int) Math.round(r - Math.sqrt(2 * r * y - y * y));
			int len = 2 * (r - x);

			for (int i = 0; i <= x; i++) {
				System.out.print(' ');
			}
			System.out.print('*');

			for (int j = 0; j <= len; j++) {
				System.out.print(' ');
			}
			System.out.println('*');
		}
	}
}