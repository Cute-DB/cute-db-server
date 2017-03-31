package io.github.cutedb.controller;

import io.github.cutedb.dto.Lint;
import io.github.cutedb.model.Run;
import io.github.cutedb.service.LintService;
import io.github.cutedb.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by barmi83 on 15/06/16.
 */
@RestController
@RequestMapping(value = "/runs")
public class RunController {

    private List<SseEmitter> sseEmitters = new LinkedList<>();

    @Autowired
    private RunService runService;
    @Autowired
    private LintService lintService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Run> findItems() {
        return runService.findRunsExceptAborted();
    }


    @RequestMapping(value="/uuid/{uuid}", method = RequestMethod.POST)
    public Run addRun(@RequestBody Run run, @PathVariable String uuid) {
        run.setId(null);
        run.setUuid(uuid);
        run = runService.addRun(run);

        synchronizeWithUi(run);

        return run;
    }

    @RequestMapping(value = "uuid/{uuid}", method = RequestMethod.PUT)
    public io.github.cutedb.dto.Run updateRun(@RequestBody Run updatedRun, @PathVariable String uuid) {
        Run run = runService.findbyUuid(uuid);
        run.setStatus(updatedRun.getStatus());
        run.setEnded(updatedRun.getEnded());
        run = runService.updateRun(run,run.getId());
        synchronizeWithUi(run);
        return runService.runToRunDto(run);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRun(@PathVariable Long id) {
        runService.deleteRun(id);
    }

    @RequestMapping(value ="/uuid", method = RequestMethod.GET)
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }

    @RequestMapping(value ="/uuid/{uuid}", method = RequestMethod.GET)
    public Run findByUuid(@PathVariable String uuid){
        return runService.findbyUuid(uuid);
    }


    @RequestMapping(value ="/uuid/{uuid}/lints", method = RequestMethod.GET)
    public List<Lint> findAllLintsByRunUuid(@PathVariable String uuid){
        List<io.github.cutedb.model.Lint> lintsList = lintService.findAllByRunUiid(uuid);
        List<Lint> finalList = new ArrayList<>();

        lintsList.forEach(item-> finalList.add(lintService.lintToLintDto(item)));
        return finalList;
    }

    @RequestMapping(value ="/uuid/{uuid}/lints/top/{nbTable}", method = RequestMethod.GET)
    public List<Lint> findBadTables(@PathVariable String uuid, @PathVariable int nbTable){
        List<io.github.cutedb.model.Lint> lintsList = lintService.getWorstTables(nbTable, uuid);
        List<Lint> finalList = new ArrayList<>();

        lintsList.forEach(item -> finalList.add(lintService.lintToLintDto(item)));
        return finalList;
    }




    @RequestMapping (path = "/register", method = RequestMethod.GET)
    public SseEmitter register() throws IOException {
        System.out.println("register");
        System.out.println("sseEmitters"+sseEmitters);
        SseEmitter emitter = new SseEmitter();

        synchronized (sseEmitters) {
            sseEmitters.add(emitter);
        }
        emitter.onCompletion(() -> sseEmitters.remove(emitter));

        return emitter;

    }

    private void synchronizeWithUi(Run run){
        synchronized (sseEmitters) {
            for (SseEmitter emitter : sseEmitters) {
                try {
                    emitter.send(run, MediaType.APPLICATION_JSON);
                } catch (IOException e) {
                    emitter.complete();
                    sseEmitters.remove(emitter);
                }
            }

        }
    }


}
