package com.marondal.memo.user.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserScheduler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	//@Scheduled(fixedDelay=5000)
	@Scheduled(cron="0/5 * * * * ?")
	public void run() {
		logger.error("스케쥴러 실행!!");
	}

}
