package io.github.cutedb.controller;

import io.github.cutedb.model.Run;
import io.github.cutedb.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by barmi83 on 15/06/16.
 */
@RestController
@RequestMapping(value = "/runs")
public class RunController {

    @Autowired
    private RunService runService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Run> findItems() {
        return runService.findRuns();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Run addRun(@RequestBody Run run) {
        run.setId(null);
        return runService.addRun(run);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Run updateRun(@RequestBody Run updatedRun, @PathVariable Long id) {
        return runService.updateRun(updatedRun,id);
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
}
