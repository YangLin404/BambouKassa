package be.linyang.kassa.ScheduledTasks;

import be.linyang.kassa.RestoManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataReloader {
    private static final Logger LOGGER = LogManager.getLogger(DataReloader.class);

    @Autowired
    private RestoManager restoManager;

    @Scheduled(cron = "0 0 12 * * *")
    public void reloadData() {
        if (restoManager.reloadData()) {
            LOGGER.info("sheduled task: reload resto manager data succeed");
        } else {
            LOGGER.error("sheduled task: reload resto manager data failed");
        }

    }
}
