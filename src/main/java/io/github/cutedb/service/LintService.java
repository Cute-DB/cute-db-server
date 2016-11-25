package io.github.cutedb.service;

import io.github.cutedb.model.Lint;
import io.github.cutedb.model.LintSeverity;
import io.github.cutedb.repository.LintRepository;
import io.github.cutedb.utils.ScoredTable;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Lint> getWorstTables(int nbTables, String uuid){
        List<Lint> allResults = IteratorUtils.toList(lintRepository.findAllByRunUuid(uuid).iterator());
        Map<String, ScoredTable> sortedLints = new HashMap<>();

        allResults.stream().forEach(lint -> {

                    if (!sortedLints.containsKey(lint.getObjectName())) {
                        ScoredTable scoredTable = new ScoredTable(lint.getObjectName());
                        sortedLints.put(lint.getObjectName(), scoredTable);
                    }

                    ScoredTable scoredTable = sortedLints.get(lint.getObjectName());

                    if (lint.getSeverity() == LintSeverity.critical) {
                        scoredTable.setCriticalHits(scoredTable.getCriticalHits()+1);
                    }
                    if (lint.getSeverity() == LintSeverity.high) {
                        scoredTable.setHighHits(scoredTable.getHighHits()+1);
                    }
                    if (lint.getSeverity() == LintSeverity.medium) {
                        scoredTable.setMediumHits(scoredTable.getMediumHits()+1);
                    }
                    if (lint.getSeverity() == LintSeverity.low) {
                        scoredTable.setLowHits(scoredTable.getLowHits()+1);
                    }
                }
        );

        List<ScoredTable> scoredTables = new ArrayList<>(sortedLints.values());
        scoredTables.sort((ScoredTable o1, ScoredTable o2)->o2.getWeightedScore()-o1.getWeightedScore());

        List<String> badTables = new ArrayList<>();
        scoredTables.subList(0, nbTables).stream().forEach(table -> badTables.add(table.getName()));

        List<Lint> finalTopList = new ArrayList<>();
        finalTopList = allResults.stream()
                .filter(lint -> badTables.contains(lint.getObjectName()))
                .collect(Collectors.toList());

        return finalTopList;
    };

}
