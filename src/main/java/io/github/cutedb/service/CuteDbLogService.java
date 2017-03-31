package io.github.cutedb.service;

import io.github.cutedb.model.CuteDbLog;
import io.github.cutedb.model.Run;
import io.github.cutedb.repository.CuteDbLogRepository;
import io.github.cutedb.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by barmi83 on 3/10/17.
 */
@Service
public class CuteDbLogService {

    @Autowired
    CuteDbLogRepository cuteDbLogRepository;
    @Autowired
    RunRepository runRepository;

    public void addLog(CuteDbLog log){
        cuteDbLogRepository.save(log);
    }

    public List<CuteDbLog> findLogsByRunUuid(String uuid){
        Run run = runRepository.findByUuid(uuid);
        List<CuteDbLog> logs = new ArrayList<>();
        if(run != null) {
            logs = cuteDbLogRepository.findAllByRunId(String.valueOf(run.getId()));
            logs.stream()
                    .sorted((l1,l2) -> Long.compare(l1.getId(), l2.getId()));
        }
        return logs;
    }
}
