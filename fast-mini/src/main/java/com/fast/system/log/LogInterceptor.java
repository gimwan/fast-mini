package com.fast.system.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogInterceptor {
	static Logger logger = Logger.getLogger(LogInterceptor.class);

	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		StringBuffer sb = new StringBuffer();
		try {
			Object result = joinPoint.proceed();
			return result;
		} catch (Exception e) {
			sb.append("开始方法：" + joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + "()  ");
			sb.append("错误信息如下：[" + e.getMessage() + "]");
			logger.error(sb.toString());
		}
		// 因为用到了strut2，所以出现异常会返回到<result name="error">/erreo.jsp</result>这个页面中，当然啦，你也可以配置成全局的异常返回页。
		return "error";
	}
}
