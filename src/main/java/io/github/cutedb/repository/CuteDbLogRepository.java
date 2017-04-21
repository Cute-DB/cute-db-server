package io.github.cutedb.repository;

import io.github.cutedb.model.CuteDbLog;
import io.github.cutedb.model.Lint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by barmi83 on 3/10/17.
 */
public interface CuteDbLogRepository extends PagingAndSortingRepository<CuteDbLog, Long> {

    List<CuteDbLog> findAllByRunId(@Param("id_run") String runId);

}
