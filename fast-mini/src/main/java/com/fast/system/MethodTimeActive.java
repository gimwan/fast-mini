package com.fast.system;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;

import com.fast.system.log.FastLog;

/**
 * 方法运行时间
 * 
 * @author J
 *
 */
public class MethodTimeActive implements MethodInterceptor {

	/**
	 * 拦截要执行的方法
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 创建一个计时器
		StopWatch watch = new StopWatch();
		// 计时器开始
		watch.start();
		// 执行方法
		Object object = invocation.proceed();
		// 计时器停止
		watch.stop();
		// 方法名称
		String methodName = invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName();
		// 获取计时器计时时间
		Long time = watch.getTime();

		String logMsg = methodName + ":" + time + "毫秒";
		System.out.println(logMsg);
		FastLog.info(logMsg);
		
		return object;
	}

}
