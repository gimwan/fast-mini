package com.fast.task;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.fast.service.IOrderMaintService;

public class QuartzTask {
	
	@Autowired
	IOrderMaintService iOrderMaintService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public void execute() {
		System.out.println("定时任务开始，" + dateFormat.format(System.currentTimeMillis()));
		
		try {
			// 订单自动取消
			iOrderMaintService.cancelOrderTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("定时任务结束，" + dateFormat.format(System.currentTimeMillis()));
    }

}
