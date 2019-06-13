package com.javamail.EmailJava.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.javamail.EmailJava.scheduler.service.CheckEmail;

@Component
@EnableScheduling
public class EmailScheduler {

	@Autowired
    private CheckEmail check;	
	
     @Scheduled(cron = "*/15 * * * * *")
	 public void checkEmail() {
	    this.check.setEmailProperties();
	 }
}
