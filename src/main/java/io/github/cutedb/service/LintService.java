package io.github.cutedb.service;

import io.github.cutedb.model.Lint;
import io.github.cutedb.repository.LintRepository;
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
public class LintService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LintService.class);

    @Autowired
    private LintRepository lintRepository;
    @Autowired
    private RunService runService;

    public List<Lint> findLints(){
        return IteratorUtils.toList(lintRepository.findAll().iterator());
    }


    public List<Lint> findAllByRunUiid(String uuid){
        return IteratorUtils.toList(lintRepository.findAllByRunUuid(uuid).iterator());
    }

    public Lint addLint(io.github.cutedb.dto.Lint lint) {
        lint.setId(null);
        return lintRepository.save(lintDtoToLint(lint));
    }

//    public Run updateRun(Run updatedRun, Long id) {
//        updatedRun.setId(id);
//        return runRepository.save(updatedRun);
//    }

//    public void deleteRun(Long id) {
//        runRepository.delete(id);
//    }
//
//    public String initRun(String jdbcUrl){
//        Run run = new Run();
//        run.setJdbcUrl(jdbcUrl);
//        runRepository.save(run);
//        return run.getUuid().toString();
//    }

    public Lint findbyUuid(String uuid){
        return lintRepository.findByUuid(uuid);
    }

    public Lint lintDtoToLint(io.github.cutedb.dto.Lint lintDto){
        Lint newLint = new Lint();
        newLint.setSeverity(lintDto.getSeverity());
        newLint.setValue(lintDto.getValue());
        newLint.setObjectName(lintDto.getObjectName());
        newLint.setLinter(lintDto.getLinter());
        newLint.setMessage(lintDto.getMessage());
        newLint.setUuid(lintDto.getUuid());
        newLint.setRun(runService.findbyUuid(lintDto.getRunUuid()));
        return newLint;
    }

    public io.github.cutedb.dto.Lint lintToLintDto(Lint lint){
        io.github.cutedb.dto.Lint lintDto = new io.github.cutedb.dto.Lint();
        lintDto.setSeverity(lint.getSeverity());
        lintDto.setValue(lint.getValue());
        lintDto.setObjectName(lint.getObjectName());
        lintDto.setLinter(lint.getLinter());
        lintDto.setMessage(lint.getMessage());
        lintDto.setUuid(lint.getUuid());
        if(lint.getRun() != null)
            lintDto.setRunUuid(lint.getRun().getUuid());
        lintDto.setId(lint.getId());

        return lintDto;
    }

}
