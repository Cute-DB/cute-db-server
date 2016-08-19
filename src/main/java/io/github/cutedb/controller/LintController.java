package io.github.cutedb.controller;

import io.github.cutedb.model.Lint;
import io.github.cutedb.service.LintService;
import io.github.cutedb.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by barmi83 on 15/06/16.
 */
@RestController
@RequestMapping(value = "/lints")
public class LintController {

    private final List<SseEmitter> sseEmitters = new LinkedList<>();

    @Autowired
    private LintService lintService;
    @Autowired
    private RunService runService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Lint> findItems() {
        return lintService.findLints();
    }

    @RequestMapping(value="/run/{uuid}",method = RequestMethod.GET)
    public List<Lint> findLintsByRunUuid(@PathVariable String uuid) {
        return lintService.findAllByRunUiid(uuid);
    }

    @RequestMapping(value="/uuid/{uuid}", method = RequestMethod.POST)
    public io.github.cutedb.dto.Lint addLint(@RequestBody io.github.cutedb.dto.Lint lint, @PathVariable String uuid) {
        Lint newLint = lintService.addLint(lint);
        return lintService.lintToLintDto(newLint);
    }

    @RequestMapping(value ="/uuid/{uuid}", method = RequestMethod.GET)
    public Lint findByUuid(@PathVariable String uuid){
        return lintService.findbyUuid(uuid);
    }

//    @RequestMapping (path = "/register", method = RequestMethod.GET)
//    public SseEmitter register() throws IOException {
//
//        SseEmitter emitter = new SseEmitter();
//
//        synchronized (sseEmitters) {
//            sseEmitters.add(emitter);
//        }
//        emitter.onCompletion(() -> sseEmitters.remove(emitter));
//
//        return emitter;
//
//    }

}
