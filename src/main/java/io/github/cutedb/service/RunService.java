package io.github.cutedb.service;

import io.github.cutedb.model.Run;
import io.github.cutedb.repository.RunRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barmi83 on 15/06/16.
 */
@Service
public class RunService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunService.class);

    @Autowired
    private RunRepository runRepository;


    public List<Run> findRuns(){
        return IteratorUtils.toList(runRepository.findAll().iterator());
    }

    //public List<Run> findRuns(){
    //    findAll(Pageable pageable);
    //    return IteratorUtils.toList(runRepository.findAll().iterator());
    //}

    public Run addRun(Run run) {
        run.setId(null);
        return runRepository.save(run);
    }

    public Run updateRun(Run updatedRun, Long id) {
        updatedRun.setId(id);
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

}
