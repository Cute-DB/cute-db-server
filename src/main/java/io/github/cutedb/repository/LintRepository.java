package io.github.cutedb.repository;

import io.github.cutedb.model.Lint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by barmi83 on 05/08/16.
 */
public interface LintRepository extends PagingAndSortingRepository<Lint, Long> {

    Lint findByUuid(@Param("uuid") String uuid);

    List<Lint> findAllByRunUuid(@Param("id_run") String runUuid);
}
