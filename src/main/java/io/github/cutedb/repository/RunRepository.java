package io.github.cutedb.repository;

import io.github.cutedb.model.Run;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by barmi83 on 10/06/16.
 */
public interface RunRepository extends PagingAndSortingRepository<Run, Long> {

    List<Run> findByJdbcUrl(@Param("jdbcUrl") String jdbcUrl);

}
