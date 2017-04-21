package io.github.cutedb.controller;

import io.github.cutedb.model.CuteDbLog;
import io.github.cutedb.service.CuteDbLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by barmi83 on 2/13/17.
 */
@RestController
@RequestMapping(value = "/logs")
public class LogController {

    @Autowired
    CuteDbLogService cuteDbLogService;

    @RequestMapping(method = RequestMethod.POST)
    public void addLog(@RequestBody CuteDbLog log) {
        cuteDbLogService.addLog(log);
    }
}
