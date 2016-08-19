package io.github.cutedb;

import io.github.cutedb.model.BuildStatus;
import io.github.cutedb.model.Run;
import io.github.cutedb.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by barmi83 on 24/06/16.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    RunRepository repository;


    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        Run run = new Run();
        run.setUuid(UUID.randomUUID().toString());
        run.setUser("toto");
        run.setStatus(BuildStatus.PENDING);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Date());
        run.setEnded(run.getStarted());
        run = repository.save(run);

        run = new Run();
        run.setUuid(UUID.randomUUID().toString());
        run.setUser("toto");
        run.setStatus(BuildStatus.FAILURE);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Date());
        run.setEnded(run.getStarted());
        run.setCriticalHits(5);
        run = repository.save(run);

        run = new Run();
        run.setUuid(UUID.randomUUID().toString());
        run.setUser("toto");
        run.setStatus(BuildStatus.SUCCESS);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Date());
        run.setEnded(run.getStarted());
        run.setHighHits(3);
        run.setMediumHits(1);
        run.setLowHits(2);
        run = repository.save(run);

        run = new Run();
        run.setUuid(UUID.randomUUID().toString());
        run.setUser("toto");
        run.setStatus(BuildStatus.RUNNING);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Date());
        run.setEnded(run.getStarted());
        run = repository.save(run);
    }

} // class
