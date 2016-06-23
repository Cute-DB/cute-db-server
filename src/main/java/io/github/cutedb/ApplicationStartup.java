package io.github.cutedb;

import io.github.cutedb.model.BuildStatus;
import io.github.cutedb.model.Run;
import io.github.cutedb.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

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
        run.setUser("toto");
        run.setStatus(BuildStatus.PENDING);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Timestamp(new Date().getTime()));
        run.setEnded(run.getStarted());
        run = repository.save(run);

        run = new Run();
        run.setUser("toto");
        run.setStatus(BuildStatus.FAILURE);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Timestamp(new Date().getTime()));
        run.setEnded(run.getStarted());
        run = repository.save(run);

        run = new Run();
        run.setUser("toto");
        run.setStatus(BuildStatus.SUCCESS);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Timestamp(new Date().getTime()));
        run.setEnded(run.getStarted());
        run = repository.save(run);
    }

} // class
