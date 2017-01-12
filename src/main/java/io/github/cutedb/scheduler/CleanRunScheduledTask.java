package io.github.cutedb.scheduler;

import io.github.cutedb.model.BuildStatus;
import io.github.cutedb.model.Run;
import io.github.cutedb.service.RunService;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by barmi83 on 1/12/17.
 */
@Component
public class CleanRunScheduledTask {

    public static final int MAX_RUNNING_TIME_MINUTES = 2;
    private static final int CHECK_AND_CLEAN_RATE = 600000;


    private static final Logger log = LoggerFactory.getLogger(CleanRunScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    RunService runService;

    @Scheduled(fixedRate = CHECK_AND_CLEAN_RATE)
    public void checkAndClean() {
        log.info("Check and clean run list... : ", dateFormat.format(new Date()));

        List<Run> currentRuns = runService.findByStatus(BuildStatus.RUNNING);
        if(! currentRuns.isEmpty()){
            DateTime now = new DateTime();
            currentRuns.stream().forEach(run -> {
                DateTime runStartedDate = new DateTime(run.getStarted());
                if(Minutes.minutesBetween(runStartedDate, now).getMinutes() > MAX_RUNNING_TIME_MINUTES){
                    run.setStatus(BuildStatus.ABORTED);
                    runService.updateRun(run,run.getId());
                    log.info("Run with uuid : "+run.getUuid()+" has been aborted.");
                }
            });

        }
    }
}
