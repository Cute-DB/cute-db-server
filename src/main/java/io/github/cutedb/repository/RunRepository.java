package io.github.cutedb.repository;

import io.github.cutedb.model.Run;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by barmi83 on 10/06/16.
 */
@RepositoryRestResource(collectionResourceRel = "runs", path = "runs")
public interface RunRepository extends PagingAndSortingRepository<Run, Long> {

    List<Run> findByJdbcUrl(@Param("jdbcUrl") String jdbcUrl);

}
