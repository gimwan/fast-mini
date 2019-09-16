package com.fast.task;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.fast.service.IOrderMaintService;
import com.fast.service.IVipMaintService;

public class QuartzTask {
	
	@Autowired
	IVipMaintService iVipMaintService;
	
	@Autowired
	IOrderMaintService iOrderMaintService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public void execute() {
		System.out.println("定时任务开始，" + dateFormat.format(System.currentTimeMillis()));
		
		try {
			// 推送会员
			iVipMaintService.pushVipTask();
			// 订单自动取消
			iOrderMaintService.cancelOrderTask();
			// 推送订单
			iOrderMaintService.pushOrderTask();
			// 更新订单状态
			//iOrderMaintService.changeOrderStatusTask();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("定时任务结束，" + dateFormat.format(System.currentTimeMillis()));
    }

}
