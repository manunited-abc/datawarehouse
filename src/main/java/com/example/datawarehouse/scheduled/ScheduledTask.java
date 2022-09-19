package com.example.datawarehouse.scheduled;


import com.example.datawarehouse.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Component
public class ScheduledTask {
    @Autowired
    DataService dataService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);
    @Scheduled(cron = "00 10 22 * * ?")
    public void scheduleTaskWithCronExpression(){
        LocalDateTime date = LocalDateTime.now();
        dataService.writeToCSV(date);
        LOGGER.info("Write file success");
    }
}
