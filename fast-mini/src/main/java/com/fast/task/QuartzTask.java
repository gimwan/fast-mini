package com.fast.task;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.fast.service.ext.IExtMaintService;

public class QuartzTask {
	
	@Autowired
	IExtMaintService iExtMaintService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public void execute() {
		System.out.println("定时任务开始，" + dateFormat.format(System.currentTimeMillis()));
		
		try {
			// 推送会员
			iExtMaintService.pushVipTask();
			// 订单自动取消
			iExtMaintService.cancelOrderTask();
			// 更新订单状态
			iExtMaintService.changeOrderStatusTask();
			// 推送订单
			iExtMaintService.pushOrderTask();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("定时任务结束，" + dateFormat.format(System.currentTimeMillis()));
    }

}
