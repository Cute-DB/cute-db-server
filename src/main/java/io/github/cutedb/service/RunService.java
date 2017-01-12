package io.github.cutedb.service;

import io.github.cutedb.model.BuildStatus;
import io.github.cutedb.model.Lint;
import io.github.cutedb.model.Run;
import io.github.cutedb.repository.LintRepository;
import io.github.cutedb.repository.RunRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static io.github.cutedb.model.LintSeverity.*;

/**
 * Created by barmi83 on 15/06/16.
 */
@Service
public class RunService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunService.class);

    @Autowired
    private RunRepository runRepository;
    @Autowired
    private LintRepository lintRepository;


    public List<Run> findRuns(){
        return IteratorUtils.toList(runRepository.findAll().iterator());
    }

    public List<Run> findByStatus(BuildStatus status){
        return runRepository.findByStatus(status);
    }


    public Run addRun(Run run) {
        run.setId(null);
        return runRepository.save(run);
    }

    public Run updateRun(Run updatedRun, Long id) {
        updatedRun.setId(id);
        if(updatedRun.getStatus() == BuildStatus.SUCCESS){
            List<Lint> lints = lintRepository.findAllByRunUuid(updatedRun.getUuid());

            // Count critical hits
            Stream<Lint> hits = lints.stream().filter(l -> l.getSeverity() == critical);
            updatedRun.setCriticalHits(((Long)hits.count()).intValue());

            // Count high hits
            hits = lints.stream().filter(l -> l.getSeverity() == high);
            updatedRun.setHighHits(((Long)hits.count()).intValue());

            // Count medium hits
            hits = lints.stream().filter(l -> l.getSeverity() == medium);
            updatedRun.setMediumHits(((Long)hits.count()).intValue());

            // Count low hits
            hits = lints.stream().filter(l -> l.getSeverity() == low);
            updatedRun.setLowHits(((Long)hits.count()).intValue());

        }
        return runRepository.save(updatedRun);
    }

    public void deleteRun(Long id) {
        runRepository.delete(id);
    }

    public String initRun(String jdbcUrl){
        Run run = new Run();
        run.setJdbcUrl(jdbcUrl);
        runRepository.save(run);
        return run.getUuid().toString();
    }

    public Run findbyUuid(String uuid){
        return runRepository.findByUuid(uuid);
    }

    public io.github.cutedb.dto.Run runToRunDto(Run run){
        io.github.cutedb.dto.Run runDto = new io.github.cutedb.dto.Run();
        runDto.setId(run.getId());
        runDto.setUuid((run.getUuid()));
        runDto.setHost(run.getHost());
        runDto.setDbHost(run.getDbHost());
        runDto.setDbName(run.getDbName());
        runDto.setCriticalHits(run.getCriticalHits());
        runDto.setJdbcUrl(run.getJdbcUrl());
        runDto.setDatabaseProductName(run.getDatabaseProductName());
        runDto.setStarted(run.getStarted());
        runDto.setEnded(run.getEnded());
        runDto.setRunnerVersion(run.getRunnerVersion());
        runDto.setSchemaCrawlerVersion(run.getSchemaCrawlerVersion());
        runDto.setAdditionalLintsVersion(run.getAdditionalLintsVersion());
        runDto.setStatus(run.getStatus());
        runDto.setHighHits(run.getHighHits());
        runDto.setLowHits(run.getLowHits());
        runDto.setMediumHits(run.getMediumHits());

        return runDto;
    }


}
