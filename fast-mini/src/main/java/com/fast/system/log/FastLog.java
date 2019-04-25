package com.fast.system.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志类
 * @author gimwan
 *
 */
public class FastLog {

	private static final Logger log = LoggerFactory.getLogger(FastLog.class);

	public static void debug(String msgs) {
		log.debug(msgs);
	}

	public static void infoMsg(String msgs) {
		log.info(msgs);
	}

	public static void info(String msgs) {
		log.info(msgs);
	}
	
	public static void error(String msgs) {
		log.error(msgs);
	}
	
	public static void error(String msgs, Throwable e) {
	    log.error(msgs, e);
	}
}
