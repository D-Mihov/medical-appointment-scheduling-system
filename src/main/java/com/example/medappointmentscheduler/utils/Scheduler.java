package com.example.medappointmentscheduler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    // 5000 - 5 seconds in milliseconds, multiplied by 1000
//    @Scheduled(fixedRate = 5000)
//    public void testScheduling() {
//        log.debug("Email sent");
//    }

//    @Scheduled(cron = "* * 15 * * *")
//    public void testCron() {
//        log.debug("Cron");
//    }
}
